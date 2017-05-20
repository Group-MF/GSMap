package Pathfinder;

import java.util.*;

public class Options {
	public static boolean accessibility;
	public static boolean largeRoom;
	public static boolean fireExits;
	
	public Options() {
		accessibility = false;
		largeRoom = false;
		fireExits = false;
	}

	public void setAccess(boolean b) {
		accessibility = b;
	}

	public void setLargeRoom(boolean b) {
		largeRoom = b;
	}

	public void setFireExits(boolean b) {
		fireExits = b;
	}
	
	// Needs testing
	public static void Options(EdgeWeightedGraph RHB) { // Turn into an event listener
		Iterator<Vertex> vertexIt = RHB.getVertices().values().iterator();
		while(vertexIt.hasNext()) { // Goes through all vertices
			Vertex vertex = vertexIt.next();
			String vertexType = vertex.getType();
			if(vertexType.contains("Stair")) vertex.setActive(!accessibility);
			else if(vertexType.equals("Lift")) vertex.setActive(accessibility);
			else if(vertexType.equals("LargeRoom")) vertex.setActive(largeRoom);
			else if(vertexType.equals("Exit") && vertex.getLabel().contains("FE")) vertex.setActive(fireExits);
		}
	}
}
