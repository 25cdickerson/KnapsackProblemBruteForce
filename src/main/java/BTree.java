import java.util.ArrayList;

public class BTree {
	
	private BNode node;

	// Constructor
	public BTree() {
		node = null;
	}
	
	
	// Need to figure out how to build tree here
	public void buildTree(ArrayList<String> Item_name, ArrayList<Integer> Item_weight, ArrayList<Integer> Item_value, int length) {
		node = new BNode(Item_name.get(0), Item_weight.get(0), Item_value.get(0));
		BNode temp = node;
		for(int i = 0; i < Item_name.size(); i++) {
			
		}
	}
	
}
