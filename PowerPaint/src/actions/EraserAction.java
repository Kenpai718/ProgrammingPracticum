package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import tools.EraserTool;
import tools.PaintTool;
import view.PaintPanel;

/**
 * Sets up the Eraser action for a button
 * 
 * @author Kenneth Ahrens
 * @author Katlyn Malone
 * @version Fall 2020
 */

public class EraserAction extends AbstractAction {

	// constants
	/** Name for the button*/
	public static final String NAME = "Eraser";
	/** Icon for the button*/
	public static final ImageIcon ICON = new ImageIcon("./images/eraser_bw.gif");

	/** The JPanel to associate with this Action. */
	private final PaintPanel myPanel;

	/**The tool associated with this action*/
	private final PaintTool myTool;

	/**
	 * Construct an Action of the Line tool.
	 * 
	 * @param thePanel a JPanel to associate with this Action.
	 */
	public EraserAction(final PaintPanel thePanel) {
		super(NAME, ICON);

		myPanel = thePanel;
		myTool = new EraserTool();

		putValue(Action.MNEMONIC_KEY, myTool.getMnemonic());
		putValue(Action.SELECTED_KEY, true);
		putValue(Action.SHORT_DESCRIPTION, "An Eraser");

	}

    /**
     * When clicking the button change to specified tool
     * 
     * @param ActionEvent clicking the button
     */
	@Override
	public void actionPerformed(final ActionEvent theEvent) {
		myPanel.setCurrentTool(myTool);
		myPanel.repaint();
	}
	
    /**
     * Get tool for this action
     * 
     * @return the tool used for this action
     */
    public PaintTool getTool() {
    	return myTool;
    }
	
    @Override
    public String toString() {
    	return NAME;
    }

}
