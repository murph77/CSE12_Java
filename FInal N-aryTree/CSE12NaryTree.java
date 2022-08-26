/**
 * The implementation of CSE12NaryTree and relevant methods
 * Name: Ruoqing Song
 * ID: A17191150
 * Email: r2song@ucsd.edu
 * Sources used: zybook, PA
 *
 * File description:
 * This file contains the implementation of CSE12NaryTree class
 * and the methods to operate the tree
 */

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * This class implements the CSE12NaryTree and its relevant methods
 * Instance Variables:
 * root - the root of the tree
 * size - the number of the elements in the tree
 * N - the number of children a node can have
 */
public class CSE12NaryTree<E extends Comparable<E>> {
    
    /**
     * This inner class encapsulates the data and children for a Node.
     * Do NOT edit this inner class.
     * Instance Variables:
     * data - the data of the tree
     * children - a list that contains all children of a node
     */
    protected class Node{
        E data;
        List<Node> children;
    
        /**
         * Initializes the node with the data passed in
         * 
         * @param data The data to initialize the node with
         */
        public Node(E data) {
            this.data = data;
            this.children = new ArrayList<>();
        }
    
        /**
         * Getter for data
         * 
         * @return Return a reference to data
         */
        public E getData() {
            return data;
        }

        /**
         * Setter for the data
         * 
         * @param data Data that this node is set to
         */
        public void setData(E data) {
            this.data = data;
        }

        /**
         * Getter for children
         * 
         * @return reference to the list of children
         */
        public List<Node> getChildren() {
            return children;
        }

        /**
         * Returns the number of children
         * 
         * @return number of children
         */
        public int getNumChildren() {
            // assume there are no nulls in list
            return children.size();
        }

        /**
         * Add the given node to this node's list of children
         * 
         * @param node The node to add
         */
        public void addChild(Node node) {
            children.add(node);
        }
    
    }
    
    Node root;
    int size;
    int N;

    /**
     * Constructor that initializes an empty N-ary tree, with the given N
     * 
     * @param N The N the N-tree should be initialized with
     */
    public CSE12NaryTree(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.root = null;
        this.size = 0;
        this.N = N;
    }

    /**
     * Add a new Node containing element to the N-ary tree
     * 
     * @param element The element needs to be added
     */
    public void add(E element) {

        if(element == null){
            throw new NullPointerException();
        }
        //if empty tree add to the root
        if (this.root == null){
            size ++;
            Node addNode = new Node(element);
            this.root = addNode;
            return;
        }
        Node parentNode = this.root;
        //check if a node's children is full
        int childSpace = N - this.root.getNumChildren();
        Queue<Node> childList = new LinkedList<Node>
        (this.root.getChildren());
        //if children is full
        while (childSpace <= 0){
            parentNode = childList.poll();
            childSpace = N - parentNode.getNumChildren();
            childList.addAll(parentNode.getChildren());
        }
        size ++;
        //if children is not full
        Node addNode = new Node(element);
        parentNode.addChild(addNode);
    }

    /**
     * Check if an element is in the N-ary tree
     *
     * @param element The element needs to be checked
     * @return true if the element exist
     */
    public boolean contains(E element) {
        if(element == null){
            throw new NullPointerException();
        }
        Node curr = this.root;
        Queue<Node> visited = new LinkedList<Node>
        (this.root.getChildren());
        int counter = 0;
        //when element not found and not reach the end
        while (!curr.getData().equals(element) && counter < this.size){
            counter++;
            curr = visited.poll();
            if (curr == null){
                return false;
            }
            visited.addAll(curr.getChildren());
        }
        //when element found
        if (curr.getData().equals(element)){
            return true;
        }
        return false;
    }

    /**
     * Sort a tree to an ArrayList in ascending order
     *
     * @return ArrayList of all elements in the tree in sorted order
     */
    public ArrayList<E> sortTree(){
        Node curr = this.root;
        ArrayList<Node> newList = new ArrayList<Node>();
        int counter = 0;
        newList.add(curr);
        //add all nodes to arraylist
        while (curr.getNumChildren() != 0){
            newList.addAll(curr.getChildren());
            counter++;
            curr = newList.get(counter);
        }
        //add all elements to arraylist
        ArrayList<E> elementList = new ArrayList<>();
        for (int i=0; i < newList.size(); i++){
            elementList.add(newList.get(i).getData());
        }
        //use priority queue and perform minheap to do sort the elements
        Queue<E> newQueue = new PriorityQueue<>(elementList);
        ArrayList sortedTree = new ArrayList<E>();
        while(!newQueue.isEmpty()){
            sortedTree.add(newQueue.poll());
        }
        return sortedTree;
    }

}
