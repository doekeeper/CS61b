package hw2;

import edu.princeton.cs.introcs.*;

public class PercolationStats {
    private double [] percentageOfSites;               // track numberOfSites
    private int n;                              // row and col of percolation map
    private int testNumber;                     // number of test repeats

    /**
     * perform T independent experiments on an N-by-N grid
     * @param N row and col of the percolation map
     * @param T repeats of the test
     * @param pf instantiation of PercolationFactory class
     */
    public PercolationStats(int N, int T, PercolationFactory pf){
        if( N <= 0 || T <= 0) throw new IllegalArgumentException("input N or T is not positive");
        this.n = N;
        this.testNumber = T;
        percentageOfSites = new double[T];
        int i = 0;
        while (i < T) {
            Percolation sim = pf.make(N);
            while (!sim.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                sim.open(row, col);
            }
            percentageOfSites[i] = (double) sim.numberOfOpenSites()/(n*n);
            i++;
        }
    }

    public double mean() {
        double sum = 0.0;
        for (int i = 0; i < percentageOfSites.length; i++) {
            sum = sum + percentageOfSites[i];
        }
        return sum/percentageOfSites.length;
    }

    public double stdev() {
        double squaredDev = 0.0;
        for (int i = 0; i < percentageOfSites.length; i++) {
            squaredDev = squaredDev + Math.pow((percentageOfSites[i] - mean()), 2);
        }
        double variance = squaredDev/(percentageOfSites.length - 1);
        return Math.sqrt(variance);
    }

    public double confidenceLow() {
        return mean() - 1.96*stdev()/Math.sqrt(percentageOfSites.length);
    }

    public double confidenceHigh() {
        return mean() + 1.96*stdev()/Math.sqrt(percentageOfSites.length);
    }

    public static void main (String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats percolationSim = new PercolationStats(20, 10000,pf);
        double mean = percolationSim.mean();
        double stdev = percolationSim.stdev();
        double confidenceLow = percolationSim.confidenceLow();
        double confidenceHigh = percolationSim.confidenceHigh();
        System.out.println("mean of percolation is " + mean);
        System.out.println("stdev of percolation is " + stdev);
    }
}
