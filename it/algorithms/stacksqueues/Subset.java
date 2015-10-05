package it.algorithms.stacksqueues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*----------------------------------------------------------------
 *  Author:        Angelo Postiglione
 *  Written:       01/10/2015
 *  Last updated:  05/10/2015
 *
 *  Takes a command-line integer k; reads in a sequence of N strings 
 *  from standard input using StdIn.readString(); and prints out exactly k of them, uniformly at random
 *  
 *  Each item from the sequence can be printed out at most once. 
 *  You may assume that 0 <= k <= N, where N is the number of string on standard input.
 *  
 *  http://coursera.cs.princeton.edu/algs4/assignments/queues.html
 *
 *----------------------------------------------------------------*/

public class Subset {
    //You may use only a constant amount of memory plus either one Deque or RandomizedQueue object
    //of maximum size at most N, where N is the number of strings on standard input
    
    public static void main(String[] args) {

        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }
        
        for (int i = 1; i <= k; i++) {
            StdOut.println(rq.dequeue());
        }
       
    }
}