import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/*
ID: vlim710
LANG: JAVA
TASK: zerosum
*/
public class zerosum {

    private static PrintWriter out;
    private static int[] arr = {0, 1, 2, 3, 4, 5 , 6, 7, 8, 9};
    private static ArrayList<String> equations;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("zerosum.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));

        int N = Integer.parseInt(f.readLine());

        equations = new ArrayList<>();
        recurse(N, 0, 2, "1", 1, 1);
        Collections.sort(equations);
        for (String eq: equations) out.println(eq);
        out.close();
    }

    private static void recurse(int N, int sum, int pointer, String eq, int num, int sign) {
        if (pointer > N) {
            if (sum + sign*num == 0) equations.add(eq);
            return;
        }

        recurse(N, sum+num*sign, pointer+1, eq+'+'+arr[pointer], arr[pointer], 1);
        recurse(N, sum+num*sign, pointer+1, eq+'-'+arr[pointer], arr[pointer], -1);
        recurse(N, sum, pointer+1, eq+' '+arr[pointer], num*10+arr[pointer], sign);
    }


}
