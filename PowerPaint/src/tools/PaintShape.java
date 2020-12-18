package tools;

import java.awt.Color;
import java.awt.Shape;

/**
 * Stores the information that paint panel needs to draw a shape
 * 
 * @author Kenneth Ahrens
 * @author Katlyn Malone
 * @version Fall 2020
 */

public class PaintShape {

	
	/** The shape to be painted */
	private final Shape myShape;

	/** The color of the shape */
	private final Color myColor;

	/** The thickness of the shape. */
	private final int myThickness;

    /**
     * Constructs that stores info about a PaintShape
     * 
     * @param theShape the shape 
     * @param theColor color of the shape
     * @param theThickness the width of the stroke 
     * 
     */
	public PaintShape(final Shape theShape, final Color theColor, final int theThickness) {
		myShape = theShape;
		myColor = theColor;
		myThickness = theThickness;
	}
	
    
    /** 
     * Getter for myShape
     * 
     * @return Shape 
     */
	public Shape getShape() {
		return myShape;
	}
	
    /** 
     * Getter for myColor
     * 
     * @return Color
     */
	public Color getColor() {
		return myColor;

	}
	
    /** 
     * Getter for myThickness
     * 
     * @return int
     */
	public int getThickness() {
		return myThickness;
	}
}
