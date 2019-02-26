import static org.junit.Assert.*;
import org.junit.Test;
import edu.princeton.cs.algs4.Queue;

public class TestQuickSort {

    @Test
    public void testPartition() {
        Queue unsorted = new Queue<Integer>();
        Queue less = new Queue<Integer>();
        Queue equal = new Queue<Integer>();
        Queue greater = new Queue<Integer>();
        Queue lessExpected = new Queue<Integer>();
        Queue equalExpected = new Queue<Integer>();
        Queue greaterExpected = new Queue<Integer>();
        int pivot = 4;

        unsorted.enqueue(9);
        unsorted.enqueue(5);
        unsorted.enqueue(7);
        unsorted.enqueue(4);
        unsorted.enqueue(4);
        unsorted.enqueue(4);
        unsorted.enqueue(1);
        unsorted.enqueue(2);
        unsorted.enqueue(3);

        lessExpected.enqueue(1);
        lessExpected.enqueue(2);
        lessExpected.enqueue(3);

        equalExpected.enqueue(4);
        equalExpected.enqueue(4);
        equalExpected.enqueue(4);

        greaterExpected.enqueue(9);
        greaterExpected.enqueue(5);
        greaterExpected.enqueue(7);

        QuickSort.partition(unsorted, pivot, less, equal, greater);
        for (Object i : less) {
            assertTrue(i.equals(lessExpected.dequeue()));
        }
        for (Object i : equal) {
            assertTrue(i.equals(equalExpected.dequeue()));
        }
        for (Object i : greater) {
            assertTrue(i.equals(greaterExpected.dequeue()));
        }
    }

    @Test
    public void testQuickSort() {
        Queue unsorted = new Queue<Integer>();
        Queue sorted = new Queue<Integer>();
        Queue sortedExpected = new Queue<Integer>();

        unsorted.enqueue(9);
        unsorted.enqueue(5);
        unsorted.enqueue(7);
        unsorted.enqueue(4);
        unsorted.enqueue(4);
        unsorted.enqueue(4);
        unsorted.enqueue(1);
        unsorted.enqueue(2);
        unsorted.enqueue(3);

        sortedExpected.enqueue(1);
        sortedExpected.enqueue(2);
        sortedExpected.enqueue(3);
        sortedExpected.enqueue(4);
        sortedExpected.enqueue(4);
        sortedExpected.enqueue(4);
        sortedExpected.enqueue(5);
        sortedExpected.enqueue(7);
        sortedExpected.enqueue(9);

        sorted = QuickSort.quickSort(unsorted);
        for (Object i : sorted) {
            assertTrue(i.equals(sortedExpected.dequeue()));
        }
    }
}
