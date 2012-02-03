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
	
	public String getMyVariable() {
		return myVariable;
	}

	public void setMyVariable(String myVariable) {
		this.myVariable = myVariable;
	}

	public abstract RGBColor evaluate(Map<String, Expression> map);
}
