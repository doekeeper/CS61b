package hw2;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestPercolation {

    @Test
    public void testPercolation() {
        Percolation p = new Percolation(10);
        assertFalse(p.grid[0][0]);
        assertFalse(p.grid[9][9]);
        assertEquals(10, p.grid.length);
        assertEquals(10, p.grid[0].length);
        // Percolation pNeg = new Percolation(0);
    }

    @Test
    public void testOpen() {
        Percolation p = new Percolation(10);
        p.open(0,0);
        assertTrue(p.grid[0][0]);
        assertFalse(p.grid[1][1]);
    }

    @Test
    public void testIsOpen() {
        Percolation p = new Percolation(10);
        p.open(0, 0);
        assertTrue(p.isOpen(0,0));
        assertFalse(p.isOpen(1,1));
    }

    @Test
    public void testIsFull() {
        Percolation p = new Percolation(10);
        p.open(0, 0);
        p.open(0, 1);
        p.open(1, 1);
        p.open(1, 2);
        assertTrue(p.isFull(1, 2));
    }
}
