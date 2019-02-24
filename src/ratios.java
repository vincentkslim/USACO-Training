/*
ID: vlim710
LANG: JAVA
TASK: ratios
*/
import java.io.*;
import java.util.*;

public class ratios {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("ratios.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ratios.out")));

		int[] goal = new int[3];
		StringTokenizer st = new StringTokenizer(f.readLine());
		for(int i=0; i<3; i++) goal[i] = Integer.parseInt(st.nextToken());
		
		int[][] ratios = new int[3][3];
		for(int i=0; i<3; i++) {
			st = new StringTokenizer(f.readLine());
			for(int j=0; j<3; j++) {
				ratios[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		for(int[] arr: ratios) System.out.println(Arrays.toString(arr));
//		System.out.println(check(goal, ratios, 8, 1, 5));
		
		int res = -1;
		out:
		for (int a = 0; a < 100; a++) {
			for (int b = 0; b < 100; b++) {
				for (int c = 0; c < 100; c++) {
					if((res = check(goal, ratios, a, b, c)) != -1) {
						out.printf("%d %d %d %d\n", a, b, c, res);
						break out;
					}
				}
			}
		}
		
		if(res == -1) out.println("NONE");
		out.close();
	}
	
	static int check(int[] goal, int[][] ratios, int a, int b, int c) {
		int barley = a*ratios[0][0] + b * ratios[1][0] + c * ratios[2][0];
		int oats = a*ratios[0][1] + b * ratios[1][1] + c * ratios[2][1];
		int wheat = a*ratios[0][2] + b * ratios[1][2] + c * ratios[2][2];
		
		int[] newRatios = {barley, oats, wheat};
		
		int r = -1;
		for(int i=0; i<3; i++) {
			if(goal[i] != 0 && newRatios[i] % goal[i] == 0) {
				r = newRatios[i] / goal[i];
			} 
		}
		if(r == -1 || r==0) return -1;
		for (int i = 0; i < 3; i++) {
			if(goal[i] == 0 && newRatios[i] != goal[i]) return -1;
			else if(goal[i] * r != newRatios[i]) return -1;
		}
		return r;
	}
}
