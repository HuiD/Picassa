package model.expression;

import java.util.Map;

import model.RGBColor;

/**
 * Contains an constant value.
 * @author Hui Dong
 *
 */
public class ConstantExpression extends Expression{
	private double myValue;

	public ConstantExpression(double myValue) {
		this.myValue = myValue;
	}
	public ConstantExpression(){
		
	}
	@Override
	public RGBColor evaluate(Map<String, Expression> map) {
		return new RGBColor(myValue);
	}

}
