
import org.junit.Test;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.Queue;

public class TestMergeSort {
    @Test
    public void testGetMin() {
        Queue q1 = new Queue<Integer>();
        Queue q2 = new Queue<Integer>();
        q1.enqueue(1);
        q2.enqueue(2);
        assertEquals(1, MergeSort.getMin(q1, q2));
    }

    @Test
    public void testMakeSingleItemQueues() {
        String A = "Alice";
        String B = "Bob";
        String C = "Cara";
        String P = "Peter";
        Queue q1 = new Queue<String>();
        Queue q2 = new Queue<Queue>();
        Queue q3 = new Queue<Queue>();
        q1.enqueue(A);
        q1.enqueue(P);
        q1.enqueue(C);
        q1.enqueue(B);
        Queue singleQ1 = new Queue<String>();
        singleQ1.enqueue(A);
        Queue singleQ2 = new Queue<String>();
        singleQ2.enqueue(P);
        Queue singleQ3 = new Queue<String>();
        singleQ3.enqueue(C);
        Queue singleQ4 = new Queue<String>();
        singleQ4.enqueue(B);
        q2.enqueue(singleQ1);
        q2.enqueue(singleQ2);
        q2.enqueue(singleQ3);
        q2.enqueue(singleQ4);
        q3 = MergeSort.makeSingleItemQueues(q1);
        assertEquals(q3.dequeue().toString(), q2.dequeue().toString());
        assertEquals(q3.dequeue().toString(), q2.dequeue().toString());
        assertEquals(q3.dequeue().toString(), q2.dequeue().toString());
        assertEquals(q3.dequeue().toString(), q2.dequeue().toString());
    }

    @Test
    public void testMergeSort() {
        Queue q1 = new Queue<Integer>();
        Queue q2 = new Queue<Integer>();
        Queue q3 = new Queue<Integer>();
        Queue q4 = new Queue<Integer>();
        for (int i = 0; i < 10; i = i + 2) {
            q1.enqueue(i);
            q2.enqueue(i + 1);
        }
        for (int i = 0; i < 10; i++) {
            q3.enqueue(i);
        }
        q4 = MergeSort.mergeSortedQueues(q1, q2);
        while (!q3.isEmpty() && !q4.isEmpty()) {
            assertEquals(q3.dequeue().toString(),q4.dequeue().toString());
        }
    }

    @Test
    public void testMergeSortedQueues() {
        Queue q1 = new Queue<Integer>();
        Queue q2 = new Queue<Integer>();
        Queue q3 = new Queue<Integer>();

        for (int i = 0; i < 10; i = i + 2) {
            q1.enqueue(i);
        }
        for (int i = 1; i < 10; i = i + 2) {
            q1.enqueue(i);
        }
        for (int i = 0; i < 10; i++) {
            q2.enqueue(i);
        }
        q3 = MergeSort.mergeSort(q1);
        while(!q1.isEmpty() && !q2.isEmpty()) {
            assertEquals(q2.dequeue(), q3.dequeue());
        }
    }
}
