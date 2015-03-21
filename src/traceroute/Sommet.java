package traceroute;

public class Sommet {
	private String noeud;
	private double longitude, latitude;
	
	public Sommet(String Ip) {
		// TODO Auto-generated constructor stub
		this.noeud=Ip;
		longitude=latitude=0;
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
	
	
}
