package patterns_hw3;

import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.introcs.In;

/**
 * Examines 4 points at a time and checks whether they all lie on the same line
 * segment, printing out any such line segments to standard output and drawing
 * them using standard drawing.
 */
public class Brute {

	private static List<Point> readPoints(String filename) {
		List<Point> points = new LinkedList<Point>();

		// read in the input
		In in = new In(filename);
		int N = in.readInt();
		for (int i = 0; i < N; i++) {
			int x = in.readInt();
			int y = in.readInt();
			Point p = new Point(x, y);
			points.add(p);
		}

		return points;
	}

	private static boolean isSameSegment(List<Point> points) {
		// Checks if all of the provided points are on the same segment.
		// To check whether the 4 points p, q, r, and s are collinear, check
		// whether the slopes between p and q, between p and r, and between p
		// and s are all equal.

		assert (points.size() == 4);

		// Only need to compare one point against all of the others
		Point chosenPoint = points.get(0);
		double establishedSlope = chosenPoint.slopeTo(points.get(1));

		Point currPoint;
		double currSlope;
		int size = points.size();
		for (int i = 2; i < size; i++) {
			currPoint = points.get(i);
			currSlope = chosenPoint.slopeTo(currPoint);

			if (currSlope != establishedSlope)
				return false;
		}

		return true;
	}

	public static void main(String[] args) {
		// read in the input
		String filename;
		try {
			filename = args[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			filename = "src/test/collinear/input6.txt";

		}

		List<Point> points = readPoints(filename);

		

	}

}
