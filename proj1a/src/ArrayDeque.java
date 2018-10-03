
public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;

    /** constructor
     *
     */
    public ArrayDeque(){
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = items.length-1;
        nextLast = 0;
    }
    /** resize the arraylist (items) to store more item
     * activated when current arraylist is full
     */
    private void resize(int size){
        T[] a = (T[]) new Object[size];
        if (nextFirst<nextLast){
            System.arraycopy(items,nextFirst+1,a,nextFirst+1, size());
        } else {
            if(nextFirst!=items.length-1 && nextLast!=0){
                System.arraycopy(items, 0,a,0,nextLast);
                int newNextFirst = a.length-(items.length-nextFirst);
                System.arraycopy(items, nextFirst+1,a,newNextFirst+1, items.length-nextFirst-1);
                nextFirst = newNextFirst;
            } else if(nextFirst==items.length-1) {
                System.arraycopy(items, 0, a, 0, size());
                nextFirst = a.length-1;
            } else {
                int newNextFirst = a.length-(items.length-nextFirst);
                System.arraycopy(items, nextFirst+1, a, newNextFirst+1, items.length-nextFirst-1);
                nextFirst = newNextFirst;
            }
        }
        items = a;
    }

    /** add item to the beginning of the list
     * warning: haven't considered the case when the array needs to be expanded (size = items.length)
     */
    public void addFirst(T item){
        if (size==items.length-1){
            resize(size*2);
        }
        items[nextFirst] = item;
        size++;
        if(nextFirst>0){
            nextFirst--;
        } else{
            nextFirst=items.length-1;
        }
    }

    /** add item to the end of the list
     * warning: haven't considered the case when the array needs to be expanded (size = items.length)
     */
    public void addLast(T item){
        if(size==items.length-1){
            resize(size*2);
        }
        items[nextLast]=item;
        size++;
        if(nextLast<items.length-1){
            nextLast++;
        } else {
            nextLast = 0;
        }
    }

    /** check if the list is empty */
    public boolean isEmpty(){
        if(nextFirst==items.length-1 && nextLast==0) {
            return true;
        } else {
            if(nextFirst+1==nextLast){
                return true;
            } else {
                return false;
            }
        }
    }

    /** return size of the list */
    public int size(){
        return size;
    }

    /** print the whole list */
    public void printList(){
        System.out.println("Printing list...");
        int p = nextFirst+1;                        // let p points to the location of first item (nextFirst+1);
        while (p<items.length){                     // if p location exceed the length of list, then move p to position 0 of the array; if not, proceed
            if(p==nextLast){                        // if p reaches the end of the list, loop completed and end the function;
                return;                             // function ended as it reaches the end of list;
            } else {                                // if it has reached the end of the list, print the item at location p
                System.out.print(items[p]+" ");
            }
            p++;                                    // move p to next location
        }
        p = 0;                                      // if p location exceed the length of list, move p to position 0
        while (p<items.length && p!=nextLast){      // if p is not exceeding the length of list and p doesn't reach the end of list, continue print
            System.out.print(items[p]+ " ");
            p++;                                    // move p to next location
        }
        System.out.println("");
        System.out.println("End of list...");
    }

    /** remove the first item in the list */
    public T removeFirst(){
        if(isEmpty()){
            System.out.println("This is empty list");
            return null;
        } else {
            size--;
            T temp;
            if(nextFirst+1==items.length){
                temp = items[0];
                nextFirst = 0;
                items[nextFirst]=null;
            } else {
                temp = items[nextFirst+1];
                nextFirst++;
                items[nextFirst]=null;
            }
            if((double) size/items.length<0.25){
                resize(items.length/2);
            }
            return temp;
        }
    }

    /** remove the last item in the list */
    public T removeLast(){
        if(isEmpty()){
            System.out.println("Empty List...");
            return null;
        } else {
            size--;
            T temp;
            if(nextLast==0){
                temp = items[items.length-1];
                nextLast = items.length-1;
                items[nextLast]=null;
            } else {
                temp = items[nextLast-1];
                nextLast--;
                items[nextLast]=null;
            }
            if((double) size/items.length<0.25){
                resize(items.length/2);
            }
            return temp;
        }
    }

    /** get the item at the given index */
    public T get(int index){
        if (index>=0 && index<size()){
            if (nextFirst+1+index<items.length){
                return items[nextFirst+1+index];
            } else {
                return items[nextLast-(size()-index)];
            }
        } else {
            System.out.println("Invalid input, index is out of range");
            return null;
        }
    }


    public static void main(String[] args){
        ArrayDeque L = new ArrayDeque<Integer>();
        System.out.println("Is the list empty: "+ L.isEmpty());
        System.out.println("Size of the list: " + L.size());
        L.addFirst(10);
        L.addFirst(5);
        L.addFirst(0);
        L.addFirst(-5);
        L.addFirst(-10);
        L.printList();
        L.addLast(15);
        L.addLast(20);
        L.addLast(25);
        L.printList();
       // L.addLast(30);
       // L.addLast(35);
        L.removeFirst();
        L.removeFirst();
        L.removeFirst();
        L.removeFirst();
        L.removeFirst();
        L.printList();
        System.out.println(L.get(1));

    }
}
