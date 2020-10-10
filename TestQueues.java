import java.util.*;

/**
** Author: Cassandra Jacklya
** Purpose: to test the DSAQueue class and ensure all methods works correctly
** Last modified: 23rd August 2020
**/

public class TestQueues {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//delcaring variables
		int size;
		boolean stop = false;
		char choice;
		Object value;
		String wordOne, wordTwo;
		wordOne = "";
		wordTwo = "";
		
		//creates two instances of DSAQueue for both ShufflingQueue and CircularQueue
		DSAQueue[] q = new DSAQueue[2];
		
		try {
			size = 'n';
			System.out.print("Do you want to enter size of array? y/n ");
			choice = sc.next().charAt(0);
			sc.nextLine();
			if ((choice == 'y') || (choice == 'Y')) {
				System.out.print("\n" + "Enter size of array: ");
				size = sc.nextInt();
				q[0] = new ShufflingQueue(size);
				q[1] = new CircularQueue(size);
			}
			else if ((choice == 'n') || (choice == 'N')) {
				q[0] = new ShufflingQueue();
				q[1] = new CircularQueue();
			}
			else {
				throw new IllegalArgumentException("Invalid option");
			}
			
			do {
				System.out.println("\n" + "Choose an operation");
				System.out.println("(E)nqueue");
				System.out.println("(D)equeue");
				System.out.println("(S)how queue");
				choice = sc.next().charAt(0);
			
				switch(choice) {
				
					case 'E': case 'e':
						System.out.print("\n" + "Enter any value: ");
						sc.nextLine();
						value = sc.nextLine();
						q[0].enqueue((Object)value);
						q[1].enqueue((Object)value);
						break;
					
					case 'D': case 'd':
						q[0].dequeue();
						q[1].dequeue();
						break;
					
					case 's': case 'S':
						wordOne = q[0].toString();
						wordTwo = q[1].toString();
						stop = true;
						break;
					default:
						throw new IllegalArgumentException("Invalid option");
				}
			} while (stop == false);

			System.out.println("\n" + "Shuffling Queue: ");
			System.out.println(wordOne);
			System.out.println("Rear: " + q[0].getRear());
			System.out.println("Front: " + q[0].getFront());
			System.out.println("Count: " + q[0].getCount());
		
			System.out.println("\n" + "Circular Queue: ");
			System.out.println(wordTwo);
			System.out.println("Rear: " + q[1].getRear());
			System.out.println("Front: " + q[1].getFront());
			System.out.println("Count: " + q[1].getCount());
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		catch (InputMismatchException e2) {
			System.out.println("Value must be an integer");
		}
	}
}