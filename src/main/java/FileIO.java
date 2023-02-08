import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;


public class FileIO {

	// Global Variables
	public ArrayList<String> Item_name = new ArrayList<String>();
	public ArrayList<Integer> Item_weight = new ArrayList<Integer>();
	public ArrayList<Integer> Item_value = new ArrayList<Integer>();
	int length = 0;
	
	// Get the values from the input file
	public void readInput() {
		// Make sure arrays are clear for new reading
		Item_name.clear();
		Item_weight.clear();
		Item_value.clear();
		length = 0;
		
		// Create File Variable and read
		// Replace MainClass with the name of your main class
		String jarDir;
		try {
			jarDir = ClassLoader.getSystemClassLoader().getResource(".").toURI().toString();
			jarDir = jarDir + "knapsack_input.txt";
			jarDir = jarDir.substring(5, jarDir.length());
			File myFile = new File(jarDir);
			System.out.println("file path: " + myFile.getPath());
			
		    try {
				Scanner myReader = new Scanner(myFile);
				while(myReader.hasNextLine()) {
					Item_name.add(myReader.nextLine());
					Item_weight.add(Integer.parseInt(myReader.nextLine()));
					Item_value.add(Integer.parseInt(myReader.nextLine()));
					length++;
				}
				myReader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("An error has occurred reading the input file");
				e.printStackTrace();
			}
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			System.out.println("Couldn't load file directory");
			e1.printStackTrace();
		}
	}
	
	
	// Getters and Setters
	public ArrayList<String> getItem_name() {
		return Item_name;
	}



	public void setItem_name(ArrayList<String> item_name) {
		Item_name = item_name;
	}



	public ArrayList<Integer> getItem_weight() {
		return Item_weight;
	}



	public void setItem_weight(ArrayList<Integer> item_weight) {
		Item_weight = item_weight;
	}



	public ArrayList<Integer> getItem_value() {
		return Item_value;
	}



	public void setItem_value(ArrayList<Integer> item_value) {
		Item_value = item_value;
	}



	public int getLength() {
		return length;
	}



	public void setLength(int length) {
		this.length = length;
	}


	// Ask the user for weight input
	public int getUserInput() {
		Scanner text = new Scanner(System.in);
		System.out.println("How much Weight? ");
		
		int ret = Integer.parseInt(text.nextLine());
		text.close();
		
		return ret;
	}

	// Main method
	public static void main(String[] args) throws IOException {
		// get the file info
		FileIO file = new FileIO();
		file.readInput();
			
		BTree tree = new BTree();
			
		// buildTree
		ArrayList<BNode> abTree = tree.buildTree(file.Item_name, file.Item_weight, file.Item_value);
			
		// Get user input and traverse the tree, write it out to the file
		int in = file.getUserInput();
		tree.Traverse(abTree, file.getLength(), in);

	}

}