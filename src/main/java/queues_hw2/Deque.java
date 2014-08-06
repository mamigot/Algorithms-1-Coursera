package queues_hw2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A double-ended queue is a generalization of a stack and a queue that supports
 * inserting and removing items from either the front or the back of the data
 * structure.
 * 
 * Linked-list implementation.
 * 
 * @author miguelamigot
 */
public class Deque<Item> implements Iterable<Item> {

	private class Node {
		public Item value;
		public Node next;
		public Node past;

		private Node(Item val, Node next, Node past) {
			this.value = val;
			this.next = next;
			this.past = past;
		}
	}

	private Node HEAD;
	private Node TAIL;
	private int size;

	/** Construct an empty deque **/
	public Deque() {
		this.size = 0;
	}

	/** Is the deque empty? **/
	public boolean isEmpty() {
		return this.size() == 0;
	}

	/** Return the number of items on the deque **/
	public int size() {
		return this.size;
	}

	/** Insert the item at the front **/
	public void addFirst(Item item) {
		if (item == null)
			throw new NullPointerException();

		Node newNode = new Node(item, null, null);

		if (this.size == 0) {
			this.HEAD = newNode;
			this.TAIL = newNode;

		} else {
			newNode.next = this.HEAD;
			this.HEAD.past = newNode;
			this.HEAD = newNode;

		}

		this.size++;
	}

	/** Insert the item at the end **/
	public void addLast(Item item) {
		if (item == null)
			throw new NullPointerException();

		Node newNode = new Node(item, null, null);

		if (this.size == 0) {
			this.HEAD = newNode;
			this.TAIL = newNode;

		} else {
			newNode.past = this.TAIL;
			this.TAIL.next = newNode;
			this.TAIL = newNode;

		}

		this.size++;
	}

	/** Delete and return the item at the front **/
	public Item removeFirst() {
		if (this.isEmpty())
			throw new NoSuchElementException();

		Item retVal = this.HEAD.value;

		if (this.size == 1) {
			this.HEAD = null;
			this.TAIL = null;

		} else {
			this.HEAD.next.past = null;
			this.HEAD = this.HEAD.next;

		}

		this.size--;

		return retVal;
	}

	/** Delete and return the item at the end **/
	public Item removeLast() {
		if (this.isEmpty())
			throw new NoSuchElementException();

		Item retVal = this.TAIL.value;

		if (this.size == 1) {
			this.HEAD = null;
			this.TAIL = null;

		} else {
			this.TAIL.past.next = null;
			this.TAIL = this.TAIL.past;

		}

		this.size--;

		return retVal;
	}

	/** Return an iterator over items in order from front to end **/
	public Iterator<Item> iterator() {

		// If the client class the next() method in the iterator and there are
		// no more items to return, throw a NoSuchElementException
		Iterator<Item> it = new Iterator<Item>() {

			private Item[] values;
			private int startPos;
			private int size;

			public boolean hasNext() {
				return this.startPos < this.size;

			}

			public Item next() {
				if (!this.hasNext())
					throw new NoSuchElementException();

				Item retVal = this.values[startPos];
				this.startPos++;

				return retVal;

			}

			public void remove() {
				throw new UnsupportedOperationException();

			}

		};

		return it;

	}

	/*
	 * @Override private String toString() { StringBuilder sb = new
	 * StringBuilder();
	 * 
	 * Node curr = this.HEAD; while (curr != null) { sb.append(curr.value +
	 * ", "); curr = curr.next; }
	 * 
	 * return sb.toString(); }
	 */

	/** Unit testing **/
	public static void main(String[] args) {

		// Deque<Integer> nums = new Deque<Integer>();
		// nums.addFirst(2);
		// nums.addFirst(3);
		// nums.addLast(10);
		// nums.addFirst(7);
		//
		// Iterator<Integer> it = nums.iterator();
		// System.out.println(nums);
		//
		// System.out.println("\n");
		//
		// for(Integer curr:nums)
		// System.out.println(curr);

		// System.out.println(it.next());

	}

}
