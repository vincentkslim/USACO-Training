/*
ID: vlim710
LANG: JAVA
TASK: humble
*/

import java.io.*;
import java.util.*;

public class humble {
    public static void main (String [] args) throws IOException {
        Scanner f = new Scanner(new File("humble.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));

        StringTokenizer st = new StringTokenizer(f.nextLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] nums = new int[K];
        for(int i=0; i<K; i++) nums[i] = f.nextInt();
        
        Arrays.sort(nums);
            
        int[] humblenums = new int[N+1];
        humblenums[0]=1;
        int[] next = new int[K];

        for (int i = 1; i <= N; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < K; j++) {
                while (next[j] < i && nums[j]*humblenums[next[j]] <= humblenums[i-1]) {
                    next[j]++;
                }
                min = Math.min(nums[j]*humblenums[next[j]], min);
            }
            humblenums[i] = min;
        }
        out.println(humblenums[N]);
        out.close();
    }
}