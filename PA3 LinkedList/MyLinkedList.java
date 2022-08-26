/**
 * Implementation of MyLinkedList
 * Name: Ruoqing Song
 * ID: A17191150
 * Email: r2song@ucsd.edu
 * Sources used: Zybooks, Lecture slides, Lecture handouts
 *
 * This file is a linked list implementation, which includes 1 constructor,
 * and 9 methods.
 */
import java.util.AbstractList;

/**
 * This class is the implementation of linkedList called myLinkedList
 * Instance Variables:
 * size - Keep track of the number of nodes in the linked list
 * head - Reference to the sentinel head of linked list
 * tail - Reference to the sentinel tail of linked list
 */
public class MyLinkedList<E> extends AbstractList<E> {

    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes.
     * Instance Variables:
     * data - The element contained in a node
     * next - The next node
     * prev - The previous node
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         *
         * @param element Element to add, can be null
         */
        public Node(E element) {
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /**
         * Set the parameter prev as the previous node
         *
         * @param prev - new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;
        }

        /**
         * Set the parameter next as the next node
         *
         * @param next - new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /**
         * Set the parameter element as the node's data
         *
         * @param element - new element
         */
        public void setElement(E element) {
            this.data = element;
        }

        /**
         * Accessor to get the next Node in the list
         *
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Accessor to get the prev Node in the list
         *
         * @return the previous node
         */
        public Node getPrev() {
            return this.prev;
        }

        /**
         * Accessor to get the Nodes Element
         *
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        }
    }

    //  Implementation of the MyLinkedList Class

    /**
     * This function is the constructor of MyLinkedList objects
     * Only 0-argument constructor is defined
     */
    public MyLinkedList() {
        this.head = new Node(null);
        this.tail = new Node(null);
        this.tail.setPrev(this.head);
        this.head.setNext(this.tail);
    }

    /**
     * Return the number of nodes being stored
     *
     * @return int - the number of stored nodes
     */
    @Override
    public int size() {
        return this.size;
    }

    /** 
     * Get data within the node at specific index
     * @param index - the index of the node
     * @return type E element at the index 
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Node getNode = this.head;
        for (int i = 0; i < index + 1; i++) {
            getNode = getNode.getNext();
        }
        return getNode.getElement();
    }

    /** 
     * Add a node into this list at specific index
     * @param index - the index of the node
     * @param data - the element on the node
     */
    @Override
    public void add (int index, E data) {
        if (data == null) {
            throw new NullPointerException("Node pointer is null");
        }
        if (index < 0 || index > this.size){
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Node newNode = new Node(data);
        Node prevNode = this.head; // necessary? 
        for (int i = 0; i < index; i++){
            prevNode = prevNode.getNext();
        }
        Node nextNode = prevNode.getNext();
        // Connect newNode and nextNode
        newNode.setNext(nextNode);
        nextNode.setPrev(newNode);
        // Connect prevNode and newNode
        prevNode.setNext(newNode);
        newNode.setPrev(prevNode);
        this.size += 1;
    }

    /** 
     * Add a node at the end of this list
     * @param data - the data on the new node
     * @return true by default
     */
    // Do we need to consider when the list is not empty?
    public boolean add(E data) {
        if (data == null) {
            throw new NullPointerException("Data is null");
        }
        Node lastNode = tail.getPrev();
        Node newNode = new Node(data);
        newNode.setNext(tail);
        tail.setPrev(newNode);
        if (isEmpty() == true) {
            newNode.setPrev(head);
            head.setNext(newNode);
        }else{
            lastNode.setNext(newNode);
            newNode.setPrev(lastNode);
        }
        this.size += 1; 
        return true; 
    }

    /**
     * Set the element for the node at index to data and 
     * return the element that was previously stored in this node
     * @param index - the index of the target node
     * @param data - the data need to set
     * @return type E element that was replaced by new data
     */
    public E set(int index, E data) {
        if (data == null) {
            throw new NullPointerException("Data is null");
        }
        if (index < 0 || index >= this.size){
                throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Node newNode = new Node(data); 
        Node prevNode = this.head;
        for (int i = 0; i < index; i++){
            prevNode = prevNode.getNext();
        }
        Node nextNode = prevNode.getNext().getNext();
        // Connect newNode and nextNode
        newNode.setNext(nextNode);
        nextNode.setPrev(newNode);
        // Connect prevNode and newNode
        prevNode.setNext(newNode);
        newNode.setPrev(prevNode);
        return get(index);
    }

    /**
     * Remove the node from the position index in this list 
     * and return the data within the removed node
     * @param index - the index of the target node
     * @return type E element that was within the removed node
     */
    public E remove(int index) {
        if (index < 0 || index >= this.size){
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        E removeIndex = this.get(index);
        Node prevNode = this.head;
        for (int i = 0; i < index; i++){
            prevNode = prevNode.getNext();
        }
        Node nextNode = prevNode.getNext().getNext();
        // Connect prevNode and nextNode
        prevNode.setNext(nextNode);
        nextNode.setPrev(prevNode);
        this.size -= 1;
        return removeIndex;
    }
     /**
     * Remove all nodes from the list
     */
    public void clear() {
        head.setNext(tail);
        tail.setPrev(head);
        this.size = 0;
    }

    /**
     * Determine if the list is empty
     * @return true if the list empty 
     */
    public boolean isEmpty() {
        if (head.getNext() == tail && tail.getPrev() == head){
            return true;
        }else{
            return false;
        }
    }

    /**
     * A helper method that returns the Node at a specified index
     * @param index - the index of the target node
     * @return the Node at a specified index
     */
    protected Node getNth(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Node newNode = this.head; 
        for (int i = 0; i < index + 1; i++){
            newNode = newNode.getNext();
        }
        return newNode;   
    }
}