public class SequentialScoreCard
{
	public void printScoreCardHeading ( )
	{
		System.out.println("\n  NAME           1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20");
		System.out.println("+----------------------------------------------------------------------------------------------+");
	}

	public void printPlayerStatus (SequentialPlayer sp)
	{

		if (sp.getName().length() <= 12)
		{
			System.out.printf("| %-12s |",sp.getName());
		}
		else
		{
			System.out.printf("| %-12s |",sp.getName().substring(0,12));
		}
		for (int i = 0; i < 20; i++)
		{
			if (sp.getScoreArrayElement(i) > -1)
			{
				System.out.printf(" %d |",sp.getScoreArrayElement(i));
			}
			else
			{
				System.out.print("   |");
			}
		}

		System.out.println("\n+----------------------------------------------------------------------------------------------+");
	}

	public void changeScore(SequentialPlayer sp, DiceGroup dice, String order)
	{
			for(int i = 0; i<20; i++){
				if(sp.getScoreArrayElement(i)==-1){

						sp.setArrayElement((Integer.parseInt(""+order.charAt(0))),i,dice);
						sp.setArrayElement((Integer.parseInt(""+order.charAt(1))),i+1,dice);
						sp.setArrayElement((Integer.parseInt(""+order.charAt(2))),i+2,dice);
						sp.setArrayElement((Integer.parseInt(""+order.charAt(3))),i+3,dice);

						return;
				}
			}
	}


}
