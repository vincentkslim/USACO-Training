import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
ID: vlim710
LANG: JAVA
TASK: combo
*/
public class combo {

    private static ArrayList<int[]> combos;

    /** Main method*/
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("combo.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));

        combos = new ArrayList<int[]>(); //arraylist of combinations sorted by the first number in the combo

        int dialTicks = Integer.parseInt(f.readLine());
        StringTokenizer combo1 = new StringTokenizer(f.readLine());
        StringTokenizer combo2 = new StringTokenizer(f.readLine());
        int[] john = new int[3];
        int[] master = new int[3];
        for (int i=0; i<3; i++) {
            john[i] = Integer.parseInt(combo1.nextToken());
            master[i] = Integer.parseInt(combo2.nextToken());
        }

        generate(0, new int[3], john, dialTicks);

        /* some debugging stuff
        for(int[] arr: combos) {
            for(int i: arr) System.out.print(i + " ");
            System.out.println();
        }
        */
        //System.out.println(combos.size());

        generate(0, new int[3], master, dialTicks);

        //System.out.println(combos.size());

        out.println(combos.size());
        out.close();
    }

    /**
     * Recursive method to generate every combination according to the problem (within two of the correct combo)
     * @param pointer               current index to generate
     * @param generatedCombo        combo that is being generated
     * @param inputCombo            correct combo
     * @param dialTicks             amount of ticks on the dial
     */
    private static void generate(int pointer, int[] generatedCombo, int[] inputCombo, int dialTicks) {
        if(generatedCombo[generatedCombo.length-1] == 0) {
            for(int i=-2; i<=2; i++) { //generates 5 new arrays, each with the generatedPointer[i] incremented by one--this covers every possible combination
                generatedCombo = Arrays.copyOf(generatedCombo, 3);
                //subtracting 1 and adding one at the end is in order to shift the range from [0, dialTicks-1] to [1, dialTicks],
                //which is the correct range
                generatedCombo[pointer] = (((inputCombo[pointer] + i - 1) % dialTicks) + dialTicks) % dialTicks + 1;
                generate(pointer+1, generatedCombo, inputCombo, dialTicks);
            }
        } else {
            //adds the generated combo to preserve the order
            add(generatedCombo);
        }
    }

    /**
     * Uses binary search to add the combination array to the correct location
     * @param arr       the array to add
     */
    private static void add(int[] arr) {
        //just in case the combos array is empty
        if(combos.size() == 0) {
            combos.add(arr);
            return;
        }

        //binary search!
        int low = 0;
        int high = combos.size()-1;

        while(low <= high) {
            int mid = (low+high)/2;
            int compare = compare(arr, combos.get(mid));

            if(compare<0) high = mid-1;
            else if(compare>0) low = mid+1;
            else return;
        }

        combos.add(low, arr);

    }

    /**
     * Compares two arrays through their values.
     * Returns the first difference between values at the same index that is not zero. If all values are the same,
     * the arrays are effectively equal and this method returns zero.
     * Precondition: both arrays must be the same length
     * @param arr1      array1
     * @param arr2      array2
     * @return          first non-zero difference. otherwise, zero.
     */
    private static int compare(int[] arr1, int[] arr2) {
        int result = 0;
        for(int i=0; i<arr1.length; i++)
            if((result = arr1[i]-arr2[i]) != 0) return result;

        return result;
    }
}
