package main;
import java.util.Arrays;
import java.util.Stack;

public class MyLIFO_App {
	
	// This method reserves the given array
	public static <E> void reserve(E[] array) {
		Stack<E> stack = new Stack<>();
		for (int i = 0; i < array.length; i++) {
			stack.push(array[i]);
		}
		int i = 0;
		while(!stack.isEmpty()) {
			array[i] = stack.pop();
			i++;
		}
		System.out.println(Arrays.toString(array));
	}
	
	public static boolean isCorrect(String input) {
		Stack<Character> stack = new Stack<>();
		for (int i = input.length() - 1; i >= 0; i--) {
			Character crt = input.charAt(i);
			if (!stack.empty() && (crt.equals('(') || crt.equals('{') || crt.equals('['))) {
				if (isCorrectHelp(crt, stack.peek())) {
					stack.pop();
				} else {
					return false;
				}
		} else {
				stack.push(crt);
			}
		}
		return stack.empty();
	}

	public static boolean isCorrectHelp(Character crt1, Character crt2) {
		if (crt1.equals('(') && crt2.equals(')'))
			return true;
		if (crt1.equals('{') && crt2.equals('}'))
			return true;
		if (crt1.equals('[') && crt2.equals(']'))
			return true;
		return false;
	}
	
	
	
	
	// This method evaluates the value of an
	// expression
	// i.e. 51 + (54 *(3+2)) = 321

	public static int evaluateExpression2(String expression) {
		expression = expression.replaceAll("\\s+", "");
		Stack<Character> opearatorStack = new Stack<>();
		Stack<Integer> opearandStack = new Stack<>();
		String num = "";
		for (int i = 0; i < expression.length(); i++) {
			if(expression.charAt(i) == ' ') continue;
			Character curChar = expression.charAt(i);
			if(i == 0) {
				if(curChar == '-') {
					opearandStack.push(0);
					opearatorStack.push('-');
				}
			}
			else {
				if(isMinus(expression.charAt(i-1), curChar)) {
					opearandStack.push(0);
					opearatorStack.push('-');
					continue;
				}
			}
			if (!isOperator(curChar)) {
				num += curChar;
			} else {
				if (!num.equals("")) {
					opearandStack.push(Integer.parseInt(num));
					num = "";
				}
				switch (curChar) {
				case '+', '-':
					while(!opearatorStack.isEmpty() && opearatorStack.peek() != '(' && opearandStack.size() >= 2) {
						int math = doMath(opearandStack.pop(), opearatorStack.pop(), opearandStack.pop());
						opearandStack.push(math);
					}
					opearatorStack.push(curChar);
					break;
				case '*', '/':
					if(!opearatorStack.isEmpty() && (opearandStack.peek() == '*' || opearatorStack.peek() == '/' )) {
						int math = doMath(opearandStack.pop(), opearatorStack.pop(), opearandStack.pop());
						opearandStack.push(math);
					}
				    opearatorStack.push(curChar);
					break;
				case ')':
					while(opearatorStack.peek() != '(') {
						int math = doMath(opearandStack.pop(), opearatorStack.pop(), opearandStack.pop());
						opearandStack.push(math);
					}
					opearatorStack.pop();
					break;
				default:
					opearatorStack.push(curChar);
				}
			}
		}
		if(!num.equals("")) {
			opearandStack.push(Integer.parseInt(num)); 
		}
		while (!opearatorStack.isEmpty() && opearandStack.size()>=2) {
			opearandStack.push(doMath(opearandStack.pop(), opearatorStack.pop(), opearandStack.pop()));
		}
		return opearandStack.pop();
	}

	public static boolean isOperator(Character input) {
		return input == '+' || input == '-' || input == '*' || input == '/' || input == '(' || input == ')';
    }
	
	public static int doMath(int num1, Character curChar, int num2) {
		int result = 0;
		switch (curChar) {
		case '+':
			result = num1 + num2;
			break;
		case '-':
			result = num2 - num1;
			break;
		case '*':
			result = num1 * num2;
			break;
		case '/':
			result = num2 / num1;
			break;
		}
		return result;
	}
	
	public static boolean isMinus(Character c1, Character c2) {
		return c1 == '(' && c2 == '-';
	}

	public static void main(String[] args) {
		
		reserve(new Integer[] {1, 2, 3, 4});
		System.out.println("==========");
		
		System.out.println(isCorrectHelp('[', ']'));
		System.out.println(isCorrect("()(())[]{(())}"));
		System.out.println(isCorrect("([)]{[]}()"));
		System.out.println(isCorrect("((((()"));
		Stack<Character> stack = new Stack<>();
		stack.empty();
		
		System.out.println("==========");
		
		System.out.println(evaluateExpression2(" 51 +(54*(3+2)) "));
		System.out.println(evaluateExpression2("2+(9-4+3)+(9*4)"));
		System.out.println(evaluateExpression2("2+(9*4)+3-1-(2-3)"));
		System.out.println(evaluateExpression2("(9*2)+(4+3)+(3+1)"));
		System.out.println(evaluateExpression2("2-3*2+3"));
		System.out.println(evaluateExpression2("2-(2+3*2*2*2)"));
		System.out.println(evaluateExpression2("0"));
		System.out.println(evaluateExpression2("1-(    -2)"));
		System.out.println(evaluateExpression2("- (3 - (- (4 + 5) ) )"));
	}

}
