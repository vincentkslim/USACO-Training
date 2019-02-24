import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
ID: vlim710
LANG: JAVA
TASK: numtri
*/
public class numtri {

    private static int[][] rows;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("numtri.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));

        int numRows = Integer.parseInt(f.readLine());
        rows = new int[numRows][numRows];
        for (int i=0; i<numRows; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            //rows[i] = new int[i+1];
            for (int j=0; j<i+1; j++) {
                rows[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //for (int[] arr: rows) System.out.println(Arrays.toString(arr));

        compute2(rows.length-2);

        //for (int[] arr: rows) System.out.println(Arrays.toString(arr));

        //out.println(compute(1, 0, rows[0][0], rows));
        out.println(rows[0][0]);
        out.close();
    }
    /** This brute force method is too slow!*/
    public static int compute(int row, int index, int sum, int[][] rows) {
        if (row >= rows.length) return sum;
        return Math.max(compute(row+1, index, sum+rows[row][index], rows), compute(row+1, index+1, sum+rows[row][index+1], rows));
    }

    public static void compute2(int row) {
        if (row < 0) return;
        else {
            for (int i=0; i<row+1; i++) {
                rows[row][i] += Math.max(rows[row+1][i], rows[row+1][i+1]);
            }
            compute2(row-1);
        }
    }
}
