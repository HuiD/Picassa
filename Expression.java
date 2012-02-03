package model;

import model.ParserException.Type;
import model.util.ColorCombinations;

/**
 * An Expression represents a mathematical expression as a tree.
 * 
 * In this format, the internal nodes represent mathematical 
 * functions and the leaves represent constant values.
 *
 * @author former student solution
 * @author Robert C. Duvall (added comments, some code)
 */
public class Expression
{
    private RGBColor myValue;
    private String myCommand;
    private Expression myOperand1;
    private Expression myOperand2;
    private Expression myOperand3;
    private String XYExpression;


    /**
     * Create expression representing the given constant value
     */
    public Expression (RGBColor value)
    {
        myValue = value;
        myCommand = null;
        myOperand1 = null;
        myOperand2 = null;
    }
    
    public Expression (String expression){
    	XYExpression = expression;
    }
    
    /**
     * Create expression representing the given operation between the
     * two given sub-expressions.
     */
    public Expression (String command, Expression operand1, Expression operand2, Expression operand3)
    {
        myCommand = command;
        myOperand1 = operand1;
        myOperand2 = operand2;
        myOperand3 = operand3;
        myValue = null;
    }


    /**
     * @return value of expression
     */
    public RGBColor evaluate (double x, double y)
    {
        if (myCommand == null)
        {
        	if(XYExpression.equals("x"))
        		return new RGBColor(x,x,x);
        	if(XYExpression.equals("y"))
        		return new RGBColor(y,y,y);
        	
            return myValue;
        }
        else
        {
            if (myCommand.equals("plus"))
                return ColorCombinations.add(myOperand1.evaluate(x, y), myOperand2.evaluate(x, y));
            else if (myCommand.equals("minus")) 
                return ColorCombinations.subtract(myOperand1.evaluate(x,y), myOperand2.evaluate(x,y));
            else if (myCommand.equals("mul"))
                return ColorCombinations.multiply(myOperand1.evaluate(x,y), myOperand2.evaluate(x,y));
            else if (myCommand.equals("div"))
                return ColorCombinations.divide(myOperand1.evaluate(x,y), myOperand2.evaluate(x,y));
            else if (myCommand.equals("mod"))
            	return ColorCombinations.mod(myOperand1.evaluate(x,y), myOperand2.evaluate(x,y));
            else if (myCommand.equals("exp"))
            	return ColorCombinations.exp(myOperand1.evaluate(x,y), myOperand2.evaluate(x,y));
            else if(myCommand.equals("neg"))
            	return ColorCombinations.negates(myOperand1.evaluate(x,y));
            else if(myCommand.equals("color"))
            	return ColorCombinations.color(myOperand1.evaluate(x,y), myOperand2.evaluate(x,y), myOperand3.evaluate(x,y));
            else
                throw new ParserException("Unknown Command " + myCommand, Type.UNKNOWN_COMMAND);
        }
    }


    /**
     * @return string representation of expression
     */
    public String toString ()
    {
        StringBuffer result = new StringBuffer();
        if (myCommand == null)
        {
            result.append(myValue); 
        }
        else
        {
            result.append("(");
            result.append(" " + myCommand + " ");
            result.append(myOperand1.toString());  
            result.append(myOperand2.toString());
            result.append(")");
        }
        return result.toString();
    }
    
    public String getXYExpression(){
    	return XYExpression;
    }
}
