import java.util.*;

public class Pathfinder {
	public static boolean liftMode = false;
	public static boolean throughRoom = false;
	public static boolean fireExits = false;
	
	public Pathfinder() {}
	
	// Needs testing
	static public void Options(EdgeWeightedGraph RHB) { // Turn into an event listener
		Iterator<Vertex> vertexIt = RHB.getVertices().values().iterator();
		if(location) liftMode = !liftMode;		// Location is the position of the button
		if(location) throughRoom = !throughRoom;
		if(location) fireExits = !fireExits;
		while(vertexIt.hasNext()) { // Goes through all vertices
			Vertex vertex = vertexIt.next();
			String vertexType = vertex.getType();
			if(vertexType.contains("Stair")) vertex.setActive(!liftMode);
			else if(vertexType.equals("Lift")) vertex.setActive(liftMode);
			else if(vertexType.equals("LargeRoom")) vertex.setActive(throughRoom);
			else if(vertexType.equals("Exit") && vertex.getLabel().contains("FE")) vertex.setActive(fireExits);
		}
	}

	static public LinkedList<String> Dijkstra(EdgeWeightedGraph g, String start, String end) {
		TreeMap<String, Integer> arrayPos = new TreeMap<String, Integer>();
		TreeMap<String, Vertex> unvisited = new TreeMap<String, Vertex>(g.getVertices());
		String[] previous = new String[g.size()];
		double[] distance = new double[g.size()];
		LinkedList<String> path = null;
		Vertex current = unvisited.get(start);
		
		for(Vertex vertex: unvisited.values()) { // If the vertex is not active removes it from the list of unvisited vertices (It will not be used for A*)
			if(!(vertex.getLabel().equals(start) || vertex.getLabel().equals(end))) { // If the vertex is the start of the end then it will still keep it
				if(!vertex.isActive()) unvisited.remove(vertex.getLabel()); // Removes the vertex
			}
		}
		
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
				Vertex chkVertex = currentEdge.getOther(current);
				if(unvisited.containsValue(chkVertex)) { // Check if the edge has already been used
					String chkVertexLabel = chkVertex.getLabel();
					int vertexPos = arrayPos.get(chkVertexLabel); // Finds the array position of the other vertex connected to current edge
					double tentDist = distance[arrayPos.get(current.getLabel())] + currentEdge.getWeight(); // Calculates the tentative distance (from start to next vertex)
					if(distance[vertexPos] == Double.POSITIVE_INFINITY || distance[vertexPos] >= tentDist) { // If the tentative distance is smaller then the current distance
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
				System.out.print("Unable to reach destination");
				return path;
			}
			if(current.getLabel().equals(end)) { // When the current vertex is the destination
				path = new LinkedList<String>(); // Initialize LinkedList
				String label = end;
				while(!label.equals(start)) { // Goes through previous array to find the path used
					path.add(label);
					label = previous[arrayPos.get(label)]; // Gets the name of the previous vertex on the path
				}
				path.add(start);
				return path;
			}
		}
		return path;
	}
}
