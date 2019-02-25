import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    protected static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    protected static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        // Your code here!
        Queue qOfQueue = new Queue<Queue<Item>>();

        for (Item i: items) {
            Queue q = new Queue<Item>();
            q.enqueue(i);
            qOfQueue.enqueue(q);
        }
        return qOfQueue;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    protected static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        // Your code here!
        Queue q3 = new Queue<Item>();
        while(!q1.isEmpty() && !q2.isEmpty()) {
            if (q1.peek().compareTo(q2.peek()) < 0) {
                q3.enqueue(q1.dequeue());
            } else {
                q3.enqueue(q2.dequeue());
            }
        }
        if (q1.isEmpty()) {
            while (!q2.isEmpty()) {
                q3.enqueue(q2.dequeue());
            }
        } else if (q2.isEmpty()) {
            while (!q1.isEmpty()) {
                q3.enqueue(q1.dequeue());
            }
        }
        return q3;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        // Your code here!
        if (items.size() <= 1) {
            return items;
        } else if (items.size() == 2) {
            Queue<Queue<Item>> qOfQueue = makeSingleItemQueues(items);
            return mergeSortedQueues(qOfQueue.dequeue(), qOfQueue.dequeue());
        } else {
            Queue q1 = new Queue<Item>();
            Queue q2 = new Queue<Item>();
            int i = 0;
            for (Item item: items) {
                if (i % 2 == 0) q1.enqueue(item);
                if (i % 2 == 1) q2.enqueue(item);
                i++;
            }
            return mergeSortedQueues(mergeSort(q1), mergeSort(q2));
        }
    }
}

/**
 * if items.size == 1 return items
 * if items.size == 2 return mergeSortedQueues(MakeSingleItemQueues);
 * return mergeSortedQueues(mergeSort(first half of queue), mergeSort(second half of the queue))
 *
 */
