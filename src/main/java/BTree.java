import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class BTree {
	
	public BNode root;

	// Constructor
	public BTree() {
		root = null;
	}
	
	// Generate BNode ArrayList
	// Generating based on the given names, weights, and values
	public ArrayList<BNode> genBNodeList(ArrayList<String> Item_name, ArrayList<Integer> Item_weight, ArrayList<Integer> Item_value){
		ArrayList<BNode> arr = new ArrayList<BNode>();
		for(int i = 0; i < Item_name.size(); i++) {
			arr.add(new BNode(Item_name.get(i), Item_weight.get(i), Item_value.get(i)));
		}
		
		return arr;
	}
	
	
	// Build Binary Tree
	// Each level of the tree has a singular item on it
	public ArrayList<BNode> buildTree(ArrayList<String> Item_name, ArrayList<Integer> Item_weight, ArrayList<Integer> Item_value) {
		ArrayList<BNode> arr = genBNodeList(Item_name, Item_weight, Item_value);
		
		// Generate levels of the tree and add them to the list
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
		
		// Set the indices of each node
		for(int i = 0; i < ret.size(); i++) {
			ret.get(i).index = i;
		}
		
		return ret;
	}
	
	// Get the NO for a parent
	public BNode leftChild(int parentIndex, ArrayList<BNode> ret) {
		// if out of bounds, return null
		if((2 * parentIndex + 1) > ret.size() ) {
			return null;
		}
		
		return ret.get(2 * parentIndex + 1);
	}
	
	// Get the YES for a parent
	public BNode rightChild(int parentIndex, ArrayList<BNode> ret) {
		// if out of bounds, return null
		if((2 * parentIndex + 2) > ret.size() ) {
			return null;
		}
		
		return ret.get(2 * parentIndex + 2);
	}
	
	// Generate Binary Strings For Traversal
	public ArrayList<String> genBinStrings(int length){
		ArrayList<String> binStrings = new ArrayList<String>();
		
		// loop through each option of a binary string of some length, add to the binary array
		for (int i=0; i < (Math.pow(2,length)); i++) {
			if(Integer.toBinaryString(i).length() == length) {
				binStrings.add(Integer.toBinaryString(i));
			}
			else {
				// Make all of the lesser bin strings the given length (To get all combo)
				String bin = Integer.toBinaryString(i);
				StringBuilder binBuilder = new StringBuilder(bin);
				while(binBuilder.length() < length){
					binBuilder.insert(0, '0');
				}
				binStrings.add(binBuilder.toString());
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
		
		// Create new writer to write to a file
		String jarDir;
		BufferedWriter out;
		//try {
			jarDir = System.getProperty("user.dir");
			jarDir = jarDir + File.separator + "output.txt";
			//jarDir = jarDir.substring(6, jarDir.length());
			out = new BufferedWriter(new FileWriter(jarDir));
	
			
			
			
			// Loop through each of the binary strings
			for(int i = 0; i < binStrings.size(); i++) {
				trav = tree.get(0);
				currentVal = 0;
				currentWeight = 0;
				
				// loop though each value within the binary strings
				for(int x = 0; x < binStrings.get(i).length();  x++) {
					// if zero, go left, if one go right and add the current values and weights
					if(0 == Integer.parseInt(String.valueOf(binStrings.get(i).charAt(x)))) {
						trav = leftChild(trav.index, tree);
					}
					else if(1 == Integer.parseInt(String.valueOf(binStrings.get(i).charAt(x)))) {
						currentVal = currentVal + trav.value;
						currentWeight = currentWeight + trav.weight;
						trav = rightChild(trav.index, tree);
					}
				}
				// Write the value of the current path
				out.write(binStrings.get(i) + '\n');
				out.write(Integer.toString(currentWeight) + '\n');
				out.write(Integer.toString(currentVal) + '\n');
				
				// Compare current and best value
				if((currentVal > bestVal) && (currentWeight <= weight)) {
					bestVal = currentVal;
					bestWeight = currentWeight;
					bestBinString = binStrings.get(i);			
				}
			}
			
			// Write out the best value
			out.write(bestBinString + '\n');
			out.write(Integer.toString(bestWeight) + '\n');
			out.write(Integer.toString(bestVal));
			out.close();
		/*} catch (URISyntaxException e) {
			System.out.println("Cannot write to output file");
			e.printStackTrace();
		}*/
	}
}