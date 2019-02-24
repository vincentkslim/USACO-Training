import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
ID: vlim710
LANG: JAVA
TASK: milk
 */
public class milk {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("milk.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());

        int amountNeeded = Integer.parseInt(st.nextToken());
        int numFarmers = Integer.parseInt(st.nextToken());

        Farmer[] farmers = new Farmer[numFarmers];
        for(int i=0; i<numFarmers; i++) {
            st = new StringTokenizer(f.readLine());
            farmers[i] = new Farmer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(farmers, new Comparator<Farmer>() {
            @Override
            public int compare(Farmer o1, Farmer o2) {
                return o1.getPrice() - o2.getPrice();
            }
        });

        //for(Farmer farm: farmers) System.out.println(farm);

        int price = 0;
        int counter = 0;
        while(amountNeeded > 0) {
            if(amountNeeded >= farmers[counter].getNumUnits()) {
                price+=farmers[counter].getPrice() * farmers[counter].getNumUnits();
                amountNeeded-=farmers[counter].getNumUnits();
            } else {
                price+=amountNeeded * farmers[counter].getPrice();
                amountNeeded=0;
            }
            counter++;
        }

        out.println(price);
        out.close();

    }

    static class Farmer {

        private int price, numUnits;
        public Farmer(int price, int numUnits) {
            this.price = price;
            this.numUnits = numUnits;
        }

        public int getPrice() { return price; }
        public int getNumUnits() { return numUnits; }

        public String toString() {
            return price + " " + numUnits;
        }
    }
}
