package model.expression;

import java.util.Map;
import java.util.Random;

import model.RGBColor;

public class RandomExpression extends CommandExpression {
	private static final int OPERAND_NUMBER = 0;
	private Random random;
	private double[] colors = new double[3];
	public RandomExpression(){
		random = new Random();
		for(int i = 0; i < colors.length; i++)
			colors[i] = random.nextDouble()*2 - 1;
	}
	public int getOperandNumber() {
		return OPERAND_NUMBER;
	}

	@Override
	public RGBColor evaluate(Map<String, Expression> map) {
		return new RGBColor(colors[0], colors[1], colors[2]);
	}

}
