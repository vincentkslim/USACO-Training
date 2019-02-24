import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
ID: vlim710
LANG: JAVA
TASK: barn1
 */
public class barn1 {
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("barn1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int maxBoards = Integer.parseInt(st.nextToken());
        int totalStalls = Integer.parseInt(st.nextToken());
        int numCows = Integer.parseInt(st.nextToken());

        //this occupiedStalls array isn't necessary--I only need to keep track of the first and last occupied stall
        //I could get the intervals with a single pass through the file.
        int[] occupiedStalls = new int[numCows];
        for(int i=0; i<numCows; i++)
            occupiedStalls[i] = Integer.parseInt(f.readLine());

        Arrays.sort(occupiedStalls);
        int[] intervals = new int[numCows-1];
        for(int i=0; i<numCows-1; i++) intervals[i] = occupiedStalls[i+1] - occupiedStalls[i]-1;
        Arrays.sort(intervals);

        //cuts the board at the largest interval between occupied stalls
        int numStallsOpen = 0;
        for(int i=intervals.length-1; i>Math.max(intervals.length-maxBoards, -1); i--)
            numStallsOpen+=intervals[i];

        //beginning and end of the line of stalls do not need to be covered if they are not occupied
        numStallsOpen+=occupiedStalls[0]-1;
        numStallsOpen+=totalStalls-occupiedStalls[occupiedStalls.length-1];

        out.println(totalStalls-numStallsOpen);
        out.close();
    }
}
