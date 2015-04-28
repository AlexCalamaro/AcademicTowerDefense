package code.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
/**
 * Path is a singleton data storage class that represents the path the enemies run down.
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * * @version June 9, 2014
 */
public class Path extends ImageActor{
	
	private Point[] points;
	private static Path thePath;
/**
 * Initializes the internal array using the given array
 */
	private Path(Point[] path){
		super(new Point(0,0), new Dimension(50,50), "Path");
		points = path;
	}
	/**
	 * Returns the single path that exists. Utilized lazy instantiation.
	 * @return The single path that exists.
	 */
	public static Path getPath()
	{
		if(thePath==null)
			thePath=getDefaultPath();
		return thePath;
	}
	/**
	 * @return The list of points that make up the path.
	 */
	public Point[] getPoint()
	{
		return points;
	}
	/**
	 * Draws the path by iterating through the list of points and drawing lines between them.
	 * NOTE: Will have unpredictable behavior if the points are not at 90 degree angles relative to each other.
	 */
	public void paint(Graphics g)
	{
		int curX = 0;
		int curY = 0;
		for(int i = 0; i < points.length;)
		{
			if(curX != points[i].x){
				if(curX < points[i].x)
				{
					g.drawImage(image, curX, curY, null);
					curX += 50;
					if(curX > points[i].x){
						curX = points[i].x;
					}
				}
				else
				{
					g.drawImage(image, curX-50, curY, null);
					curX -= 50;
					if(curX < points[i].x){
						curX = points[i].x;
					}
				}
			}
			
			else if(curY != points[i].y){
				if(curY < points[i].y){
					g.drawImage(image, curX, curY, null);
					curY += 50;
					if(curY > points[i].y){
						curY = points[i].y;
					}
				}
				else{
					g.drawImage(image, curX, curY-50, null);
					curY -= 50;
					if(curY < points[i].y){
						curY = points[i].y;
					}
				}
			}
			else{
				g.drawImage(image, curX, curY, null);
				i++;
			}
			
		}

	}
	/**
	 * @return The default path the enemies should run down.
	 */
	private static Path getDefaultPath()
	{
		return new Path(new Point[]{new Point(0,0),
				new Point(200, 0), new Point(200, 250), new Point(100, 250),
				new Point(100, 500), new Point(500, 500), new Point(500, 400),
				new Point(400, 400), new Point(400, 200), new Point(700, 200),
				new Point(700, 800)});
	}		
}

