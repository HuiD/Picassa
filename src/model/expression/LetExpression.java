package model.expression;

import java.util.List;
import java.util.Map;
import model.ParserException;
import model.RGBColor;

public class LetExpression extends CommandExpression {
	private static int OPERAND_NUMBER = 3;
	@Override
	public int getOperandNumber() {
		return OPERAND_NUMBER;
	}
	public LetExpression(){
		
	}
	@Override
	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getExpList();
		if(list.size() != OPERAND_NUMBER)
			throw new ParserException("Let Expression should have: " + OPERAND_NUMBER +"parameters.");
		map.put(list.get(0).getMyVariable(), list.get(1));
		RGBColor color = list.get(2).evaluate(map);
		map.put(list.get(0).getMyVariable(), null);
		return color;
	}

}
