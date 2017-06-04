public class YahtzeePlayer{
	
	private String name;
	private int [] scoreArray;
	private int totalScore;
	
	public YahtzeePlayer(){
		totalScore = 0;
		name = new String("");
		scoreArray = new int [13];
		for(int z = 0; z<scoreArray.length;z++){
			scoreArray[z] = -1;
		}
	}
	
	public void setName(String n){
		name = new String(n);
	}
	
	public String getName(){
		return name;
	}
	
	public void setScoreArrayElement(int i, int value){
		scoreArray[i] = value;
	}
	
	public int getScoreArrayElement(int i){
		return scoreArray[i];
	}
	public int calculateScore(){
		totalScore = 0;
		for(int z = 0; z < scoreArray.length; z++){
			if(scoreArray[z]!= -1)
				totalScore += scoreArray[z];
		}
		return totalScore;
	}
	
	public void printScore(){
		calculateScore();
		System.out.printf("%-12s : %4d%n", name, totalScore);
	}
}
