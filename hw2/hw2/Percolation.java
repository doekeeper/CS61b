package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.introcs.*;
import java.lang.Math.*;

public class Percolation {
    private final boolean BLOCKED = false;            //  BLOCKED is defined as false
    private final boolean OPEN = true;                //  OPEN is defined as true


    private int row, col;                               // width and height of the percolation grid model
    private boolean[][] siteMap;                        //  2-D boolean array to track openness of each site (true if the site is open, false otherwise)
    private boolean percolated;                         //  track the status of percolation. True if it is percolated (any open site at bottom row is connected to any open site on the top row), false otherwise
                                                        //  In another word, percolated becomes true if siteConnection has a set that contains both top row and bottom row
    private int n;                                      //  number of open sites
    private WeightedQuickUnionUF siteConnection;        //  store the connectedness of all the sites; connected sites will have the same root; percolation becomes true if any set contains both top row sites and bottom row sites

    /**
     * constructor for percolation class
     * create N-by-N sites, with all sites initially blocked
     * @param N, row and column length equals N;
     * @throws IllegalArgumentException if N is zero or negative
     */
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException("argument to Percolation constructor is invalid");
        this.row = N;
        this.col = N;
        percolated = false;
        siteMap = new boolean[row][col];        // by default, all the site are false
        n = 0;
        siteConnection = new WeightedQuickUnionUF(N*N);
    }

    /**
     * open the site (row, col) if it is not open already
     * @param row, col - coordinate of the site ( 0 <= row < N, 0 <= col < N)
     * @throws IndexOutOfBoundsException if row < 0, or row >= N, or col < 0, or col >= N
     */

    public void open(int row, int col) {
        if (row < 0 || row >= this.row || col < 0 || col >= this.col) {
            throw new IndexOutOfBoundsException("row or col input argument to open() is out of bounds");
        }
        if (isOpen(row, col) == OPEN) return;
        else {
            siteMap[row][col] = OPEN;                                                   // set the site(row, col) to OPEN
            n++;                                                                        // open site count + 1
            updateSiteConnection(row, col);                                             // update the connections around the newly opened site
            if (percolated(row, col)) {                                                 // check if it is percolated
                percolated = true;                                                      // set percolated to true;
                System.out.println("Percolated! It requires " + n + " open sites.");
                return;
            }
        }
    }

    /**
     * find if it is percolated
     * In another word, if there are both top row open site and bottom row open site in the same set of the open site (row, col) of interest
     * @param row
     * @param col
     * @return true if there is top row open site and bottom row open site in the same set of the site (row, col); return false otherwise
     */
    public boolean percolated(int row, int col) {
        if (hasTopRowSite(row, col) && hasBottomRowSite(row, col) ) return true;
        return false;
    }

    /**
     * check if the site (row, col) of interest is connected to any top row site (0, COL) where 0< COL < M
     * @param row
     * @param col
     * @return true if they are connected; false otherwise
     */
    private boolean hasTopRowSite(int row, int col) {
        int p = this.col * row + col;
        for (int i = 0; i < this.col; i++) {
            int q = i;
            if (siteConnection.connected(p, q)) return true;
        }
        return false;
    }

    /**
     * check if the site (row, col) of interest is connected to any bottom row site (this.row - 1, COL) where 0< COL < M
     * @param row
     * @param col
     * @return  true if they are connected; false otherwise
     */
    private boolean hasBottomRowSite(int row, int col) {
        int p = this.col * row + col;
        for (int i = 0; i < this.col; i++) {
            int q = this.col * (this.row - 1) + i;
            if (siteConnection.connected(p, q)) return true;
        }
        return false;
    }

    /**
     * is site (row, col) is connected to full site
     * @return true if the site is already open; false otherwise
     */
    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= this.row || col < 0 || col >= this.col) {
            throw new IndexOutOfBoundsException("row or col input argument to open() is out of bounds");
        }
        return siteMap[row][col];
    }

    /**
     * if new site is open, run updateSiteConnection(row, col) to update new connections in the site map around the newly added site
     * the following steps is needed:
     * 1. check top neighbour - if top neighbour is open site, union the two sets which contains top neighbour and the site of interest
     * 2. repeat step 1 for bottom neighbour, left neighbour, right neighbour
     * @param row
     * @param col
     */
    private void updateSiteConnection(int row, int col) {
        if (isConnected(row, col, 'U')) connect(row, col, row - 1, col);            // Check Up
        if (isConnected(row, col, 'D')) connect(row, col, row + 1, col);            // Check Down
        if (isConnected(row, col, 'L')) connect(row, col, row, col - 1);             // Check Left
        if (isConnected(row, col, 'R')) connect(row, col, row, col + 1);            // Check Right

    }

    /**
     * check if the site (row, col) is connected to other site?
     * In another word, if any neighbour (up, down, left, right) of the newly opened site (row, col) is also an open site?
     * @param row       row coordinate
     * @param col       col coordinate
     * @return true if the site (row, col) has neighbour (up, down, left, right) with open site
     */
    private boolean isConnected(int row, int col, char neighbour) {
        switch (neighbour) {
            case 'U':
                if (row - 1 >= 0) {
                    return isOpen(row - 1, col);
                }
                break;
            case 'D':
                if (row + 1 < this.row) {
                    return isOpen(row + 1, col);
                }
                break;
            case 'L':
                if (col - 1 >= 0) {
                    return isOpen(row, col - 1);
                }
                break;
            case 'R':
                if (col + 1 < this.col) {
                    return isOpen(row, col + 1);
                }
                break;
        }
        return false;
    }

    /**
     * union the two sets that contains the two sites - site(row1, col1) and site(row2, col2)
     * siteConnection is 1-D array, the corresponding site (row, col) is equivalent to siteConnection[k], where k = 6 * row + col;
     * @param row1
     * @param col1
     * @param row2
     * @param col2
     */
    private void connect(int row1, int col1, int row2, int col2) {
        int p = this.col * row1 + col1;
        int q = this.col * row2 + col2;
        if (!siteConnection.connected(p, q)) {
            siteConnection.union(p, q);
        }
     }

     public boolean isFull(int row, int col) {
         if (row < 0 || row >= this.row || col < 0 || col >= this.col) {
             throw new IndexOutOfBoundsException("row or col input argument to open() is out of bounds");
         }
        if (hasTopRowSite(row, col)) return true;
        return false;
     }

    /**
     * return number of open sites
     * @return number of Open Sites, aka, n
     */
    public int numberOfOpenSites() {
        return n;
     }

    /**
     * check if it percolates
     * @return true if it percolates; false otherwise
     */
     public boolean percolates() {
        return percolated;
     }

     public static void main(String[] args) {
        Percolation sim = new Percolation(20);
        while(!sim.percolates()) {
            int row = StdRandom.uniform(20);
            int col = StdRandom.uniform(20);
            sim.open(row, col);
        }
        System.out.println("Simulation completed");
     }
}
