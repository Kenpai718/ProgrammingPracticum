package tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

/**
 * Tool draws a rectangle shape
 * 
 * @author Kenneth Ahrens
 * @author Katlyn Malone
 * @version Fall 2020
 */

public class RectangleTool extends AbstractPaintTool {

	/** Constant name for the tool */
	private static final String MY_NAME = "Rectangle";

	/** Constant mnemonic for the tool */
	private static final int MY_MNEMONIC = KeyEvent.VK_R;

	/**Next point field */
	private Point myNextPoint;

	/**
	 * Default constructor: defines rectangle
	 * 
	 */
	public RectangleTool() {
		super(MY_NAME, MY_MNEMONIC);
		myNextPoint = NO_POINT;
	}
	
	/**
	 * Constructor for inheritance
	 * 
	 * @param theName     is the name of the tool
	 * @param theMnemonic is the keyboard shortcut value of the tool
	 */
	public RectangleTool(final String theName, final int theMnemonic) {
		super(theName, theMnemonic);
		myNextPoint = NO_POINT;
	}

	/**
	 * Get Shape of the tool
	 * 
	 * @return Shape of tool
	 */
	@Override
	public Shape getShape() {
		final Rectangle2D.Double rect = new Rectangle2D.Double();
		rect.setFrameFromDiagonal(getStartPoint(), myNextPoint);
		return rect;
	}

	/**
	 * Change starting point
	 * 
	 * @param thePoint is the new start point
	 */
	@Override
	public void setStartPoint(final Point thePoint) {
		super.setStartPoint(thePoint);
		myNextPoint = thePoint;
	}

	/**
	 * Change next point
	 * 
	 * @param thePoint is the new next point
	 */
	@Override
	public void setNextPoint(final Point thePoint) {
		myNextPoint = thePoint;
	}

	/**
	 * Reset tool to default
	 */
	@Override
	public void reset() {
		super.reset();
		myNextPoint = NO_POINT;
	}

}
