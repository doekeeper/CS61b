// Unit Test Class for QuickFindUF.java

import static org.junit.Assert.*;
import org.junit.*;

public class TestQuickFindUF {



    @Test
    public void testQuickFindUF () {
        int n = 10; // # of elements
        QuickFindUF uf = new QuickFindUF(n);
        int [] IDArray = uf.getIDArray();
        for (int i = 0; i < 10; i++) {
            assertEquals(i, IDArray[i]);
        }
    }

    @Test
    public void testCount() {
        int n = 10; // # of elements
        QuickFindUF uf = new QuickFindUF(n);
        assertEquals(n, uf.count());
        uf.union(1, 2);
        assertEquals(9, uf.count());
        assertTrue(uf.isConnected(1,2));
        uf.union(3, 4);
        assertTrue(uf.isConnected(3, 4));
        assertEquals(8, uf.count());
        uf.union(1,4);
        assertTrue(uf.isConnected(1,4));
        assertTrue(uf.isConnected(1,3));
        assertTrue(uf.isConnected(2,3));
        assertTrue(uf.isConnected(2,4));
        assertEquals(7, uf.count());
        uf.union(2, 3);
        assertEquals(7, uf.count());
    }
}