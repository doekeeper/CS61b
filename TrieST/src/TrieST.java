/**
 * code example of Trie from https://algs4.cs.princeton.edu/52trie/TrieST.java.html
 * TrieST is a string symbol table for extended ASCII strings, implemented using a 256-way trie
 */

import edu.princeton.cs.algs4.Queue;

/**
 * The TrieST class represents an symbol table of key-vale pairs with string keys and generic values.
 * It supports the usual 'put', 'get', 'contains', 'delete', 'size' and 'is-empty' methods.
 * It also provides character-based methods for finding the string in the symbol table that is
 * the longest prefix of a given prefix, finding all strings in the symbol table that 'match'
 * a given pattern. A symbol table implements the 'associative array' abstraction:
 * When associating a value with a key that is already in the symbol table, the convention is
 * to replace the old value with the new value. Unlike {java.util.Map}， this class uses the
 * convention tat values cannot be {null}- setting the value associated with a key to {null}
 * is equivalent to deleting the key from the symbol table.
 * This implementation uses a 256-way trie.
 * The 'put', 'contains', 'delete', and 'longest prefix' operations take time proportional to
 * the length of key (in the worst case).
 * The construction takes constant time. The 'size' and 'is-empty' operations take constant time.
 * Construction takes constant time.
 */

public class TrieST<Value> {
    private static final int R = 256;       //extend ASCII

    private Node root;      // root of trie
    private int n;          // number of keys in trie

    // R-way trie node
    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    /**
     * Initializes an empty string symbol table.
     * ST means symbol table
     */
    public TrieST() {
    }

    /**
     * Returns the value associated with the given key.
     *
     * @param key the key
     * @return the value associated with the given key if the key is the symbol table
     * and null if the key is not in the symbol table
     * @throws IllegalArgumentException if key is null
     */
    public Value get(String key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.val;
    }

    /**
     * @param x   input node
     * @param key search key in a form of String
     * @param d
     * @return
     */
    private Node get(Node x, String key, int d) {
        if (x == null) return null;     // if the node doesn't exist, return null
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);    // search next char in the String
    }

    public boolean contains(String key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    /**
     * Inserts the key-value pair into the symbol table, overwriting the old value
     * with the new value if the key is already in the symbol table.
     * If th value is null, this effectively deletes the key from the symbol table
     *
     * @param key the key
     * @param val the value
     * @throws IllegalArgumentException if key is null
     */
    public void put(String key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) delete(key);
        else root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) x = new Node();      // create a new node if it is currently empty
        if (d == key.length()) {
            if (x.val == null) n++;         // if x is newly added node, then n++
            x.val = val;                    // assign val to x.val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d + 1);  // update subtrie x
        return x;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table.
     */
    public int size() {
        return n;
    }

    /**
     * Is this symbol table empty?
     *
     * @return true if this symbol table is empty and false otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns all keys in the symbol table as Iterable.
     * To iterate over all of the keys in the symbol table named st,
     * use the foreach notation: for (Key key: st.key())
     *
     * @return all keys in the symbol table as an Iterable
     */
    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> results = new Queue<String>();        // construct an empty queue for storing Strings
        Node x = get(root, prefix, 0);                  // return the nodes of last char in prefix
        collect(x, new StringBuilder(prefix), results);     // collect all the keys with prefix and store them in queue results
        return results;
    }

    private void collect(Node x, StringBuilder prefix, Queue<String> results) {
        if (x == null) return;                                  // if Node x is empty node, then return nothing
        if (x.val != null)
            results.enqueue(prefix.toString());  // if Node x's value is NOT empty, add prefix key to string queue
        for (char c = 0; c < R; c++) {                          // iterate c from 0 to R
            prefix.append(c);                                   // add c to prefix string
            collect(x.next[c], prefix, results);                // explore all the existing keys with current prefix (with added char)
            prefix.deleteCharAt(prefix.length() - 1);           // when all the keys with added char are found, remove the most recently added char
        }
    }

    /**
     * Returns all of the keys in the symbol table that match pattern, where . symbol is
     * treated as a wildcard character
     *
     * @param pattern the pattern
     * @return all of the keys in the symbol table that match pattern, as an iterable,
     * where . is treated as a wildcard character.
     */
    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> results = new Queue<>();
        collect(root, new StringBuilder(), pattern, results);
        return results;
    }

    private void collect(Node x, StringBuilder prefix, String pattern, Queue<String> results) {
        if (x == null) return;
        int d = prefix.length();
        if (d == pattern.length() && x.val != null)
            results.enqueue(prefix.toString());
        if (d == pattern.length()) return;
        char c = pattern.charAt(d);
        if (c == '.') {
            for (char ch = 0; ch < R; ch++) {
                prefix.append(ch);
                collect(x.next[ch], prefix, pattern, results);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        } else {
            prefix.append(c);
            collect(x.next[c], prefix, pattern, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    public String longestPrefixOf(String query) {
        if (query == null) throw new IllegalArgumentException("argument to longestPrefix() is null");
        int length = longestPrefixOf(root, query, 0, -1);
        if (length == -1) return null;
        else return query.substring(0, length);
    }

    private int longestPrefixOf(Node x, String query, int d, int length) {
        if (x == null) return length;
        if (x.val != null) length = d;
        if (d == query.length()) return length;
        char c = query.charAt(d);
        return longestPrefixOf(x.next[c], query, d+1, length);
    }

    /**
     * Removes the key from the set if the key is present
     * @param key the key
     * @throws IllegalArgumentException if key is null
     */
    public void delete(String key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) {
            if (x.val != null) n--;
            x.val = null;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d+1);
        }
        // remove subtrie rooted at x if it is completely empty
        if (x.val != null) return x;
        for (int c = 0; c < R; c++) {
            if (x.next[c] != null) return x;
        }
        return null;
    }

    public static void main(String[] args) {
        // build symbol table from standard input
        TrieST<Integer> st = new TrieST<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        // print results
        if (st.size() < 100) {
            StdOut.println("keys(\"\"):");
            for (String key : st.keys()) {
                StdOut.println(key + " " + st.get(key));
            }
            StdOut.println();
        }
        StdOut.println("longestPrefixOf(\"shellsort\"):");
        StdOut.println(st.longestPrefixOf("shellsort"));
        StdOut.println();

        StdOut.println("longestPrefixOf(\"quicksort\"):");
        StdOut.println(st.longestPrefixOf("quicksort"));
        StdOut.println();

        StdOut.println("keysWithPrefix(\"shor\"):");
        for(String s: st.keysWithPrefix("shor"))
            StdOut.println(s);
        StdOut.println();

        StdOut.println("keysThatMatch(\".he.l.\"):");
        for(String s: st.keysThatMatch(".he.l."))
            StdOut.println(s);
    }
}
