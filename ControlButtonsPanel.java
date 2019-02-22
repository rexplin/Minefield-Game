import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 * Creates a panel. Adds to the panel a variety of buttons that will be used to
 * control the game. It adds a reset button, a show/hide mines button, a
 * show/hide path button, a resize button, and a slider from 10-25.
 * 
 * @author Alex Esplin
 *
 */
@SuppressWarnings("serial")
public class ControlButtonsPanel extends JPanel {
	// Instance variables
	private JButton path;
	private JButton mines;
	private JButton reset;
	private JSlider sizeSlider;
	private JButton resize;
	private String showPath = "Show Path";
	private String hidePath = "Hide Path";
	private String showMine = "Show Mines";
	private String hideMine = "Hide Mines";

	// Constructor
	public ControlButtonsPanel() {

		path = new JButton(showPath);

		mines = new JButton(showMine);

		reset = new JButton("Give up");
		// creates a slider that starts at 10 and goes to 25. Sets labeled Ticks every
		// 5, and unlabeled ticks every 1
		sizeSlider = new JSlider(10, 25, 10);
		sizeSlider.setMajorTickSpacing(5);
		sizeSlider.setMinorTickSpacing(1);
		sizeSlider.setPaintTicks(true);
		sizeSlider.setPaintLabels(true);
		resize = new JButton("Resize");
		// Adds the four buttons to the panel and one slider
		add(path);
		add(mines);
		add(reset);
		add(sizeSlider);
		add(resize);
	}

	/**
	 * returns a reference to the resize JButton
	 * 
	 * @return go. JButton
	 */
	public JButton getResize() {
		return resize;
	}

	/**
	 * returns a reference to the path JButton
	 * 
	 * @return path. JButton
	 */
	public JButton getPath() {
		return path;
	}

	/**
	 * returns a reference to the path JButton
	 * 
	 * @return mines. JButton
	 */
	public JButton getMines() {
		return mines;
	}

	/**
	 * returns a reference to the reset JButton
	 * 
	 * @return reset. JButton
	 */
	public JButton getReset() {
		return reset;
	}

	/**
	 * "Show Path"
	 * 
	 * @return showPath. String
	 */
	public String showPath() {
		return showPath;
	}

	/**
	 * "Hide Path"
	 * 
	 * @return hidePath. String
	 */
	public String hidePath() {
		return hidePath;
	}

	/**
	 * "Show Mines"
	 * 
	 * @return showMine. String
	 */
	public String showMines() {
		return showMine;
	}

	/**
	 * "Hide Mines"
	 * 
	 * @return hideMine. String
	 */
	public String hideMines() {
		return hideMine;
	}

	/**
	 * returns a reference to the slider
	 * 
	 * @return sizeSlider. JSlider
	 */
	public JSlider getSlider() {
		return sizeSlider;
	}
}
