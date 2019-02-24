/*
ID: vlim710
LANG: JAVA
TASK: beads
*/
import java.io.*;
public class beads {
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("beads.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));

        int numBeads = Integer.parseInt(f.readLine());
        String beads = f.readLine();

        beads = beads + beads; //do this in case of wraparound

        int maxLength = 0;
        int previousLen = 0;
        for(int startIndex=0; startIndex<beads.length()/2; startIndex++) { //i represents index of starting character
            char firstChar = beads.charAt(startIndex);
            int length = 1;
            int wCounter = 0; //count the amount of whites at the end of this substring
            for(int pointer=startIndex+1; pointer<Math.min(startIndex+numBeads, beads.length()); pointer++) { //avoid counting beads twice
                char compare = beads.charAt(pointer);
                if(firstChar == 'w' && compare != 'w') firstChar = compare;
                if (firstChar == compare) {
                    length++;
                    wCounter = 0;
                } else if(compare == 'w') {
                    length++;
                    wCounter++;
                } else pointer = startIndex + 9999;
            }
            if(length + previousLen > maxLength) maxLength = length + previousLen;
            previousLen = length-wCounter;
            startIndex+=length-wCounter-1;
        }

        out.println(maxLength);
        out.close();
    }
}
