package view;

import java.awt.BorderLayout;


import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import actions.PencilAction;
import actions.LineAction;
import actions.RectangleAction;
import actions.EllipseAction;
import actions.EraserAction;

/**
 * Creates the GUI that the user interacts with for the paint program
 * 
 * @author Kenneth Ahrens
 * @author Katlyn Malone
 * @version Fall 2020
 */

public class PowerPaintGUI {

	/** A drawing panel. */
	private PaintPanel myPanel;

	/** The window for this GUI. */
	private JFrame myFrame;
	
	/** The created top Menu Bar*/
	private PaintMenuBar myPaintMenuBar;
	
	/** The created bottom Tool Bar*/
	private PaintToolBar myPaintToolBar;

	/** The image icon to show in the window title and about window. */
	private ImageIcon myImageIcon = new ImageIcon("./images/w.gif");
	
	/**List of actions for the tools*/
	private ArrayList<Action> myToolActions;
	
	/**Default tool action */
	private LineAction myDefaultAction;
	

	/**
	 * Constructor of PowerPaintGUI
	 * 
	 */
	public PowerPaintGUI() {
		// initialize panels, menu, toolbar, etc
		setupGUI();

	}

	/**
	 * Helper method to initialize all GUI components
	 */
	public void setupGUI() {
		// initialize jframe
		myFrame = new JFrame("PowerPaint");
		// add a custom icon to the frame
		myFrame.setIconImage(myImageIcon.getImage());

		myPanel = new PaintPanel();
		//myPanel.addPropertyChangeListener(myPaintMenuBar);
		myFrame.add(myPanel);
		
		myDefaultAction = new LineAction(myPanel);
		myToolActions = new ArrayList<Action>();
		setupToolActions();
		
		myPaintMenuBar = new PaintMenuBar(myFrame, myPanel, myToolActions, myDefaultAction);
		myPaintToolBar = new PaintToolBar(myFrame, myPanel, myToolActions, myDefaultAction);
		

		// put bars on the panel
		myFrame.setJMenuBar(myPaintMenuBar.getMenuBar());
		myFrame.add(myPaintToolBar.getPaintToolBar(), BorderLayout.SOUTH);

		// finalize frame
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.pack();
		myFrame.setLocationRelativeTo(null);
		myFrame.setVisible(true);

	}
	
	/**
	 * Creates an action for each tool and then adds it to the 
	 * array list of actions
	 */
	public void setupToolActions () {
		myToolActions.add(new PencilAction(myPanel));
		myToolActions.add(myDefaultAction); //line tool 
		myToolActions.add(new RectangleAction(myPanel));
		myToolActions.add(new EllipseAction(myPanel));
		myToolActions.add(new EraserAction(myPanel));
	}
	
}
