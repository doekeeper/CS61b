/**
 * this class represents a union-find data type (also known as the disjoint-sets data type).
 * It supports the union and find operations, along with a connected operation for determining whether two sites are in the same component
 * and a count operation that returns the total number of components.
 * The union-find data type models connectivity among a set of n sites, named 0 through n-1.
 * The is-connected-to relation must be an equivalence relation.
 * In this case, two sites are in the same component if and only if they connected. Both sites and components are identified with integers between 0 and n-1.
 * union(p, q) adds a connection between the two sites p and q. If p and q are in different components, then it replaces these two components with a new component that is the union of the two.
 * find(p) returns the conponent identifier of the component containing p.
 * connected(p, q) returns true if both p and q are in the same conponent, and false otherwise.
 * count() returns the number of components.
 * This implementation uses weighted quick union by size (without path compression).
 * Initializing a data structure with n sites takes linear time.
 * Afterwards, the union, find, and connected operation take log(n) time (in the worse case) and the count operation takes constant time.
 * For alternate implementation of the same API, see UF, QuickFindUF, QuickUnionUF
 * Code is based upon the code example from "Algorithms, 4th ed., R. Sedgewick and K. Wayne
 */

public class WeightedQuickUnionPC {

    private int [] parents;     // parent[i] = parent of i
    private int [] size;        // size[i] = number of sites in subtree rooted at i
    private int count;      // number of components


    /**
     * Initialize an empty union-find data structure with n sites
     * 0 through n-1. Each site is initially in tis own component
     * @param n the number of sites
     * @throws  IllegalArgumentException if n < 0
     */
    public WeightedQuickUnionPC(int n) {
        parents = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i ++) {
            parents[i] = i;
            size[i] = 1;
        }
        count = n;
    }

    /**
     * Returns the number of components
     *
     * @return the number of components (between 1 and n)
     */
    public int count() {
        return count;
    }

    /**
     * Returns the component identifier for the component containing site p.
     * @param p the integer representing one object
     * @return the component identifier for the component containing site p.
     * @throws IllegalArgumentException unless 0 <= p < n
     */
    public int find (int p) {
        validate(p);
        while (p != parents[p]) {
            p = parents[p];
        }
        return p;
    }

    /**
     * validate that p is a valid index.
     */
    private void validate (int p) {
        int n = parents.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException ("index" + p + " is not between 0 and " + (n-1));
        }
    }


    /**
     * Returns true if the two sites are in the same component.
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @return true if the two sites p and q are in the same components; false otherwise
     * @throws IllegalArgumentException unless (0 <= p < n) and (0 <= p < n)
     */
    public boolean Connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Merges the components containing sites p with the component containing site q.
     * @param p the integer representing one site.
     * @param q the integer representing the other site.
     * @throws IllegalArgumentException unless (0 <= p < n) and (0 <= p < n)
     */

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        } else {
            if (size[pRoot] < size[qRoot]) {
                parents[pRoot] = qRoot;
                size[qRoot]+=size[pRoot];
            }
            else {
                parents[qRoot] = pRoot;
                size[pRoot]+= size[qRoot];
            }
            count--;
        }
    }
}
