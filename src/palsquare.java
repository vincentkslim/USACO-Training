import java.io.*;

/*
ID: vlim710
LANG: JAVA
TASK: palsquare
*/
public class palsquare {

    private static String digits = "0123456789ABCDEFGHIJ";
    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("palsquare.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));

        int base = Integer.parseInt(f.readLine());
        for(int i=1; i<=300; i++) {

            //long num = int2base(i, base);
            //long square = int2base(i*i, base);

            String num = base10Converter(i, base);
            String square = base10Converter(i*i, base);

            if(checkPalindrome(square)) {
                out.println(num + " " + square);
            }
        }
        out.close();
    }
    //converts base 10 int x into base b
    public static long int2base(int x, int b) {
        String tmp = "";
        while(x>0) {
            tmp = (x%b) + tmp;
            x /= b;
        }
        if(tmp.length() == 0) tmp = "0";
        return Long.parseLong(tmp);
    }

    public static String base10Converter(long num, long base) {
        long q = num/base;
        long r = num%base;
        if(q == 0) return digits.charAt((int)r) + "";
        else return base10Converter(q, base) + digits.charAt((int)r);
    }

    //checks if a string is a palindrome (spelled the same forward and backward)
    public static boolean checkPalindrome(String input) {
        for(int i=0; i<input.length()/2; i++) {
            if(input.charAt(i) != input.charAt(input.length()-1-i)) {
                return false;
            }
        }
        return true;
    }
}
