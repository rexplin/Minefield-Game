import javax.swing.JFrame;

/**
 * Driver class. Creates the JFrame. Creates the MineWalkerPanel, which calls
 * and creates the rest of the game. Sets as visible.
 * 
 * @author Alex Esplin
 *
 */
public class MineWalker {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Mine Walker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MineWalkerPanel mineWalker = new MineWalkerPanel(10, 10);
		frame.getContentPane().add(mineWalker);
		frame.pack();
		frame.setVisible(true);
	}

}
