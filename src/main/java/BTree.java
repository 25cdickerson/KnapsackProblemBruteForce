import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
	
	
	// Build Binary Tree
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
		
		for(int i = 0; i < ret.size(); i++) {
			ret.get(i).index = i;
		}
		
		return ret;
	}
	
	// Get the NO for a parent
	public BNode leftChild(int parentIndex, ArrayList<BNode> ret) {
		if((2 * parentIndex + 1) > ret.size() ) {
			return null;
		}
		
		return ret.get(2 * parentIndex + 1);
	}
	
	// Get the YES for a parent
	public BNode rightChild(int parentIndex, ArrayList<BNode> ret) {
		if((2 * parentIndex + 2) > ret.size() ) {
			return null;
		}
		
		return ret.get(2 * parentIndex + 2);
	}
	
	// Generate Binary Strings For Traversal
	public ArrayList<String> genBinStrings(int length){
		ArrayList<String> binStrings = new ArrayList<String>();
		
		String zeroStr = "";
		for(int i = 0; i < length; i++) {
			zeroStr = zeroStr + '0';
		}
		
		binStrings.add(zeroStr);
		
		for (int i=0; i < (Math.pow(2,length)); i++) {
			if(Integer.toBinaryString(i).length() == length) {
				binStrings.add(Integer.toBinaryString(i));
			}
		}
		
		return binStrings;
	}
	
	// Traverse the tree
	public void Traverse(ArrayList<BNode> tree, int numItems, int weight) throws IOException {
		ArrayList<String> binStrings = genBinStrings(numItems);
		int currentVal = 0;
		int currentWeight = 0;
		int bestVal = 0;
		int bestWeight = 0;
		String bestBinString = binStrings.get(0);
		BNode trav;
		
		BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
		
		
		for(int i = 0; i < binStrings.size(); i++) {
			trav = tree.get(0);
			currentVal = 0;
			currentWeight = 0;
			
			for(int x = 0; x < binStrings.get(i).length();  x++) {
				if(0 == Integer.parseInt(String.valueOf(binStrings.get(i).charAt(x)))) {
					trav = leftChild(trav.index, tree);
				}
				else if(1 == Integer.parseInt(String.valueOf(binStrings.get(i).charAt(x)))) {
					currentVal = currentVal + trav.value;
					currentWeight = currentWeight + trav.weight;
					trav = rightChild(trav.index, tree);
				}
			}
			out.write(binStrings.get(i) + '\n');
			out.write(Integer.toString(currentWeight) + '\n');
			out.write(Integer.toString(currentVal) + '\n');
			System.out.println("Current Val: " + currentVal + " Current Weight: " + currentWeight + " Current Bin String: " + binStrings.get(i));
			if((currentVal > bestVal) && (currentWeight <= weight)) {
				bestVal = currentVal;
				bestWeight = currentWeight;
				bestBinString = binStrings.get(i);			
			}
		}
		out.write(bestBinString + '\n');
		out.write(Integer.toString(bestWeight) + '\n');
		out.write(Integer.toString(bestVal) + '\n');
		out.close();
		System.out.println("Best Val: " + bestVal + " Best Weight: " + bestWeight + " Best Bin String: " + bestBinString);
	}
	
	
}