/**
** Author: Cassandra Jacklya
** Purpose: creates a queue class to implement the last in first out operation
** Last modified on: 23rd August 2020
**/

public class DSAQueue {
	public final int defCapacity = 100;
	Object[] Queues;
	int count, rear, front;
	
	//default constructor
	public DSAQueue() {
		Queues = new Object[defCapacity];
		rear = -1;
		front = -1;
	}
	
	//alternate constructor
	public DSAQueue(int numElements) {
		Queues = new Object[numElements];
		rear = -1;
		front = -1;
	}
	
	//getter methods
	public int getRear() {
		return this.rear;
	}
	
	public int getFront() {
		return this.front;
	}
	
	public int getCount() {
		return this.count;
	}
	
	public Object peek() {
		Object top = Queues[getFront()];
		return top;
	}
	
	//validation methods to check whether the queue is empty or full
	public boolean isEmpty() {
		boolean empty = false;
		if ((getFront() == -1) && (getRear()== -1)) {
			empty = true;
		}
		return empty;
	}
	
	public boolean isFull() {
		boolean full = false;
		if (((getRear()+1)%Queues.length)==getFront()) {
			full = true;
		}
		return full;
	}
	
	//setter methods
	//these methods will be overrided in the child classes
	public void enqueue(Object value) {
	}
	
	public Object dequeue() {
		return Queues;
	}
	
	//this method displays the current queue to the main
	public String toString() {
		String word = "";
		for (int ii = 0; ii < Queues.length; ii++) {
			word = word + "[" + ii + "] " + Queues[ii] + "\n";
		}
		return word;
	}

}

	//implements two child classes extending the parent class
	class ShufflingQueue extends DSAQueue {
		
		public ShufflingQueue(int numElements) {
			//to inherit the method from the parent class
			super(numElements);
		}
		
		public ShufflingQueue() {
			super();
		}
		
		//setter methods
		public void enqueue(Object value) {
			try {
				if (isFull()) {
					throw new ArrayIndexOutOfBoundsException("Operation aborted: The shuffling queue is full");
				}
				else if (isEmpty()) {
					front = 0;
					rear = 0;
				}
				else {
					this.rear = rear + 1;
				}
				Queues[getRear()] = value;
				count+= 1;
			}
			catch (ArrayIndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
			}
		}
	
		
		public Object dequeue() {
			Object topValue = peek();
			try {
				if (isEmpty()) {
					throw new ArrayIndexOutOfBoundsException("Operation aborted: The shuffling queue is empty");
				}
				else if (getFront() == getRear()) {
					front = -1;
					rear = -1;
				}
				else {
					topValue = peek();
					this.Queues = shuffleUp();
					this.rear = rear -1;
				}
				count-=1;
			}
			catch (ArrayIndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
			}
			return topValue;
		}
	
		//this method is to shuffleup the elements 
		//as this class idea is to implement a shuffle queue in which the elements will
			//...be moved up after a dequeue methods is called
		private Object[] shuffleUp() {
			for (int up = 1; up < Queues.length; up++) {
				Queues[up-1] = Queues[up];
			}
			Queues[Queues.length-1] = null;
			return Queues;
		}
	}
	
	//... the other child class
	class CircularQueue extends DSAQueue {
		
		public CircularQueue() {
			super();
		}
		
		public CircularQueue(int numElements) {
			super(numElements);
		}
		
		//setter methods 
		public void enqueue(Object value) {
			try {
				int max = Queues.length;
				if (((getRear()+1)%max) == getFront()) {
					throw new ArrayIndexOutOfBoundsException("Operation aborted: The circular queue is full");
				}
				else if (isEmpty()) {
					front = 0;
					rear = 0;
				}
				else {
					this.rear = (rear + 1)% max;
				}
				Queues[getRear()] = value;
				count+= 1;
			}
			catch (ArrayIndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
			}
		}
	
		public Object dequeue() {
			Object topValue = peek();
			try {
				int max = Queues.length;
				if (isEmpty()) {
					throw new ArrayIndexOutOfBoundsException("Operation aborted: The circular queue is empty");
				}
				else {
					Queues[getFront()] = null;
				}
				if (getFront() == getRear()) {
					front = -1;
					rear = -1;
				}
				else {
					this.front = (front + 1)%max;
					topValue = peek();
				}
				count-=1;
			}
			catch (ArrayIndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
			}
			return topValue;
		}
	}

