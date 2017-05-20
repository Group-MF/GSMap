package Pathfinder;

import java.util.*;
import java.io.*;

public class NaviRHB {
	Extractor RHB_F1_E;
	Extractor RHB_F2_E;
	Extractor RHB_F3_E;
	GraphMaker RHB_F1;
	GraphMaker RHB_F2;
	GraphMaker RHB_F3;
	Searcher RHB_RTV;
	String start, finish;
	LinkedList<String> path;
	
	public NaviRHB(String st, String finish) {
		path = null;
		makeGraph();
		makeSearcher();
		start = RHB_RTV.findVertex(st).get(0);
	}
	
	public void extractInfo() {
		RHB_F1_E = new Extractor("RHB_Layout_F1.txt");
		RHB_F2_E = new Extractor("RHB_Layout_F2.txt");
		RHB_F3_E = new Extractor("RHB_Layout_F3.txt");
		RHB_F1_E.printFile();
		RHB_F2_E.printFile();
		RHB_F3_E.printFile();
	}
	
	private void makeGraph() {
		try {
			RHB_F1 = new GraphMaker("RHB_Layout_F1_info.txt", "F1_");
			RHB_F2 = new GraphMaker("RHB_Layout_F2_info.txt", "F2_");
			RHB_F3 = new GraphMaker("RHB_Layout_F3_info.txt", "F3_");
		} catch (IOException e) {
			System.err.print("unable to create graph " + e.toString());
		}
	}
	
	private void makeSearcher() {
		try {
			RHB_RTV = new Searcher();
		} catch (IOException e) {
			System.err.print("unable to create searcher " + e.toString());
		}
	}
	
	public LinkedList<String> getPath() {
		return path;
	}

	public void startPathfinder(){
		findPath(start, finish, RHB_RTV);
	}
	
	public void findPath(String start, String end, Searcher RHB) {
		double smallest = Double.POSITIVE_INFINITY;
		
		for(String potentEnd: RHB.findVertex(end)) {	// For each appropriate exit
			double smallestWeight = Double.POSITIVE_INFINITY;
			boolean endIsHighest = false;
			char startFloor = start.charAt(1);
			char endFloor = potentEnd.charAt(1);
			EdgeWeightedGraph RHB_F = getFloor(startFloor);
			List<String> stairList = new ArrayList<String>();
			LinkedList<String> crntPath = new LinkedList<String>();
			
			if(startFloor != endFloor) {						// If the startFloor and endFloor are not the same level
				for(String stairs: RHB.findVertex("STAIRS")) {	// find all stair vertices
																// Start search at the highest floor as floor 3 has obstacles
					if(Integer.valueOf(startFloor) > Integer.valueOf(endFloor)) {	// Find which if the start is the highest floor
						if(stairs.contains("F"+startFloor)) stairList.add(stairs);	// then gets a list of all the stairs on the startFloor
					} else {														// If the end is the highest floor
						if(stairs.contains("F"+endFloor)) stairList.add(stairs);	// gets a list of all the stairs on the endFloor
						endIsHighest = true;
					}
				}
				if(endIsHighest) {
					RHB_F = getFloor(endFloor);	// Gets a graph of the floor
					for(String endStairs: stairList) {				// Goes through the stairs on the floor
						aStar navi = new aStar(RHB_F, potentEnd, endStairs); // Finds a path to the stairs, there is an additional heuristic
						double heuristic = aStar.calcSLD(getFloor(startFloor).getVertex(start).getX(), getFloor(startFloor).getVertex(start).getX(), getFloor(endFloor).getVertex(endStairs).getX(), getFloor(endFloor).getVertex(endStairs).getY());
						double crntWeight = navi.getPathWeight() + heuristic;	// the weight of the path has the additional heuristic added onto it
						if(smallestWeight > crntWeight && navi.getPath() != null) {					// Takes the smallest path
							smallestWeight = crntWeight;
							crntPath = navi.getPath();
						}
					}
					String nextStart = linkFloors(crntPath);	//
					RHB_F = getFloor(nextStart.charAt(1));
					if(nextStart.equals("F2_SUU") || nextStart.equals("F2_SUM")) {
						System.out.println(nextStart);
						path = crntPath;
						//findPath(start, nextStart, RHB);
						break;
					}
					System.out.println("check");
					aStar navi = new aStar(RHB_F, nextStart, start);
					crntPath.addAll(0, navi.getPath());
					smallestWeight += navi.getPathWeight();
					if(smallest > smallestWeight) {
						smallest = smallestWeight;
						path = crntPath;
					}
				} else {
					RHB_F = getFloor(startFloor);	// Gets a graph of the floor
					for(String startStairs: stairList) {				// Goes through the stairs on the floor
						aStar navi = new aStar(RHB_F, start, startStairs); // Finds a path to the stairs, there is an additional heuristic
						double heuristic = aStar.calcSLD(getFloor(endFloor).getVertex(potentEnd).getX(), getFloor(endFloor).getVertex(potentEnd).getX(), getFloor(startFloor).getVertex(startStairs).getX(), getFloor(startFloor).getVertex(startStairs).getY());
						double crntWeight = navi.getPathWeight() + heuristic;	// the weight of the path has the additional heuristic added onto it
						if(smallestWeight > crntWeight && navi.getPath() != null) {					// Takes the smallest path
							smallestWeight = crntWeight;
							crntPath = navi.getPath();
						}
					}
					String nextStart = linkFloors(crntPath);
					RHB_F = getFloor(nextStart.charAt(1));
					aStar navi = new aStar(RHB_F, nextStart, potentEnd);
					crntPath.addAll(0, navi.getPath());
					smallestWeight += navi.getPathWeight();
					if(smallest > smallestWeight) {
						smallest = smallestWeight;
						path = crntPath;
					}
				}
			} else {
				aStar navi = new aStar(RHB_F, start, potentEnd);
				if(smallest > navi.getPathWeight()) {
					smallest = navi.getPathWeight();
					path = navi.getPath();
				}
			}
		}
	}
	
	private String linkFloors(LinkedList<String> path) {
		String stairs = path.get(0);
		char floorNum = stairs.charAt(1);
		char[] destination = stairs.toCharArray();
		destination[1] = '1';
		if(Options.accessibility) return new String(destination);
		destination[4] = 'U';
		if(stairs.equals("F3_SDU") || stairs.equals("F3_SDM")) {
			destination[1] = '2';
			return new String(destination);
		}		
		if(floorNum == '3') {
			LinkedList<String> tempList = new LinkedList<String>();
			char[] stairLink1 = stairs.toCharArray();
			char[] stairLink2 = stairs.toCharArray();
			stairLink1[1] = '2';
			stairLink2[1] = '2';
			stairLink2[4] = 'U';
			tempList.add(new String(stairLink1)); 	// Updates the path to include stairs
			tempList.add(new String(stairLink2));
			path.addAll(0, tempList);
			path = tempList;
			Vertex vertex1 = RHB_F2.getGraph().getVertex(new String(stairLink1));	// Get the vertex associated with the stair name
			Vertex vertex2 = RHB_F2.getGraph().getVertex(new String(stairLink2));	// Get the vertex associated with the stair name
		}		
		return new String(destination);	// Return the stairs on the required floor
	}
	
	private EdgeWeightedGraph getFloor(char floorNum) {
		EdgeWeightedGraph RHB_F = null;
		if(floorNum == '1') RHB_F = RHB_F1.getGraph();
		if(floorNum == '2') RHB_F = RHB_F2.getGraph();
		if(floorNum == '3') RHB_F = RHB_F3.getGraph();
		return RHB_F;
	}
}

