package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    ArrayRingBuffer<String> rb = new ArrayRingBuffer<String>(4);
    @Test
    public void testArrayRingBuffer() {
        int actual = rb.capacity;
        assertEquals(4, actual);
        assertTrue(rb.isEmpty());
        assertFalse(rb.isFull());
    }
    @Test
    public void testEnqueue() {
        rb.enqueue("Ada");
        int actual = rb.fillCount();
        assertEquals(1, actual);
        assertFalse(rb.isEmpty());
    }

    @Test
    public void testDeQueue() {
        rb.enqueue("Ada");
        rb.enqueue("Betty");
        String actual1 = rb.dequeue();
        String actual2 = rb.dequeue();
        assertTrue(actual1.equals("Ada"));
        assertTrue(actual2.equals("Betty"));
        assertTrue(rb.isEmpty());
    }

    @Test
    public void testPeek() {
        rb.enqueue("Ada");
        rb.enqueue("Betty");
        String actual = rb.peek();
        assertTrue(actual.equals("Ada"));
    }


    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
