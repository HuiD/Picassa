package model.expression;

import java.util.List;
import java.util.Map;

import model.RGBColor;

/**
 * convert color to RGB from luminance/chrominace space.
 * @author Hui Dong
 *
 */
public class YCrCb2RGBExpression extends CommandExpression {
	private static final int OPERAND_NUMBER = 1;
	@Override
	public int getOperandNumber() {
		return OPERAND_NUMBER;
	}

	@Override
	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getExpList();
		RGBColor c = new RGBColor(list.get(0).evaluate(map));
	    return new RGBColor(
                   c.getRed() + c.getBlue() *  1.4022,
                   c.getRed() + c.getGreen() * -0.3456 + c.getBlue() * -0.7145,
                   c.getRed() + c.getGreen() *  1.7710);
	}

}
