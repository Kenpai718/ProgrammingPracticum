package contoller;

import static model.PropertyChangeEnabledMutableColor.PROPERTY_GREEN;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.ColorModel;
import model.PropertyChangeEnabledMutableColor;

public abstract class ValueRowPanel extends JPanel
		implements PropertyChangeListener {
	
	
    /**  
     * A generated serial version UID for object Serialization. 
     * http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
     */
    private static final long serialVersionUID = 2284116355218892348L;
    
    /** The size of the increase/decrease buttons. */
    private static final Dimension BUTTON_SIZE = new Dimension(26, 26);
    
    /** The size of the text label. */
    private static final Dimension LABEL_SIZE = new Dimension(45, 26);
    
    /** The number of columns in width of the TextField. */
    private static final int TEXT_FIELD_COLUMNS = 3;
    
    /** The amount of padding for the change panel. */
    private static final int HORIZONTAL_PADDING = 30;
    
    /** The backing model for the system. */
    private final PropertyChangeEnabledMutableColor myColor;

    /** The CheckBox that enables/disables editing of the TextField. */
    private final JCheckBox myEnableEditButton;
    
    /** The TextField that allows the user to type input for the color value. */
    private final JTextField myValueField;
    
    /** The Button that when clicked increases the color value. */
    private final JButton myIncreaseButton;
    
    /** The Button that when clicked decreases the color value. */
    private final JButton myDecreaseButton;
    
    /** The Slider that when adjusted, changes the color value. */
    private final JSlider myValueSlider;
    
    /** The panel that visually displays ONLY the GREEN value for the color. */
    private final JPanel myColorDisplayPanel;
    
	/**
	 * String to store textbox input in case it needs to be restored
	 */
	private int myPreviousInput;
	
	private boolean myFocus;
	
	private String myName;

	public ValueRowPanel(final PropertyChangeEnabledMutableColor theColor, final String theName) {
	       super();
	        myColor = theColor;
	        myName = theName;
	        
	        myEnableEditButton = new JCheckBox("Enable edit");
	        myValueField = new JTextField();
	        myIncreaseButton = new JButton();
	        myDecreaseButton = new JButton();
	        myValueSlider = new JSlider();
	        myColorDisplayPanel = new JPanel();
	        
	        layoutComponents();
	        addListeners();
	        
	        myPreviousInput = getColorValue();
	}
	
    /**
     * Setup and add the GUI components for this panel. 
     */
    private void layoutComponents() {
        myColorDisplayPanel.setPreferredSize(BUTTON_SIZE);
        
        String labelTitle = myName + ": ";
        final JLabel rowLabel = new JLabel(labelTitle);
        
        
        if(myName.equals("Red")) {
        	myColorDisplayPanel.setBackground(new Color(0, myColor.getRed(), 0));
            myValueField.setText(String.valueOf(myColor.getRed()));
        } else if (myName.equals("Green")) {
        	myColorDisplayPanel.setBackground(new Color(0, myColor.getGreen(), 0));
            myValueField.setText(String.valueOf(myColor.getGreen()));
        } else if (myName.equals("Blue")) {
        	myColorDisplayPanel.setBackground(new Color(0, myColor.getBlue(), 0));
            myValueField.setText(String.valueOf(myColor.getBlue()));
        }

        rowLabel.setPreferredSize(LABEL_SIZE);
        myValueField.setEditable(false);
        myValueField.setColumns(TEXT_FIELD_COLUMNS);
        myValueField.setHorizontalAlignment(JTextField.RIGHT);
        
        final JPanel rightPanel = new JPanel();
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 
                                                             HORIZONTAL_PADDING, 
                                                             0, 
                                                             HORIZONTAL_PADDING));
        rightPanel.setBackground(rightPanel.getBackground().darker());
        myIncreaseButton.setIcon(new ImageIcon("./images/ic_increase_value.png"));
        myIncreaseButton.setPreferredSize(BUTTON_SIZE);
        
        myValueSlider.setMaximum(ColorModel.MAX_VALUE);
        myValueSlider.setMinimum(ColorModel.MIN_VALUE);
        myValueSlider.setValue(getColorValue());
        myValueSlider.setBackground(rightPanel.getBackground());
        
        myDecreaseButton.setIcon(new ImageIcon("./images/ic_decrease_value.png"));
        myDecreaseButton.setPreferredSize(BUTTON_SIZE);
        myDecreaseButton.setEnabled(false);
        
        rightPanel.add(myDecreaseButton);
        rightPanel.add(myValueSlider);
        rightPanel.add(myIncreaseButton);
        
        add(myColorDisplayPanel);
        add(rowLabel);
        add(myValueField);
        add(myEnableEditButton);
        add(rightPanel);

    }
    
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_GREEN.equals(theEvent.getPropertyName())) {
            myValueField.setText(theEvent.getNewValue().toString());
            myValueSlider.setValue((Integer) theEvent.getNewValue());
            myColorDisplayPanel.
                setBackground(new Color(0, (Integer) theEvent.getNewValue(), 0));
        }
        
    }
    
  

	/**
	 * Add listeners (event handlers) to any GUI components that require
	 * handling.
	 */
	private void addListeners() {
		// DO not remove the following statement.
		myColor.addPropertyChangeListener(this);

		// action listeners for buttons, textbox and slider
		myEnableEditButton.addActionListener(new EnableValueField());
		myIncreaseButton.addActionListener(new ColorIncrease());
		myDecreaseButton.addActionListener(new ColorDecrease());
		myValueSlider.addChangeListener(new SliderAdjuster());
		myValueField.addActionListener(new GrabText());

	}
	
	private int getColorValue() {
		int value = 0;
		
		if(myName.equals("Red")) {
			value = myColor.getRed();
		} else if((myName.equals("Green"))) {
			value = myColor.getGreen();
		} else if((myName.equals("Blue"))) {
			value = myColor.getBlue();
		}
		return value;
	}

	private void setColorValue(int theValue) {
		if(myName.equals("Red")) {
			myColor.setRed(theValue);
		} else if((myName.equals("Green"))) {
			myColor.setGreen(theValue);
		} else if((myName.equals("Blue"))) {
			myColor.setBlue(theValue);
		}
	}
	
	/**
	 * Private helper method to check if a passed in value is within the bounds
	 * of a color range.
	 * 
	 * @param theNum int value to be checked
	 * @return boolean if value is within bound (true) or not (false)
	 */
	private boolean withinHexRange(int theNum) {
		boolean result = false;

		// within 0-255
		if (theNum >= ColorModel.MIN_VALUE && theNum <= ColorModel.MAX_VALUE) {
			result = true;
		}
		return result;
	}

	/**
	 * Private helper method to disable or enable increment or decrement buttons
	 * everytime the value of the color is changed.
	 * 
	 * @param theNum int value to be checked
	 * @return boolean if value is within bound (true) or not (false)
	 */
	private void updateButtons(int theNum) {

		// decrement button
		if (theNum == ColorModel.MIN_VALUE) {
			myDecreaseButton.setEnabled(false);
		} else {
			myDecreaseButton.setEnabled(true);
		}

		// increment button
		if (theNum == ColorModel.MAX_VALUE) {
			myIncreaseButton.setEnabled(false);
		} else {
			myIncreaseButton.setEnabled(true);
		}
	}

	/**
	 * Private helper method to disable or enable increment or decrement buttons
	 * everytime the value of the color is changed.
	 * 
	 * @param theNum int value to be checked
	 * @return boolean if value is within bound (true) or not (false)
	 */
	private void inputColorValue() {

		final String input = myValueField.getText().trim();
		final int num;

		// try converting input into a integer
		try {
			num = Integer.parseInt(input);

			// check if between 0-255. If so set the value.
			if (withinHexRange(num)) {
				// dont't set color again if it's the same value as before
				if (num != myPreviousInput) {
					setColorValue(num);
					myPreviousInput = num;
				}

			} else {
				myValueField.setText(String.valueOf(myPreviousInput));
			}

			// exception when non integer is passed in
		} catch (NumberFormatException ne) {
			myValueField.setText(String.valueOf(myPreviousInput));
			System.out.println(
					"Your input is invalid, please try again with an integer.");
		}

	}
	
	/**
	 * Event handler for the textbox input
	 */
	class GrabText implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent theEvent) {
			if (myFocus) {
				inputColorValue();
			}

		}

	}

	/**
	 * Focus Event handler for the textbox input. Checks when focus is lost on
	 * textbox.
	 */
	class FocusText implements FocusListener {

		@Override
		public void focusGained(FocusEvent theFocus) {
			myFocus = true;
			// System.out.println("Focus gain");
		}

		@Override
		public void focusLost(FocusEvent theFocus) {
			myFocus = false;
			// System.out.println("Focus lost");

			if (myIncreaseButton.isSelected() == false
					|| myDecreaseButton.isSelected() == false) {
				inputColorValue();
			} else {
				myValueField.setText(String.valueOf(myPreviousInput));
			}
		}

	}

	/**
	 * Event handler for the checkbox that enables/disables textbox
	 */
	class EnableValueField implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent theEvent) {

			// enable the textbox when checkbox is checked
			if (myEnableEditButton.isSelected()) {
				myValueField.setEditable(true);
			} else {
				myValueField.setEditable(false);
			}

		}

	}

	/**
	 * Event handler for slider that sets value of color
	 */
	class SliderAdjuster implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent theEvent) {

			int sliderValue = myValueSlider.getValue();

			// set textbox to slider value
			myValueField.setText(String.valueOf(sliderValue));
			myPreviousInput = sliderValue;

			// update color to value of slider and update button status
			setColorValue(sliderValue);
			updateButtons(sliderValue);

		}

	}

	/**
	 * Event handler for the button that increments by 1
	 */
	class ColorIncrease implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent theEvent) {

			final String input = myValueField.getText().trim();
			final int num = Integer.parseInt(input);
			updateButtons(num); // when max value, button is disabled

			// only enters when value < 255
			if (myIncreaseButton.isEnabled()) {

				final int increasedNum = num + 1;
				setColorValue(increasedNum);
				myPreviousInput = increasedNum;
				myValueField.setText(String.valueOf(increasedNum));
				myValueSlider.setValue(increasedNum);

			}
		}

	}

	/**
	 * Event handler for the button that decrements by 1
	 */
	class ColorDecrease implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent theEvent) {

			final String input = myValueField.getText().trim();
			final int num = Integer.parseInt(input);
			updateButtons(num); // when min value, button is disabled

			if (myDecreaseButton.isEnabled()) {
				final int decreasedNum = num - 1;
				setColorValue(decreasedNum);
				myPreviousInput = decreasedNum;
				myValueField.setText(String.valueOf(decreasedNum));
				myValueSlider.setValue(decreasedNum);
			}
		}

	}

}
