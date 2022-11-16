/**
 * This is the custom test for the implementation of MyBST, MyBSTIterator,
 * and MyCalendar
 * Name: Ruoqing Song
 * ID: A17191150
 * Email: r2song@ucsd.edu
 * Sources used: Zybook
 *
 * This file contains the junit test that ensures the correct implementation
 * of MyBST, MyBSTIterator, and MyCalendar
 */
import java.beans.Transient;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * This class contains custom test cases for MyBST, MyBSTIterator,
 * and MyCalendar
 */
 public class CustomTester {

    MyBST<Integer, Integer> newTree;

    /**
     * This method set up newTree for testing purposes
     
     			5
     		       / \
     		      4   9
		           \
			   10 
     */
    @Before
    public void setup(){
        MyBST.MyBSTNode<Integer, Integer> root = 
            new MyBST.MyBSTNode<>(5, 1, null);
        MyBST.MyBSTNode<Integer, Integer> four = 
            new MyBST.MyBSTNode<>(4, 2, root);
        MyBST.MyBSTNode<Integer, Integer> nine =
            new MyBST.MyBSTNode<>(9, 3, root);
        MyBST.MyBSTNode<Integer, Integer> ten = 
            new MyBST.MyBSTNode<>(10, 4, nine);

        this.newTree = new MyBST<>();
        this.newTree.root = root;
        root.setLeft(four);
        root.setRight(nine);
        nine.setRight(ten);
        this.newTree.size = 4;
    }

    /**
     * Test when predecessor is null
     */
    @Test
	public void testPredecessorNull() {
        MyBST.MyBSTNode<Integer, Integer> root = newTree.root;
        assertSame(null, root.getLeft().predecessor());
    }

    /**
     * Test insert a node when key already exist
     */
    @Test
	public void testInsertExistKey() {
        MyBST.MyBSTNode<Integer, Integer> root = newTree.root;
        newTree.insert(10,5);
        assertEquals((Integer)10, root.getRight().getRight().getKey());
        assertEquals(4, newTree.size);
    }

    /**
     * Test insert a node with a null key
     */
    @Test
	public void testInsertNullkey() {
        MyBST.MyBSTNode<Integer, Integer> root = newTree.root;
        boolean exceptionThrown = false;
		try {
			newTree.insert(null,5);
		}catch (NullPointerException e){
			exceptionThrown = true;
		}
		String errorMsg = "Exception not thrown";
		assertTrue(exceptionThrown);
    }

    /**
     * Test search a node when key is null
     */
    @Test 
    public void testSearchNull() {
        assertEquals(null, newTree.search(null));
    }

    /**
     * Test remove a node that has 2 children
     */
    @Test 
    public void testRemoveWithTwoChildren() {
        MyBST.MyBSTNode<Integer, Integer> root = newTree.root;
        assertEquals((Integer)1, newTree.remove(5));
        assertEquals((Integer)9, root.getKey());
        assertEquals(3, newTree.size);
    }

    /**
     * Test remove a non-existing node
     */
    @Test 
    public void testRemoveNonExistNode() {
        MyBST.MyBSTNode<Integer, Integer> root = newTree.root;
        assertEquals(null, newTree.remove(6));
        assertEquals(4, newTree.size);
    }

    /**
     * Test remove a node that has a null key
     */
    @Test 
    public void testRemoveKeyIsNull() {
        MyBST.MyBSTNode<Integer, Integer> root = newTree.root;
        assertEquals(null, newTree.remove(null));
        assertEquals(4, newTree.size);
    }

    /**
     * Test book two events that has exact same time
     */
    @Test
    public void testCalender() {
        MyCalendar calendar = new MyCalendar();
        assertTrue(calendar.book(0, 3));
        assertFalse(calendar.book(0, 3));
    }

    /**
     * Test book an event that ends before start
     */
    @Test
    public void testInvalidCalender() {
        MyCalendar calendar = new MyCalendar();
        boolean exceptionThrown = false;
        try {
            calendar.book(3, 0);
        }catch (IllegalArgumentException e){
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    /**
     * Test book an event that start = end
     */
    @Test
    public void testSameTimeBook() {
        MyCalendar calendar = new MyCalendar();
        boolean exceptionThrown = false;
        try {
            calendar.book(0, 0);
        }catch (IllegalArgumentException e){
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }


}
