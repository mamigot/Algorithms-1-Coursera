package patterns_hw3;

import java.util.Comparator;

import edu.princeton.cs.introcs.StdDraw;

/**
 * Built on top of: http://coursera.cs.princeton.edu/algs4/checklists/Point.java
 * which provides the constructor, draw(), drawTo() and toString().
 */
public class Point implements Comparable<Point> {

	private class SlopeOrder implements Comparator<Point> {

		// The SLOPE_ORDER comparator should compare points by the slopes they
		// make with the invoking point (x0, y0). Formally, the point (x1, y1)
		// is less than the point (x2, y2) if and only if the slope (y1 - y0) /
		// (x1 − x0) is less than the slope (y2 − y0) / (x2 − x0). Treat
		// horizontal, vertical, and degenerate line segments as in the
		// slopeTo() method
		public int compare(Point p1, Point p2) {
			double slope_p1 = Point.this.slopeTo(p1);
			double slope_p2 = Point.this.slopeTo(p2);

			if (slope_p1 < slope_p2)
				return -1;
			else if (slope_p1 > slope_p2)
				return 1;
			else
				return 0;

		}

	}

	// YOUR DEFINITION HERE
	public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();

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
		if (that.y == this.y && that.x == this.x)
			return Double.NEGATIVE_INFINITY;
		if (that.x == this.x)
			return Double.POSITIVE_INFINITY;
		if (that.y == this.y)
			return 0;

		else
			return (that.y - this.y) / (that.x - this.x);

	}

	// Avoid "strange" behavior by syncing it with compareTo()
	// http://docs.oracle.com/javase/7/docs/api/java/util/Comparator.html
	public boolean equals(Point that) {
		if (this.y == that.y && this.x == that.x)
			return true;
		else
			return false;

	}

	// Compare points by their y-coordinates, breaking ties by their
	// x-coordinates
	public int compareTo(Point that) {
		if (this.y > that.y)
			return 1;

		if (this.y < that.y)
			return -1;

		if (this.x > that.x)
			return 1;

		if (this.x < that.x)
			return -1;

		else
			return 0;

	}

	// return string representation of this point
	@Override
	public String toString() {
		/* DO NOT MODIFY */
		return "(" + x + ", " + y + ")";

	}

	public static void main(String[] args) {

		int xmike = 5;
		int ymike = 4;
		Point mike = new Point(xmike, ymike);

		int xbob = 2;
		int ybob = 5;
		Point bob = new Point(xbob, ybob);

		int res = mike.compareTo(bob);
		if (res == 1)
			System.out.println("mike is greater");
		else if (res == -1)
			System.out.println("bob is greater");
		else if (res == 0)
			System.out.println("they are equal");
		else
			System.out.println("what.");

	}
}
