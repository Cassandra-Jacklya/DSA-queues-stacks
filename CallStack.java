/**
** Author: Cassandra Jacklya
** Purpose: creates a call stack class to imitate the built in stack class in Java
** Last modified on: 23rd August 2020
**/

public class CallStack {
	
	//creates a fixed number of instances of DSAStack just for testing purposes
	static DSAStack st = new DSAStack(4);
	
	/**
	** Submodule name: hello
	** Purpose: method for nested call
	** Input: none
	** Export: none
	**/
	public static void hello() {
		st.push("hello");
		display();
		world();
		st.pop();
		display();
	}
	
	/**
	** Submodule name: world
	** Purpose: method for nested call
	** Input: none
	** Export: none
	**/
	public static void world() {
		st.push("world");
		display();
		bye();
		st.pop();
		display();
	}
	
	/**
	** Submodule name: bye
	** Purpose: method for nested call
	** Input: none
	** Export: none
	**/
	public static void bye() {
		st.push("bye");
		display();
		end();
		st.pop();
		display();
	}
	
	/**
	** Submodule name: end
	** Purpose: method for nested call
	** Input: none
	** Export: none
	**/
	public static void end() {
		st.push("end");
		display();
		st.pop();
		display();
	}
	
	/** 
	** Submodule name: display
	** Purpose: to display the method name and arguments (if there are) using a stack
	** Input: none
	** Export: none
	**/
	private static void display() {
		String word = st.toString();
		System.out.println(word);
	}
	
	/**
	** Submodule name: wrapRec
	** Purpose: to wrap around the recursive method for safe calling (catch any errors)
	** Input: calls (integer)
	** Export: Rec(calls) (integer) --> calls the recursive method
	**/
	public static int wrapRec(int calls) {
		if (calls < 0) { 
			throw new IllegalArgumentException("Call value should not be negative");
		}
		return Rec(calls);
	}
	
	/**
	** Submodule name: Rec
	** Purpose: to recursively call itself(method) for a fixed number of times and displays the
		stack to the terminal each call
	** Import: n (Integer)
	** Export: n (Integer)
	**/
	private static int Rec(int n) {
		st.push("Recursive call: " + n);
		display();
		if (n != 1) {
			n = n * Rec(n-1);
		}
		st.pop();
		display();
		
		return n;
	}
}
		
		
		
	
	
	
	
	
		