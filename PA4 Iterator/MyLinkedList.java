

import java.util.AbstractList;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> extends AbstractList<E> {

	int size;
	Node head;
	Node tail;

	@Override
	public ListIterator<E> listIterator() {
		return new MyListIterator();
	}

	@Override
	public Iterator<E> iterator() {
		return new MyListIterator();
	}

	protected class MyListIterator implements ListIterator<E> {

		// class variables here
		Node left;
		Node right;
		int idx;
		boolean forward;
		boolean canRemoveOrSet;
		// MyListIterator methods

		// Default Constructor used to initialize the iterator
		public MyListIterator() {
			this.left = head;
			this.right = head.getNext();
			this.idx = 0;
			boolean forward = true;
			boolean canRemoveOrSet = false;
		}

		// Return true if there is an element node when going in the forward (head to
		// tail) direction
		// from the current iterator position. Sentinel (dummy) nodes do not count as
		// element nodes
		public boolean hasNext() {

			if (left.getNext().getNext() == null) {

				return false;
			}
			return true;
		}
		// more methods, etc.

		@Override
//		Return the next element in the list when going forward, 
//		and move the iterator forward by one node.
		public E next() {
			// TODO Auto-generated method stub

			E result = null;
			if (left.getNext() == null) {
				throw new NoSuchElementException();
			}
			if (right.next == null) {//checking if it's the only element
				canRemoveOrSet = true;//updating variables
				forward = true;
				idx++;
				result = right.data;

			}
			if (hasNext()) {//test if it has next;
				canRemoveOrSet = true;//updating variables
				forward = true;
				idx++;
				result = right.data;
				right = right.getNext();

			}

			return result;
		}

		@Override
		//Return true if there is an element node
		//when going in the backward (tail to head) direction
		//from the current iterator position. Sentinel 
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			if (right.getPrev().getPrev() == null) {

				return false;
			}
			return true;
		}

		@Override
		//Return the next element in the list when going backward, 
		//and move the iterator backward by one node.
		public E previous() {

			// TODO Auto-generated method stub
			E result = null;
			if (left.getPrev() == null) {
				throw new NoSuchElementException();
			}
			if (left.prev == null) {
				canRemoveOrSet = true;
				forward = false;
				idx--;
				result = left.data;

			}
			if (left.prev != null) {
				canRemoveOrSet = true;
				forward = false;
				idx--;
				result = left.data;
				left = left.getPrev();
			}
			return result;
		}

		@Override
		//Return the index of the element that would be returned by a call to next().
		//Return the list size if at the end of the list.

		public int nextIndex() {
			if (idx == size) {
				return size;
			} else {
				return idx;
			}
			// TODO Auto-generated method stub

		}

		@Override
		//Return the index of the element that would be returned by a call to previous().
		//Return -1 if at the start of the list.

		public int previousIndex() {
			// TODO Auto-generated method stub
			if (idx == 0) {
				return -1;
			} else {
				return idx - 1;
			}

		}

		
		//Remove the last element node returned 
		//by the most recent next/previous call
		//Throw a NullPointerException if element is null.
		//Throw an IllegalStateException if neither next nor previous were called, 
		//or if add or remove have been called since the most recent next/previous call.
		@Override
		public void remove() {
			if (forward && canRemoveOrSet == false) {
				throw new IllegalStateException();
			} else {
				if (canRemoveOrSet && forward) {
					left.data = left.getPrev().data;
					idx--;
				}
				if (forward == false && canRemoveOrSet) {
					right.data = right.getNext().data;
				}
			}
			size--;

			canRemoveOrSet = false;
			// TODO Auto-generated method stub

		}

		
		//For the node returned by the most recent next/previous call, 
		//replace its value with the new value element.

		@Override
		public void set(E e) {
			if (e == null) {
				throw new NullPointerException();
			}
			if (forward && canRemoveOrSet == false) {
				throw new IllegalStateException();
			} else {
				if (canRemoveOrSet && forward) {//use varables to track whether next and previous					                            
					left.data = e;              //has been called and update it
				}
				if (forward == false && canRemoveOrSet) {
					right.data = e;
				}

			}
			// TODO Auto-generated method stub

		}

		
		//Insert the given item into the list immediately 
		//before the element that would be returned by next().
		//If we call previous() immediately following add,
		//the newly added item would be returned.
		//Throw a NullPointerException if element is null.
		
		@Override
		public void add(E e) {
			if (e == null) {
				throw new NullPointerException();
			} else {
				Node insertNode = new Node(e);
				left.setPrev(insertNode);
				left = insertNode;

				idx++;
				size++;
				canRemoveOrSet = false;
			}
			// TODO Auto-generated method stub

		}
	}

	/**
	 * A Node class that holds data and references to previous and n ext Nodes.
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

	// Implementation of the MyLinkedList Class
	/** Only 0-argument constructor is defined */
	public MyLinkedList() {
		/* Add your implementation here */
		// TODO
		this.head = new Node(null);
		this.tail = new Node(null);
		head.next = tail;
		tail.prev = head;

		this.size = 0;
	}

	@Override
	public int size() {
		// need to implement the size method
		return size; // TODO
	}

	@Override
	public E get(int index) {

		Node current = getNth(index);

		return current.getElement(); // TODO

	}

	@Override
	public void add(int index, E data) {
		/* Add your implementation here */
		// TODO
		if (data == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
//		 Node current = getNth(index);
		Node insert = new Node(data);

//		    for(int i = 0; i < index; i++) {
//		        insert = insert.next;	      
//		    }
		if (index == 0) {
			head.next = insert;
		}
		if (index == size) {
			tail.prev = insert;
		}

		else {
			Node count = head;
			for (int i = 0; i < index; i++) {
				count = count.getNext();
				insert.setNext(count.getNext());
				count.setNext(insert);
				insert.setPrev(count);
				insert.getNext().setPrev(insert);

			}

		}
		size++;
	}

	public boolean add(E data) {
		if (data == null) {
			throw new NullPointerException();
		}
		Node endNode = new Node(data);
		if (head == null) {

			head = endNode;
		} else {
			Node current = head;
			while (current.next != null && current.next != tail) {
				current = current.next;

			}
			current.next = endNode;
			endNode.prev = current;
			endNode.setNext(tail);
			tail.setPrev(endNode);

		}
		size++;
		return true;
		// TODO
	}

	public E set(int index, E data) {

		if (data == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		Node current = getNth(index);

		current.setElement(data);

		return current.data; // TODO
	}

	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node current = getNth(index);
		if (current.getPrev() != null) {
			current.getPrev().setNext(current.getNext());
		}
		if (current.getNext() != null) {
			current.getNext().setPrev(current.getPrev());
		}
		size--;
		return current.data; // TODO
	}

	public void clear() {
		head.next = tail;
		tail.prev = head;
		size = 0;
		/* Add your implementation here */
	}

	public boolean isEmpty() {
		if (size == 0) {
			return true;
		} else {
			return false;
		}
		// TODO
	}

	protected Node getNth(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node currentNode = head.next;
		int count = 0;
		while (currentNode != null) {
			if (count == index)
				return currentNode;
			count++;
			currentNode = currentNode.next;
		}

		return head;

		// TODO
	}
}