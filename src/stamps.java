/*
ID: vlim710
LANG: JAVA
TASK: stamps
*/

import java.io.*;
import java.util.*;

public class stamps {
    public static void main (String [] args) throws IOException {
        //BufferedReader f = new BufferedReader(new FileReader("stamps.in"));
        Scanner input = new Scanner(new File("stamps.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));

        int K = input.nextInt();
        int N = input.nextInt();

        int[] stamps  = new int[N];
        for (int i = 0; i < N; i++) {
            stamps[i] = input.nextInt();
        }

        Arrays.sort(stamps);
        int[] reachable = new int[stamps[N-1]*K + 5];
        Arrays.fill(reachable, 999);
        reachable[0] = 0;

        for (int i = 0; i < reachable.length; i++) {
            if (reachable[i] != 999 && reachable[i] < K) {
                for (int j = 0; j < N; j++) {
                    reachable[i+stamps[j]] = Math.min(reachable[i+stamps[j]], reachable[i]+1);
                }
            } else if(reachable[i] == K) continue;
            else {
                out.println(i-1);
                break;
            }
        }
        /*
        int counter=0;
        while(reachable[counter] != 999) {
            System.out.printf("%d ", reachable[counter]);
            counter++;
        }*/
        out.close();
    }
}