package it.algorithms.stacksqueues;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

public class TestDeque {
    
    private Deque<String> deque = new Deque<String>();

    @Test
    public void testEmpty() {
        assertEquals(deque.size(), 0);
    }
    
    @Test(expected = NullPointerException.class) 
    public void testNullPointerExceptionsWhenAddNullFirst() {
        deque.addFirst(null);
    }
    
    @Test(expected = NullPointerException.class) 
    public void testNullPointerExceptionsWhenAddNullLast() {
        deque.addLast(null);
    }
    
    @Test(expected = java.util.NoSuchElementException.class)
    public void testNoSuchElementExceptionWhenRemoveFirstEmpty() {
       deque.removeFirst();
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testNoSuchElementExceptionWhenRemoveLastEmpty() {
       deque.removeLast();
    }
    
    @Test
    public void testOneEmptyOne() {
        deque.addFirst("1");
        
        assertEquals(deque.removeFirst(), "1");
        
        deque.addLast("2");
    }
    
    @Test
    public void test10() {
        
        deque = new Deque<String>();
        
        deque.addFirst("1");
        deque.addFirst("2");
        deque.addFirst("3");
        deque.addFirst("4");
        assertEquals(deque.removeLast(), "1");
        assertEquals(deque.removeFirst(), "4");
        deque.addLast("7");
        assertEquals(deque.removeLast(), "7");
        assertEquals(deque.removeLast(), "2");
        assertEquals(deque.removeLast(), "3");
        assertEquals(deque.size(), 0);
    }
    
    
    @Test
    public void testAddFirstAndRemoveLastAll() {
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
    public void testAddFirstAndRemoveFirstAll() {
        deque.addFirst("1");
        deque.addFirst("2");
        deque.addFirst("3");
        deque.addFirst("4");
        deque.addFirst("5");
        
        assertEquals(deque.removeFirst(), "5");
        assertEquals(deque.removeFirst(), "4");
        assertEquals(deque.removeFirst(), "3");
        assertEquals(deque.removeFirst(), "2");
        assertEquals(deque.removeFirst(), "1");
    }
    
    
    @Test
    public void testAddLastAndRemoveLastAll() {
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
    public void testAddLastAndRemoveFirstAll() {
        deque.addLast("1");
        deque.addLast("2");
        deque.addLast("3");
        deque.addLast("4");
        
        assertEquals(deque.removeFirst(), "1");
        assertEquals(deque.removeFirst(), "2");
        assertEquals(deque.removeFirst(), "3");
        assertEquals(deque.removeFirst(), "4");
    }
    
    
    @Test
    public void testAddLastAndAddFirstWithRemoveLast() {
        deque.addLast("4");
        deque.addLast("5");
        deque.addLast("6");
        deque.addLast("7");
        
        deque.addFirst("3");
        deque.addFirst("2");
        deque.addFirst("1");
        
        assertEquals(deque.removeLast(), "7");
        assertEquals(deque.removeLast(), "6");
        assertEquals(deque.removeLast(), "5");
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
