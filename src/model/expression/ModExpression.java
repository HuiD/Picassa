package model.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import model.RGBColor;

public class ModExpression extends CommandExpression{
	private static final int OPERAND_NUMBER = 2;
	
	public int getOperandNumber() {
		return OPERAND_NUMBER;
	}

	public ModExpression(){
		
	}
	
	
	public ModExpression(List<Expression> list) {
		super(list);
	}


	public ModExpression(String command, ArrayList<Expression> list,
			RGBColor value) {
		super(list);
	}

	@Override
	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getExpList();
		RGBColor operand1 = list.get(0).evaluate(map);
		RGBColor operand2 = list.get(1).evaluate(map);
        return new RGBColor(operand1.getRed()%operand2.getRed(),
        					operand1.getGreen()%operand2.getGreen(),
        					operand1.getBlue()%operand2.getBlue());
	}
}
