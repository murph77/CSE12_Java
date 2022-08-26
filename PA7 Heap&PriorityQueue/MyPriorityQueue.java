/**
 * Implementation of MyPriorityQueue.
 * Name: Ruoqing Song
 * ID: A17191150
 * Email: r2song@ucsd.edu
 * Sources used: Zybooks and Lecture Slides
 *
 * This file contains the implementation of a Priority Queue using MyMinHeap.
 */

import java.util.Collection;

/**
 * This class contains the implementation of MyPriorityQueue
 * and the methods that help to operate the MinHeap
 * Instance Variables:
 * heap - the underlying MyMinHeap structure of MyPriorityQueue
 */
public class MyPriorityQueue<E extends Comparable<E>>
{

	public MyMinHeap<E> heap;

	
	/**
	 * Constructor that creates an empty priority queue
	 */
	public MyPriorityQueue(){
		heap = new MyMinHeap<>();
	}

	/**
	 * Constructor that creates a priority queue from a collection
	 * @param collection The collection used to intialize priority queue
	 */
	public MyPriorityQueue(Collection<? extends E> collection){
		heap = new MyMinHeap<>(collection);
	}

	/**
	 * Adds an element to the priority queue
	 * @param element the element to be added
	 */
	public void push(E element){
		heap.insert(element);
	}

	/**
	 * Removes the element with the highest priority from the priority queue 
	 * @return the element with the highest priority
	 */
	public E pop(){
		return heap.remove();
	}

	/**
	 * Sees the element with the highest priority from the priority queue
	 * @return the element with the highest priority
	 */
	public E peek(){
		return heap.getMin();
	}

	/**
	 * Finds the number of elements in the priority queue
	 * @return the number of elements in the priority queue
	 */
	public int getLength(){
		return heap.size();
	}

	/**
	 * Remove all the elements from the priority queue.
	 */
	public void clear(){
		heap.clear();
	}
}