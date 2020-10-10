import java.util.*;
/**
** Author: Cassandra Jacklya
** Purpose: to implement a stack similar to the built in stack class in Java
** Last modified: 23rd August 2020
**/


public class DSAStack {
	public final int defCapacity = 100;
	Object[] StackEg;
	int count;
	
//constructor (default)
	public DSAStack() {
		StackEg = new Object[defCapacity];	//stack capacity will be 0-99 by default (100 elements)
		count = 0;
	}
	
//constructor (alternate)
	public DSAStack(int numElements) {
		StackEg = new Object[numElements];	
		count = 0;
	}
	
//getter method
	public int getCount() {
		return this.count;
	}
	
//setter methods
	public void push(Object value) {
		try {
			if (isFull()) {
				throw new ArrayIndexOutOfBoundsException("Operation aborted: The stack is full");
			}
			else {
				StackEg[getCount()] = value;
				this.count = count + 1;
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Object pop() {
		Object top = topVal();
		try {
			if (isEmpty()) {
				throw new ArrayIndexOutOfBoundsException("Operation aborted: The stack is empty");
			}
			else {
				top = topVal();
				StackEg[getCount()-1] = null;
				this.count = count - 1;
			}
		}
		catch (ArrayIndexOutOfBoundsException e2) {
			System.out.println(e2.getMessage());
		}
		return top;
	}
	
	public Object topVal() {
		Object peek = "";
		try {
			if (isEmpty()) {
				throw new ArrayIndexOutOfBoundsException("No elements found. Stack is empty");
			}
			else {
				peek = StackEg[getCount()-1];
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
		return peek;
	}
	
	//this method prints out the stack to the main
	public String toString() {
		String word = "";
		for (int ii = (StackEg.length -1); ii >= 0; ii--) {
			word = word + "[" + ii + "] " + StackEg[ii] + "\n";
		}
		return word;
	}
	
	//the last two methods are validation methods 
	//checks whether the stack is empty
	public boolean isEmpty() {
		boolean empty = false;
		if (getCount() == 0) {
			empty = true;
		}
		return empty;
	}
	
	//check whether the stack is full
	public boolean isFull() {
		boolean full = false;
		if (getCount() == StackEg.length) {
			full = true;
		}
		return full;
	}
	
	
		
}


	
	
	
	