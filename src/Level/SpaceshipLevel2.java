package Level;
import Engine.Key;
import java.util.Calendar;
import java.util.Date;
import javax.swing.Timer;

import javax.swing.JPanel;

import Builders.MapTileBuilder;
import Engine.KeyLocker;
import Engine.Keyboard;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Players.Cat;
import Players.CatLevel3;
import Players.SpaceshipPlayer;
import Utils.AirGroundState;
import Utils.Direction;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class SpaceshipLevel2 extends JPanel {	
		    // values that affect player movement
	    // these should be set in a subclass
	private Timer timer;
	private boolean upPressed; 
	private boolean downPressed;
	private SpaceshipPlayer spaceship;
	int speed = 5;
	
	public SpaceshipLevel2() {
		timer = new Timer(1, new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(upPressed) {
					spaceship.setLocation(spaceship.getX(), spaceship.getY() - 5);
				}
				else if(downPressed) {
					spaceship.setLocation(spaceship.getX(), spaceship.getY() + 5);
				}
				repaint();
			}
		});
		
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					upPressed = true;
//					System.out.println("pressed");
				}
				else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					downPressed = true;
				}
				repaint();
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					upPressed = false;
				}
				else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					downPressed = false;
				}
			}
		});
		
		this.setFocusable(true);
		this.setDoubleBuffered(true);
	}
}


