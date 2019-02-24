import java.io.*;

/*
ID: vlim710
LANG: JAVA
TASK: sprime
*/
public class sprime {
    private static PrintWriter out;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("sprime.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));

        int length = Integer.parseInt(f.readLine());

        generate(length, 0);
        out.close();
    }

    public static void generate(int length, int num) {
        if ((num+"").length() > length) {
            out.println(num/10);
            System.out.println(num/10);
            return;
        }
        for (int i=0; i<=9; i++) {
            int newNum = num+i;
            //System.out.println(newNum);
            if (isPrime(newNum)) {
                generate(length, newNum*10);
            }
        }
    }

    public static boolean isPrime(int num) {
        if (num == 0 || num == 1) return false;
        if (num == 2) return true;
        for (int i=2; i<=(int)Math.sqrt(num); i++) if (num%i == 0) return false;
        return true;
    }
}
