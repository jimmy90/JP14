package vSansIHM;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The main program used to invoke the application.
 */
public class Main {
	
	public final static String ipRegex="^\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?"
			+ "[0-9][0-9]?)\\.){3}"
			+ "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b$";
	public final static String IPADDRESSV4_PATTERN = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?"
														+ "[0-9][0-9]?)\\.){3}(?:25[0-5]|"
														+ "2[0-4][0-9]|[01]?[0-9][0-9]?)";

	public static void main(String[] args) {
		
		Pattern p = Pattern.compile(IPADDRESSV4_PATTERN);
		String arg0="uisdf iusfhsf zeufh 199.199.64.2 ejf iez12.25.0.255";
		Matcher m = p.matcher(arg0);
		while (m.find()){
			System.out.println(m.group(0));
		}
		System.out.println(p.matcher("199.199.64.2").find());
		System.out.println(p.matcher("1.0.255.0").find());
		System.out.println(p.matcher("12.25.0.255").find());
		System.out.println(p.matcher("25.199.64.97").find());
		String[] args1 = { "tracert", "-h", "20", "-d", "www.wikipedia.fr" };
		//String[] args2 = { "tracert", "-d", "www.gmail.com" };
		//Tracert t = new Tracert(args2, 30);
		//t.start();
		Tracert t1 = new Tracert(args1, 30);
		t1.start();

	}
}