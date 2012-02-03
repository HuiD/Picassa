package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.expression.*;

/**
 * Create specific expression classes. Expression are divided into three types:
 * constant, variable and command. Command Expression is consist of several
 * subclasses which implement specific functions.
 * 
 * @author Hui Dong
 */

public class ExpressionFactory {
	private static final int INFINITE = -1;
	private Map<String, CommandExpression> CommandExpressionMap = new HashMap<String, CommandExpression>();
	private Pattern[] patterns = { Pattern.compile("[a-z]+"),
			Pattern.compile("(\\-?[0-9]+(\\.[0-9]+)?)|(\\.[0-9]+)"),
			Pattern.compile("(\\(([a-zA-Z]+))|(\\(([\\+\\*\\-%/!^]))") };
	private int myCurrentPosition;
	private String myInput;
	// command name
	private static final String COLOR = "color";
	private static final String MINUS = "minus";
	private static final String minus = "-";
	private static final String DIVIDE = "div";
	private static final String divide = "/";
	private static final String MULTI = "mul";
	private static final String multi = "*";
	private static final String EXPONENT = "exp";
	private static final String exponent = "^";
	private static final String MOD = "mod";
	private static final String mod = "%";
	private static final String NEGATE = "neg";
	private static final String negate = "!";
	private static final String PLUS = "plus";
	private static final String plus = "+";
	private static final String LET = "let";
	private static final String RANDOM = "random";
	private static final String FLOOR = "floor";
	private static final String CEIL = "ceil";
	private static final String ABS = "abs";
	private static final String CLAMP = "clamp";
	private static final String WRAP = "wrap";
	private static final String SIN = "sin";
	private static final String COS = "cos";
	private static final String TAN = "tan";
	private static final String ATAN = "atan";
	private static final String LOG = "log";
	private static final String RGB2YCRCB = "rgbToYCrCb";
	private static final String YCRCB2RGB = "yCrCbtoRGB";
	private static final String PERLINCOLOR = "perlinColor";
	private static final String PERLINBW = "perlinBW";
	private static final String SUM = "sum";
	private static final String MAX = "max";
	private static final String AVERAGE = "average";
	private static final String MIN = "min";
	private static final String PRODUCT = "product";
	private static final String IF = "if";

	private static enum EXPRESSION_TYPE {
		XY_EXPRESSION, CONSTANT_EXPRESSION, COMMAND_EXPRESSION;
		public static EXPRESSION_TYPE getType(int i) {
			if (i == 0)
				return XY_EXPRESSION;
			if (i == 1)
				return CONSTANT_EXPRESSION;
			if (i == 2)
				return COMMAND_EXPRESSION;
			return null;
		}
	}

	/**
	 * Build the command expression map.
	 */
	public ExpressionFactory() {
		myCurrentPosition = 0;
		buildMap();
	}

	public int getMyCurrentPosition() {
		return myCurrentPosition;
	}

	/**
	 * 
	 * @param myCurrentPosition
	 *            is the position of the string myInput.
	 * @param myInput
	 *            is what the user input in the GUI.
	 * @return the expression type.
	 */
	private EXPRESSION_TYPE getExpressionType() {
		for (int i = 0; i < patterns.length; i+=1) {
			Matcher matcher = patterns[i].matcher(myInput
					.substring(myCurrentPosition));
			if (matcher.lookingAt()) {
				return EXPRESSION_TYPE.getType(i);
			}
		}
		throw new ParserException("Unable to parse expression");
	}

	/**
	 * Will generate specific expressions.
	 * 
	 * @param myCurrentPosition
	 *            is the position of the string myInput.
	 * @param myInput
	 *            is what the user input in the GUI.
	 * @return the specific expression.
	 */
	public Expression parseExpression(String input) {
		myInput = input;
		switch (getExpressionType()) {
		case XY_EXPRESSION:
			String VarMatch = getMatch(patterns[0]);
			VariableExpression varExpression = new VariableExpression();
			varExpression.setMyVariable(VarMatch);
			return varExpression;

		case CONSTANT_EXPRESSION:
			String numberMatch = getMatch(patterns[1]);
			double value = Double.parseDouble(numberMatch);
			return new ConstantExpression(value);

		case COMMAND_EXPRESSION:
			String commandName = getMatch(patterns[2]);
			commandName = commandName.substring(1);
			List<Expression> list = new ArrayList<Expression>();
			list = generateExpressionList(commandName);
			CommandExpression cmdExpression = CommandExpressionMap
					.get(commandName);
			if (cmdExpression != null) {
				cmdExpression.setExpList(list);
				buildMap();
				return cmdExpression;
			} 
		default:
			throw new ParserException("Unknown command");
		}
	}

	private String getMatch(Pattern pattern) {
		Matcher variableMatcher = pattern.matcher(myInput);
		variableMatcher.find(myCurrentPosition);
		String varMatch = variableMatcher.group();
		myCurrentPosition = variableMatcher.end();
		return varMatch;
	}

	/**
	 * 
	 * @param myCurrentPosition
	 *            is the position of the string myInput.
	 * @param myInput
	 *            is what the user input in the GUI.
	 * @param commandName
	 *            is a string that contains the name of the command.
	 * @return a list of expressions which are parameters in the user input.
	 */
	public List<Expression> generateExpressionList(String commandName) {
		if (!CommandExpressionMap.keySet().contains(commandName)) {
			throw new ParserException("Unknown command " + commandName);
		}
		List<Expression> list = new ArrayList<Expression>();
		int count = 1;
		while (true) {
			int num = CommandExpressionMap.get(commandName).getOperandNumber();
			if (num == 0) {
				myCurrentPosition += 1;
				return null;
			}
			skipWhiteSpace();
			list.add(parseExpression(myInput));
			skipWhiteSpace();
			if (currentCharacter() == ')') {
				myCurrentPosition += 1;
				if (num != INFINITE)
					if (count != num)
						throw new ParserException("Number of operands for "
								+ commandName + " command should be:" + num);
				return list;
			}
			count++;
		}
	}

	
	/**
	 * Build the map between the command name and its command expression
	 */
	public void buildMap() {
		CommandExpressionMap.put(PLUS, new PlusExpression());
		CommandExpressionMap.put(MOD, new ModExpression());
		CommandExpressionMap.put(EXPONENT, new ExpExpression());
		CommandExpressionMap.put(MINUS, new MinusExpression());
		CommandExpressionMap.put(MULTI, new MulExpression());
		CommandExpressionMap.put(COLOR, new ColorExpression());
		CommandExpressionMap.put(DIVIDE, new DivExpression());
		CommandExpressionMap.put(NEGATE, new NegateExpression());
		CommandExpressionMap.put(LET, new LetExpression());
		CommandExpressionMap.put(RANDOM, new RandomExpression());
		CommandExpressionMap.put(FLOOR, new FloorExpression());
		CommandExpressionMap.put(CEIL, new CeilExpression());
		CommandExpressionMap.put(ABS, new AbsExpression());
		CommandExpressionMap.put(ATAN, new AtanExpression());
		CommandExpressionMap.put(CLAMP, new ClampExpression());
		CommandExpressionMap.put(COS, new CosExpression());
		CommandExpressionMap.put(LOG, new LogExpression());
		CommandExpressionMap.put(PERLINBW, new PerlinBWExpression());
		CommandExpressionMap.put(PERLINCOLOR, new PerlinColorExpression());
		CommandExpressionMap.put(SIN, new SinExpression());
		CommandExpressionMap.put(TAN, new TanExpression());
		CommandExpressionMap.put(WRAP, new WrapExpression());
		CommandExpressionMap.put(RGB2YCRCB, new RGB2YCrCbExpression());
		CommandExpressionMap.put(YCRCB2RGB, new YCrCb2RGBExpression());
		CommandExpressionMap.put(plus, new PlusExpression());
		CommandExpressionMap.put(minus, new MinusExpression());
		CommandExpressionMap.put(multi, new MulExpression());
		CommandExpressionMap.put(mod, new ModExpression());
		CommandExpressionMap.put(negate, new NegateExpression());
		CommandExpressionMap.put(divide, new DivExpression());
		CommandExpressionMap.put(exponent, new ExpExpression());
		CommandExpressionMap.put(SUM, new SumExpression());
		CommandExpressionMap.put(MIN, new MinExpression());
		CommandExpressionMap.put(MAX, new MaxExpression());
		CommandExpressionMap.put(AVERAGE, new AverageExpression());
		CommandExpressionMap.put(PRODUCT, new ProductExpression());
		CommandExpressionMap.put(IF, new ConditionExpression());
	}

	private void skipWhiteSpace() {
		while (notAtEndOfString() && Character.isWhitespace(currentCharacter())) {
			myCurrentPosition += 1;
		}
	}

	private char currentCharacter() {
		if(myCurrentPosition >= myInput.length())
			throw new ParserException("Expected )");
		return myInput.charAt(myCurrentPosition);
	}

	private boolean notAtEndOfString() {
		return myCurrentPosition < myInput.length();
	}
}
