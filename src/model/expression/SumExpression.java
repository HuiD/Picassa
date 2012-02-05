package model.expression;

import java.util.List;
import java.util.Map;

import model.RGBColor;

/**
 * Sum the value of variables for each color component.
 * @author Hui Dong
 *
 */
public class SumExpression extends CommandExpression{
	private static final int OperandNumber = -1;;
	@Override
	public int getOperandNumber() {
		// TODO Auto-generated method stub
		return OperandNumber;
	}

	@Override
	public RGBColor evaluate(Map<String, Expression> map) {
		// TODO Auto-generated method stub
		List<Expression> list = getExpList();
		double redSum = 0;
		double greenSum = 0;
		double blueSum = 0;
		for(Expression exp : list){
			redSum += exp.evaluate(map).getRed();
			greenSum += exp.evaluate(map).getGreen();
			blueSum += exp.evaluate(map).getBlue();
		}
		return new RGBColor(redSum, greenSum, blueSum);
	}

}
