import java.util.Scanner;

public class SenatorList
{
	public static void main ( String [] args )
	{
		SenatorList order = new SenatorList();
		order.mainMenu();
	}

	public void mainMenu ()
	{
		SinglyLinkedList list = new SinglyLinkedList();

		String choice;
		Scanner console = new Scanner(System.in);

		System.out.println("\n\n");
		do
		{
			System.out.println("\nLinked List algorithm menu\n");
			System.out.println("(1) Read data from file");
			System.out.println("(2) Print list ordered by birth day");
			System.out.println("(3) Search list");
			System.out.println("(4) Delete from list");
			System.out.println("(5) Clear entire list");
			System.out.println("(6) Count nodes in list");
			System.out.println("(7) Print list backwards by birth day");
			System.out.println("(8) Print list ordered by name");
			System.out.println("(Q) Quit\n");
			do
			{
				System.out.print("Choice ---> ");
				choice = console.nextLine() + " ";
				System.out.println();
			}
			while((choice.charAt(0) < '1' || choice.charAt(0) > '8') && (choice.charAt(0) != 'Q' && choice.charAt(0) != 'q'));

			if ('1' <= choice.charAt(0) && choice.charAt(0) <= '8')
			{
				switch (choice.charAt(0))
				{
					case '1' :
						list.loadData();
						break;
					case '2' :
						System.out.println();
						System.out.println("The list printed in order by year\n");
						list.bubbleSortByYear();
						list.printList();
						System.out.println();
						break;
					case '3' :
						list.testFind();
						break;
					case '4' :
						list.testDelete();
						break;
					case '5' :
						list.clear();
						break;
					case '6' :
						System.out.println("Number of nodes = " + list.getSize ());
						System.out.println();
						break;
					case '7' :
						System.out.println();
						System.out.println("The list printed in reverse order by year\n");
						list.bubbleSortByYear();
						list.printBackwards();
						System.out.println();
						break;
					case '8' :
						System.out.println();
						System.out.println("The list printed in order by name\n");
						list.selectionSortName();
						list.printList();
						System.out.println();
						break;
				}
			}
		}
		while (choice.charAt(0) != 'Q' && choice.charAt(0) != 'q');
	}
}
