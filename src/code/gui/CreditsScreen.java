package code.gui;

import javax.swing.JPanel;
/**
 * Credits Screen is a simple text screen that just displays information about the makers of the game.
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */
public class CreditsScreen extends TextScreen 
{
	private static final String credits="This game made by Alex Calamaro, Aidan Carroll, Andrew Elenbogen, and Jerry Wei. "+
"It was made for Software Design Class during Spring Term 2014 at Carleton College. Thanks to Amy Csizmar Dalal for her help " +
"and mentorship throughout the project and course.";
	
	public CreditsScreen(JPanel lastScreen, TowerDefenseGUI ui)
	{
		super(credits, lastScreen, ui);
	}
}
