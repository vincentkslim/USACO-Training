import java.io.*;
import java.util.Arrays;

/*
ID: vlim710
LANG: JAVA
TASK: sort3
*/
public class sort3 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("sort3.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
        int numMedals = Integer.parseInt(f.readLine());

        int[] arr = new int[numMedals];
        int[] count = new int[3];
        for (int i=0; i<numMedals; i++) {
            arr[i] = Integer.parseInt(f.readLine());
            count[arr[i]-1]++;
        }
        count = new int[]{0, count[0]-1, count[0], count[0]+count[1]-1, count[0]+count[1], numMedals-1};

        //System.out.println(Arrays.toString(count));

        int numSwaps = 0;
        for (int i=0; i<arr.length; i++) {
            //check if arr[i] is out of place
            if (!withinRange(i, count[(arr[i]-1)*2], count[(arr[i]-1)*2 + 1])) {
                //arr[i] is out of place, look for another number that, if switched with arr[i], will be in the right
                //place
                for (int j=count[(arr[i]-1)*2]; j<=count[(arr[i]-1)*2 + 1]; j++) {
                    if (withinRange(i, count[(arr[j]-1)*2], count[(arr[j]-1)*2 + 1])) {
                        int temp = arr[j];
                        arr[j] = arr[i];
                        arr[i] = temp;
                        numSwaps++;
                        break;
                    }
                }
            }
        }
        //System.out.println(Arrays.toString(arr));

        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);

        //look for numbers still out of place, and then divide the number of nums out of place by 3 and then multiply
        //by 2
        int stillOutOfPlace = 0;
        for (int i=0; i<arr.length; i++)
            if (arr[i] != sorted[i]) stillOutOfPlace++;
        numSwaps+= (stillOutOfPlace/3) * 2;

        out.println(numSwaps);
        out.close();
    }

    public static boolean withinRange(int i, int min, int max) {
        if (i >= min && i <= max) return true;
        return false;
    }
}
