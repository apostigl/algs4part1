package it.algorithms.stacksqueues;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class TestRandomizedQueue {
    
    // Make sure to test that multiple iterators can be used simultaneously.
    // You can test this with a nested foreach loop. The iterators should operate independently of one another.
    private RandomizedQueue<String> rq = new RandomizedQueue<String>();
    
    @Test
    public void test4() {
        assertTrue(rq.isEmpty());

        rq.enqueue("11");
        rq.enqueue("29");
        rq.enqueue("0");
        rq.enqueue("38");
        
        assertNotNull(rq.dequeue());
        assertNotNull(rq.dequeue());
        assertNotNull(rq.dequeue());
        assertNotNull(rq.dequeue());
    }

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
        assertTrue(value.equals("1") || value.equals("2") || value.equals("3"));
    } 
    
    @Test
    public void testOneEmptyOne() {
        rq.enqueue("1");
        assertEquals(rq.dequeue(), "1");
        rq.enqueue("2");
    }
    
    
    @Test
    public void testIterator() {
        rq.enqueue("0");
        rq.enqueue("1");
        rq.enqueue("2");
        rq.enqueue("3");
        rq.enqueue("4");
        
        Iterator<String> iterator = rq.iterator();
        
        while (iterator.hasNext())
        {
            String s = iterator.next();
            assertNotNull(s);
            assertTrue(1 <= Integer.parseInt(s) && Integer.parseInt(s) <= rq.size());
            
            assertNotNull(rq.dequeue());
        }
    }
    
    // Check randomness of iterator() by enqueueing strings, getting an iterator()
    // and repeatedly calling next() until a specific enqueued string appears.
    // 1.Enqueue strings A to C, create iterator(), and call next() until C is returned
    @Test
    public void randomnessIterator() {
        for (int i = 0; i < 3000; i++) {
            rq = new RandomizedQueue<String>();

            rq.enqueue("A");
            rq.enqueue("B");
            rq.enqueue("C");
            
            Iterator<String> iterator = rq.iterator();
            
            String s = "";
            
            while (!s.equals("C")) {
                s = iterator.next();
            }
        }
    }
    
    @Test(expected = java.util.NoSuchElementException.class)
    public void emptyIterator() {
        rq = new RandomizedQueue<String>();
        
        Iterator<String> iterator = rq.iterator();
        iterator.next();
    }
}
