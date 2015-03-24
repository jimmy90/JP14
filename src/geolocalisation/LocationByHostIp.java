package geolocalisation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LocationByHostIp {
	private double longitude,latitude;
	private String cityName,countryName;
	public LocationByHostIp(String IP) {
		try {
			try {
				this.parseXMLFile(this.getXmlFileByUrl(IP));
			} catch (SAXException | ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private BufferedReader getXmlFileByUrl(String IP) throws IOException{
		URL hostip_url = new URL("http://api.hostip.info/?ip=" + IP);
		URLConnection url_connection = hostip_url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				url_connection.getInputStream()));
		return in;
	}
	
	private void parseXMLFile(BufferedReader in) throws SAXException, IOException, ParserConfigurationException{
try {
			
			String inputLine;
			String A = "";

			while ((inputLine = in.readLine()) != null) {
				A = A + inputLine;
			}
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler() {
				Boolean D = false;
				boolean cityName=false;
				boolean countryName=false;
				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {
					if (qName.equals("gml:coordinates")) {
						D = true;
					}
					if (qName.equals("gml:name")){
						cityName=true;
					}
					if (qName.equals("countryName")){
						countryName=true;
					}
				}

				public void endElement(String uri, String localName,
						String qName) throws SAXException {
					if (qName.equals("gml:coordinates")) {
						D = false;
					}
					if (qName.equals("gml:name")){
						cityName=false;
					}
					if (qName.equals("countryName")){
						countryName=false;
					}
				}

				public void characters(char ch[], int start, int length)
						throws SAXException {
					if (D) {
						String A = new String(ch, start, length);
						String [] Coordonnes=A.split(",");
						longitude=Double.parseDouble(Coordonnes[0]);
						latitude=Double.parseDouble(Coordonnes[1]);
					}
					if (cityName){
						LocationByHostIp.this.cityName=new String(ch, start, length).split(",")[0];
					}
					if (countryName){
						LocationByHostIp.this.countryName=new String(ch, start, length);
					}

				}
			};
			saxParser.parse(new InputSource(new StringReader(A)), handler);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public String getCityName() {
		return cityName;
	}

	public String getCountryName() {
		return countryName;
	}
}
