import java.io.*;
import java.util.Arrays;
import java.util.BitSet;
import java.util.StringTokenizer;

/*
ID: vlim710
LANG: JAVA
TASK: castle
*/
public class castle {

    private static BitSet[][] graph;
    private static int[][] components;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("castle.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int length = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());
        graph = new BitSet[height][length];
        components = new int[height][length];

        for (int i=0; i<graph.length; i++) {
            st = new StringTokenizer(f.readLine());
            for (int j = 0; j < graph[i].length; j++) {
                graph[i][j] = BitSet.valueOf(new byte[]{Byte.parseByte(st.nextToken())});
                components[i][j] = -2;
            }
            //System.out.println(Arrays.toString(graph[i]));
        }

        int component = 1;
        for (int i=0; i<graph.length; i++) {
            for (int j=0; j<graph[i].length; j++) {
                if (components[i][j] == -2) {
                    depthFirst(i, j, component);
                    component++;
                }
            }
        }
        //System.out.println(component);

        //count the size of each component
        int[] componentSize = new int[component-1];
        for (int[] arr: components) for (int i: arr) componentSize[i-1]++;

        //for (int[] arr: components) System.out.println(Arrays.toString(arr));
        //System.out.println(Arrays.toString(componentSize));

        //brute force!! Go through every single block/module in the castle and try removing any adjacent walls--pick
        //the one with the largest size.
        int maxSize = 0;
        int row=0, col=0;
        char dir=' ';
        for (int i=0; i<components.length; i++) {
            for (int j=0; j<components[i].length; j++) {
                int size = 0;
                char tempDir = ' ';

                //try removing the north wall
                if (i-1 >=0 && components[i-1][j] != components[i][j]) {
                    if (size < componentSize[components[i-1][j]-1] + componentSize[components[i][j]-1]) {
                        size = componentSize[components[i-1][j]-1] + componentSize[components[i][j]-1];
                        tempDir = 'N';
                    }
                }
                    //size = Math.max(size, componentSize[components[i-1][j]] + componentSize[components[i][j]]);


                /*
                //try removing the south wall
                if (i+1 < components.length && components[i+1][j] != components[i][j])
                    if (size < componentSize[components[i+1][j]-1] + componentSize[components[i][j]-1]) {
                        size = componentSize[components[i+1][j]-1] + componentSize[components[i][j]-1];
                        tempDir = 'S';
                    }
                    //size = Math.max(size, componentSize[components[i+1][j]] + componentSize[components[i][j]]);

                //try removing the west wall
                if (j-1 >= 0 && components[i][j-1] != components[i][j]){
                    if (size < componentSize[components[i][j-1]-1] + componentSize[components[i][j]-1]) {
                        size = componentSize[components[i][j-1]-1] + componentSize[components[i][j]-1];
                        tempDir = 'W';
                    }
                }
                    //size = Math.max(size, componentSize[components[i][j-1]] + componentSize[components[i][j]]);
                */


                //try removing the east wall
                if (j+1 < components[i].length && components[i][j+1] != components[i][j]) {
                    if (size < componentSize[components[i][j+1]-1] + componentSize[components[i][j]-1]) {
                        size = componentSize[components[i][j+1]-1] + componentSize[components[i][j]-1];
                        tempDir = 'E';
                    }
                }
                    //size = Math.max(size, componentSize[components[i][j+1]] + componentSize[components[i][j]]);

                //now see if this size is greater than the max
                if (size != 0) {
                    if (size > maxSize) {
                        row = i;
                        col = j;
                        maxSize = size;
                        dir = tempDir;
                    } else if (size == maxSize) {
                        if (j < col) {
                            row = i;
                            col = j;
                            maxSize = size;
                            dir = tempDir;
                        } else if (j == col && i > row) {
                            row = i;
                            col = j;
                            maxSize = size;
                            dir = tempDir;
                        }
                    }
                }
            }
        }
        //System.out.println(row + " " + col + " " + dir + " " + maxSize);

        out.println(componentSize.length);
        Arrays.sort(componentSize);
        out.println(componentSize[componentSize.length-1]);
        out.println(maxSize);
        out.println((row+1) + " " + (col+1) + " " + dir);
        out.close();
    }

    public static void depthFirst(int row, int col, int component) {
        if (components[row][col] != -2) return;

        components[row][col] = component;
        //if no neighbors, return
        if (graph[row][col].cardinality() == 4) return;
        //otherwise, look for more neighbors and recurse!

        //there is a neighbor west (to the right)
        if (!graph[row][col].get(0)) depthFirst(row, col-1, component);
        //neighbor north (up)
        if (!graph[row][col].get(1)) depthFirst(row-1, col, component);
        //neighbor east (left)
        if (!graph[row][col].get(2)) depthFirst(row, col+1, component);
        //neighbor south (down)
        if (!graph[row][col].get(3)) depthFirst(row+1, col, component);
    }
}
