
/**
 * Sequential.java
 * Plays the game of Sequential.
 * There are 20 "game spaces" in the game of SEQUENTIAL. In each turn, a player can
 * roll his/her dice up to 3 times in order to get the desired combination. On the
 * first roll, the player rolls all four of the dice at once. On the second and
 * third rolls, the player can roll any number of dice he/she wants to, including
 * none or all of them, trying to get a good combination.
 * The player can choose whether he/she wants to roll once, twice or three times in
 * each turn.  The goal is to fill in the game spaces in sequential order.  This
 * means that the next number must always be the same as the previous value, one
 * more than the previous value, or one less than the previous value.  When one
 * player fills up the 20 game spaces, the game is over.  Each ordered and filled
 * game space is worth one point, with a couple of exceptions.  (1) Each number in a
 * "Small Straight" is worth 3 points.  For example, 1 2 3 4 or 2 3 4 5 or 3 4 5 6
 * or 4 3 2 1 or 5 4 3 2 or 6 5 4 3.  (2) Each number in 3 of a kind is worth
 * 2 points.  For example, 4 4 4 or 1 1 1 or 6 6 6.
 *
 * @author Shiv Ansal
 * @version 1.0
 * @since 10/7/2016
 */

import java.util.Scanner;

public class Sequential
{
	/**    The Scanner object, for getting input from the keyboard.    */
	private Scanner keyboard;

	/**    The two players.  Each player has a name, score array, total score, and current index.   */
	private SequentialPlayer player1, player2;

	/**    The group of 4 dice to be used in the game.   */
	private DiceGroup dice;

	/**    The Sequentil game score card, to be used to print out the current game status.   */
	private SequentialScoreCard card;

	/**    A boolean value to toggle between player turns.   */
	private boolean player1turn;

	private int turnCounter;

	/**
	 *  Creates a Sequential object, with a Scanner to read from
	 *  the keyboard, two Sequential players, 4 six-sided dice,
	 *  a score card to show the game status, and a boolean to
	 *  keep track of whose turn it is.
	 */
	public Sequential ( )
	{
		keyboard = new Scanner(System.in);
		player1 = new SequentialPlayer();
		player2 = new SequentialPlayer();
		dice = new DiceGroup(4);
		card = new SequentialScoreCard();
		player1turn = true;
		turnCounter = 0;
	}

	/**
	 *  Sets up and runs the game of Sequential.
	 *  @param  args     An array of String arguments (not used here).
	 */
	public static void main (String [] args)
	{
		Sequential game = new Sequential();
		game.playOneGame();
	}

	/**
	 *  Plays one game of Sequential, alternating turns until one of
	 *  the players reaches the last (20th) space.
	 */
	public void playOneGame ( )
	{
		introduction();
		getName(player1,1);
		getName(player2,2);
		chooseWhoGoesFirst();
		printCards(false);
		boolean done = false;
		while(player1.getIndex() < 20&&player2.getIndex() < 20)
		{
			takeATurnForEachPlayer();
		}
		finalMessage();
	}

	/**
	 *  The instructions.
	 */
	public void printCards(boolean show){
		card.printScoreCardHeading();
		card.printPlayerStatus(player1);
		card.printPlayerStatus(player2);
			if(show){
				int player1score = player1.calculateScore();
				int player2score = player2.calculateScore();
				System.out.printf("\n%-12s : %4d%n",player1.getName(),player1score);
				System.out.printf("%-12s : %4d%n",player2.getName(),player2score);

			}
	}
	public void introduction ( )
	{
		System.out.println("\n\n+------------------------------------------------------------------------------------+");
		System.out.println("| WELCOME TO SEQUENTIAL!                                                             |");
		System.out.println("|                                                                                    |");
		System.out.println("| There are 20 \"game spaces\" in the game of SEQUENTIAL. In each turn, a player can   |");
		System.out.println("| roll his/her dice up to 3 times in order to get the desired combination. On the    |");
		System.out.println("| first roll, the player rolls all four of the dice at once. On the second and       |");
		System.out.println("| third rolls, the player can roll any number of dice he/she wants to, including     |");
		System.out.println("| none or all of them, trying to get a good combination.                             |");
		System.out.println("| The player can choose whether he/she wants to roll once, twice or three times in   |");
		System.out.println("| each turn.  The goal is to fill in the game spaces in sequential order.  This      |");
		System.out.println("| means that the next number must always be the same as the previous value, one      |");
		System.out.println("| more than the previous value, or one less than the previous value.  When one       |");
		System.out.println("| player fills up the 20 game spaces, the game is over.  Each ordered and filled     |");
		System.out.println("| game space is worth one point, with a couple of exceptions.  (1) Each number in a  |");
		System.out.println("| \"Small Straight\" is worth 3 points.  For example, 1 2 3 4 or 2 3 4 5 or 3 4 5 6    |");
		System.out.println("| or 4 3 2 1 or 5 4 3 2 or 6 5 4 3.  (2) Each number in 3 of a kind is worth         |");
		System.out.println("| 2 points.  For example, 4 4 4 or 1 1 1 or 6 6 6.                                   |");
		System.out.println("|                                                                                    |");
		System.out.println("| LET'S PLAY SOME SEQUENTIAL!                                                        |");
		System.out.println("+------------------------------------------------------------------------------------+\n\n");
	}

	/**
	 *  Prompts the user, gets and sets the name of a player.
	 *  @param  sp      A player in the Sequential game.
	 *  @param  num     The player number (1 or 2 will be used here).
	 */
	public void getName (SequentialPlayer sp, int num)
	{
		System.out.print("\nPlayer " + num + ", please enter your first name : ");
		String name = keyboard.nextLine();
		sp.setName(name);
	}

	/**
	 *  Chooses who goes first.  Rolls the dice for each player, then compares
	 *  the sums.  The player with the highest sum goes first, and if the sums
	 *  are the same the process is repeated.
	 */
	public void chooseWhoGoesFirst ( )
	{
		int player1Sum = 0, player2Sum = 0;

		do
		{
			System.out.print("\nLet's see who will go first.  " + player1.getName() + ", please hit enter to roll the dice : ");
			keyboard.nextLine();
			dice.rollDice();
			dice.printDice();
			player1Sum = dice.getTotal();
			System.out.print("\n" + player2.getName() + ", it's your turn.  Please hit enter to roll the dice : ");
			keyboard.nextLine();
			dice.rollDice();
			dice.printDice();
			player2Sum = dice.getTotal();
			if (player1Sum == player2Sum)
			{
				System.out.println("Whoops, we have a tie (both rolled " + player1Sum +
					"). Looks like we'll have to try that again . . .");
			}
		} while (player1Sum == player2Sum);

		setFirstPlayer(player1Sum,player2Sum);
	}

	/**
	 *  Sets the player who will take the first turn, according to the sum of the dice.
	 */
	public void setFirstPlayer (int p1sum, int p2sum)
	{

		System.out.print("\n" + player1.getName() + ", you rolled a sum of " + p1sum);
		System.out.print(", and " + player2.getName() + ", you rolled a sum of " + p2sum + ".");
		if (p1sum > p2sum)
		{
			player1turn = true;
			System.out.println("\n" + player1.getName() + ", since your sum was higher, you'll roll first.\n");
		}
		else
		{
			player1turn = false;
			System.out.println("\n" + player2.getName() + ", since your sum was higher, you'll roll first.\n");
		}
	}

	/**
	 *  Both player turns are taken.  Either player1 then player 2, or
	 *  player2 then player1, depending on who was set as the first player.
	 */
	public void takeATurnForEachPlayer ()
	{
			if(player1turn){
				turnCounter++;
				takeTurn(player1);
				player1turn = false;
			}
			else{
				turnCounter++;
				takeTurn(player2);
				player1turn = true;
			}

	}

	/**
	 *  Takes a single turn for the current player.
	 *  @param  sp      A player in the Sequential game.
	 */
	public void takeTurn (SequentialPlayer sp)
	{
		String inputDecision = "";
		System.out.println("\n"+sp.getName()+", it's your turn to play.  Please hit enter to roll the dice :");
		keyboard.nextLine();
		dice.rollDice();
		dice.printDice();
	 	promptForAnotherRoll();
		inputDecision = keyboard.nextLine();
		if (!inputDecision.equals("-1"))
		{
			dice.rollDice(inputDecision);
			dice.printDice();
			promptForAnotherRoll();
			inputDecision = keyboard.nextLine();
			if (!inputDecision.equals("-1"))
			{
				dice.rollDice(inputDecision);
				dice.printDice();
			}
		}
		makeChoice(sp);
	}

	/**
	 *  Prompts the player with instructions on how to take the next roll.
	 */
	public void promptForAnotherRoll ( )
	{
		int decide;
		System.out.println("Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without");
		System.out.println("spaces.  For example, if you'd like to 'hold' die 1, 2, and 5, enter 125");
		System.out.print("(enter -1 if you'd like to end the turn) : ");
	}

	/**
	 *  Prompts the user to order the dice.  Then changes the score array for the player.
	 *  THIS METHOD IS INCOMPLETE!
	 *  @param  sp      A player in the Sequential game.
	 */
	public void makeChoice (SequentialPlayer sp)
	{
		printCards(false);
		String order = "";
		System.out.println("\n\n" + sp.getName() + ", now it's time to put your dice in order.  Enter the values of the dice");
		System.out.println("you'd like to add to your score, in the proper order.  For example, if you'd like to choose");
		System.out.print("the order 4, 2, 1, 3, enter 4213 : ");
		order = keyboard.nextLine();
		card.changeScore(sp, dice, order);
		printCards(true);

	}

	/**
	 *  A final message, to be printed at the end of the game.
	 */
	public void finalMessage ( )
	{
		System.out.println("\n\nThanks for playing SEQUENTIAL!");
		System.out.println("Here are the final results:\n");
		player1.printScore();
		player2.printScore();
		System.out.println("\n\n\n");
	}
}





/*

C:\Java\Sequential>


+------------------------------------------------------------------------------------+
| WELCOME TO SEQUENTIAL!                                                             |
|                                                                                    |
| There are 20 "game spaces" in the game of SEQUENTIAL. In each turn, a player can   |
| roll his/her dice up to 3 times in order to get the desired combination. On the    |
| first roll, the player rolls all four of the dice at once. On the second and       |
| third rolls, the player can roll any number of dice he/she wants to, including     |
| none or all of them, trying to get a good combination.                             |
| The player can choose whether he/she wants to roll once, twice or three times in   |
| each turn.  The goal is to fill in the game spaces in sequential order.  This      |
| means that the next number must always be the same as the previous value, one      |
| more than the previous value, or one less than the previous value.  When one       |
| player fills up the 20 game spaces, the game is over.  Each ordered and filled     |
| game space is worth one point, with a couple of exceptions.  (1) Each number in a  |
| "Small Straight" is worth 3 points.  For example, 1 2 3 4 or 2 3 4 5 or 3 4 5 6    |
| or 4 3 2 1 or 5 4 3 2 or 6 5 4 3.  (2) Each number in 3 of a kind is worth         |
| 2 points.  For example, 4 4 4 or 1 1 1 or 6 6 6.                                   |
|                                                                                    |
| LET'S PLAY SOME SEQUENTIAL!                                                        |
+------------------------------------------------------------------------------------+



Player 1, please enter your first name : Fred

Player 2, please enter your first name : Ethel

Let's see who will go first.  Fred, please hit enter to roll the dice :

    # 1           # 2           # 3           # 4
  _______       _______       _______       _______
 |       |     |       |     |       |     |       |
 |       |     |     O |     |       |     | O   O |
 |   O   |     |   O   |     |   O   |     |       |
 |       |     | O     |     |       |     | O   O |
 |_______|     |_______|     |_______|     |_______|


Ethel, it's your turn.  Please hit enter to roll the dice :

    # 1           # 2           # 3           # 4
  _______       _______       _______       _______
 |       |     |       |     |       |     |       |
 | O   O |     | O   O |     |     O |     |     O |
 |       |     |       |     |       |     |   O   |
 | O   O |     | O   O |     | O     |     | O     |
 |_______|     |_______|     |_______|     |_______|


Fred, you rolled a sum of 9, and Ethel, you rolled a sum of 13.
Ethel, since your sum was higher, you'll roll first.


  NAME           1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20
+----------------------------------------------------------------------------------------------+
| Fred         |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+
| Ethel        |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+


Ethel, it's your turn to play.  Please hit enter to roll the dice :

    # 1           # 2           # 3           # 4
  _______       _______       _______       _______
 |       |     |       |     |       |     |       |
 |       |     | O   O |     | O   O |     | O   O |
 |   O   |     |   O   |     |   O   |     |       |
 |       |     | O   O |     | O   O |     | O   O |
 |_______|     |_______|     |_______|     |_______|

Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without
spaces.  For example, if you'd like to 'hold' die 1, 2, and 5, enter 125
(enter -1 if you'd like to end the turn) : 234

    # 1           # 2           # 3           # 4
  _______       _______       _______       _______
 |       |     |       |     |       |     |       |
 |       |     | O   O |     | O   O |     | O   O |
 |   O   |     |   O   |     |   O   |     |       |
 |       |     | O   O |     | O   O |     | O   O |
 |_______|     |_______|     |_______|     |_______|

Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without
spaces.  For example, if you'd like to 'hold' die 1, 2, and 5, enter 125
(enter -1 if you'd like to end the turn) : 234

    # 1           # 2           # 3           # 4
  _______       _______       _______       _______
 |       |     |       |     |       |     |       |
 | O   O |     | O   O |     | O   O |     | O   O |
 |       |     |   O   |     |   O   |     |       |
 | O   O |     | O   O |     | O   O |     | O   O |
 |_______|     |_______|     |_______|     |_______|


  NAME           1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20
+----------------------------------------------------------------------------------------------+
| Fred         |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+
| Ethel        |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+

Ethel, now it's time to put your dice in order.  Enter the values of the dice
you'd like to add to your score, in the proper order.  For example, if you'd like to choose
the order 4, 2, 1, 3, enter 4213 : 2341

  NAME           1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20
+----------------------------------------------------------------------------------------------+
| Fred         |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+
| Ethel        | 5 | 5 | 4 | 4 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+
Fred         :    0
Ethel        :    4

Fred, it's your turn to play.  Please hit enter to roll the dice :

    # 1           # 2           # 3           # 4
  _______       _______       _______       _______
 |       |     |       |     |       |     |       |
 | O   O |     | O   O |     |     O |     |       |
 |   O   |     |   O   |     |   O   |     |   O   |
 | O   O |     | O   O |     | O     |     |       |
 |_______|     |_______|     |_______|     |_______|

Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without
spaces.  For example, if you'd like to 'hold' die 1, 2, and 5, enter 125
(enter -1 if you'd like to end the turn) : 12

    # 1           # 2           # 3           # 4
  _______       _______       _______       _______
 |       |     |       |     |       |     |       |
 | O   O |     | O   O |     | O   O |     |     O |
 |   O   |     |   O   |     | O   O |     |       |
 | O   O |     | O   O |     | O   O |     | O     |
 |_______|     |_______|     |_______|     |_______|

Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without
spaces.  For example, if you'd like to 'hold' die 1, 2, and 5, enter 125
(enter -1 if you'd like to end the turn) : 123

    # 1           # 2           # 3           # 4
  _______       _______       _______       _______
 |       |     |       |     |       |     |       |
 | O   O |     | O   O |     | O   O |     | O   O |
 |   O   |     |   O   |     | O   O |     |   O   |
 | O   O |     | O   O |     | O   O |     | O   O |
 |_______|     |_______|     |_______|     |_______|


  NAME           1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20
+----------------------------------------------------------------------------------------------+
| Fred         |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+
| Ethel        | 5 | 5 | 4 | 4 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+

Fred, now it's time to put your dice in order.  Enter the values of the dice
you'd like to add to your score, in the proper order.  For example, if you'd like to choose
the order 4, 2, 1, 3, enter 4213 : 3421

  NAME           1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20
+----------------------------------------------------------------------------------------------+
| Fred         | 6 | 5 | 5 | 5 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+
| Ethel        | 5 | 5 | 4 | 4 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+
Fred         :    7
Ethel        :    4


Ethel, it's your turn to play.  Please hit enter to roll the dice :

    # 1           # 2           # 3           # 4
  _______       _______       _______       _______
 |       |     |       |     |       |     |       |
 | O   O |     |     O |     |     O |     | O   O |
 | O   O |     |   O   |     |       |     |   O   |
 | O   O |     | O     |     | O     |     | O   O |
 |_______|     |_______|     |_______|     |_______|

Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without
spaces.  For example, if you'd like to 'hold' die 1, 2, and 5, enter 125
(enter -1 if you'd like to end the turn) : 23

    # 1           # 2           # 3           # 4
  _______       _______       _______       _______
 |       |     |       |     |       |     |       |
 |     O |     |     O |     |     O |     |       |
 |       |     |   O   |     |       |     |   O   |
 | O     |     | O     |     | O     |     |       |
 |_______|     |_______|     |_______|     |_______|

Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without
spaces.  For example, if you'd like to 'hold' die 1, 2, and 5, enter 125
(enter -1 if you'd like to end the turn) : -1

  NAME           1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20
+----------------------------------------------------------------------------------------------+
| Fred         | 6 | 5 | 5 | 5 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+
| Ethel        | 5 | 5 | 4 | 4 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+

Ethel, now it's time to put your dice in order.  Enter the values of the dice
you'd like to add to your score, in the proper order.  For example, if you'd like to choose
the order 4, 2, 1, 3, enter 4213 : 2341

  NAME           1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20
+----------------------------------------------------------------------------------------------+
| Fred         | 6 | 5 | 5 | 5 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+
| Ethel        | 5 | 5 | 4 | 4 | 3 | 2 | 1 | 2 |   |   |   |   |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+
Fred         :    7
Ethel        :   16

Fred, it's your turn to play.  Please hit enter to roll the dice :

    # 1           # 2           # 3           # 4
  _______       _______       _______       _______
 |       |     |       |     |       |     |       |
 |       |     |     O |     | O   O |     | O   O |
 |   O   |     |   O   |     | O   O |     | O   O |
 |       |     | O     |     | O   O |     | O   O |
 |_______|     |_______|     |_______|     |_______|

Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without
spaces.  For example, if you'd like to 'hold' die 1, 2, and 5, enter 125
(enter -1 if you'd like to end the turn) : 34

    # 1           # 2           # 3           # 4
  _______       _______       _______       _______
 |       |     |       |     |       |     |       |
 |     O |     |       |     | O   O |     | O   O |
 |       |     |   O   |     | O   O |     | O   O |
 | O     |     |       |     | O   O |     | O   O |
 |_______|     |_______|     |_______|     |_______|

Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without
spaces.  For example, if you'd like to 'hold' die 1, 2, and 5, enter 125
(enter -1 if you'd like to end the turn) : 34

    # 1           # 2           # 3           # 4
  _______       _______       _______       _______
 |       |     |       |     |       |     |       |
 | O   O |     | O   O |     | O   O |     | O   O |
 | O   O |     |   O   |     | O   O |     | O   O |
 | O   O |     | O   O |     | O   O |     | O   O |
 |_______|     |_______|     |_______|     |_______|


  NAME           1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20
+----------------------------------------------------------------------------------------------+
| Fred         | 6 | 5 | 5 | 5 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+
| Ethel        | 5 | 5 | 4 | 4 | 3 | 2 | 1 | 2 |   |   |   |   |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+

Fred, now it's time to put your dice in order.  Enter the values of the dice
you'd like to add to your score, in the proper order.  For example, if you'd like to choose
the order 4, 2, 1, 3, enter 4213 : 2134

  NAME           1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20
+----------------------------------------------------------------------------------------------+
| Fred         | 6 | 5 | 5 | 5 | 5 | 6 | 6 | 6 |   |   |   |   |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+
| Ethel        | 5 | 5 | 4 | 4 | 3 | 2 | 1 | 2 |   |   |   |   |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+
Fred         :   15
Ethel        :   16


Ethel, it's your turn to play.  Please hit enter to roll the dice :

    # 1           # 2           # 3           # 4
  _______       _______       _______       _______
 |       |     |       |     |       |     |       |
 | O   O |     |       |     |       |     |     O |
 |       |     |   O   |     |   O   |     |   O   |
 | O   O |     |       |     |       |     | O     |
 |_______|     |_______|     |_______|     |_______|

Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without
spaces.  For example, if you'd like to 'hold' die 1, 2, and 5, enter 125
(enter -1 if you'd like to end the turn) : 14

    # 1           # 2           # 3           # 4
  _______       _______       _______       _______
 |       |     |       |     |       |     |       |
 | O   O |     |     O |     | O   O |     |     O |
 |       |     |       |     |   O   |     |   O   |
 | O   O |     | O     |     | O   O |     | O     |
 |_______|     |_______|     |_______|     |_______|

Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without
spaces.  For example, if you'd like to 'hold' die 1, 2, and 5, enter 125
(enter -1 if you'd like to end the turn) : 134

    # 1           # 2           # 3           # 4
  _______       _______       _______       _______
 |       |     |       |     |       |     |       |
 | O   O |     | O   O |     | O   O |     |     O |
 |       |     |       |     |   O   |     |   O   |
 | O   O |     | O   O |     | O   O |     | O     |
 |_______|     |_______|     |_______|     |_______|


  NAME           1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20
+----------------------------------------------------------------------------------------------+
| Fred         | 6 | 5 | 5 | 5 | 5 | 6 | 6 | 6 |   |   |   |   |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+
| Ethel        | 5 | 5 | 4 | 4 | 3 | 2 | 1 | 2 |   |   |   |   |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+

Ethel, now it's time to put your dice in order.  Enter the values of the dice
you'd like to add to your score, in the proper order.  For example, if you'd like to choose
the order 4, 2, 1, 3, enter 4213 : 4231

  NAME           1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20
+----------------------------------------------------------------------------------------------+
| Fred         | 6 | 5 | 5 | 5 | 5 | 6 | 6 | 6 |   |   |   |   |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+
| Ethel        | 5 | 5 | 4 | 4 | 3 | 2 | 1 | 2 | 3 | 4 | 5 | 4 |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+
Fred         :   15
Ethel        :   28

Fred, it's your turn to play.  Please hit enter to roll the dice :

    # 1           # 2           # 3           # 4
  _______       _______       _______       _______
 |       |     |       |     |       |     |       |
 | O   O |     | O   O |     | O   O |     | O   O |
 | O   O |     |   O   |     |       |     |   O   |
 | O   O |     | O   O |     | O   O |     | O   O |
 |_______|     |_______|     |_______|     |_______|

Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without
spaces.  For example, if you'd like to 'hold' die 1, 2, and 5, enter 125
(enter -1 if you'd like to end the turn) : -1

  NAME           1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20
+----------------------------------------------------------------------------------------------+
| Fred         | 6 | 5 | 5 | 5 | 5 | 6 | 6 | 6 |   |   |   |   |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+
| Ethel        | 5 | 5 | 4 | 4 | 3 | 2 | 1 | 2 | 3 | 4 | 5 | 4 |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+

Fred, now it's time to put your dice in order.  Enter the values of the dice
you'd like to add to your score, in the proper order.  For example, if you'd like to choose
the order 4, 2, 1, 3, enter 4213 : 1243

  NAME           1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20
+----------------------------------------------------------------------------------------------+
| Fred         | 6 | 5 | 5 | 5 | 5 | 6 | 6 | 6 | 6 | 5 | 5 | 4 |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+
| Ethel        | 5 | 5 | 4 | 4 | 3 | 2 | 1 | 2 | 3 | 4 | 5 | 4 |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+
Fred         :   20
Ethel        :   28


Ethel, it's your turn to play.  Please hit enter to roll the dice :

    # 1           # 2           # 3           # 4
  _______       _______       _______       _______
 |       |     |       |     |       |     |       |
 | O   O |     |     O |     | O   O |     | O   O |
 |   O   |     |   O   |     |       |     | O   O |
 | O   O |     | O     |     | O   O |     | O   O |
 |_______|     |_______|     |_______|     |_______|

Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without
spaces.  For example, if you'd like to 'hold' die 1, 2, and 5, enter 125
(enter -1 if you'd like to end the turn) : -1

  NAME           1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20
+----------------------------------------------------------------------------------------------+
| Fred         | 6 | 5 | 5 | 5 | 5 | 6 | 6 | 6 | 6 | 5 | 5 | 4 |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+
| Ethel        | 5 | 5 | 4 | 4 | 3 | 2 | 1 | 2 | 3 | 4 | 5 | 4 |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+

Ethel, now it's time to put your dice in order.  Enter the values of the dice
you'd like to add to your score, in the proper order.  For example, if you'd like to choose
the order 4, 2, 1, 3, enter 4213 : 2314

  NAME           1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20
+----------------------------------------------------------------------------------------------+
| Fred         | 6 | 5 | 5 | 5 | 5 | 6 | 6 | 6 | 6 | 5 | 5 | 4 |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+
| Ethel        | 5 | 5 | 4 | 4 | 3 | 2 | 1 | 2 | 3 | 4 | 5 | 4 | 3 | 4 | 5 | 6 |   |   |   |   |
+----------------------------------------------------------------------------------------------+
Fred         :   20
Ethel        :   40

Fred, it's your turn to play.  Please hit enter to roll the dice :

    # 1           # 2           # 3           # 4
  _______       _______       _______       _______
 |       |     |       |     |       |     |       |
 |     O |     | O   O |     | O   O |     | O   O |
 |   O   |     |   O   |     |   O   |     |   O   |
 | O     |     | O   O |     | O   O |     | O   O |
 |_______|     |_______|     |_______|     |_______|

Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without
spaces.  For example, if you'd like to 'hold' die 1, 2, and 5, enter 125
(enter -1 if you'd like to end the turn) : 234

    # 1           # 2           # 3           # 4
  _______       _______       _______       _______
 |       |     |       |     |       |     |       |
 |       |     | O   O |     | O   O |     | O   O |
 |   O   |     |   O   |     |   O   |     |   O   |
 |       |     | O   O |     | O   O |     | O   O |
 |_______|     |_______|     |_______|     |_______|

Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without
spaces.  For example, if you'd like to 'hold' die 1, 2, and 5, enter 125
(enter -1 if you'd like to end the turn) : 234

    # 1           # 2           # 3           # 4
  _______       _______       _______       _______
 |       |     |       |     |       |     |       |
 | O   O |     | O   O |     | O   O |     | O   O |
 |   O   |     |   O   |     |   O   |     |   O   |
 | O   O |     | O   O |     | O   O |     | O   O |
 |_______|     |_______|     |_______|     |_______|


  NAME           1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20
+----------------------------------------------------------------------------------------------+
| Fred         | 6 | 5 | 5 | 5 | 5 | 6 | 6 | 6 | 6 | 5 | 5 | 4 |   |   |   |   |   |   |   |   |
+----------------------------------------------------------------------------------------------+
| Ethel        | 5 | 5 | 4 | 4 | 3 | 2 | 1 | 2 | 3 | 4 | 5 | 4 | 3 | 4 | 5 | 6 |   |   |   |   |
+----------------------------------------------------------------------------------------------+

Fred, now it's time to put your dice in order.  Enter the values of the dice
you'd like to add to your score, in the proper order.  For example, if you'd like to choose
the order 4, 2, 1, 3, enter 4213 : 1234

  NAME           1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20
+----------------------------------------------------------------------------------------------+
| Fred         | 6 | 5 | 5 | 5 | 5 | 6 | 6 | 6 | 6 | 5 | 5 | 4 | 5 | 5 | 5 | 5 |   |   |   |   |
+----------------------------------------------------------------------------------------------+
| Ethel        | 5 | 5 | 4 | 4 | 3 | 2 | 1 | 2 | 3 | 4 | 5 | 4 | 3 | 4 | 5 | 6 |   |   |   |   |
+----------------------------------------------------------------------------------------------+
Fred         :   28
Ethel        :   40


Ethel, it's your turn to play.  Please hit enter to roll the dice :

    # 1           # 2           # 3           # 4
  _______       _______       _______       _______
 |       |     |       |     |       |     |       |
 |     O |     |     O |     | O   O |     |       |
 |   O   |     |       |     |       |     |   O   |
 | O     |     | O     |     | O   O |     |       |
 |_______|     |_______|     |_______|     |_______|

Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without
spaces.  For example, if you'd like to 'hold' die 1, 2, and 5, enter 125
(enter -1 if you'd like to end the turn) :

    # 1           # 2           # 3           # 4
  _______       _______       _______       _______
 |       |     |       |     |       |     |       |
 | O   O |     |     O |     |     O |     |     O |
 | O   O |     |       |     |   O   |     |   O   |
 | O   O |     | O     |     | O     |     | O     |
 |_______|     |_______|     |_______|     |_______|

Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without
spaces.  For example, if you'd like to 'hold' die 1, 2, and 5, enter 125
(enter -1 if you'd like to end the turn) : 1

    # 1           # 2           # 3           # 4
  _______       _______       _______       _______
 |       |     |       |     |       |     |       |
 | O   O |     | O   O |     |       |     |     O |
 | O   O |     | O   O |     |   O   |     |   O   |
 | O   O |     | O   O |     |       |     | O     |
 |_______|     |_______|     |_______|     |_______|


  NAME           1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20
+----------------------------------------------------------------------------------------------+
| Fred         | 6 | 5 | 5 | 5 | 5 | 6 | 6 | 6 | 6 | 5 | 5 | 4 | 5 | 5 | 5 | 5 |   |   |   |   |
+----------------------------------------------------------------------------------------------+
| Ethel        | 5 | 5 | 4 | 4 | 3 | 2 | 1 | 2 | 3 | 4 | 5 | 4 | 3 | 4 | 5 | 6 |   |   |   |   |
+----------------------------------------------------------------------------------------------+

Ethel, now it's time to put your dice in order.  Enter the values of the dice
you'd like to add to your score, in the proper order.  For example, if you'd like to choose
the order 4, 2, 1, 3, enter 4213 : 12

  NAME           1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20
+----------------------------------------------------------------------------------------------+
| Fred         | 6 | 5 | 5 | 5 | 5 | 6 | 6 | 6 | 6 | 5 | 5 | 4 | 5 | 5 | 5 | 5 |   |   |   |   |
+----------------------------------------------------------------------------------------------+
| Ethel        | 5 | 5 | 4 | 4 | 3 | 2 | 1 | 2 | 3 | 4 | 5 | 4 | 3 | 4 | 5 | 6 | 6 | 6 |   |   |
+----------------------------------------------------------------------------------------------+
Fred         :   28
Ethel        :   44

Fred, it's your turn to play.  Please hit enter to roll the dice :

    # 1           # 2           # 3           # 4
  _______       _______       _______       _______
 |       |     |       |     |       |     |       |
 | O   O |     | O   O |     | O   O |     |       |
 |   O   |     |       |     | O   O |     |   O   |
 | O   O |     | O   O |     | O   O |     |       |
 |_______|     |_______|     |_______|     |_______|

Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without
spaces.  For example, if you'd like to 'hold' die 1, 2, and 5, enter 125
(enter -1 if you'd like to end the turn) : 12

    # 1           # 2           # 3           # 4
  _______       _______       _______       _______
 |       |     |       |     |       |     |       |
 | O   O |     | O   O |     | O   O |     |     O |
 |   O   |     |       |     |   O   |     |   O   |
 | O   O |     | O   O |     | O   O |     | O     |
 |_______|     |_______|     |_______|     |_______|

Which di(c)e would you like to keep?  Enter the values you'd like to 'hold' without
spaces.  For example, if you'd like to 'hold' die 1, 2, and 5, enter 125
(enter -1 if you'd like to end the turn) : -1

  NAME           1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20
+----------------------------------------------------------------------------------------------+
| Fred         | 6 | 5 | 5 | 5 | 5 | 6 | 6 | 6 | 6 | 5 | 5 | 4 | 5 | 5 | 5 | 5 |   |   |   |   |
+----------------------------------------------------------------------------------------------+
| Ethel        | 5 | 5 | 4 | 4 | 3 | 2 | 1 | 2 | 3 | 4 | 5 | 4 | 3 | 4 | 5 | 6 | 6 | 6 |   |   |
+----------------------------------------------------------------------------------------------+

Fred, now it's time to put your dice in order.  Enter the values of the dice
you'd like to add to your score, in the proper order.  For example, if you'd like to choose
the order 4, 2, 1, 3, enter 4213 : 1324

  NAME           1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20
+----------------------------------------------------------------------------------------------+
| Fred         | 6 | 5 | 5 | 5 | 5 | 6 | 6 | 6 | 6 | 5 | 5 | 4 | 5 | 5 | 5 | 5 | 5 | 5 | 4 | 3 |
+----------------------------------------------------------------------------------------------+
| Ethel        | 5 | 5 | 4 | 4 | 3 | 2 | 1 | 2 | 3 | 4 | 5 | 4 | 3 | 4 | 5 | 6 | 6 | 6 |   |   |
+----------------------------------------------------------------------------------------------+
Fred         :   34
Ethel        :   44


Thanks for playing SEQUENTIAL!
Here are the final results:

Fred         :   34
Ethel        :   44





C:\Java\Sequential>
*/
