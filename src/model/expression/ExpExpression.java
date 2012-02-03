package model.expression;

import java.util.List;
import java.util.Map;
import model.RGBColor;

public class ExpExpression extends CommandExpression{
	
	private static final int OPERAND_NUMBER = 2;
	
	public int getOperandNumber() {
		return OPERAND_NUMBER;
	}

	public ExpExpression(List<Expression> list) {
		super(list);
	}
	
	public ExpExpression(){
		
	}
	
	@Override
	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getExpList();
		RGBColor operand1 = list.get(0).evaluate(map);
		RGBColor operand2 = list.get(1).evaluate(map);
        return new RGBColor(Math.pow(operand1.getRed(),operand2.getRed()),
        					Math.pow(operand1.getGreen(),operand2.getGreen()),
        					Math.pow(operand1.getBlue(),operand2.getBlue()));
	}
}
