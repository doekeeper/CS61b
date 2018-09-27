/** add addAdjecent method so that if 2 numbers in a row are the same, 
 * we add them together and make one large node.
 */

/** current methods only works for (4,4,5,5) or similar array.
  * it failed test with array (1,1,2,4,10,10,5,5,5), because recursive methods will stay on 
  *	Recommendation - DLList might work better for this case, since it could merge front or back cells as needed
  */ 

public class IntList{
	/** declare fields */
	public int first;
	public IntList rest;
	/** constructor */
	public IntList(int f, IntList r){
		this.first = f;
		this.rest = r;
	}

	/** addAdjecent method */
	public void addAdjecent(){
		/** no change is needed if there is only one item in the linked list
			"this.rest == null" means there is only one item in the linked list
		 */ 
		int prev_size = this.size();
		if(this.rest==null){
			return;
		} else {
			IntList prev = this.rest;
			IntList next = prev.rest;
			if(this.first == prev.first){
				this.first*=2;
				this.rest = prev.rest;
			} else {
				while(prev!=null && next!=null){
					if(prev.first ==next.first){
						prev.first*=2;
						prev.rest = next.rest;
						break;
					} else {
						prev = prev.rest;
						next = next.rest;
					}
				}
			}
		}
		int current_size = this.size();
		if(prev_size==current_size){
			return;
		} else {
			this.addAdjecent();
		}
	}

	/* return the size of the linked list */
	public int size(){
		IntList p = this.rest;
		int size = 1;
		while (p!=null){
			size++;
			p = p.rest;
		}
		return size;
	}
	/** print linked list */
	public void printList(){
		IntList p = this.rest;
		System.out.println(this.first);
		while(p!=null){
			System.out.println(p.first);
			p = p.rest;
		}
	}
	/** Square the IntList when add a value
	*/
	public void squareListAdd(int n){

	}

	public static void main(String[] args){
		IntList L = new IntList(10, null);
		L = new IntList(10, L);
		L = new IntList(5, L);
		L = new IntList(5, L);
		L = new IntList(4, L);
		L = new IntList(2, L);
		L = new IntList(1, L);
		L = new IntList(1, L);
		L.printList();
		System.out.println("size before addAdjecent: " + L.size());
		L.addAdjecent();
		L.printList();
		System.out.println("size after addAdjecent: " + L.size());
	}
}