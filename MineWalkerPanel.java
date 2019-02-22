import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

/**
 * The main panel that the driver class will create. All other panels are added
 * to this one.
 * 
 * @author Alex Esplin
 *
 */
@SuppressWarnings("serial")
public class MineWalkerPanel extends JPanel {
	// instance variables
	private ScorePanel myScore;
	private MinefieldPanel myMinefield;
	private ControlButtonsPanel myControl;
	private GameKeyPanel myKey;
	private int row;
	private int col;
	private final Color[] COLORS = new Color[] { Color.BLACK, Color.CYAN };
	private int colorIndex = 0;
	private Timer timer;

	// Constructor
	public MineWalkerPanel(int width, int height) {
		// creates a layout for this panel
		setLayout(new BorderLayout());
		// creates new ScorePanel and adds it to the north part of the BorderLayout
		myScore = new ScorePanel();
		add(myScore, BorderLayout.NORTH);
		// creates a new MinefieldPanel and adds it to the center of the BorderLayout
		myMinefield = new MinefieldPanel(width, height);
		// Adds the listener to each button in the mine field
		for (int i = 0; i < myMinefield.getField().length; i++) {
			for (int j = 0; j < myMinefield.getField()[i].length; j++) {
				myMinefield.getField()[i][j].getButton().addActionListener(new ButtonListener());
			}
		}
		// sets the color and position of the starting JButton
		row = myMinefield.getField().length - 1;
		col = 0;
		myMinefield.getField()[myMinefield.getField().length - 1][0].getButton().setBackground(Color.CYAN);
		myMinefield.getField()[0][myMinefield.getField().length - 1].getButton().setBackground(Color.MAGENTA);
		add(myMinefield, BorderLayout.CENTER);
		// creates a new ControlButtonsPanel, adds a listener to all buttons within and
		// adds to MineWalkerPanel in the South part of the BorderLayout
		myControl = new ControlButtonsPanel();
		myControl.getPath().addActionListener(new ControlListener());
		myControl.getMines().addActionListener(new ControlListener());
		myControl.getReset().addActionListener(new ControlListener());
		myControl.getResize().addActionListener(new ControlListener());
		add(myControl, BorderLayout.SOUTH);
		// creates a new GameKeyPanel and adds it to the West part of the BorderLayout
		myKey = new GameKeyPanel();
		add(myKey, BorderLayout.WEST);
		startAnimation();
	}

	/**
	 * Performs action when timer event fires. Sets the border to alternate from
	 * Black to Cyan.
	 */
	private class TimerActionListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			myMinefield.getField()[row][col].getButton().setBorder(new LineBorder(COLORS[colorIndex], 4));
			myMinefield.getField()[row][col].getButton().setBorderPainted(true);
			if (colorIndex >= 1) {
				colorIndex = 0;
			} else {
				colorIndex++;
			}
		}
	}

	/**
	 * Create an animation thread that runs periodically
	 */
	private void startAnimation() {
		TimerActionListener taskPerformer = new TimerActionListener();
		timer = new Timer(400, taskPerformer);
		timer.start();
	}

	/**
	 * Listener for the buttons created in ControlButtonsPanel. Determines which of
	 * the buttons was pressed and performs actions based on that
	 * 
	 * @author Alex Esplin
	 *
	 */
	private class ControlListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// Checks if the resize button was pressed
			if (e.getSource() == myControl.getResize()) {
				// removes the old mine field, then creates a new one based on the value from
				// the
				// slider. Adds the new one to the panel, and adds new listeners
				remove(myMinefield);
				myMinefield = new MinefieldPanel(myControl.getSlider().getValue(), myControl.getSlider().getValue());
				myMinefield.setVisible(true);
				for (int i = 0; i < myMinefield.getField().length; i++) {
					for (int j = 0; j < myMinefield.getField()[i].length; j++) {
						myMinefield.getField()[i][j].getButton().addActionListener(new ButtonListener());
					}
				}
				// resets the button back to the first one
				row = myMinefield.getField().length - 1;
				col = 0;
				// resets the text
				myControl.getMines().setText(myControl.showMines());
				myControl.getPath().setText(myControl.showPath());
				myScore.getLives().setText(myScore.resetLife());
				myControl.getReset().setText("Give Up");
				myScore.getScore().setText(myScore.resetScore());
				// resets the field back to black with all mines hidden and resets the borders
				for (int i = 0; i < myMinefield.getField().length; i++) {
					for (int j = 0; j < myMinefield.getField()[i].length; j++) {
						myMinefield.getField()[i][j].getButton().setBackground(Color.black);
						myMinefield.getField()[i][j].getButton().setText("");
						myMinefield.getField()[i][j].getButton().setBorder(new LineBorder(Color.gray));
					}

				}
				// sets the color of the start and end points
				myMinefield.getField()[myMinefield.getField().length - 1][0].getButton().setBackground(Color.CYAN);
				myMinefield.getField()[0][myMinefield.getField().length - 1].getButton().setBackground(Color.MAGENTA);
				// creates a new path and new mine locations
				myMinefield.resetWalk();
				myMinefield.resetMines();
				timer.stop();
				startAnimation();
				add(myMinefield);

			}
			// check if the path button was pressed
			if (e.getSource() == myControl.getPath()) {
				// check if the button currently says show path
				if (myControl.getPath().getText() == myControl.showPath()) {
					// change text to hide path
					myControl.getPath().setText(myControl.hidePath());
					// shows the path
					for (int i = 0; i < myMinefield.getField().length; i++) {
						for (int j = 0; j < myMinefield.getField()[i].length; j++) {
							for (int k = 0; k < myMinefield.getWalk().getPath().size(); k++) {
								if (i == myMinefield.getWalk().getPath().get(k).getX()
										&& j == myMinefield.getWalk().getPath().get(k).getY()) {
									myMinefield.getField()[i][j].getButton().setForeground(Color.MAGENTA);
									myMinefield.getField()[i][j].getButton().setText("Safe");
									;
								}
							}
						}
					}
				} else {
					myControl.getPath().setText(myControl.showPath());
					// hide the path
					for (int i = 0; i < myMinefield.getField().length; i++) {
						for (int j = 0; j < myMinefield.getField()[i].length; j++) {
							for (int k = 0; k < myMinefield.getWalk().getPath().size(); k++) {
								if (i == myMinefield.getWalk().getPath().get(k).getX()
										&& j == myMinefield.getWalk().getPath().get(k).getY()) {
									myMinefield.getField()[i][j].getButton().setText("");
									;
								}
							}
						}
					}
				}
			}
			// checks if the mines button was pressed
			if (e.getSource() == myControl.getMines()) {
				// checks if the current text is show mines.
				if (myControl.getMines().getText() == myControl.showMines()) {
					// changes text to hide mines
					myControl.getMines().setText(myControl.hideMines());
					// shows the mines
					for (int i = 0; i < myMinefield.getField().length; i++) {
						for (int j = 0; j < myMinefield.getField()[i].length; j++) {
							// checks if a button is a mine, if true it sets text to MINE
							if (myMinefield.getField()[i][j].isMine() == true) {
								myMinefield.getField()[i][j].getButton().setForeground(Color.RED);
								myMinefield.getField()[i][j].getButton().setText("MINE");
							}
						}
					}
				} else {
					// changes text to show mines
					myControl.getMines().setText(myControl.showMines());
					// hides the mines
					for (int i = 0; i < myMinefield.getField().length; i++) {
						for (int j = 0; j < myMinefield.getField()[i].length; j++) {
							if (myMinefield.getField()[i][j].isMine() == true) {
								myMinefield.getField()[i][j].getButton().setText("");

							}

						}
					}
				}
			}
			// checks if the button pressed was the reset button
			if (e.getSource() == myControl.getReset()) {
				// resets the button back to the first one
				row = myMinefield.getField().length - 1;
				col = 0;
				// resets the text
				myControl.getMines().setText(myControl.showMines());
				myControl.getPath().setText(myControl.showPath());
				myScore.getLives().setText(myScore.resetLife());
				myControl.getReset().setText("Give Up");
				myScore.getScore().setText(myScore.resetScore());
				// resets the field back to black with all mines hidden and resets the borders
				for (int i = 0; i < myMinefield.getField().length; i++) {
					for (int j = 0; j < myMinefield.getField()[i].length; j++) {
						myMinefield.getField()[i][j].getButton().setBackground(Color.black);
						myMinefield.getField()[i][j].getButton().setText("");
						myMinefield.getField()[i][j].getButton().setBorder(new LineBorder(Color.gray));
					}

				}
				// sets the color of the start and end points
				myMinefield.getField()[myMinefield.getField().length - 1][0].getButton().setBackground(Color.CYAN);
				myMinefield.getField()[0][myMinefield.getField().length - 1].getButton().setBackground(Color.MAGENTA);
				// creates a new path and new mine locations
				myMinefield.resetWalk();
				myMinefield.resetMines();
				timer.stop();
				startAnimation();
			}
		}

	}

	/**
	 * creates the listener that will be added to each button. It checks to see
	 * which button was pushed, and then if its a mine or not.
	 * 
	 * @author Alex Esplin
	 *
	 */
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < myMinefield.getField().length; i++) {
				for (int j = 0; j < myMinefield.getField()[i].length; j++) {
					if (e.getSource() == myMinefield.getField()[i][j].getButton()) {
						// check if what you pressed is within one space
						if ((col < myMinefield.getField().length - 1
								&& myMinefield.getField()[i][j] == myMinefield.getField()[row][col + 1])
								|| (row > 0 && myMinefield.getField()[i][j] == myMinefield.getField()[row - 1][col])
								|| (row < myMinefield.getField().length - 1
										&& myMinefield.getField()[i][j] == myMinefield.getField()[row + 1][col])
								|| (col > 0 && myMinefield.getField()[i][j] == myMinefield.getField()[row][col - 1])) {
							// check if the button is a mine. if so it sets color to gray, you lose a life
							// and some score
							if (myMinefield.getField()[i][j].isMine() == true) {
								myMinefield.getField()[i][j].getButton().setBackground(Color.GRAY);
								myScore.getLives().setText(myScore.loseLife());
								myScore.getScore().setText(myScore.hitMine());
							}
							// check if its not a mine. if not it sees how many are nearby and changes
							// background color. Also makes it the current button
							if (myMinefield.getField()[i][j].isMine() == false) {
								// checks how many mines are nearby
								int nearbyMine = 0;
								if (i + 1 < myMinefield.getField().length
										&& myMinefield.getField()[i + 1][j].isMine() == true) {
									nearbyMine += 1;
								}
								if (i - 1 > 0 && myMinefield.getField()[i - 1][j].isMine() == true) {
									nearbyMine += 1;
								}
								if (j + 1 < myMinefield.getField().length
										&& myMinefield.getField()[i][j + 1].isMine() == true) {
									nearbyMine += 1;
								}
								if (j - 1 > 0 && myMinefield.getField()[i][j - 1].isMine() == true) {
									nearbyMine += 1;
								}
								// based on how many nearby mines it decides what color to set background
								if (nearbyMine == 0) {
									myMinefield.getField()[i][j].getButton().setBackground(Color.green);
								}
								if (nearbyMine == 1) {
									myMinefield.getField()[i][j].getButton().setBackground(Color.yellow);
								}
								if (nearbyMine == 2) {
									myMinefield.getField()[i][j].getButton().setBackground(Color.orange);
								}
								if (nearbyMine == 3) {
									myMinefield.getField()[i][j].getButton().setBackground(Color.red);
								}
								// sets the row and column for the current button to the one just pressed
								row = i;
								col = j;
								// drops your score each time you move
								myScore.getScore().setText(myScore.loseScore());

							}
							// Check if you reached the goal
							if (myMinefield.getField()[i][j] == myMinefield.getField()[0][myMinefield.getField().length
									- 1]) {
								myMinefield.getField()[0][myMinefield.getField().length - 1].getButton()
										.setBackground(Color.MAGENTA);
								// changes reset button text to new game
								myControl.getReset().setText("New Game");
								// pops up a window saying you won
								JOptionPane.showMessageDialog(null, "You Win! " + myScore.getScore().getText(),
										"Congratulations!", JOptionPane.INFORMATION_MESSAGE);
							}
							// Checks if you have run out of lives
							if (myScore.getMyLives() == 0) {
								// pops up a you lose message
								JOptionPane.showMessageDialog(null, "You have Lost...", "FAIL",
										JOptionPane.WARNING_MESSAGE);
								// resets the button back to the first one
								row = myMinefield.getField().length - 1;
								col = 0;
								// resets the text
								myControl.getMines().setText(myControl.showMines());
								myControl.getPath().setText(myControl.showPath());
								myScore.getLives().setText(myScore.resetLife());
								myControl.getReset().setText("Give Up");
								myScore.getScore().setText(myScore.resetScore());
								// resets the field back to black with all mines hidden and resets the borders
								for (i = 0; i < myMinefield.getField().length; i++) {
									for (j = 0; j < myMinefield.getField()[i].length; j++) {
										myMinefield.getField()[i][j].getButton().setBackground(Color.black);
										myMinefield.getField()[i][j].getButton().setText("");
										myMinefield.getField()[i][j].getButton().setBorder(new LineBorder(Color.gray));
									}

								}
								// sets the color of the start and end points
								myMinefield.getField()[myMinefield.getField().length - 1][0].getButton()
										.setBackground(Color.CYAN);
								myMinefield.getField()[0][myMinefield.getField().length - 1].getButton()
										.setBackground(Color.MAGENTA);
								// creates a new path and new mine locations
								myMinefield.resetWalk();
								myMinefield.resetMines();
								i = 0;
								j = 0;
								timer.stop();
								startAnimation();
							}
						}

					}
				}
			}
		}
	}
}
