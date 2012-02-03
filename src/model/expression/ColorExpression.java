package model.expression;

import java.util.List;
import java.util.Map;
import model.RGBColor;

/**
 * Create a color which color components are determined seperately
 * @author Hui Dong
 *
 */
public class ColorExpression extends CommandExpression {
	
	private static final int OPERAND_NUMBER = 3;
	
	public int getOperandNumber() {
		return OPERAND_NUMBER;
	}



	@Override
	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getExpList();
		return new RGBColor(list.get(0).evaluate(map).getRed(),
							list.get(1).evaluate(map).getRed(),
							list.get(2).evaluate(map).getRed());
	}


}
