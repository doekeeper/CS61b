// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;
import java.util.Iterator;
import java.util.ArrayList;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue <T> implements Iterable <T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /* implementation of iterator() method*/
    public Iterator<T> iterator() {
        ArrayList <T> al = new ArrayList <T>();
        return al.iterator();
    }

    /* nested class for iterator */
    class MyIterator <T> implements Iterator <T>{
        public boolean hasNext() {
            return first!=last;
        }
        public T next() {
            return (T) dequeue();
        }
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (fillCount == capacity()) {
            throw new RuntimeException("Ring buffer overflow");
        } else {
            fillCount++;
            rb[last] = x;
            if (last + 1 == capacity) {
                last = 0;
            } else {
                last++;
            }
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        T dequeuedItem;
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            fillCount--;
            dequeuedItem = rb[first];
            if (first == capacity() - 1) {
                first = 0;
            } else {
                first++;
            }
        }
        return dequeuedItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (fillCount() == 0) {
            throw new RuntimeException("Empty Queue...");
        } else {
            return rb[first];
        }
        // TODO: Return the first item. None of your instance variables should change.
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
}
