package code.game;

import java.awt.Shape;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import code.enemies.Enemy;
import code.gui.GameScreen;
import code.gui.TowerDefenseGUI;
import code.wave.WaveManager;
import cs101.awt.geom.ShapeUtils;

/**
 * The GameEngine is a singleton responsible for updating all Actors in the board each tick, checking 
 * for wins and loses and handling collision detection. It is multi-threaded such that the game can 
 * deal with player input via buttons ect. and simultaneously keep everything moving. Failing to do this
 * can result in some weird issues with input being missed or slow unresponsive play.
 * 
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 * 
 * Note: This class based off Code by Andrew Elenbogen and Ben Froehlich, Greenhills the Game, 2011
 * That class was based off code from a class called BreakoutEngine in a breakout class I have lost all record of.
 */
public class GameEngine implements Runnable 
{

	public static int TICK_LENGTH = 20;
	private Thread thread;

	private GameStatus status;
	private GameScreen ui;
	private static GameEngine theEngine;

	/**
	 * Intializes the GameEngine by basically just initializing the thread.
	 */
	private GameEngine() 
	{
		if(thread == null || !thread.isAlive()) {
			this.thread = new Thread(this);
			this.thread.start();
		}
	}
/**
 * Gets the singleton GameEngine, intialzies it if required.
 * This is known as lazy instantiation. 
 * @return The singleton GameEngine.
 */
	public static GameEngine getEngine()
	{
		if(theEngine==null)
			theEngine=new GameEngine();
		return theEngine;
	}

	/**
	 * Starts or unpauses the Game. 
	 * 
	 * Does nothing if the Game is in the won or Lost states, since such states are permanent, and recreates
	 * the thread if required.
	 */
	public void start() 
	{
		if(this.status!=GameStatus.WON && this.status!=GameStatus.LOST)
		{
			Board.getBoard().requestFocusInWindow();


			if(thread == null || !thread.isAlive()) {
				this.thread = new Thread(this);
				this.thread.start();
			}

			this.status = GameStatus.PLAYING;
		}
	}

	/** Stops the game. * */
	public void stop() 
	{
		if(this.status!=GameStatus.WON && this.status!=GameStatus.LOST)
			this.status = GameStatus.STOPPED;
	}

	/** This method should ONLY be called by the Thread field of GameEngine
	 * having other classe call it will lead to unpreditcable and undesirable results. * */
	public void run() 
	{
		while (status!=GameStatus.WON && status!=GameStatus.LOST) 
		{
			long time = System.currentTimeMillis();
			Board.getBoard().requestFocusInWindow();
			if(status==GameStatus.PLAYING)
			{
				tick();
			}
			updateActorsToReflectLastTick();

			//Have the game sleep to keep ticks a constant length.
			//This is done even when the game isn't running to keep the UpdateActors method from running constantly
			long timePassed = (System.currentTimeMillis() - time);
			try
			{
				if(timePassed < TICK_LENGTH) 
				{
					Thread.sleep(TICK_LENGTH - timePassed);
				}
			}
			catch (InterruptedException ie) 
			{
				System.err.println("Board: sleep() interrupted: "+ ie.getMessage());
			}
		}
	}
	/**
	 * Actors who are added to/removed from the board are forced to wait until the end of a tick to actually 
	 * be addded/removed. The reason for this is that doing otherwise would result in concurrent modification exceptions.
	 * After actors are added/removed, the board is repainted so the changes actually show up.
	 */
	private void updateActorsToReflectLastTick()
	{
		Board.getBoard().addWaitingActors();
		Board.getBoard().removeWaitingActors();
		Board.getBoard().repaint();
	}

	/**
	 * Updates the state of the world by one tick.
	 */
	private void tick() 
	{
		/*This boolean is used for victory checking*/
		boolean hasOneEnemy=false;
		/* update phase */
		Iterator<Actor> iterator = Board.getBoard().getAllActors();
		while (iterator.hasNext()) 
		{
			List<Actor> overlapping = new ArrayList<Actor>();
			Actor currentActor = iterator.next();
			//Two iterators are required to check for collisions.
			Iterator<Actor> otherIterator = Board.getBoard().getAllActors();
			while (otherIterator.hasNext()) 
			{
				Actor otherActor = otherIterator.next();
				Shape currentShape = Board.getBoard().getBoundingBox(currentActor);
				Shape otherShape = Board.getBoard().getBoundingBox(otherActor);
				//If the two actors overlap and they are not the same actor, they overlap.
				if (!currentActor.equals(otherActor)
						&& currentShape.intersects(otherShape.getBounds2D())
						&& ShapeUtils.isOverlapping(currentShape, otherShape)) 
				{
					overlapping.add(otherActor);
				}
			}
			//For this game, we only care about collisions that involve enemies
			if (currentActor instanceof Enemy) 
			{
				hasOneEnemy=true;
				Iterator<Actor> overlappingIterator = overlapping.iterator();
				while (overlappingIterator.hasNext()) 
				{
					Actor collidedWith = overlappingIterator.next();
					/*
					 *If the other actor is an observer, notify them of the collision. 
					 */
					if(collidedWith instanceof Observer )
					{
						((Observer) collidedWith).collision((Enemy)currentActor);
					}
				}
			}
			currentActor.update();
		}

		iterator = Board.getBoard().getAllActors();

		
		if(!Board.getBoard().alive())
		{
			status=GameStatus.LOST;
		}
		else if(!WaveManager.getWaveManager().moreWaves() && !hasOneEnemy)
		{
			status=GameStatus.WON;
		}
		/* win/lose maintenance phase */
		if (this.getGameStatus() == GameStatus.WON) 
		{
			ui.GameOver(true);
		} 
		else if (this.getGameStatus() == GameStatus.LOST) 
		{
			ui.GameOver(false);
		}
	}
	/**
	 * @return The current status of the game.
	 */
	public GameStatus getGameStatus() 
	{
		return status;
	}
	/**
	 * Sets the GameEngines UI to the given UI.
	 * This is done here instead of in the GameEngines constructor because the singleton design pattern
	 * makes it impossible to pass things directly to that method.
	 * @param ui The TowerDefenseGUI associated with this GameEngine.
	 */
	public void setUI(GameScreen ui)
	{
		this.ui=ui;
	}
}