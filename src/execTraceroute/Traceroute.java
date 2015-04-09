package execTraceroute;

import interactionUser.StartWithUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Traceroute extends Thread {
	private final int hops;
	private final String IP;
	
	public Traceroute(String IP,int hops) {
		// TODO Auto-generated constructor stub
		this.IP=IP;
		this.hops=hops;
	}

	public String getIP() {
		return IP;
	}

	public int getHops() {
		return hops;
	}
	
	private String findCommandExec(){
		if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
			return "tracert";
		else
			return "traceroute";
	}
	
	public void run() {
		Runtime runtime = Runtime.getRuntime();
		Process process;
		String[] args1 = { findCommandExec(), "-h", Integer.toString(hops),
					"-d",this.IP };
		try {
			process = runtime.exec(args1);
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			line = br.readLine();
			line = br.readLine();
				while ((line = br.readLine()) != null) {
					System.out.println("<"+IP+"> "+line);
					StartWithUser.getMyGraph().
					beforeAddingVertexIn(this.IP,extractNode(line));			
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String extractNode(String line) {
		Pattern p = Pattern.compile(StartWithUser.getRegexipv4());
		Matcher m = p.matcher(line);
		while (m.find()) {
			return m.group(0);
		}
		return null;
	}

}
