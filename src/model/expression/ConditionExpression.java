package model.expression;

import java.util.List;
import java.util.Map;

import model.RGBColor;
/**
 * Conditional expressions contains three parameters, the first parameter will give the 
 * condition statement, while the other two give the value.
 * @author Hui Dong
 *
 */
public class ConditionExpression extends CommandExpression {
	private static final int OperandNumber = 3;
    /**
     * return the operand number of the expression
     */
	@Override
	public int getOperandNumber() {
		return OperandNumber;
	}

	/**
	 * Check the value of the first expression, if the value of the first expression
	 * is greater than 0, then return the second expression's value, otherwise return
	 * the value of the third expression.
	 */
	@Override
	public RGBColor evaluate(Map<String, Expression> map) {
		List<Expression> list = getExpList();
		Expression conditionExpr = list.get(0);
		double value =( conditionExpr.evaluate(map).getRed()
				+ conditionExpr.evaluate(map).getGreen()
				+ conditionExpr.evaluate(map).getBlue())/3;
        if(value > 0)
        	return list.get(1).evaluate(map);
        else
        	return list.get(2).evaluate(map);
	}

}
