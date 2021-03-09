package mainPackage;

// Holds the value of the cost as well as a pointer to the Cost that this came from, 
// or {0,0} is there was no previous Cost.
public class Cost {

	public int value;
	private int[] pointer;
	
	public Cost() {
		this.value = 0;
		this.pointer = new int[] {0,0};
	}
	
	public Cost(int value, int[] pointer) {
		this.value = value;
		this.pointer = pointer;
	}
	
	public void setPointer(int[] newPointer) {
		this.pointer = newPointer;
	}
	
	public int[] getPointer() {
		return pointer;
	}
	
}
