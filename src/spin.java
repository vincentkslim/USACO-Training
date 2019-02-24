/*
ID: vlim710
LANG: JAVA
TASK: spin
*/

import java.io.*;
import java.util.*;

public class spin {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("spin.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spin.out")));

		int[][] wedges = new int[5][];
		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int start = Integer.parseInt(st.nextToken());
			int numWedges = Integer.parseInt(st.nextToken());
			wedges[i] =  new int[2 + numWedges*2];
			wedges[i][0] = start;
			wedges[i][1] = numWedges;
			for (int j = 2; j < wedges[i].length; j+=2) {
				int startAngle = Integer.parseInt(st.nextToken());
				int extent = Integer.parseInt(st.nextToken());
				wedges[i][j] = startAngle;
				wedges[i][j+1] = extent;
			}
		}
		
//		for (int[] arr : wedges) System.out.println(Arrays.toString(arr));
		
		//simulate
		
		//setup
		int[] angle = new int[5];
		HashSet<String> seen = new HashSet<String>();
		String state = "0 0 0 0 0";
		int[] align = new int[360];
		for (int i=0; i<5; i++) for(int j=2; j<wedges[i].length; j+=2) addOne(align, wedges[i][j], wedges[i][j+1]);
		
//		System.out.println(Arrays.toString(align));
		
		int time = 0;
		boolean found = false;
		//actual simulation
		out:
		while(!seen.contains(state)) {
			seen.add(state);
			//check if all are aligned at one point
			for (int i=0; i<360; i++) if(align[i] == 5) { found = true; break out; }
			
			align = new int[360];
			for (int i = 0; i < 5; i++) {
				angle[i] += wedges[i][0];
				angle[i] %= 360;
				for(int j=2; j<wedges[i].length; j+=2) 
					addOne(align, wedges[i][j] + angle[i], wedges[i][j+1]);
			}
			state = String.format("%d %d %d %d %d", angle[0], angle[1], angle[2], angle[3], angle[4]);
			time++;
		}
		
		if(found) out.println(time);
		else out.println("none");
		out.close();
	}
	
	static void addOne(int[] arr, int start, int distance) {
		for (int i = start; i <= start+distance; i++) 
			arr[i%arr.length]++;
	}
}
