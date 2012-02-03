package model.expression;


import java.util.List;
import java.util.Map;



import model.RGBColor;

public class MulExpression extends CommandExpression{
	private static final int OPERAND_NUMBER = 2;
	
	public int getOperandNumber() {
		return OPERAND_NUMBER;
	}

	public MulExpression(List<Expression> list) {
		super(list);
		// TODO Auto-generated constructor stub
	}
	
	public MulExpression(){
		
	}

	@Override
	public RGBColor evaluate(Map<String, Expression> map) {
		// TODO Auto-generated method stub
		List<Expression> list = getExpList();
		RGBColor operand1 = list.get(0).evaluate(map);
		RGBColor operand2 = list.get(1).evaluate(map);
        return new RGBColor(operand1.getRed()*operand2.getRed(),
        					operand1.getGreen()*operand2.getGreen(),
        					operand1.getBlue()*operand2.getBlue());
	}
	
	
	






}
