import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
ID: vlim710
LANG: JAVA
TASK: concom
*/
public class concom {

    private static int[][] companies;
    private static boolean[][] controls;

    public static void main(String[] args) throws IOException {
        System.out.println("hi");
        BufferedReader f = new BufferedReader(new FileReader("concom.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));

        int inputs = Integer.parseInt(f.readLine());
        companies = new int[101][101]; //company i owns companies[i][j]% of j
        controls = new boolean[101][101];

        for (int k=0; k<inputs; k++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            //companies[i][j] = p;
            addOwner(i,j,p);
        }

        //for (int[] arr: companies) System.out.println(Arrays.toString(arr));
        //for (boolean[] arr: controls) System.out.println(Arrays.toString(arr));

        for (int i=1; i<companies.length; i++) {
            for (int j = 1; j < companies[i].length; j++) {
                if (controls[i][j]&& i != j) out.println(i + " " + j);
            }
        }
        out.close();
    }

    public static void addControl(int i, int j) {
        if (i == j) return;

        if (controls[i][j]) return;

        controls[i][j] = true;
        //set i to control j
        System.out.println("company " + i + " controls " + j);
        //for every company that j controls, add that % to i controls
        for (int a=1; a< companies[j].length; a++) {
            companies[i][a]+=companies[j][a];
            if (companies[i][a] > 50) addControl(i, a);
        }
        //make sure every company that controls i also controls j
        for (int a=1; a<companies.length; a++)
            if (companies[a][i] > 50) addControl(a, j);
    }

    public static void addOwner(int i, int j, int p) {
        companies[i][j]+=p;

        for (int a=1; a<companies.length; a++)
            if (controls[a][i]) companies[a][j]+=p;

        for (int a=1; a<companies.length; a++) {
            if (companies[a][j] > 50) addControl(a, j);
        }
    }
}
