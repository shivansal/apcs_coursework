//Written by Shiv Ansal
//May-18-2017

import java.util.NoSuchElementException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SinglyLinkedList
{
	private ListNode first;
	private ListNode last;

	public SinglyLinkedList()
	{
		first = null;
		last = null;
	}

	public Object getFirst()
	{
		if (first == null)
			throw new NoSuchElementException();
		else
			return first.getValue();
	}

	public void addFirst(Object value)
	{
		first = new ListNode(value, first);

	}

	public void addLast(Object value)
	{
		if(first!=null){
			ListNode temp = first;
			while(temp.getNext() != null){ //will find the last ListNode
				temp = temp.getNext();
			}
			temp.setNext(new ListNode(value,null)); //sets the next ListNode to the end
			last = temp.getNext();
		}
		else{
			addFirst(value);
		}
	}

	public void printList()
	{
		ListNode temp = first; // start at the first node
		while (temp != null)
		{
			System.out.println(temp.getValue());
			temp = temp.getNext(); // go to next node
		}
	}

	public void loadData(){
		String choice;
		Scanner console = new Scanner(System.in);
		Scanner infile = OpenFile.openToRead("senator.txt");
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
		  addLast(new Senator(name,years,bMonth,bDay,bYear,party,state,tMonth,tDay,tYear)); //creates a senator
		}
		infile.close();
	}

	//method to sort(bubble sort) all the ListNode's by year
	public void bubbleSortByYear(){
		for(int i = 0; i < getSize(); i++){
			ListNode current = first;
			ListNode previous = null;
			ListNode next = first.getNext();
			ListNode chLast = last;
				while(current != chLast && current!= null){
					Senator c = (Senator)current.getValue();
					Senator n = (Senator)current.getNext().getValue();
					if(c.compareTo((n))>0){ //if current is greater than next
						swapAdjacent(previous, current, next); //swaps the adjacent values
					}
					previous = current;
					current = current.getNext();
					if(current != null)
						next = current.getNext();
				}
			chLast = current;
		}
	}

	//swaps the current value with next value
	private void swapAdjacent(ListNode previous, ListNode current, ListNode next){
		if(first == current && last == next){ //if LinkedList only has 2 nodes
			first = next;
			last = current;
			first.setNext(last);
			last.setNext(null);
		}
		else if(first==current){ //if current is first
			current.setNext(next.getNext());
			next.setNext(current);
			first = next;
		}
		else if(last == next){ //if next is last
			previous.setNext(next);
			current.setNext(null);
			next.setNext(current);
			last = current;
		}
		else{ //all other cases
			previous.setNext(next);
			current.setNext(next.getNext());
			next.setNext(current);
		}
	}

	//returns the total nodes
	public int getSize(){
		int i = 0;
		ListNode current = first;
		while(current!=null){
			i++;
			current = current.getNext();
		}
		return i;
	}

	//removes all nodes from LinkedList
	public void clear(){
		first = null;
	}

	//uses selection sort to alphabetically sort all the nodes
	public void selectionSortName(){
		ListNode chFirst = first; //the Node that is choosen to be set to the lowest value
		ListNode current = null;
		ListNode leastVal = first;
		for(int o = 0; o < getSize(); o++){
			current = leastVal;
			for (int i = 0; i < getSize()-o; i++){
				Senator c = (Senator)current.getValue();
				Senator n = (Senator)leastVal.getValue();
				if(c.getName().compareTo((n.getName()))<0){ //if the current value is alphabetically before the next value
					leastVal = current;
				}
				current = current.getNext();
			}
			Senator least = (Senator)leastVal.getValue(); //saves the senator object in the least node
			leastVal.setValue((Senator)chFirst.getValue());
			chFirst.setValue(least); //swap values between least and chFirst
			chFirst = chFirst.getNext();  
			leastVal = chFirst;
		}
	}

	//prints the nodes backwards
	public void printBackwards(){
		ListNode current = null;
		for(int i = 0; i < getSize(); i++){
			current = first;
			for(int z = 0 ; z < getSize()-i-1; z++){
				current = current.getNext();
			}
			System.out.println((Senator)current.getValue());
		}
	}

	//method to find a certain node
		public void testFind(){
			boolean run = true;
			String input = "";
			Scanner console = new Scanner(System.in);
			while(run){
				System.out.print("Enter a Senator's name to be searched (Q to quit):");
				input = console.nextLine(); //takes input
				if(input.equalsIgnoreCase("Q")){ //if q then quit
					run = false;
				}
				else{
					ListNode found = null;
					ListNode current = first;
					while(current!=null && current.getNext() != null){
						Senator o = null;
						if(current != first){
							 o = (Senator)current.getNext().getValue();
						}
						else{
							 o = (Senator)current.getValue();
						}
						String name = o.getName();
						if(name.equalsIgnoreCase(input)){
							found = current; //if found then set found to current
						}
						current = current.getNext();
					}
					if(found == null){ //if not found
						System.out.println("\n"+input+" could not be found.\n");
					}
					else{ //if found
						if(found == first)
							System.out.println("\n"+(Senator)found.getValue()+"\n");
						else{
							System.out.println("\n"+(Senator)found.getNext().getValue()+"\n");
						}
					}
				}
			}
		}

		//method to delete a node
		public void testDelete(){
			boolean run = true;
			String input = "";
			Scanner console = new Scanner(System.in);
			while(run){
				System.out.print("Enter a Senator's name to delete (Q to quit):");
				input = console.nextLine(); //takes in input
				if(input.equalsIgnoreCase("Q")){ //if q then quit
					run = false;
				}
				else{
					ListNode found = null;
					ListNode current = first;
					while(current!=null && current.getNext() != null){
						Senator o = null;
						if(current != first){
							 o = (Senator)current.getNext().getValue();
						}
						else{
							 o = (Senator)current.getValue();
						}
						String name = o.getName();
						if(name.equalsIgnoreCase(input)){
							found = current; //if found then set found to current
						}
						current = current.getNext();
					}
					if(found == null){ //if not found
						System.out.println("\n"+input+" could not be deleted.\n");
					}
					else{ //if found
						if(found == first){
							first = first.getNext();
						}
						else if(found.getNext()==last){
							last = found;
							last.setNext(null);
						}
						else{
							ListNode delete = found.getNext();
							found.setNext(delete.getNext());
						}
						System.out.println("\n"+input+" was deleted.\n");

					}
				}
			}
		}
	}
