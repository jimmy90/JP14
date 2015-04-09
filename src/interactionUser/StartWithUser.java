package interactionUser;

import ihm.dialog.ErrorJOptionPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import dijkstra.Dijkstra;
import entities.Graph;
import execTraceroute.Traceroute;

public class StartWithUser {

	protected static Scanner sc = new Scanner(System.in);
	private ArrayList<String> targets = new ArrayList<String>();
	protected static final String REGEXURL = "(@)?(href=')?(HREF=')?(HREF=\")?(href=\")?"
			+ "(http://)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?";
	protected static final String REGEXIPV4 = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?"
			+ "[0-9][0-9]?)\\.){3}(?:25[0-5]|"
			+ "2[0-4][0-9]|[01]?[0-9][0-9]?)";
	private static int serviceGeoChoice = 1;
	private int nbExecSameTime = 0;
	protected static Graph myGraph;
	protected int nbHOPS = 30;
	private ExecutorService service;
	private ArrayList<String> listTrStart;

	public ArrayList<String> getListTrStart() {
		return listTrStart;
	}

	public StartWithUser() {
		// TODO Auto-generated constructor stub
		myGraph = new Graph();
		listTrStart = new ArrayList<String>();
//		try {
//			service.awaitTermination(1, TimeUnit.MINUTES);
//			Dijkstra.computePaths(myGraph.getSource());
//			System.out.println(myGraph);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public void TerminateExecution(){
		service.shutdown();
	}
	
	public boolean checkValue(String IpOrName){
		Pattern P_url = Pattern.compile(REGEXURL);
		Pattern P_ipv4 = Pattern.compile(REGEXIPV4);
		return (P_url.matcher(IpOrName).find()
				|| P_ipv4.matcher(IpOrName).find());
	}
	
	public void addTrIntoGraph(String IpOrName){
		service.execute(new Traceroute(IpOrName, nbHOPS));
	}
	
	public String generateRandomIp(){
		Random R = new Random();
		return R.nextInt(256) + 
				"." + R.nextInt(256) + 
				"." + R.nextInt(256) + "." + R.nextInt(256);
	}

	public int getNbExecSameTime() {
		return nbExecSameTime;
	}

	public void setNbExecSameTime(int nbExecSameTime) {
		service = Executors.newFixedThreadPool(nbExecSameTime);
		this.nbExecSameTime = nbExecSameTime;
	}

	public void setNbHOPS(int nbHOPS) {
		this.nbHOPS= nbHOPS;
	}

	private void possibilityToChangeService() {
		System.out
				.println("Le service de géolocalisation par defaut est GEOIP");
		System.out.println("Pour changer , tapez C ");
		if (sc.next().toUpperCase().equals("C"))
			changeService();
	}

	public static void changeService() {
		if (getServiceGeoChoice() == 1)
			setServiceGeoChoice(2);
		else
			setServiceGeoChoice(1);
	}

	private int nbTargets() {
		System.out.println("Nombre de cibles à ajouter");
		try {
			return sc.nextInt();
		} catch (Exception e) {
			return nbTargets();
		}
	}

	private void nbHOPS() {
		System.out.println("Nombre de sauts max pour chacun des traceroutes ");
		try {
			nbHOPS = sc.nextInt();

		} catch (Exception e) {
			nbHOPS();
		}
	}

	public ArrayList<String> getTargets() {
		return targets;
	}

	public static String getRegexurl() {
		return REGEXURL;
	}

	public static String getRegexipv4() {
		return REGEXIPV4;
	}

	public static Graph getMyGraph() {
		return myGraph;
	}
	
	public int getNbHOPS(){
		return this.nbHOPS;
	}

	private void execInsameTime() {
		System.out.println("Nombre d'execution simultanement");
		try {
			int nb = sc.nextInt();
			if (nb < 1)
				execInsameTime();
			if (nb > this.targets.size())
				nbExecSameTime = nb;
			else
				nbExecSameTime = nb;
		} catch (Exception e) {
			execInsameTime();
		}
	}

	private void addTargets(int nbTargets) {
		boolean exit = false;
		Pattern P_url = Pattern.compile(REGEXURL);
		Pattern P_ipv4 = Pattern.compile(REGEXIPV4);
		System.out.println(" Saisir les cibles ");
		String write;
		while (!exit) {
			write = sc.next();
			if (this.targets.size() == nbTargets || write.equals("q"))
				exit = true;
			else {
				if (!this.targets.contains(write)) {
					if (P_url.matcher(write).find()
							|| P_ipv4.matcher(write).find()) {
						this.targets.add(write);
					}
				}
			}
		}
	}

	private synchronized void executeThreads() {
		for (String target : targets) {
			service.execute(new Traceroute(target,this.nbHOPS));
		}
	}

	public static int getServiceGeoChoice() {
		return serviceGeoChoice;
	}

	public static void setServiceGeoChoice(int serviceGeoChoice) {
		StartWithUser.serviceGeoChoice = serviceGeoChoice;
	}

	public ExecutorService getService() {
		return service;
	}

}
