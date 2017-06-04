
public class SequentialPlayer
{
	private String name;
	private int [] scoreArray;
	private int totalscore;
	private int index;
	private DiceGroup dieIns;

	public SequentialPlayer ( )
	{
		totalscore = 0;
		index = 0;
		name = new String("NO NAME");
		dieIns = new DiceGroup();
		scoreArray = new int [20];
		for (int i = 0; i < scoreArray.length; i++)
		{
			scoreArray[i] = -1;
		}
	}

	public void setName(String input){
		name = input;
	}

	public String getName(){
		return name;
	}

	public int getIndex(){
		int count = 0;
		for(int i=0; i<=19; i++){
			if(scoreArray[i]!=-1){
				count++;
			}
		}
		return count;
	}



	//  Getters and setters need to be written

	public int getScoreArrayElement(int i){
		return scoreArray[i];
	}

	public void setArrayElement(int passValue, int passIndex, DiceGroup dice){
		scoreArray[passIndex] = dice.getDieValue(passValue-1);

	}

	public boolean checkStraight(int i) //3point
	{
		int check = scoreArray[i];
		if(check!=-1){
			if(i+2<scoreArray.length&&(scoreArray[i+1]==check+1&&scoreArray[i+2]==check+2)){
				return true;
			}
			else if((i+1<scoreArray.length&&i-1>=0)&&(scoreArray[i-1]==check-1&&scoreArray[i+1]==check+1)){
				return true;
			}
			else if(i-2>=0&&(scoreArray[i-2]==check-2&&scoreArray[i-1]==check-1)){
				return true;
			}
			else if(i+2<scoreArray.length&&(scoreArray[i+1]==check-1&&scoreArray[i+2]==check-2)){
				return true;
			}
			else if((i+1<scoreArray.length&&i-1>=0)&&(scoreArray[i-1]==check+1&&scoreArray[i+1]==check-1)){
				return true;
			}
			else if(i-2>=0&&(scoreArray[i-2]==check+2&&scoreArray[i-1]==check+1)){
				return true;
			}
		}
		//X23
		//1X3
		//12X
		//X21
		//3X1
		//32X
		return false;
	}
	public boolean checkSingle(int i){
		if(scoreArray[i]==-1)
			return false;
			return true;
	}

	public boolean checkThreeSame(int i)//2poiints
	{
		int check = scoreArray[i];
		if(check!=-1){
			if(i+2<scoreArray.length&&(scoreArray[i+1]==check&&scoreArray[i+2]==check)){ //Xxx
				return true;
			}
			else if((i+1<scoreArray.length&&i-1>=0)&&(scoreArray[i+1]==check&&scoreArray[i-1]==check)){ //xXx
				return true;
			}
			else if(i-2>=0&&(scoreArray[i-2]==check&&scoreArray[i-1]==check)){ //xxX
				return true;
			}
		}
		return false;
	}

	public void printScore ( )
	{

		calculateScore();
		System.out.printf("%-12s : %4d%n",name,calculateScore());
	}
	public int calculateScore(){
		int totPlayerScore = 0;
		for(int i = 0; i<scoreArray.length; i++){
			if(checkStraight(i)){
				totPlayerScore += 3;
			}
			else if(checkThreeSame(i)){
				totPlayerScore += 2;
			}
			else if(checkSingle(i)){
				totPlayerScore +=1;
			}
		}
		return totPlayerScore;
	}
}
