import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/*
ID: vlim710
LANG: JAVA
TASK: preface
*/
public class preface {

    //private static int[] count = new int[7];

    private static LinkedHashMap<Character, Integer> count = new LinkedHashMap<>();
    private static LinkedHashMap<String, Integer> romanNumerals = new LinkedHashMap<>();

    static {
        romanNumerals.put("M", 1000);
        romanNumerals.put("CM", 900);
        romanNumerals.put("D", 500);
        romanNumerals.put("CD", 400);
        romanNumerals.put("C", 100);
        romanNumerals.put("XC", 90);
        romanNumerals.put("L", 50);
        romanNumerals.put("XL", 40);
        romanNumerals.put("X", 10);
        romanNumerals.put("IX", 9);
        romanNumerals.put("V", 5);
        romanNumerals.put("IV", 4);
        romanNumerals.put("I", 1);


        count.put('I', 0);
        count.put('V', 0);
        count.put('X', 0);
        count.put('L', 0);
        count.put('C', 0);
        count.put('D', 0);
        count.put('M', 0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("preface.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));


        int numPages = Integer.parseInt(f.readLine());

        for (int i=1; i<=numPages; i++) {
            int2Roman(i);
        }

        System.out.println(count);

        for (Character c: count.keySet()) if (count.get(c) > 0)out.println(c + " " + count.get(c));

        out.close();
    }

    public static void int2Roman(int i) {
        for (Map.Entry<String, Integer> entry: romanNumerals.entrySet()) {
            int temp = i/entry.getValue();

            for (int j=0; j<temp; j++)
                for (int c = 0; c<entry.getKey().length(); c++)
                    //count[numeral2Index(entry.getKey().charAt(c))]++;
                    count.put(entry.getKey().charAt(c), count.get(entry.getKey().charAt(c))+1);


            i %= entry.getValue();

        }

    }

    public static int numeral2Index(char c) {
        switch (c) {
            case 'M':
                return 6;
            case 'D':
                return 5;
            case 'C':
                return 4;
            case 'L':
                return 3;
            case 'X':
                return 2;
            case 'V':
                return 1;
            default:
                return 0;
        }
    }
}
