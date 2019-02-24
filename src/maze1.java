import java.io.*;
import java.util.*;

/*
ID: vlim710
LANG: JAVA
TASK: maze1
*/
public class maze1 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("maze1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int width = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());

        char[][] graph = new char[2*height+1][2*width+1];
        int[][] mask = new int[height+1][width+1];
        Point[][] bfs = new Point[height+1][width+1];

        for (char[] arr: graph) Arrays.fill(arr, ' ');
        for (int i=0; i<2*height+1; i++) {
            String s = f.readLine();
            //System.out.println(s);
            for (int j = 0; j < s.length(); j++) {
                graph[i][j] = s.charAt(j);

            }
        }

        Queue<Point> queue = new LinkedList<>();
        for (int i=1; i<graph.length; i+=2) {
            for (int j = 1; j < graph[i].length; j+=2) {
                Point p = new Point(j/2+1, i/2+1, Integer.MAX_VALUE);

                int bitmask = 0b0000;
                //nsew
                if (graph[i-1][j] == ' ') bitmask |= 0b1000;
                if (graph[i+1][j] == ' ') bitmask |= 0b0100;
                if (graph[i][j+1] == ' ') bitmask |= 0b0010;
                if (graph[i][j-1] == ' ') bitmask |= 0b0001;

                if (i == 1) {
                    //test if can exit upwards
                    if (graph[i-1][j] == ' ') {
                        //this is an exit
                        p.dist = 1;
                        queue.add(p);
                        bitmask &= 0b0111;
                    }
                }
                if (i == graph.length-2) {
                    //test if can exit down
                    if (graph[i+1][j] == ' ') {
                        //is exit
                        p.dist = 1;
                        queue.add(p);
                        bitmask &= 0b1011;
                    }
                }
                if (j == 1) {
                    //test if can exit left (west)
                    if (graph[i][j-1] == ' ') {
                        p.dist = 1;
                        queue.add(p);
                        bitmask &= 0b1110;
                    }
                }
                if (j == graph[i].length-2) {
                    //test if can exit right (east)
                    if (graph[i][j+1] == ' ') {
                        p.dist = 1;
                        queue.add(p);
                        bitmask &= 0b1101;
                    }
                }

                mask[i/2+1][j/2+1] = bitmask;
                bfs[i/2+1][j/2+1] = p;
            }
        }


        while (!queue.isEmpty()) {
            Point state = queue.poll();

            int i = state.y;
            int j = state.x;

            int newdist = state.dist+1;

            int bitmask = mask[i][j];
            if ((bitmask & 0b1000) != 0) {
                //test if north bit is on
                if (bfs[i-1][j].dist > newdist) {
                    bfs[i-1][j].dist = newdist;
                    queue.add(bfs[i-1][j]);
                }
            }

            if ((bitmask & 0b0100) != 0) {
                //test if south bit is on
                if (bfs[i+1][j].dist > newdist) {
                    bfs[i+1][j].dist = newdist;
                    queue.add(bfs[i+1][j]);
                }
            }

            if ((bitmask & 0b0010) != 0) {
                //test if east bit is on
                if (bfs[i][j+1].dist > newdist) {
                    bfs[i][j+1].dist = newdist;
                    queue.add(bfs[i][j+1]);
                }
            }

            if ((bitmask & 0b0001) != 0) {
                //test if west bit is on
                if (bfs[i][j-1].dist > newdist) {
                    bfs[i][j-1].dist = newdist;
                    queue.add(bfs[i][j-1]);
                }
            }
        }

        /*
        for (char[] arr: graph) System.out.println(Arrays.toString(arr));
        for (int[] arr: mask) {
            for (int i: arr) System.out.print(Integer.toBinaryString(i) + " ");
            System.out.println();
        }*/

        int max = -999;
        for (Point[] arr: bfs) {
            for (Point p: arr) if (p != null) max = Math.max(max, p.dist);
        }

        out.println(max);
        out.close();
    }

}

class Point {
    public int x, y, dist;
    public Point(int _x, int _y, int _dist) {
        x = _x;
        y = _y;
        dist = _dist;
    }
}
