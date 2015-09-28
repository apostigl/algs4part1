/*----------------------------------------------------------------
 *  Author:        Angelo Postiglione
 *  Written:       28/09/2015
 *  Last updated:  28/09/2015
 *
 *  A program to perform a series of computational experiments
 *  using Percolation
 *  
 *  http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
 *  
 *----------------------------------------------------------------*/


package it.algorithms.percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	private Percolation percolation;
	private static int N;
	private static int T;
	
	private double siteOpened;
	private double percolationThreshold = 0.0;
	private double[] percolationThresholdsList;
	private int gridSize;
	
	/**
	 * Performs T independent experiments on an N-by-N grid
	 * @param N The grid side value.
	 * @param T The number of experiments
	 */
	public PercolationStats(int N, int T){
		//The constructor should throw a java.lang.IllegalArgumentException if either N <= 0 or T <= 0.
		if(N <= 0 || T <= 0){
			throw new IllegalArgumentException("Arguments N and T shall be greater than zero.");
		}

		percolationThresholdsList = new double[T];
		gridSize = N*N;
		
		//A series of T computational experiments
		for(int i=0; i < T ; i++){
			//Initialize a new experiment by creating a new data structure and reset the opened sites.
			percolation = new Percolation(N);
			siteOpened = 0;
			
			//Until the system percolates
			while(! percolation.percolates() ){
				//Choose a site (row x, column y) uniformly at random among all blocked sites.
				int x = StdRandom.uniform(1,N+1);
				int y = StdRandom.uniform(1,N+1);
				
				if(!percolation.isOpen(x, y)){
					//Open the site (row x, column y)
					percolation.open(x, y);
					siteOpened ++;
					
					if(percolation.percolates()){
						//The fraction of sites that are opened when the system percolates provides an estimate of the percolation threshold
						percolationThreshold = siteOpened / gridSize;
						
						//Add the current threshold to the list of T thresholds
						percolationThresholdsList[i] = percolationThreshold;  
					}
					
				}
			}
			
		}
	}
	
	/**
	 * Sample mean of percolation threshold
	 * @return
	 */
	public double mean(){
		return StdStats.mean(percolationThresholdsList);
	}
	
	/**
	 * Sample standard deviation of percolation threshold
	 * @return
	 */
	public double stddev(){
		//If T equals 1 the sample standard deviation is undefined. We recommend returning Double.NaN.
		if( T== 1){
			return Double.NaN;
		}
		
		return StdStats.stddev(percolationThresholdsList);
	}
	
	/**
	 * Low endpoint of 95% confidence interval
	 * @return
	 */
	public double confidenceLo(){
		return mean() - (1.96 * Math.sqrt(stddev()) / Math.sqrt(T));
	}
	
	 
	/**
	 * High endpoint of 95% confidence interval
	 * @return
	 */
	 public double confidenceHi(){
		return mean() + (1.96 * Math.sqrt(stddev()) / Math.sqrt(T));
	}

	 
	/**
	 *  Takes two command-line arguments N and T, performs T independent computational 
	 *  experiments on an N-by-N grid, and prints the mean, standard deviation, 
	 *  and the 95% confidence interval for the percolation threshold. 
	 */
	public static void main(String[] args){
		N = Integer.parseInt(args[0]);
		T = Integer.parseInt(args[1]);
		
		PercolationStats pStats = new PercolationStats(N, T);
		System.out.println("mean = " + pStats.mean());
		System.out.println("stdev = " + pStats.stddev());
		System.out.println("95% confidence interval = " + pStats.confidenceLo() + ", " + pStats.confidenceHi());
	}    
}