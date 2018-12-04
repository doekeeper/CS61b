package hw2;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestPercolation {

    @Test
    public void testPercolation() {
        Percolation p = new Percolation(10);
        assertFalse(p.isOpen(0,0));
        assertFalse(p.isOpen(9,9));
        // Percolation pNeg = new Percolation(0);
    }

    @Test
    public void testOpen() {
        Percolation p = new Percolation(10);
        p.open(0,0);
        assertTrue(p.isOpen(0, 0));
        assertFalse(p.isOpen(1, 1));
    }

    @Test
    public void testIsOpen() {
        Percolation p = new Percolation(10);
        p.open(0, 0);
        assertTrue(p.isOpen(0,0));
        assertFalse(p.isOpen(1,1));
    }
}
