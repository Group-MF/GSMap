package Pathfinder;

import java.util.*;
import java.io.*;

public class GraphMaker {
	private EdgeWeightedGraph graph;
	private String append;
	
	public GraphMaker(InputStream filename) throws IOException {
		append = "";
		graph = createGraph(filename);
	}
	
	public GraphMaker(InputStream filename, String a) throws IOException {
		append = a;
		graph = createGraph(filename);
	}
	
	public EdgeWeightedGraph getGraph(){
		return graph;
	}
	
	private EdgeWeightedGraph createGraph(InputStream filename) throws IOException {
		EdgeWeightedGraph newGraph = new EdgeWeightedGraph();
		Scanner file = new Scanner(filename);
		HashMap<String, String> nodeToVertex = new HashMap<String, String>();
		
		while(file.hasNextLine()) { // Reads through a .txt file to gather the info required to create a graph
			String temp = file.nextLine();
			String[] info = temp.split("\\s+"); // Splits each line at blank space
			if(info[0].contains("n")) {	// checks if the content is correct
				info[1] = append+info[1];	// The append is added onto the name of each vertex
				nodeToVertex.put(info[0], info[1]);	 // Adds the information of the node map label and the new vertex label for the edges
				newGraph.addVertex(info[1], info[2], Double.parseDouble(info[3]), Double.parseDouble(info[4]));	// Creates vertices using info from txt file 
			} else {
				newGraph.addEdge(nodeToVertex.get(info[1]), nodeToVertex.get(info[2]), Double.parseDouble(info[3])); // Creates new edges
			}
		}
		return newGraph;
	}
}
