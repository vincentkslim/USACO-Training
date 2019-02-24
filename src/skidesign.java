import java.io.*;
import java.util.Arrays;

/*
ID: vlim710
LANG: JAVA
TASK: skidesign
 */
public class skidesign {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("skidesign.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));

        int numHills = Integer.parseInt(f.readLine());

        int[] hills = new int[numHills];
        for(int i=0; i<numHills; i++) hills[i] = Integer.parseInt(f.readLine());

        Arrays.sort(hills);

        int lowestPrice = Integer.MAX_VALUE;
        for (int i=0; i<84; i++) {
            int price = 0;
            int min = i;
            int max = i+17;

            for (int h : hills) {
                if (h < min || h > max) {
                    price += Math.pow(Math.min(Math.abs(h - max), Math.abs(h - min)), 2);
                }
            }

            if (price < lowestPrice) lowestPrice = price;
        }

        out.println(lowestPrice);
        out.close();
    }
}
