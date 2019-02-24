import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
ID: vlim710
LANG: JAVA
TASK: ariprog
*/
public class ariprog {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("ariprog.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));

        int length = Integer.parseInt(f.readLine());
        int upper = Integer.parseInt(f.readLine());

        boolean[] arr = new boolean[upper*upper*2+1];
        //ArrayList<Integer> bisquares = new ArrayList<>();
        //make all bisquares, keep track of available ones in boolean array
        for (int i=0; i<=upper; i++) {
            for (int j=0; j<=upper; j++) {
                //bisquares.add(i*i+j*j);
                arr[i*i+j*j] = true;
            }
        }
        //Collections.sort(bisquares);

        ArrayList<String> ariprogs = new ArrayList<>();
        for (int i=0; i<arr.length; i++) {
            int start = i;
            if(!arr[i]) continue;
            for (int diff = 1; diff<5000; diff++) {
                int count = 1; //already one bisquare in the sequence(the first one)
                for (int j = start+diff; j<arr.length && count < length; j+=diff) {
                    if (arr[j]) count++;
                    else break;
                }
                if (count == length) {
                    ariprogs.add(start + " " + diff);
                }
            }
        }

        Collections.sort(ariprogs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.parseInt(o1.substring(o1.indexOf(' ')+1)) - Integer.parseInt(o2.substring(o2.indexOf(' ')+1));
            }
        });

        for (String s: ariprogs) out.println(s);
        if (ariprogs.size() == 0) out.println("NONE");
        out.close();
    }
}
