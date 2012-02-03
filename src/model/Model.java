package model;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import model.expression.ConstantExpression;
import model.expression.Expression;

/**
 * Evaluate an expression for each pixel in a image.
 * 
 * @author Robert C Duvall
 */
public class Model {
	public static final double DOMAIN_MIN = -1;
	public static final double DOMAIN_MAX = 1;
	
	private static final String OPERAND_X = "x";
	private static final String OPERAND_Y = "y";
	private static final String OPERAND_T = "t";
    public static final int NUM_FRAMES = 50;

    private double myCurrentTime = 0;


    /**
     * Advance to the next frame in the animation.
     */
    public void reset ()
    {
        myCurrentTime = 0;
    }


    /**
     * Advance to the next frame in the animation.
     */
    public void nextFrame ()
    {
        myCurrentTime += 1.0 / NUM_FRAMES;
    }

	private Map<String, Expression> map = new HashMap<String, Expression>();
	/**
	 * Evaluate an expression for each point in the image.
	 */
	public Pixmap evaluate(String input, Dimension size) {
		Pixmap result = new Pixmap(size);
		// create expression to evaluate just once
		Expression toEval = new Parser().makeExpression(input);
		// evaluate at each pixel
		for (int imageY = 0; imageY < size.height; imageY++) {
			double evalY = imageToDomainScale(imageY, size.height);
			for (int imageX = 0; imageX < size.width; imageX++) {
				double evalX = imageToDomainScale(imageX, size.width);
				ConstantExpression xExpression = new ConstantExpression(evalX);
				ConstantExpression yExpression = new ConstantExpression(evalY);
				ConstantExpression tExpression = new ConstantExpression(2*myCurrentTime - 1);
				map.put(OPERAND_X, xExpression);
				map.put(OPERAND_Y, yExpression);
				map.put(OPERAND_T, tExpression);
					result.setColor(imageX, imageY, toEval.evaluate(map)
						.toJavaColor());
			}
		}
		return result;
	}
	


	/**
	 * Convert from image space to domain space.
	 */
	protected double imageToDomainScale(int value, int bounds) {
		double range = DOMAIN_MAX - DOMAIN_MIN;
		return ((double) value / bounds) * range + DOMAIN_MIN;
	}
}
