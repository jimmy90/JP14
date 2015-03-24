package traceroute;

import geolocalisation.LocationByGeoIp;
import geolocalisation.LocationByHostIp;

public class Sommet {
	private String noeud,cityName,countryName;
	private double longitude, latitude;
	
	public Sommet(String Ip) {
		// TODO Auto-generated constructor stub
		this.noeud=Ip;
		longitude=latitude=0;
		if (MainTest.serviceGeoChoice==1){
			System.out.println(Ip);
			LocationByGeoIp loc= new LocationByGeoIp(Ip);
			cityName=loc.getCityName();
			countryName=loc.getCountryName();
			longitude=loc.getLongitude();
			latitude=loc.getLatitude();
		}else{
			LocationByHostIp loc = new LocationByHostIp(Ip);
			cityName=loc.getCityName();
			countryName=loc.getCountryName();
			longitude=loc.getLongitude();
			latitude=loc.getLatitude();
		}
	}
	
	public Sommet(String Ip, double longitude, double latitude){
		this.noeud=Ip;
		this.longitude=longitude;
		this.latitude=latitude;
	}

	public String getNoeud() {
		return new String(noeud);
	}

	public void setNoeud(String noeud) {
		this.noeud = noeud;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof Sommet){
			Sommet s= (Sommet) obj;
			return (s.noeud.equals(noeud))
					&&(s.getLatitude()==latitude)
					&&(s.getLongitude()==longitude);
		}
		return false;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.noeud+"( Longitude= "+this.longitude+
				", latitude= "+this.latitude+
				")";
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	
}
