package model.expression;


import java.util.List;
import java.util.Map;



import model.RGBColor;


public class NegateExpression extends CommandExpression{
	private static final int OPERAND_NUMBER = 1;
	
	public int getOperandNumber() {
		return OPERAND_NUMBER;
	}

	@Override
	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getExpList();
        return new RGBColor(- list.get(0).evaluate(map).getRed(),
        					- list.get(0).evaluate(map).getGreen(),
        					- list.get(0).evaluate(map).getBlue());
	}
	






}
