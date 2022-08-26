/**
 * Implementation of MyArrayList with the reverse a region method
 * Name: Ruoqing Song
 * ID: A17191150
 * Email: r2song@ucsd.edu
 * File description: This file is the implementation of MyArrayList
 * with the reverse a region method, that can reverse the element in the array
 * between a specified range.
 */

/**
 * A class that implements MyArrayList with the reverse a region method
 * Instance Variables:
 * data - Object array whose index correspond to the index in the ArrayList.
 * size - Integer that equals to the valid element in data array.
 */
public class MyArrayList<E> implements MyReverseList<E> {
    static final int DEFAULT_CAPACITY = 5;
    
    Object[] data;
    int size;

    //IMPORTANT: DO NOT MODIFY THIS CONSTRUCTOR!
    //IMPORTANT: DO NOT ADD ANY MORE CONSTRUCTORS!

    /**
     * Constructor to create an array list with the given array's elements
     * @param arr - array of elements to be used to construct the ArrayList
     */
    public MyArrayList(E[] arr) {
        if (arr == null) {
            this.data = new Object[DEFAULT_CAPACITY];
        } else {
            this.data = arr.clone();
            this.size = arr.length;
        }
    }

    /**
	 * Reverse the elements in the list between fromIndex and toIndex
     * and throw exception when index out of bounds.
     * @param fromIndex - the starting index of the reverse region
     * @param toIndex - the ending index of the reverse region
	 */
    public void reverseRegion(int fromIndex, int toIndex){
        // fromIndex out of bounds
        if (fromIndex < 0 || fromIndex >= this.size) {
            String errorMsg = "Index is out of bounds";
            throw new IndexOutOfBoundsException(errorMsg); 
        }
        // toIndex out of bounds
        if (toIndex < 0 || toIndex >= this.size) {
            String errorMsg = "Index is out of bounds";
            throw new IndexOutOfBoundsException(errorMsg); 
        }
        // both within bounds
        if (fromIndex < toIndex){
            // copy the list
            Object[] newData = new Object[this.size];
            for (int i = 0; i < this.size; i++){
                newData[i] = this.data[i];
            }
            // reverse within the range
            for (int i = fromIndex; i < toIndex + 1; i++){
                this.data[i] = newData[toIndex - i + fromIndex];
            }
        };
    


    }

    @Override
    /**
     * A method that returns the number of valid elements
     * in the ArrayList 
     * @return - number of valid elements in the arraylist
     */
    public int size() {
        return this.size;
    }

    @Override
    /**
     * A method that returns an Element at the specified index
     * @param index - the index of the return Element
     * @return Element at specified index
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) data[index];
    }
}
