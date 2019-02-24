/*
ID: vlim710
LANG: JAVA
TASK: friday
*/
import java.io.*;
import java.util.*;

class friday {
	public static void main (String [] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("friday.in"));
    	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
    	int[] counter = new int[7]; //index 0 = sunday, 1 = monday, etc...

    	int years = Integer.parseInt(f.readLine());
    	int dayOfWeek = 1; //jan 1, 1900 day of week is monday, represented by 1
    	//outer loop represents years
    	for(int i=0; i<years; i++) {
    		int year = 1900 + i;
    		boolean leap = isLeapYear(year);
    		//inner loop represents each month
    		for(int j = 1; j<=12; j++) { //jan = 1, feb = 2, etc
    			//System.out.println("Computing year " + year + ", month " + j);
    			//System.out.println("The first day of the month is " + dayOfWeek);
    			counter[((dayOfWeek-1) % 7 + 7) % 7]++; //java modulous returns negative numbers!!
    			if(j == 9 || j == 4 || j == 6 || j == 11) {
    				dayOfWeek = (dayOfWeek+ 2)%7; //30 % 7 = 2
    			} else if(j == 2) {
    				if(leap){
    					dayOfWeek = (dayOfWeek+1) % 7; //29 % 7 = 1 
    				} //else the first day of the month doesn't change
    			} else {
    				dayOfWeek = (dayOfWeek+3) % 7; //31 % 7 = 3
    			}
    		}
    	}

    	//for(int i: counter) out.print(i + " ");
    	for(int i=0; i<counter.length; i++) {
    		if(i < counter.length-1) out.print(counter[i] + " ");
    		else out.println(counter[i]);
    	}
    	out.close();
	}

	public static boolean isLeapYear(int year) {
		if(year % 400 == 0) return true;
		if(year % 100 == 0) return false;
		if(year % 4 == 0) return true;
		return false;
	}
}
