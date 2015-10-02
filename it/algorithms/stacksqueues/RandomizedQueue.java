package it.algorithms.stacksqueues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

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

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Item[] randomQueue;
    private int N;
    
    /**
     * Construct an empty randomized queue
     */
    public RandomizedQueue() {
        randomQueue = (Item[]) new Object[2];
    }
    
    /**
     * Checks if the RQ is empty
     * @return True if the RQ is empty, false otherwise.
     */
    public boolean isEmpty() {
        return N == 0;
    }
    
    /**
     * Returns the number of items on the queue
     * @return the size of the RQ
     */
    public int size() {
        return N;
    }
    
    /**
     * Add the item to the RQ
     * @param item The item to ad
     */
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        
        if (N == randomQueue.length) {
            resize(2*randomQueue.length);
        }

        randomQueue[N++] = item;
    }
    
    private void resize(int capacity)
    {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = randomQueue[i];
        }
        randomQueue = copy;
    }
    
    
    /**
     * Removes and returns a random item
     * @return The random item removed
     */
    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        
        int index = StdRandom.uniform(N);
        
        Item item = randomQueue[index];
        randomQueue[index] = null;
        
        // shrink size of array if necessary
        if (N > 0 && N == randomQueue.length/4) { 
            resize(randomQueue.length/2);
        }
        
        N--;
        
        return item;
    }
    
    /**
     * Returns (but do not removes) a random item
     * @return A random item in the queue
     */
    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        
        int index = StdRandom.uniform(N);
        
        return randomQueue[index];
    }
    
    
    /**
     * Return an independent iterator over items in random order.
     */
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }
    
    // The order of two or more iterators to the same randomized queue must be mutually independent
    
    /**
     * An iterator for the Randomized Queue. It doesn't implement remove() since it's optional.
     * The iterator must support operations next() and hasNext() in constant worst-case time; and construction in linear time.
     */
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i;
        
        public RandomizedQueueIterator() {
            i = StdRandom.uniform(N);
        }

        public boolean hasNext() {
            return randomQueue[i+1] != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            return randomQueue[i+1];
        }
       
    }
    
    
    // unit testing
    public static void main(String[] args) {
        
    }
 }