package model.expression;

import java.util.Map;
import model.RGBColor;

/**
 * An expression takes mathematical expression and generate 
 * the RGBColor according to the expression.
 * @author Hui Dong
 *
 */

public abstract class Expression {
	private String myVariable;
	
	/**
	 * 
	 * @return a string variable
	 */
	public String getMyVariable() {
		return myVariable;
	}

	
	/**
	 * String variable
	 * @param myVariable
	 */
	public void setMyVariable(String myVariable) {
		this.myVariable = myVariable;
	}
	
	
    /**
     * Generate RGBColor depending on various mathmatical expression.
     * @param map contains the variables (e.g. constant, x, y or t)
     * @return a RGBColor 
     */
	public abstract RGBColor evaluate(Map<String, Expression> map);
}
