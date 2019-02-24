import java.io.*;
import java.util.StringTokenizer;

/*
ID: vlim710
LANG: JAVA
TASK: crypt1
*/
public class crypt1 {

    private static int count;

    public static void main(String[] args) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("crypt1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));

        int amtNums = Integer.parseInt(f.readLine());

        //read in all of the allowed digits
        int[] nums = new int[amtNums];
        StringTokenizer st = new StringTokenizer(f.readLine());
        for(int i=0; i<amtNums; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        count = 0;

        build(nums, "");

        out.println(count);
        out.close();
    }

    /**
     * Recursive method to generate all possible 5 digit combinations (repeats allowed) used in the
     * cryptarithm problem, described below:
     * From the USACO Training page:
     * """
     * [Note that this diagram shows far more digits in its results than
     * the required diagram above which has three digit partial products!]
     *
     *           a b c     <-- number 'abc'
     *         x   d e     <-- number 'de'; the 'x' means 'multiply'
     *      -----------
     * p1      * * * *     <-- product of e * abc; first star might be 0 (absent)
     * p2    * * * *       <-- product of d * abc; first star might be 0 (absent)
     *      -----------
     *       * * * * *     <-- sum of p1 and p2 (e*abc + 10*d*abc) == de*abc
     * """
     * p1 and p2 are generated exactly as described above.
     *
     * @param digits        Digits allowed to be in the generated cryparithm
     * @param output        Output string with length 5 that represent a,b,c,d,e
     */
    public static void build(int[] digits, String output) {
        if(output.length() != 5) {
            for(int i: digits) {
                String newOut = output+i;
                build(digits, newOut);
            }
        } else {

            //find the partial products
            int p1 = Integer.parseInt(output.substring(0,3)) * Integer.parseInt(output.charAt(4) + "");
            int p2 = Integer.parseInt(output.substring(0, 3)) * Integer.parseInt(output.charAt(3) + "");

            //find the actual product
            int product = p1 + (10*p2);

            if(
                Integer.toString(p1).length() == 3 && //check to make sure each product (including partials) are of the
                Integer.toString(p2).length() == 3 && //right length as described in the problem
                Integer.toString(product).length() == 4 &&
                checkDigits(Integer.toString(p1), digits) &&    //check to make sure each product has the right digits
                checkDigits(Integer.toString(p2), digits) &&
                checkDigits(Integer.toString(product), digits)
                )
                count++;

            //System.out.println(output);
        }
    }

    /**
     * Checks if the input string (must be made of all numbers) has only digits that are in the digits array.
     * @param input     Input string
     * @param digits    Allowed digits
     * @return          true if the input string only has digits from the digits array; false otherwise
     */
    public static boolean checkDigits(String input, int[] digits) {
        out: //read: labeled loops
        for(int i=0; i<input.length(); i++) {
            for(int j=0; j<digits.length; j++)
                if (Integer.parseInt(input.charAt(i) + "") == digits[j]) continue out;
            return false;
        }
        return true;
    }
}
