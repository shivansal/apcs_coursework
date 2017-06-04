/**
 * This is a class that tests the Card class.
 */
public class CardTester 
{
	/**
	 * The main method in this class checks the Card operations for consistency.
	 *	@param args is not used.
	 */
	public static void main(String[] args) 
	{
		Card test1 = new Card("King","Spades",12);
		Card test2 = new Card("King","Spades",12);
		Card test3 = new Card("King","Spades",12);
		System.out.println(test1.rank());
		System.out.println(test1);
		if(test1.matches(test2))
			System.out.println("Works");

	}
}
