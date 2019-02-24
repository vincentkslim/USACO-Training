import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
ID: vlim710
LANG: JAVA
TASK: milk3
*/
public class milk3 {
    private static int[] maxMilk;
    private static boolean[] recorded;
    private static HashSet<String> redundant;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("milk3.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        maxMilk = new int[3];
        maxMilk[0] = Integer.parseInt(st.nextToken());
        maxMilk[1] = Integer.parseInt(st.nextToken());
        maxMilk[2] = Integer.parseInt(st.nextToken());

        redundant = new HashSet<>();

        recorded = new boolean[maxMilk[2]+1];
        checkMilk(new int[]{0,0,maxMilk[2]});
        for (int i=0; i<recorded.length; i++) if (recorded[i] && i!=recorded.length-1) out.print(i + " ");
        out.println(recorded.length-1);
        out.close();
    }

    //brute force the problem--the secret is to store every visited combination of the amount in each bucket in a hashset
    //so that you can terminate the recursive loop if you already visited a node before.
    //use a hashset so checking if an element is already in one is in constant time.
    public static void checkMilk(int[] amtMilk) {
        if (redundant.contains(Arrays.toString(amtMilk))) return;
        else redundant.add(Arrays.toString(amtMilk));
        if (amtMilk[0] == 0)
            recorded[amtMilk[2]] = true;

        System.out.println(Arrays.toString(amtMilk));
        for (int i=0; i<3; i++) {
            if (amtMilk[i] == 0) continue;
            for (int j=0; j<3; j++) {
                if (amtMilk[j] == maxMilk[j]) continue;
                if (i!=j) {
                    int[] milk = Arrays.copyOf(amtMilk, amtMilk.length);
                    if (milk[i] + milk[j] > maxMilk[j]) {
                        milk[i] = milk[i] - (maxMilk[j] - milk[j]);
                        milk[j] = maxMilk[j];
                    } else {
                        milk[j] += milk[i];
                        milk[i] = 0;
                    }
                    checkMilk(milk);
                }
            }
        }
    }
}
