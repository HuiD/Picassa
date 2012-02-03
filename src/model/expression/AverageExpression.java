package model.expression;

import java.util.List;
import java.util.Map;

import model.RGBColor;

/**
 * Compute the average value of each color component and generate the RGBColor
 * @author Hui Dong
 *
 */
public final class AverageExpression extends CommandExpression{
	private static final int OperandNumber = -1;
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
		
		return new RGBColor(redSum/list.size(), greenSum/list.size(), blueSum/list.size());
	}

}
