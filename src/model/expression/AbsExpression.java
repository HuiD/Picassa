package model.expression;

import java.util.List;
import java.util.Map;

import model.RGBColor;

/**
 * Generate the absolute value of the variable.
 * @author Hui Dong
 *
 */
public class AbsExpression extends CommandExpression {
	private static final int OPERAND_NUMBER = 1;
	
	@Override
	public int getOperandNumber() {
		return OPERAND_NUMBER;
	}
	
	@Override
	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getExpList();
		return new RGBColor(Math.abs(list.get(0).evaluate(map).getRed()), 
				            Math.abs(list.get(0).evaluate(map).getGreen()),
				            Math.abs(list.get(0).evaluate(map).getBlue()));
	}

}
