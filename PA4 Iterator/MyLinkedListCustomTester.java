
/**
 * The custom tests for ListIterator implementation
 * Name: Ruoqing Song
 * ID: A17191150
 * Email: r2song@ucsd.edu
 * Sources used: Zybooks, Lecture slides, Lecture handouts
 * 
 * This file contains the JUnit tests to ensure the ListIterator
 * implementation functions correctly.
 */

import static org.junit.Assert.*;
import org.junit.*;

import java.util.NoSuchElementException;

/**
 * This class is the custom tester implementation for ListIterator
 * 
 * IMPORTANT: Do not change the method headers and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class MyLinkedListCustomTester {

	/**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * test the hasNext method when going backward
	 */
	@Test
	public void testHasNext() {
		MyLinkedList newList = new MyLinkedList();
		MyLinkedList.MyListIterator newIter = newList.new MyListIterator();
		newList.add(1);
		newIter.forward = false;
		assertFalse("has next when going backward",
				newIter.hasNext());
	}

	/**
	 * test the next method when there is no next element
	 */
	@Test
	public void testNext() {
		MyLinkedList newList = new MyLinkedList();
		MyLinkedList.MyListIterator newIter = newList.new MyListIterator();
		boolean nextCheck = false;
		try {
			newIter.next();
		} catch (NoSuchElementException e) {
			nextCheck = true;
		}
		assertTrue("NoSuchElementException was not raised", nextCheck);
	}

	/**
	 * test the hasPrevious method when going forward
	 */
	@Test
	public void testHasPrevious() {
		MyLinkedList newList = new MyLinkedList();
		MyLinkedList.MyListIterator newIter = newList.new MyListIterator();
		newList.add(1);
		newIter.right = newList.tail;
		newIter.left = newList.tail.getPrev();
		newIter.forward = true;
		assertTrue("Call hasPrev when going forward",
				newIter.hasPrevious());
	}

	/**
	 * test the previous method when call previous twice
	 */
	@Test
	public void testPrevious() {
		MyLinkedList newList = new MyLinkedList();
		MyLinkedList.MyListIterator newIter = newList.new MyListIterator();
		newList.add(1);
		newList.add(0);
		newIter.left = newList.tail.getPrev();
		newIter.right = newList.tail;
		assertEquals("Call previous function once",0,
				newIter.previous());
		assertEquals("Call previous function twice",1,
				newIter.previous());
	}

	/**
	 * test the nextIndex method when calling twice
	 */
	@Test
	public void testNextIndex() {
		MyLinkedList newList = new MyLinkedList();
		newList.add(1);
		newList.add(0);
		MyLinkedList.MyListIterator newIter = newList.new MyListIterator();
		assertEquals("Call nextIndex once", 0,
				newIter.nextIndex());
		newIter.next();
		assertEquals("Call nextIndex once", 1,
				newIter.nextIndex());
	}

	/**
	 * test the previousIndex method when going forward
	 */
	@Test
	public void testPreviousIndex() {
		MyLinkedList newList = new MyLinkedList();
		newList.add(1);
		MyLinkedList.MyListIterator newIter = newList.new MyListIterator();
		assertEquals(newIter.next(), 1);
		assertEquals("Call previousIndex when going forward", 0,
				newIter.previousIndex());
	}

	/**
	 * test the set method when element is null
	 */
	@Test
	public void testSet() {
		MyLinkedList newList = new MyLinkedList();
		MyLinkedList.MyListIterator newIter = newList.new MyListIterator();
		boolean setCheck = false;
		try {
			newIter.set(null);
		} catch (NullPointerException e) {
			setCheck = true;
		}
		assertTrue("NullPointerException was not raised", setCheck);
	}

	/**
	 * test the remove method when canRemoveOrSet is false
	 */
	@Test
	public void testRemoveTestOne(){
		MyLinkedList newList = new MyLinkedList();
		MyLinkedList.MyListIterator newIter = newList.new MyListIterator();
		boolean removeCheck = false;
		try {
			newIter.remove();
		} catch (IllegalStateException e) {
			removeCheck = true;
		}
		assertTrue("IllegalStateException was not raised", removeCheck);
	}

	/**
	 * test the remove method when remove twice
	 */
	@Test
	public void testRemoveTestTwo() {
		MyLinkedList newList = new MyLinkedList();
		newList.add(1);
		MyLinkedList.MyListIterator newIter = newList.new MyListIterator();
		newIter.next();
		assertEquals("it is 1", 1,
				newIter.left.getElement());
		newIter.remove();
		assertEquals("call remove once", null,
				newIter.left.getElement());
		boolean removeCheck = false;
		try {
			newIter.remove();
		} catch (IllegalStateException e) {
			removeCheck = true;
		}
		assertTrue("IllegalStateException was not raised", removeCheck);



	}

	/**
	 * test the add method when element = null
	 */
	@Test
	public void testAdd() {
		MyLinkedList newList = new MyLinkedList();
		MyLinkedList.MyListIterator newIter = newList.new MyListIterator();
		boolean addCheck = false;
		try {
			newIter.add(null);
		} catch (NullPointerException e) {
			addCheck = true;
		}
		assertTrue("NullPointerException was not raised", addCheck);

	}

}