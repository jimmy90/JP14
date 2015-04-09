package entities;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
	private ArrayList<Vertex> globalVertex;
	private Vertex source;
	private HashMap<String, String> previousListVertex;
	public Graph() {
		// TODO Auto-generated constructor stub
		globalVertex= new ArrayList<Vertex>();
		previousListVertex = new HashMap<String, String>();
	}

	public ArrayList<Vertex> getGlobalVertex() {
		return globalVertex;
	}

	public void setGlobalVertex(ArrayList<Vertex> globalVertex) {
		this.globalVertex = globalVertex;
	}
	
	public int getIndexOfVertex(Vertex vertex){
		return globalVertex.indexOf(vertex);
	}
	
	public synchronized void beforeAddingVertexIn(String target,String iPString){
		if (iPString != null)
			addVertexIn(target,iPString);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		for (Vertex ver : globalVertex){
			sb.append(ver.toString()+ " \n ");
		}
		return sb.toString();
	}
	
	public synchronized void addVertexIn(String target,String IPV4){
		
		if (globalVertex.isEmpty()||globalVertex.get(0).getNoeud().equals(IPV4)){
			if (globalVertex.isEmpty()){
				source = new Vertex(IPV4);
				globalVertex.add(source);
			}
			previousListVertex.put(target, IPV4);			
		}else{
			int idx_previousVertex = getIndexOfVertex(new Vertex(previousListVertex.get(target)));
			Vertex previousVertex = globalVertex.get(idx_previousVertex);
			Vertex tmpVertex= new Vertex(IPV4);
			if (globalVertex.contains(tmpVertex)){
				int idx_vertex=getIndexOfVertex(tmpVertex);
				tmpVertex.addNeighbours(previousVertex);
				if (idx_vertex>-1){
					globalVertex.get(idx_vertex).mergeNeighbours(tmpVertex);
					globalVertex.get(idx_previousVertex).addNeighbours(tmpVertex);
					previousListVertex.put(target, IPV4);
				}
			}else{
				tmpVertex.addNeighbours(previousVertex);
				globalVertex.get(idx_previousVertex).addNeighbours(tmpVertex);
				globalVertex.add(tmpVertex);
				previousListVertex.put(target, IPV4);
			}
		}
	}

	public Vertex getSource() {
		return source;
	}

}
