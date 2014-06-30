package percolation_hw1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import shortcuts.print;

public class Percolation {

	private int N;
	private int virtualTopSite;
	private int virtualBottomSite;

	private boolean[] indicators = null;
	private int[][] mappedGrid = null;
	
	private WeightedQuickUnionUF uf = null;

	
	
	/** create N-by-N grid, with all sites blocked */
	public Percolation(int N){
		this.N = N;
		
		// grid with (0, 0) at the left
		// used to find the "target" (see open())
		this.mappedGrid = new int[N][N];
		
		int counter = 0;
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				this.mappedGrid[i][j] = counter;
				counter++;
			}
		}
		
		this.indicators = new boolean[N*N];
		for(int i = 0; i < this.indicators.length; i++){
			this.indicators[i] = false;
		}

		

		// WeightedQuickUnion's relevant values are from 0 to N*N-1...
		// the N*N value is for the virtualTopSite, which is connected to the first N entries
		// N*N + 1 is for the virtualBottomSite, which is connected to the last N entries
		this.uf = new WeightedQuickUnionUF(N*N + 1 + 1);
		
		// this.virtualTopSite takes the value of the last position in this.uf
		this.virtualTopSite = N*N;
		this.virtualBottomSite = N*N + 1;
		//this.uf.union(N*N, this.virtualTopSite);
		
		
		// connect all in [0, N) to the virtual site (located at the last spot)
		for(int i = 0; i < N; i++)
			this.uf.union(i, this.virtualTopSite);
		
		// connect the bottom row to that virtual site
		for(int i = N*N-N; i < N*N; i++)
			this.uf.union(i, this.virtualBottomSite);
	}
	
	
	/** open site (row i, column j) if it is not already */
	public void open(int i, int j){
		if(i > N || j > N) throw new IndexOutOfBoundsException();
		if(i < 1 || j < 1) throw new IndexOutOfBoundsException();

		// the UF structure we use starts at 0
		// do this to match it
		i--; j--;

		
		// get the value we'd like to open in the UF structure
		int target = this.mappedGrid[i][j];
		// highlight the indicator (used for isOpen())
		this.indicators[target] = true;

		
		// check up, down, left and right positions
		// if they're open, then union them with this one!
		try{
		if(this.isOpenMapped(i-1, j))
			this.uf.union(target, this.up(i, j));
		
		}catch(IndexOutOfBoundsException e){}
		
		try{
		if(this.isOpenMapped(i+1, j))
			this.uf.union(target, this.down(i, j));
		
		}catch(IndexOutOfBoundsException e){}
		
		try{
		if(this.isOpenMapped(i, j-1))
			this.uf.union(target, this.left(i, j));
		
		}catch(IndexOutOfBoundsException e){}
		
		try{
		if(this.isOpenMapped(i, j+1))
			this.uf.union(target, this.right(i, j));
		
		}catch(IndexOutOfBoundsException e){}
		
		
	}
	
	
	/** is site (row i, column j) open? */
	public boolean isOpen(int i, int j){
		if(i > N || j > N) throw new IndexOutOfBoundsException();
		if(i < 1 || j < 1) throw new IndexOutOfBoundsException();

		// retrieve the index from the mappedGrid and then its flag
		return this.indicators[ this.mappedGrid[i - 1][j - 1] ];
	}
	
	
	/** is site (row i, column j) full? */
	public boolean isFull(int i, int j){
		if(i > N || j > N) throw new IndexOutOfBoundsException();
		if(i < 1 || j < 1) throw new IndexOutOfBoundsException();
		
		// a full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites.

		return this.isOpen(i, j) && this.uf.connected(this.mappedGrid[i-1][j-1], this.virtualTopSite);
	}
	
	
	/** does the system percolate? */
	public boolean percolates(){
		
		return this.uf.connected(this.virtualTopSite, this.virtualBottomSite);
	}
	
	
	
	
	// used to check if the mapped UF structure is open
	private boolean isOpenMapped(int i, int j){
		// isOpenMapped takes the "mapped" values, which
		// reduce i and j by 1, whereas isOpen doesn't reduce them!
		return this.isOpen(i + 1, j + 1);
		
	}
	
	// retrieve up position
	private int up(int i, int j) throws IndexOutOfBoundsException{
		try{ return this.mappedGrid[i-1][j]; }
		
		catch(IndexOutOfBoundsException e){ throw new IndexOutOfBoundsException("no up!");}
	}
	
	// retrieve down position
	private int down(int i, int j) throws IndexOutOfBoundsException{
		try{ return this.mappedGrid[i+1][j]; }
		
		catch(IndexOutOfBoundsException e){ throw new IndexOutOfBoundsException("no down!"); }
	}
	
	// retrieve left position
	private int left(int i, int j) throws IndexOutOfBoundsException{
		try{ return this.mappedGrid[i][j-1]; }
		
		catch(IndexOutOfBoundsException e){ throw new IndexOutOfBoundsException("no left!"); }
	}
	
	// retrieve right position
	private int right(int i, int j) throws IndexOutOfBoundsException{
		try{ return this.mappedGrid[i][j+1]; }
		
		catch(IndexOutOfBoundsException e){ throw new IndexOutOfBoundsException("no right!"); }
	}


	
	public static void main(String[] args) {
		
		int N = 4;
		Percolation bob = new Percolation(N);
		
		bob.open(1, 1);		
		bob.open(4, 1);
		bob.open(3, 1);
		bob.open(4, 1);
		
//		print.ln("isOpen = " + bob.isOpen(4, 2));
//		System.exit(1);
		
//		bob.open(1, 4);
//		bob.open(1, 3);
//		bob.open(3, 3);
//		bob.open(4, 3);
		
//		print.ln("connected? " + bob.uf.connected(0, 4) );
//		print.ln();
		
//		print.ln("mapped");
//		print.ln(bob.mappedGrid);
//		print.ln();
		
//		print.ln("visual");
//		print.ln(bob.visualGrid);
//		print.ln();
//		//print.ln(bob.indicators);
//		
//		print.ln();
//		
//		int[] positions = new int[N*N];
//		for(int i = 0; i < N*N; i++)
//			positions[i] = i;
//		
//		print.ln(positions);
//		print.ln(linearSetup);
//		
//		print.ln();
//
//		print.ln("percolates? " + bob.percolates());
		
	}

}
