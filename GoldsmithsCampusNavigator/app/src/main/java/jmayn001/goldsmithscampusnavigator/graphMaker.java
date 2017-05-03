package jmayn001.goldsmithscampusnavigator;
import java.util.*;
import java.io.*;

public class graphMaker {
	private EdgeWeightedGraph graph;
	private String append;
	
	public graphMaker(String filename) throws IOException {
		append = "";
		graph = createGraph(filename);
	}
	
	public graphMaker(String filename, String a) throws IOException {
		append = a;
		graph = createGraph(filename);
	}
	
	public EdgeWeightedGraph getGraph(){
		return graph;
	}
	
	private EdgeWeightedGraph createGraph(String filename) throws IOException {
		EdgeWeightedGraph newGraph = new EdgeWeightedGraph();
		Scanner file = new Scanner(new File(filename));
		HashMap<String, String> nodeToVertex = new HashMap<String, String>();
		
		while(file.hasNextLine()) {
			String temp = file.nextLine();
			String[] info = temp.split("\\s+");
			if(info[0].contains("n")) {
				info[1] = append+info[1];
				nodeToVertex.put(info[0], info[1]);
				newGraph.addVertex(info[1], info[2], Double.parseDouble(info[3]), Double.parseDouble(info[4]));
			} else {
				newGraph.addEdge(nodeToVertex.get(info[1]), nodeToVertex.get(info[2]), Double.parseDouble(info[3]));
			}
		}
		return newGraph;
	}
}
