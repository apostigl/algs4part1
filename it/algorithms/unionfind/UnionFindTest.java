package it.algorithms.unionfind;

import java.util.Scanner;

/**
 * Repeat:
 * - read in pair of integers from standard input
 * – if they are not yet connected, connect them and print out pair
*/

public class UnionFindTest {

	public static void main(String[] args){
       Scanner in = new Scanner(System.in);
       
       System.out.println("Insert size n");
       int n = in.nextInt();
       System.out.println("Size is "+ n);
       
       QuickFindUF uf = new QuickFindUF(n+1);
       
       while (in.hasNext()) {
    	   String s = in.next();
    	   if(s.equals("exit")) {
    		   System.out.println("Program closed.");
    		   
    		   System.out.print("Array values: " + uf.toString());
    		   break;
    	    }
    	    
    	   int p = Integer.parseInt(s);
    	   int q = in.nextInt();
    	   
    	   if (!uf.connected(p, q)) {
    		   uf.union(p, q);
    		   System.out.println(p + " and " + q + " are now connected");
    	   }
       }
       
       in.close(); 
	}
}
