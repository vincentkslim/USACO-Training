/*
ID: vlim710
LANG: JAVA
TASK: agrinet
*/

import java.io.*;
import java.util.*;

public class agrinet {
    public static void main (String [] args) throws IOException {
        //BufferedReader f = new BufferedReader(new FileReader("agrinet.in"));
        Scanner f = new Scanner(new File("agrinet.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));
        
        int N = Integer.parseInt(f.nextLine());
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            //StringTokenizer st = new StringTokenizer(f.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(f.next());
            }
        }

        //for(int[] arr: matrix) System.out.println(Arrays.toString(arr));

        //build mst
        int INF = (int)1e9;
        int[] distance = new int[N];
        int[] source = new int[N];
        boolean[] intree = new boolean[N];

        Arrays.fill(distance, INF);
        Arrays.fill(source, -1);
        
        int treesize = 1;
        int treecost = 0;

        intree[0] = true;
        for (int i = 0; i < N; i++) {
            if(matrix[0][i] != 0) distance[i] = matrix[0][i];
            source[i] = 0;
        }
        while(treesize < N) {
            int i=-1;
            int mindist = INF;
            for (int j=0; j<N; j++) if(!intree[j] && distance[j] < mindist) {
                i=j;
                mindist = distance[j];
            }

            //System.out.printf("Adding %d to the graph with weight %d\n", i, mindist);
            if(i==-1) System.out.println("graph aint connected");

            treesize++;
            treecost+=mindist;
            intree[i] = true;

            for (int j = 0; j < N; j++) {
                if(matrix[i][j] != 0) if(matrix[i][j] < distance[j]) {
                    distance[j] = matrix[i][j];
                    source[j] = i;
                }
            }
        }

        out.println(treecost);
        out.close();
    }
}