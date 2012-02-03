package model.expression;

import java.util.List;
import java.util.Map;

import model.RGBColor;
import model.util.PerlinNoise;

public class PerlinColorExpression extends CommandExpression {
	private static final int OPERAND_NUMBER = 2;
	@Override
	public int getOperandNumber() {
		return OPERAND_NUMBER;
	}

	@Override
	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getExpList();
		RGBColor left = new RGBColor(list.get(0).evaluate(map));
		RGBColor right = new RGBColor(list.get(1).evaluate(map));
		return PerlinNoise.colorNoise(left, right);
	}

}
