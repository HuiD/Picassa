package model.expression;

import java.util.Map;
import model.RGBColor;



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
