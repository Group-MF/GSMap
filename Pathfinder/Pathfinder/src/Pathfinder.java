import java.util.*;

public class Pathfinder {
	public static boolean liftMode;
	public static boolean throughRoom;
	public static boolean fireExits;
	
	public Pathfinder() {
		liftMode = false;
		throughRoom = false;
		fireExits = false;
	}
	
	// Needs testing
	public static void Options(EdgeWeightedGraph RHB) { // Turn into an event listener
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
}
