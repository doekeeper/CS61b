/**
 * Weighted quick-union with path compression
 * The WeightedQuickUnionPathCompression class represents a union-find data structure.
 * It supports the union and find operations, along with methods for determining whether two sites
 * are in the same component and the total number of components.
 * This implementation uses weighted quick union (by size) with full path compression.
 * Initializing a data structure with n sites takes linear time.
 * Afterwards, union, find, and connected take logarithmic time (in ths worst case) and
 * count takes constant time. Moreover, the amortized time per union, find and connected
 * operation has inverse ackermann complexity.
 */

public class WeightedQuickUnionPathCompressionUF {
    private int[] parent;   // parent[i] is parent of i
    private int[] size;     // size[i] is number of sites in tree rooted at i
                            // not necessarily correct if i is not a root node
    private int count;      // number of components

    /**
     * Initializes an empty union-find data structure with n sites 0 through n-1.
     * Each site is initially in its own component.
     * @param n the number of sites
     * @throws IllegalArgumentException if (n < 0)
     */
    public WeightedQuickUnionPathCompressionUF(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * Returns the number of components.
     * @return the number of components between 1 and n.
     */
    public int count() {
        return count;
    }

    /**
     * Returns the component identifier for the component containing site p.
     * @param p the integer representing one site
     * @return the component identifier for the component containing site p
     * @throws IllegalArgumentException unless 0 <= p < n
     */
    public int find(int p) {
        validate(p);
        if (p == parent[p]) {
            return p;
        } else {
            parent[p] = find(parent[p]);
            return parent[p];
        }
    }

    private void validate (int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("index" + p + " is not between 0 and " + (parent.length-1));
        }
    }


}
