import java.io.*;
import java.util.Arrays;

/*
ID: vlim710
LANG: JAVA
TASK: subset
*/
public class subset {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("subset.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));

        int N = Integer.parseInt(f.readLine());
        int sum = (1+N) * N/2;

        if (sum%2 == 1) out.println(0);
        else {
            long[][] sumCount = new long[N+1][sum+1]; //using 1 based indexing (1....N)

            sumCount[1][0] = 1;
            sumCount[1][1] = 1;

            for (int i = 2; i<sumCount.length; i++) {
                sumCount[i] = Arrays.copyOf(sumCount[i-1], sumCount[i-1].length);

                for (int j=0; sumCount[i-1][j] > 0; j++) {
                    sumCount[i][i+j]+=sumCount[i-1][j];
                }
            }

            //for (long[] arr: sumCount) System.out.println(Arrays.toString(arr));
            out.println(sumCount[N][sum/2]/2);
        }

        out.close();
    }
}
