package code.game;

import code.enemies.Enemy;
/**
 * A very simple interface and modified implementation of the observer design pattern.
 * Basically, any class that implements this interface will be notified via the collision method
 * by GameEngine anytime they colldie with an enemy. This way, the GameEngine does not have to have a 
 * hardcoded list of classes that care about collisions.
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */

public interface Observer 
{
	public void collision(Enemy enemy);
}
