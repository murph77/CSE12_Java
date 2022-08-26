/**
 * The custom testers for the implementation of MyMinHeap.
 * Name: Ruoqing Song
 * ID: A17191150
 * Email: r2song@ucsd.edu
 * Sources used: Zybooks and Lecture Slides
 *
 * This file contains the custom testers to ensure the correctness of
 * the implementation of MyMinHeap.
 */

import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * This class contains custom testers to ensure the correctness of
 * the implementation of MyMinHeap.
 * 
 * IMPORTANT: Do not change the method names and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class CustomTester {
	
	/**
	 * Test the constructor when there is null in collection
	 */
	@Test
	public void testMyMinHeapConstructor() {
		boolean exceptionThrown = false;
		try {
			ArrayList<Integer> inputList = new ArrayList<Integer>(
					Arrays.asList(
							new Integer[]{1, 2, 3, null}
					)
			);
			MyMinHeap<Integer> heap = new MyMinHeap<>(inputList);
		}catch (NullPointerException e){
			exceptionThrown = true;
		}
		String errorMsg = "Exception not thrown";
		assertTrue(errorMsg,exceptionThrown);
	}

	/**
	 * Test the getMinChildIdx method when two children are equal
	 */
	@Test
	public void testGetMinChildIdx() {
		MyMinHeap<Integer> heap = new MyMinHeap<>();
		ArrayList<Integer> inputList = new ArrayList<Integer>(
				Arrays.asList(
						new Integer[]{1, 0, 0}
				)
		);
		heap.data = new ArrayList<>(inputList);
		assertEquals("Minimum child index of 0",
				1, heap.getMinChildIdx(0));
	}

	/**
	 * Test the percolateUp method when current = parent
	 */
	@Test
	public void testPercolateUp() {
		MyMinHeap<Integer> heap = new MyMinHeap<>();
		ArrayList<Integer> inputList = new ArrayList<Integer>(
				Arrays.asList(
						new Integer[]{1, 1, 0}
				)
		);
		heap.data = new ArrayList<>(inputList);
		heap.percolateUp(1);
		Integer[] expected = { 1, 1, 0 };
		for (int i = 0; i < 3; i++) {
			assertEquals(
					"Nothing should change",
					expected[i],
					heap.data.get(i));
		}
	}

	/**
	 * Test the percolateDown method when is a leaf
	 */
	@Test
	public void testPercolateDown() {
		MyMinHeap<Integer> heap = new MyMinHeap<>();
		ArrayList<Integer> inputList = new ArrayList<Integer>(
				Arrays.asList(
						new Integer[]{1, 0, 0}
				)
		);
		heap.data = new ArrayList<>(inputList);
		heap.percolateDown(1);
		Integer[] expected = { 1, 0, 0 };
		for (int i = 0; i < 3; i++) {
			assertEquals(
					"Nothing should change", expected[i], heap.data.get(i));
		}
	}

	/**
	 * Test the deleteIndex method when is the last element
	 */
	@Test
	public void testDeleteIndex() {
		MyMinHeap<Integer> heap = new MyMinHeap<>();
		ArrayList<Integer> inputList = new ArrayList<Integer>(
				Arrays.asList(
						new Integer[] { 1, 0, 0 }
				)
		);
		heap.data = new ArrayList<>(inputList);
		heap.deleteIndex(2);
		Integer[] expected = { 1, 0 };
		for (int i = 0; i < 2; i++) {
			assertEquals(
					"Only delete last element", expected[i], heap.data.get(i)
			);
		}
		assertEquals("size after delete ", 2, heap.data.size()
		);
	}

	/**
	 * Test the deleteIndex method when needs to percolate up
	 */
	@Test
	public void testDeleteIndex2() {
		MyMinHeap<Integer> heap = new MyMinHeap<>();
		ArrayList<Integer> inputList = new ArrayList<Integer>(
				Arrays.asList(
						new Integer[] { 5,4,3,2,1 }
				)
		);
		heap.data = new ArrayList<>(inputList);
		heap.deleteIndex(3);
		Integer[] expected = { 1,5,3,4 };
		for (int i = 0; i < 4; i++) {
			assertEquals(
					"heap after delete", expected[i], heap.data.get(i)
			);
		}
		assertEquals("size after delete ", 4, heap.data.size()
		);
	}

	/**
	 * Test the insert method when element is null
	 */
	@Test
	public void testInsert(){
		MyMinHeap<Integer> heap = new MyMinHeap<>();
		ArrayList<Integer> inputList = new ArrayList<Integer>(
				Arrays.asList(
						new Integer[]{1,0,0}
				)
		);
		boolean exceptionThrown = false;
		try {
			heap.insert(null);
		}catch (NullPointerException e){
			exceptionThrown = true;
		}
		String errorMsg = "Exception not thrown";
		assertTrue(errorMsg,exceptionThrown);
	}

	/**
	 * Test the insert method when no percolate is needed
	 */
	@Test
	public void testInsert2(){
		MyMinHeap<Integer> heap = new MyMinHeap<>();
		ArrayList<Integer> inputList = new ArrayList<Integer>(
				Arrays.asList(
						new Integer[] { 1,2,3,4}
				)
		);
		heap.data = new ArrayList<>(inputList);
		heap.insert(5);
		Integer[] expected = { 1,2,3,4,5 };
		for (int i = 0; i < 5; i++) {
			assertEquals(
					"heap after delete", expected[i], heap.data.get(i)
			);
		}
		assertEquals("size after insert ", 5, heap.data.size()
		);
	}
   
	/**
	 * Test remove when heap is empty
	 */
	@Test
	public void testRemove(){
		MyMinHeap<Integer> heap = new MyMinHeap<>();
		ArrayList<Integer> inputList = new ArrayList<Integer>(
				Arrays.asList(
						new Integer[]{}
				)
		);
		assertEquals("should return null",
				null, heap.remove());
	}

  
	/**
	 * Test getMin when heap is empty
	 */
	@Test
	public void testGetMin(){
		MyMinHeap<Integer> heap = new MyMinHeap<>();
		ArrayList<Integer> inputList = new ArrayList<Integer>(
				Arrays.asList(
						new Integer[]{}
				)
		);
		assertEquals("should return null",
				null, heap.getMin());
		
	}
}