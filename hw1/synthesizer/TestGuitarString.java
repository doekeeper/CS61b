package synthesizer;

/* Since this test is part of a package, we have to import the package version of StdAudio. */
/* Don't worry too much about this, we'll get there in due time. */
import edu.princeton.cs.introcs.StdAudio;

import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the GuitarString class.
 *  @author Josh Hug
 */

public class TestGuitarString {

    @Test
    public void testGuitarString() {
        GuitarString gs = new GuitarString(441.0);
        int actual = gs.buffer.capacity();
        assertEquals(100, actual);
        assertFalse(gs.buffer.isEmpty());
        double item = gs.buffer.dequeue();
        assertEquals(0.0, item, 1e-6);

    }

    @Test
    public void testPluck() {
        GuitarString gs = new GuitarString(440.0);
        gs.pluck();
        ArrayRingBuffer<Double> buffer = (ArrayRingBuffer<Double>) gs.buffer;
        boolean actual = true;
        int count = 0;

        while(!buffer.isEmpty()) {
            double item = buffer.dequeue();
            if (item < -0.5 || item > 0.5) {
                actual = false;
                break;
            }
            if (item == 0) {
                count++;
            }
        }
        if (count == 100) {
            actual = false;
        }
        assertTrue(actual);
    }

    @Test
    public void testPluckTheAString() {
        double CONCERT_A = 440.0;
        GuitarString aString = new GuitarString(CONCERT_A);
        aString.pluck();
        for (int i = 0; i < 50000; i += 1) {
            StdAudio.play(aString.sample());
            aString.tic();
        }
    }

    @Test
    public void testTic() {
        // Create a GuitarString of frequency 11025, which
        // is an ArrayRingBuffer of length 4. 
        GuitarString s = new GuitarString(11025);
        s.pluck();

        // Record the front four values, ticcing as we go.
        double s1 = s.sample();
        s.tic();
        double s2 = s.sample();
        s.tic(); 
        double s3 = s.sample();
        s.tic();
        double s4 = s.sample();

        // If we tic once more, it should be equal to 0.996*0.5*(s1 + s2)
        s.tic();

        double s5 = s.sample();
        double expected = 0.996 * 0.5 * (s1 + s2);

        // Check that new sample is correct, using tolerance of 0.001.
        // See JUnit documentation for a description of how tolerances work
        // for assertEquals(double, double)
        assertEquals(expected, s5, 0.001);

    }


    /** Calls tests for GuitarString. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestGuitarString.class);
    }
} 
