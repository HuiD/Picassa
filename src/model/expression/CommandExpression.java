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
	
	/**
	 * 
	 * @return the number of operand for the command expression.
	 */
	public abstract int getOperandNumber();
    
	/**
	 * CommandExpression constructor method
	 */
	public CommandExpression(){
		
	}

    
	/**
	 * 
	 * @return the list that contains all the expressions in the expression
	 */
	public List<Expression> getExpList() {
		return expList;
	}
    
	/**
	 * Set the expresison list of the command expression.
	 * @param expList
	 */
	public void setExpList(List<Expression> expList) {
		this.expList = expList;
	}
}
