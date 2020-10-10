import java.util.*;

/**
** Author: Cassandra Jacklya
** Purpose:  to test the DSAStack class and ensure that it works correctly
** Last modified on: 23rd August 2020
**/

public class TestStacks {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		char choice = 'n';
		int size;
		DSAStack[] s = new DSAStack[1];
		try {
			System.out.print("Do you want to enter an array size? y/n ");
			choice = sc.next().charAt(0);
			if ((choice == 'y') || (choice == 'Y')) {
				System.out.print("\n" + "Enter size of array: ");
				size = sc.nextInt();
				s[0] = new DSAStack(size);
			}
			else if ((choice == 'n') || (choice == 'N')) {
				s[0] = new DSAStack();
			}
			else {
				throw new IllegalArgumentException("Invalid option");
			}
		
			int ops;
			Object value;
			boolean stop = false;
			String word = "";
		
			do {	
				System.out.println("\n" + "Choose an operation");
				System.out.println("1. Push");
				System.out.println("2. Pop");
				System.out.println("3. Peek at top value");
				System.out.println("4. Show stack");
				ops = sc.nextInt();
			
				switch(ops) {
					case 1:
						System.out.print("\n" + "Enter a value: ");
						sc.nextLine();
						value = sc.nextLine();
						s[0].push((Object)value);
						break;
					case 2:
						s[0].pop();
						break;
					case 3:
						System.out.println("The top value is: " + s[0].topVal());
						break;
					case 4:
						word = s[0].toString();
						stop = true;
						break;
					default:
						throw new IllegalArgumentException("Invalid option");
				}
			} while (stop == false);
		
			System.out.println("\n" + word);
			System.out.println("Count: " + s[0].getCount());
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		catch (InputMismatchException e2) {
			System.out.println("Value must be a number");
		}
	}
}
		
		
					
			
			