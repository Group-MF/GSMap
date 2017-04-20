import java.util.*;

public class Test {
	public static void main(String[] args) {
		/*EdgeWeightedGraph test = new EdgeWeightedGraph();
		test.addVertex("a");
		test.addVertex("b");
		test.addVertex("c");
		test.addVertex("d");
		test.addVertex("e");
		test.addVertex("f");
		test.addEdge("a", "b", 10);
		test.addEdge("a", "d", 4);
		test.addEdge("a", "e", 8);
		test.addEdge("b", "e", 6);
		test.addEdge("e", "c", 2);
		test.addEdge("e", "f", 20);
		test.addEdge("f", "c", 5);		
		LinkedList<String> testD = Pathfinder.Dijkstra(test, "a", "c");
		for(String i: testD) System.out.print(i + "\t");
		//System.out.print(test.toString());*/
		Extractor RHB_F1 = new Extractor("RHB_F1.txt");
		Extractor RHB_F2 = new Extractor("RHB_F2.txt");
		Extractor RHB_F3 = new Extractor("RHB_F3.txt");
		RHB_F1.printFile();
		RHB_F2.printFile();
		RHB_F3.printFile();
	}
}
