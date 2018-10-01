

/** basically SLList is a wrapper of IntNode/IntList list */
public class DLList{
	private IntNode sentinel = new IntNode(100, null);

	public static class IntNode{
		public int item;
		public IntNode next;
		public IntNode(int i, IntNode n){
			this.item = i;
			this.next = n;
		}
	}

	public DLList(int x){
		sentinel.next = new IntNode(x, null);
	}

	/** add an item in the beginning of the list */
	public void addFirst(int i){
		this.sentinel.next = new IntNode (i, sentinel.next);
	}
	/** get 1st item in the list */
	public int getFirst(){
		return this.sentinel.item;
	}

	/** print the list */
	public void printList(){
		IntNode p = this.sentinel;
		while (p.next!=null){
			System.out.println(p.next.item);
			p = p.next;
		}
	}

	/** add item to the end of the list */
	public void addLast(int i){
		IntNode p = this.sentinel;
		while (p.next!=null){
			p = p.next;
		}
		p.next = new IntNode(i, null);
	}

	/** return the size of DLList */
	public int size(){
		int size = 0;
		IntNode p = this.sentinel;
		while(p.next!=null){
			size++;
			p = p.next;
		}
		return size;
	}

	/** return the size of DLList using recursive way
	 * but this recursive method doesn't work for empty list
	 */
	public int sizeRecWrapper(IntNode p){

		if(p.next == null){
			return 1;
		} else {
			return 1+sizeRecWrapper(p.next);
		}
	}
	public int sizeRec(){
		return sizeRecWrapper(this.sentinel.next);
	}

/**
	public void addLast(int x){
		size += 1;
		IntNode p = sentinel;
		while(p.next!=null){
			p = p.next;
		}

		p.next = new IntNode(x, null);
	}

*/

	public static void main(String[] args){
		DLList L = new DLList(15);
		L.printList();
		L.addFirst(10);
		L.addFirst(5);
		L.printList();
		L.addLast(20);
		L.printList();
		System.out.println("Size of the list is: " +L.size());
		System.out.println("Size of the list is: " + L.sizeRec());
	}
}
