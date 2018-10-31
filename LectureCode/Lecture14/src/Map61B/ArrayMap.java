package Map61B;

import org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Iterator;

/**
 * An array based implementation of the Map61B class.
 */
public class ArrayMap<K, V> implements Map61B<K, V>, Iterable <K> {

    private K[] keys;
    private V[] values;
    private int size;

    public ArrayMap() {
        keys = (K[]) new Object[100];
        values = (V[]) new Object[100];
    }

    public Iterator<K> iterator() {
        return new KeyIterator();
    }
    /* man-made Iterator(nested class) */
    class KeyIterator implements Iterator <K> {
        private int wizardPosition;

        public KeyIterator() {
            wizardPosition = 0;
        }
        public boolean hasNext() {
            return wizardPosition < size;
        }

        public K next() {
            K returnVal = keys[wizardPosition];
            wizardPosition ++;
            return returnVal;
        }

    }


    /** Return the index of the given key if it exists,
     * -1 otherwise.
     */
    public int keyIndex(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean containsKey(K key) {
        int index = keyIndex(key);
        return index>-1;
    }

    @Override
    public void put(K key, V value) {
        int index = keyIndex(key);
        if (index == -1) {
            keys[size] = key;
            values[size] = value;
            size++;
            return;
        } else {
            values[index] = value;
        }
    }

    @Override
    public V get(K key) {
        if (keyIndex(key) == -1) {
            throw new IllegalArgumentException("The key provided " + key + " was not in ArrayMap.");
        }
        return values[keyIndex(key)];
    }

    @Override
    public int size() {
        return size;

    }

    @Override
    public List<K> keys() {
        List<K> keyList = new ArrayList<>();
        for (K key:keys) {
            keyList.add(key);
        }
        return keyList;
    }


    @Test
    public void test() {
        ArrayMap<Integer, Integer> am = new ArrayMap<Integer, Integer>();
        am.put(2, 5);
        int expected = 5;
        assertTrue(am.get(2).equals(expected));
    }

    public static void main(String[] args) {
        ArrayMap<String, Integer> m = new ArrayMap<String, Integer>();
        m.put("horse", 3);
        m.put("fish", 9);
        m.put("house", 10);
    }
}
