import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
ID: vlim710
LANG: JAVA
TASK: money
*/
public class money {
    public static void main(String[] args) throws IOException {
        //BufferedReader f = new BufferedReader(new FileReader("money.in"));
        Scanner s = new Scanner(new File("money.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));

        StringTokenizer st = new StringTokenizer(s.nextLine());
        int numCoins = Integer.parseInt(st.nextToken());
        int total = Integer.parseInt(st.nextToken());

        //st = new StringTokenizer(f.readLine());
        int[] coins = new int[numCoins+1];
        for (int i=1; i<=numCoins; i++) coins[i] = Integer.parseInt(s.next());

        long[][] solution = new long[total+1][numCoins+1];
        Arrays.fill(solution[0], 1);
        for (int i=1; i<solution.length; i++) {
            for (int j=1; j<solution[i].length; j++) {
                solution[i][j] += solution[i][j-1];
                if (i-coins[j] >= 0) solution[i][j] += solution[i-coins[j]][j];
            }
        }
        for (long[] arr: solution) System.out.println(Arrays.toString(arr));
        out.println(solution[total][numCoins]);
        out.close();
    }
}
