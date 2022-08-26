/**
 * Implementation of MyDeque based on the Deque interface
 * Name:Ruoqing Song
 * Email: r2song@ucsd.edu
 * ID: A17191150
 * Sources used: zybook
 *
 * This file contains a MyDeque class, which is an implementation
 * for the Deque Interface. Elements can be added, removed, and returned
 * from the circular array.
 */

public class MyDeque<E> {
    Object[] data;
    int size;
    int rear;
    int front;

/** 
 * Constructor to initialize the instance variables
 * @param initialCapacity 
 */
    public MyDeque(int initialCapacity) {
        if(initialCapacity<0){
            String errorMsg = "Argument<0";
            throw new IllegalArgumentException("errorMsg");
        }
        this.data = new Object[initialCapacity];
        this.rear = 0;
        this.front = 0;
    }

    /**
     * Return the size of the deque
     * @return the size of the deque
     */
    public int size(){
        return this.size;
    }

    /**
     * Doubles the current capacity
     */
    public void expandCapacity(){
        int capacity = this.data.length;
        if (capacity == 0){
            capacity = 10;
        }else{
            capacity *= 2;
        }
        Object[] newdata = new Object[capacity];
            for (int i = 0; i < this.data.length; i++){
                newdata[i] = this.data[(front + i) % this.data.length];
            }
        this.data = newdata;
        if(this.size == 0){
            this.rear = 0;
        }else{
            this.rear = this.size - 1;
        }
        this.front = 0;
    }

    /**
     * Add the specified element to the front of the deque
     * @param element
     */
    public void addFirst(E element){
        if(element == null){
            String errorMsg = "Argument is null";
            throw new NullPointerException("errorMsg");
        }
        if(this.size >= this.data.length){
            this.expandCapacity();
        }
        //when empty
        if(this.size == 0){
            this.front = 0;
        }
        //when starts from 0 index
        else if(this.front == 0) {
            this.front = this.data.length - 1;
        }
        //when starts from middle
        else if (this.front != 0){
            this.front = front - 1; 
        }
        this.data[this.front] = element;
        this.size += 1;
    }

    /**
     * Add the specified element to the rear of the deque
     * @param element
     */
    public void addLast(E element){
        if(element == null){
            String errorMsg = "Argument is null";
            throw new NullPointerException("errorMsg");
        }
        if(this.size >= this.data.length){
            this.expandCapacity();
        }
        //when empty
        if(this.size == 0){
            this.rear = 0;
        }
        //when at the end
        if(this.rear == this.data.length - 1){
            this.rear = 0;
        }
        //when in middle
        if(this.rear != 0 && this.rear != this.data.length - 1){
            this.rear = rear + 1;
        }
        this.data[this.rear] = element;
        this.size += 1;
    }

    /**
     * Removes and returns the element at the front of the deque
     * @return the removed first element
     */
    public E removeFirst(){
        //when empty 
        if(this.size == 0 || data[this.front] == null){
            return null;
        }
        E first = (E) this.data[this.front]; 
        this.data[this.front] = null; 
        if(front != rear){
            front = (front + 1) % this.data.length;
        }
        this.size -= 1;
        return first;
    }

    /**
     * Removes and returns the element at the rear of the deque
     * @return the removed last element
     */
    public E removeLast(){ 
        //when empty
        if(this.size == 0 || data[rear] == null){
            return null; 
        }
        E last = (E) this.data[this.rear];
        this.data[this.rear] = null;
        // when rear is the first element
        if (front != rear) {
            rear = (this.data.length - 1 + this.rear) % this.data.length;
        } 
        this.size -= 1;
        return last;
    }

    /**
     * Returns the element at the front of the deque
     * @return the first element
     */
    public E peekFirst(){
        //when empty
        if(this.size == 0){
            return null;
        }
        return (E) this.data[this.front];
    }

    /**
     * Returns the element at the rear of the deque
     * @return the last element
     */
    public E peekLast(){
        //when empty
        if(this.size == 0){
            return null;
        }
        return (E) this.data[this.rear];
    }
}








