package Pathfinder;

import java.util.*;

public class EdgeWeightedGraph {
	private HashSet<Edge> edges;
	private TreeMap<String, Vertex> vertices;
	
	public EdgeWeightedGraph() {
		edges = new HashSet<Edge>();
		vertices = new TreeMap<String, Vertex>();
	}
	
	public int size() { // Returns the number of Vertices in graph
		return vertices.size();
	}
	
	public int edgeNum() { // Returns the number of edges in the graph
		return edges.size();
	}
	
	public TreeMap<String, Vertex> getVertices() { // Returns the vertices treeMap
		return vertices;
	}
	
	public Vertex getVertex(String label) { // Returns the vertex associated with input label
		for(String l: vertices.keySet()) {
			if(l.equals(label)) return vertices.get(l);
		}
		return null;
	}
	
	public void addVertex(String label, double xPos, double yPos) { 
		vertices.put(label, new Vertex(label, xPos, yPos));
	}
	
	public void addVertex(String label, String type, double xPos, double yPos) {
		vertices.put(label, new Vertex(label, type, xPos, yPos));
	}
	
	public void addVertex(Vertex vertex) {
		String label = vertex.getLabel();
		if(vertices.containsValue(vertex)) throw new IllegalArgumentException("Vertex " + label + " already exists"); // Prevents vertices from being overridden
		if(vertices.containsKey(label)) throw new IllegalArgumentException("Vertex " + label + " already exists"); // To make sure that each label is only used once
		vertices.put(label, vertex);
	}
	
	public void removeVertex(String vertexLabel) {
		vertices.remove(vertexLabel);
	}
	
	public void removeVertex(Vertex vertex) {
		vertices.remove(vertex.getLabel());
	}
	
	public HashSet<Edge> getEdges() {
		return edges;
	}
	
	public void addEdge(String vertexLabel1, String vertexLabel2, double w) { // Attach an edge to each of the vertices via they labels
		edges.add(new Edge(getVertex(vertexLabel1), getVertex(vertexLabel2), w));
	}
	
	public void addEdge(Vertex vertex1, Vertex vertex2, double w) {
		edges.add(new Edge(vertex1, vertex2, w));
	}
	
	public void removeEdge(String vertexLabel1, String vertexLabel2) {
		Vertex vertex1 = vertices.get(vertexLabel1);
		Vertex vertex2 = vertices.get(vertexLabel2);
		edges.remove(vertex1.findEdge(vertex2));
		vertex1.remove(vertex2);
		vertex2.remove(vertex1);
	}
	
	public void removeEdge(Edge e) {
		edges.remove(e);
		e.getVertex1().remove(e);
		e.getVertex2().remove(e);
	}
	
	public int degree(String label) {
		return getVertex(label).degree();
	}
	
	public String toString() {
		String s = ("Number of Vertices: " + vertices.size() + "\n" +
					"Number of Edges: " + edges.size() + "\n");
		for(String l: vertices.keySet()) s += (vertices.get(l).toString() + "\n");
		return s;
	}
}
