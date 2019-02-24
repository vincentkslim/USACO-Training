/*
ID: vlim710
LANG: JAVA
TASK: contact
*/

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class contact {
    public static void main (String [] args) throws IOException {
        Scanner input = new Scanner(new File("contact.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));
        
        int A = input.nextInt();
        int B = input.nextInt();
        int N = input.nextInt();
        
        String str = "";
        StringBuilder sb = new StringBuilder(); //using stringbuilder makes a HUGE difference!
        while (input.hasNext()) {
            sb.append(input.nextLine());
        }
        str = sb.toString();
        HashMap<String, Integer> frequency = new HashMap<>();
        for (int i = 0; i <= str.length() - A; i++) {
            for (int j = i+A; j <= Math.min(i+B, str.length()); j++) {
                //generate all substrings of length [A,B]
                String sub = str.substring(i,j);
                Integer val  = frequency.get(sub);
                frequency.put(sub, (val==null) ? 1 : val+1);
            }
        }

        /*
        for (String tmp : frequency.keySet()) {
            System.out.printf("%s: %d\n", tmp, frequency.get(tmp));
        }*/

        //everything else from here on out is just to print out the frequencies in the 
        //right format
        List<Entry<String, Integer>> sort = new ArrayList<>(frequency.entrySet());

        //sort.sort(Entry.comparingByValue());

        sort.sort(new Comparator<Entry<String, Integer>>() { //sorting based on the problem statement
            public int compare(Entry<String, Integer> o1, Entry<String, Integer>  o2) {
                int result;
                if((result = o1.getValue()-o2.getValue())!= 0) return result;
                else if((result = o2.getKey().length() - o1.getKey().length()) != 0) return result;
                else return Integer.parseInt(o2.getKey(), 2) - Integer.parseInt(o1.getKey(), 2);
            }
        });

        /*
        for (Entry<String,Integer> entry : sort) {
            System.out.printf("%s: %d\n", entry.getKey(), entry.getValue());
        }*/

        int counter = 1;
        int linecounter = 1;
        int previous = sort.get(sort.size()-1).getValue();
        out.println(previous);
        out.print(sort.get(sort.size()-1).getKey());

        for (int i = sort.size()-2; i >= 0; i--) {
            Entry<String, Integer> u = sort.get(i);
            if(u.getValue() == previous) {
                if(linecounter < 6) {
                    out.print(" " + u.getKey());
                    linecounter++;
                } else {
                    out.println();
                    out.print(u.getKey());
                    linecounter = 1;
                }
            }
            else {
                if(counter >= N) break;
                out.println();
                out.println(u.getValue());
                out.print(u.getKey());
                previous=u.getValue();
                counter++;
                linecounter=1;
            }
        }
        out.println();
        out.close();
    }
}