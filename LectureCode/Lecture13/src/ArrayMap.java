// Excercise - level C

public class ArrayMap<K, V> {
    private int size;
    private K [] keys;
    private V [] values;

    public ArrayMap() {
        size = 0;
        keys  = (K []) new Object[100];
        values = (V []) new Object[100];
    }

    // associate key with value
    public void put(K key, V value) {
        keys[size] = key;
        values[size] = value;
        size ++;
    }

    // check to see if arraymap contains the key
    public boolean containsKey(K key) {
        for (K i: keys) {
            if (i == key) {
                return true;
            }
        }
        return false;
    }

    // returns value, assuming key exists
    public V get(K key) {
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (keys[i] == key) {
                index = i;
                return values[index];
            }
        }
        if (index == -1) {
            throw new IllegalArgumentException("the key provide " + key + "was not in ArrayMap");
        }
        return null;
    }

    // return a list of all keys
    public K[] keys() {

        return keys;
    }

    // return numbers of keys
    public int size() {
        return size;
    }
}
