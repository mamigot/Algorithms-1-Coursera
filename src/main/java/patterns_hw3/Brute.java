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

	private static boolean areCollinear(List<Point> points) {
		// Checks if all of the provided points are on the same segment.
		// To check whether the 4 points p, q, r, and s are collinear, check
		// whether the slopes between p and q, between p and r, and between p
		// and s are all equal.
		return true;

	}

	private static List<List<Point>> assembleDistinctLineSegments(
			List<Point> readPoints) {

		List<List<Point>> lineSegments = new LinkedList<List<Point>>();

		List<Point> currList = new LinkedList<Point>();
		boolean collinear;
		for (Point p1 : readPoints) {
			currList.clear();

			// Create a sorted list of the points
			currList.add(p1);

			for (Point p2 : readPoints) {
				currList.add(p2);

				for (Point p3 : readPoints) {
					currList.add(p3);

					for (Point p4 : readPoints) {
						currList.add(p4);

						// Check if the points are collinear
						collinear = areCollinear(currList);

						// Add to lineSegments if the
						// permutation doesn't exist already
						if (collinear) {
							// Check if it's in lineSegments and add accordingly
							// DOUBLE CHECK THAT THIS "CONTAINS" WORKS
							if (!lineSegments.contains(currList))
								lineSegments.add(currList);

						}

					}
				}
			}
		}

		return lineSegments;
	}

	public static void main(String[] args) {
		// read in the input
		String filename;
		try {
			filename = args[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			filename = "src/test/collinear/input6.txt";

		}

		List<Point> readPoints = readPoints(filename);

		List<List<Point>> lines = assembleDistinctLineSegments(readPoints);

		// Display and print the line segments

	}

}
