/** An SLList is a list of integers, which hides the terrible truth
 *	of the nakedness within*/
 public class SLList{

 	private static class IntNode{
 		private int item;
 		IntNode next;

 		private IntNode(int i, IntNode n){
		item = i;
		next = n;
		}
	}

 	private IntNode first;
 	private int size;
 	/** The first item (if it exists) is at sentinel.next.
 	 */
 	private IntNode sentinel;

 	public SLList(int x){
 		sentinel = new IntNode(63, null);
 		sentinel.next = new IntNode(x, null);
 		size = 1;
 	}

 	/** Creates an empty SLList */
 	public SLList(){
 		sentinel = new IntNode(63, null);
 		size = 0;
 	}
 	/** Add an item to the end of the list */
 	public void addFirst(int x){
 		sentinel.next = new IntNode(x, sentinel.next);
 		size++;
 	}

 	/** delete the first item in the list*/
 	public void deleteFirst(){
 		size--;
 		sentinel.next = sentinel.next.next;
	}

 	/** Add an item to the end of the list */
 	public void addLast(int x){
 		size++;
 		IntNode p = sentinel;
 		while (p.next!=null){
 			p = p.next;
 		}
 		p.next = new IntNode(x, null);
 	}

 	/** Return the size of SLList - the iterative way*/
 	public int size(SLList L){
 		int size = 1;
 		while(L.first.next!=null){
 			L.first = L.first.next;
 			size++;
 		}
 		return size;
 	}

 	/** Return the size of SLList - the recursive way */
 	/** In order to do that, a private-type helper method is needed */
 	private static int size(IntNode p){
 		if(p.next == null){
 			return 1;
 		} else {
 			return 1+size(p.next);
 		}
 	}
 	public int size (){
 		return size(sentinel.next);
 	}

 	/** Returns the first item in the list. */
 	/** getNthItem is not working properly, as first is not point to the correct reference point */
 	public int getNthItem(int n){
 		while(n!=0){
 			first = first.next;
 			n--;
 		}
 		return first.item;
 	}

 	public int getFirst(){
 		return sentinel.next.item;
 	}

 	public static void main(String[] args){
 		/* Creates a list of one integer, namely 10 */
 		SLList L = new SLList(15);
 		L.addFirst(10);
 		L.addFirst(5);
 		L.addLast(20);
 		System.out.println(L.getFirst());
 		System.out.println("size of SLList is: " + L.size());
 		L.deleteFirst();
 		System.out.println("size of SLList is: " + L.size());
 	}
 }