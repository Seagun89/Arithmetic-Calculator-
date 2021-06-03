/*RESOURCE:
 * http://db.cs.duke.edu/courses/cps100e/spring06/code/Infix.java
 * https://stackoverflow.com/questions/40519580/trying-to-determine-if-a-string-is-an-integer
 * https://www.programmersought.com/article/63414535311/
 * https://www.programiz.com/java-programming/examples/factors-number
 * https://stackoverflow.com/questions/8647059/finding-factors-of-a-given-integer/45789083
 * https://docs.oracle.com/javase/tutorial/java/nutsandbolts/op2.html
 * https://wxx5433.gitbooks.io/interview-preparation/content/part_ii_leetcode_lintcode/stack/expression_evaluation.html
 * https://www.javatpoint.com/java-int-to-string
 * https://en.wikipedia.org/wiki/Unary_operation
 * https://docs.oracle.com/javase/tutorial/java/nutsandbolts/op1.html
 * https://stackoverflow.com/questions/20949556/java-calculator-how-to-handle-negative-numbers
 * https://www.py4j.org/getting_started.html
 * https://liveexample.pearsoncmg.com/html/EvaluateExpression.html
 * https://www.whitman.edu/mathematics/java_tutorial/java/nutsandbolts/operators.html
 * https://chortle.ccsu.edu/java5/Notes/chap09B/ch09B_4.html
 * https://codereview.stackexchange.com/questions/177532/exception-handling-calculate-power
 * https://www.cs.miami.edu/home/schulz/CSC519.pdf
 * https://www.programiz.com/java-programming/examples/calculator-switch-case
 */
package calc;

import java.util.Stack;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

//MyCalcModel class takes a string and converts it to an expression takes the infix expression to a postfix expression 
public class MyCalcModel {
	
/*Combining both methods with their stacks to give calculation as a string*/
	 public String stringToArithmetic(String string) {
	    	double temp = suffixToArithmetic(infixToSuffix(string));
	    	long x = (long)temp; 
	    	String str = Long.toString(x);
	        return str;
	    }//Close stringToArithmetic Method
	 
/*Calculate operations only, each operators will react to other operations but this
 method takes an unique operators such as unary operation, brackets and perform together and calculate*/
	 public static String infixToSuffix(String infix) {
	        Stack< Character> stack = new Stack< Character>();
	        String suffix = "";
	        int length = infix.length();
	        for (int i = 0; i < length; i++) {

	            Character temp ;
	            char c = infix.charAt(i);
	            switch (c) {
	                
	            case '(':
	                stack.push(c);
	                break;
	                
	            case '+':
	            	 if (infix.charAt(i)=='+'&&( i==0||infix.charAt(i-1)=='(')){
	            	    suffix += "0" + "";
	                }//Close if
	                
	            case '-':
	                if (infix.charAt(i)=='-'&&( i==0||infix.charAt(i-1)=='(')){
	            	    suffix += "0" + "";
	                }//Close if
	                while (stack.size() != 0) {
	                    temp = stack.pop();
	                    if (temp == '(') {
	                        stack.push('(');
	                        break;
	                    }//close if
	                    suffix += " " + temp;
	                }//Close while
	                stack.push(c);
	                suffix += " ";
	                break;
	                
	            case '*':
	                while (stack.size() != 0) {
	                    temp = stack.pop();
	                    if (temp == '(' || temp == '+' || temp == '-') {
	                        stack.push(temp);
	                        break;
	                    } else {
	                        suffix += " " + temp;
	                    }//close else
	                }//close while 
	                stack.push(c);
	                suffix += " ";
	                break;
	                
	            case '^':
	                while (stack.size() != 0) {
	                    temp = stack.pop();
	                    if (temp == '*' ||temp == '(' || temp == '+' || temp == '-') {
	                        stack.push(temp);
	                        break;
	                    } else {
	                        suffix += " " + temp;
	                    }//close else
	                }//close while 
	                stack.push(c);
	                suffix += " ";
	                break;
	                
	            case ')':
	                while (stack.size() != 0) {
	                    temp = stack.pop();
	                    if (temp == '(') {
	                        break;
	                    } else {
	                        suffix += " " + temp;
	                    }//close else
	                }//close while 
	                break;
	                
	            default:
	                suffix += c;
	            }//Close swtich
	        }//close for
	        while (stack.size() != 0) {
	            suffix += " " + stack.pop();
	        }//Close while
	        return suffix;
	    }//Close infixToSuffix Method

/*Split each words to understand one by one string and use it as Stack method */	    
	    public static double suffixToArithmetic(String postfix) {
	        Pattern pattern = Pattern.compile("\\d+"); 
	        String strings[] = postfix.split(" ");
	        for (int i = 0; i < strings.length; i++) {
	            strings[i].trim(); 
	        }//Close for
	        Stack< Double> stack = new Stack< Double>();
	        for (int i = 0; i < strings.length; i++) {
	            if (strings[i].equals("")) {
	                continue;
	            }//Close if
	            if ((pattern.matcher(strings[i])).matches()) {
	                stack.push(Double.parseDouble(strings[i]));
	            } else {
	                double y = stack.pop();
	                double x = stack.pop();
	                stack.push(calculator(x, y, strings[i]));
	            }//Close else
	        }//close for
	        return stack.pop();
	    }//Close suffixToArithmetic Method
	    
/*Calculates each operator*/	    
	    private static double calculator(double x, double y, String symbol) {
	        if (symbol.trim().equals("+")) {
	            return x + y;
	        }if (symbol.trim().equals("-")) {
	            return x - y;
	        }if (symbol.trim().equals("*")) {
	            return x * y;
	        } if (symbol.trim().equals("^")) {
	            if (y>=0) {    		
	                return Math.pow(x,y);
	            } else {
	        	   JOptionPane.showMessageDialog(null,"The power should be non-negative. Try again!");
	        	   System.out.println("The power should be non-negative. Try again!");
	        	}//Close else
	            return 0;
	        }//Close else
	        return 0;
	    }//Close calculator Method
}//Close MyCalcModel Class