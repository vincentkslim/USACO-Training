/*
ID: vlim710
LANG: JAVA
TASK: inflate
*/

import java.io.*;
import java.util.*;

public class inflate {
    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("inflate.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
        
        StringTokenizer st = new StringTokenizer(f.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] categories = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(f.readLine());
            categories[i][0] = Integer.parseInt(st.nextToken()); //points
            categories[i][1] = Integer.parseInt(st.nextToken()); //time
        }

        int[] time = new int[M+10];

        for(int i=0; i<M; i++) {
            for (int[] p : categories) {
                if(i+p[1] <= M) 
                    time[i+p[1]] = Math.max(time[i]+p[0], time[i+p[1]]);
            }
        }
    
        for (int i = M; i >= 0; i--) {
            if(time[i] != 0) out.println(time[i]);
            break;
        }    
        //out.println(points);
        out.close();
    }
}