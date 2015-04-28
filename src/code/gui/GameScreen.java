package code.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import code.game.Board;
import code.game.GameEngine;
/**
 *GameScreen is the Jpanel from which the entire game and in-game menus exist.
 * @author Andrew Elenbogen, Aidan Carroll, Alex Calamaro, Jerry Wei
 * @version June 9, 2014
 */
public class GameScreen extends JPanel 
{
	private GameEngine engine;
	/**
	 * Intializes the GameScreen by creating an engine, starting it, and using a GridBag layout.
	 */
	public GameScreen() 
	{
		super();
		engine=GameEngine.getEngine();
		engine.setUI(this);
		engine.start();
		initGui();
	}
	/**
	 * A private helper method used to move some of the clutter out of the constructor.
	 */
	private void initGui()
	{
		this.setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;

		this.add(Board.getBoard(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;

		this.add(new MenuPanel(), c);
	}
	/**
	 * This method is called by the GameEngine to inform the GameScreen that the game is over.
	 * It pops up a message informing the player of their success or failure.
	 * @param won True if the player won, false if they lost.
	 */
	public void GameOver(boolean won)
	{
		if(won)
		{
			this.popUpMessage("You Won!", "Congratulations, all the students dropped out. YOU WIN!", new Font("Arial", Font.BOLD, 40), "youwin.jpg", false);
		}
		else
		{
			this.popUpMessage("You Lose!", "Too many students graduated. You lost.", new Font("Arial", Font.BOLD, 40), "youlose.jpg", false);
		}
	}
    /**
     * Pops up a new message window with the potential for text, an image, and a "Start" button,
     * which hides the window and starts the game.
     * This method was created originally by Ben Froehlich and Andrew Elenbogen for the Pacrobber project. 2007
     * 
     * @param title                 the title of the window
     * @param text                  the text to be displayed in the window
     * @param font                  the font of the text
     * @param imageName             the name of the image (eg "thief.png") to include in the window, possibly null
     * @param includeStartButton    whether or not to include a start button in the window
     */
    public void popUpMessage(String title, String text, Font font, String imageName, boolean includeStartButton) 
    {
        final JFrame popUp = new JFrame(title);
        JPanel popUpP = new JPanel();
        popUpP.setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea(text);
        if(font != null) {
            textArea.setFont(font);
        }
        textArea.setEditable(false);
        popUpP.add(textArea, BorderLayout.NORTH);
        if(imageName != null) {
            PicturePanel middle = new PicturePanel(imageName);
            popUpP.add(middle, BorderLayout.CENTER);
        }                
        if(includeStartButton) {
            JButton startB = new JButton("Ok");
            startB.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    popUp.setVisible(false);
                    //Start the Game
                }
            });
            JPanel bottom = new JPanel();
            bottom.add(startB, CENTER_ALIGNMENT);
            popUpP.add(bottom, BorderLayout.SOUTH);
            
        }
        popUp.add(popUpP);
        popUp.setVisible(true);
        popUp.pack();
    }
    /**
     * Picture Panel is the panel used by pop-up message to display the various popups
     *
     */
    private class PicturePanel extends JPanel {
        private Image image;
        
        /**
         * Constructor for objects of class PicturePanel.
         * @param imageName the name of the image (eg "thief.png") to be displayed.
         */
        public PicturePanel(String imageName) {
            try {
                ImageIcon icon = new ImageIcon(imageName);
                image = icon.getImage();
                this.setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
                repaint();
            }
            catch(Exception e) {
                System.out.println("Invalid File Path");
            }
        }
        
        /**
         * Paints the image in this PicturePanel.
         * @param g the graphics object used to paint the image
         */
        public void paint(Graphics g) {
            int x = (getWidth() - image.getWidth(this))/2;
            int y = (getHeight() - image.getHeight(this))/2;
            g.drawImage(image, x, y, null);
        }
    }

}
