import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
ID: vlim710
LANG: JAVA
TASK: wormhole
 */
public class wormhole {

    static int[] x,y;
    static int[] pairs;
    static int[] next;
    static int numWormholes;
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("wormhole.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));

        numWormholes = Integer.parseInt(f.readLine());
        x = new int[numWormholes+1];
        y = new int[numWormholes+1];
        pairs = new int[numWormholes+1];
        next = new int[numWormholes+1];

        for (int i=1; i<=numWormholes; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        for (int i=1; i<=numWormholes; i++) {
            for (int j=1; j<=numWormholes; j++) {
                if (y[i] == y[j] && x[j] > x[i] && (next[i] == 0 || x[j] < x[next[i]]))
                    next[i] = j;
            }
        }

        build();

        out.println(count);
        out.close();
    }

    private static void build() {
        int pointer;
        for (pointer = 1; pointer<=numWormholes; pointer++) if (pairs[pointer] == 0) break;

        if (pointer > numWormholes)  {
            if(checkCycle()) count++;
        }
        for (int i=pointer+1; i<pairs.length; i++) {
            if (pairs[i] == 0) {
                pairs[pointer] = i;
                pairs[i] = pointer;
                build();
                pairs[i] = pairs[pointer] = 0;
            }
        }
    }

    private static boolean checkCycle() {
        //for (int i=1; i<=numWormholes; i++) System.out.print(i + ":" + pairs[i] + " ");
        //System.out.println();

        for (int i=1; i<=numWormholes; i++) {
            int pos = i;
            for (int j=0; j<numWormholes; j++) pos = next[pairs[pos]];
            if (pos != 0) return true;
        }
        return false;
    }
}
