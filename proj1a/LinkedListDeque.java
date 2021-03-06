/** Project 1A for CS 61b; The intent is to create a doubly linked list (DLList)
 * @param <T> - the type of data stored in the DLList
 */

public class LinkedListDeque<T> {

    /** fields required for DLList */
    private int size;           // store the size of DLList
    private Node sentinel = new Node();      // store the sentinel of DLList

    /** nested class Node for creating backbone
     * of individual node including Node prev / next and stored item;
     */
    private class Node{
        Node prev;
        T item;
        Node next;
        public Node(T item){
            this.item = item;
        }
        public Node(){}
        public T getRecursiveHelper(int index){
            if (index == 0){
                return this.next.item;
            } else {
                return this.next.getRecursiveHelper(index-1);
            }
        }
    }

    /** Constructor for an empty LinkedListDeque */
    public LinkedListDeque(){
        size = 0;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }
    /** Constructor for LinkedListDeque with item
    public LinkedListDeque(T item){
        size = 1;
        sentinel.next = new Node(item);
        sentinel.next.next = sentinel;
        sentinel.next.prev = sentinel;
        sentinel.prev = sentinel.next;
    }
     */

    /** add item to the front of the list
     * @param item - item to be added to the list
     */
    public void addFirst(T item){
        size++;
        Node p = sentinel.next;             // temporary pointer
        sentinel.next = new Node(item);     // add new Node with new item to sentinel.next (1st place in DLList)
        sentinel.next.prev = sentinel;      // new Node's prev points back to sentinel
        sentinel.next.next = p;             // new Node's next points to the rest of the DLList
        p.prev = sentinel.next;             // 1st Node in the remainder of the list points back to the newly added Node
    }

    /** add item to the end of the list
     * @param item
     */
    public void addLast(T item){
        size++;
        sentinel.prev.next = new Node(item);        // add new Node to the current last Node's next
        sentinel.prev.next.prev = sentinel.prev;    // newly added Node's prev points to earlier Node
        sentinel.prev.next.next = sentinel;         // newly added Node's next points to sentinel
        sentinel.prev = sentinel.prev.next;         // sentinel's prev points to newly added Node
    }
    /** check if the list is empty or not */
    public boolean isEmpty(){
        if(sentinel.next==sentinel){
            return true;
        } else {
            return false;
        }
    }

    /** return the size of the list */
    public int size() {
        return size;
    }

    /** print the list */
    public void printDeque(){
       // System.out.println("Print List...");
        Node p = sentinel;
        while(p.next!=sentinel){
            System.out.print(p.next.item+" ");
            p = p.next;
        }
        System.out.println("");
        // System.out.println("End of List...");
    }
    /** remove the first item in the list*/
    public T removeFirst(){
        if(sentinel.next==sentinel){
            System.out.println("It's already an empty list...");
            return null;
        } else {
            size--;
            T temp = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            return temp;
        }
    }
    /** remove the last item in the list */
    public T removeLast(){
        if(sentinel.next==sentinel){
            System.out.println("It's already an empty list...");
            return null;
        } else {
            size--;
            T temp;
            temp = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            return temp;
        }
    }

    /** get ith item in the list
     * 0th item is the first item in the list
     * @param index - position in the list
     */
    public T get(int index){
        if (index<0){
            System.out.println("Input is not valid, index has to be zero or positive integer. Please re-enter...");
            return null;
        } else if (size==0) {
            System.out.println("This is empty list.");
            return null;
        } else if (index>size-1) {
            System.out.println("Index is out of range. Please re-enter...");
            return null;
        } else {
            Node p = sentinel;
            while(index!=0){
                p = p.next;
                index--;
            }
            return p.next.item;
        }
    }
    public T getRecursive(int index){
        return sentinel.getRecursiveHelper(index);
    }
}
