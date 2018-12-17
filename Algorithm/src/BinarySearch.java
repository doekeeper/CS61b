// Fail to test the outcome, as I don't know how to pass an array of integers to main method via args

import java.util.Arrays;

public class BinarySearch {

    public static int rank (int key, int[] a) {
        // Array must be sorted.
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // key is in a [lo..hi] or not present
            int mid = lo + (hi - lo)/2;
            if (key < a[mid]) {
                hi = mid -1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;      // return -1 if no match is found.
    }

    public static void main(String[] args) {
        int[] whiteList = In.readInts(args[0]);
        Arrays.sort(whiteList);
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if(rank(key, whiteList) < 0) {
                StdOut.println(key);
            }
        }
    }
}
