import java.io.*;

/*
ID: vlim710
LANG: JAVA
TASK: runround
*/
public class runround {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("runround.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));

        int start = Integer.parseInt(f.readLine());


        for (int i=1;;i++){
            if (!checkRepeats(start+i)) continue;

            if (runAround(start+i)) {
                out.println(start+i);
                break;
            }
        }

        out.close();
    }

    public static boolean checkRepeats(int i) {
        boolean[] flags = new boolean[10];

        while (i > 0) {
            int check = i%10;
            if (flags[check]) return false;
            else flags[check] = true;

            i = i/10;
        }

        return true;
    }

    private static boolean runAround(int i) {
        String temp = i+"";
        boolean[] flags = new boolean[temp.length()];
        int index = 0;
        for (int j=0; j<temp.length(); j++) {
            //System.out.println(temp.charAt(index));
            if (flags[index]) return false;
            flags[index] = true;

            int change = Integer.parseInt(temp.charAt(index)+"") % temp.length();

            index = (change+index) % temp.length();
        }


        if (index == 0) return true;
        return false;
    }
}
