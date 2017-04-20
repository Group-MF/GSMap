import java.util.*;

public class Vertex {
	private String label;
	private String type;
	private boolean active;
	private HashSet<Edge> edges;
	
	public Vertex(String l) {
		label = l;
		type = calcType();
		active = calcActive();
		edges = new HashSet<Edge>();
	}
	
	public Vertex(String l, String t) {
		label = l;
		type = t;
		active = calcActive();
		edges = new HashSet<Edge>();
	}
	
	public Vertex(String l, String t, HashSet<Edge> e) {
		label = l;
		type = t;
		active = calcActive();
		edges = new HashSet<Edge>();
		Iterator<Edge> i = e.iterator();	// Iterates through the input HashSet
		while(i.hasNext()) add(i.next());	// to check if the edges are connected to this vertex
	}
	
	public void add(Edge e) {
		// Prevents edges in the set from being overridden
		if(edges.contains(e)) throw new IllegalArgumentException("Edge " + e + "already exists"); // Error message
		edges.add(e);
	}
	
	public void remove(Vertex vertex) {
		edges.remove(findEdge(vertex));
	}
	
	public void remove(Edge e) {
		edges.remove(e);
	}
	
	public int degree() {
		return edges.size();
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getType() {
		return type;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean b) {
		active = b;
	}
	
	public Edge findEdge(Vertex vertex) {
		Iterator<Edge> edgeIt = edges.iterator();
		Edge e = null;
		while(edgeIt.hasNext()) {
			Edge temp = edgeIt.next();
			if(vertex.equals(temp.getVertex1())) e = temp;
			if(vertex.equals(temp.getVertex2())) e = temp;
		}
		return e;
	}
	
	public HashSet<Edge> getEdges(){
		return edges;
	}

	public String toString() {
		String s = ("Vertex " + label + ": ");
		Iterator<Edge> i = edges.iterator();
		while(i.hasNext()){
			Edge tempEdge = i.next();
			s += (tempEdge.getOther(this).getLabel() + " " + tempEdge.getWeight() + "\t");
		}
		return s;
	}
	
	private String calcType() {
		String t = null;
		if(type.contains("L")) t = "Lift";
		else if(type.contains("SU")) t = "StairUp";
		else if(type.contains("SD")) t = "StairDown";
		else if(type.contains("T")) t = "Toilet";
		else if(type.contains("E")) t = "Exits";
		else if(type.contains("LA")) t = "Large Area";
		else t = "hall";
		return t;
	}
	
	private boolean calcActive() {
		boolean act = true;
		if(type.equals("Lift")) act = false; // Lift mode is off by default
		if(type.equals("Large Room")) act = false; // Option to go through large rooms is off by default
		if(type.equals("Exit") && label.contains("FE")) act = false; // Fire exits are off by default
		return act;
	}
}
