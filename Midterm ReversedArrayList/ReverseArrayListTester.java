/**
 * Tests to check the implementation of reverseRegion method in MyArrayList.java
*/

//other import statements
import org.junit.Assert;
import org.junit.*;

//IMPORTANT: DO NOT MODIFY THE TEST HEADERS!
/**
 * This class contains various test cases to test the reverseRegion method
 */
public class ReverseArrayListTester {

    /**
     * Run before every test
     */
    @Before
    public void setUp(){

    }


    /**
     * Tests reverseRegion method when either fromIndex or toIndex
     * or both are out of bounds.
     */
    @Test
    public void testReverseIndexOutOfBounds(){
        Object[] importArr = {1,0};
        MyArrayList newList = new MyArrayList(importArr);
        String errorMsg = "IndexOutOfBoundsException not thrown";
        // fromIndex < 0
        boolean indexExceptionThrown = false;
        try {
            newList.reverseRegion(-1, 1);
        } catch (IndexOutOfBoundsException e) {
            indexExceptionThrown = true;
        }
        Assert.assertTrue(errorMsg, indexExceptionThrown);
        // fromIndex >= size
        indexExceptionThrown = false;
        try {
            newList.reverseRegion(2, 1);
        } catch (IndexOutOfBoundsException e) {
            indexExceptionThrown = true;
        }
        Assert.assertTrue(errorMsg, indexExceptionThrown);
        // toIndex < 0
        indexExceptionThrown = false;
        try {
            newList.reverseRegion(1, -1);
        } catch (IndexOutOfBoundsException e) {
            indexExceptionThrown = true;
        }
        Assert.assertTrue(errorMsg, indexExceptionThrown);
        // toIndex >= size
        indexExceptionThrown = false;
        try {
            newList.reverseRegion(0, 2);
        } catch (IndexOutOfBoundsException e) {
            indexExceptionThrown = true;
        }
        Assert.assertTrue(errorMsg, indexExceptionThrown);
        // both fromIndex and toIndex are out of bounds
        indexExceptionThrown = false;
        try {
            newList.reverseRegion(-1, 2);
        } catch (IndexOutOfBoundsException e) {
            indexExceptionThrown = true;
        }
        Assert.assertTrue(errorMsg, indexExceptionThrown);
    }


    /**
     * Tests reverseRegion method when
     * fromIndex > toIndex
     */
    @Test
    public void testReverseFromIndexGreater(){
        Object[] importArr = {1,0};
        MyArrayList newList = new MyArrayList(importArr);
        newList.reverseRegion(1,0);
        Object[] expectedOutput = {1,0};
        Assert.assertArrayEquals(expectedOutput, newList.data);
    }

    /**
     * Tests reverseRegion method when
     * fromIndex and toIndex are within bounds and
     * in the middle of the list 
     * (i.e. fromIndex > 0 and toIndex < size-1), 
     * and fromIndex is less than toIndex.
    */
    @Test
    public void testReverseIndexWithinBounds(){
        Object[] importArr = {1,1,0,0};
        MyArrayList newList = new MyArrayList(importArr);
        newList.reverseRegion(0, 2);
        Object[] expectedOutput = {0,1,1,0};
        Assert.assertArrayEquals(expectedOutput, newList.data);
    }

    /**
     * Test reverseRegion Method when fromIndex = toIndex and
     * both are within the bounds
    */
    @Test
    public void testReverseCustom(){
        Object[] importArr = {1,0};
        MyArrayList newList = new MyArrayList(importArr);
        newList.reverseRegion(1,1);
        Object[] expectedOutput = {1,0};
        Assert.assertArrayEquals(expectedOutput, newList.data);
    }


}
