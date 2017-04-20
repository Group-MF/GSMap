import java.util.*;

public class EdgeWeightedGraph {
	private HashSet<Edge> edges;
	private TreeMap<String, Vertex> vertices;
	
	public EdgeWeightedGraph() {
		edges = new HashSet<Edge>();
		vertices = new TreeMap<String, Vertex>();
	}
	
	public EdgeWeightedGraph(int v) {
		if(v < 0) throw new IllegalArgumentException("Expected positive integer but was " + v);
		edges = new HashSet<Edge>();
		vertices = new TreeMap<String, Vertex>();
		for(int i = 0; i < v; i++) {
			String label = String.valueOf(i);
			vertices.put(label, new Vertex(label));
		}
	}
	
	public int size() {
		return vertices.size();
	}
	
	public int edgeNum() {
		return edges.size();
	}
	
	public TreeMap<String, Vertex> getVertices() {
		return vertices;
	}
	
	public Vertex getVertex(String label) {
		for(String l: vertices.keySet()) {
			if(l.equals(label)) return vertices.get(l);
		}
		return null;
	}
	
	public void addVertex(String label) {
		vertices.put(label, new Vertex(label));
	}
	
	public void addVertex(String label, String type) {
		vertices.put(label, new Vertex(label, type));
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
	
	public void addEdge(String vertexLabel1, String vertexLabel2, double w) {
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
