package mainPackage;

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
