package Project1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
import javax.swing.JTextField;


public class ExpressionConversion {
	
	public static String preToPost(String inputString) throws SyntaxError {
		//validate input uses proper alphabet
		
		boolean isValid = validateInput(inputString);
		if (!isValid) {
			System.out.println("not performing function bc input invalid");
			//TODO: make return statement / error throwing better
			throw new SyntaxError();
		}
		
		//TODO: add validation of proper prefix expression syntax, error stuff still in stack or error
		
		Stack<String> reversalStack = new Stack<String>();
		Stack<String> operandStack = new Stack<String>();
		
		
		String[] inputArray = inputString.split("(?<=[-*+/ ])|(?=[-*+/ ])");
		
		//push all units to reversal stack
		for (int i = 0; i<inputArray.length; i++) {
			//System.out.println("in for loop");
			System.out.println("Token " + (i+1) + ": " + inputArray[i].toString());
			if (!(inputArray[i].equals(" "))) {
				reversalStack.push(inputArray[i].toString());
			}
			else {
				System.out.println("Token " + inputArray[i] + "is a space and will not be pushed to stack");
			}
		}
		
		while (! reversalStack.isEmpty()) {
			String poppedUnit = (String) reversalStack.pop();
			System.out.println("popped off: " + poppedUnit);
			
			if (poppedUnit.matches("^[0-9]*$")) { //popped unit is an operand
				operandStack.push(poppedUnit);
			}
			
			else { //popped unit is an operator
				//pop two units off operand stack
				
				String op1 = (String) operandStack.pop();
				String op2 = (String) operandStack.pop();
				
				String unitToPush = op1 + " " + op2 + " " + poppedUnit;
				operandStack.push(unitToPush);
			}
		}
		
		System.out.println("postfix expression built: " + operandStack.peek());
		String postString = operandStack.pop();
		
		//throwing custom exception here
		if (!operandStack.isEmpty()) {
			throw new SyntaxError();
		}
		
		return postString;
	}
	

	public static String postToPre(String inputString) throws SyntaxError {
		System.out.println("execute method: post to pre");
		
		boolean isValid = validateInput(inputString);
		if (!isValid) {
			System.out.println("not performing function bc input invalid");
			throw new SyntaxError();
		}	
		
		Stack<String> operandStack = new Stack<String>();
		
		String[] inputArray = inputString.split("(?<=[-*+/ ])|(?=[-*+/ ])");
		
		
		for (int i = 0; i<inputArray.length; i++) {
			System.out.println("Token " + (i+1) + ": " + inputArray[i].toString());
			
			if (!(inputArray[i].equals(" "))) {
				
				if (inputArray[i].matches("^[0-9]*$"))
					operandStack.push(inputArray[i].toString());
				
				else { //unit is an operator
					//pop 2 operands off stack
	
					String op1 = operandStack.pop();
					String op2 = operandStack.pop();
					
					//push string with operator, operand1, operand2
					String stringToPush = inputArray[i] + " " + op1 + " " + op2;
					
					operandStack.push(stringToPush);
				}
			}
			
			else { //token is a space. ignore.
				System.out.println("Token " + inputArray[i] + "is a space and will not be pushed to stack");
			}
		}
		
		String processedExpression = operandStack.pop();
		
		//stack should now be empty if input is valid
		if (!operandStack.isEmpty()) {
			throw new SyntaxError();
		}
		
		return processedExpression;
	}
	
	public static boolean validateInput(String inputField) {
		System.out.println("In validateInput. inputField: " + inputField);
		
	
		if (inputField.isBlank() || inputField.isEmpty() || !inputField.matches("^[0-9+/ *-]*$")) {
			System.out.println("input invalid. must only use numbers, spaces, and the four math operations: *+-/");
			return false;
		}
		else {
			System.out.println("input is valid");
			return true;
		}
		
	}
}
