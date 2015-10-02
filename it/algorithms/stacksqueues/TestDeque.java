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
    
    @Test
    public void testAddFirstAndRemoveAll() {
        deque.addFirst("1");
        deque.addFirst("2");
        deque.addFirst("3");
        deque.addFirst("4");
        deque.addFirst("5");
        
        assertEquals(deque.removeLast(), "1");
        assertEquals(deque.removeLast(), "2");
        assertEquals(deque.removeLast(), "3");
        assertEquals(deque.removeLast(), "4");
        assertEquals(deque.removeLast(), "5");
    }

    @Test
    public void testAddLastAndRemoveAll() {
        deque.addLast("1");
        deque.addLast("2");
        deque.addLast("3");
        deque.addLast("4");
        
        assertEquals(deque.removeLast(), "4");
        assertEquals(deque.removeLast(), "3");
        assertEquals(deque.removeLast(), "2");
        assertEquals(deque.removeLast(), "1");
    }
    
    
    
    
    @Test
    public void testIterator() {
        deque.addFirst("1");
        deque.addFirst("2");
        deque.addFirst("3");
        deque.addFirst("4");
        deque.addFirst("5");
        
        deque.removeFirst();
        
        Iterator<String> iterator = deque.iterator();
        
        int i = 4;
        while (iterator.hasNext())
        {
            String s = iterator.next();
            assertEquals(s, ""+i);
            
            i--;
        }
    }
}
