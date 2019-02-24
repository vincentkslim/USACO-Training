import java.io.*;
import java.util.Arrays;
import java.util.HashSet;

/*
ID: vlim710
LANG: JAVA
TASK: ttwo
*/
public class ttwo {

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("ttwo.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));


        HashSet<String> visited = new HashSet<>();

        int fx=-100, fy=-100, cx=-100, cy=-100;
        char fdir = 'N', cdir = 'N';

        char[][] grid = new char[11][11];
        for (int i=1; i<grid.length; i++) {
            String s = f.readLine();
            for (int j=0; j<s.length(); j++) {
                grid[i][j+1] = s.charAt(j);
                if (s.charAt(j) == 'F') {
                    fx = i;
                    fy = j+1;
                } else if (s.charAt(j) == 'C') {
                    cx = i;
                    cy = j+1;
                }
            }
        }

        assert fx != -100 && cx >= -100;

        //for (char[] arr: grid) System.out.println(Arrays.toString(arr));
        //simulate the action!

        int counter = 0;
        //x is the row and y is the col....
        while(fx != cx || fy != cy) {
            //System.out.print("\033[H\033[2J");
            //for (char[] arr: grid) System.out.println(Arrays.toString(arr));

            counter++;
            int fnextx = fx;
            int fnexty = fy;
            if (fdir == 'N') {
                fnextx--;
            } else if (fdir == 'S') {
                fnextx++;
            } else if (fdir == 'E') {
                fnexty++;
            } else if (fdir == 'W'){
                fnexty--;
            }
            //check if any of these places are invalid
            if (fnextx < 1 || fnextx > 10 ||
                fnexty < 1 || fnexty > 10 ||
                grid[fnextx][fnexty] == '*') {
                //rotate
                if (fdir == 'N') fdir = 'E';
                else if (fdir == 'E') fdir = 'S';
                else if (fdir == 'S') fdir = 'W';
                else if (fdir == 'W') fdir = 'N';
            } else {
                grid[fx][fy] = '.';
                grid[fnextx][fnexty] = 'F';

                fx = fnextx;
                fy = fnexty;
            }


            int cnextx = cx;
            int cnexty = cy;
            if (cdir == 'N') {
                cnextx--;
            } else if (cdir == 'S') {
                cnextx++;
            } else if (cdir == 'E') {
                cnexty++;
            } else if (cdir == 'W'){
                cnexty--;
            }
            //check if any of these places are invalid
            if (cnextx < 1 || cnextx > 10 ||
                    cnexty < 1 || cnexty > 10 ||
                    grid[cnextx][cnexty] == '*') {
                //rotate
                if (cdir == 'N') cdir = 'E';
                else if (cdir == 'E') cdir = 'S';
                else if (cdir == 'S') cdir = 'W';
                else if (cdir == 'W') cdir = 'N';
            } else {

                grid[cx][cy] = '.';
                grid[cnextx][cnexty] = 'C';

                cx = cnextx;
                cy = cnexty;
            }

            String state = fx + " " + fy + " " + fdir + " " + cx + " " + cy + " " + cdir;
            if (visited.contains(state)) {
                counter = 0;
                break;
            }
            else visited.add(fx + " " + fy + " " + fdir + " " + cx + " " + cy + " " + cdir);
        }

        out.println(counter);
        out.close();
    }
}
