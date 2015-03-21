package traceroute;

public class ArcSommet {
	private Sommet voisin1, voisin2;
	
	public ArcSommet(Sommet v1, Sommet v2) {
		// TODO Auto-generated constructor stub
		this.voisin1=v1;
		this.voisin2=v2;
	}

	public Sommet getVoisin1() {
		return voisin1;
	}

	public void setVoisin1(Sommet voisin1) {
		this.voisin1 = voisin1;
	}

	public Sommet getVoisin2() {
		return voisin2;
	}

	public void setVoisin2(Sommet voisin2) {
		this.voisin2 = voisin2;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "["+this.voisin1.toString()+"<---->"+this.voisin2.toString()+"]";
	}
	
	
}
