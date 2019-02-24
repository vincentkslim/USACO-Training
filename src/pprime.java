import java.io.*;
import java.util.StringTokenizer;

/*
ID: vlim710
LANG: JAVA
TASK: pprime
*/
public class pprime {

    private static int min;
    private static int max;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("pprime.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        min = Integer.parseInt(st.nextToken());
        max = Integer.parseInt(st.nextToken());

        for (int i=(min+"").length(); i<=(max+"").length(); i++) {
            generatePrimePalindromes(i);
        }
        out.close();
    }

    public static boolean isPrime(int num) {
        for (int i=2; i<=(int)Math.sqrt(num); i++) if (num%i == 0) return false;
        return true;
    }

    public static void validNum(int num) {
        if (num >= min && num <= max && isPrime(num)) {
            out.println(num);
            //System.out.println(num);
        }
    }

    public static void generatePrimePalindromes(int length) {
        for (int d1=1; d1<=9; d1+=2) {
            if (length == 1) {
                validNum(d1);
            } else if(length == 2) {
                int num = 10*d1 + d1;
                validNum(num);
            } else {
                for (int d2=0; d2<=9; d2++) {
                    if (length == 3) {
                        int num =  100*d1 + 10*d2 + d1;
                        validNum(num);
                    } else if(length == 4) {
                        int num = 1000*d1 + 100*d2 + 10*d2 + d1;
                        validNum(num);
                    }
                    else {
                        for (int d3=0; d3<=9; d3++) {
                            if (length == 5) {
                                int num = 10000*d1 + 1000*d2 + 100*d3 + 10*d2 + d1;
                                validNum(num);
                            } else if (length == 6) {
                                int num = 100000*d1 + 10000*d2 + 1000*d3 + 100*d3 + 10*d2 + d1;
                                validNum(num);
                            } else {
                                for (int d4=0; d4<=9; d4++) {
                                    if (length == 7) {
                                        int num = 1000000*d1 + 100000*d2 + 10000*d3 + 1000*d4 + 100*d3 + 10*d2 + d1;
                                        validNum(num);
                                    } else if (length == 8) {
                                        int num = 10000000*d1 + 1000000*d2 + 100000*d3 + 10000*d4 + 1000*d4 + 100*d3 + 10*d2 + d1;
                                        validNum(num);
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}
