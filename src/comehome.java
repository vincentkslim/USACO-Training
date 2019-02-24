/*
ID: vlim710
LANG: JAVA
TASK: comehome
*/

import java.io.*;
import java.util.*;

public class comehome {


    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("comehome.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));
        
        
        int P = Integer.parseInt(f.readLine());
        int[][] adjmatrix = new int[52][52];
        for(int[] arr: adjmatrix) Arrays.fill(arr, (int) 1e9);

        for(int i=0; i<P; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int start = toInt(st.nextToken().charAt(0));
            int end = toInt(st.nextToken().charAt(0));
            int weight = Integer.parseInt(st.nextToken());

            adjmatrix[start][end] = Math.min(weight, adjmatrix[start][end]);
            //undirected graph?
            adjmatrix[end][start] = Math.min(weight, adjmatrix[end][start]);
        }

        //run floyd warshall -- its simplier and is guaranteed to be within the time limit as V<=52 (or potentially 26),
        //which makes V^3 time possible
        for (int k = 0; k < 52; k++) {
            for (int i = 0; i < 52; i++) {
                for (int j = 0; j < 52; j++) {
                    adjmatrix[i][j] = Math.min(adjmatrix[i][j], adjmatrix[i][k] + adjmatrix[k][j]);
                }
            }   
        }

        char pasture = ' ';
        int min = (int) 1e9;
        for (int i = 0; i < 25; i++) {
            int[] dist = adjmatrix[i];
            if(dist[25] < (int) 1e9) {
                char c = ((char) (i + 'A'));
                //System.out.println(c + ": " + dist[25]);
                if(dist[25] < min) {
                    min = dist[25];
                    pasture = c;
                }
            }
        }
        
        out.println(pasture + " " + min);
        out.close();
    }

    //upper case letters are 0-25, lowercase are 26-51
    public static int toInt(char c) {
        if(Character.isLowerCase(c)) return c-'a'+26;
        return c-'A';
    }
}