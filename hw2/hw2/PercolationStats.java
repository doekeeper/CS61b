package hw2;

public class PercolationStats {

    /**
     * perform T independent experiments on an N-by-N grid
     * @param N
     * @param T
     * @param pf
     */
    public PercolationStats(int N, int T, PercolationFactory pf){
        if( N <= 0 || T <= 0) throw new IllegalArgumentException("input N or T is not positive");
        int i = 0;
        while (i < T) {
            pf.make(N);
            i++;
        }

    }

    public double mean() {
        return -1;
    }

    public double stdev() {
        return -1;
    }

    public double confidenceLow() {
        return -1;
    }

    public double confidenceHigh() {
        return -1;
    }

}
