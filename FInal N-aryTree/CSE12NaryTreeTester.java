/**
 * Test to check the correctness of the methods in CSE12NaryTree
 * Name: Ruoqing Song
 * ID: A17191150
 * Email: r2song@ucsd.edu
 * Sources used: zybook, PA
 *
 * File description:
 * This file contains the implementation of the test to ensure
 * the correctness of the methods in CSE12NaryTree class
 */

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import java.util.ArrayList;


/**
 * This class contains various test cases to test the CSE12NaryTree methods
 */
public class CSE12NaryTreeTester {

    CSE12NaryTree NaryTree1;  

    /**
     * tree1 that has a root and 5 children
     *
     *          5
     *    /  /  |  \  \
     *   2  4   6   8  10
     * 
     */

    CSE12NaryTree NaryTree2;  

    /**
     * tree2 that only has a root
     *
     *          5
     * 
     */

    CSE12NaryTree NaryTree3;

    /**
     * tree 3 that has 5 children and the first child
     * has four children
     *
     *                  5
     *          /    /  |  \  \
     *         2    4   6   8  10
     *      / /|\
     *     1 3 7 9
     */

    @Before
    public void setup(){
        // set up tree1 that has a root and 5 children
        NaryTree1 = new CSE12NaryTree<>(5);
        NaryTree1.add(5);
        NaryTree1.add(2);
        NaryTree1.add(4);
        NaryTree1.add(6);
        NaryTree1.add(8);
        NaryTree1.add(10);
        // set up tree2 that only has a root
        NaryTree2 = new CSE12NaryTree<>(5);
        NaryTree2.add(5);
        // set up tree3 that has 5 children and the first child
        // has four children
        NaryTree3 = new CSE12NaryTree<>(5);
        NaryTree3.add(5);
        NaryTree3.add(2);
        NaryTree3.add(4);
        NaryTree3.add(6);
        NaryTree3.add(8);
        NaryTree3.add(10);
        NaryTree3.add(1);
        NaryTree3.add(3);
        NaryTree3.add(7);
        NaryTree3.add(9);
    }

    /**
     * Tests the add method on a 5-ary tree that 
     * contains only a root node and its 5 children nodes.
     */
    @Test
    public void testAdd(){
        int [] result = {5,2,4,6,8,10,7};
        NaryTree1.add(7);
        CSE12NaryTree.Node curr = NaryTree1.root;
        ArrayList<CSE12NaryTree.Node> newList =
                new ArrayList<CSE12NaryTree.Node>();
        // add all nodes in the tree to an arraylist
        int counter = 0;
        newList.add(curr);
        while (curr.getNumChildren() != 0){
            newList.addAll(curr.getChildren());
            counter++;
            curr = newList.get(counter);
        }
        // check if the node arraylist is as expected
        for (int i = 0; i < newList.size(); i++){
            assertEquals(result[i], newList.get(i).getData());
        }
    }

    /**
     * Tests the add method on a 5-ary tree that
     * if not full
     */
    @Test
    public void testAddNotFull(){
        int [] result = {5,2,4,6,8,10,1,3,7,9,3};
        NaryTree3.add(3);
        CSE12NaryTree.Node curr = NaryTree3.root;
        ArrayList<CSE12NaryTree.Node> newList =
                new ArrayList<CSE12NaryTree.Node>();
        // add all nodes in the tree to an arraylist
        int counter = 0;
        newList.add(curr);
        while (curr.getNumChildren() != 0){
            newList.addAll(curr.getChildren());
            counter++;
            curr = newList.get(counter);
        }
        // check if the node arraylist is as expected
        for (int i = 0; i < newList.size(); i++){
            assertEquals(result[i], newList.get(i).getData());
        }
    }

    /**
     * Tests the contains method on a 5-ary tree 
     * when the element is not present in it.
     */
    @Test
    public void testContains(){
        assertFalse(NaryTree1.contains(9));
        assertTrue(NaryTree1.contains(2));
    }

    /**
     * Tests the sortTree method on a 5-ary tree
     * that contains only the root node.
     */
    @Test
    public void testSortTree(){
        ArrayList result = new ArrayList<>();
        result.add(5);
        assertTrue(result.equals(NaryTree2.sortTree()));
    }

    /**
     * Tests the sortTree method on a two layer 5-ary tree
     * that contains a root and five children
     */
    @Test
    public void testCustom(){
        ArrayList result = new ArrayList<>(Arrays.asList(2,4,5,6,8,10));
        assertTrue(result.equals(NaryTree1.sortTree()));
        
    }

    /**
     * Tests the sortTree method on a 5-ary tree
     * that is not full
     */
    @Test
    public void testSortTreeThreeLayers(){
        ArrayList result = new ArrayList<>
                (Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        assertTrue(result.equals(NaryTree3.sortTree()));
    }

}
