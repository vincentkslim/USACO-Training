import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
ID: vlim710
LANG: JAVA
TASK: nocows
*/
public class nocows {

    private static long[][] dp = new long[101][201];

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("nocows.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        dp[1][1] = 1;
        for(int i = 1; i<=K; i++) {
            for (int j=1; j<=N; j+=2) {
                for (int m = 1; m<=j-1; m++) {
                    dp[i][j]+=dp[i-1][m]*dp[i-1][j-m-1];
                }
                if (j == 1) dp[i][j]+=dp[i-1][j];
                dp[i][j] %= 9901;
            }
        }
        //for (long[] arr: dp) System.out.println(Arrays.toString(arr));
        out.println((dp[K][N]-dp[K-1][N] + 9901) % 9901);
        out.close();
    }
}
