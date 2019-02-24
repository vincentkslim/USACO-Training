import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
ID: vlim710
LANG: JAVA
TASK: hamming
*/
public class hamming {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("hamming.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int numWords = Integer.parseInt(st.nextToken());
        int numBits = Integer.parseInt(st.nextToken());
        int distance = Integer.parseInt(st.nextToken());

        int maxInt = maxInt(numBits);

        ArrayList<Integer> solution = new ArrayList<>();

        //System.out.println(Integer.bitCount(7^8));

        out:
        for (int start = 0; start <= maxInt; start++) {
            ArrayList<Integer> used = new ArrayList<>();
            solution = new ArrayList<>();

            solution.add(start);
            used.add(start);
            for (int i = start+1; i <= maxInt; i++) {
                if (checkInt(i, used, distance)) {
                    solution.add(i);
                    used.add(i);
                }
                if (solution.size() == numWords) break out;
            }
        }


        for (int i=0; i<solution.size(); i++) {
            if (Integer.toString(i).endsWith("9") || i == solution.size()-1) out.println(solution.get(i));
            else out.print(solution.get(i) + " ");
        }

        out.close();
    }

    public static boolean checkInt(int i, ArrayList<Integer> check, int distance) {
        for (Integer integer: check) {
            if (Integer.bitCount(integer ^ i) < distance) return false;
        }

        return true;
    }

    public static int maxInt(int numBits) {
        int max = 0;
        for(int i=0; i<numBits; i++) max += Math.pow(2, i);
        return max;
    }
}
