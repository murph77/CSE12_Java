/**
 * implementation of MyMinHeap.
 * Name: Ruoqing Song
 * ID: A17191150
 * Email: r2song@ucsd.edu
 * Sources used: Zybooks and Lecture Slides
 * 
 * This file contains the implementation of MyMinHeap class,
 * and the methods that help to operate the MinHeap.
 */

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class contains the implementation of MyMinHeap and
 * the methods that help to operate the MinHeap.
 * Instance Variables:
 * data - The underlying ArrayList of MyMinHeap
 */
public class MyMinHeap<E extends Comparable<E>> implements MinHeapInterface <E>{
	public ArrayList<E> data;

	/**
	 * This is the constructor method that
	 * initializes data to an empty ArrayList
	 */
	public MyMinHeap(){
		data = new ArrayList<>();
	}

	/**
	 * This is the constructor method that
	 * Initializes a min-heap using the elements in collection
	 * @param collection - collection of elements to be used for the Heap
	 */
	public MyMinHeap(Collection<? extends E> collection){
		data = new ArrayList<>(collection);
		if(data.isEmpty() || data.contains(null)){
			String errorMsg = "Collection contains null";
			throw new NullPointerException(errorMsg);
		}
		for (int i = data.size() - 1; i >= 0; i--){
			this.percolateDown(i);
		}
	}

	/**
	 * Swap the elements at from and to indices in data
	 * @param from - the first index that needs swap
	 * @param to - the second index that needs swap
	 */
	protected void swap(int from, int to){
		E fromElement = this.data.get(from);
		E toElement = this.data.get(to);
		this.data.set(from, toElement);
		this.data.set(to, fromElement);
	}

	/**
	 * Calculate and return the parent index of the parameter index
	 * @param index - the index of the target Element
	 * @return integer that shows the parent index
	 */
	protected int getParentIdx(int index){
		int parentIdx = (int)(index-1)/2;
		return parentIdx;
	}

	/**
	 * Calculate and return the left child index of the parameter index
	 * @param index - the index of the target Element
	 * @return integer that shows the left child index
	 */
	protected int getLeftChildIdx(int index){
		int leftChild = 2 * index + 1;
		return leftChild;
	}

	/**
	 * Calculate and return the right child index of the parameter index
	 * @param index - the index of the target Element
	 * @return integer that shows the right child index
	 */
	protected int getRightChildIdx(int index){
		int rightChild = 2 * index + 2;
		return rightChild;
	}

	/**
	 * Return the index of the smaller child of the element at index.
	 * @param index - the index of the target Element
	 * @return integer that shows the smaller child index
	 */
	protected int getMinChildIdx(int index){
		int lIdx = getLeftChildIdx(index);
		int rIdx = getRightChildIdx(index);
		if(lIdx > this.data.size()-1 || rIdx < 0) {
			return -1;
		} else if (rIdx > this.data.size()-1) {
			return lIdx;
		}
		E lChild = this.data.get(lIdx);
		E rChild = this.data.get(rIdx);
		if (lChild.compareTo(rChild) <= 0) {
			return lIdx;
		}
		else if (lChild.compareTo(rChild) > 0) {
			return rIdx;
		}
		return -1;
	}


	/**
	 * Percolate the element at index up until
	 * no heap properties are violated by this element
	 * @param index - the index of the target Element
	 */
	protected void percolateUp(int index){
		int parentIdx = this.getParentIdx(index);
		E parentNode = this.data.get(parentIdx);
		E curNode = this.data.get(index);
		if(index != 0 && curNode.compareTo(parentNode) < 0){
			this.swap(index, parentIdx);
			this.percolateUp(parentIdx);
		}
	}

	/**
	 * Percolate the element at index down until 
	 * no heap properties are violated by this element
	 * @param index - the index of the target Element
	 */
	protected void percolateDown(int index){
		int lIdx = this.getLeftChildIdx(index);
		int rIdx = this.getRightChildIdx(index);
		int newIdx = index;
		// when has child
		if (lIdx < this.data.size() || rIdx < this.data.size()){
			// when only has left child and > left child
			if(lIdx < this.data.size() && rIdx > this.data.size() - 1){
				if(this.data.get(index).compareTo(this.data.get(lIdx)) > 0){
					this.swap(index,lIdx);
					newIdx = lIdx;
				}
			}
			// when only has right child and > right child
			if(rIdx < this.data.size() && lIdx > this.data.size() - 1){
				if(this.data.get(index).compareTo(this.data.get(rIdx)) > 0) {
					this.swap(index, rIdx);
					newIdx = rIdx;
				}
			}
			// when has both children
			if (rIdx < this.data.size() && lIdx < this.data.size()){
				// when only greater than left child
				if(this.data.get(index).compareTo(this.data.get(lIdx)) > 0
						&& this.data.get(index).compareTo
						(this.data.get(rIdx)) <= 0){
					this.swap(index,lIdx);
					newIdx = lIdx;
				}
				// when only greater than right child
				if(this.data.get(index).compareTo(this.data.get(rIdx)) > 0
						&& this.data.get(index).compareTo
						(this.data.get(lIdx)) <= 0) {
					this.swap(index, rIdx);
					newIdx = rIdx;
				}
				// when greater than both child
				if(this.data.get(index).compareTo(this.data.get(rIdx)) > 0
						&& this.data.get(index).compareTo
						(this.data.get(lIdx)) > 0) {
					int minChild = this.getMinChildIdx(index);
					this.swap(index, minChild);
					newIdx = minChild;
				}
			}
			if (newIdx != index){
				this.percolateDown(newIdx);
			}
		}
	}

	/**
	 * Remove the element at index from data and return it
	 * @param index - the index of the target Element
	 * @return element that just deleted
	 */
	protected E deleteIndex(int index){
		if (index == this.data.size() - 1){
			return this.data.remove(index);
		}else{
			this.swap(index,this.data.size()-1);
			E return_val = this.data.remove(this.data.size()-1);
			E newElement = this.data.get(index);
			this.percolateUp(index);
			if (newElement == this.data.get(index)){
				this.percolateDown(index);
			}
			return return_val;
			}
	}

	/**
	 * Add element to the end of the heap and percolate 
	 * the inserted element up until no heap properties are violated
	 * @param element - the element needs to insert
	 */
	public void insert(E element){
		if(element == null){
            String errorMsg = "Argument is null";
            throw new NullPointerException("errorMsg");
        }
		this.data.add(element);
		percolateUp(this.data.size()-1);
	}

	/**
	 * Return the root element of the heap
	 * @return the root element of the heap
	 */
	public E getMin(){
		if (data.size() == 0){
			return null;
		}else{
			return data.get(0);
		}
	}

	/**
	 * Remove and return the root element in the heap
	 * @return the root element of the heap
	 */
	public E remove(){
		if (data.size() == 0){
			return null;
		}else{
			E root = this.deleteIndex(0);
			return root;
		}
	}

	/**
	 * Return the number of elements in this min-heap
	 * @return integer number of elements in this min-heap
	 */
	public int size(){
		return data.size();
	}

	/**
	 * Clear out the entire heap
	 */
	public void clear(){
		this.data.clear();

	}


}