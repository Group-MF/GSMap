public class Edge {
	private Vertex vertex1, vertex2;
	private double weight;
	
	public Edge(Vertex v1, Vertex v2) {
		vertex1 = v1;
		vertex2 = v2;
		weight = 0;
		// Adds this edge to both vertices
		v1.add(this);
		v2.add(this);
	}
	
	public Edge(Vertex v1, Vertex v2, double w) {
		vertex1 = v1;
		vertex2 = v2;
		weight = w;
		// Adds this edge to both vertices
		v1.add(this);
		v2.add(this);
	}
	
	public Vertex getVertex1() {
		return vertex1;
	}
	
	public Vertex getVertex2() {
		return vertex2;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double w) {
		weight = w;
	}
	
	public Vertex getOther(Vertex v) {
		if(vertex1.equals(v)){
			return vertex2;
		}
		return vertex1;
	}
	
	public String toString() {
		return ("Vertex 1: " + vertex1.getLabel() + "\t" +
				"Vertex 2: " + vertex2.getLabel() + "\t" +
				"Edge Weight: " + weight);
	}
} 