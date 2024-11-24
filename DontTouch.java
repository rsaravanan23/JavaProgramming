/**
 * DontTouch.java
 *
 * Provide a description of the program here.
 *
 * @author YOUR NAME HERE
 * @version 1.0
 * @since 10/26/2022
 */

import java.awt.Color;
import java.awt.Font;

public class DontTouch
{
	/**  The array for the Circles to be drawn.                         */
	Circle[] circles;

	/**  Constructs the size (1000) for the array of Circles.           */
	public DontTouch ( )
	{
		circles = new Circle[1000];
	}

	public static void main(String [] args)
	{
		DontTouch run = new DontTouch();
		run.setUpCanvas();
		run.drawCircles();
		run.drawAxes();
		run.printArea();
	}

	/** 
	 *  Sets up the canvas, using methods from StdDraw.  This includes
	 *  setting up the canvas size, the horizontal scale (Xscale), and
	 *  the vertical scale (Yscale).  
	 */
	public void setUpCanvas ( )
	{
		final int WIDTH = 1200;
		final int HEIGHT = 800;
		StdDraw.setCanvasSize(WIDTH, HEIGHT);
		StdDraw.setXscale(-6, 6);
		StdDraw.setYscale(-4, 4);
		StdDraw.clear(new Color(255,255,255));

		StdDraw.enableDoubleBuffering();
	}

	/**
	 *  Creates the Circles in the array of Circles.  Draws the Circles.
	 */
	public void drawCircles ( )
	{

		long startTime = System.currentTimeMillis();

		//  Your code here.
		int circleCount = 0; 
		boolean works = false;
		//double max = 2.0; 
		double rad = 1.0;
		int count = 0;		

		for(int x = 0; x < 1000; x++) { 
			circles[x] = new Circle(0.0,0.0,0.0);

		}

		while(circleCount < 1000) { 

			for(int x = 0; x < 2000; x++) { 
				double newX = Math.random() * 13 - 6; 
				double newY = Math.random() * 9 - 4;
				//System.out.println("NewX: " + newX); 
				//System.out.println("NewY: " + newY);
				if(x >= circleCount) { 
					rad-= 0.000003;
					x = 0;
				}
				if(circleWorks(newX, newY, rad, circleCount)) {
					if(newX + rad < -6 || newX + rad > 6) { 
						System.out.println("Hey");
					} else { 
						StdDraw.setPenColor(new Color((int)(255 * Math.random()), (int)(255 * Math.random()), (int)(255 * Math.random()))); 
						circles[circleCount].setX(newX);
						circles[circleCount].setY(newY);
						circles[circleCount].setRadius(rad);
						StdDraw.filledCircle(newX, newY, rad);
						circleCount++;
						break;
					}
					//System.out.println("Hey");
				}
			}
		}
		
		System.out.println(circleCount);

		StdDraw.show();
		
		long endTime = System.currentTimeMillis();
		System.out.println("Time " + (endTime - startTime));
	}

	public boolean circleWorks(double x1, double y1, double r1, int circleCount) {

		/*if ((x1+r1 >= 12) || (x1+r1 <=-12) || (y1+r1 >= 8) || (y1+r1 <=-8))
		{
			System.out.println("Touches end");
			return false;
		}*/
		for(int x = 0; x < circleCount; x++) { 
			double distance = Math.sqrt(Math.abs((circles[x].getX()-x1)) * Math.abs((circles[x].getX()-x1)) + Math.abs((circles[x].getY() - y1)) * Math.abs(circles[x].getY() - y1));
			//System.out.println(distance + " is distance.");
			//System.out.println(circles[x].getRadius() + r1);
			
			if(x1 + r1 >= 5.99999999999999 || x1 - r1 <= -5.999999999999) { 
				return false;
			}
			if(y1 + r1 >= 3.999999999999 || y1 - r1 <= -3.999999999999) { 
				return false;
			}
			
			if(distance <= circles[x].getRadius() + r1) { 
				return false;
			}	
		}
					
		
		return true;		
	}


	//  You may need to write a helper method or two.






	/**
	 *  Draws a pair of axes, over the drawn Circles.  Grid lines are drawn and
	 *  the scale is shown, to help the viewer see the size of the Circles.
	 */
	public void drawAxes ( )
	{
		Font font = new Font("Arial", Font.PLAIN, 18);
		StdDraw.setFont(font);
		StdDraw.setPenColor(new Color(220,220,220)); 
		for(double integers = -6; integers <= 6; integers++)
		{
			StdDraw.line(integers,-4,integers,4);
			StdDraw.line(-6,integers,6,integers);
			StdDraw.setPenColor(new Color(0,0,0)); 	
			StdDraw.text(integers,-0.4,"" + (int)integers);
			StdDraw.text(-0.3,integers-0.05,"" + (int)integers);
		}
		StdDraw.show();
	}

	/**
	 *  Adds the area of each circle to a total area.  Prints this total 
	 *  area to the terminal window.
	 */
	public void printArea ( )
	{	
		double area = 0.0;

		//  Your code here.
		
		for(int x = 0; x < 1000; x++) { 
			area = area + circles[x].getRadius() * circles[x].getRadius() * Math.PI;
		}

		System.out.println("\n\n\nTotal Area: " + area + "\n\n\n");
	}
}