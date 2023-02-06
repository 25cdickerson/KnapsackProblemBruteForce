
public class BNode {
	// Variables
	//Links to other nodes in the tree
	public BNode no;
	public BNode yes;
	public String name;
	public int weight;
	public int value;
	
	public BNode(String name, int weight, int value) {
		this.name = name;
		this.weight = weight;
		this.value = value;
	}

	// Getters and Setters
	public BNode getNo() {
		return no;
	}

	public void setNo(BNode no) {
		this.no = no;
	}

	public BNode getYes() {
		return yes;
	}

	public void setYes(BNode yes) {
		this.yes = yes;
	}
	
	
	
	
	
}
