import java.util.*;
import java.io.*;

public class Extractor {
	private Scanner file;
	private PrintWriter output;
	public TreeMap<String, String[]> vertices;	// String array [vertexLabel, type, x pos, y pos]
	public HashMap<String[], Double> edges; 	// String array [edge ID, nodeLabel1, nodeLabel2]
	
	public Extractor(String filename){
		output = null;
		vertices = new TreeMap<String, String[]>();
		edges = new HashMap<String[], Double>();
		try{
			file = new Scanner(new File(filename));
			output = new PrintWriter(new FileWriter(nameOutput(filename), false));
		} catch(IOException e) {
			System.err.print(e.toString());
		}
		extract();
	}
	
	private void extract() {
		while(file.hasNext()) {
			String[] nodeInfo = new String[4];
			String temp = file.nextLine();
			if(temp.contains("node id")) {
				String type = null;
				String[] nodeID = temp.split(String.valueOf((char)34));
				
				for(int i = 0; i < 3; i++) {
					temp = file.nextLine();
				}			
				String[] geometry = temp.split("\\s+");
				String[] xPos = geometry[4].split(String.valueOf((char)34)); 
				String[] yPos = geometry[5].split(String.valueOf((char)34));
				nodeInfo[2] = xPos[1];
				nodeInfo[3] = yPos[1];
				
				for(int i = 0; i < 3; i++) {
					temp = file.nextLine();
				}			
				String[] splitLabel = temp.split((char)34+">");
				
				for(int i = 0; i < 7; i++) {
					temp = file.nextLine();
				}			
				String[] splitType = temp.split(String.valueOf((char)34));
				if(splitType[1].equals("ellipse")) {
					type = "hall";
				} else if(splitType[1].equals("rectangle")) {
					type = "Lift";
				} else if(splitType[1].equals("hexagon")) {
					type = "largeArea";
				} else if(splitType[1].equals("triangle")) {
					type = "Exit";
				} else if(splitType[1].equals("star8")) {
					type = "Toilet";
				} else if(splitType[1].equals("trapezoid")) {
					type = "StairUp";
				} else if(splitType[1].equals("trapezoid2")) {
					type = "StairDown";
				}
				nodeInfo[0] = splitLabel[1].replaceAll("<y:LabelModel>", "");
				nodeInfo[1] = type;
				vertices.put(nodeID[1], nodeInfo);
			}
			if(temp.contains("edge id")) {
				String[] edgeInfo = temp.split("\\s+");
				String[] edgeID = edgeInfo[2].split(String.valueOf((char)34));
				String[] nodeLabel1 = edgeInfo[3].split(String.valueOf((char)34));
				String[] nodeLabel2 = edgeInfo[4].split(String.valueOf((char)34));
				String edge = edgeID[1];
				String node1 = nodeLabel1[1];
				String node2 = nodeLabel2[1];
				edgeInfo = new String[]{edge, node1, node2};
				edges.put(edgeInfo, calcWeight(edgeInfo));
			}
		}
	}
	
	private double calcWeight(String[] edgeInfo) {
		String[] tempVertex1 = vertices.get(edgeInfo[1]);
		String[] tempVertex2 = vertices.get(edgeInfo[2]);
		double xDist = Double.parseDouble(tempVertex1[2]) - Double.parseDouble(tempVertex2[2]);
		double yDist = Double.parseDouble(tempVertex1[3]) - Double.parseDouble(tempVertex2[3]);
		if(xDist < 0) xDist *= -1;
		if(yDist < 0) yDist *= -1;
		double weight = xDist + yDist;
		return weight;
	}
	
	public void printFile() {
		for(String[] vertexInfo: vertices.values()) {
			for(String index: vertexInfo) output.print(index + " ");
			output.println();
		}
		for(String[] edgeInfo: edges.keySet()) {
			for(String index: edgeInfo)  output.print(index + " ");
			output.print(edges.get(edgeInfo));
			output.println();
		}
		output.close();
	}
	
	private String nameOutput(String s){
		char[] temp = s.toCharArray();
		char[] newName = new char[s.length() + 5];
		for(int i = 0; i < temp.length; i++){
			if(temp[i] == '.' && i < newName.length - 1){
				newName[i] = '_';
				newName[i + 1] = 'i';
				newName[i + 2] = 'n';
				newName[i + 3] = 'f';
				newName[i + 4] = 'o';
				for(int j = i + 5; j < newName.length; j++){
					newName[j] = temp[i];
					i++;
				}
				break;
			}
			else{
				newName[i] = temp[i];
			}
		}
		return new String(newName);
	}
}

