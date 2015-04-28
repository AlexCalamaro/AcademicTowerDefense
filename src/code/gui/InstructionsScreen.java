package code.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JTextArea;
/**
 * 
instructions Screen is a simple text screen that just displays information about how to play the game.
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */
public class InstructionsScreen extends TextScreen 
{
	private static final String instructions="You are a teacher employed at the prestigous Carleton College. Due to extreme budget cuts "
			+"your school has only purchased 30 diplomas. If more than that number of students graduate this year, you and all your fellow"
			+ "teachers will lose your jobs! To stop them, drag and drop classes from the side menu to the screen. Then, when you are ready, "
			+ "press start wave to start a wave of students. They will move down the prescibed path in a futile attempt to gradate. As students "
			+ "drop or fail out of school, the board of trustees will increase your funding, allowing you to afford more classes. Be warned "
			+ "however: each year the students will be better the last, so you need to keep adding more classes. Can you defeat all waves of "
			+ "students without losing your job?";
	
	public InstructionsScreen(JPanel lastScreen, TowerDefenseGUI ui)
	{
		super(instructions, lastScreen, ui);
	}
}


