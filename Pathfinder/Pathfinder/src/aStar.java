import java.util.*;

public class aStar {
	private double pathWeight;
	private LinkedList<String> path;
	
	public aStar(EdgeWeightedGraph graph, String start, String end) {
		pathWeight = 0;
		path = calcPath(graph, start, end);
	}
	
	public aStar(EdgeWeightedGraph graph, String start, String end, String originStart) {
		System.out.println(originStart);
		System.out.println(graph.getVertex("F1_ME"));
		pathWeight += aStar.calcSLD(graph.getVertex(originStart).getX(), graph.getVertex(originStart).getX(), graph.getVertex(end).getX(), graph.getVertex(end).getY());
		path = calcPath(graph, start, end);
	}
	
	public double getPathWeight() {
		return pathWeight;
	}
	
	public LinkedList<String> getPath() {
		return path;
	}
	
	public static double calcSLD(Double x, Double  y, Double  x1, Double  y1) { // Straight Line Distance (SLD)
		double xDist;
		double yDist;
		
		if(x > x1) { // Keeps the numbers positive
			xDist = x - x1;
		} else xDist = x1 - x;
		
		if(y > y1) { // Keeps the numbers positive
		 yDist = y - y1;
		} else yDist = y1 - y;
		return xDist + yDist;
	}

	private LinkedList<String> calcPath(EdgeWeightedGraph graph, String start, String end) {
		TreeMap<String, Integer> arrayPos = new TreeMap<String, Integer>();
		TreeMap<String, Vertex> unvisited = new TreeMap<String, Vertex>(graph.getVertices());
		String[] previous = new String[graph.size()];
		double[] distance = new double[graph.size()];
		LinkedList<String> path = null;
		ArrayList<String> inaccessV = new ArrayList<String>(); // Inaccessible vertex(inaccess V) a list of vertives to be removes from unvisited
		Vertex current = unvisited.get(start);
		
		for(Vertex vertex: unvisited.values()) { // If the vertex is not active removes it from the list of unvisited vertices (It will not be accessed)
			if(!(vertex.getLabel().contains(start.substring(0,2)) || vertex.getLabel().contains(end.substring(0,2)))) { // If the vertex is the start of the end then it will still allow it to be accessed
				if(!vertex.isActive()) inaccessV.add(vertex.getLabel());
			}
		}
		for(String vertexLabel: inaccessV) unvisited.remove(vertexLabel); // Removes the vertex
		
		int count = 0;
		for(String label: unvisited.keySet()) { // Iterates through labels to give each label an array position
			arrayPos.put(label, count);
			count++;
		}
		
		for(int i = 0; i < distance.length; i++) { // Initialize the distance array to be infinity
			distance[i] = Double.POSITIVE_INFINITY;
		}
		distance[arrayPos.get(start)] = 0; // 

		while(unvisited.size() > 0) {
			unvisited.remove(current.getLabel());
			double smallest = Double.POSITIVE_INFINITY;
			Iterator<Edge> edgeIt = current.getEdges().iterator();
			while(edgeIt.hasNext()) { // Goes through each edge connected to the current vertex
				Edge currentEdge = (Edge)edgeIt.next();
				Vertex otherVertex = currentEdge.getOther(current);
				if(unvisited.containsValue(otherVertex)) { // Check if the edge has already been used
					String otherVertexLabel = otherVertex.getLabel();
					int vertexPos = arrayPos.get(otherVertexLabel); // Finds the array position of the other vertex connected to current edge
					// Calculates the straight line distance for current vertex and the other vertex to calculate the heuristic
					double heuristic = calcSLD(current.getX(), current.getY(), unvisited.get(end).getX(), unvisited.get(end).getY()) - calcSLD(otherVertex.getX(), otherVertex.getY(), unvisited.get(end).getX(), unvisited.get(end).getY());
					double tentDist = distance[arrayPos.get(current.getLabel())] + currentEdge.getWeight() + heuristic; // Calculates the tentative distance (from start to next vertex) taking the heuristic into account
					if(distance[vertexPos] == Double.POSITIVE_INFINITY || distance[vertexPos] > tentDist) { // If the tentative distance is smaller then the current distance
						distance[vertexPos] = tentDist;			// updates the distance
						previous[vertexPos] = current.getLabel();	// and previous vertex
					} else break;
				}
			}
			for(int i = 0; i < distance.length; i++) {
				if(smallest > distance[i]) {
					boolean updated = false;
					for(Map.Entry<String, Integer> entry: arrayPos.entrySet()) {	// Iterates through the entries in arrayPos
						if(entry.getValue() == i && unvisited.containsKey(entry.getKey())) { // To find the vertex with position i, and check if it has been visited
							current = unvisited.get(entry.getKey()); // 
							updated = true;
						}
					}
					if(updated) smallest = distance[i];
				}
			}
			
			if(smallest == Double.POSITIVE_INFINITY) {
				System.out.println("Unable to reach destination");
				return null;
			}
			if(current.getLabel().equals(end)) { // When the current vertex is the destination
				path = new LinkedList<String>(); // Initialize LinkedList
				String label = end;
				pathWeight = distance[arrayPos.get(label)];
				while(!label.equals(start)) { // Goes through previous array to find the path used
					path.add(label);
					label = previous[arrayPos.get(label)]; // Gets the name of the previous vertex on the path
				}
				path.add(start);
				return path;
			}
		}
		return null;
	}
}
