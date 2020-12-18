package view;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.PaintPanelProperties;
import model.PaintShape;

/**
 * Sets up the top Menu bar that includes the File, Options, Tools, and Help
 * menus and all menu items inside of them including their listeners in the GUI
 * 
 * @author Kenneth Ahrens
 * @author Katlyn Malone
 * @version Fall 2020
 */

public class PaintMenuBar extends JMenuBar
		implements PropertyChangeListener, PaintPanelProperties {

	// constants

	/** Slider minimum value. */
	private static final int THICKNESS_MIN = 0;

	/** Slider maximum value. */
	private static final int THICKNESS_MAX = 20;

	/** Slider major spacing. */
	private static final int MAJOR_SPACING = 5;

	/** Slider minor spacing. */
	private static final int MINOR_SPACING = 1;

	/** Slider initial value position. */
	private static final int THICKNESS_INITIAL = 10;

	/** About message displayed in the About... pop out */
	private static final String ABOUT_MSG = "Katlyn Malone and Kenneth Ahrens\nAutumn 2020 \nTCSS 305 Assignment 4";

	// fields

	/** Primary color icon */
	private final ColorIcon myColorIcon;

	/** Secondary color icon */
	private final ColorIcon myColorIcon2;

	/** PaintPanel that is drawn on */
	private final PaintPanel myPaintPanel;

	/** Frame that everything is attached too */
	private final JFrame myFrame;

	/** Menubar that contains all dropdowns */
	private JMenuBar myMenuBar;

	/** Tools drop down menu */
	private JMenu myToolsMenu;

	/** A button group for tool actions in tool menu */
	private ButtonGroup myToolMenuButtons;

	/** A list of tool actions from actions package. */
	private List<Action> myToolActions;

	/** File menu option */
	private JMenu myFileMenu;

	/** Top file menu bar. */
	private JMenu myOptionsMenu;

	/** Top help menu bar. */
	private JMenu myHelpMenu;

	/** The image icon to show in the window title and about window. */
	private ImageIcon myImageIcon = new ImageIcon("./images/w.gif");

	/** Primary color menu button in options menu */
	private JMenuItem myPrimaryButton;

	/** Secondary color menu button in options menu */
	private JMenuItem mySecondaryButton;

	/** Thickness slider */
	private JSlider myThicknessSlider;

	/** Clear button in options. */
	private JMenuItem myClearButton;

	/** Clear button in options. */
	private JMenuItem myUndoButton;

	/** Clear button in options. */
	private JMenuItem myRedoButton;

	/** Sets default tool action */
	private Action myDefaultAction;

	/**
	 * PaintMenuBar constructor
	 * 
	 * @param theFrame         is the frame the menu bar is attached to
	 * @param thePanel         is the drawing panel
	 * @param theToolActions   is the list of tool actions to assign to buttons
	 * @param theDefaultAction is the list of tool actions to assign to button
	 */
	public PaintMenuBar(JFrame theFrame, PaintPanel thePanel,
			ArrayList<Action> theToolActions, Action theDefaultAction) {
		super();
		myPaintPanel = thePanel;
		myFrame = theFrame;
		myToolMenuButtons = new ButtonGroup();
		myColorIcon = new ColorIcon(DEFAULT_PRIMARY);
		myColorIcon2 = new ColorIcon(DEFAULT_SECONDARY);

		myDefaultAction = theDefaultAction;
		myToolActions = theToolActions;

		myPaintPanel.addPropertyChangeListener(this);

		setupMenuBar();
	}

	/**
	 * Getter for the menubar
	 *
	 * @return JMenuBar the finished menubar
	 */
	public JMenuBar getMenuBar() {
		return myMenuBar;
	}

	/**
	 * Sets up the menubar with all dropdowns
	 */
	public void setupMenuBar() {
		myMenuBar = new JMenuBar();

		// helper methods to condense code
		setupMenuFile();
		setupMenuOptions();
		setupMenuTools();
		setupMenuHelp();

	}

	/**
	 * Creates the File menu on the top bar and all items in it's dropdown menu
	 * with shortcuts
	 */
	public void setupMenuFile() {
		myFileMenu = new JMenu("File");
		myFileMenu.setMnemonic(KeyEvent.VK_F);
		myMenuBar.add(myFileMenu);

		JMenuItem newMenuItem = new JMenuItem("New");
		newMenuItem.setMnemonic(KeyEvent.VK_N);
		myFileMenu.add(newMenuItem);
		newMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// clears panel to make a new one
				myPaintPanel.clearShapes();

			}

		});

		myFileMenu.addSeparator();

		JMenuItem save = new JMenuItem("Save");
		save.setMnemonic(KeyEvent.VK_S);
		save.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		myFileMenu.add(save);

		// save the current drawings if this button is clicked
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					saveToFile(myFrame);
				} catch (IOException e1) {

					e1.printStackTrace();
				}

			}

		});

		myFileMenu.addSeparator();

		JMenuItem load = new JMenuItem("Load");
		load.setMnemonic(KeyEvent.VK_L);
		load.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		myFileMenu.add(load);

		// load a drawing from file if this button is clicked
		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loadFromFile(myFrame);

			}

		});

		myFileMenu.addSeparator();

		JMenuItem exit = new JMenuItem("Exit", KeyEvent.VK_X);
		exit.addActionListener(new ExitAction());
		myFileMenu.add(exit);

	}

	/**
	 * Creates the saved file and saves it to the computer Stores info on the
	 * shapes currently drawn to paintpanel.
	 * 
	 * @param theFrame the window that opens when you choose to save the file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void saveToFile(Frame theFrame)
			throws FileNotFoundException, IOException {

		FileDialog fd; // A file dialog box that will let the user
						// specify the output file.

		// start file dialog to prompt user for file name/location to save to
		fd = new FileDialog(theFrame, "Save to File", FileDialog.SAVE);
		fd.show();

		String fileName = fd.getFile(); // gets what the user input for file
										// name

		// if user closes without entering a name
		if (fileName == null) {
			JOptionPane.showMessageDialog(null, "Save was canceled.");
			return;
		} else {

			// add a file extension if user did not do so
			if (!fileName.endsWith(".shp"))
				fileName += ".shp";

			String directoryName = fd.getDirectory();

			// make the file
			File file = new File(directoryName, fileName);

			// write object to a file
			ObjectOutputStream out;

			try {
				// write info to the file
				out = new ObjectOutputStream(
						new BufferedOutputStream(new FileOutputStream(file)));

				// get the current list of shapes from the panel
				Stack<PaintShape> shapesList = myPaintPanel.getShapesList();

				// save the serialized object to the file
				out.writeObject(shapesList);
				out.close();

				// tell user it worked
				JOptionPane.showMessageDialog(null,
						fileName + " saved sucessfully!");

				// error catching
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, e);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}

	}

	/**
	 * Reads the saved file and loads in the paintshapes to paintpanel
	 * 
	 * @param theFrame the window that opens when you choose to load the file
	 */
	public void loadFromFile(Frame theFrame) {

		FileDialog fd; // A file dialog box that will let the user
		// specify the input file.

		// start file dialog to prompt user for file name/location to load from
		fd = new FileDialog(theFrame, "Save to File", FileDialog.LOAD);
		fd.show();

		String fileName = fd.getFile();

		// cancel load if user doesnt input anything
		if (fileName == null) {
			JOptionPane.showMessageDialog(null, "Load was canceled.");
			return;
		} else {

			String directoryName = fd.getDirectory();

			File file = new File(directoryName, fileName);

			ObjectInputStream in;

			try {
				in = new ObjectInputStream(
						new BufferedInputStream(new FileInputStream(file)));

				// get the paintshape data from the input file
				Object obj = in.readObject();
				Stack<PaintShape> shapeData = (Stack<PaintShape>) obj;

				// send the loaded shapes to panel to redraw
				myPaintPanel.loadShapes(shapeData);

				// tell user it worked
				JOptionPane.showMessageDialog(null,
						fileName + " loaded successfully!");

				// error catching below
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, e);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e);
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}

	}

	/**
	 * Creates the Option button on the top bar and all items in it's dropdown
	 * menu with shortcuts
	 */
	public void setupMenuOptions() {
		myOptionsMenu = new JMenu("Options");
		myOptionsMenu.setMnemonic(KeyEvent.VK_O);
		myMenuBar.add(myOptionsMenu);

		// thickness sub menu
		JMenu thickMenu = new JMenu("Thickness");
		thickMenu.setMnemonic(KeyEvent.VK_T);
		myOptionsMenu.add(thickMenu);
		setupThicknessSlider();
		thickMenu.add(myThicknessSlider);
		myThicknessSlider.addChangeListener(new SliderAdjuster());

		myOptionsMenu.addSeparator();

		// primary and secondary colors menu
		setupColorButtons();

		myOptionsMenu.addSeparator();

		// clear button is disabled until a shape is drawn
		myClearButton = new JMenuItem("Clear", KeyEvent.VK_C);
		myClearButton.setEnabled(false);
		myClearButton.addActionListener(new ClearHandler());

		// adds the clear function
		myOptionsMenu.add(myClearButton);
		myOptionsMenu.addSeparator();

		// undo option: Shorcut ctrl + z
		myUndoButton = new JMenuItem("Undo", KeyEvent.VK_Y);
		myUndoButton.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		myUndoButton.setEnabled(false);
		myUndoButton.addActionListener(new UndoAction());
		myOptionsMenu.add(myUndoButton);

		// redo option: Shortcut ctrl + y
		myRedoButton = new JMenuItem("Redo", KeyEvent.VK_Y);
		myRedoButton.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		myRedoButton.setEnabled(false);
		myRedoButton.addActionListener(new RedoAction());
		myOptionsMenu.add(myRedoButton);

	}

	/**
	 * Creates the thickness slider in the Options menu used to control the
	 * thickness of the line when drawing
	 */
	public void setupThicknessSlider() {
		myThicknessSlider = new JSlider(THICKNESS_MIN, THICKNESS_MAX,
				THICKNESS_INITIAL);
		myThicknessSlider.setMajorTickSpacing(MAJOR_SPACING);
		myThicknessSlider.setMinorTickSpacing(MINOR_SPACING);
		myThicknessSlider.setPaintTicks(true);
		myThicknessSlider.setPaintLabels(true);
	}

	/**
	 * Adds the Primary/Secondary Color item to Options with the custom icons
	 * and adds the listener for the color picker pop out
	 */
	public void setupColorButtons() {
		myPrimaryButton = new JMenuItem("Primary Color...", myColorIcon);
		myPrimaryButton.setMnemonic(KeyEvent.VK_P);
		myOptionsMenu.add(myPrimaryButton);

		mySecondaryButton = new JMenuItem("Secondary Color...", myColorIcon2);
		mySecondaryButton.setMnemonic(KeyEvent.VK_S);
		myOptionsMenu.add(mySecondaryButton);

		// listeners to open color swatch and assign new colors and icons
		myPrimaryButton.addActionListener(new ColorChoicePrimary());
		mySecondaryButton.addActionListener(new ColorChoiceSecondary());
	}

	/**
	 * Setups the menu tools dropdown with radio buttons
	 * 
	 */
	public void setupMenuTools() {
		myToolsMenu = new JMenu("Tools");
		myToolsMenu.setMnemonic(KeyEvent.VK_T);
		myMenuBar.add(myToolsMenu);

		// associate tool buttons with actions and add to toolbar
		for (final Action act : myToolActions) {
			JRadioButtonMenuItem rb = new JRadioButtonMenuItem(act);

			myToolMenuButtons.add(rb);
			myToolsMenu.add(rb);

			// selects the line tool at the start as default
			if (rb.getAction() == myDefaultAction) {
				rb.setSelected(true);
			}

		}

	}

	/**
	 * Setups help menu
	 * 
	 */
	public void setupMenuHelp() {
		myHelpMenu = new JMenu("Help");
		myHelpMenu.setMnemonic(KeyEvent.VK_H);
		myMenuBar.add(myHelpMenu);

		JMenuItem about = new JMenuItem("About...", KeyEvent.VK_A);
		myHelpMenu.add(about);
		about.addActionListener(new PopOutAction());
	}

	// inner classes/ event listeners

	// class to open color swatch for primary color. After user chooses color,
	// that color and icon is updated.
	class ColorChoicePrimary implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// gets the color choice from user input
			final Color colorChoice = JColorChooser.showDialog(null,
					"Select a color", DEFAULT_PRIMARY);

			// update primary colors based on input
			myColorIcon.setColor(colorChoice);

			// set the color for the jpanel tool
			if (colorChoice != null) {
				myPaintPanel.setColor(colorChoice);
			}
		}

	}

	// class to open color swatch for secondary color. After user chooses color,
	// that color and icon is updated.
	class ColorChoiceSecondary implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			final Color colorChoice = JColorChooser.showDialog(null,
					"Select a color", DEFAULT_SECONDARY);

			// update secondary colors based on input
			myColorIcon2.setColor(colorChoice);

			if (colorChoice != null) {
				myPaintPanel.setSecondaryColor(colorChoice);
			}
		}

	}

	// class to clear the shapes from the paint panel when the clear button is
	// enabled
	class ClearHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (myClearButton.isEnabled()) {

				myPaintPanel.clearShapes(); // clear stored info on panel

			}

		}

	}

	// class to undo the previous action when the button is enabled
	class UndoAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (myUndoButton.isEnabled()) {
				myPaintPanel.undo();
			}
		}
	}

	// class to redo an action that has been undone when the button is enabled
	class RedoAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (myRedoButton.isEnabled()) {

				myPaintPanel.redo();
			}
		}
	}

	// class to changed the thickness of the line when drawing
	private class SliderAdjuster implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider) e.getSource();
			if (!source.getValueIsAdjusting()) {
				int changeValue = (int) source.getValue();
				myPaintPanel.setThickness(changeValue);
			}
		}
	}

	// class to create a pop out when "About..." is clicked
	class PopOutAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// gets the image we want to use for our icon
			final ImageIcon icon = myImageIcon;
			// takes the image from the icon
			Image imgIcon = icon.getImage();
			// scales the image down
			Image newImgIcon = imgIcon.getScaledInstance(50, 35,
					java.awt.Image.SCALE_SMOOTH);
			// takes our scaled image and makes it into an icon
			final ImageIcon icon2 = new ImageIcon(newImgIcon);
			// creates the pop up window when "about" is clicked with our custom
			// title, message, and icon
			JOptionPane.showMessageDialog(null, ABOUT_MSG, "About",
					JOptionPane.INFORMATION_MESSAGE, icon2);
		}
	}

	// listener for when exit is chosen
	class ExitAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			myFrame.dispose();
			System.exit(0);

		}

	}

	// listens for changes for the Clear, Undo, and Redo buttons
	@Override
	public void propertyChange(PropertyChangeEvent theEvent) {

		// make sure the value is a boolean in case there are other properties
		if (theEvent.getNewValue() instanceof Boolean) {
			Boolean status = (boolean) theEvent.getNewValue();

			if (PROPERTY_HAS_SHAPE.equals(theEvent.getPropertyName())) {
				myClearButton.setEnabled(status);
				myUndoButton.setEnabled(status);
			}

			if (PROPERTY_SHAPE_REDO.equals(theEvent.getPropertyName())) {
				myRedoButton.setEnabled(status);
			}
		}

	}
}
