
/**
 * This is a class that tests the Deck class.
 */
public class DeckTest 
{
	/**
	 * The main method in this class checks the Deck operations for consistency.
	 *	@param args is not used.
	 */
	public static void main(String[] args) 
	{
		String[] ranks = {"Freshman", "Sophomore", "Junior", "Senior"};
		String[] suits = {"Purple", "Gold", "Black"};
		int[] pointValues = {9, 10, 11, 12};
		Deck d = new Deck(ranks, suits, pointValues);

		System.out.println();
		System.out.println();
		System.out.println("**** Original Deck Methods ****");
		System.out.println("  toString:\n" + d.toString());
		System.out.println("  isEmpty: " + d.isEmpty());
		System.out.println("  size: " + d.size());
		System.out.println();
		System.out.println();

		System.out.println("**** Deal a Card ****");
		System.out.println("  deal: " + d.deal());
		System.out.println();
		System.out.println();

		System.out.println("**** Deck Methods After 1 Card Dealt ****");
		System.out.println("  toString:\n" + d.toString());
		System.out.println("  isEmpty: " + d.isEmpty());
		System.out.println("  size: " + d.size());
		System.out.println();
		System.out.println();

		System.out.println("**** Deal Remaining 11 Cards ****");
		for (int i = 0; i < 11; i++) {
			System.out.println("  deal: " + d.deal());
		}
		System.out.println();
		System.out.println();

		System.out.println("**** Deck Methods After All Cards Dealt ****");
		System.out.println("  toString:\n" + d.toString());
		System.out.println("  isEmpty: " + d.isEmpty());
		System.out.println("  size: " + d.size());
		System.out.println();
		System.out.println();

		System.out.println("**** Deal a Card From Empty Deck ****");
		System.out.println("  deal: " + d.deal());
		System.out.println();
		System.out.println();
		
		ranks = new String[] {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight"};
		suits = new String[] {"Earth", "Air", "Fire", "Water"};
		pointValues = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
		d = new Deck(ranks, suits, pointValues);

		System.out.println("**** Original Deck Methods ****");
		System.out.println("  toString:\n" + d.toString());
		System.out.println("  isEmpty: " + d.isEmpty());
		System.out.println("  size: " + d.size());
		System.out.println();
		System.out.println();

		System.out.println("**** Deal a Card ****");
		System.out.println("  deal: " + d.deal());
		System.out.println();
		System.out.println();

		System.out.println("**** Deck Methods After 1 Card Dealt ****");
		System.out.println("  toString:\n" + d.toString());
		System.out.println("  isEmpty: " + d.isEmpty());
		System.out.println("  size: " + d.size());
		System.out.println();
		System.out.println();

		System.out.println("**** Deal Next 31 Cards ****");
		for (int i = 0; i < 31; i++) {
			System.out.println("  deal: " + d.deal());
		}
		System.out.println();
		System.out.println();

		System.out.println("**** Deck Methods After All Cards Dealt ****");
		System.out.println("  toString:\n" + d.toString());
		System.out.println("  isEmpty: " + d.isEmpty());
		System.out.println("  size: " + d.size());
		System.out.println();
		System.out.println();

		System.out.println("**** Deal a Card From Empty Deck ****");
		System.out.println("  deal: " + d.deal());
		System.out.println("  deal: " + d.deal());
		System.out.println("  deal: " + d.deal());
		System.out.println();
		System.out.println();

	}
}

/*

C:\Java\Elevens>java DeckTest


**** Original Deck Methods ****
  toString:
size = 12
Undealt cards:
Senior of Black (point value = 12), Senior of Gold (point value = 12),
Senior of Purple (point value = 12), Junior of Black (point value = 11),
Junior of Gold (point value = 11), Junior of Purple (point value = 11),
Sophomore of Black (point value = 10), Sophomore of Gold (point value = 10),
Sophomore of Purple (point value = 10), Freshman of Black (point value = 9),
Freshman of Gold (point value = 9), Freshman of Purple (point value = 9)

Dealt cards:


  isEmpty: false
  size: 12


**** Deal a Card ****
  deal: Senior of Black (point value = 12)


**** Deck Methods After 1 Card Dealt ****
  toString:
size = 11
Undealt cards:
Senior of Gold (point value = 12), Senior of Purple (point value = 12),
Junior of Black (point value = 11), Junior of Gold (point value = 11),
Junior of Purple (point value = 11), Sophomore of Black (point value = 10),
Sophomore of Gold (point value = 10), Sophomore of Purple (point value = 10),
Freshman of Black (point value = 9), Freshman of Gold (point value = 9),
Freshman of Purple (point value = 9)
Dealt cards:
Senior of Black (point value = 12)

  isEmpty: false
  size: 11


**** Deal Remaining 11 Cards ****
  deal: Senior of Gold (point value = 12)
  deal: Senior of Purple (point value = 12)
  deal: Junior of Black (point value = 11)
  deal: Junior of Gold (point value = 11)
  deal: Junior of Purple (point value = 11)
  deal: Sophomore of Black (point value = 10)
  deal: Sophomore of Gold (point value = 10)
  deal: Sophomore of Purple (point value = 10)
  deal: Freshman of Black (point value = 9)
  deal: Freshman of Gold (point value = 9)
  deal: Freshman of Purple (point value = 9)


**** Deck Methods After All Cards Dealt ****
  toString:
size = 0
Undealt cards:

Dealt cards:
Senior of Black (point value = 12), Senior of Gold (point value = 12),
Senior of Purple (point value = 12), Junior of Black (point value = 11),
Junior of Gold (point value = 11), Junior of Purple (point value = 11),
Sophomore of Black (point value = 10), Sophomore of Gold (point value = 10),
Sophomore of Purple (point value = 10), Freshman of Black (point value = 9),
Freshman of Gold (point value = 9), Freshman of Purple (point value = 9)


  isEmpty: true
  size: 0


**** Deal a Card From Empty Deck ****
  deal: null


**** Original Deck Methods ****
  toString:
size = 32
Undealt cards:
Eight of Water (point value = 8), Eight of Fire (point value = 8),
Eight of Air (point value = 8), Eight of Earth (point value = 8),
Seven of Water (point value = 7), Seven of Fire (point value = 7),
Seven of Air (point value = 7), Seven of Earth (point value = 7),
Six of Water (point value = 6), Six of Fire (point value = 6),
Six of Air (point value = 6), Six of Earth (point value = 6),
Five of Water (point value = 5), Five of Fire (point value = 5),
Five of Air (point value = 5), Five of Earth (point value = 5),
Four of Water (point value = 4), Four of Fire (point value = 4),
Four of Air (point value = 4), Four of Earth (point value = 4),
Three of Water (point value = 3), Three of Fire (point value = 3),
Three of Air (point value = 3), Three of Earth (point value = 3),
Two of Water (point value = 2), Two of Fire (point value = 2),
Two of Air (point value = 2), Two of Earth (point value = 2),
One of Water (point value = 1), One of Fire (point value = 1),
One of Air (point value = 1), One of Earth (point value = 1)

Dealt cards:


  isEmpty: false
  size: 32


**** Deal a Card ****
  deal: Eight of Water (point value = 8)


**** Deck Methods After 1 Card Dealt ****
  toString:
size = 31
Undealt cards:
Eight of Fire (point value = 8), Eight of Air (point value = 8),
Eight of Earth (point value = 8), Seven of Water (point value = 7),
Seven of Fire (point value = 7), Seven of Air (point value = 7),
Seven of Earth (point value = 7), Six of Water (point value = 6),
Six of Fire (point value = 6), Six of Air (point value = 6),
Six of Earth (point value = 6), Five of Water (point value = 5),
Five of Fire (point value = 5), Five of Air (point value = 5),
Five of Earth (point value = 5), Four of Water (point value = 4),
Four of Fire (point value = 4), Four of Air (point value = 4),
Four of Earth (point value = 4), Three of Water (point value = 3),
Three of Fire (point value = 3), Three of Air (point value = 3),
Three of Earth (point value = 3), Two of Water (point value = 2),
Two of Fire (point value = 2), Two of Air (point value = 2),
Two of Earth (point value = 2), One of Water (point value = 1),
One of Fire (point value = 1), One of Air (point value = 1),
One of Earth (point value = 1)
Dealt cards:
Eight of Water (point value = 8)

  isEmpty: false
  size: 31


**** Deal Next 31 Cards ****
  deal: Eight of Fire (point value = 8)
  deal: Eight of Air (point value = 8)
  deal: Eight of Earth (point value = 8)
  deal: Seven of Water (point value = 7)
  deal: Seven of Fire (point value = 7)
  deal: Seven of Air (point value = 7)
  deal: Seven of Earth (point value = 7)
  deal: Six of Water (point value = 6)
  deal: Six of Fire (point value = 6)
  deal: Six of Air (point value = 6)
  deal: Six of Earth (point value = 6)
  deal: Five of Water (point value = 5)
  deal: Five of Fire (point value = 5)
  deal: Five of Air (point value = 5)
  deal: Five of Earth (point value = 5)
  deal: Four of Water (point value = 4)
  deal: Four of Fire (point value = 4)
  deal: Four of Air (point value = 4)
  deal: Four of Earth (point value = 4)
  deal: Three of Water (point value = 3)
  deal: Three of Fire (point value = 3)
  deal: Three of Air (point value = 3)
  deal: Three of Earth (point value = 3)
  deal: Two of Water (point value = 2)
  deal: Two of Fire (point value = 2)
  deal: Two of Air (point value = 2)
  deal: Two of Earth (point value = 2)
  deal: One of Water (point value = 1)
  deal: One of Fire (point value = 1)
  deal: One of Air (point value = 1)
  deal: One of Earth (point value = 1)


**** Deck Methods After All Cards Dealt ****
  toString:
size = 0
Undealt cards:

Dealt cards:
Eight of Water (point value = 8), Eight of Fire (point value = 8),
Eight of Air (point value = 8), Eight of Earth (point value = 8),
Seven of Water (point value = 7), Seven of Fire (point value = 7),
Seven of Air (point value = 7), Seven of Earth (point value = 7),
Six of Water (point value = 6), Six of Fire (point value = 6),
Six of Air (point value = 6), Six of Earth (point value = 6),
Five of Water (point value = 5), Five of Fire (point value = 5),
Five of Air (point value = 5), Five of Earth (point value = 5),
Four of Water (point value = 4), Four of Fire (point value = 4),
Four of Air (point value = 4), Four of Earth (point value = 4),
Three of Water (point value = 3), Three of Fire (point value = 3),
Three of Air (point value = 3), Three of Earth (point value = 3),
Two of Water (point value = 2), Two of Fire (point value = 2),
Two of Air (point value = 2), Two of Earth (point value = 2),
One of Water (point value = 1), One of Fire (point value = 1),
One of Air (point value = 1), One of Earth (point value = 1)


  isEmpty: true
  size: 0


**** Deal a Card From Empty Deck ****
  deal: null
  deal: null
  deal: null
*/

