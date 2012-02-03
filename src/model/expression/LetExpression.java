package model.expression;

import java.util.List;
import java.util.Map;
import model.ParserException;
import model.RGBColor;
/**
 * Let expression will assign value of an expression to an variable name. The value of the variable name
 * is given by the second expression of the let expression.
 * @author Hui Dong
 *
 */
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
