/**
 * A symbol table implemented with a separate-chaining hash table.
 */

/**
 * The SeparateChainingHashST class represents a symbol table of generic key-value pairs.
 * It supports the usual put, get, contains, delete, size, and is-empty methods.
 * It also provides a keys emthods for iterating over all of the keys.
 * A symbol table implements the associative array abstraction:
 * when associating a value with a key that is already in the symbol table,
 * the convention is to replace the old value with the new value.
 * Unlike java.util.Map, this class uses the convention that values cannot be null
 * setting the value associated with a key to null is equivalent to deleting the key
 * from the symbol table.
 * This implementation uses a separate chaining hash table. It requires that the key
 * type overrides the equals() and hashcode() methods.
 * The expected time per put, contains, or remove operation is constant,
 * subject to the uniform hashing assumption.
 * The size, and is-empty operation is constant time. Construction takes constant time.
 */

public class SeparateChainingHashST <Key, Value> {
    private static final int INIT_CAPACITY = 4;

    private int n;                                  // number of key-value pairs
    private int m;                                  // hash table size
    private SequentialSearchST<Key, Value>[] st;    // array of linked-list tables

    /**
     * Initialize an empty symbol table
     */
    public SeparateChainingHashST() {
        this(INIT_CAPACITY);                        // constructor override
    }

    /**
     * Initialize an empty symbol table with m chains.
     * @param m the initial number of chains
     */
    public SeparateChainingHashST(int m) {
        this.m = m;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];      // st's length is m
        for (int i = 0; i < m; i++) {
            st[i] = new SequentialSearchST<Key, Value>();                       // Each element in st is an object of SequentialSearchST
        }
    }

    // resize the hash table to have the given number of chains
    // rehashing all of the keys
    private void resize(int chains) {
        SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<Key, Value>(chains);
        for (int i = 0; i < m; i++) {
            for (Key key: st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.m = temp.m;
        this.n = temp.n;
        this.st = temp.st;
    }

    // hash value between 0 and m-1
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
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
     * Returns true if this symbol table is empty
     *
     * @return true if this symbol table is empty; false otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns true if this symbol table contains the specified key.
     *
     * @param key the key
     * @return true if this symbol table contains key; false otherwise
     * @throws IllegalArgumentException if key is null
     */
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    /**
     * Returns the values associated with the specified key in this symbol table.
     *
     * @param key the key
     * @return the value associated with key in the symbol table
     * @throws IllegalArgumentException is key is null
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        int i = hash(key);
        return st[i].get(key);
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old value
     * with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is null.
     *
     * @param key the key
     * @param val the value
     * @throws IllegalArgumentException if key is null
     */



}
