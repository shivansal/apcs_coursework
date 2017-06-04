// Die.java
// A simple simulation of a 6-sided die.

public class Die{

		private int numDots;
		
		public Die(){
			numDots = 0;
		}
		
		public static void main(String[]args){
			Die die = new Die();
			die.roll();
			System.out.println("\n\n\n"+die.getNumDots());
			die.roll();
			System.out.println("\n\n\n" +die.getNumDots());
		}
		
		public void roll(){
			numDots = (int)(Math.random()*6)+1;
		}
		
		public int getNumDots(){
			return numDots;
		}
}
