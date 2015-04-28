package code.game;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.JPanel;

import code.gui.TowerDefenseGUI;
import code.gui.UpgradeMenu;
import code.towers.Tower;
import code.wave.WaveManager;

/**
 * Board is the pane which contains the list of all actors
 * It redraws all actors at their current locations, notifies interested observers in changes of lives, money
 * and waves and is a singleton.
 * 
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 * 
 * NOTE: Some code in this class based off Greenhills the Game, a project by Andrew Elenbogen and Ben Froehlich, 2011.
 */

public class Board extends JPanel implements MouseListener
{
	private ArrayList<PlayerStatObserver> observers;
	protected ArrayList<Actor> actors;
    private ArrayList<Actor> toBeAdded;
    private ArrayList<Actor> toBeRemoved;
	private static Image background;
	private static Board theBoard=new Board();
	
	private int money=10000;
	private int lives=30;
	
	/**
	 * Constructor for Board. Initializes background, actors and adds the path as the first actor
	 */
	private Board()
	{
		observers=new ArrayList<PlayerStatObserver>();
		this.setPreferredSize(new Dimension(800, 800));
		if(background==null)
			background=TowerDefenseGUI.loadImage("LinedPaper.jpg", 800, 800);
		actors=new ArrayList<Actor>();
		toBeAdded=new ArrayList<Actor>();
		toBeRemoved=new ArrayList<Actor>();
		Board.AddDefaults(this);
		addMouseListener(this);
	}
	/**
	 * Adds the default actors any game needs to the path.
	 * Specifically, adds the Path, the Wave Manager and the goal at appropiate locations.
	 * @param board The board to add the default objects to.
	 */
	private static void AddDefaults(Board board)
	{
		board.addActor(Path.getPath());
		board.addActor(WaveManager.getWaveManager());
		
	
		Point goalLoc=Path.getPath().getPoint()[Path.getPath().getPoint().length-1];
		goalLoc.y-=Goal.DEFAULT_SIZE.height;
		board.addActor(new Goal(goalLoc));
	}
	/**
	 * A getter for Board, as specified by the a singleton design pattern.
	 * @return The only board that exists.
	 */
	public static Board getBoard()
	{
		return theBoard;
	}
	
	
	/**
	 * Returns an iterator that iterates through all actors on the board in a synchronized way.
	 * The synchronization is needed because GameEngine runs in a seperate thread than the rest of the game.
	 * @return An iterator that is synchronized and iterates through the actors.
	 */
    public Iterator<Actor> getAllActors() {
        return Collections.synchronizedCollection(actors).iterator();
    }
    
    /**
     * Gives an Iterator that's a clone of allComponents
     * to avoid ConcurrentModificationExceptions.
     * @return  a clone of all components
     */
    public Iterator<Actor> getAllActorsClone() {
        return Collections.synchronizedCollection(new ArrayList<Actor>(actors)).iterator();
    }
	
	/**
	 * Adds all the actors in the given collection to the board. 
	 * @param actorsToAdd The collection of actors to add to the board.
	 */
	public void addActors(Collection<? extends Actor> actorsToAdd) {
		toBeAdded.addAll(actorsToAdd);
	}
	/**
	 * Adds the given actor to the board.
	 * @param actorToAdd The actor to add to the board.
	 */
	public void addActor(Actor actorToAdd) {
		toBeAdded.add(actorToAdd);
	}
	/**
	 * Removes the given actor from the board.
	 * @param actorToRemove The actor to remove from the board.
	 */
	public void removeActor(Actor actorToRemove) {
		toBeRemoved.add(actorToRemove);
	}
	
	/**
	 * Draws it's background at (0,0). Then, shifts the graphics object g to the location of each Actor on the board. 
	 * Then calls each actors paint method. This ensures that all actors consider (0,0) to be themselves, in essence
	 * carrying around their own coordinate system rather than having to refer to some central one.
	 * @param g The Graphics object to draw this on.
	 */
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(background, 0, 0, null);

        Iterator<Actor> it = getAllActorsClone();
        while (it.hasNext()) {
            Actor c = it.next();
            if(c != null) {
                Graphics translatedG = g.create();
                translatedG.translate(c.getLocation().x, c.getLocation().y);
                translatedG.setColor(Color.black);
                c.paint(translatedG);
            }
        }
	}
	
	/**
     * Adds the components that have been submitted for adding in the last tick.
     */    
    public synchronized void addWaitingActors() 
    {
         for(Actor c : toBeAdded) {
             this.actors.add(c);
         }
         toBeAdded.clear();
    }
    /**
     * Clears the actors that had been waiting to be added.
     */
    public synchronized void removeWaitingActors() 
    {
    	for(Actor c : toBeRemoved) 
    	{
    		actors.remove(c);
    	}
    	toBeRemoved.clear();
    }
    /**
     * @param The actor to check if the actors list contains
     *@return Whether or not the actors list contains the given actor.
     */
    public boolean contains(Actor actor)
    {
    	return actors.contains(actor);
    }
    
    /**
     * Get all actors within the given distance. Note that this is merely an estimation, but it is 
     * good enough for all practical purposes. Also, note that this method intentionally will not return
     * any out of bound objects, as towers should not be able to fire at things off screen.
     * @param location The location to get actors around. 
     * @param distance The distance to get actors within.
     * @return A list of actors that are within the given distance from the given location.
     */
    public ArrayList<Actor> getActorsWithinDistance(Point location, int distance)
    {
    	ArrayList<Actor> inDistance=new ArrayList<Actor>();
    	
    	
    	for(Actor current: actors)
    	{
    		double distanceToBoundBoxCenter=Math.sqrt(Math.pow(current.getSize().height, 2)+Math.pow(current.getSize().width, 2));
    		double distanceFromPointToCenter=current.getLocation().distance(location);
    		if(distance>(distanceFromPointToCenter-distanceToBoundBoxCenter) && this.isInBounds(current.getLocation()))
    		{
    			inDistance.add(current);
    		}
    			
    	}
    	return inDistance;
    }
    /**
     * Returns the box used for collisions associated with a given actor.
     * @param actor The actor to get the bounding box for
     * @return The bounding box of the given actor.
     */
    public Rectangle getBoundingBox(Actor actor)
    {
    	return new Rectangle(actor.getLocation(), actor.getSize());
    }
    /**
     * Returns whether or not the given point is inbounds.
     * @param point The point to check
     * @return A boolean: True if that point is in the boards dimensions, false otherwise.
     */
    public boolean isInBounds(Point point)
    {
    	if(point.x>this.getPreferredSize().width || point.y>this.getPreferredSize().height)
    	{
    		return false;
    	}
    	else
    	{
    		return true;
    	}
    }

    /**
     * Registers the given PlayerStatObserver to observe.
     * This employs the observer design pattern, and observers will be notified whenever
     * lives, money or waves change. The Board also notifies them right off the bat.
     * @param pso The person signing up to observe.
     */
    public void signUpToObserve(PlayerStatObserver pso)
    {
    	observers.add(pso);
    	pso.notify(lives, money, WaveManager.getWaveManager().getCurrentWave());
    }
    /**
     * Tells the board to lose a life, then notifies observers. Is called only by Goal at present, 
     * since only the Goal knows when enemies collide with it.
     */
    public void loseLife()
    {
    	lives--;
    	notifyObservers();
    	
    }
    /**
     * Notifies all observers of any changes in the status of lives, money and waves.
     */
    public void notifyObservers()
    {
    	for(PlayerStatObserver pso: observers)
    	{
    		pso.notify(lives, money, WaveManager.getWaveManager().getCurrentWave());
    	}
    }
    /**
     * @return Whether or not the player is alive.
     */
    public boolean alive()
    {
    	return lives>0;
    }

    /***
     * Increases the players money by the given amount and then notifies observers. 
     * @param increase The amount to increase the players money by.
     */
    public void increaseMoney(int increase)
    {
    	money+=increase;
    	notifyObservers();
    }
    /**
     * Checks if the player has the given amount of money.
     * If they do, spend it, notify observers and return true.
     * Otherwise, return false.
     * 
     * This method is implemented in this manner to avoid having to send two separate calls
     * to board every time another class wants spend money.
     * @param amount The amount to spend
     * @return Whether or not you have that much
     */
    public boolean spendMoney(int amount)
    {
    	if(money>=amount)
    	{
    		money-=amount;
    		notifyObservers();
    		return true;
    	}
    	return false;
    }


	@Override
	/**
	 * This is method required to exist bt mouse listener. Basically, it just checks for a tower
	 * at the clicked point, and if it finds one informs the UpgradeMenu singleton of that fact. 
	 * @param e The Mous click event in question
	 */
	public void mouseClicked(MouseEvent e) 
	{
		Tower found;
		for(Actor current: actors)
		{
			if(current instanceof Tower && this.getBoundingBox(current).contains(e.getPoint()))
			{
				found=(Tower) current;
				UpgradeMenu.getUpgradeMenu().notifyOfClick(found);
				break;
			}
		}
	}

	/**
	 * These methods are required by the mouse listener interface, but are not actually used here
	 * so they have been left intentionally blank.
	 */
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e){
	}
}
