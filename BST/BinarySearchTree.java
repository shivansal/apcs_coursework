//Written by Shiv Ansal 
//May 30

import java.util.NoSuchElementException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class BinarySearchTree
{
	private TreeNode root = null;

	public BinarySearchTree ( )
	{
	}
	

	public void loadData() //loads the data from the text file
	{
		String choice;
		Scanner console = new Scanner(System.in);
		System.out.print("Enter a file name: ");
		Scanner infile = OpenFile.openToRead(console.nextLine());
		String name = "";
		int bMonth = 0;
		int bDay = 0;
		int bYear = 0;
		int years = 0;
		String party = "";
		String state = "";
		int tMonth = 0;
		int tDay = 0;
		int tYear = 0;
		while(infile.hasNext()){
		  name = infile.next();
		  bMonth = infile.nextInt();
		  bDay = infile.nextInt();
		  years = infile.nextInt();
		  bYear = infile.nextInt();
		  party = infile.next();
		  state = infile.next();
		  tMonth = infile.nextInt();
		  tDay = infile.nextInt();
		  tYear = infile.nextInt();
			addValue(new Senator(name,years,bMonth,bDay,bYear,party,state,tMonth,tDay,tYear)); //creates a senator and calls the addValue method
		}
		infile.close();
	}

	public void addValue(Object o){ //takes an object and then adds it the tree with a call to add
		root = add(o, root); //sets root to the TreeNode returned by add
	}


	private TreeNode add(Object s, TreeNode r){ //will add the passed Object
		if(r == null){ 
			r = new TreeNode(s); //if there is no BST(root is null) then it sets the root
		}
		else{
			int d = (((Senator)s).getName()).compareTo(((Senator)r.getValue()).getName());
			if(d < 0) //if less than go left
				r.setLeft(add(s, r.getLeft()));
			if(d > 0) //if greater than go right
				r.setRight(add(s, r.getRight()));
		}
		return r;
	}

	public void countNodes(){ //returns the number of nodes in the BST
		System.out.println("Number of nodes = "+counter(root)); //calls the counter method
	}

	private int counter(TreeNode r){ //counts the number of nodes
		if(r == null){ //if the node is null then 0
			return 0;
		}
		if(r.getLeft() == null && r.getRight() == null) //if no children then only 1
			return 1;
		return 1 + counter(r.getLeft()) + counter(r.getRight()); //return 1(this node) + all the nodes in the subtree
	}


	public void printList(){ //calls printValues method with the root 
		printValues(root);
	}

	private void printValues(TreeNode r){
		if(r==null) //if the node is null then don't do anything
			return;
		printValues(r.getLeft()); //otherwise use in order printing
		System.out.println(r.getValue());
		printValues(r.getRight());
	}

	public void clearAll(){  //sets the root to null therefore clearing the whole tree
		root = null;
	}

	public void find(){
		boolean run = true;
		String input = "";
		Scanner console = new Scanner(System.in);
		while(run){ //tales the user input
			System.out.print("Enter a Senator's name to be searched (Q to quit): ");
			input = console.nextLine(); //takes input
			System.out.println("\n");
			if(input.equalsIgnoreCase("Q")){ //if q then quit
				run = false;
			}
			else{
				TreeNode check = findName(input, root); //calls the findName method with the input from user
				if(check != null) //if found
					System.out.println(check.getValue());
				else //if not
					System.out.println(input+" could not be found.");
				System.out.println("\n");
			}
		}
	}

	private TreeNode findName(String s, TreeNode r){ //finds a certain senator name in the BST
		if(r == null) //if the node is null, returns null (input was not found)
			return r;
		int d = s.toLowerCase().compareTo(((Senator)r.getValue()).getName().toLowerCase());
		if(d == 0)
				return r;
		else if(d > 0) //if greater than go right
			return findName(s, r.getRight());
		else //if less than go left
			return findName(s, r.getLeft());
	}

	public void delete(){
		boolean run = true;
		String input = "";
		Scanner console = new Scanner(System.in);
		while(run){ //takers user input
			System.out.print("Enter a Senator's name to be removed (Q to quit): ");
			input = console.nextLine(); //takes input
			if(input.equalsIgnoreCase("Q")){ //if q then quit
				run = false;
			}
			else{ 
				root = deleteNode(input, root); //sets root to the return Node of delete
				System.out.println("\n"+input+" was removed from the BST. \n"); 
			}
		}
	}

	private TreeNode deleteNode(String s, TreeNode r){ //deletes a certain senator in the BST
		if(r == null)
			return r;
		int d = s.toLowerCase().compareTo(((Senator)r.getValue()).getName().toLowerCase());
		if(d > 0) //if greater than go right
			r.setRight(deleteNode(s, r.getRight()));
		else if(d < 0) //if less than go left
			r.setLeft(deleteNode(s, r.getLeft()));
		else{
			if(r.getLeft() == null) //if only child is right
				return r.getRight();
			else if(r.getRight() == null) //if only child is left
				return r.getLeft();

		TreeNode tempRoot = root;
		while(root.getLeft() != null){
			tempRoot = tempRoot.getLeft(); //gets the most left(least) node
		}
		r.setValue(tempRoot.getValue()); //sets the value to least value 
		String find = ((Senator)(r.getValue())).getName();
		r.setRight(deleteNode(find, r.getRight())); //sets the right value to the node returned by the call to deleteNode(with the string of the most left (least) node)
	}

	return r;
}

}
