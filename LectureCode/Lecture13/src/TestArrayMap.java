import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayMap<K, V> {
    // instantiate an ArrayMap class (key: String, value: String)
    ArrayMap<String, String> m = new ArrayMap<String, String>();

    @Test
    public void testPut() {
        m.put("A", "a");
        String expected = "a";
        assertEquals(expected, m.get("A"));
        }
    @Test
    public void testContainsKey() {
        m.put("B", "b");
        assertTrue(m.containsKey("B"));
        }
    @Test
    public void testGet() {
        m.put("A", "a");
        assertEquals("a", m.get("A"));
        }
        /**
    @Test
    public void testKeys() {
        m.put("B", "b");
        String [] expected = {"A", "B"};
        assertArrayEquals(expected, m.keys());
        }
         */
    @Test
        public void testSize() {
        m.put("A", "a");
        assertEquals(1, m.size());
        }
}
