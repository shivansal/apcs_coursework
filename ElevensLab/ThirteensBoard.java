import java.util.List;
import java.util.ArrayList;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class ThirteensBoard extends Board 
{
	/**
	 * The size (number of cards) on the board.
	 */
	private static final int BOARD_SIZE = 10;

	/**
	 * The ranks of the cards for this game to be sent to the deck.
	 */
	private static final String[] RANKS =
		{"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

	/**
	 * The suits of the cards for this game to be sent to the deck.
	 */
	private static final String[] SUITS =
		{"spades", "hearts", "diamonds", "clubs"};

	/**
	 * The values of the cards for this game to be sent to the deck.
	 */
	private static final int[] POINT_VALUES =
		{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

	/**
	 * Flag used to control debugging print statements.
	 */
	private static final boolean I_AM_DEBUGGING = false;


	/**
	 * Creates a new <code>ElevensBoard</code> instance.
	 */
	 public ThirteensBoard() 
	 {
	 	super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
	 }

	/**
	 * Determines if the selected cards form a valid group for removal.
	 * In Elevens, the legal groups are (1) a pair of non-face cards
	 * whose values add to 11, and (2) a group of three cards consisting of
	 * a jack, a queen, and a king in some order.
	 * @param selectedCards the list of the indices of the selected cards.
	 * @return true if the selected cards form a valid group for removal;
	 *         false otherwise.
	 */
	//size == 2 then check for the sum and if size == 1 check for king, return false otherwise
	@Override
	public boolean isLegal(List<Integer> selectedCards) 
	{
		if(selectedCards.size() == 2)
			return containsPairSum13(selectedCards); //call containsPairSum13
		else if(selectedCards.size() == 1) 
			return containsKing(selectedCards); //call containsKing
		else 
			return false;

	}

	/**
	 * Determine if there are any legal plays left on the board.
	 * In Elevens, there is a legal play if the board contains
	 * (1) a pair of non-face cards whose values add to 13, or (2) a king.
	 * @return true if there is a legal play left on the board;
	 *         false otherwise.
	 */
	//Will check for another possible move on the board by calling both the containsKing and containsPairSum13 methods
	@Override
	public boolean anotherPlayIsPossible() 
	{
		List<Integer> temp = cardIndexes(); //calls cardIndexes and saves into Integer List of indices 
		return(containsKing(temp)|| containsPairSum13(temp));
	}

	/**
	 * Check for an 13-pair in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find an 13-pair.
	 * @return true if the board entries in selectedCards
	 *              contain an 13-pair; false otherwise.
	 */
	//Will go through all possible pairs and find value of thirteen 	 
	private boolean containsPairSum13(List<Integer> selectedCards) 
	{
		for(int i = 0; i < selectedCards.size(); i++){ 
			for(int z = 0; z < selectedCards.size(); z++){
				if(i!=z){ //make sure you are not checking yourself (doesn't really matter in this scenario as the sum is not even)
				int sum = 0;
				sum += cardAt(selectedCards.get(i).intValue()).pointValue(); //uses cardAt method to get card
				sum += cardAt(selectedCards.get(z).intValue()).pointValue();
					if(sum == 13)
						return true;	
				}
			}
		}
		return false;		
	}

	/**
	 * Check for a King in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find a King 
	 * @return true if the board entries in selectedCards
	 *              include a king; false otherwise.
	 */
	//Will check for king 
	private boolean containsKing(List<Integer> selectedCards) 
	{

			for(int i = 0; i < selectedCards.size(); i++){
				if((cardAt((selectedCards.get(i)).intValue()).rank()).equals("king")) //uses cardAt method to get card and rank() to see the card type 
					return true;
			}
			return false;
	}
}
