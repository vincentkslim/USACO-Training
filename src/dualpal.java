import java.io.*;
import java.util.StringTokenizer;

/*
ID: vlim710
LANG: JAVA
TASK: dualpal
 */
public class dualpal {
    private static String digits = "0123456789ABCDEFGHIJ";

    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("dualpal.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int num = Integer.parseInt(st.nextToken());
        int min = Integer.parseInt(st.nextToken());

        int count=0; //number of dual palindromic integers found
        int pointer = min+1; //current integer being checked
        while(count != num) {
            int palindromeCount = 0; //number of bases an integer is palindromic in
            for(int i=2; i<=10; i++) {
                if(palindromeCount >=2 ) break;
                if(checkPalindrome(base10Converter(pointer, i))) palindromeCount++;
            }
            if(palindromeCount >= 2) {
                out.println(pointer);
                count++;
            }
            pointer++;
        }
        out.close();
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
