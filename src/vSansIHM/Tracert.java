package vSansIHM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Tracert extends Thread {
	private String[] args;
	static ArrayList<Ip> listeip = new ArrayList<Ip>();

	ArrayList<String> res = new ArrayList<String>();

	Tracert(String[] args, int nbsauts) {
		this.args = args;

	}

	@Override
	public synchronized void run() {

		Runtime runtime = Runtime.getRuntime();
		Process process;
		try {
			System.out.println("Debut "+args[args.length-1]);
			process = runtime.exec(args);
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			// List res = new ArrayList<String>();

			System.out.printf("Output of running %s is:", Arrays.toString(args));
			while ((line = br.readLine()) != null) {
				CreationIp(line);
			}
			System.out.println("Taille de la liste "+listeip.size());
			for (int y = 0; y < listeip.size(); y++)
				System.out.println(listeip.get(y).ip);
			System.out.println("Fin "+args[args.length-1]);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private synchronized void CreationIp(String line){
		Pattern p=Pattern.compile(Main.ipRegex);
		 String [] str= line.split(" ");
		 for (String s : str){
			 if (p.matcher(s).find()){
				 listeip.add(new Ip(s));
			 }
		 }
	}
	
	public ArrayList<Ip> getListeip() {
		return listeip;
	}

}
