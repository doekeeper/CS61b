/** add addAdjecent method so that if 2 numbers in a row are the same, 
 * we add them together and make one large node.
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
		if (this.first==this.rest.first){
			this.first*=2;
			this.rest = this.rest.rest;
		}
		while(this.rest!=null){
			this.addAdjecent();
			this.rest = this.rest.rest;
		}
	}

	public static void main(String[] args){
		IntList L = new IntList(1,null);
		L = IntList(1, L);
		L = IntList(2, L);
		L = IntList(4, L);
		L = IntList(5, L);
		L = IntList(5, L);
		L = IntList(5, L);
		L.addAdjecent();
	}
} 