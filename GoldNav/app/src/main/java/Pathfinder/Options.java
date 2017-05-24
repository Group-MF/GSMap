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

	public Boolean getAccess() {
		return accessibility;
	}

	public void setLargeRoom(boolean b) {
		largeRoom = b;
	}

	public Boolean getLargeRoom() {
		return largeRoom;
	}

	public void setFireExits(boolean b) {
		fireExits = b;
	}

	public Boolean getFireExits() {
		return fireExits;
	}
	
	// Needs testing
	public void updateGraph(EdgeWeightedGraph RHB) {
		Iterator<Vertex> vertexIt = RHB.getVertices().values().iterator();
		while(vertexIt.hasNext()) { // Goes through all vertices
			Vertex vertex = vertexIt.next();
			String vertexType = vertex.getType();
			if(vertexType.contains("stair")) vertex.setActive(!accessibility);
			else if(vertexType.equals("lift")) vertex.setActive(accessibility);
			else if(vertexType.equals("largeRoom")) vertex.setActive(largeRoom);
			else if(vertexType.equals("exit") && vertex.getLabel().contains("FE")) vertex.setActive(fireExits);
		}
	}
}
