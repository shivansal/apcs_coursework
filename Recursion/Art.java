/**
 * Art.java
 * Recursive circle drawing using StdDraw.
 * @author Shiv Ansal
 * @version 1.0
 * @since 1/23/2017
 */

import java.awt.Color;

public class Art{
	
	/**
	 *  Sets up and runs drawimng.
	 *  @param  args     An array of String arguments (the user can pass the name
	 *  of the number of calls they want.  If no argument
	 *  is entered, the program is ran 8 times by default. 
	 */	
	public static void main(String [] args){
		StdDraw.setCanvasSize(1000,1000);
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.filledSquare(.5 , .5, .5);
		//StdDraw.line(0.0,.5,1.0,.5);
		int n = 8;
		if(args.length > 0)
			n= Integer.parseInt(args[0]);
		makeCircle(n,.5, .5, .25); //intial circle
	}
	
	
		/**
	 *  Draws the circle and calls i recrusively until n==0
	 *  @param  n     How many more times to call the method makeCircle
	 *  @param  x     x-coord
	 *  @param  y     y-coord
	 *  @param  rad   radius
	 */		
	public static void makeCircle(int n, double x, double y, double rad){
		if(n==0)
			return;
		StdDraw.setPenColor(new Color(100+(int)(Math.random()*(100)),100+(int)(Math.random()*(100)),100+(int)(Math.random()*(100)))); //changes the color
		StdDraw.circle(x, y, rad); //draws regular call
		makeCircle(n-1, x+rad, y, rad/2); //right circle
		makeCircle(n-1, x-rad, y, rad/2); //left circle
		if(n%2==0){ //draws the upper and lower circles every n%2
			makeCircle(n-1, x, y+rad, rad/3);
			makeCircle(n-1, x, y-rad, rad/3);
		}
	}
}
