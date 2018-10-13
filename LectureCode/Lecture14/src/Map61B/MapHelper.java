package Map61B;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Class to demonstrate how generic methods work in Java.
 */
public class MapHelper{
    /* Write the following three methods:
    /* get(Key) : Return item in map if it exists. */

    public static <K, V> V get(Map61B<K, V> sim, K key) {
        if (sim.containsKey(key)) {
            return sim.get(key);
        }
        return null;
    }

    /* maxKey() : Return max of all keys. Works only if x and y have comparable data. */
    public static <K extends Comparable<K>, V> K maxKey(Map61B<K, V> sim) {
        List<K> keyList = sim.keys();
        K largest = keyList.get(0);
        for (K k:keyList) {
            if (k == null) { break; }
            if (k.compareTo(largest) > 0) {
                largest = k;
            }
        }
        return largest;
    }

    @Test
    public void testGet() {
        Map61B<String, Integer> m = new ArrayMap<String, Integer>();
        m.put("horse", 3);
        m.put("fish", 9);
        m.put("house", 10);

        Integer actual = MapHelper.get(m, "fish");
        Integer expected = 9;
        assertEquals(expected, actual);
    }

    @Test
    public void testMaxKey() {
        Map61B<String, Integer> m = new ArrayMap<String, Integer>();
        m.put("horse", 3);
        m.put("fish", 9);
        m.put("house", 10);

        String actual = MapHelper.maxKey(m);
        String expected = "house";
        assertEquals(expected, actual);
    }
}