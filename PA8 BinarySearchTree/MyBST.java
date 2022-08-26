/**
 * This is an implementation of Binary Search Tree.
 * Name: Ruoqing Song
 * Email: r2song@ucsd.edu
 * Sources used: Zybook
 *
 * This file contains the implementation of the binary search tree
 * and its relevant operation methods such as insert, remove, search,
 * and inorder.
 */

import java.util.ArrayList;


/**
 * This class implements MyBST and its relevant methods
 * Instance Variables:
 * root - the root of the tree
 * size - the number of the elements in the tree
 * N - the number of children a node can have
 */
public class MyBST<K extends Comparable<K>,V>{
    MyBSTNode<K,V> root = null;
    int size = 0;

    /**
     * This method returns the size of the tree
     *
     * @return the number of elements in a tree
     */
    public int size(){
        return size;
    }

    /**
     * This method insert a node according to the BST properties
     *
     * @param key the key of the node
     * @param value the value of the node
     * @return V the data stored in this MyBSTNode
     */
    public V insert(K key, V value) {
        if(key == null){
            String errorMsg = "Key is null";
			throw new NullPointerException(errorMsg);   
        }
        MyBSTNode<K,V> newNode = new MyBSTNode<K,V>(key, value, null);
        MyBSTNode<K,V> curr = root;
        if(curr == null){
            this.root = newNode;
            this.size++;
            return null;
        }
        while(curr != null){
            //if key < curr.key
            if(key.compareTo(curr.getKey())<0){
                if (curr.left == null) {
                    curr.left = newNode;
                    newNode.parent = curr;
                    size += 1;
                    return null;
                }
                curr = curr.getLeft();
            }
            //if key > curr.key
            else if(key.compareTo(curr.getKey())>0){
                if (curr.right == null) {
                    curr.right = newNode;
                    newNode.parent = curr;
                    size += 1;
                    return null;
                }
                curr = curr.getRight();
            }
            //if key = curr.key, replace
            else if(key.compareTo(curr.getKey()) == 0){
                V oldValue = curr.value;
                curr.value = value;
                return oldValue;
            }
        }
        return null;
    }

    /**
     * This method search for a node with a specific key
     * and return the value
     *
     * @param key the key of the target node
     * @return V the data corresponded with a specific key
     */
    public V search(K key){
        if (key == null){
            return null;
        }
        MyBSTNode<K,V> curr = root;
        while(curr != null){
            if(key.compareTo(curr.getKey())<0){
                curr = curr.getLeft();
            }
            else if(key.compareTo(curr.getKey())>0){
                curr = curr.getRight();
            }
            else if(key.compareTo(curr.getKey())==0){
               return curr.getValue();
            }
        }
        return null;
    }

    /**
     * This method remove the node that has a specific key
     * and return the value
     *
     * @param key the key of the target node
     * @return V the data corresponded with the specific key
     */
    public V remove(K key){
        if (key == null){
            return null;
        }
        if (this.search(key) == null){
            return null;
        }
        MyBSTNode<K,V> curr = root;
        while(curr != null){
            // move right
            if(curr.getKey().compareTo(key)<0) {
                curr = curr.getRight();
            // move left
            }else if(curr.getKey().compareTo(key)>0){
                curr = curr.getLeft();
            // found the key
            }else if(curr.getKey()==key){
                // no child
                V returnVal = curr.getValue();
                if(curr.getLeft() == null && curr.getRight() == null){
                    MyBSTNode<K,V> curParent = curr.parent;
                    if(curr == curParent.left){
                        curParent.left = null;
                        curr.parent = null;
                    }else{
                        curParent.right = null;
                        curr.parent = null;
                    }
                    size --;
                // only left child
                }else if(curr.right == null){
                    MyBSTNode<K,V> curParent = curr.parent;
                    MyBSTNode<K,V> curChild = curr.left;
                    if(curr == curParent.left){
                        curParent.left = curChild;
                        curChild.parent = curParent;
                    }else{
                        curParent.right = curChild;
                        curChild.parent = curParent;
                    }
                    size --;
                // only right child
                }else if(curr.left == null){
                    MyBSTNode<K,V> curParent = curr.parent;
                    MyBSTNode<K,V> curChild = curr.right;
                    if(curr == curParent.left){
                        curParent.left = curChild;
                        curChild.parent = curParent;
                    }else{
                        curParent.right = curChild;
                        curChild.parent = curParent;
                    }
                    size --;
                // two children
                }else{
                    MyBSTNode<K,V> successor = curr.successor();
                    MyBSTNode<K,V> sucParent = successor.parent;
                    curr.setKey(successor.key);
                    curr.setValue(successor.value);
                    if(successor.getRight() == null) {
                        successor = null;
                        if (successor == sucParent.left) {
                            sucParent.left = null;
                        } else {
                            sucParent.right = null;
                        }
                        size--;
                    }else if(successor.getRight() != null){
                        successor = null;
                        if (successor == sucParent.left) {
                            sucParent.left = successor.right;
                            successor.right.parent = sucParent;
                        } else {
                            sucParent.right = successor.right;
                            successor.right.parent = sucParent;
                        }
                        size --;
                        successor.right = null;
                    }
                }
            return returnVal;
            }
        }
        return null;
    }

    /**
     * This method do an in-order traversal of the tree
     *
     * @return ArrayList an array that contains the sorted elements
     * of tree
     */
    public ArrayList<MyBSTNode<K, V>> inorder() {
        ArrayList<MyBSTNode<K, V>> arr =new ArrayList<>();
        MyBSTNode<K, V> curr = root;
        if(root == null || this.size == 0){
            return arr;
        }
        // move left
        while(curr.left !=null){
            curr = curr.left;
        }
        while(curr!=null){
            arr.add(curr);
            curr = curr.successor();
        }
        return arr;
    }

    /**
     * This class is a static nested class of the MyBST class that
     * implements MyBSTNode
     *
     * Instance Variables:
     * key - The key that we are using to sort our nodes
     * value - The data stored in this MyBSTNode
     * parent - A reference to the parent of this MyBSTNode
     * left - A reference to the left child of this MyBSTNode
     * right - A reference to the right child of this MyBSTNode
     */
    static class MyBSTNode<K,V>{
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K,V> parent;
        private MyBSTNode<K,V> left = null;
        private MyBSTNode<K,V> right = null;

        /**
         * Creates a MyBSTNode<K,V> storing specified data
         * @param key the key the MyBSTNode<K,V> will
         * @param value the data the MyBSTNode<K,V> will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent){
            this.key = key;
            this.value = value;
            this.parent = parent; 
        }

        /**
         * Return the key stored in the the MyBSTNode<K,V>
         * @return the key stored in the MyBSTNode<K,V>
         */
        public K getKey(){
            return key;
        }

        /**
         * Return data stored in the MyBSTNode<K,V>
         * @return the data stored in the MyBSTNode<K,V>
         */
        public V getValue(){
            return value;
        }

        /**
         * Return the parent
         * @return the parent
         */
        public MyBSTNode<K,V> getParent(){
            return parent;
        }

        /**
         * Return the left child 
         * @return left child
         */
        public MyBSTNode<K,V> getLeft(){
            return left;
        }

        /**
         * Return the right child 
         * @return right child
         */
        public MyBSTNode<K,V> getRight(){
            return right;
        }

        /**
         * Set the key stored in the MyBSTNode<K,V>
         * @param newKey the key to be stored
         */
        public void setKey(K newKey){
            this.key = newKey;
        }

        /**
         * Set the data stored in the MyBSTNode<K,V>
         * @param newValue the data to be stored
         */
        public void setValue(V newValue){
            this.value = newValue;
        }

        /**
         * Set the parent
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K,V> newParent){
            this.parent = newParent;
        }

        /**
         * Set the left child
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K,V> newLeft){
            this.left = newLeft;
        }

        /**
         * Set the right child
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K,V> newRight){
            this.right = newRight;
        }

        /**
         * TODO: add inline comments for this method to demonstrate your
         *   understanding of this method. The predecessor can be implemented
         *   in a similar way.
         *
         * This method returns the in order successor of current node object.
         * It can be served as a helper method when implementing inorder().
         * @return the successor of current node object
         */
        public MyBSTNode<K, V> successor(){
            // move to the right subtree
            if(this.getRight() != null){
                MyBSTNode<K,V> curr = this.getRight();
                // find the smallest in the right subtree
                while(curr.getLeft() != null){
                    curr = curr.getLeft();
                }
                return curr;
            }
            else{
                // if right is null
                MyBSTNode<K,V> parent = this.getParent();
                MyBSTNode<K,V> curr = this;
                // traverse to find the parent
                while(parent != null && curr == parent.getRight()){
                    curr = parent;
                    parent = parent.getParent();
                }
                return parent;
            }
        }

        /*
         * This method returns the node with the greatest key that
         * is still smaller than the key of the current node.
         *
         * @return the predecessor of current node object
         */
        public MyBSTNode<K, V> predecessor(){
            // move to the left subtree
            if(this.getRight() != null){
                MyBSTNode<K,V> curr = this.getLeft();
                // get the largest element in left subtree
                while(curr.getRight() != null){
                    curr = curr.getRight();
                }
                return curr;
            }
            else{
                MyBSTNode<K,V> parent = this.getParent();
                MyBSTNode<K,V> curr = this;
                // find parent
                while(parent != null && curr == parent.getLeft()){
                    curr = parent;
                    parent = parent.getParent();
                }
                return parent;
            }
        }

        /** This method compares if two node objects are equal.
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj){
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K,V> comp = (MyBSTNode<K,V>)obj;
            
            return( (this.getKey() == null ? comp.getKey() == null : 
                this.getKey().equals(comp.getKey())) 
                && (this.getValue() == null ? comp.getValue() == null : 
                this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         * @return "Key:Value" that represents the node object
         */
        public String toString(){
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }
}