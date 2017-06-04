
/**
 *  Matador.java
 *  In this game, the user will start with 200 dollars.
 *  The user places a bet (a random number of dollars
 *  from 1 to 10), then chooses a possible outcome for
 *  the rolling of 3 6-sided dice:
 *  (1) Any Triple (betting that all 3 dice will show the same number), at 30 to 1 odds
 *  (2) Go Big (betting that the sum of the dice will be 11 or higher, and not a triple), at 1 to 1 odds 
 *  (3) Go Small (betting that the sum of the dice will be 10 or lower, and not a triple), at 1 to 1 odds 
 *  (4) Go Extreme (betting that the sum of the dice will be less than 8 or greater than 12), at 1 to 1 odds 
 *  The dice are then rolled, and the player either wins or loses,
 *  with the appropriate amount of money either added or subtracted
 *  from the player's total.  The game is then repeated, and this
 *  continues until the player winds up with 0 dollars.
 *  The number of turns (games played) is then displayed
 *  and the game terminates.
 *  
 *  @author Shiv Ansal
 *  @version 1.0
 *  @since 8/23/2016
 */
public class Matador
{
	/**    Declare fields here.    */
	private int money;
	private int counter; 
	private Dice die1, die2, die3;
	private boolean triple;
	private boolean win;

	
	/**
	 *  Creates a Matador object, with three six-sided Dice, one
	 *  ten-sided die for the money bet, and a four-sided die
	 *  for the bet choice.  Sets the amount of money to 200 dollars.
	 */
	public Matador ( )
	{
		die1 = die2 = die3 = new Dice();
		money = 200;
		counter = 0;
	}
	
	/**
	 *  Sets up and runs the rounds of Matador.
	 *  @param  args     An array of String arguments (not used here).
	 */
	public static void main (String [] args)
	{
		Matador run = new Matador();
		run.playGames();
	}
	
	/**
	 *  Plays all of the rounds of Matador, until the player has no money left.
	 */
	public void playGames ( )
	{
		System.out.println("\n\n\n");
		do
		{
			counter++;
			System.out.println("+----------------------------------------------------------------+");
			System.out.printf("|  ROUND %5d                                                   |%n",counter);
			playASingleGame();
		}
		while(money > 0);
		System.out.println("+----------------------------------------------------------------+\n");
		System.out.println("\n\nSorry, but you know that the house always wins!\n\n\n\n\n");
	}
	
	/**
	 *  Plays a single game of Matador.
	 */
	public void playASingleGame ( )
	{	
		int automatedWager = wager();
		int automatedChoice = choice();
		int automatedSum = rollDie();
		System.out.printf("|  Your money total:  $%4d                                      |%n",money);
		System.out.printf("|  Your wager      :  $%4d                                      |%n",automatedWager);
		System.out.print("|  Your choice     :  ");
		if(automatedChoice == 0)
			System.out.println("Any Triple (30 to 1)                       |");
		else if(automatedChoice == 1)
			System.out.println("Go Big (sum >= 11, not a triple, 1 to 1)   |");
		else if(automatedChoice == 2)
			System.out.println("Go Small (sum <= 10, not a triple, 1 to 1) |");
		else if(automatedChoice == 3)
			System.out.println("Go Extreme (sum < 8 or sum > 12, 1 to 1)   |");
		System.out.printf("|  Roll            :%3d%3d%3d                                    |%n",die1.getValue(),die2.getValue(),die3.getValue());
		System.out.printf("|  Sum             :  %-2d             +---------------+           |%n",automatedSum);
		System.out.print("|  A Triple        :  ");
		if(triple)
			System.out.print("YES            ");
		if(!triple)	
			System.out.print("NO             ");
		getMoney(automatedSum, automatedWager, automatedChoice);
		if(win)
			System.out.println("|   YOU WIN!    |           |");
		else{
			System.out.println("|   YOU LOSE!   |           |");
		}
		System.out.printf("|  New money total :  $%4d          +---------------+           |%n",money);
	}
	
	/**
	 *  Split the work up into smaller methods.  Don't write all of your code in
	 *  playASingleGame if you are looking to earn full credit.
	 */
	 
	 /**
	 *  Automates the amount the user will wager and makes sure if user has
	 *  less than or equal for 10 dollars, they cannot wager more than amount.
	 *  @return         The automated wager.
	 */
	 public int wager(){
		int wager = 0;
		if(money<=10)
			wager = (int)(Math.random()*(money))+1;
		else{
			wager = (int)(Math.random()*10)+1;
		}
		return wager;
	 }
	 
	 /**
	 *  Automates which choice the user will pick using Math.random.
	 *  @return         The automated choice.
	 */	 
	 public int choice(){
		int choice = (int)(Math.random()*4);
		return choice;
	 }
	 
	 	 /**
	 *  Creates die instances and checks for triple and finds the sum of all die.
	 *  @return         The sum of all die.
	 */
	 public int rollDie(){
		die1 = new Dice(); 
		die2 = new Dice(); 
		die3 = new Dice(); 
		die1.roll();
		die2.roll();
		die3.roll();
		int sum = die1.getValue()+die2.getValue()+die3.getValue();
		if(die1.getValue()==die2.getValue()&&die2.getValue()==die3.getValue())
			triple = true;
		else{
			triple = false;
		}
		return sum;
	 }
	
	/**
	 *  Calculates how much money the user will recieve for each choice and 
	 * 	wheteher they lost or won.
	 *  @param userSum        The sum of all the die.
	 * 	@param bet      	  They wager the user put on.
	 *	@param choice         The users choice.
	 */ 
	public void getMoney(int userSum, int bet, int choice){
		if(choice == 0){
			if(triple){
				money = money + (bet*30);
				win = true;
			}
			else{
				money = money - bet;
				win = false;
			}
		}
		else if(choice == 1){
			if((!triple)&&userSum>=11){
				money = bet + money;
				win = true;
			}
			else{
				money = money - bet;
				win = false;
			}
		}
		else if(choice == 2 ){
			if((!triple)&&userSum<=10){
				money = bet + money;
				win = true;
			}
			else{
				money = money - bet;
				win = false;
			}
		}
		else if(choice == 3){
			if(userSum<8||userSum>12){
				money = bet + money;
				win = true;
			}
			else{
				money = money - bet;
				win = false;
			}
		}		
		}
	}
	 


