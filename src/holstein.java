import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
ID: vlim710
LANG: JAVA
TASK: holstein
*/
public class holstein {

    private static int numVitaminTypes;
    private static int[] vitaminRequirement;
    private static int[][] scoops;
    private static int numScoops;
    private static boolean solutionFound;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("holstein.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));

        numVitaminTypes = Integer.parseInt(f.readLine());
        vitaminRequirement = new int[numVitaminTypes];
        StringTokenizer st = new StringTokenizer(f.readLine());
        for (int i=0; i<vitaminRequirement.length; i++) vitaminRequirement[i] = Integer.parseInt(st.nextToken());

        //System.out.println(Arrays.toString(vitaminRequirement));

        numScoops = Integer.parseInt(f.readLine());
        scoops = new int[numScoops][numVitaminTypes];
        for (int i=0; i<numScoops; i++) {
            st = new StringTokenizer(f.readLine());
            for (int j=0; j<numVitaminTypes; j++) {
                scoops[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //for (int[] arr: scoops) System.out.println(Arrays.toString(arr));

        //brute force--generate all subsets of length 1, length 2, etc.... until you find a solution
        solutionFound = false;
        for (int i=1; i<=numScoops && !solutionFound; i++) {
            getSubsets(i, 0, new ArrayList<>());
        }

        out.close();
    }

    /**
     * Generates all subsets of a set 0...numScoops of length len.
     * @param len       length of subset
     * @param pointer   temporary pointer to keep track of what integer to test
     * @param current   current solution set
     */
    private static void getSubsets(int len, int pointer, ArrayList<Integer> current) {
        if (solutionFound) return;
        if (current.size() == len) {
            //check if the current solution is a valid one
            //System.out.println("checking " + current);
            if (testSolution(current)) {
                //System.out.println(current + " is a solution!");
                solutionFound = true;

                String printout = current.size() + " ";
                for (Integer i: current) printout += (i+1) + " ";
                out.println(printout.trim());
            }
            return;
        }

        if (pointer == numScoops) return;

        Integer x = pointer;

        current.add(x);
        getSubsets(len, pointer+1, current);

        current.remove(x);
        getSubsets(len, pointer+1, current);
    }

    /**
     * Tests a solution, given an ArrayList<Integer> that contains indexes of the scoops
     * @param solution      The solution to test
     * @return              true if the solution works, false otherwise.
     */
    private static boolean testSolution(ArrayList<Integer> solution) {
        int[] vitamin = Arrays.copyOf(vitaminRequirement, vitaminRequirement.length);
        for (Integer i: solution)
            vitamin = subtract(vitamin, scoops[i]);

        for (int i: vitamin) if (i > 0) return false;
        return true;
    }
    

    public static int[] subtract(int[] a1, int[] a2) {
        int[] result = new int[a1.length];
        for (int i=0; i<result.length; i++) result[i] = a1[i] - a2[i];
        return result;
    }
}
