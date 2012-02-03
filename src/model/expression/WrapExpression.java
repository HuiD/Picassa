package model.expression;

import java.util.List;
import java.util.Map;

import model.RGBColor;

public class WrapExpression extends CommandExpression {
	private static final int OPERAND_NUMBER = 1;
	@Override
	public int getOperandNumber() {
		return OPERAND_NUMBER;
	}

	@Override
	public RGBColor evaluate(Map<String, Expression> map) {
        List<Expression> list =getExpList();
        RGBColor rgbColor = new RGBColor(list.get(0).evaluate(map));
        rgbColor.wrap();
		return rgbColor;
	}
	


}
