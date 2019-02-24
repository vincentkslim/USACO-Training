import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
ID: vlim710
LANG: JAVA
TASK: milk2
*/
public class milk2 {
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));

        int numFarmers = Integer.parseInt(f.readLine());

        String[] times = new String[numFarmers];
        for(int i=0; i<times.length; i++) times[i] = f.readLine();

        /*
        //insertion sort--this is too slow, do not use it!
        for(int i=1; i<times.length; i++) {
            for(int j = i; j>0; j--) {
                if(Integer.parseInt(new StringTokenizer(times[j-1]).nextToken()) > Integer.parseInt(new StringTokenizer(times[j]).nextToken())) {
                    String tmp = times[j - 1];
                    times[j - 1] = times[j];
                    times[j] = tmp;
                }
            }
        }*/

        Arrays.sort(times, new Comparator<String>() { //uses mergesort to sort the list--much faster and completes the task within the time limit
            @Override
            public int compare(String o1, String o2) {
                return Integer.parseInt(new StringTokenizer(o1).nextToken()) - Integer.parseInt(new StringTokenizer(o2).nextToken());
            }
        });

        int maxInterval = 0; //max interval where no cow was milked
        int startTime = 0;
        int endTime = 0; //start and end times for the max interval where at least one cow was milked
        int maxIntervalMilked = 0; //max interval where at least one cow was milked

        for(int i=0; i<numFarmers; i++) {
            StringTokenizer st = new StringTokenizer(times[i]);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if(startTime == 0) {
                startTime = start;
                endTime = end;
            } else if(start <= endTime && end > endTime) {
                endTime = end;
            }
            if (endTime - startTime > maxIntervalMilked) maxIntervalMilked = endTime-startTime;

            if(start > endTime) {
                if (start - endTime > maxInterval) maxInterval = start - endTime;
                startTime = start;
                endTime = end;
            }
        }

        out.println(maxIntervalMilked + " " + maxInterval);
        out.close();
    }
}

