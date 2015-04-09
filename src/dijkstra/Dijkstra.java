package dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import entities.Vertex;

public class Dijkstra {
	private static final int WEIGHT=1;
	 public synchronized static void computePaths(Vertex source)
	    {
	        source.setDistance(0);
	        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
	      	vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
		    Vertex u = vertexQueue.poll();

	            // Visit each edge exiting u
	            for (Vertex e : u.getNeighbours())
	            {
	                Vertex v = e;
	                int distanceThroughU = u.getDistance() + WEIGHT;
			if (distanceThroughU < v.getDistance()) {
			    vertexQueue.remove(v);
			    v.setDistance(distanceThroughU) ;
			    v.setPredecessor(u);
			    vertexQueue.add(v);
			}
	            }
	        }
	    }
	public static int getWeight() {
		return WEIGHT;
	}
	
	public synchronized static List<Vertex> getShortestPathTo(Vertex target)
    {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.getPredecessor())
            path.add(vertex);
        Collections.reverse(path);
        return path;
    }
}
