import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class FileIO {

	// Global Variables
	ArrayList<String> Item_name = new ArrayList<String>();
	ArrayList<Integer> Item_weight = new ArrayList<Integer>();
	ArrayList<Integer> Item_value = new ArrayList<Integer>();
	int length = 0;
	
	public void readInput() {
		// Make sure arrays are clear for new reading
		Item_name.clear();
		Item_weight.clear();
		Item_value.clear();
		length = 0;
		
		// Create File Variable and read
		File myFile = new File("knapsack_input.txt");
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
	    
	    length = length / 3;
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



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}