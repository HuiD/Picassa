package model.expression;

import java.util.List;
import java.util.Map;

import model.RGBColor;

/**
 * round up the value;
 * @author Hui Dong
 *
 */
public class CeilExpression extends CommandExpression {

	
	private static final int OPERAND_NUMBER = 1;
	
	@Override
	public int getOperandNumber() {
		return OPERAND_NUMBER;
	}

	@Override
	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getExpList();
		double red = Math.ceil(list.get(0).evaluate(map).getRed());
		double green = Math.ceil(list.get(0).evaluate(map).getGreen());
		double blue = Math.ceil(list.get(0).evaluate(map).getBlue());
		return new RGBColor(red, green,blue);
	}

}
