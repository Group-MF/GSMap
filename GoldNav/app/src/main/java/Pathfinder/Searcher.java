package Pathfinder;

import java.util.*;
import java.io.*;

public class Searcher {
	private HashMap<String,HashSet<String>> rtv_f1, rtv_f2, rtv_f3;
	private String[] rooms;
    private String[] destinations;

	public Searcher() throws IOException{
		rtv_f1 = createRTV("../../assets/textInfo/RHB_F1_RTN.txt", rtv_f1);
		rtv_f2 = createRTV("../../assets/textInfo/RHB_F2_RTN.txt", rtv_f2);
		rtv_f3 = createRTV("../../assets/textInfo/RHB_F3_RTN.txt", rtv_f3);
		destinations = new String[rtv_f1.size() + rtv_f2.size() + rtv_f3.size()];
	}

	private HashMap<String,HashSet<String>> createRTV(String filename, HashMap<String,HashSet<String>> roomToVertex) throws IOException {
		roomToVertex = new HashMap<String,HashSet<String>>();
		Scanner file = new Scanner(new File(filename));

		while(file.hasNextLine()) {
			String temp = file.nextLine();
			String[] roomInfo = temp.split("\t");
			roomToVertex.put(roomInfo[0], new HashSet<String>());
			String[] roomName = roomInfo[1].split(":");
			for(String room : roomName) {
				roomToVertex.get(roomInfo[0]).add(room.trim());
			}
		}
		return roomToVertex;
	}

	private void createArray() {
		int count = 0;
		for(String name: rtv_f1.keySet()) {
			destinations[count] = name;
			count++;
		}
		for(String name: rtv_f2.keySet()) {
			destinations[count] = name;
			count++;
		}
		for(String name: rtv_f3.keySet()) {
			destinations[count] = name;
			count++;
		}
	}

    public String[] getRooms() {
        return rooms;
    }

	public String[] getDestinations() {
		return destinations;
	}
	
	public ArrayList<String> findVertex(String location) { // searches building to find the vertices associated with the locations
		ArrayList<String> vertexLabel = new ArrayList<String>(); // Used a list as locations can be accessed to multiple vertex
		for(Map.Entry<String, HashSet<String>> entry: rtv_f1.entrySet()) {	// Goes through all entries on the first floor
			Iterator<String> roomIt = entry.getValue().iterator();			// Iterates through each location name
			while(roomIt.hasNext()) {
				String room = roomIt.next();
				if(room.contains(location)) {								// If the location is correct
					vertexLabel.add("F1_"+entry.getKey());						// Adds the vertexLabel to the ArrayList
					break;
				}
			}
		}
		
		for(Map.Entry<String, HashSet<String>> entry: rtv_f2.entrySet()) {	// Goes through all entries on the second floor
			Iterator<String> roomIt = entry.getValue().iterator();			// Iterates through each location name
			while(roomIt.hasNext()) {
				String room = roomIt.next();
				if(room.contains(location)) {								// If the location is correct
					vertexLabel.add("F2_"+entry.getKey());						// Adds the vertexLabel to the ArrayList
					break;
				}
			}
		}
		
		for(Map.Entry<String, HashSet<String>> entry: rtv_f3.entrySet()) {	// Goes through all entries on the second floor
			Iterator<String> roomIt = entry.getValue().iterator();			// Iterates through each location name
			while(roomIt.hasNext()) {
				String room = roomIt.next();
				if(room.contains(location)) {								// If the location is correct
					vertexLabel.add("F3_"+entry.getKey());						// Adds the vertexLabel to the ArrayList
					break;
				}
			}
		}
		return vertexLabel;
	}
}