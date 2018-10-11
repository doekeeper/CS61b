public interface Deque<T> {

    void addFirst(T item);

    void addLast(T item);

    boolean isEmpty();

    int size();

    void printDeque();

    /** remove first item in the Deque, and return the item which is removed */
    T removeFirst();

    /** remove last item in the Deque, and return the item which is removed */
    T removeLast();

    /** return the item with ith index in the Deque */
    T get(int index);
}