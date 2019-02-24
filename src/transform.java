import java.io.*;
/*
ID: vlim710
LANG: JAVA
TASK: transform
*/
public class transform {
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("transform.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));

        int numLines = Integer.parseInt(f.readLine());
        String[] original = new String[numLines];
        String[] transformed = new String[numLines];
        for(int i=0; i<numLines; i++) original[i] = f.readLine();
        for(int i=0; i<numLines; i++) transformed[i] = f.readLine();
        //for(String s: rotate180(original)) System.out.println(s);

        String result = "7";
        if(checkEqual(original, transformed)) {
            result = "6";
        }
        if(checkEqual(rotate90(reflectHorizontal(original)), transformed)) {
            result = "5";
        }
        if(checkEqual(rotate180(reflectHorizontal(original)), transformed)) {
            result = "5";
        }
        if(checkEqual(rotate270(reflectHorizontal(original)), transformed)) {
            result = "5";
        }
        if(checkEqual(reflectHorizontal(original), transformed)) {
            result = "4";
        }
        if(checkEqual(rotate270(original), transformed)) {
            result = "3";
        }
        if(checkEqual(rotate180(original), transformed)) {
            result = "2";
        }
        if(checkEqual(rotate90(original), transformed)) {
            result = "1";
        }

        out.println(result);
        out.close();

    }

    private static boolean checkEqual(String[] p1, String[] p2) {
        for(int i=0; i<p1.length; i++) {
            if(! p1[i].equals(p2[i])) return false;
        }
        return true;
    }

    private static String[] rotate90(String[] p) {
        String[] result = new String[p.length];
        //last row becomes first col, second to last row becomes second col, etc....
        for(int i=p.length-1; i>=0; i--) {
            for(int j = 0; j<p[i].length(); j++) {
                if(result[j] == null) result[j] = p[i].charAt(j) + "";
                else result[j] += p[i].charAt(j) + "";
            }
        }
        return result;
    }

    private static String[] rotate180(String[] p) {
        return rotate90(rotate90(p));
    }

    private static String[] rotate270(String[] p) {
        return rotate180(rotate90(p));
    }

    private static String[] reflectHorizontal(String[] p) {
        String[] result = new String[p.length];
        for(int i=0; i<p.length; i++) {
            for(int j=p[i].length()-1; j>=0; j--) {
                if(result[i] == null) result[i] = p[i].charAt(j) + "";
                else result[i]+=p[i].charAt(j) + "";
            }
        }
        return result;
    }
}

