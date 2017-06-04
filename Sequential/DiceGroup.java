import java.util.Scanner;

public class DiceGroup{
	private Dice [] die;

	public DiceGroup(){
		die = new Dice[5];
		for(int i=0; i < die.length; i++){
			die[i] = new Dice();

		}
	}

	public DiceGroup(int d){
		die = new Dice[d];
		for(int i=0; i < die.length; i++){
			die[i] = new Dice();

		}
	}

	public static void main(String[]args){
		DiceGroup sample = new DiceGroup();
		sample.rollDice();
		sample.printDice();
	}

	public void rollDice(){
		for(int i = 0; i < die.length; i++){
			die[i].roll();
		}
	}

	public void rollDice(String rawHold){
		for(int i = 0; i < die.length; i++){
			boolean doRoll = true;
			for(int j =0; j < rawHold.length();j++){
				if((int)rawHold.charAt(j)-49 == i)
				{
					doRoll = false;
				}

			}
			if(doRoll){
				die[i].roll();
			}
		}
	}

	public void printDice ( )
	{
		printDiceHeadings();

		for (int row = 0; row < 6; row++)
		{
			System.out.print(" ");
			for (int diceindex = 0; diceindex < die.length; diceindex++)
			{
					findAndPrintCorrectLine(row,diceindex);
			}
			System.out.println();
		}
		System.out.println();
	}

	public void printDiceHeadings ( )
	{
		System.out.println();
		for (int i = 0; i < die.length; i++)
		{
			System.out.print("    # " + (i+1) + "       ");
		}
		System.out.println();
	}

	public void findAndPrintCorrectLine (int row, int diceindex)
	{
		Scanner infile = OpenFile.openToRead("Dice.txt");

		int composite = 7 * row + die[diceindex].getValue();
		int linenum = getLineNumber(composite);
		String line = new String("");

		for (int filerow = 0; filerow < linenum; filerow++)
		{
			line = infile.nextLine();
		}
		System.out.print(line + "     ");
		infile.close();
	}

	public int getLineNumber (int value)
	{
		if (value < 14)
			return value / 7 + 1;
		else
		{
			switch(value)
			{
				case 15: case 23: case 25: case 29:
					return 2;
				case 18: case 19: case 20: case 27: case 32: case 33: case 34:
					return 3;
				case 22: case 24: case 26:
					return 4;
				case 16: case 17:
					return 5;
				case 30: case 31:
					return 6;
				default:  // for value >= 35
					return 7;
			}
		}
	}

	public Dice getDie (int index)
	{
		return die[index];
	}

	public int getTotal(){
		int  sum = 0;
		for(int i = 0; i < die.length; i++){
			sum += die[i].getValue();
		}
		return sum;
	}
	public int getDieValue(int number){
		return die[number].getValue();
	}

}
