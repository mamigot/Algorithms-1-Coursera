package queues_hw2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.introcs.StdRandom;

/**
 * Resizing-array implementation.
 * 
 * @author miguelamigot
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] values;
	/** Where we dequeue **/
	private int front;
	/** Where we enqueue **/
	private int rear;
	private int size;

	/** Construct an empty randomized queue **/
	public RandomizedQueue() {
		int startingSize = 5;
		this.values = (Item[]) new Object[startingSize];

		this.front = 0;
		//this.rear = 0;

		this.size = 0;
	}

	/** Is the queue empty? **/
	public boolean isEmpty() {
		return this.size() == 0;
	}

	/** Return the number of items on the queue **/
	public int size() {
		return this.size;
	}

	/** Add the item **/
	public void enqueue(Item item) {
		if (item == null)
			throw new NullPointerException();

		// Double the size of the array when it is full
		if (this.isFull())
			this.resize(2 * this.values.length);

		// Add it to the array
		this.values[this.rear] = item;
		this.rear = this.incrementPosition(this.rear);
		this.size++;

	}

	/** Delete and return a random item **/
	public Item dequeue() {
		if (this.isEmpty())
			throw new NoSuchElementException();

		// Halve the size of the array when it is
		// one-quarter full
		if (!this.isEmpty() && this.size() == this.values.length / 4)
			this.resize(this.values.length / 2);

		// Take it out of the array
		Item retVal = this.values[this.front];
		// Avoid loitering
		this.values[this.front] = null;
		this.front = this.incrementPosition(this.front);
		this.size--;

		return retVal;
	}

	/** Return (but do not delete) a random item **/
	public Item sample() {
		if (this.isEmpty())
			throw new NoSuchElementException();

		// Ensure that the returned value is not null

		Item retVal = null;
		int randomIndex;
		do {
			randomIndex = StdRandom.uniform(0, this.values.length);
			retVal = this.values[randomIndex];

		} while (retVal == null);

		return retVal;
	}

	/** Return an independent iterator over items in random order **/
	public Iterator<Item> iterator() {
		// If the client class the next() method in the iterator and there are
		// no more items to return, throw a NoSuchElementException
		class CustomIterator<Item> implements Iterator<Item> {

			private Item[] itValues;
			private int front;
			private int size;

			private CustomIterator(Object[] values, int size) {
				// Fill itValues in a nonrandom order
				this.itValues = (Item[]) new Object[size];
				int counter = 0;

				for (int i = 0; i < values.length; i++) {
					if (values[i] != null) {
						this.itValues[counter] = (Item) values[i];
					}

					counter++;
				}

				// Implement O(N) randomization algorithm
				// Knuth shuffle
				StdRandom.shuffle(itValues);
				this.front = 0;
			}

			public boolean hasNext() {
				return this.front <= this.itValues.length - 1;

			}

			public Item next() {
				if (!this.hasNext())
					throw new NoSuchElementException();

				Item retVal = this.itValues[this.front];
				this.itValues[this.front] = null;
				this.front++;

				return retVal;

			}

			public void remove() {
				throw new UnsupportedOperationException();

			}

		}

		return new CustomIterator<Item>(this.values,
				this.size());
		
	}

	private boolean isFull() {
		return !this.isEmpty() && (this.front == this.rear);

	}

	private int incrementPosition(int curr) {
		return (curr + 1) % this.values.length;
	}

	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
		int copyIndex = 0;

		while (copyIndex == 0 || this.front != this.rear) {
			copy[copyIndex] = this.values[this.front];

			this.front = this.incrementPosition(this.front);
			copyIndex++;
		}

		this.values = copy;
		// Reset front and rear pointers
		this.front = 0;
		this.rear = copyIndex;

	}

	/*
	 * @Override public String toString() { StringBuilder sb = new
	 * StringBuilder();
	 * 
	 * for (int i = 0; i < this.values.length; i++) { if (this.values[i] ==
	 * null) sb.append(" _ "); else sb.append(" " + this.values[i] + " "); }
	 * 
	 * return sb.toString();
	 * 
	 * }
	 */

	/** Unit testing **/
	public static void main(String[] args) {

		 RandomizedQueue<Character> nums = new RandomizedQueue<Character>();
		 nums.enqueue('a');
		 nums.enqueue('a');
		 nums.enqueue('a');
		 nums.dequeue();
		 nums.dequeue();
		 nums.enqueue('b');
		 nums.enqueue('c');
		 nums.enqueue('d');
		 nums.enqueue('e');
		 nums.enqueue('f');
		 
		 for(Character bob:nums)
			 System.out.println(bob);

	}

}
