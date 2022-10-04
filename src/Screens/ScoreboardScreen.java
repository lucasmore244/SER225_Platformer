package Screens;
import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.LevelState;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;
import java.awt.*;
import java.util.Scanner;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ScoreboardScreen extends Screen {
	Scanner scanner = new Scanner(System.in);
	protected ScreenCoordinator screenCoordinator;
	protected Map background;
	protected KeyLocker keyLocker = new KeyLocker();
	protected SpriteFont playertimeheader, playertimedisplay, playersnameheader,playersnamedisplay;
	protected DisplayTime time = new DisplayTime();
	protected String playertime = time.getTime(), playersinput;
	protected LevelLoseScreen levellose;
	protected LevelState levelState;
	protected JTable table;
	

	public ScoreboardScreen(ScreenCoordinator screenCoordinator) {
		this.screenCoordinator = screenCoordinator;
	}

	@Override
	public void initialize() {
		playersnameheader = new SpriteFont("Player Name", 200, 50, "Times New Roman", 20, Color.white);
		playertimeheader = new SpriteFont("Time Used", 450, 50, "Times New Roman", 20, Color.white);
		String data[][]= new String[4][10]; 
		String[] columnNames = { "Player Name", "Time Used"};
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		table = new JTable(model);
		keyLocker.lockKey(Key.SPACE);
	}

	public void update() {
		if (levelState == LevelState.LEVEL_COMPLETED) {
			System.out.println("Enter your name");
			playersinput = scanner.nextLine();
			playersnamedisplay = new SpriteFont(playersinput, 200, 70, "Times New Roman", 20, Color.white);
			playertimedisplay = new SpriteFont(playertime, 450, 70, "Times New Roman", 20, Color.white);	
		}
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        // if space is pressed, go back to main menu
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
            screenCoordinator.setGameState(GameState.MENU);
        } 
	}

	public void draw(GraphicsHandler graphicsHandler) {
		playersnameheader.draw(graphicsHandler);
		playertimeheader.draw(graphicsHandler);
	}
}

	
