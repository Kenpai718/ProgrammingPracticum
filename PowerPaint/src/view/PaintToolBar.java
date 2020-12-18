package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/**
 * 
 * Sets up the bottom tool bar in the GUI
 * 
 * @author Kenneth Ahrens
 * @author Katlyn Malone
 * @version Fall 2020
 */

public class PaintToolBar extends JToolBar {

	/** A button group for tool actions in toolbar */
	private ButtonGroup myToolBarButtons;

	/** A list of tool actions from actions package. */
	private List<Action> myToolActions;

	/** Toolbar of tools attached to jpanel */
	private JToolBar myToolBar;

	/** Sets default tool action */
	private Action myDefaultAction;
	
	/**
	 * PaintToolBar constructor
	 * 
	 * @param theFrame               is the frame the menu bar is attached to
	 * @param thePanel               is the drawing panel
	 * @param theToolActions         is the list of tool actions to assign to buttons
	 * @param theAction              is the action used for the default action
	 */
	public PaintToolBar(JFrame theFrame, PaintPanel thePanel,
			ArrayList<Action> theToolActions, Action theAction) {
		super();
		myToolBar = new JToolBar();
		myToolBarButtons = new ButtonGroup();
		myToolActions = theToolActions;

		// initialize tool actions for buttons
		myDefaultAction = theAction;

		setupToolBarActions();
	}
	
	/**
	 * Creates each Tool Action as a JToggleButton 
	 * and adds it to the tool bar
	 */
	public void setupToolBarActions() {

		// associate tool buttons with actions and add to toolbar
		for (final Action act : myToolActions) {
			final JToggleButton tb = new JToggleButton(act);
			myToolBarButtons.add(tb);
			myToolBar.add(tb);

			// selects the line tool at the start as default
			if (tb.getAction() == myDefaultAction) {
				tb.setSelected(true);
			}

		}

	}
	
	/**
	 *Getter for the toolbar
	 * 
	 * @return JToolBar the finished toolbar
	 */
	public JToolBar getPaintToolBar() {
		return myToolBar;
	}
}
