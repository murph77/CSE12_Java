/**
 * This is an implementation of the Binary Search Tree iterator.
 * Name: Ruoqing Song
 * Email: r2song@ucsd.edu
 * Sources used: Zybook
 *
 * This file contains the implementation of the binary search tree
 * iterator and  its relevant operation methods such as nextNode
 * to help the iterator move around the tree.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * This class implements MyBSTIterator and its relevant methods.
 */
public class MyBSTIterator<K extends Comparable<K>, V> extends MyBST<K, V> {

    /**
     * This is the abstract class that implements MyBSTIterator that
     * implements Iterator.
     *
     * Instance Variables:
     * next - the next node of current node
     * lastVisited - the previous node of current node
     */
    abstract class MyBSTNodeIterator<T> implements Iterator<T> {
        MyBSTNode<K, V> next;
        MyBSTNode<K, V> lastVisited;

        /**
         * Constructor that initializes the node iterator
         *
         * @param first The initial node that next points
         */
        MyBSTNodeIterator(MyBSTNode<K, V> first) {
            next = first;
            lastVisited = null;
        }

        /**
         * This method is used for determining if the next pointer in the
         * iterator points to null.
         *
         * @return If next is null based on the current position of iterator
         */
        public boolean hasNext() {
            return next != null;
        }


        /**
         * This method advances the iterator to the next node and
         * returns the node we visited to.
         *
         * @return MyBSTNode that we last visited
         */
        MyBSTNode<K, V> nextNode() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastVisited = next; 
            next = next.successor();
            return lastVisited;
        }

        /**
         * TODO: add inline comments for this method to demonstrate your
         *   understanding of this method.
         *
         * This method removes the last visited node from the tree.
         */
        public void remove() {
            // Throw exceptions
            if (lastVisited == null) {
                throw new IllegalStateException();
            }
            // traverse
            if (lastVisited.getRight() != null &&
                    lastVisited.getLeft() != null) {
                next = lastVisited;
            }
            // remove the node
            MyBSTIterator.this.remove(lastVisited.getKey());
            lastVisited = null;
        }
    }

    /**
     * BST Key iterator class that extends the node iterator.
     */
    class MyBSTKeyIterator extends MyBSTNodeIterator<K> {

        MyBSTKeyIterator(MyBSTNode<K, V> first) {
            super(first);
        }

        /**
         * This method advance the iterator and returns a node key
         *
         * @return K the next key
         */
        public K next() {
            return super.nextNode().getKey();
        }
    }

    /**
     * BST value iterator class that extends the node iterator.
     */
    class MyBSTValueIterator extends MyBSTNodeIterator<V> {

        /**
         * Call the constructor method from node iterator
         *
         * @param first The initial value that next points
         */
        MyBSTValueIterator(MyBSTNode<K, V> first) {
            super(first);
        }

        /**
         * This method advance the iterator and returns a node value
         *
         * @return V the next value
         */
        public V next() {
            return super.nextNode().getValue();
        }
    }

    /**
     * This method is used to obtain an iterator that iterates through the
     * value of BST.
     *
     * @return The value iterator of BST.
     */
    public MyBSTKeyIterator getKeyIterator() {
        MyBSTNode<K, V> curr = root;
        if (curr != null) {
            while (curr.getLeft() != null) {
                curr = curr.getLeft();
            }
        }
        return new MyBSTKeyIterator(curr);
    }

    /**
     * This method is used to obtain an iterator that iterates through the
     * value of BST.
     *
     * @return The value iterator of BST.
     */
    public MyBSTValueIterator getValueIterator() {
        MyBSTNode<K, V> curr = root;
        if (curr != null) {
            while (curr.getLeft() != null) {
                curr = curr.getLeft();
            }
        }
        return new MyBSTValueIterator(curr);
    }
}