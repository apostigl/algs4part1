/*----------------------------------------------------------------
 *  Author:        Angelo Postiglione
 *  Written:       24/09/2015
 *  Last updated:  28/09/2015
 *
 *  A program to estimate the value of the percolation 
 *  threshold via Monte Carlo simulation.
 *  
 *  http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
 *
 *----------------------------------------------------------------*/

package it.algorithms.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	 
	private final boolean[][] grid;
	
    private final int N;
    
	private final WeightedQuickUnionUF uf;
	
	public Percolation(int N){
		this.N = N;

		// Contains a grid with all sites blocked by default (false)
		// Since valid row and column indices are between 1 and N, the last row and the first column are extra
		// and we use them to store the virtual top in (0,0) which means position 0 in the union data structure
		// and the virtual bottom in (0,N+1) which maps to position N*N+1 in the union data structure
		grid = new boolean[N + 1][N + 1];
		 
		if(N <= 0) 
			throw new java.lang.IllegalArgumentException("Grid size shall be greater than zero.");
		
		// Initializes an empty union-find data structure.
		uf = new WeightedQuickUnionUF( (N+1) * N );
		
		//links the top virtual site (with index 0 in the data structure) with every site in the first row of the grid
		for(int i=1; i < N+1; i++){
			int q = xyTo1D(1,i);
			uf.union(0, q);
		}
		
		//links the bottom virtual site (with index N*N+1 in the data structure) with every site in the last row of the grid
		for(int i=1; i < N+1; i++){
			int p = xyTo1D(N,i);
			uf.union(p, N*N + 1);
		}
	}
	
	/**
	 * Opens site (row i, column j) if it is not open already
	 * 
	 * @param i The x coordinate of the site
	 * @param j The y coordinate of the site
	 */
	public void open(int i, int j){
		//the first column and the last row are extra
		if(i!=N+1 && j!=0){
			//Validate the indices of the site that it receives.
			validateIndex(i, j);
			
			if (isOpen(i, j)) {
				return;
			}
			
			grid[i][j] = true;
			
			//links the site in question to its open neighbors
			connectTop(i, j);
			connectBottom(i, j);
			connectLeft(i, j);
			connectRight(i, j);
		}
	}
	
	/**
	 * Connects to the top site if opened
	 * @param i
	 * @param j
	 */
	private void connectTop(int i,int j){
		if (i == 1) {
            return;
        }

		//link to the top site if opened
        if (isOpen(i - 1, j)) {
            int index = xyTo1D(i, j);
            int topSiteIndex = xyTo1D(i-1, j);
            uf.union(index, topSiteIndex);
        }
	}

	/**
	 * Connects to the bottom site if opened
	 * @param i
	 * @param j
	 */
	private void connectBottom(int i,int j){
		if (i == N) {
            return;
        }

		//link to the bottom site if opened
        if (isOpen(i + 1, j)) {
            int index = xyTo1D(i, j);
            int bottomSiteIndex = xyTo1D(i+1, j);
            uf.union(index, bottomSiteIndex);
        }
	}
	
	/**
	 * Connects to the left site if opened
	 * @param i
	 * @param j
	 */
	private void connectLeft(int i,int j){
		if (j == 1) {
            return;
        }

		//link to the left site if opened
        if (isOpen(i, j-1)) {
            int index = xyTo1D(i, j);
            int leftSiteIndex = xyTo1D(i, j-1);
            uf.union(index, leftSiteIndex);
        }
	}
	
	/**
	 * Connects to the right site if opened
	 * @param i
	 * @param j
	 */
	private void connectRight(int i,int j){
		if (j == N) {
            return;
        }

		//link to the right site if opened
        if (isOpen(i, j+1)) {
            int index = xyTo1D(i, j);
            int rightSiteIndex = xyTo1D(i, j+1);
            uf.union(index, rightSiteIndex);
        }
	}
	
	
	/**
	 * Checks if the site in (i,j) is open.
	 * 
	 * @param i The x coordinate of the site
	 * @param j The y coordinate of the site
	 * @return true if the site (i,j) is open
	 */
	public boolean isOpen(int i, int j){
		return grid[i][j];
	}  
	
	
	/**
	 * Checks if site (row i, column j) is full. 
	 * A full site is an open site that can be connected to an open site in the TOP row via a chain of neighboring (left, right, up, down) open sites
	 * 
	 * @param i The x coordinate of the site
	 * @param j The y coordinate of the site
	 * @return True if the site is full
	 */
	public boolean isFull(int i, int j){
		if(!isOpen(i, j)){
			return false;
		}
		
		//this site
		int q = xyTo1D(i,j);
		
		//checks the connection between this site and the top virtual site
		return uf.connected(0, q);
	}
	

	/**
	 * Uniquely maps 2D coordinates to 1D coordinates.
	 * 
	 * @param x The x coordinate of the site
	 * @param y The y coordinate of the site
	 * @return The 1D position of the 2D site.
	 */
	private int xyTo1D(int x, int y){
		return (x-1)* N + y;
	}
	
	/**
	 * Validates indexes of a site.
	 * 
	 * @param x The x coordinate of the site
	 * @param y The y coordinate of the site
	 */
	private void validateIndex(int x, int y){
		if(x < 0 || x > N){
			throw new java.lang.IndexOutOfBoundsException("Row index out of bounds");
		}
		else if(y < 0 ||y > N){
			throw new java.lang.IndexOutOfBoundsException("Coloumn index out of bounds");
		}
	}
	
	/**
	 * The system percolates if there is a full site in the bottom row
	 * 
	 * @return true if the system percolates
	 */
	public boolean percolates(){
		if (N == 1) {
			return isOpen(1, 1);
		}

		//if virtual top site is connected to virtual bottom site
		if( uf.connected(0, N*N + 1)){
			return true;
		}
		
		return false;
	}           
	
	
	public static void main(String[] args){
		Percolation percolation = new Percolation(4);
		
		percolation.open(1,1);
		percolation.open(2,1);
		System.out.println("isFull? " + percolation.isFull(4, 4));
		percolation.open(2,2);
		percolation.open(3,2);
		System.out.println("isFull? " + percolation.isFull(4, 4));
		percolation.open(4,4);
		percolation.open(4,2);
		System.out.println("isFull? " + percolation.isFull(4, 4));
		
		int p = percolation.xyTo1D(1, 1);
		int q = percolation.xyTo1D(4, 2);
		
		System.out.println("Connected? " + percolation.uf.connected(p, q));
		System.out.println("Percolates? " + percolation.percolates());
	}  
}