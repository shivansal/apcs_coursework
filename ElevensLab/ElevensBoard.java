import java.util.List;
import java.util.ArrayList;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class ElevensBoard extends Board 
{
	/**
	 * The size (number of cards) on the board.
	 */
	private static final int BOARD_SIZE = 9;

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
		{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0};

	/**
	 * Flag used to control debugging print statements.
	 */
	private static final boolean I_AM_DEBUGGING = false;


	/**
	 * Creates a new <code>ElevensBoard</code> instance.
	 */
	 public ElevensBoard() 
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
	//size == 2 then check for the sum and if size == 3 check for JQK, return false otherwise
	@Override
	public boolean isLegal(List<Integer> selectedCards) 
	{
		if(selectedCards.size() == 2)
			return containsPairSum11(selectedCards); //call containsPairSum11
		else if(selectedCards.size() == 3) 
			return containsJQK(selectedCards); //call containsJQK
		else 
			return false;

	}

	/**
	 * Determine if there are any legal plays left on the board.
	 * In Elevens, there is a legal play if the board contains
	 * (1) a pair of non-face cards whose values add to 11, or (2) a group
	 * of three cards consisting of a jack, a queen, and a king in some order.
	 * @return true if there is a legal play left on the board;
	 *         false otherwise.
	 */
	//Will check for another possible move on the board by calling both the containsJQK and containsPairSum11 methods
	@Override
	public boolean anotherPlayIsPossible() 
	{
		List<Integer> temp = cardIndexes(); //calls cardIndexes and saves into Integer List of indices 
		return(containsJQK(temp)|| containsPairSum11(temp));
	}

	/**
	 * Check for an 11-pair in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find an 11-pair.
	 * @return true if the board entries in selectedCards
	 *              contain an 11-pair; false otherwise.
	 */
	//Will go through all possible pairs and find value of eleven 
	private boolean containsPairSum11(List<Integer> selectedCards) 
	{
		for(int i = 0; i < selectedCards.size(); i++){ 
			for(int z = 0; z < selectedCards.size(); z++){
				if(i!=z){ //make sure you are not checking yourself (doesn't really matter in this scenario as the sum is not even)
				int sum = 0;
				sum += cardAt(selectedCards.get(i).intValue()).pointValue(); //uses cardAt method to get card
				sum += cardAt(selectedCards.get(z).intValue()).pointValue();
					if(sum == 11)
						return true;	
				}
			}
		}
		return false;		
	}

	/**
	 * Check for a JQK in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find a JQK group.
	 * @return true if the board entries in selectedCards
	 *              include a jack, a queen, and a king; false otherwise.
	 */
	//Will check for jack, queen, king cards 
	private boolean containsJQK(List<Integer> selectedCards) 
	{
			boolean j = false;
			boolean q = false;
			boolean k = false;
			for(int i = 0; i < selectedCards.size(); i++){
				if((cardAt((selectedCards.get(i)).intValue()).rank()).equals("king")){ //uses cardAt method to get card
					k = true;
				}
				else if((cardAt((selectedCards.get(i)).intValue()).rank()).equals("queen")){ //also uses rank() to get card type
					q = true;
				}
				else if((cardAt((selectedCards.get(i)).intValue()).rank()).equals("jack")){
					j = true;
				}
			}
			return (j&&q)&&k; 
		
	}
}
