/*
ID: vlim710
LANG: JAVA
TASK: gift1
*/
import java.io.*;
import java.util.*;
class gift1 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("gift1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));

		int number = Integer.parseInt(f.readLine());
		//System.out.println(number);
		String[] names = new String[number];
		//int[] initial = new int[number];
		int[] bank = new int[number];
		for(int i = 0; i<number; i++) names[i] = f.readLine();

		//and so begins the gift giving!
		for(int i=0; i<number; i++) {
			String name = f.readLine();
			StringTokenizer st = new StringTokenizer(f.readLine());
			int money = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			int perPerson, leftover;
			if(num != 0) {
				perPerson = money/num;
				leftover = money%num;
			} else {
				perPerson = 0;
				leftover = money;
			}

			//System.out.println(name + " splits " + money + " among " + num + " people");

			//first, subtract money from name
			
			for(int j = 0; j< number; j++) 
				if(names[j].equals(name))
					bank[j]-=money;

			//add money to each person
			for(int j = 0; j<num; j++) {
				String recipient = f.readLine();
				//System.out.println(name + " gave " + perPerson + " to " + recipient);
				for(int k = 0; k<names.length; k++){
					if(names[k].equals(recipient)){
						bank[k]+=perPerson;
						break;
					}
				}
			}

			//give back leftover
			for(int j = 0; j< number; j++) 
				if(names[j].equals(name))
					bank[j]+=leftover;
		}

		//output file
		for(int i=0; i<number; i++){
			out.println(names[i] + " " + bank[i]); 
		}
		out.close();
	}
}