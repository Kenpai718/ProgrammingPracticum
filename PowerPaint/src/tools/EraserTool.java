package tools;

import java.awt.event.KeyEvent;

/**
 * Eraser tool is like the pencil tool but draws a white path to erase.
 * 
 * @author Kenneth Ahrens
 * @author Katlyn Malone
 * @version Fall 2020
 */

public class EraserTool extends PencilTool {

	/** Constant name for the tool */
	private static final String NAME = "Eraser";

	/** Constant mnemonic for the tool */
	private static final int MNEMONIC = KeyEvent.VK_A;

	/**
	 * Default constructor: defines eraser
	 * 
	 */
	public EraserTool() {
		super(NAME, MNEMONIC);
	}

}
