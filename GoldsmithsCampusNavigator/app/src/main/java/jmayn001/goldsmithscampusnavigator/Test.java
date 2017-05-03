import java.util.*;
import java.io.*;

public class Test {
	public static void main(String[] args) {
		
		naviRHB test = new naviRHB("MAIN ENTRANCE", "342");
		//naviRHB test = new naviRHB("MAIN ENTRANCE", "306");
		//test.extractInfo();
		for(String s: test.getPath()) System.out.print(s + " ");
		/*
		EdgeWeightedGraph test = new EdgeWeightedGraph();
		test.addVertex("a", "1", 0, 0);
		test.addVertex("b", "1", 0, 0);
		test.addVertex("c", "1", 0, 0);
		test.addVertex("d", "1", 0, 0);
		test.addVertex("e", "1", 0, 0);
		test.addVertex("f", "1", 0, 0);
		test.addEdge("a", "b", 11);
		test.addEdge("a", "d", 4);
		test.addEdge("a", "e", 8);
		test.addEdge("b", "e", 6);
		test.addEdge("b", "c", 2);
		test.addEdge("e", "c", 9);
		test.addEdge("e", "f", 20);
		test.addEdge("f", "c", 5);		
		LinkedList<String> testD = Pathfinder.Dijkstra(test, "a", "c");
		for(String i: testD) System.out.print(i + "\t");
		//System.out.print(test.toString());*/
		
		/*Extractor RHB_F1_E = new Extractor("RHB_Layout_F1.txt");
		Extractor RHB_F2_E = new Extractor("RHB_Layout_F2.txt");
		Extractor RHB_F3_E = new Extractor("RHB_Layout_F3.txt");
		RHB_F1_E.printFile();
		RHB_F2_E.printFile();
		RHB_F3_E.printFile();
		graphMaker RHB_F1 = null;
		graphMaker RHB_F2 = null;
		graphMaker RHB_F3 = null;
		try {
			RHB_F1 = new graphMaker("RHB_Layout_F1_info.txt", "F1_");
			RHB_F2 = new graphMaker("RHB_Layout_F2_info.txt", "F2_");
			RHB_F3 = new graphMaker("RHB_Layout_F3_info.txt", "F3_");
			//System.out.println(RHB_F1.getGraph().toString());
			//System.out.println(RHB_F2.getGraph().toString());
			//System.out.println(RHB_F3.getGraph().toString());
			Searcher test = new Searcher();
			String start = "F1_ME";
			double smallest = Double.POSITIVE_INFINITY;
			LinkedList<String> path = null;
			
			for(String end: test.findVertex("F3_306")) {	// For each appropriate exit
				String tempStart = start;
				boolean endIsHighest = false;
				char startFloor = start.charAt(1);
				char endFloor = end.charAt(1);
				List<String> stairList = new ArrayList<String>();
				List<String> crntPath = new LinkedList<String>();
				if(startFloor != endFloor) {						// If the startFloor and endFloor are not the same level
					for(String stairs: test.findVertex("STAIRS")) {	// find all stair vertices
																	// Start search at the highest floor as floor 3 has obstacles
						if(Integer.valueOf(startFloor) > Integer.valueOf(endFloor)) {	// Find which if the start is the highest floor
							if(stairs.contains("F"+startFloor)) stairList.add(stairs);	// then gets a list of all the stairs on the startFloor
						} else {														// If the end is the highest floor
							if(stairs.contains("F"+endFloor)) stairList.add(stairs);	// gets a list of all the stairs on the endFloor
							endIsHighest = true;
						}
					}
					if(endIsHighest) tempStart = end;	
					for(String endStairs: stairList) {
						aStar temp = new aStar(RHB_F1.getGraph(), tempStart, endStairs, start);
						double crntWeight = temp.getPathWeight();
						if(smallest > crntWeight) {
							smallest = crntWeight;
							path = temp.getPath();
						}
					}
					
				} else {
					crntPath = Pathfinder.aStar(RHB_F1.getGraph(), start, end);
					if(smallest == null || smallest.size() > crntPath.size()) {
						smallest = (LinkedList<String>)crntPath;
					}
				}
			}
			for(String i: smallest) System.out.print(i + " ");
			
		} catch (IOException e) {
			System.err.print("failed");
		}
		public EdgeWeightedGraph getFloor(int floorNum) {
			EdgeWeightedGraph RHB_F = null;
			if(floorNum == 1) RHB_F = RHB_F1.getGraph();
			if(floorNum == 2) RHB_F = RHB_F2.getGraph();
			if(floorNum == 3) RHB_F = RHB_F3.getGraph();
			return RHB_F;
		}*/
	}
}
