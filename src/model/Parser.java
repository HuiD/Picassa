package model;


import model.expression.Expression;


/**
 * Parses a string into an expression tree based on rules for arithmetic.
 * 
 * Due to the nature of the language being parsed, a recursive descent parser is
 * used http://en.wikipedia.org/wiki/Recursive_descent_parser
 * 
 * @author former student solution
 * @author Robert C. Duvall (added comments, exceptions, some functions)
 */
public class Parser {
	// double is made up of an optional negative sign, followed by a sequence
	// of one or more digits, optionally followed by a period, then possibly
	// another sequence of digits
	
	// expression begins with a left paren followed by the command name,
	// which is a sequence of alphabetic characters
	
	private ExpressionFactory factory;
	public Parser(){	
		factory = new ExpressionFactory();
	}
	

	
	// state of the parser
	private int myCurrentPosition;
	private String myInput;
	/**
	 * Converts given string into expression tree.
	 * 
	 * @param input
	 *            expression given in the language to be parsed
	 * @return expression tree representing the given formula
	 */
	public Expression makeExpression(String input) {
		myInput = input;
		Expression result = parseExpression();
		myCurrentPosition = factory.getMyCurrentPosition();
		if (myCurrentPosition < myInput.trim().length()) {
			throw new ParserException(
					"Unexpected characters at end of the string: "
							+ myInput.substring(myCurrentPosition),
					ParserException.Type.EXTRA_CHARACTERS);
		}
		return result;
	}
    
	/**
	 * Parse the input
	 * @return an expression.
	 */
	private Expression parseExpression() {
		return factory.parseExpression(myInput);
	}


}
