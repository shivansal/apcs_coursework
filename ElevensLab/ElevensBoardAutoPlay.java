import java.util.List;
import java.util.ArrayList;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class ElevensBoardAutoPlay extends Board 
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
	 * Creates a new <code>ElevensBoard</code> instance.
	 */
	 public ElevensBoardAutoPlay() 
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
	@Override
	public boolean isLegal(List<Integer> selectedCards) 
	{
		if(findPairSum11(selectedCards).size()>0) //findPairSum11 returns a list
			return true;
		else if(findJQK(selectedCards).size()>0)
			return true;
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
	@Override
	public boolean anotherPlayIsPossible() 
	{
		List<Integer> temp = cardIndexes(); //calls cardIndexes and saves into Integer List of indices 
		return(findJQK(temp).size()>0|| findPairSum11(temp).size()>0);
	}

	/**
	 * Look for an 11-pair in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find an 11-pair.
	 * @return a list of the indexes of an 11-pair, if an 11-pair was found;
	 *         an empty list, if an 11-pair was not found.
	 */
	private List<Integer> findPairSum11(List<Integer> selectedCards) 
	{
		List<Integer> pair11 = new ArrayList<Integer>();
		for(int i = 0; i < selectedCards.size(); i++){ 
			for(int z = 0; z < selectedCards.size(); z++){
				if(i!=z){ //make sure you are not checking yourself (doesn't really matter in this scenario as the sum is not even)
				int sum = 0;
				sum += cardAt(selectedCards.get(i).intValue()).pointValue(); //uses cardAt method to get card
				sum += cardAt(selectedCards.get(z).intValue()).pointValue();
					if(sum == 11){
						pair11.add(selectedCards.get(i)); //add index to list to return
						pair11.add(selectedCards.get(z));
						return pair11;
					}
				}
			}
		}
		return pair11;		
	}

	/**
	 * Look for a JQK in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find a JQK group.
	 * @return a list of the indexes of a JQK, if a JQK was found;
	 *         an empty list, if a JQK was not found.
	 */
	private List<Integer> findJQK(List<Integer> selectedCards) 
	{
			List<Integer> pairJQK = new ArrayList<Integer>();
			boolean j = true;
			boolean q = true;
			boolean k = true;
			for(int i = 0; i < selectedCards.size(); i++){
				if(k&&(cardAt((selectedCards.get(i)).intValue()).rank()).equals("king")){ //uses cardAt method to get card
					k = false;
					pairJQK.add(selectedCards.get(i)); //add index to list to return
				} 
				else if(q&&(cardAt((selectedCards.get(i)).intValue()).rank()).equals("queen")){ //also uses rank() to get card type
					q = false;
					pairJQK.add(selectedCards.get(i));

				}
				else if(j&&(cardAt((selectedCards.get(i)).intValue()).rank()).equals("jack")){
					j = false;
					pairJQK.add(selectedCards.get(i));
				}
			}
			return pairJQK;

	}

	/**
	 * Looks for a legal play on the board.  If one is found, it plays it.
	 * @return true if a legal play was found (and made); false othewise.
	 */
	public boolean playIfPossible() 
	{
		return (playPairSum11IfPossible()||playJQKIfPossible()); //short circuiting
	}

	/**
	 * Looks for a pair of non-face cards whose values sum to 11.
	 * If found, replace them with the next two cards in the deck.
	 * The simulation of this game uses this method.
	 * @return true if an 11-pair play was found (and made); false othewise.
	 */
	private boolean playPairSum11IfPossible() 
	{
		List<Integer> index = findPairSum11(cardIndexes());
		if(index.size()==2){
			replaceSelectedCards(index); //calls replace method in board which also deals new cards
			return true;
		}
		return false;
	}

	/**
	 * Looks for a group of three face cards JQK.
	 * If found, replace them with the next three cards in the deck.
	 * The simulation of this game uses this method.
	 * @return true if a JQK play was found (and made); false othewise.
	 */
	private boolean playJQKIfPossible() 
	{
		List<Integer> index = findJQK(cardIndexes()); 
		if(index.size()==3){
			replaceSelectedCards(index); //calls replace method in board whih also deals new cards     
			return true;
		}
		return false;
	}
}
