package Engine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

import javax.swing.*;

import Game.GameState;
import Game.ScreenCoordinator;

/*
 * The JFrame that holds the GamePanel
 * Just does some setup and exposes the gamePanel's screenManager to allow an external class to setup their own content and attach it to this engine.
 */
public class GameWindow implements KeyListener{
	private JFrame gameWindow;
	private GamePanel gamePanel;
	private JLabel imageLabel = new JLabel();
	private Timer timer;
	protected ImageIcon ii;
	protected KeyLocker keylocker = new KeyLocker();
//	protected ScreenCoordinator screenCoordinator;

	public GameWindow() {
		gameWindow = new JFrame("Game");
		gameWindow.setResizable(false);
		gameWindow.setSize(Config.GAME_WINDOW_WIDTH, Config.GAME_WINDOW_HEIGHT);
		gameWindow.setLocationRelativeTo(null);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // it'd be nice if this actually worked more than
																	// 1/3rd of the time	
		playVideo();
	}

	public void switchToGamePanel() {
		gamePanel = new GamePanel();
		gamePanel.setFocusable(true);
		gamePanel.requestFocusInWindow();
		gamePanel.setSize(800, 605);
		gameWindow.setContentPane(gamePanel);
//		gamePanel.revalidate();
//		gamePanel.repaint();
		gamePanel.requestFocus();
		gamePanel.setupGame();
		startGame();
		ScreenManager screenManager = getScreenManager();
		screenManager.setCurrentScreen(new ScreenCoordinator());
	}

	// triggers the game loop to start as defined in the GamePanel class
	public void startGame() {
		gamePanel.startGame();
	}

	public ScreenManager getScreenManager() {
		return gamePanel.getScreenManager();
	}

	public JFrame getGameFrame() {
		return gameWindow;
	}

	public void playVideo() {	
		try {
			JPanel contentPane = new JPanel();
			contentPane.setFocusable(true);
			contentPane.requestFocus();
			contentPane.setLayout(new BorderLayout());
			contentPane.setBackground(Color.BLACK);
			contentPane.addKeyListener(this);
			// add the image label
				ii = new ImageIcon(
						new ImageIcon("./src/Video.gif").getImage().getScaledInstance(800, 305, Image.SCALE_DEFAULT));
	
			imageLabel.setIcon(ii);
			imageLabel.setLocation(0, 0);
			gameWindow.setContentPane(contentPane);
			imageLabel.setFocusable(false);
			contentPane.add(imageLabel, BorderLayout.CENTER);
			int delay = 8000;
			timer = new Timer(1, new ActionListener() {
				long startTime = System.currentTimeMillis();

				@Override
				public void actionPerformed(ActionEvent e) {
					if (System.currentTimeMillis() - startTime >= delay) {
						timer.stop();
						switchToGamePanel();
					}
				}
			});
			timer.start();
			timer.setRepeats(true);
			gameWindow.setVisible(true);
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			timer.stop();
			switchToGamePanel();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
