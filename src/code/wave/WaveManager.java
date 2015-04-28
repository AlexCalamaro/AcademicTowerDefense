package code.wave;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;


import code.enemies.*;
import code.game.*;

/**
 * This actor adds waves of enemies into the game at the correct times.
 * It is a singleton
 * 
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */
public class WaveManager extends Actor{

	//stores the singleton
	private static WaveManager manager;
	
	//stores all the waves
	private Wave[] waves;
	
	//tracks which wave will be played next
	private int currentWave;
	
	//tracks what tick we are on so it can create enemies at the right time
	private int tick;
	
	//tracks if the game is plaing or it is in the between wave state
	private boolean isPlaying;
	
	//used to notify the board when there are no more waves
	private boolean gameOver;
	
	/**
	 * private constructor used for the singleton. sets all default values
	 */
	private WaveManager()
	{
		
		super(new Point(-50, -50), new Dimension(0, 0));
		tick = 0;
		currentWave = 0;
		isPlaying = false;
		waves = new Wave[8];
		gameOver = false;
		
		createDefaultWaves();
	}
	
	/**
	 * @return the current wave you are on. +1 so that it is indexed at 1
	 */
	public int getCurrentWave(){
		return currentWave + 1;
	}
	
	/**
	 * @return the singleton wave manager
	 */
	public static WaveManager getWaveManager(){
		//lazy instantiation
		if(manager == null){
			manager = new WaveManager();
		}
		return manager;
	}
	
	/**
	 * called every tick of the board. adds enemies correctly
	 * If the game is not playing, do nothing
	 */
	public void update()
	{
		//makes sure the game is playing
		if(isPlaying && currentWave<waves.length)
		{
			//this denotes that the are no more enemies in the current wave
			if(waves[currentWave].getNextTime() == -1)
			{
				isPlaying = false;
				tick = 0;
				currentWave++;
				
				//if there are no more levels in the game
				if(currentWave == waves.length){
					gameOver= true;
					currentWave--;
				}
			}
			
			//if it is the correct time add the next enemy
			else if(waves[currentWave].getNextTime() == tick)
			{
				waves[currentWave].addNextEnemy();
			}
			
			
			tick++;
		}
		
		else{
			tick = 0;
		}
	}
	
	
	/**
	 * @return true if there are more levels to the game
	 */
	public boolean moreWaves(){
		return !gameOver;
	}
	
	/**
	 * called when the startWave button is pressed
	 */
	public void startWave(){
		if(!isPlaying)
		{
			isPlaying = true;
			
			Board.getBoard().notifyObservers();
		}
	}
	
	/**
	 * creates the default waves
	 */
	private void createDefaultWaves(){
		
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		ArrayList<Integer> times = new ArrayList<Integer>();
		
		Point start = Path.getPath().getPoint()[0];
		
		//Wave 1
		for(int i = 0; i < 5; i++){
			enemies.add(new BasicEnemy(new Point(start.x, start.y)));
		}
		for(int i = 0; i < 5; i++){
			times.add(new Integer(i*50));
		}
		
		
		waves[0] = new Wave(enemies, times);
		
		
		//Wave 2
		
		enemies = new ArrayList<Enemy>();
		times = new ArrayList<Integer>();
		
		for(int i = 0; i < 10; i++){
			enemies.add(new BasicEnemy(new Point(start.x, start.y)));
		}
		for(int i = 0; i < 10; i++){
			times.add(new Integer(i*50));
		}
		waves[1] = new Wave(enemies, times);
		
		//Wave 3
		
		enemies = new ArrayList<Enemy>();
		times = new ArrayList<Integer>();
			
		for(int i = 0; i < 5; i++){
			enemies.add(new FastEnemy(new Point(start.x, start.y)));
		}
		for(int i = 0; i < 5; i++){
			times.add(new Integer(i*50));
		}
		waves[2] = new Wave(enemies, times);
		
		
		//Wave 4 
		
		enemies = new ArrayList<Enemy>();
		times = new ArrayList<Integer>();
			
		for(int i = 0; i < 5; i++){
			enemies.add(new BasicEnemy(new Point(start.x, start.y)));
		}
		for(int i = 0; i < 5; i++){
			enemies.add(new FastEnemy(new Point(start.x, start.y)));
		}	
		for(int i = 0; i < 10; i++){
			times.add(new Integer(i*50));
		}
		waves[3] = new Wave(enemies, times);
		
		//Wave 5 
		
		enemies = new ArrayList<Enemy>();
		times = new ArrayList<Integer>();
			
		for(int i = 0; i < 6; i++){
			enemies.add(new RegenerateEnemy(new Point(start.x, start.y)));
		}
		for(int i = 0; i < 6; i++){
			enemies.add(new FastEnemy(new Point(start.x, start.y)));
		}	
		for(int i = 0; i < 12; i++){
			times.add(new Integer(i*50));
		}
		
		waves[4] = new Wave(enemies, times);
		//Wave 6
		enemies = new ArrayList<Enemy>();
		times = new ArrayList<Integer>();
			
		for(int i = 0; i < 7; i++){
			enemies.add(new RegenerateEnemy(new Point(start.x, start.y)));
		}
		for(int i = 0; i < 7; i++){
			enemies.add(new ArmorEnemy(new Point(start.x, start.y)));
		}	
		for(int i = 0; i < 14; i++){
			times.add(new Integer(i*50));
		}
		waves[5] = new Wave(enemies, times);
		//Wave 7
		enemies = new ArrayList<Enemy>();
		times = new ArrayList<Integer>();
			
		for(int i = 0; i < 8; i++){
			enemies.add(new RegenerateEnemy(new Point(start.x, start.y)));
		}
		for(int i = 0; i < 8; i++){
			enemies.add(new ArmorEnemy(new Point(start.x, start.y)));
		}	
		for(int i = 0; i < 16; i++){
			times.add(new Integer(i*50));
		}
		
		waves[6] = new Wave(enemies, times);
		//Wave 8
		enemies = new ArrayList<Enemy>();
		times = new ArrayList<Integer>();
			
		for(int i = 0; i < 10; i++){
			enemies.add(new RegenerateEnemy(new Point(start.x, start.y)));
		}
		for(int i = 0; i < 10; i++){
			enemies.add(new BossEnemy(new Point(start.x, start.y)));
		}	
		for(int i = 0; i < 20; i++){
			times.add(new Integer(i*50));
		}
		waves[7] = new Wave(enemies, times);
		
		
	}
	
}
