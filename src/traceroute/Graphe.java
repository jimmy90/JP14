package traceroute;

import java.util.LinkedList;

public class Graphe {
	private LinkedList<ArcSommet> graphe;
	
	public Graphe() {
		// TODO Auto-generated constructor stub
		graphe= new LinkedList<ArcSommet>();
	}
	
	public Graphe(LinkedList<ArcSommet> arc){
		this.graphe=arc;
	}
	
	public LinkedList<ArcSommet> getGraphe() {
		return new LinkedList<ArcSommet>(graphe);
	}
	
	public void setGraphe(LinkedList<ArcSommet> graphe) {
		this.graphe = graphe;
	}
	
	public synchronized void addArcSommet(ArcSommet s){
		this.graphe.add(s);
	}
	
	public synchronized void addGraphe(LinkedList<ArcSommet> graphe){
		this.graphe.addAll(graphe);
	}
	
	public String toString(){
		StringBuilder sBuilder=new StringBuilder();
		for (ArcSommet arc: this.graphe){
			sBuilder.append(arc.toString());
		}		
		return sBuilder.toString();
	}
}
