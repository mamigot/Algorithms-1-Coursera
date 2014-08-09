package patterns_hw3;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdDraw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Examines 4 points at a time and checks whether they all lie on the same line
 * segment, printing out any such line segments to standard output and drawing
 * them using standard drawing.
 */
public class Brute {

	private Point[] allReadPoints;
	private List<Point[]> distinctLines;

	/**
	 * The grader tests for this constructor specifically, which is rather
	 * useless
	 */
	public Brute() {

	}

	/**
	 * Read the points Assemble the distinct line segments
	 * 
	 * @param filename
	 */
	private Brute(String filename) {

		// String filename = "src/test/collinear/input6.txt";

		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);

		// read in the input
		In in = new In(filename);
		int N = in.readInt();

		this.allReadPoints = new Point[N];

		for (int i = 0; i < N; i++) {
			int x = in.readInt();
			int y = in.readInt();
			Point p = new Point(x, y);
			p.draw();
			this.allReadPoints[i] = p;
		}

		this.getDistinctLines();
	}

	private List<Point[]> getDistinctLines() {

		this.distinctLines = new ArrayList<Point[]>();

		// Make arrays of four points each
		// Check if that array (or a permutation) is present in lines

		
		int numPoints = this.allReadPoints.length;
		Point[] currPoints = new Point[4];

		for (int a = 0; a < numPoints; a++) {
			// Arrays.fill(currPoints, null); // Clean out previous currPoints
			currPoints[0] = this.allReadPoints[a];

			for (int b = a + 1; b < numPoints; b++) {
				currPoints[1] = this.allReadPoints[b];

				for (int c = b + 1; c < numPoints; c++) {
					currPoints[2] = this.allReadPoints[c];

					for (int d = c + 1; d < numPoints; d++) {				
						currPoints[3] = this.allReadPoints[d];
						
						// // //
						
						// doesn't cover: 14 19 21 32
						
						// 14 18 19 32 exists
						// 14 18 19 21 exists
						
						Point ted = new Point(14000, 10000);
						Point ned = new Point(19000, 10000);
						Point tom = new Point(21000, 10000);
						Point cat = new Point(32000, 10000);
						boolean first = false;
						boolean second = false;
						boolean third = false;
						boolean fourth = false;
						for(Point test:currPoints){
							if(test.compareTo(ted) == 0)
								first = true;
							if(test.compareTo(ned) == 0)
								second = true;
							if(test.compareTo(tom) == 0)
								third = true;
							if(test.compareTo(cat) == 0)
								fourth = true;	
						}
						if(first && second && third && fourth){
							System.out.println("we got a match!");
							System.exit(1);
						}
						
						
						

						if (areCollinear(currPoints)) {
							Arrays.sort(currPoints);

							if (this.distinctLines.isEmpty())
								this.distinctLines.add(currPoints.clone());

							else {
								// Only add it if it's not present
								int listSize = this.distinctLines.size();

								for (int p = 0; p < listSize; p++) {
									if (Arrays.equals(currPoints,
											this.distinctLines.get(p))) {
										break;

									} else if (p == listSize - 1) {
										this.distinctLines.add(currPoints
												.clone());
									}
								}

							}
						}

					}
				}
			}
		}

		
		for (Point[] pts : this.distinctLines)
			displayLine(pts);

		return this.distinctLines;

	}

	private void displayLine(Point[] points) {
		Arrays.sort(points, points[0].SLOPE_ORDER);

		points[0].drawTo(points[points.length - 1]);

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < points.length; i++) {
			sb.append(points[i]);

			if (i != points.length - 1)
				sb.append(" -> ");
			else
				continue;

		}

		System.out.println(sb.toString());

	}

	private boolean areCollinear(Point[] points) {
		// To check whether the 4 points p, q, r, and s are collinear, check
		// whether the slopes between p and q, between p and r, and between
		// p and s are all equal.

		assert (points.length >= 2);

		// Curr slope
		double currSlope = points[0].slopeTo(points[1]);

		for (int i = 0; i < points.length; i++) {
			for (int k = i + 1; k < points.length; k++) {
				if (currSlope != points[i].slopeTo(points[k]))
					return false;
			}
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

		Brute useless = new Brute();
		Brute brute = new Brute(filename);

	}

}
