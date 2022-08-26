/**
 * The custom tests for MyLinkedList implementation
 * Name: Ruoqing Song
 * ID: A17191150
 * Email: r2song@ucsd.edu
 * Sources used: Zybooks, Lecture slides, Lecture handouts
 *
 * This file contains the JUnit tests to ensure the MyLinkedList
 * implementation functions correctly.
 */

import static org.junit.Assert.*;
import org.junit.*;


public class MyLinkedListCustomTester {

    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    @Before
    public void setUp() {

    }

    /**
     * Test the add method when data is null
     */
    @Test
    public void testAdd() {
        MyLinkedList testAddList = new MyLinkedList<>();
        boolean addCheck = false;
        try {
            testAddList.add(null);
        } catch (NullPointerException e) {
            addCheck = true;
        }
        assertTrue("NullPointerException was not raised", addCheck);
    }

    /**
     * Test the add with index method when data is null
     */
    @Test
    public void testAddWithIndexTestOne() {
        MyLinkedList testAddList = new MyLinkedList<>();
        boolean addCheck = false;
        try {
            testAddList.add(0,null);
        } catch (NullPointerException e) {
            addCheck = true;
        }
        assertTrue("NullPointerException was not raised", addCheck);
    }

    /**
     * Test the add with index method when index is smaller than 0
     */	
    @Test
    public void testAddWithIndexTestTwo() {
        MyLinkedList testAddList = new MyLinkedList<>();
        boolean addCheck = false;
        try {
            testAddList.add(-1,1);
        } catch (IndexOutOfBoundsException e) {
            addCheck = true;
        }
        assertTrue("IndexOutOfBoundsException was not raised", addCheck);
    }

    /**
     * Test the get method when index out of boundary
     */
    @Test
    public void testGet() {
        MyLinkedList testAddList = new MyLinkedList<>();
        testAddList.add(1);
        // Check when index smaller than 0
        boolean getCheck = false;
        try {
            testAddList.get(-1);
        } catch (IndexOutOfBoundsException e) {
            getCheck = true;
        }
        assertTrue("IndexOutOfBoundsException was not raised", getCheck);
        // Check when index = size
        getCheck = false;
        try {
            testAddList.get(1);
        } catch (IndexOutOfBoundsException e) {
            getCheck = true;
        }
        assertTrue("IndexOutOfBoundsException was not raised", getCheck);
        // Check when index greater than size
        getCheck = false;
        try {
            testAddList.get(2);
        } catch (IndexOutOfBoundsException e) {
            getCheck = true;
        }
        assertTrue("IndexOutOfBoundsException was not raised", getCheck);
    }

    /**
     * Test the getNth method when index out of bounds
     */
    @Test
    public void testGetNth() {
        MyLinkedList testNthList = new MyLinkedList<>();
        testNthList.add(1);
        // Check when index smaller than 0
        boolean nCheck = false;
        try {
            testNthList.getNth(-1);
        } catch (IndexOutOfBoundsException e) {
            nCheck = true;
        }
        assertTrue("IndexOutOfBoundsException was not raised", nCheck);
        // Check when index = size
        nCheck = false;
        try {
            testNthList.getNth(1);
        } catch (IndexOutOfBoundsException e) {
            nCheck = true;
        }
        assertTrue("IndexOutOfBoundsException was not raised", nCheck);
        // Check when index bigger than size
        nCheck = false;
        try {
            testNthList.getNth(2);
        } catch (IndexOutOfBoundsException e) {
            nCheck = true;
        }
        assertTrue("IndexOutOfBoundsException was not raised", nCheck);
    }

    /**
     * Test the set method when index out of bounds
     */
    @Test
    public void testSet() {
        MyLinkedList setList = new MyLinkedList<>();
        setList.add(1);
        // Check when index smaller than 0
        boolean setCheck = false;
        try {
            setList.set(-1,1);
        } catch (IndexOutOfBoundsException e) {
            setCheck = true;
        }
        assertTrue("IndexOutOfBoundsException was not raised", setCheck);
        // Check when index = size
        setCheck = false;
        try {
            setList.set(1,1);
        } catch (IndexOutOfBoundsException e) {
            setCheck = true;
        }
        assertTrue("IndexOutOfBoundsException was not raised", setCheck);
        // Check when index bigger than size
        setCheck = false;
        try {
            setList.set(2,1);
        } catch (IndexOutOfBoundsException e) {
            setCheck = true;
        }
        assertTrue("IndexOutOfBoundsException was not raised", setCheck);
    }

    /**
     * Test the remove method when index less than 0
     */
    @Test
    public void testRemoveTestOne() {
        MyLinkedList removeList = new MyLinkedList<>();
        removeList.add(1);
        boolean removeCheck = false;
        try {
            removeList.remove(-1);
        } catch (IndexOutOfBoundsException e) {
            removeCheck = true;
        }
        assertTrue("IndexOutOfBoundsException was not raised", removeCheck);
    }
    
    /**
     * Test the remove method when index equals to or larger than size
     */
    @Test
    public void testRemoveTestTwo(){
        MyLinkedList removeList = new MyLinkedList<>();
        removeList.add(1);
        // Check when index = size
        boolean removeCheck = false;
        try {
            removeList.remove(1);
        } catch (IndexOutOfBoundsException e) {
            removeCheck = true;
        }
        assertTrue("IndexOutOfBoundsException was not raised", removeCheck);
        // Check when index bigger than size
        removeCheck = false;
        try {
            removeList.remove(2);
        } catch (IndexOutOfBoundsException e) {
            removeCheck = true;
        }
        assertTrue("IndexOutOfBoundsException was not raised", removeCheck);
    }

    /**
     * Test the clear method when list is empty
     */
    @Test
    public void testClear() {
        MyLinkedList emptyList = new MyLinkedList<>();
        emptyList.clear();
        assertEquals("Size should be updated", 0, emptyList.size());
        assertTrue("LinkedList should be empty", emptyList.isEmpty());
    }

    /**
     * Test the size method when list is not empty
     */
    @Test
    public void testSize() {
        MyLinkedList newList = new MyLinkedList<>();
        newList.add(1);
        assertEquals("Size is not updated",1, newList.size());
    }
}