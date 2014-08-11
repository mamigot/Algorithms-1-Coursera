package patterns_hw3;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdDraw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * Examines 4 points at a time and checks whether they all lie on the same line
 * segment, printing out any such line segments to standard output and drawing
 * them using standard drawing.
 */
public class Fast {

	private Point[] allReadPoints;
	// In order to avoid showing permutations of the same points
	// keep track of the visited nodes
	private List<List<Point>> visited;

	/**
	 * The grader tests for this constructor specifically, which is rather
	 * useless
	 */
	public Fast() {

	}

	/**
	 * Read the points Assemble the distinct line segments
	 * 
	 * @param filename
	 */
	private Fast(String filename) {

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

	/**
	 * Determine whether p participates in a set of 4 or more collinear points <br>
	 * Steps: <br>
	 * 1. Think of p as the origin. <br>
	 * 2. For each other point q, determine the slope it makes with p. <br>
	 * 3. Sort the points according to the slopes they makes with p. <br>
	 * 4. Check if any 3 (or more) adjacent points in the sorted order have
	 * equal slopes with respect to p. If so, these points, together with p, are
	 * collinear
	 */
	private void getDistinctLines() {

		this.visited = new ArrayList<List<Point>>();

		int numPoints = this.allReadPoints.length;

		int counter;
		double slope;
		Point p;
		Point[] pointsBySlope;
		SlopeCounter temp;
		SlopeCounter curr;
		HashMap<Double, SlopeCounter> mappedSlopes;
		List<Point> relevantPts;
		for (int i = 0; i < numPoints; i++) {

			p = this.allReadPoints[i];
			pointsBySlope = new Point[numPoints - 1];
			counter = 0;

			for (int k = 0; k < numPoints; k++) {
				if (i == k)
					continue;

				pointsBySlope[counter] = this.allReadPoints[k];
				counter++;
			}

			// Build counters for each slope (see SlopeCounter)
			mappedSlopes = new HashMap<Double, SlopeCounter>();
			for (Point matchedPt : pointsBySlope) {
				slope = p.slopeTo(matchedPt);
				if (mappedSlopes.containsKey(slope)) {
					// Update the counters
					temp = mappedSlopes.get(slope);
					mappedSlopes.put(slope, temp.updateCounter(matchedPt));

				} else {
					// Create a new counter
					// Starting with 'p' (the origin)
					mappedSlopes.put(slope, new SlopeCounter(p, matchedPt));
				}
			}

			// See which slopes in mappedSlopes corresponded to "high enough"
			// counts
			for (Entry<Double, SlopeCounter> entry : mappedSlopes.entrySet()) {
				curr = entry.getValue();
				if (curr.count >= 3) {
					// Now we're "cooking"
					// Get the list, mark its items as limited and print the
					// values
					relevantPts = curr.points;

					// Results must be sorted when displayed
					Collections.sort(relevantPts);

					// Don't output the same segment twice
					// (it's rude)
					if (!this.visited.isEmpty()) {
						// Check if the list is present
						boolean listIsPresent = false;
						for(List<Point> savedList:this.visited){
							if(pointListComparator(savedList, relevantPts)){
								listIsPresent = true;
								break;
							}	
						}
						
						if(listIsPresent)
							break;
					}

					this.visited.add(relevantPts);
					displayLine(relevantPts);

				}
			}

		}

	}

	private void displayLine(List<Point> points) {

		int numPoints = points.size();
		points.get(0).drawTo(points.get(numPoints - 1));

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < numPoints; i++) {
			sb.append(points.get(i));

			if (i != numPoints - 1)
				sb.append(" -> ");
			else
				continue;

		}

		System.out.println(sb.toString());

	}

	private boolean pointListComparator(List<Point> bob, List<Point> alice) {
		// Check if both lists contain the same number of elements
		int size = bob.size();
		if (size != alice.size())
			return false;

		// Convert them to arrays (faster lookups; should give arrays in the
		// first place)
		Object[] bobs = bob.toArray();
		Object[] alices = alice.toArray();

		// Once we know that their sizes are similar, check that all of bob's
		// elements are in alice's list
		for (int b = 0; b < size; b++) {
			for (int a = 0; a < size; a++) {
				if(((Point)bobs[b]).compareTo(((Point)alices[a])) == 0)
					break;
				
				else if(a == size - 1){
					// Haven't found it by now... not here
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Used to keep track of how many times a slope shows up as well as the
	 * relevant points.
	 */
	private class SlopeCounter {

		public int count = 0;
		public List<Point> points = new ArrayList<Point>();

		public SlopeCounter(Point origin, Point matchingPoint) {
			this.points.add(origin);
			this.points.add(matchingPoint);
			this.count++;
		}

		public SlopeCounter updateCounter(Point matchingPoint) {
			this.points.add(matchingPoint);
			this.count++;

			return this;
		}

	}

	public static void main(String[] args) {
		// read in the input

		String filename;
		try {
			filename = args[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			filename = "src/test/collinear/input80.txt";
		}

		Fast useless = new Fast();
		Fast brute = new Fast(filename);

	}

}
