//Shiv Ansal
//May 30

import java.util.Scanner;

public class TreeTester
{
	public static void main ( String [] args )
	{
		TreeTester order = new TreeTester();
		order.mainMenu();
	}

	public void mainMenu ()
	{
		BinarySearchTree list = new BinarySearchTree();

		String choice;
		Scanner console = new Scanner(System.in);

		System.out.println("\n\n");
		do
		{
			System.out.println("\n\n\nBinary Search Tree algorithm menu\n");
			System.out.println("(1) Read data from file");
			System.out.println("(2) Print list ordered by name");
			System.out.println("(3) Search list");
			System.out.println("(4) Delete from list");
			System.out.println("(5) Count nodes in list");
			System.out.println("(6) Clear the list");
			System.out.println("(Q) Quit\n");

			do
			{
				System.out.print("Choice ---> ");
				choice = console.nextLine() + " ";
				System.out.println();
			}
			while((choice.charAt(0) < '1' || choice.charAt(0) > '6') && (choice.charAt(0) != 'Q' && choice.charAt(0) != 'q')); //checks for input 1-6 and not q

			if ('1' <= choice.charAt(0) && choice.charAt(0) <= '6')
			{
				switch (choice.charAt(0))
				{
					case '1' :
						list.loadData();
						break;
					case '2' :
						System.out.println();
						System.out.println("The list printed in order by name\n");
						list.printList();
						System.out.println();
						break;
					case '3' :
						list.find();
						break;
					case '4' :
						list.delete();
						break;
					case '5' :
						list.countNodes();
						break;
					case '6' :
						list.clearAll();
						break;

				}
			}
		}
		while (choice.charAt(0) != 'Q' && choice.charAt(0) != 'q'); //if q then quit
	}
}
