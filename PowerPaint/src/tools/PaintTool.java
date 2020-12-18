package tools;

import java.awt.Point;
import java.awt.Shape;

/**
 * Defines what makes a tool
 * 
 * @author Kenneth Ahrens
 * @author Katlyn Malone
 * @version Fall 2020
 */

public interface PaintTool {
	
	/**
	 * Get the name of the tool
	 * 
	 * @return String the name of the tool
	 */
	String getName();
	
	/**
	 * Get the mnemonic of the tool
	 * 
	 * @return int the mnemonic value
	 */
	int getMnemonic();
	
	/**
	 * Get Shape of the tool
	 * 
	 * @return Shape of tool
	 */
	Shape getShape();
	
	/**
	 * Change starting point
	 * 
	 * @param thePoint is the new start point
	 */
	void setStartPoint(final Point thePoint);
	
	/**
	 * Change next point
	 * 
	 * @param thePoint is the new next point
	 */
	void setNextPoint(final Point thePoint);
	
	/**
	 * Get starting point
	 * 
	 * @return Point the start point
	 */
	Point getStartPoint();
	
	/**
	 * Reset tool to default
	 */
	void reset();

}
