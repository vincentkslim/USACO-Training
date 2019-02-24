/*
ID: vlim710
LANG: JAVA
PROG: ride 
*/
import java.io.*;
import java.util.*;
class ride {
	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("ride.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));

		String ufo = f.readLine();
		String comet = f.readLine();

		int ufoNum = 1;
		for(int i=0; i<ufo.length(); i++) ufoNum*=(int)ufo.charAt(i) - 64;

		int cometNum = 1;
		for(int i=0; i<comet.length(); i++) cometNum *= (int)comet.charAt(i) - 64;

		if(ufoNum%47 == cometNum%47) out.println("GO");
		else out.println("STAY");
		out.close();
	}
}
