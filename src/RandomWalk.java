import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * This creates the RandomWalk class
 * 
 * @author Alex Esplin
 *
 */
public class RandomWalk {

	/* instance variables */
	ArrayList<Point> path = new ArrayList<Point>();
	Random rand = new Random();
	private int size;
	private boolean done = false;

	/* constructor */
	/**
	 * Constructor: creates a RandomWalk object with specified values.
	 * 
	 * @param gridSize:
	 *            the size of grid the walk occurs on
	 */
	public RandomWalk(int gridSize) {
		this.size = gridSize;
		Point start = new Point(0, gridSize - 1);
		this.path.add(start);

	}

	/**
	 * Constructor: creates a RandomWalk object with specified values.
	 * 
	 * @param gridSize:
	 *            the size of grid the walk occurs on
	 * @param seed:
	 *            seeds the random number generator
	 */
	public RandomWalk(int gridSize, long seed) {
		this.size = gridSize;
		Point start = new Point(0, gridSize - 1);
		this.path.add(start);
		this.rand.setSeed(seed);
	}

	/* other methods */

	/**
	 * Makes the walk go one step further, and adds that step to the ArrayList. It
	 * will add one step to either x or y. If the step is the final one it will flip
	 * the boolean flag.
	 * 
	 */
	public void step() {
		Point last = path.get(path.size() - 1);
		int lastX = last.x;
		int lastY = last.y;
		int randX = 0, randY = 0;

		if (lastX == size - 1) {
			randY = 1;
			randX = 0;
		} else if (lastY == 0) {
			randX = 1;
			randY = 0;
		} else if (lastX < size - 1 || lastY > 0) {
			if (rand.nextInt(2) == 1) {
				randX = 1;
				randY = 0;
			} else {
				randX = 0;
				randY = 1;
			}
		}

		path.add(new Point((lastX + randX), (lastY - randY)));

		if (path.contains(new Point(size - 1, 0))) {
			done = true;
		}

	}

	/**
	 * Creates the entire walk from the start of the grid to the end. It will
	 * continue to add steps until isDone is true
	 */
	public void createWalk() {
		while (this.isDone() != true) {
			this.step();
		}
	}

	/**
	 * Is a boolean value that is true if the walk is done, and false if it is not
	 * 
	 * @return boolean for if the walk is done
	 */
	public boolean isDone() {
		boolean isDone = done;
		return isDone;
	}

	/**
	 * Gets the path that is used for the walk.
	 * 
	 * @return all points in the ArrayList
	 */
	public ArrayList<Point> getPath() {

		return path;

	}

	/**
	 * This gives a nicely formatted list of all the points in the ArrayList
	 * 
	 * @return Points in ArrayList
	 */
	public String toString() {
		String result = "";
		for (int i = 0; i <= path.size() - 1; i++) {
			result += "[" + (int) path.get(i).getX() + "," + (int) path.get(i).getY() + "] ";
		}

		return result;
	}

}
