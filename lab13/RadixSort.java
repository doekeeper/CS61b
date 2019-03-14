/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {

    /**
     * Find the length of the longest string in an array of strings
     * @param a - an array of strings
     * @return the length of longest String
     */
    private static int findLongestLength(String[] a) {
        int longest = 0;
        for (String s: a) {
            if (s.length() > longest) longest = s.length();
        }
        return longest;
    }

    /**
     * find the char presentation for specific char in a string
     * @param i, ith string in the array
     * @param d, dth char in the string
     * @param a, input string array
     * @return, if d >= 0 && d < a[i].length(), return a[i].charAt(d);
     * else, return 0, which means least value to sort.
     */
    private static int findCharAtInString(int i, int d, String[] a) {
        if (d < 0 || d >= a[i].length()) return 0;
        return a[i].charAt(d);
    }
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        int n = asciis.length;
        int R = 256;        // extended ASCII alphabet size.
        String[] aux = new String[n];
        int w = findLongestLength(asciis);  // w is the length of the longest string in asciis
        for (int d = w - 1; d >= 0; d--) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[R + 1];
            for (int i = 0; i < n; i++) {
                int c = findCharAtInString(i, d, asciis);
                count[c + 1]++;
            }
            //Transform counts to indices
            for (int i = 1; i < R; i++) {
                count[i + 1] += count[i];
            }
            // distribute the records
            for (int i = 0; i < n; i++) {
                int c = findCharAtInString(i, d, asciis);
                aux[count[c]++] = asciis[i];
            }
            // copy back
            for (int i = 0; i < n; i++) {
                asciis[i] = aux[i];
            }
        }
        return asciis;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort


        return;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
}
