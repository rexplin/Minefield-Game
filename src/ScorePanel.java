import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Sets up the games score panel. The score panel is made of two JLabels, one
 * for the score, and one for the lives. Contains methods for getting the score
 * and lives. as well as losing and resetting them.
 * 
 * @author Alex Esplin
 *
 */
@SuppressWarnings("serial")
public class ScorePanel extends JPanel {
	// Instance Variables
	private JLabel scoreLabel;
	private int score;
	private JLabel livesLabel;
	private int lives;
	private String livesText;
	private String scoreText;
	private JLabel blankLabel;

	// Constructor
	public ScorePanel() {
		// Creates the score and lives label at the top
		this.setPreferredSize(new Dimension(50, 75));
		score = 500;
		scoreLabel = new JLabel("Score: " + score);
		scoreLabel.setFont(new Font("arial", Font.PLAIN, 50));
		add(scoreLabel);
		blankLabel = new JLabel("   ");
		add(blankLabel);
		lives = 5;
		livesLabel = new JLabel("Lives: " + lives);
		livesLabel.setFont(new Font("arial", Font.PLAIN, 50));
		add(livesLabel);
	}

	/**
	 * Checks if lives are already at zero. If not then it subtracts one from the
	 * lives and returns the String.
	 * 
	 * @return livesText. The text on the JLabel
	 */
	public String loseLife() {
		if (lives == 0) {
		} else {
			lives = lives - 1;
		}
		livesText = "Lives: " + lives;
		return livesText;
	}

	/**
	 * gets a reference to the JLabel called livesLabel.
	 * 
	 * @return livesLabel. A JLabel
	 */
	public JLabel getLives() {
		return livesLabel;
	}

	/**
	 * resets the lives back to 5. Returns the string.
	 * 
	 * @return livesText. The text on the JLabel
	 */
	public String resetLife() {
		lives = 5;
		livesText = "Lives: " + lives;
		return livesText;
	}

	public int getMyLives() {
		return lives;
	}

	/**
	 * gets a reference to the JLabel called scoreLabel
	 * 
	 * @return scoreLabel. A JLabel
	 */
	public JLabel getScore() {
		return scoreLabel;
	}

	/**
	 * checks if the score is at zero. If not it will subtract one from the score
	 * and return the string
	 * 
	 * @return scoreText. A string that shows the current score
	 */
	public String loseScore() {
		if (score == 0) {
		} else {
			score = score - 1;
		}
		scoreText = "Score: " + score;
		return scoreText;
	}

	/**
	 * checks if score is at zero. If not it will subtract 50 from the score and
	 * return the string.
	 * 
	 * @return scoreText. A string that shows current score
	 */
	public String hitMine() {
		if (score == 0) {
		} else {
			score = score - 50;
		}
		scoreText = "Score: " + score;
		return scoreText;
	}

	/**
	 * resets the score back to 500.
	 * 
	 * @return scoreText. A string that shows the current score
	 */
	public String resetScore() {
		score = 500;
		scoreText = "Score: " + score;
		return scoreText;
	}
}
