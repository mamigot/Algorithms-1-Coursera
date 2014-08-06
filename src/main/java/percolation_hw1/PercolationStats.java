package percolation_hw1;

/**
 * Programming Assignment #1: Percolation
 * 
 * Algorithms, Part 1
 * http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
 * <hr>
 * 
 * Using {@link Percolation}, it performs a series of computational experiments
 * on an N-by-N grid to empirically determine that the percentage of indices
 * that need to be open in order for it to percolate is, indeed, ~0.59.
 * 
 * These are examples from a trial wherein N = 200 and T = 100 (N determines the
 * size of the grid and T determines the number of independent computational
 * experiments):
 * 
 * mean = 0.5925962499999999
 * <p>
 * stddev = 0.010419615567439727
 * <p>
 * 95% confidence interval = 0.5905540053487818, 0.5946384946512181
 * 
 * @author miguelamigot
 * @since 2014-07-04
 * 
 */
public class PercolationStats {

	// saved rates
	private double[] rates;
	private double mean;
	private double stddev;

	private int T;

	/** perform T independent computational experiments on an N-by-N grid */
	public PercolationStats(int N, int T) {

		if (N <= 0 || T <= 0)
			throw new IllegalArgumentException();

		this.T = T;

		Percolation per;
		double openSites;
		double rate;
		rates = new double[T];

		int randomX;
		int randomY;

		for (int i = 0; i < T; i++) {
			per = new Percolation(N);
			openSites = 0;

			do {
				randomX = StdRandom.uniform(1, N + 1);
				randomY = StdRandom.uniform(1, N + 1);

				if (!per.isOpen(randomX, randomY)) {
					per.open(randomX, randomY);
					openSites++;
				}

			} while (!per.percolates());

			rate = (openSites / ((double) N * N));

			rates[i] = rate;

		}

	}

	/** sample mean of percolation threshold */
	public double mean() {

		double len = this.rates.length;
		double sum = 0;

		for (int i = 0; i < len; i++)
			sum += rates[i];

		this.mean = sum / len;
		return this.mean;

	}

	/** sample standard deviation of percolation threshold */
	public double stddev() {
		// http://coursera.cs.princeton.edu/algs4/checklists/percolation.html
		if (this.T == 1)
			return Double.NaN;

		this.stddev = StdStats.stddev(rates);
		return this.stddev;

	}

	/** returns lower bound of the 95% confidence interval */
	public double confidenceLo() {
		if (this.mean == 0.0)
			this.mean();
		if (this.stddev == 0.0)
			this.stddev();

		return Math.abs(this.mean - (1.96 * this.stddev)
				/ (Math.pow(this.T, 0.5)));

	}

	/** returns upper bound of the 95% confidence interval */
	public double confidenceHi() {
		if (this.mean == 0.0)
			this.mean();
		if (this.stddev == 0.0)
			this.stddev();

		return Math.abs(this.mean + (1.96 * this.stddev)
				/ (Math.pow(this.T, 0.5)));

	}

	public static void main(String[] args) {

		int N = 200;
		int T = 100;

		PercolationStats stats = new PercolationStats(N, T);

		System.out.println("mean\t\t\t= " + stats.mean());
		System.out.println("stddev\t\t\t= " + stats.stddev());
		System.out.println("95% confidence interval = " + stats.confidenceLo()
				+ ", " + stats.confidenceHi());

	}

}
