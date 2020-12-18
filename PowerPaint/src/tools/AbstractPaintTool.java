package tools;

import java.awt.Point;
import java.awt.Shape;

/**
 * 
 * Sets up most of the methods that define a tool.
 * 
 * @author Kenneth Ahrens
 * @author Katlyn Malone
 * @version Fall 2020
 */

public abstract class AbstractPaintTool implements PaintTool {

	/** Constant for a point off the panel */
	public static final Point NO_POINT = new Point(-50, -50);
	/** Name for the tool */
	private final String myName;
	/** Mnemonic value for the tool */
	private final int myMnemonic;
	/** Starting point of the tool */
	private Point myStartPoint;

	/**
	 * Constructor
	 * 
	 * @param theName     is the name of the tool
	 * @param theMnemonic is the keyboard shortcut value of the tool
	 */
	public AbstractPaintTool(final String theName, final int theMnemonic) {
		myName = theName;
		myMnemonic = theMnemonic;
		myStartPoint = NO_POINT;
	}

	/**
	 * Get the name of the tool
	 * 
	 * @return String the name of the tool
	 */
	@Override
	public String getName() {
		return myName;
	}

	/**
	 * Get the mnemonic of the tool
	 * 
	 * @return int the mnemonic value
	 */
	@Override
	public int getMnemonic() {
		return myMnemonic;
	}

	/**
	 * Set start point of the tool
	 * 
	 */
	@Override
	public void setStartPoint(final Point thePoint) {
		myStartPoint = thePoint;

	}

	/**
	 * Get startpoint
	 * 
	 * @return Point startpoint
	 */
	@Override
	public Point getStartPoint() {
		return myStartPoint;
	}

	/**
	 * Reset tools to default
	 */
	@Override
	public void reset() {
		myStartPoint = NO_POINT;

	}

	/**
	 * Get Shape of the tool
	 * 
	 * @return Shape of tool
	 */
	@Override
	public abstract Shape getShape();

	/**
	 * Setter for next point
	 * 
	 */
	@Override
	public abstract void setNextPoint(final Point thePoint);

}
