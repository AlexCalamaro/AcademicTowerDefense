package code.game;

/**
 * The PlayerStatObserver is any class that cares about the state of lives, money and waves/levels in the 
 * board. Any such classes need to implement this interface, then register with the board and they will 
 * be notified anytime the state of these values changes.
 * 
 * Employs Observer design pattern.
* @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
* @version June 9, 2014
* */
public interface PlayerStatObserver 
{
	public void notify(int lives, int money, int level);

}
