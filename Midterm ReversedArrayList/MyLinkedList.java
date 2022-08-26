/**
 * Implementation of MyLinkedList with the reverse a region method
 * Name: Ruoqing Song
 * ID: A17191150
 * Email: r2song@ucsd.edu
 * File description: This file is the implementation of MyLinkedList
 * with the reverse a region method, that can reverse the element in the nodes
 * between a specified range.
 */

/**
 * A class that implements MyLinkedList with the reverse a region method
 * Instance Variables:
 * size - Keep track of the number of nodes in the linked list
 * head - Reference to the sentinel head of linked list
 * tail - Reference to the sentinel tail of linked list
 */
public class MyLinkedList<E> implements MyReverseList<E>{

    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes
     * This class is used for both MyLinkedList and MyListIterator.
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
         * @param element Element to add, can be null	
         */
        public Node(E element) {
            //Initialise the elements
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /** 
         * Set the previous node in the list
         * @param p new previous node
         */
        public void setPrev(Node p) {
            //Set the node p on the previous position
            prev = p;
        }

        /** 
         * Set the next node in the list
         * @param n new next node
         */
        public void setNext(Node n) {
            //Set the node n on the next position
            next = n;
        }

        /** 
         * Set the element 
         * @param e new element 
         */
        public void setElement(E e) {
            this.data = e;
        }

        /** 
         * Accessor to get the next Node in the list 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }
        /** 
         * Accessor to get the prev Node in the list
         * @return the previous node  
         */
        public Node getPrev() {
            return this.prev;
        } 
        /** 
         * Accessor to get the Nodes Element 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        } 
    }

    //IMPORTANT: DO NOT MODIFY THIS CONSTRUCTOR!
    //IMPORTANT: DO NOT ADD ANY MORE CONSTRUCTORS!
    /**
     * Constructor to create a doubly linked list 
     * with the argument array's elements
     * @param arr - array of elements to be used to construct the LinkedList
     */
    public MyLinkedList(E[] arr) {

        //Create dummy nodes
        head = new Node(null);
        tail = new Node(null);
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;	

        if(arr != null){
            //create list by inserting each element
            Node currNode = head;
            for(int i=0; i<arr.length; i++){
                Node newNode = new Node(arr[i]);
                currNode.next.prev = newNode;
                newNode.next = currNode.next;
                newNode.prev = currNode;
                currNode.next = newNode;

                //move pointer to the next node
                currNode = currNode.next;
                //increase size of list
                this.size++;
            }
        }
    }

    /**
     * Reverse the elements in the list between fromIndex and toIndex
     * and throw exception when index out of bounds
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
            // get all nodes within the range
            E[] reverseArr = (E[])new Object[this.size];
            for (int i = fromIndex; i < toIndex + 1; i++){
                // add the element within the range to reverseArr
                if (i >= fromIndex){
                    reverseArr[i-fromIndex] = this.get(i);
                }
            }
            E elementSetter;
            for (int i = fromIndex; i < toIndex + 1; i++){
                // transfer elements in reverseArr to Node in reverse order
                if (i >= fromIndex){
                    Node currNode = this.head;
                    //Loop through the linked list and stop at the position
                    for (int k = 0; k <= i; k++) {
                        currNode = currNode.getNext();
                    }
                    elementSetter = reverseArr[toIndex - i];
                    currNode.setElement(elementSetter);
                }
            }
        }
    }



    @Override
    /** 
     * Returns the number of elements stored
     * @return - number of elements in the linkedlist
    */
    public int size() {
        //Return the number of nodes in the linkedlist
        return this.size;
    }

    @Override
    /** 
     * Get contents at position i
     * @param index - The index position to obtain the data
     * @return the Element at the specified index
     */
    public E get(int index)	{

        Node currNode = this.getNth(index);

        //Get the value of data at the position
        E element = currNode.getElement();

        return element;	
    }


    /** A method that returns the node at a specified index,
     *  not the content
     *  @param index - position to return the node
     * @return - Node at 'index'
     */
    // Helper method to get the Node at the Nth index
    protected Node getNth(int index) {
        if (index >= this.size || index < 0)
            throw new IndexOutOfBoundsException();

        Node currNode = this.head;

        //Loop through the linked list and stop at the position
        for (int i = 0; i <= index; i++) {
            currNode = currNode.getNext();
        }

        //return the node	
        return currNode; 
    }

}
