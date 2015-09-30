/*----------------------------------------------------------------
 *  Author:        Angelo Postiglione
 *  Written:       28/09/2015
 *  Last updated:  29/09/2015
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
    private static int n;
    private static int t;

    private double[] percolationThresholds;

    /**
     * Performs T independent experiments on an N-by-N grid
     * @param N The grid side value.
     * @param T The number of experiments
     */
    public PercolationStats(int N, int T) {
        // The constructor should throw a java.lang.IllegalArgumentException if either N <= 0 or T <= 0.
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Arguments N and T shall be greater than zero.");
        }

        percolationThresholds = new double[T];
        double gridSize = N * N;

        // A series of T computational experiments
        for (int i = 0; i < T; i++) {
            int siteOpened = 0;

            // Initialize a new experiment by creating a new data structure and reset the opened sites.
            Percolation percolation = new Percolation(N);

            while (!percolation.percolates()) {
                // Choose a site (row x, column y) uniformly at random among all blocked sites.
                int x = StdRandom.uniform(1, N+1);
                int y = StdRandom.uniform(1, N+1);

                if (!percolation.isOpen(x, y)) {
                    percolation.open(x, y);
                    siteOpened++;

                }
            }
            
            // The fraction of sites that are opened when the system 
            // percolates provides an estimate of the percolation threshold
            percolationThresholds[i] = (double) siteOpened / gridSize;
        }
    }

    /**
     * Sample mean of percolation threshold
     * @return The calculated mean
     */
    public double mean() {
        return StdStats.mean(percolationThresholds);
    }

    /**
     * Sample standard deviation of percolation threshold
     * @return The calculated standard deviation
     */
    public double stddev() {
        // If T equals 1 the sample standard deviation is undefined.
        if (t == 1) {
            return Double.NaN;
        }
        
        return StdStats.stddev(percolationThresholds);
    }

    /**
     * Low endpoint of 95% confidence interval
     * @return The low confidence interval
     */
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(t));
    }
    
    /**
     * High endpoint of 95% confidence interval
     * @return The high confidence interval
     */
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(t));
    }

    /**
     *  Takes two command-line arguments N and T, performs T independent computational 
     *  experiments on an N-by-N grid, and prints the mean, standard deviation, 
     *  and the 95% confidence interval for the percolation threshold. 
     */
    public static void main(String[] args) {
        n = Integer.parseInt(args[0]);
        t = Integer.parseInt(args[1]);

        PercolationStats pStats = new PercolationStats(n, t);
        System.out.println("n = " + n + " t = "+ t);
        System.out.println("mean = " + pStats.mean());
        System.out.println("stdev = " + pStats.stddev());
        System.out.println("95% confidence interval = " + pStats.confidenceLo() + ", " + pStats.confidenceHi());
    }    
}