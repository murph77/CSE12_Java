/**
 * Implementation of MyLinkedList and ListIterator
 * Name: Ruoqing Song
 * ID: A17191150
 * Email: r2song@ucsd.edu
 * Sources used: Zybooks, Lecture slides, Lecture handouts
 *
 * This file is the implementation of the linked list and its iterator.
 */
import java.util.AbstractList;
import java.util.NoSuchElementException;
import java.util.ListIterator;
import java.util.Iterator;

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
            String errorMsg = "Index is out of bounds";
            throw new IndexOutOfBoundsException(errorMsg);
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
            String errorMsg1 = "Node pointer is null";
            throw new NullPointerException(errorMsg1);
        }
        if (index < 0 || index > this.size){
            String errorMsg2 = "Index is out of bounds";
            throw new IndexOutOfBoundsException(errorMsg2);
        }
        Node newNode = new Node(data);
        Node prevNode = this.head; 
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
    public boolean add(E data) {
        if (data == null) {
            String errorMsg = "Data is null";
            throw new NullPointerException(errorMsg);
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
            String errorMsg1 = "Data is null";
            throw new NullPointerException(errorMsg1);
        }
        if (index < 0 || index >= this.size){
            String errorMsg2 = "Index is out of bounds";
            throw new IndexOutOfBoundsException(errorMsg2);
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
            String errorMsg = "Index is out of bounds";
            throw new IndexOutOfBoundsException(errorMsg);
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
            String errorMsg = "Index is out of bounds";
            throw new IndexOutOfBoundsException(errorMsg);
        }
        Node newNode = this.head; 
        for (int i = 0; i < index + 1; i++){
            newNode = newNode.getNext();
        }
        return newNode;   
    }

    /**
     * This class is the implementation of an iterator called ListIterator
     * Instance Variables:
     * left - The left node reference to determine the iterator location
     * right - The right node references to determine the iterator location
     * idx - Int value of the index of the next node
     * forward - Determine the current moving direction of the iterator
     * canRemoveOrSet - Keep track of whether the current state of the 
     * iterator allows remove or set operation
     */
    protected class MyListIterator implements ListIterator<E> {
        // Instance variables
        Node left, right;
        int idx;
        boolean forward;
        boolean canRemoveOrSet; 

        /**
         * Constructor that initialize the iterator
         */
        public MyListIterator(){
            this.left = head;
            this.right = head.getNext();
            idx = 0;
            this.forward = true;
            this.canRemoveOrSet = false; 
        }

        /**
         * Determine if there is an element node on the right going forwards
         * 
         * @return true if there is an element node on the right
         */
        public boolean hasNext(){
            return right.getElement() != null; // How do we differenciate dummy node?
        } 
        
        /**
         * Return the next element in the list when going forward 
         * and move the iterator forward by one node
         * 
         * @return the next element in the list
         */
        public E next(){
            if (this.right.getElement() == null) {
                String errorMsg = "There is no next element";
                throw new NoSuchElementException(errorMsg);
            }
            this.forward = true;
            this.canRemoveOrSet = true;
            idx += 1;
            E returnElement = this.right.getElement();
            this.left = this.right;
            this.right = this.right.getNext();
            return returnElement;
        }

        /**
         * Determine if there is an element node on the left going backwards
         * 
         * @return true if there is an element node on the left
         */
        public boolean hasPrevious(){
            return left.getElement() != null;
        }
        
        /**
         * Return the previous element in the list when going forward 
         * and move the iterator backward by one node
         * 
         * @return the previous element in the list
         */
        public E previous(){
            if (this.left.getElement() == null) {
                String errorMsg = "There is no previous element";
                throw new NoSuchElementException(errorMsg);
            }
            this.forward = false;
            this.canRemoveOrSet = true;
            idx -= 1;
            E returnElement = this.left.getElement();
            this.left = this.left.getPrev();
            return returnElement;
        }

        /**
         * Return the index of the next element or 
         * return the list size if at the end of the list
         * 
         * @return the index of the next element or the list size
         */
        public int nextIndex(){
            int size = 0;
            Node container = head.getNext();
            // Find size
            while (container.getElement() != null){
                container = container.getNext();
                size += 1;
            }
            if (this.right == tail){
                return size;
            }
            return this.idx;
        }

        /**
         * Return the index of the previous element or
         * return -1 if at the beginning of the list
         *
         * @return the index of the previous element or -1
         */
        public int previousIndex(){
            if (this.left == head){
                return -1; 
            }
            return this.idx - 1;
        }

        /**
         * Insert the given item into the list immediately before the element
         * that would be returned by next()
         *
         * @param - type E element that needs to add
         */
        public void add(E element){
            if (element == null) {
                String errorMsg = "The element is null";
                throw new NullPointerException(errorMsg);
            }
            Node addNode = new Node(element);
            addNode.setPrev(this.left);
            addNode.setNext(this.right);
            this.left.setNext(addNode);
            this.right.setPrev(addNode);
            this.left = addNode;
            idx += 1;
            this.canRemoveOrSet = false;
        }

        /**
         * For the node returned by the most recent next/previous call
         * replace its value with the new value element
         *
         * @param - type E element that needs to set
         */
        public void set(E element){
            if (element == null) {
                String errorMsg1 = "The element is null";
                throw new NullPointerException(errorMsg1);
            }
            if (this.canRemoveOrSet == false){
                String errorMsg2 = "Cannot remove or set";
                throw new IllegalStateException(errorMsg2);
            }
            if (this.forward == true){
                this.left.setElement(element);
            } else{
                this.right.setElement(element);
            }
        }

        /**
         * Remove the last element node returned by the most recent
         * next/previous call
         */
        public void remove(){
            if (this.canRemoveOrSet == false){
                throw new IllegalStateException("Cannot remove or set");
            }
            if (this.forward == false){
                this.right = this.right.getNext();

            }else {
                if (this.forward == true) {
                    this.left = this.left.getPrev();
                    idx -= 1;
                }
            }
            this.canRemoveOrSet = false;
            }
        }
        // more methods, etc.
}