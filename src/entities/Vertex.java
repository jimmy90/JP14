package entities;

import geolocalisation.LocationByGeoIp;
import geolocalisation.LocationByHostIp;
import interactionUser.StartWithUser;

import java.util.LinkedList;
import java.util.List;

import dijkstra.Dijkstra;
/**
 * 
 * @author Jimmy and Corentin
 * Vertex est un sommet(IP) constitué de coordonnées GPS, pays et ville de localisation,une liste de sommet voisins,un sommet père appelé predecessor(null au départ)
 * le nombre de fois qu'il a été rencontré initialisé à 1 et une distance à l'origine initialisée à plus l'infini.
 * Predecessor est affecté lors du calcul du plus court chemin, seuk le sommet à l'origine a un predecessor null
 *
 */
public class Vertex implements Comparable<Vertex> {
	private String node,cityName,countryName;
	private double longitude, latitude;
	private int nbView,distance;
	private LinkedList<Vertex> neighbours;
	private Vertex predecessor=null;
	
	public Vertex(String Ip) {
		// TODO Auto-generated constructor stub
		setNeighbours(new LinkedList<Vertex>());
		this.node=Ip;
		this.setNbView(1);
		longitude=latitude=0;
		findLocation();
		distance =  Integer.MAX_VALUE;
	}
	/**
	 * 
	 * Trouve les coordonnées GPS (longitude et latitude) +
	 * le pays et la ville de localisation en fonction du systeme
	 * de géolocalisation (HostIP ou GéoIP)
	 * 
	 */
	private void findLocation(){
		if (StartWithUser.getServiceGeoChoice()==1){
			LocationByGeoIp loc= new LocationByGeoIp(node);
			cityName=loc.getCityName();
			countryName=loc.getCountryName();
			longitude=loc.getLongitude();
			latitude=loc.getLatitude();
		}else{
			LocationByHostIp loc = new LocationByHostIp(node);
			cityName=loc.getCityName();
			countryName=loc.getCountryName();
			longitude=loc.getLongitude();
			latitude=loc.getLatitude();
		}
	}
	
	public Vertex(String Ip, double longitude, double latitude){
		this.node=Ip;
		this.longitude=longitude;
		this.latitude=latitude;
		distance=Integer.MAX_VALUE;
	}

	public String getNoeud() {
		return new String(node);
	}

	public void setNoeud(String noeud) {
		this.node = noeud;
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
		if (obj instanceof Vertex){
			Vertex s= (Vertex) obj;
			return (s.node.equals(node));
		}
		return false;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		List<Vertex> list=Dijkstra.getShortestPathTo(this);
		String str = "[ ";
		for (Vertex v: list){
			str+=v.getNoeud()+",";
		}
		str+="]";
		return this.node+"( Long= "+this.longitude+
				", lat= "+this.latitude+
				") Dist "+distance +" "+str;
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

	public LinkedList<Vertex> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(LinkedList<Vertex> neighbours) {
		this.neighbours = neighbours;
	}
	
	/**
	 * 
	 * @param Vertex s
	 * Ajoute un sommet voisin s'il n'est pas encore dans la liste des voisins
	 * 
	 */
	public void addNeighbours(Vertex s){
		if (!this.equals(s)){
			if (!this.neighbours.contains(s)){
				this.neighbours.add(s);
			}
		}
	}
	
	/**
	 * Fusion entre des sommets de noeuds identiques
	 * Les voisins de v non présents dans le sommet courant
	 * lui sont ajoutés
	 * Le nombre de vue est incrémenté car c'st pas la première
	 * fois qu'on le (sommet) rencontre
	 * 
	 * @param v
	 */
	public synchronized void mergeNeighbours(Vertex v){
		this.newView();
		for (Vertex vs : v.getNeighbours()){
			this.addNeighbours(vs);
		}
	}

	public int getNbView() {
		return nbView;
	}

	public void setNbView(int nbView) {
		this.nbView = nbView;
	}
	
	public void newView(){
		this.nbView++;
	}

	public int getDistance() {
		return distance;
	}

	public synchronized void setDistance(int distance) {
		this.distance = distance;
	}

	public Vertex getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(Vertex predecessor) {
		this.predecessor = predecessor;
	}

	@Override
	public int compareTo(Vertex other) {
		// TODO Auto-generated method stub
		return Integer.compare(distance, other.getDistance());
	}
}
