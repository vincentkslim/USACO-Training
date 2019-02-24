import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/*
ID: vlim710
LANG: JAVA
TASK: frac1
*/
public class frac1 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("frac1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));

        int maxDenom = Integer.parseInt(f.readLine());

        ArrayList<Fraction> fractions = new ArrayList<>();
        HashSet<Fraction> noDuplicates = new HashSet<>();

        fractions.add(new Fraction(0, 1));
        fractions.add(new Fraction(1, 1));

        for (int d = 1; d<=maxDenom; d++) {
            for (int n = 1; n<d; n++) {
                Fraction newFrac = new Fraction(n, d);
                if (!fractions.contains(newFrac)) {
                    fractions.add(newFrac);
                    noDuplicates.add(newFrac);
                }
            }
        }

        //for (Fraction frac: fractions) System.out.println(frac);

        Collections.sort(fractions, (Fraction o1, Fraction o2) -> o2.getDenominator()*o1.getNumerator() - o1.getDenominator()*o2.getNumerator());

        //System.out.println("--------------");
        //for (Fraction frac: fractions) System.out.println(frac);

        for (Fraction frac: fractions) out.println(frac);
        out.close();
    }

    static class Fraction {
        private int numerator, denominator;
        public Fraction(int n, int d) {
            int gcf = gcf(n, d);
            numerator = n/gcf;
            denominator = d/gcf;
        }

        public int getDenominator() {
            return denominator;
        }

        public int getNumerator() {
            return numerator;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Fraction) {
                Fraction other = (Fraction) obj;
                if (other.getDenominator() == getDenominator() && other.getNumerator() == getNumerator())
                    return true;
                else return false;
            }
            return false;
        }

        @Override
        public String toString() {
            return numerator + "/" + denominator;
        }
    }

    private static int gcf(int a, int b) {
        while ( b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
