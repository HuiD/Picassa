package model.expression;

import java.util.List;
import java.util.Map;

import model.RGBColor;

/**
 * Obtain the maximum color value of all the variables, then assign the value
 * each color component.
 * @author Hui Dong
 *
 */
public class MaxExpression extends CommandExpression{

	private static final int OperandNumber = -1;


	public int getOperandNumber() {
		// TODO Auto-generated method stub
		return OperandNumber;
	}

	@Override
	public RGBColor evaluate(Map<String, Expression> map) {
		// TODO Auto-generated method stub
		List<Expression> list = getExpList();
		double redMax = list.get(0).evaluate(map).getRed();
		double greenMax = list.get(0).evaluate(map).getGreen();
		double blueMax = list.get(0).evaluate(map).getBlue();
		for(int i = 1; i < list.size(); i+=1){
			redMax = Math.max(redMax, list.get(i).evaluate(map).getRed());
			greenMax = Math.max(greenMax, list.get(i).evaluate(map).getGreen());
			blueMax = Math.max(blueMax, list.get(i).evaluate(map).getBlue());		
		}
		return new RGBColor(redMax, greenMax, blueMax);
	}
}
