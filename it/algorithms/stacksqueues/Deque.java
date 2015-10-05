package it.algorithms.stacksqueues;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*----------------------------------------------------------------
 *  Author:        Angelo Postiglione
 *  Written:       01/10/2015
 *  Last updated:  02/10/2015
 *
 *  A double-ended queue or deque (pronounced "deck") is a generalization
 *  of a stack and a queue that supports adding and removing items from 
 *  either the front or the back of the data structure.
 *  
 *  http://coursera.cs.princeton.edu/algs4/assignments/queues.html
 *
 *----------------------------------------------------------------*/

public class Deque<Item> implements Iterable<Item> {
    
    private Node first;
    private Node last;
    
    private int size;

    /**
     * Inner class which represent a Node.
     * 
     * A node contains an Item and pointers to previous and following node:
     */
    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    /**
     * Construct an empty deque
     */
    public Deque() {
        first = null;
        last = null;
        
        size = 0;
    }   
    
    /**
     * Checks if the deque is empty
     * 
     * @return true if the deque is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }   
    
    /**
     * The number of items stored in the deque
     * 
     * @return The size of the deque
     */
    public int size() {
        return size;
    }   
    
    /**
     * Add an item to the front of the deque
     * @param item The item to be added
     */
    public void addFirst(Item item) {
        if (item == null) throw new java.lang.NullPointerException();

        Node oldFirst = first;
        
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.previous = null;
        
        if (isEmpty()) {
            last = first;
        }
        else {
            oldFirst.previous = first;
        }
                
        size++;
    }        
    
    /**
     * Add an item to the end of the deque
     * @param item The item to be added
     */
    public void addLast(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        
        Node oldLast = last;
        
        last = new Node();
        last.item = item;
        last.next = null;   //avoids loitering
        last.previous = oldLast;
                
        if (isEmpty()) {
            first = last;
        }
        else {
            oldLast.next = last;
        }
        
        size++;
    }     

    /**
     * Remove and return the item from the front
     * @return the item removed
     */
    public Item removeFirst() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        
        Item oldFirstItem = first.item;
        first.item = null;       //avoids loitering
        first = first.next;
        
        size--;

        if (!isEmpty()) {
            first.previous = null;        
        }
        
        return oldFirstItem;
    }   
    
    /**
     * Remove and return the item from the end
     * @return the item removed
     */
    public Item removeLast() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        
        Item oldLastItem = last.item;
        last.item = null;
        last = last.previous;

        size--;
        
        if (!isEmpty()) {
            last.next = null;
        }
        
        return oldLastItem;
    }   
    
    /**
     * Return an iterator over items in order from front to end
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }  
    
    /**
     * An iterator for the Deque. It doesn't implement remove() since it's optional
     *
     */
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
    
    /**
     * A test client
     * @param args
     */
    public static void main(String[] args) {
        
    }
}