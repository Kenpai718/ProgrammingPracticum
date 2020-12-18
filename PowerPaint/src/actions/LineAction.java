package actions;

import view.PaintPanel;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import tools.LineTool;
import tools.PaintTool;

/**
 * Sets up the line action for the button.
 * 
 * @author Kenneth Ahrens
 * @author Katlyn Malone
 * @version Fall 2020
 */

public class LineAction extends AbstractAction {
	
	//constants
	/** Name for the button*/
	public static final String NAME = "Line";
	/** Icon for the button*/
    public static final ImageIcon ICON = new ImageIcon("./images/line_bw.gif");
    
    /** The JPanel to associate with this Action. */
    private final PaintPanel myPanel;
    
    /** The tool that represents the Line. */
    private final PaintTool myTool;
  
    
    
   /**
     * Construct an Action of the Line tool.
     * 
     * @param thePanel a JPanel to associate with this Action.
     */
    public LineAction(final PaintPanel thePanel) {
        super(NAME, ICON);
        
        myPanel = thePanel;
        myTool = new LineTool();
        
        putValue(Action.MNEMONIC_KEY, myTool.getMnemonic());
        putValue(Action.SELECTED_KEY, true);
        putValue(Action.SHORT_DESCRIPTION, "A Line");
        

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