package pathfinder.src;

import java.util.*;
import java.io.*;

public class Extractor {
	private Scanner file;
	private PrintWriter output;
	int count = 0;
	public TreeMap<String, String[]> vertices;	// String array [nodeNum, vertexLabel, type, x pos, y pos]
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
			String[] nodeInfo = new String[5];	// 
			String temp = file.nextLine();
			if(temp.contains("node id")) {
				String type = null;
				String[] nodeID = temp.split(String.valueOf((char)34));
				nodeInfo[0] = nodeID[1];
				
				while(!temp.contains("Geometry")) {
					temp = file.nextLine();
				}			
				String[] geometry = temp.split("\\s+");
				String[] xPos = geometry[4].split(String.valueOf((char)34)); 
				String[] yPos = geometry[5].split(String.valueOf((char)34));
				nodeInfo[3] = xPos[1];
				nodeInfo[4] = yPos[1];
				
				while(!temp.contains("NodeLabel alignment")) {
					temp = file.nextLine();
				}			
				String[] splitLabel = temp.split((char)34+">");
				nodeInfo[1] = splitLabel[1].replaceAll("<y:LabelModel>", "");
				while(!temp.contains("Shape type")) {
					temp = file.nextLine();
				}			
				String[] splitType = temp.split(String.valueOf((char)34));
				if(splitType[1].equals("ellipse")) {
					type = "hall";
				} else if(splitType[1].equals("rectangle")) {
					type = "lift";
				} else if(splitType[1].equals("hexagon")) {
					if(nodeInfo[1].contains("LR")) {
						type = "largeRoom";
					} else type = "largeArea";
				} else if(splitType[1].equals("triangle")) {
					type = "exit";
				} else if(splitType[1].equals("star8")) {
					type = "toilet";
				} else if(splitType[1].equals("trapezoid")) {
					type = "stairUp";
				} else if(splitType[1].equals("trapezoid2")) {
					type = "stairDown";
				}
				nodeInfo[2] = type;
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
		// Calculate Straight Line Distance(calcSLD). Used to find the weight of each edge
		return aStar.calcSLD(Double.parseDouble(tempVertex1[3]), Double.parseDouble(tempVertex1[4]), Double.parseDouble(tempVertex2[3]), Double.parseDouble(tempVertex2[4]));
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