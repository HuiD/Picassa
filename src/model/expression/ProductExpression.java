package model.expression;

import java.util.List;
import java.util.Map;

import model.RGBColor;

public class ProductExpression extends CommandExpression{
	private static final int OperandNumber = -1;;


	public int getOperandNumber() {
		return OperandNumber;
	}

	@Override
	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getExpList();
		double red = 1;
		double green = 1;
		double blue = 1;
		for(Expression exp : list){
			red *= exp.evaluate(map).getRed();
			green *= exp.evaluate(map).getGreen();
			blue *= exp.evaluate(map).getBlue();
		}
		return new RGBColor(red, green, blue);
	}

}
