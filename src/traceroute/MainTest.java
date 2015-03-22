package traceroute;

import java.util.Scanner;

public class MainTest {
	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		String url;
		
			System.out.println("Commencons");
			url= sc.next();
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
