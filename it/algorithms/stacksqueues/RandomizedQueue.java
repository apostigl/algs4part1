package it.algorithms.stacksqueues;

/*----------------------------------------------------------------
 *  Author:        Angelo Postiglione
 *  Written:       01/10/2015
 *  Last updated:  01/10/2015
 *
 *  A randomized queue is similar to a stack or queue, except that 
 *  the item removed is chosen uniformly at random from items in the data structure.
 *  
 *  http://coursera.cs.princeton.edu/algs4/assignments/queues.html
 *
 *----------------------------------------------------------------*/

// The order of two or more iterators to the same randomized queue must be mutually independent
// throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator
// throw a java.util.NoSuchElementException if the client calls the next() method in the iterator and there are no more items to return
public class RandomizedQueue<Item> implements Iterable<Item> {
    
    // construct an empty randomized queue
    public RandomizedQueue() {
        
    }
    
    // is the queue empty?
    public boolean isEmpty() {
        
    }
    
    // return the number of items on the queue
    public int size() {
        
    }
    
    // add the item
    public void enqueue(Item item) {
        // Throw a java.lang.NullPointerException if the client attempts to add a null item
    }
    
    // remove and return a random item
    public Item dequeue() {
        //throw a java.util.NoSuchElementException if the client attemptsto sample or dequeue an item from an empty randomized queue
    }
    
    // return (but do not remove) a random item
    public Item sample() {
        
        //throw a java.util.NoSuchElementException if the client attempts to sample or dequeue an item from an empty randomized queue
        
    }
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        
    }
    
    // unit testing
    public static void main(String[] args) {
        
    }
 }