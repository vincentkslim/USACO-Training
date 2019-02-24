/*
ID: vlim710
LANG: JAVA
TASK: fracdec
*/

import java.io.*;
import java.util.*;

public class fracdec {

    static int N, D;
    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("fracdec.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));
        
        StringTokenizer st = new StringTokenizer(f.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        StringBuilder ans = new StringBuilder();

        int quotient = N/D; //first part of the quotient before the decimal point

        ans.append(quotient);
        ans.append('.');
        //calculate after the decimal pt.
        int remainder = N%D;

        int[] repeats = new int[D];
        Arrays.fill(repeats, -1);

        int repeat_index = -1;

        int counter = 0;
        while(remainder != 0) { 
            //System.out.printf("Checking if %d was already seen\n", remainder);
            if(repeats[remainder] != -1) {
                repeat_index = repeats[remainder];
                break;
            }
            repeats[remainder] = counter;
            int decimal = (remainder*10)/D;
            ans.append(decimal);

            remainder = (remainder*10) % D;
            counter++;
        }

        if(repeat_index != -1) {
            ans.insert(ans.indexOf(".")+repeat_index+1, "(");
            ans.append(")");
        }

        if(N%D == 0) ans.append(0);
        
        for (int i = 0; i < ans.length(); i+=76) {
            out.println(ans.substring(i, Math.min(i+76, ans.length())));
        }
        out.close();
    }
}