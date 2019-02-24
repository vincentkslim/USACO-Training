/*
ID: vlim710
LANG: JAVA
TASK: fact4
*/

import java.io.*;
import java.util.*;

public class fact4 {
    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("fact4.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));
        int N = Integer.parseInt(f.readLine());

        int digit = 1;
        for (int i = 2; i <= N; i++) {
            digit*=i;
            //System.out.printf("i: %d, digit: %d\n", i, digit);
            while(digit % 10 == 0) digit/=10;
            digit %= 1000000;
        }

        out.println(digit%10);

        out.close();
    }
}