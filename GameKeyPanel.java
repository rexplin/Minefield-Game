import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * creates the game key by adding a bunch of panels to the game key panel. it
 * sets the panels to the colors that they represent in the game. Adds a JLabel
 * description
 * 
 * @author Alex Esplin
 *
 */
@SuppressWarnings("serial")
public class GameKeyPanel extends JPanel {

	private JPanel startPanel;
	private JPanel goalPanel;
	private JPanel safePanel;
	private JPanel moderatePanel;
	private JPanel dangerPanel;
	private JPanel doomPanel;
	private JPanel minePanel;
	private JLabel startLabel;
	private JLabel goalLabel;
	private JLabel safeLabel;
	private JLabel moderateLabel;
	private JLabel dangerLabel;
	private JLabel doomLabel;
	private JLabel mineLabel;

	// Constructor
	public GameKeyPanel() {
		// sets the preferred size of the game key
		this.setPreferredSize(new Dimension(300, 300));
		// creates a box layout to order the panels vertically
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		// creates the start panel
		startPanel = new JPanel();
		startPanel.setBackground(Color.cyan);
		startPanel.setLayout(new BorderLayout());
		startLabel = new JLabel("   Start");
		startLabel.setFont(new Font("arial", Font.PLAIN, 30));
		startPanel.add(startLabel, BorderLayout.CENTER);
		add(startPanel);
		// creates the goal panel
		goalPanel = new JPanel();
		goalPanel.setBackground(Color.MAGENTA);
		goalPanel.setLayout(new BorderLayout());
		goalLabel = new JLabel("   Goal");
		goalLabel.setFont(new Font("arial", Font.PLAIN, 30));
		goalPanel.add(goalLabel, BorderLayout.CENTER);
		add(goalPanel);
		// creates the safe panel
		safePanel = new JPanel();
		safePanel.setBackground(Color.GREEN);
		safePanel.setLayout(new BorderLayout());
		safeLabel = new JLabel("   0 Nearby Mines");
		safeLabel.setFont(new Font("arial", Font.PLAIN, 30));
		safePanel.add(safeLabel, BorderLayout.CENTER);
		add(safePanel);
		// creates the moderate panel
		moderatePanel = new JPanel();
		moderatePanel.setBackground(Color.YELLOW);
		moderatePanel.setLayout(new BorderLayout());
		moderateLabel = new JLabel("   1 Nearby Mine");
		moderateLabel.setFont(new Font("arial", Font.PLAIN, 30));
		moderatePanel.add(moderateLabel, BorderLayout.CENTER);
		add(moderatePanel);
		// creates the danger panel
		dangerPanel = new JPanel();
		dangerPanel.setBackground(Color.orange);
		dangerPanel.setLayout(new BorderLayout());
		dangerLabel = new JLabel("   2 Nearby Mines");
		dangerLabel.setFont(new Font("arial", Font.PLAIN, 30));
		dangerPanel.add(dangerLabel, BorderLayout.CENTER);
		add(dangerPanel);
		// creates the doom panel
		doomPanel = new JPanel();
		doomPanel.setBackground(Color.red);
		doomPanel.setLayout(new BorderLayout());
		doomLabel = new JLabel("   3 Nearby Mines");
		doomLabel.setFont(new Font("arial", Font.PLAIN, 30));
		doomPanel.add(doomLabel, BorderLayout.CENTER);
		add(doomPanel);
		// creates the mine panel
		minePanel = new JPanel();
		minePanel.setBackground(Color.GRAY);
		minePanel.setLayout(new BorderLayout());
		mineLabel = new JLabel("   Exploded Mine");
		mineLabel.setFont(new Font("arial", Font.PLAIN, 30));
		minePanel.add(mineLabel, BorderLayout.CENTER);
		add(minePanel);

	}
}
