package model.expression;

import java.util.List;
import java.util.Map;

import model.RGBColor;

/**
 * Cosine, evaluate each component of color as if it were a radian value
 * @author Hui Dong
 *
 */
public class CosExpression extends CommandExpression {
	private static final int OPERAND_NUMBER =1 ;
	@Override
	public int getOperandNumber() {
		return OPERAND_NUMBER;
	}

    @Override
    public RGBColor evaluate(Map<String, Expression> map) {
        List<Expression>  list = getExpList();
        double red = list.get(0).evaluate(map).getRed();
        double green = list.get(0).evaluate(map).getGreen();
        double blue = list.get(0).evaluate(map).getBlue();
        return new RGBColor(Math.cos(red), Math.cos(green), Math.cos(blue));
    }

}
