/**
 * The hidden tests for MyArrayList implementation
 * Name: Ruoqing Song
 * ID: A17191150
 * Email: r2song@ucsd.edu
 * Sources used: Zybooks, Lecture slides, Lecture handouts
 * 
 * This file contains the hidden JUnit tests to ensure the MyArrayList 
 * implementation functions correctly.
 */

 //IMPORTANT: Do not change the headers!

import static org.junit.Assert.*;
import org.junit.*;

/**
 * This class test if the functions in MyArrayList functions
 * correctly, returns correct values, or throw exceptions when expected.
 */
public class MyArrayListHiddenTester {

	/**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test */
	@Before
	public void setUp() throws Exception {
		
	}

	/**
	 * Aims to test the constructor when the input argument
	 * is not valid
	 */
	@Test
	public void testConstructorInvalidArg(){
		boolean illegalArgumentExceptionThrown = false; 
		try{
			int initialCapacity = -7; 
			MyArrayList invalidList = new MyArrayList(initialCapacity);
		} catch (IllegalArgumentException e) {
			illegalArgumentExceptionThrown = true;
		}
		assertTrue
		("Exception not thrown for invalid input for capacity arg constructor",
		illegalArgumentExceptionThrown);
	}

	/**
	 * Aims to test the constructor when the input argument
	 * is null
	 */
	@Test
	public void testConstructorNullArg(){
		boolean nullExceptionThrown = false; 
		try { 
			Object[] nullArr = null;
			MyArrayList nullList = new MyArrayList(nullArr);
			assertTrue("Length not equal to default no-arg construtor",
			5 == nullList.getCapacity());
		} catch (NullPointerException e) { 
			nullExceptionThrown = true;
		}
		assertFalse("Exception thrown for null input for array arg constructor", nullExceptionThrown);
	}
	
	/**
	 * Aims to test the append method when an element is added to a full list
	 * Check reflection on size and capacity
	 */
	@Test
	public void testAppendAtCapacity(){
		Object[] fullArr = new Object[1];
		fullArr[0] = 1;
		MyArrayList fullList = new MyArrayList(fullArr);
		fullList.append(2);
		assertEquals("The capacity is not updated", 2, fullList.getCapacity());
	}

	/**
	 * Aims to test the prepend method when a null element is added
	 * Checks reflection on size and capacity
	 * Checks whether null was added successfully
	 */
	@Test
	public void testPrependNull(){
		Object[] preArr = new Object[1];
		preArr[0] = 1;
		MyArrayList preList = new MyArrayList(preArr);
		preList.prepend(null);
		assertNull("Null is not prepended",preList.get(0));
	}
	
	/**
	 * Aims to test the insert method when input index is out of Bounds
	 */
	@Test
	public void testInsertOutOfBounds(){
		//when index < 0
		boolean insertOutOfBoundsExceptionThrown = false; 
		try{
			Object[] insArr = new Object[1];
			MyArrayList insList = new MyArrayList(insArr);
			insList.insert(-1, 1);
		} catch (IndexOutOfBoundsException e) {
			insertOutOfBoundsExceptionThrown = true;
		}
		assertTrue("Exception not thrown for insert out of Bounds",
		insertOutOfBoundsExceptionThrown);
		//when index > size
		insertOutOfBoundsExceptionThrown = false;
		try{
			Object[] insArr = new Object[1];
			MyArrayList insList = new MyArrayList(insArr);
			insList.insert(2, 1);
		} catch (IndexOutOfBoundsException e) {
			insertOutOfBoundsExceptionThrown = true;
		}
		assertTrue("Exception not thrown for insert out of Bounds",
		insertOutOfBoundsExceptionThrown);
	}

	/**
	 * Insert multiple (eg. 1000) elements sequentially beyond capacity -
	 * Check reflection on size and capacity
	 * Hint: for loop could come in handy
	 */
	@Test 
	public void testInsertMultiple(){
		Object[] insArr = new Object[1];
		MyArrayList insList = new MyArrayList(insArr);
		for (int i = 0; i < 11; i++){
			insList.insert(i, 1);
		}
		assertEquals("Capacity is not updated", 16, insList.getCapacity());
		assertEquals("Size is not updated", 12, insList.size());
	}

	/**
	 * Aims to test the get method when input index is out of Bounds
	 */
	@Test
	public void testGetOutOfBounds(){
		//when index < 0
		boolean getOutOfBoundsExceptionThrown = false; 
		try{
			Object[] getArr = new Object[1];
			MyArrayList getList = new MyArrayList(getArr);
			getList.get(-1);
		} catch (IndexOutOfBoundsException e) {
			getOutOfBoundsExceptionThrown = true;
		}
		assertTrue("Exception not thrown for get out of Bounds",
				getOutOfBoundsExceptionThrown);
		//when index = size
		getOutOfBoundsExceptionThrown = false;
		try{
			Object[] getArr = new Object[1];
			MyArrayList getList = new MyArrayList(getArr);
			getList.get(1);
		} catch (IndexOutOfBoundsException e) {
			getOutOfBoundsExceptionThrown = true;
		}
		assertTrue("Exception not thrown for get out of Bounds",
				getOutOfBoundsExceptionThrown);
		//when index > size 
		getOutOfBoundsExceptionThrown = false;
		try{
			Object[] getArr = new Object[1];
			MyArrayList getList = new MyArrayList(getArr);
			getList.get(2);
		} catch (IndexOutOfBoundsException e) {
			getOutOfBoundsExceptionThrown = true;
		}
		assertTrue("Exception not thrown for get out of Bounds",
				getOutOfBoundsExceptionThrown);
	}

	/**
	 * Aims to test the set method when input index is out of Bounds
	 */
	@Test
	public void testSetOutOfBounds(){
		//when index < 0
		boolean setOutOfBoundsExceptionThrown = false; 
		try{
			Object[] setArr = new Object[1];
			MyArrayList setList = new MyArrayList(setArr);
			setList.set(-1,2);
		} catch (IndexOutOfBoundsException e) {
			setOutOfBoundsExceptionThrown = true;
		}
		assertTrue("Exception not thrown for set out of Bounds",
				setOutOfBoundsExceptionThrown);
		//when index = size
		setOutOfBoundsExceptionThrown = false;
		try{
			Object[] setArr = new Object[1];
			MyArrayList setList = new MyArrayList(setArr);
			setList.set(1,2);
		} catch (IndexOutOfBoundsException e) {
			setOutOfBoundsExceptionThrown = true;
		}
		assertTrue("Exception not thrown for set out of Bounds",
				setOutOfBoundsExceptionThrown);
		//when index > size 
		setOutOfBoundsExceptionThrown = false;
		try{
			Object[] setArr = new Object[1];
			MyArrayList setList = new MyArrayList(setArr);
			setList.set(2,2);
		} catch (IndexOutOfBoundsException e) {
			setOutOfBoundsExceptionThrown = true;
		}
		assertTrue("Exception not thrown for set out of Bounds",
				setOutOfBoundsExceptionThrown);
	}

	/**
	 * Aims to test the remove method when input index is out of Bounds
	 */
	@Test
	public void testRemoveOutOfBounds(){
		//when index < 0
		boolean removeOutOfBoundsExceptionThrown = false; 
		try{
			Object[] removeArr = new Object[1];
			MyArrayList removeList = new MyArrayList(removeArr);
			removeList.remove(-1);
		} catch (IndexOutOfBoundsException e) {
			removeOutOfBoundsExceptionThrown = true;
		}
		assertTrue("Exception not thrown for remove out of Bounds",
		removeOutOfBoundsExceptionThrown);
		//when index = size
		removeOutOfBoundsExceptionThrown = false;
		try{
			Object[] removeArr = new Object[1];
			MyArrayList removeList = new MyArrayList(removeArr);
			removeList.remove(1);
		} catch (IndexOutOfBoundsException e) {
			removeOutOfBoundsExceptionThrown = true;
		}
		assertTrue("Exception not thrown for remove out of Bounds",
		removeOutOfBoundsExceptionThrown);
		//when index > size 
		removeOutOfBoundsExceptionThrown = false;
		try{
			Object[] removeArr = new Object[1];
			MyArrayList removeList = new MyArrayList(removeArr);
			removeList.remove(2);
		} catch (IndexOutOfBoundsException e) {
			removeOutOfBoundsExceptionThrown = true;
		}
		assertTrue("Exception not thrown for remove out of Bounds",
		removeOutOfBoundsExceptionThrown);
	}

	/**
	 * Aims to test the expandCapacity method when 
	 * requiredCapacity is strictly less than the current capacity
	 */
	@Test
	public void testExpandCapacitySmaller(){
		boolean expandCapacitySmallerExceptionThrown = false; 
		try{
			Object[] expArr = new Object[2];
			MyArrayList expList = new MyArrayList(expArr);
			expList.expandCapacity(1);
		} catch (IllegalArgumentException e) {
			expandCapacitySmallerExceptionThrown = true;
		}
		assertTrue("Exception not thrown for expand capacity smaller",
		expandCapacitySmallerExceptionThrown);
	}

	/**
	 * Aims to test the expandCapacity method when 
	 * requiredCapacity is greater than double(2x) the current capacity
	 */
	@Test
	public void testExpandCapacityExplode(){
		Object[] expArr = new Object[2];
		MyArrayList expList = new MyArrayList(expArr);
		expList.expandCapacity(5);
		assertEquals("The capacity is not updated to the required capacity",
		5, expList.getCapacity());
	}
	

}
