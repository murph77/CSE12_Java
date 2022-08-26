/**
 * Implementation of ArrayList
 * Name: Ruoqing Song
 * ID: A17191150
 * Email: r2song@ucsd.edu
 * Sources used: Zybooks, Lecture slides, Lecture handouts
 * 
 * This file is an implementation of ArrayList, which includes
 * three constructors and nine methods.
 */

 /**
  * This class is the implementation of ArrayList called MyArrayList.
  *
  * Instance Variables:
  * data - Object array whose index correspond to the index in the ArrayList.
  * size - Integer that equals to the valid element in data array.
  */
public class MyArrayList<E> {
    
    Object[] data; 
    int size;

    /**
     * Initialize the Object array with the default length of 5.
     */
    public MyArrayList() {
        this.data = new Object[5];
        this.size = 0;
    }

    /**
     * Initialize the Object array with the length of initialCapacity.
     */
    public MyArrayList(int initialCapacity){
        if (initialCapacity < 0){
            throw new IllegalArgumentException("Initial capacity is invalid");
        }
        this.data = new Object[initialCapacity];
        this.size = 0;
    }

    /**
     * Initialize the instance variables with the input array of capacity
     * equal to the length of arr.
     */
    public MyArrayList(E[] arr){
        if (arr == null) {
            this.data = new Object[5];
            this.size = 0;
        } else {
            this.data = arr;
            this.size = arr.length;
        }
    }
    
    /**
     * Double the capacity, if current capcity is 0 reset to default, 
     * if capacity still not enough after expand, set to requiredCapacity.
     * @param requiredCapacity - capacity needed to hold the elements
     */
    public void expandCapacity(int requiredCapacity){
        if (requiredCapacity < this.getCapacity()){
            throw new IllegalArgumentException
            ("requiredCapacity is strictly less than the initial capacity.\n");
        }
        int currentCapacity = this.getCapacity();
        if (currentCapacity != 0){
            currentCapacity *= 2;
            if (requiredCapacity > currentCapacity) {
                currentCapacity = requiredCapacity;
            }
        }
        else {
            currentCapacity = 5;
            if (requiredCapacity > 5) {
                currentCapacity = requiredCapacity;
            }
        }
        //Creat new array with expanded capcity and copy the original elements
        Object[] newdata = new Object[currentCapacity];
        for (int i = 0; i < this.getCapacity(); i++){
            newdata[i] = data[i];
        }
        this.data = newdata;
    }

    /**
     * Get the capacity of the array
     * @return integer that equals to the length of the array
     */
    public int getCapacity() {
        return this.data.length; 
    }

    /**
     * Insert an element at the specific index
     * @param index - an index of the array
     * @param element - the element needs to be inserted
     */
    public void insert(int index, E element){
        if (index < 0 || index > this.size){
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        //Expand capacity if the array is full
        if (this.size == this.getCapacity()){
            this.expandCapacity(this.getCapacity() + 1);
        }
        //Creat new array with expanded capcity
        Object[] newdata = new Object[this.getCapacity()];
        //Copy the element before insert index to new array
        for (int i = 0; i < index; i++){
            newdata[i] = this.data[i];
        }
        newdata[index] = element;
        //Shift the element after insert index
        for (int i = index + 1; i < this.size + 1; i++){
            newdata[i] = this.data[i - 1];
        }
        this.data = newdata;
        this.size += 1;
    }

    /**
     * Add an element at the end of the list
     * @param element - the element needs to be added
     */
    public void append(E element){
        //Expand capacity if the array is full
        if (this.size == this.getCapacity()) {
            this.expandCapacity(this.getCapacity() + 1);
        }
        this.data[this.size] = element;
        this.size += 1;
    }

    /**
     * Add an element at the beginning of the list
     * @param element - the element needs to be added
     */
    public void prepend(E element){
        //Expand capacity if the array is full
        if (this.size == this.getCapacity()) {
            this.expandCapacity(this.getCapacity() + 1);
        }
        //Creat new array with expanded capcity
        Object[] newdata = new Object[this.getCapacity()];
        newdata[0] = element;
        //Shift the elements after index 0
        for (int i = 1; i < this.size + 1; i++){
            newdata[i] = this.data[i - 1];
        }
        this.data = newdata;
        this.size += 1;
    }

    /**
     * Get an element at the specified index
     * @param index - index of the needed element 
     * @retun the element at the specified index
     */
    public E get(int index){
        if (index < 0 || index >= this.size){
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        return (E) this.data[index];
    }

    /**
     * Set the given element at the specified index return 
     * the overwritten element
     * @param index - index of an array 
     * @param element - element to set 
     * @retun the overwritten element
     */
    public E set(int index, E element){
        if (index < 0 || index >= this.size){
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        E overwritten = (E) this.data[index];
        this.data[index] = element;
        return overwritten;
    }

    /**
     * Remove and return the element at the specified index
     * @param index - index of an array 
     * @retun the removed element
     */
    public E remove(int index){
        if (index < 0 || index >= this.size){
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        E remove = (E) this.data[index];
        //Creat new array with the new capcity
        Object[] newdata = new Object[this.getCapacity()];
        //Copy the element before remove index to new array
        for (int i = 0; i < index; i++){
            newdata[i] = this.data[i];
        }
        //Shift other elements
        for (int i = index; i < this.size - 1; i++){
            newdata[i] = this.data[i + 1];
        }
        this.data = newdata;
        this.size -= 1; 
        return remove;
    }
    /**
     * Return the number of valid elements in the arraylist
     * @retun an integer that equals to the elements in the arraylist
     */
    public int size(){
        return this.size;
    }
}

 // Hint: the 'capacity' (length of data array) is not the same as the 'size'
 // Size is the number of elements in the ArrayList, i.e., the number of valid
 // elements in the array