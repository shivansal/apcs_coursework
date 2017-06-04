
/**
 *  Dice.java
 *  Simulates a single die.  An instance of the Dice class
 *  has a number of sides, a roll count, and a face value
 *  (last rolled value).  Two constructors are included, 
 *  along with getter and setter methods.
 *  @author Shiv Ansal
 *  @version 1.0
 *  @since 8/19/2016
 */
public class Dice
{
	/**  The number of sides for the Dice object. */
	private int numSides;
	
	/**  The number of times this Dice object has been rolled. */
	private int rollCount;

	/**  The last rolled value for the die (Dice object). */
	private int value;
	
	/** 
	 *  Creates a Dice object, with 6 sides, roll count
	 *  set to 0, and value set to flag of -1.
	 */
	public Dice ( )
	{
		numSides = 6;
		rollCount = 0;
		value = -1;
	}
	
	/** 
	 *  Creates a Dice object, with n sides, roll count
	 *  set to 0, and value set to flag of -1.
	 *  @param n        The number of sides for this Dice object.
	 */
	public Dice (int n)
	{
		numSides = n;
		rollCount = 0;
		value = -1;
	}
	
	/**
	 *  Rolls the die (Dice object).  The roll count is increased by 1,
	 *  and the value is set equal to a random int value from 1 to numSides.
	 *  @return         The new face value rolled.
	 */
	public int roll ( )
	{
		rollCount++;
		value = (int)(Math.random() * numSides) + 1;
		return value;
	}
	
	/**
	 *  Gets the roll count.
	 *  @return         The current roll count.
	 */
	public int getRollCount ( )
	{
		return rollCount;
	}
	
	/**
	 *  Gets the face value.
	 *  @return         The current face value.
	 */	
	public int getValue ( )
	{
		return value;
	}
}
