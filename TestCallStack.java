import java.util.*;

/**
** Author: Cassandra Jacklya
** Purpose: to test the CallStack class and ensures it works correctly
** Last modified on: 23rd August 2020
**/
public class TestCallStack {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int elements;
		
		//testing the nested calls -- non-recursive
		System.out.println("Nested calls: ");
		CallStack.hello();
		
		//testing the recursive calls
		System.out.println("Recursive calls: ");
		
		CallStack.wrapRec(4);
	}
}