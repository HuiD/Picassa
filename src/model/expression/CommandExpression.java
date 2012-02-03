package model.expression;


import java.util.ArrayList;
import java.util.List;


/**
 * Super class for command Expressions such as plus, multiply, color and so on.
 * Varaible expList contains all the parameters of the command expression
 * @author Hui Dong
 *
 */

public abstract class CommandExpression extends Expression{
	
	private List<Expression> expList= new ArrayList<Expression>();
	
	
	public abstract int getOperandNumber();

	public CommandExpression(){
		
	}

	public CommandExpression(List<Expression> list) 
	{
		expList = list;
	}

	public List<Expression> getExpList() {
		return expList;
	}

	public void setExpList(List<Expression> expList) {
		this.expList = expList;
	}
}
