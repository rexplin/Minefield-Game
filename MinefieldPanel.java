import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JPanel;

/**
 * Class to create a MinefieldPanel. It adds all the MinefieldSquares(JButtons)
 * to an array. Determines what the safe path is, and then randomly adds mines
 * to the rest of the field.
 * 
 * @author Alex Esplin
 *
 */
@SuppressWarnings("serial")
public class MinefieldPanel extends JPanel {
	// instance variables
	private MinefieldSquare[][] field;
	private RandomWalk walk;
	private Random gen;
	private int chance;
	private int size = 1000;

	// Constructor
	public MinefieldPanel(int width, int height) {
		// sets the layout of the panel
		this.setLayout(new GridLayout(width, height));
		this.setPreferredSize(new Dimension(size, size));
		// creates the safe path through the field
		walk = new RandomWalk(width);
		walk.createWalk();
		// initializes the MinefieldSquare array and fills it with MinefieldSquares
		field = new MinefieldSquare[width][height];
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				field[i][j] = new MinefieldSquare();
				this.add(field[i][j].getButton());
			}
		}
		// runs through the field and add mines
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				gen = new Random();
				chance = gen.nextInt(10);
				if (chance < 3) {
					field[i][j].setMine();
				}
			}
		}
		// removes mines in the safe path
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				for (int k = 0; k < walk.getPath().size(); k++) {
					if (i == walk.getPath().get(k).getX() && j == walk.getPath().get(k).getY()) {
						field[i][j].notMine();
					}
				}
			}
		}
	}

	/**
	 * The Minefield created and filled in the constructor.
	 * 
	 * @return Double array- field.
	 */
	public MinefieldSquare[][] getField() {
		return field;
	}

	/**
	 * gets a reference to the RandomWalk
	 * 
	 * @return the RandomWalk created
	 */
	public RandomWalk getWalk() {
		return walk;
	}

	/**
	 * First it runs through the field and removes all the mines from it. It then
	 * goes through again and randomly adds some back in. Finally it removes any
	 * mines that have been placed in the safe path through.
	 */
	public void resetMines() {
		// runs through the field and removes all mines
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				field[i][j].notMine();
			}
		}
		// runs through the field and adds mines back in
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				gen = new Random();
				chance = gen.nextInt(10);
				if (chance < 3) {
					field[i][j].setMine();
				}
			}
		}
		// removes mines in the safe path
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				for (int k = 0; k < walk.getPath().size(); k++) {
					if (i == walk.getPath().get(k).getX() && j == walk.getPath().get(k).getY()) {
						field[i][j].notMine();
					}
				}
			}
		}
	}

	/**
	 * creates a new safe path through the minefield
	 */
	public void resetWalk() {
		walk = new RandomWalk(field.length);
		walk.createWalk();
	}

	/**
	 * gets the preferred size of the grid
	 * 
	 * @return size. Int
	 */
	public int getMySize() {
		return size;
	}
}
