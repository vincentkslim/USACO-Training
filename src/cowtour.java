/*
ID: vlim710
LANG: JAVA
TASK: cowtour
*/

import java.io.*;
import java.util.*;

public class cowtour {

    static int N_MAX = 150;
    static int INF = (int) 1e9;

    static double[][] dist = new double[N_MAX][N_MAX];

    static int[] xpos = new int[N_MAX];
    static int[] ypos = new int[N_MAX];

    static double[] farthest = new double[N_MAX]; 
    static double[] diameter = new double[N_MAX];

    static int N;

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("cowtour.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));
        
        N = Integer.parseInt(f.readLine());

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            xpos[i] = Integer.parseInt(st.nextToken());
            ypos[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            String line = f.readLine();
            for (int j = 0; j < N; j++) {
                if(line.charAt(j) != '0') {
                    dist[i][j] = euclideanDist(i, j);
                } else if(i==j) dist[i][j] = 0;
                else dist[i][j] = INF;
            }
        }

        //floyd warshall
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }   
            }
        }

        //farthest array
        for (int i = 0; i < N; i++) 
            for (int j = 0; j < N; j++) 
                if(dist[i][j] < INF)
                    farthest[i] = Math.max(farthest[i], dist[i][j]);
           
        
        /*
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) 
                System.out.printf("%.2f ", dist[i][j]);
            System.out.println();
        }

        for(double b: farthest) System.out.printf("%.2f ", b);
        */
        
        //Because of floyd warshall, we already know every single component. 
        Arrays.fill(diameter, -1);
        for (int i = 0; i < N; i++) {
            if(diameter[i] != -1d) continue;
            ArrayList<Integer> component = new ArrayList<>();
            component.add(i);
            for (int j = 0; j < N; j++) 
                if(dist[i][j] < INF) component.add(j);
            
            double maxdist = 0;
            for (int u : component) maxdist = Math.max(maxdist, farthest[u]);

            for (int u : component) diameter[u] = maxdist;
        }

        //for(double b: diameter) System.out.printf("%.2f ", b);

        double min_diameter = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(dist[i][j] >= INF) {
                    //try connecting these! 
                    double new_diameter = farthest[i] + euclideanDist(i, j) + farthest[j];
                    double existing_diameter = Math.max(diameter[i], diameter[j]);

                    min_diameter = Math.min(min_diameter, Math.max(new_diameter, existing_diameter));
                }
            }
        }

        out.printf("%.6f\n", min_diameter);
        out.close();
    }

    public static double euclideanDist(int i, int j) {
        return Math.sqrt(Math.pow(xpos[i]-xpos[j], 2) + Math.pow(ypos[i]-ypos[j], 2));
    }
}