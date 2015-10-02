package it.algorithms.stacksqueues;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class TestRandomizedQueue {
    
    // Make sure to test that multiple iterators can be used simultaneously.
    // You can test this with a nested foreach loop. The iterators should operate independently of one another.
    
    private RandomizedQueue<String> rq = new RandomizedQueue<String>();
    
    @Test
    public void testEmpty() {
        assertEquals(rq.size(), 0);
    }
    
    @Test(expected = NullPointerException.class) 
    public void testNullPointerExceptionsWhenAddNull() {
        rq.enqueue(null);
    }
    
    @Test(expected = java.util.NoSuchElementException.class)
    public void testNoSuchElementExceptionWhenRemoveEmpty() {
       rq.dequeue();
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testNoSuchElementExceptionWhenSampleEmpty() {
       rq.dequeue();
    }
    
    @Test
    public void enqueueEmpty() {
        assertTrue(rq.isEmpty());
        rq.enqueue("1");
        assertTrue(!rq.isEmpty());
    } 

    @Test
    public void dequeue() {
        rq.enqueue("1");
        rq.enqueue("2");
        rq.enqueue("3");
        
        rq.dequeue();
        assertEquals(rq.size(), 2);
    } 
    
    @Test
    public void sample() {
        rq.enqueue("1");
        rq.enqueue("2");
        rq.enqueue("3");
        
        String value = rq.sample();
        assertTrue(1 <= Integer.parseInt(value) && Integer.parseInt(value) <= rq.size());
    } 
    
    @Test
    public void testOneEmptyOne() {
        rq.enqueue("1");
        assertEquals(rq.dequeue(), "1");
        
        rq.enqueue("2");
    }
    
    
    @Test
    public void testIterator() {
        rq.enqueue("1");
        rq.enqueue("2");
        rq.enqueue("3");
        rq.enqueue("4");
        rq.enqueue("5");
        
        rq.dequeue();
        
        Iterator<String> iterator = rq.iterator();
        
        while (iterator.hasNext())
        {
            String s = iterator.next();
            assertTrue(1 <= Integer.parseInt(s) && Integer.parseInt(s) <= rq.size());
            
            rq.dequeue();
        }
    }
    
}
