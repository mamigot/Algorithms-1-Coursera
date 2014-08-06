package patterns_hw3;

import java.util.Comparator;

/**
 * Built on top of: http://coursera.cs.princeton.edu/algs4/checklists/Point.java
 * which provides the constructor, draw(), drawTo() and toString().
 */
public class Point implements Comparable<Point> {

	// YOUR DEFINITION HERE
	public final Comparator<Point> SLOPE_ORDER = null;

	private final int x;
	private final int y;

	// create the point (x, y)
	public Point(int x, int y) {
		/* DO NOT MODIFY */
		this.x = x;
		this.y = y;

	}

	// plot this point to standard drawing
	public void draw() {
		/* DO NOT MODIFY */
		StdDraw.point(x, y);

	}

	// draw line between this point and that point to standard drawing
	public void drawTo(Point that) {
		/* DO NOT MODIFY */
		StdDraw.line(this.x, this.y, that.x, that.y);

	}

	// slope between this point and that point
	public double slopeTo(Point that) {
		/* YOUR CODE HERE */
		return 0.0;

	}

	public int compareTo(Point o) {
		// TODO Auto-generated method stub
		return 0;

	}

	// return string representation of this point
	@Override
	public String toString() {
		/* DO NOT MODIFY */
		return "(" + x + ", " + y + ")";

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
