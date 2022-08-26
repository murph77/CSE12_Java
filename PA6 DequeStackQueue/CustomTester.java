/**
 * This is the custom test for the implementation of MyDeque, MyStack,
 * and MyQueue.
 * Name: Ruoqing Song
 * ID: A17191150
 * Email: r2song@ucsd.edu
 * Sources used: Zybook
 * 
 * This file contains the junit test that ensures the correct implementation
 * of MyDeque, MyStack, and MyQueue class.
 */

import org.junit.*;
import static org.junit.Assert.*;

/**
 * This class contains custom test cases for MyDeque, MyStack, and MyQueue.
 * 
 * IMPORTANT: Do not change the method names and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class CustomTester {
    // ----------------MyDeque class----------------
    /**
     * Test the constructor when initial capacity < 0
     */
    @Test
    public void testMyDequeConstructor() {
        boolean exceptionThrown = false; 
		try { 
			MyDeque<Integer> deque = new MyDeque<>(-1);
		} catch (IllegalArgumentException e) { 
			exceptionThrown = true;
		}
        String errorMsg = "Exception not thrown";
		assertTrue(errorMsg,exceptionThrown);
	}
    

    /**
     * Test the expandCapacity method when deque is at initial capacity
     */
    @Test
    public void testMyDequeExpandCapacity() {
        MyDeque<Integer> deque = new MyDeque<>(0);
        deque.expandCapacity();
        String errMsg = "Capacity should be set to 10";
        assertEquals(errMsg,10,deque.data.length);
    }

    /**
     * Test the addFirst method when front is at index = 0
     */
    @Test
    public void testAddFirst() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        Integer[] orig = { 1, 2, 3, null, null }; 
        deque.data = orig;
        deque.front = 0;
        deque.rear = 2;
        deque.size = 3; 

        deque.addFirst(0);

        assertEquals("Capacity should not change", 5, deque.data.length);
        assertEquals("Size should + 1", 4, deque.size);
        assertEquals("Front should move to the end", 4, deque.front);
        assertEquals("Rear should not change", 2, deque.rear);
    }

    /**
     * Test the addLast method when rear is at the end
     */
    @Test
    public void testAddLast() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        Integer[] orig = { null, null, 1, 2, 3 };
        deque.data = orig; 
        deque.front = 2;
        deque.rear = 4;
        deque.size = 3; 

        deque.addLast(0);

        assertEquals("Capcaity should not change", 5, deque.data.length);
        assertEquals("Size should + 1", 4, deque.size);
        assertEquals("Front should not change", 2, deque.front);
        assertEquals("Rear should move to the front", 0, deque.rear);
    }

    /**
     * Test the removeFirst method when front is the last element
     */
    @Test
    public void testRemoveFirst() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        Integer[] orig = { 2, 3, null, null, 1 }; 
        deque.data = orig;
        deque.front = 4;
        deque.rear = 1;
        deque.size = 3; 

        deque.removeFirst();

        assertEquals("Capcaity should not change", 5, deque.data.length);
        assertEquals("Size should - 1", 2, deque.size);
        assertEquals("Front should move to 0", 0, deque.front);
        assertEquals("Rear should not change", 1, deque.rear);
        assertEquals("Element should be removed", null, deque.data[4]);

    }

    /**
     * Test the removeLast method when rear is the first element
     */
    @Test
    public void testRemoveLast() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        Integer[] orig = { 3, null, null, 1, 2}; 
        deque.data = orig;
        deque.front = 3;
        deque.rear = 0;
        deque.size = 3; 

        deque.removeLast();

        assertEquals("Capcaity should not change", 5, deque.data.length);
        assertEquals("Size should - 1", 2, deque.size);
        assertEquals("Front should not chnage", 3, deque.front);
        assertEquals("Rear should move to the end", 4, deque.rear);
        assertEquals("Element should be removed", null, deque.data[0]);
    }

    /**
     * Test the peekFirst method when empty
     */
    @Test
    public void testPeekFirst(){
        MyDeque<Integer> deque = new MyDeque<>(0);
        Integer[] orig = {};
        deque.data = orig;

        deque.peekFirst();

        assertEquals("Should return null", null, deque.peekFirst());

    }

    /**
     * Test the peekLast method when empty
     */
    @Test
    public void testPeekLast(){
        MyDeque<Integer> deque = new MyDeque<>(0);
        Integer[] orig = {};
        deque.data = orig;

        deque.peekLast();

        assertEquals("Should return null", null, deque.peekLast());


    }

    // ----------------MyStack class----------------
    /**
     * Test MyStack when pop an empty stack
     */
    @Test
    public void testMyStack(){
        MyStack<Integer> stack = new MyStack<>(0);
        Integer[] orig = {};
        stack.theStack.data = orig;
        stack.pop();
        assertEquals("Should return null", null, stack.pop());

        
    }

    // ----------------MyQueue class----------------
    /**
     * Test MyQueue when peek empty queue
     */
    @Test
    public void testMyQueue(){
        MyQueue<Integer> queue = new MyQueue<>(0);
        Integer[] orig = {};
        queue.theQueue = new MyDeque<>(0);
        queue.theQueue.data = orig;
        queue.peek();
        assertEquals("Should return null", null, queue.peek());

    }
}
