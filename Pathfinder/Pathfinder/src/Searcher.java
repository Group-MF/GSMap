import java.util.*;
import java.io.*;

public class Searcher {
	private TreeSet<String> rhb_f1_dict, rhb_f2_dict, rhb_f3_dict;

	public Searcher() throws IOException{
		rhb_f1_dict = createDict("RHB_F1_RNT.txt");
		System.out.println("\n\n");
		rhb_f2_dict = createDict("RHB_F2_RNT.txt");
		System.out.println("\n\n");
		rhb_f3_dict = createDict("RHB_F3_RNT.txt");
	}

	private TreeSet<String> createDict(String filename) throws IOException {
		TreeSet<String> dict = new TreeSet<String>();
		Scanner file = new Scanner(new File(filename));

		while(file.hasNextLine()) {
			String temp = file.nextLine();
			String[] roomInfo = temp.split("\t");
			String[] roomName = roomInfo[1].split(":");
			for(String room : roomName) dict.add(room);
		}
		return dict;
	}
}