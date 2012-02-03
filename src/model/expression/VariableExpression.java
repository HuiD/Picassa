package model.expression;

import java.util.Map;

import model.ParserException;
import model.RGBColor;

/**
 * VariableExpression can take variables such as a, b, c or x, y,z.
 * @author Hui Dong
 *
 */
public class VariableExpression extends Expression{

	public VariableExpression() {
		
	}
	
	@Override
	public RGBColor evaluate(Map<String, Expression> map) {
		// TODO Auto-generated method stub
		Expression exp;
    	exp = map.get(getMyVariable());
    	if(exp == null)
    		throw new ParserException("Unknown variable");
    	return exp.evaluate(map);
	}





}
