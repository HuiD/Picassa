package model.expression;

import java.util.List;
import java.util.Map;

import model.RGBColor;

public class MinExpression extends CommandExpression{
	private static final int OperandNumber = -1;


	public int getOperandNumber() {
		// TODO Auto-generated method stub
		return OperandNumber;
	}

	@Override
	public RGBColor evaluate(Map<String, Expression> map) {
		// TODO Auto-generated method stub
		List<Expression> list = getExpList();
		double redMin = list.get(0).evaluate(map).getRed();
		double greenMin = list.get(0).evaluate(map).getGreen();
		double blueMin = list.get(0).evaluate(map).getBlue();
		for(int i = 1; i < list.size(); i+=1){
			redMin = Math.min(redMin, list.get(i).evaluate(map).getRed());
			greenMin = Math.min(greenMin, list.get(i).evaluate(map).getGreen());
			blueMin = Math.min(blueMin, list.get(i).evaluate(map).getBlue());		
		}
		return new RGBColor(redMin, greenMin, blueMin);
	}


}
