import java.io.*;
import java.util.*;

/*
ID: vlim710
LANG: JAVA
TASK: lamps
*/
public class lamps {

    private static ArrayList<Integer> lampsOn, lampsOff;
    private static int numLamps;
    private static PrintWriter out;

    private static HashMap<BitSet, HashSet<Integer>> solutionSet;
    private static ArrayList<BitSet> solutions;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("lamps.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));

        numLamps = Integer.parseInt(f.readLine());
        int buttonPresses = Integer.parseInt(f.readLine());

        int temp;

        StringTokenizer st = new StringTokenizer(f.readLine());
        lampsOn = new ArrayList<>();
        while ((temp = Integer.parseInt(st.nextToken())) != -1) lampsOn.add(temp);

        st = new StringTokenizer(f.readLine());
        lampsOff = new ArrayList<>();
        while ((temp = Integer.parseInt(st.nextToken())) != -1) lampsOff.add(temp);

        solutionSet = new HashMap<>();
        solutions = new ArrayList<>();

        //brute force only the necessary states
        BitSet current = new BitSet(numLamps + 1);
        current.flip(1, numLamps + 1);
        compute(current, 0, 1);

        //System.out.println(solutionSet);

        outer:
        for (BitSet b : solutionSet.keySet()) {
            for (int i : lampsOn) if (!b.get(i)) continue outer;
            for (int i : lampsOff) if (b.get(i)) continue outer;

            for (int i : solutionSet.get(b)) {
                if ((i <= buttonPresses && (buttonPresses - i) % 2 == 0) || buttonPresses == i) {
                    if (b.cardinality() == 0) out.println(bitsetToString(b));
                    else solutions.add(b);
                    break;
                }
            }
        }
        //for (BitSet b : solutions) System.out.println(new BigInteger(b.get(1, numLamps+1).toByteArray()) + " " + bitsetToString(b));
        Collections.sort(solutions, (BitSet o1, BitSet o2) -> {
            for (int i=1; i<=numLamps; i++) {
                if (o1.get(i) == o2.get(i)) continue;
                else if ((o1.get(i) ? 1 : 0) > (o2.get(i)? 1:0)) return 1;
                else return -1;
            }
            return 0;
        });

        for (BitSet b : solutions) out.println(bitsetToString(b));

        if (solutions.size() == 0) out.println("IMPOSSIBLE");
        out.close();
    }

    private static String bitsetToString(BitSet in) {
        String result = "";
        for (int i = 1; i <= numLamps; i++) result += in.get(i) ? 1 : 0;
        return result;
    }

    private static void compute(BitSet current, int presses, int buttonNum) {
        /*
        if (presses == maxPresses) {
            //check if the on ones are on and the off ones are off
            for (int i: lampsOn) if (!current.get(i)) return;
            for (int i: lampsOff) if (current.get(i)) return;


            //for (int i=1; i<=numLamps; i++) System.out.print(current.get(i) ? 1:0);
            //System.out.println();

            //System.out.println(current);
            //System.out.println(current.get(1, numLamps+1));
            if (! sol.contains(current)) {
                sol.add(current);
                if (current.cardinality() == 0) out.println(bitsetToString(current));
                else solutions.add((BitSet) current.clone());

                count++;
            }
            return;
        } else if(presses < maxPresses && buttonNum > 4) return;
        */
        if (buttonNum > 4) {
            if (!solutionSet.containsKey(current)) solutionSet.put((BitSet) current.clone(), new HashSet<>());
            solutionSet.get(current).add(presses);
            return;
        }
        //pretend buttonNum is pressed
        button(current, numLamps, buttonNum);
        compute(current, presses + 1, buttonNum + 1);

        //pretend buttonNum is not pressed
        button(current, numLamps, buttonNum);
        compute(current, presses, buttonNum + 1);
    }

    private static void button(BitSet in, int N, int button) {
        switch (button) {
            case 1:
                in.flip(1, N + 1);
                break;
            case 2:
                for (int i = 1; i <= N; i += 2) in.flip(i);
                break;
            case 3:
                for (int i = 2; i <= N; i += 2) in.flip(i);
                break;
            case 4:
                for (int i = 1; i <= N; i += 3) in.flip(i);
                break;
        }
    }
}
