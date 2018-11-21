public class QuickFindUF {
	private int[] id;		//id[1] = component identifier of i
	private int count;	// number of components

	/**
	 * intializes an empty union-find data structure with sites {0} through {n-1}. 
	 * Each site is initially in its own component.
	 *
	 * @param n the number of sites
	 * @throws IllegalArgumentException if {n < 0}
	 */
	public QuickFindUF (int n) {
		count = n;
		id = new int[n];
		for (int i = 0; i < n; i++) {
			id[i] = i;
		}
	}
	public int[] getIDArray() {
	    return id;
    }

	/**
	 * Returns the number of components
	 * @return the number of components (between {1} and {n})
	 */
	public int count() {
		return count;
	}

	/** 
	 * Returns the components identifier for the components containing sites {p}.
	 * 
	 * @param p the integer representing one site
	 * @return the component identifier for the component containing site {p}
	 * @throws new IllegalArugmentException if {0 <= p < n}
	 */
	public int find(int p) {
		validate(p);
		return id[p];
	}

	// validate that p is valid index
	private void validate (int p) {
		int n = id.length;
		if (p < 0 || p >= n) {
			throw new IllegalArgumentException("index " + p + " is now between 0 and " + (n-1));
		}
	}

	/**
	 * Returns true if the two sites are in the same components
	 * @param p the integer representing one site
	 * @param q the integer representing the other site
	 * @return {true} if the two sites {p} and {q} are in the same components; {false} otherwise
	 * @throws IllegalArgumentException uncless both {0 <= p < n} and {0 <= q < n}
	 */
	public boolean isConnected (int p, int q) {
		validate (p);
		validate (q);
		return (id[p] == id[q]);
	}

	/** Merges the components containing site {p} with the component containing site {q}
	 *
	 * @param p the integer representing one site
	 * @param q the integer representing the other site
	 * @throws IllegalArgumentException unless both {0 <= p < n} and {0 <= q < n}
	 * 
	 */
	public void union (int p, int q) {
		if (!isConnected(p, q)) {
		    count--;
		    int pID = id[p];
		    int qID = id[q];
			for (int i = 0; i < id.length; i++) {
				if (id[i] == pID) {
					id[i] = qID;
				}
			}

		}
	}

	/** Reads in a sequence of pairs of integers (between 0 and n-1) from standard input,
	 * where each integer represents some sites;
	 * if the sites are in different components, 
	 * merge the two components and print the pair to standard outputs.
	 * @param args the command-line arugments 
	 */
	public static void main(String[] args) {
		int n = StdIn.readInt();
		QuickFindUF uf = new QuickFindUF(n);
		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (uf.isConnected(p, q)) continue;
			uf.union(p, q);
			StdOut.println(p + " " + q);
			StdOut.println(uf.count() + " components");

		}

	}


}