package it.algorithms.stacksqueues;


import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

import edu.princeton.cs.algs4.StdOut;

public class TestDeque {
    
    private Deque<String> deque = new Deque<String>();

    @Test
    public void testEmpty() {
        assertEquals(deque.size(), 0);
    }
    
    /**
     * If you call addFirst() with the numbers 1 through N in ascending order,
     * then call removeLast() N times, you should see the numbers 1 through N in ascending order
     */
    @Test
    public void testAddFirstAndRemoveAll() {
        deque.addFirst("1");
        deque.addFirst("2");
        deque.addFirst("3");
        deque.addFirst("4");
        deque.addFirst("5");
        
        System.out.println(deque.removeLast());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeLast());
    }

    @Test
    public void testIterator() {
        deque.addFirst("1");
        deque.addFirst("2");
        deque.addFirst("3");
        deque.addFirst("4");
        deque.addFirst("5");
        
        deque.removeFirst();
        
        Iterator<String> i = deque.iterator();
        
        while (i.hasNext())
        {
            String s = i.next();
            StdOut.println(s);
        }
    }
}
