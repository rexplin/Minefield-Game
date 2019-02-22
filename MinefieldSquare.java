import java.awt.Color;

import javax.swing.JButton;

/**
 * class for the MinefieldSquare, which are the individual JButtons that make up
 * the Minefield. It sets them all to black and not a mine initially.
 * 
 * @author Alex Esplin
 *
 */
public class MinefieldSquare {
	// instance variables
	private JButton square;
	private boolean mine;

	// Constructor
	public MinefieldSquare() {
		square = new JButton();
		square.setBackground(Color.BLACK);
		mine = false;
	}

	/**
	 * returns a reference to the JButton object created.
	 * 
	 * @return JButton
	 */
	public JButton getButton() {
		return square;
	}

	/**
	 * sets the JButton as a mine. Boolean value true.
	 */
	public void setMine() {
		mine = true;
	}

	/**
	 * sets the JButton as not a mine. Boolean value false.
	 */
	public void notMine() {
		mine = false;
	}

	/**
	 * checks if the JButton is a mine or not
	 * 
	 * @return boolean. True if mine. False if not.
	 */
	public boolean isMine() {
		return mine;
	}

}
