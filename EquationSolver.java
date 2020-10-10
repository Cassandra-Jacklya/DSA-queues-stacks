import java.util.*;

/**
** Author: Cassandra Jacklya
** Purpose: converts infix expressions to postfix (RPN method) then evaluates it according to the precedence rules
** Last modified on: 24th August 2020
**/

public class EquationSolver {
	//main method to test the program
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		try {
			String exp = "";
			System.out.println("Enter a mathematical expression");
			System.out.println("Give a space after each operand and operator and even brackets");
			exp = sc.nextLine();
			
			//checks whether the expression follows the required format
			if (!(exp.contains(" "))) {
				throw new IllegalArgumentException("Invalid expression");
			}
			
			//if it follows, the compiler will attempt to evaluate the expression
			System.out.println("Answer: " + solve(exp));
		}
		
		//provides multiple catch statements that are most likely to occur during the run of program
		catch (StringIndexOutOfBoundsException e) {	//if there are no spaces between one or more operators and operands
			System.out.println("Invalid expression");
		}
		catch (ClassCastException e2) {			//if expression is wrong, this can occur from attempting to cast an Object class to a Character
			System.out.println("Expression cannot be evaluated");		//...when in fact, it is a number
		}
		catch (IllegalArgumentException e3) {		//if there are totally no spaces (format is violated)
			System.out.println(e3.getMessage());
		}
	}
	
	/**
	** Submodule name: solve
	** Purpose: Calls methods to attempt to solve the expression
	** Import: equation (String)
	** Export: answer (Double)
	**/
	public static double solve(String equation) {
		//converts the infix expression to postfix then evaluates it
		return evaluatePostfix(parseInfixToPostfix(equation));
	}
	
	/**
	** Submodule name: parseInfixToPostfix
	** Purpose: converts an infix expression to a postfix expression (RPN)
	** Import: equation (String)
	** Export: qu (DSAQueue)
	**/
	private static DSAQueue parseInfixToPostfix(String equation) {
		DSAQueue qu = new ShufflingQueue(equation.length());	//uses the length of the expression as the number of instances of DSAQueue
		int max = equation.length();								//...to provide sufficient space for the expression
		DSAStack st = new DSAStack(equation.length());
		
		String postfix = "";
		char term;
		int operand;
		int count = 0;
		
		//loop only breaks once all the infix operators and operands are iterated through
		while  (max >= 1){
			//count variable is used for removing the already used infix 
			count = 0;
			term = parse(equation);
			
			//when an opening bracket is encountered, it is pushed onto the stack
			if (term == '(') {
				st.push(term);
			}
			
			//when a closing bracket is detected, all the operators are popped off the stack into the queue/postfix expression
			else if (term == ')') {					//...as long as the top of the stack is not an opening bracket which determines the loop break
				while (((Character)st.topVal()).charValue() != '(') {
					postfix+= st.topVal();
					
					//queued into the queue for evaluation later on
					qu.enqueue((Object)st.pop());
				}
				
				//pops of the opening bracket
				st.pop();
			}
			
			//if an operator is detected, the compiler checks whether the top of stack has higher/lower precedence, and does according to the required
			else if ((term == '+') || (term == '-') || (term == '*') || (term == '/')) {
				
				//if lower, the term is pushed in and nothing is popped off, else
					//...the operators will first be popped off then only the term will be pushed in
				while ((!(st.isEmpty())) && (((Character)st.topVal()).charValue() != '(') && 
				(precedenceOf(((Character)st.topVal()).charValue()) >= precedenceOf(term))) {
					postfix+= st.pop();
					qu.enqueue((Object)term);
				}
				st.push(term);
			}
			else {
				//if it is not an operator or bracket, then it must be an operand
				operand = (int)term - 48;
				postfix+= operand;
				qu.enqueue(operand);
			}
			
			//if it is the last operator, then the compiler exits the while loop 
			if (equation.length() == 1) {
				equation = equation.substring(0,1);
				max = 0;
			}
			else {
				//if not, then the string equation will remove the used infix operator/operand
				equation = equation.substring(count+2,equation.length());
				max = equation.length();
			}
		
		}
		
		//if stack is not empty yet, empty it out in order and place it in the queue/postfix expression
		while (st.isEmpty() == false) {
			if (((Character)st.topVal()).charValue() != '(') {
				postfix+= st.topVal();
				qu.enqueue((Object)st.pop());
			}
		}
		System.out.println("\n" + "PostFix: " + postfix);
		return qu;
	}
	
	//gets the next operator/operand from the expression
	private static char parse(String eqn) {
		String[] line = eqn.split(" ");
		char c = line[0].charAt(0);
		return c;
	}
	
	/**
	** Submodule name: evaluatePostfix
	** Purpose: evaluates the expression if it is valid
	** Import: postfixQueue(DSAQueue)
	** Export: ans (Double)
	**/
	private static double evaluatePostfix(DSAQueue postfixQueue) {
		double ans = 0;
		double op1, op2;
		op1 = 0;
		op2 = 0;
		int max = postfixQueue.getCount();
		DSAStack stack = new DSAStack(postfixQueue.getCount());
		for (int ii = 0; ii < max; ii++) {
			
			//shows the stack to the user
			System.out.println(stack.toString());
			
			//if the element in the queue is an operator
			if ((Object)(postfixQueue.peek()) instanceof Character) {
				if (stack.topVal() instanceof Number) {
					op1 = ((Number)stack.pop()).doubleValue();
				}
				if (stack.topVal() instanceof Number) {
					op2 = ((Number)stack.pop()).doubleValue();
				}
				ans = executeOperation((Character)postfixQueue.dequeue(),op1,op2);
				stack.push((Object)ans);
			}
			
			//else it must be a number/operand
			else if ((Object)(postfixQueue.peek()) instanceof Number) {
				stack.push((Object)postfixQueue.dequeue());
			}
		}
		ans = ((Number)stack.pop()).doubleValue();
		return ans;
	}
	
	/**
	** Submodule name: precedenceOf
	** Purpose: follows the BODMAS rules
	** Import: theOp (char)
	** Export: value (int)
	**/
	private static int precedenceOf(char theOp) {
		int value = -10;
		switch (theOp) {
			case '+': case '-':
				value = 1;
				break;
			case '*': case '/':
				value = 2;
				break;
			default:
				System.out.println("Invalid operator");
				break;
		}
		return value;
	}
	
	/** 
	** Submodule name: executeOperation
	** Purpose: executes the operation depending on the operator passed in the method
	** Import: op (char), op1 (double), op2 (double)
	** Export: answer (double)
	**/
	private static double executeOperation(char op, double op1, double op2) {
		double answer;
		int ascii = (int)op;
		switch (ascii) {
			case 42:
				answer = op2*op1;
				break;
			case 43:
				answer = op2+op1;
				break;
			case 45:
				answer = op2-op1;
				break;
			default:
				answer = op2/op1;
				break;
		}
		return answer;
	}
	
}