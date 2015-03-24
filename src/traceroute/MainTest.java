package traceroute;

import java.util.Scanner;

public class MainTest {
	public static int serviceGeoChoice=1;
	
	public static void changeService(){
		if (serviceGeoChoice==1)
			serviceGeoChoice=2;
		else
			serviceGeoChoice=1;
	}
	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		String url;
		
		System.out.println("Le service de géolocalisation par defaut est GEOIP");
		 
			System.out.println("Commencons");
			url= sc.next();
			if (url.equals("0")){
				changeService();
				System.out.println("Service changé, Start now");
				url=sc.next();
			}
				Traceroute tr= new Traceroute(url);
				Traceroute tr2= new Traceroute("Facebook.com");
				tr.start();
				tr.join();
				tr2.start();
				tr2.join();
				
				Graphe graphe = new Graphe(tr.getResultTraceroute());
				//System.out.println("C'est la fin");
	}
}
