import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
ID: vlim710
LANG: JAVA
TASK: prefix
*/
public class prefix {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("prefix.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));

        ArrayList<String> primitives = new ArrayList<>();
        String temp;

        while (!((temp = f.readLine()).equals("."))) {
            StringTokenizer st = new StringTokenizer(temp);
            while (st.hasMoreTokens()) primitives.add(st.nextToken());
        }

        //System.out.println(primitives);


        StringBuilder sb = new StringBuilder();
        while ((temp = f.readLine()) != null) sb.append(temp);

        String sequence = sb.toString();

        boolean[] length = new boolean[sequence.length()+1];
        //Arrays.fill(length, false);
        length[0] = true;
        int max =0;
        for (int i=1; i<=sequence.length(); i++) {
            //String cmp = sequence.substring(i-1);  <-- java methods sometimes suck--substring is super slow and will slow down your program!
            for (String s: primitives) {
                if (length[i-1] && startsWith(sequence, i-1, s)) {
                    length[i-1 + s.length()] = true;
                }
            }
            if (length[i]) max = i > max ? i:max;
        }

        //System.out.println(Arrays.toString(length));

        //for(int i: length) max = i > max? i:max;
        out.println(max);
        out.close();
    }

    public static boolean startsWith(String a, int ia, String b) {
        if (a.length()-ia < b.length()) return false;
        for (int i=0; i<b.length(); i++) {
            if (a.charAt(i+ia) != b.charAt(i)) return false;
        }
        return true;
    }
}
