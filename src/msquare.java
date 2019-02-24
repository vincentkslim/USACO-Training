/*
ID: vlim710
LANG: JAVA
TASK: msquare
*/
import java.io.*;
import java.util.*;

public class msquare {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("msquare.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));
		
		int target = 0;
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		for (int i=10000000; i>0; i/=10) {
			target += Integer.parseInt(st.nextToken()) * i;
		}
		
		HashMap<Integer, Edge> seen = new HashMap<>();
		seen.put(12345678, null);
		
		
		//BFS
		Queue<Integer> q = new LinkedList<>();
		q.add(12345678);
		
		int trans = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(cur == target) break;
			//try all transformations
			trans = A(cur);
			if(! seen.containsKey(trans)) {
				seen.put(trans, new Edge(cur, 'A'));
				q.add(trans);
				if(trans == target) break;
			}
			
			trans = B(cur);
			if(! seen.containsKey(trans)) {
				seen.put(trans, new Edge(cur, 'B'));
				q.add(trans);
				if(trans == target) break;
			}
			
			trans = C(cur);
			if(! seen.containsKey(trans)) {
				seen.put(trans, new Edge(cur, 'C'));
				q.add(trans);
				if(trans == target) break;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		Edge temp;
		while((temp = seen.get(trans)) != null) {
			sb.append(temp.weight);
			trans = temp.parent;
		}
		sb.reverse();
		out.println(sb.length());
		if(sb.length() == 0) out.println();
		while(sb.length() > 0) {
			out.println(sb.substring(0, Math.min(60, sb.length())));
			sb.delete(0, 60);
		}
		out.close();
	}
	
	static class Edge {
		int parent;
		char weight;
		public Edge(int p, char w) { parent = p; weight = w;}
	}
	
	static int A(int in) {
		int ret = 0;
		while(in % 10 != 0) {
			ret+=in%10;
			ret*=10;
			in/=10;
		}
		return ret/10;
	}
	
	static int B(int in) {
		int part1 = in / 10000;
		int part2 = in % 10000;
		
		part1 = (part1 / 10) + (part1 % 10) * 1000;
		part2 = (part2 % 1000) * 10 + part2 / 1000;
		return part1 * 10000 + part2;
	}
	
	static int C(int in) { 
		int one = (in % 100) / 10;		//12345678
		int two = (in % 1000) / 100;	// 43  21
		int three = (in % 1000000) / 100000;
		int four = (in % 10000000) / 1000000;
		
		in -= four*1000000;
		in -= three*100000;
		in -= one * 10;
		in -= two * 100;

		in += two * 10;
		in += three * 100;
		in += four * 100000;
		in += one * 1000000;
		return in;
	}
}
