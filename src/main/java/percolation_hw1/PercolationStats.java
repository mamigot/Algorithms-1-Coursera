package percolation_hw1;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
	
	// saved rates
	private double[] rates;
	private double mean;
	private double stddev;
	
	
	
	/** perform T independent computational experiments on an N-by-N grid */
	public PercolationStats(int N, int T){
		
		Percolation per;	
		double openSites;
		double rate;
		rates = new double[T];
		
		int randomX;
		int randomY;
		
		for(int i = 0; i < T; i++){
			per = new Percolation(N);
			openSites = 0;
			
			do{
				randomX = StdRandom.uniform(1, N + 1);
				randomY = StdRandom.uniform(1, N + 1);
				
				if( !per.isOpen(randomX, randomY) ){
					per.open(randomX, randomY);
					openSites++;
				}
				
			}while( !per.percolates() );
			
			rate = (openSites/((double)N*N));
			
			rates[i] = rate;
			
			/*
			System.out.println("openSites = " + openSites);
			System.out.println("total = " + N*N);
			System.out.println();
			System.out.println("rate = " + (openSites/((double)N*N)));
			*/
		}
		
	}
	
	
	/** sample mean of percolation threshold */
	public double mean(){
		
		double len = this.rates.length;
		double sum = 0;
		
		for(int i = 0; i < len; i++)
			sum += rates[i];
		
		this.mean = sum/len;
		return this.mean;
		
	}
	
	
	/** sample standard deviation of percolation threshold */
	public double stddev(){
		this.stddev = StdStats.stddev(rates);
		return this.stddev;
		
	}
	
	
	/** returns lower bound of the 95% confidence interval */
	public double confidenceLo(){
		return 1.0;
				
	}
	
	
	/** returns upper bound of the 95% confidence interval */
	public double confidenceHi(){
		return 1.0;
	}
	
	
	
	public static void main(String[] args) {
		
		int N = 200;
		int T = 100;
		
		PercolationStats stats = new PercolationStats(N, T);
		
		System.out.println("mean   = " + stats.mean() );
		System.out.println("stddev = " + stats.stddev() );

	}

}
