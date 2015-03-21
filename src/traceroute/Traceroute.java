package traceroute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Traceroute extends Thread {
	public static int DEFAULTHOPS = 30;
	public final static String IPADDRESSV4_PATTERN = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?"
			+ "[0-9][0-9]?)\\.){3}(?:25[0-5]|"
			+ "2[0-4][0-9]|[01]?[0-9][0-9]?)";

	private LinkedList<ArcSommet> resultTraceroute = new LinkedList<ArcSommet>();
	private int hops;
	private String url;
	private Sommet tmpSommet;

	public Traceroute(String url) {
		// TODO Auto-generated constructor stub
		this.url = url;
		this.hops = DEFAULTHOPS;
		tmpSommet= new Sommet("192.168.1.254");
		
	}

	public Traceroute(String url, int hops) {
		// TODO Auto-generated constructor stub
		this.url = url;
		this.hops = hops;
		tmpSommet= new Sommet("192.168.1.254");
	}

	public synchronized void addArc(Sommet v1, Sommet v2) {
		this.resultTraceroute.add(new ArcSommet(v1, v2));
	}

	public synchronized void run() {
		Runtime runtime = Runtime.getRuntime();
		Process process;
		String[] args1 = { "tracert", "-h", Integer.toString(hops), "-d", this.url };
		System.out.println(tmpSommet);
		try {
			System.out.println("Traceroute " + this.url + " lancé ");
			process = runtime.exec(args1);
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			System.out
					.printf("Output of running %s is:", Arrays.toString(args1));
			line = br.readLine();
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				System.out.println(line);
						Sommet V= this.createSommet(line);
						if (V!=null && tmpSommet!=null){
							//Faire un test pour eviter des arc avec sommets identiques
							if (!V.equals(tmpSommet)){
								this.addArc(tmpSommet, this.createSommet(line));
								System.out.println(tmpSommet.getNoeud()+"-"+V.getNoeud());
							}
						}
						this.tmpSommet=V;
			}
			System.out.println("Fin "+getResultTraceroute().size());
			Graphe g= new Graphe(resultTraceroute);
			System.out.println(g);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private synchronized Sommet createSommet(String line) {
		Pattern p = Pattern.compile(IPADDRESSV4_PATTERN);
		Matcher m = p.matcher(line);
		while (m.find()) {
			return new Sommet(m.group(0));
		}
		return null;
	}

	public synchronized LinkedList<ArcSommet> getResultTraceroute() {
		return resultTraceroute;
	}
}
