package code.wave;

import java.awt.Point;
import java.util.ArrayList;

import code.enemies.BasicEnemy;
import code.enemies.Enemy;
import code.game.Actor;
import code.game.Board;
import code.game.Path;

/**
 * Holds an array of enemies and the times for them to be added to the board
 * 
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */
public class Wave 
{
	private ArrayList<Enemy> enemies;
	private ArrayList<Integer> times;

	/**
	 * @param enemies: the list of enemies
	 * @param times: the list of times
	 */
	public Wave(ArrayList<Enemy> enemies, ArrayList<Integer> times) 
	{
		this.enemies = enemies;
		this.times = times;
	}
	
	/**
	 * @return: the time to add the next enemy
	 * 			If there are no more enemies return -1
	 */
	public int getNextTime(){
		if(times.size() <= 0){
			return -1;
		}
		return times.get(0);
	}
	
	/**
	 * adds the next enemy to the Board
	 * @return true if an enemy was successfully added
	 */
	public boolean addNextEnemy(){
		if(enemies.size()<=0){
			return false;
		}
		//removes both the enemy and the corresponding time from the lists
		Board.getBoard().addActor(enemies.remove(0));
		times.remove(0);
		return true;
	}
	
	

}
