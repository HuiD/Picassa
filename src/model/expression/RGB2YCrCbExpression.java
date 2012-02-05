package model.expression;

import java.util.List;
import java.util.Map;

import model.RGBColor;

/**
 * Convert color to luminance / chrominance space.
 * @author Hui Dong
 *
 */
public class RGB2YCrCbExpression extends CommandExpression {
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
                c.getRed() *  0.2989 + c.getGreen() *  0.5866 + c.getBlue() *  0.1145,
                c.getRed() * -0.1687 + c.getGreen() * -0.3312 + c.getBlue() *  0.5,
                c.getRed() *  0.5000 + c.getGreen() * -0.4183 + c.getBlue() * -0.0816);
	}

}
