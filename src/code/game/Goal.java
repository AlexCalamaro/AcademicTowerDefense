package code.game;

import java.awt.Dimension;
import java.awt.Point;

import code.enemies.Enemy;
import code.gui.PlayerStatusDisplay;
/**
 *
 * Goal is the finish line. Basically, it removes any enemies that hit and then have the player lose a life.
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 *
 */
public class Goal extends ImageActor implements Observer 
{
	public static final Dimension DEFAULT_SIZE=new Dimension(100, 100);
/**
 * Creates a goal object.
 * @param location The location at which to put the new goal object.
 */
	public Goal(Point location) 
	{
		super(location, DEFAULT_SIZE, "Goal");
	}
	/**
	 * This method is called by the GameEngine whenever a goal collides with an enemy. 
	 * If that happens, the enemy is removed from the board, and the board is notified of this occurence.
	 */
	public void collision(Enemy e)
	{
		Board.getBoard().removeActor(e);
		Board.getBoard().loseLife();
	}

}
