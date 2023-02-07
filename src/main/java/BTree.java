import java.util.ArrayList;

public class BTree {
	
	public BNode root;

	// Constructor
	public BTree() {
		root = null;
	}
	
	// Generate BNode ArrayList
	public ArrayList<BNode> genBNodeList(ArrayList<String> Item_name, ArrayList<Integer> Item_weight, ArrayList<Integer> Item_value){
		ArrayList<BNode> arr = new ArrayList<BNode>();
		for(int i = 0; i < Item_name.size(); i++) {
			arr.add(new BNode(Item_name.get(i), Item_weight.get(i), Item_value.get(i)));
		}
		
		return arr;
	}
	
	
	// Need to figure out how to build tree here
	public ArrayList<BNode> buildTree(ArrayList<String> Item_name, ArrayList<Integer> Item_weight, ArrayList<Integer> Item_value) {
		ArrayList<BNode> arr = genBNodeList(Item_name, Item_weight, Item_value);
		
		ArrayList<Integer> numNodes = new ArrayList<Integer>();
		for(int i = 0; i < arr.size(); i++) {
			numNodes.add((int) Math.pow(2, i));
		}
		
		
		ArrayList<BNode> temp = new ArrayList<BNode>();
		ArrayList<BNode> ret = new ArrayList<BNode>();
		for(int i = 0; i < numNodes.size(); i++) {
			BNode curr = arr.get(i);
			temp.clear();
			for(int x = 0; x < numNodes.get(i); x++) {
				temp.add(curr);
			}
			ret.addAll(temp);
		}
		return ret;
	}
	
	public BNode leftChild(int parentIndex, ArrayList<BNode> ret) {
		return ret.get(2 * parentIndex + 1);
	}
	
	public BNode rightChild(int parentIndex, ArrayList<BNode> ret) {
		return ret.get(2 * parentIndex + 2);
	}
	
}