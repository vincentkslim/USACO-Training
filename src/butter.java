/*
ID: vlim710
LANG: JAVA
TASK: butter
*/
import java.io.*;
import java.util.*;

public class butter {
	
	static boolean[] taken;
	static TreeMap<IntegerPair, Integer> map;
	static ArrayList<IntegerPair>[] adjList;
	static ArrayList<IntegerPair>[] MST;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("butter.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("butter.out")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[] cows = new int[N];
		for (int i = 0; i < cows.length; i++) {
			cows[i] = Integer.parseInt(f.readLine());
		}
		
		adjList = new ArrayList[P+1];
		//MST = new ArrayList[P+1];
		
		for (int i = 0; i < adjList.length; i++) {
			adjList[i] = new ArrayList<IntegerPair>();
			//MST[i] = new ArrayList<butter.IntegerPair>();
		}
		
		
		for (int i = 0; i < C; i++) {
			st = new StringTokenizer(f.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adjList[u].add(new IntegerPair(v, w));
			adjList[v].add(new IntegerPair(u, w));
		}
		
		//literally just run dijkstra from every vertex lol
		int minDist = (int)1e9;
		for (int i = 1; i <= P; i++) {
			int[] res = dijkstra(i, P);
			//System.out.println(Arrays.toString(res));
			int distance = 0;
			for (int j : cows) {
				distance+=res[j];
			}
			//System.out.println(distance);
			minDist = Math.min(distance, minDist);
		}
		out.println(minDist);
		out.close();
	}
	
	public static int[] dijkstra(int source, int P) {
		int[] dist = new int[P+1];
		Arrays.fill(dist, (int)1e9);
		dist[source] = 0;
		PriorityQueue<IntegerPair> pq = new PriorityQueue<butter.IntegerPair>();
		pq.offer(new IntegerPair(0, source));
		
		while(!pq.isEmpty()) {
			IntegerPair pair = pq.poll();
			int d = pair.first();
			int u = pair.second();
			
			if(d > dist[u]) continue;
			for (IntegerPair next : adjList[u]) {
				int v = next.first();
				int w = next.second();
				if(dist[u] + w < dist[v]) {
					dist[v] = dist[u] + w;
					pq.offer(new IntegerPair(dist[v], v));
				}
			}
		}
		
		return dist;
	}
	
	//Taken from Competitive Programming 3
	static class IntegerPair implements Comparable<IntegerPair> {
		Integer _first, _second;

		public IntegerPair(Integer f, Integer s) {
			_first = f;
			_second = s;
		}

		public int compareTo(IntegerPair o) {
			if (!this.first().equals(o.first()))
				return this.first() - o.first();
			else
				return this.second() - o.second();
		}

		Integer first() {
			return _first;
		}

		Integer second() {
			return _second;
		}
	}
}
