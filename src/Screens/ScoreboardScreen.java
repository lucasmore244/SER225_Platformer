package Screens;
import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.LevelState;
import Level.Map;
import Maps.Level3;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;
import java.awt.*;
import javax.swing.JTextField;

public class ScoreboardScreen extends Screen {
	protected ScreenCoordinator screenCoordinator;
	protected Map background;
	protected KeyLocker keyLocker = new KeyLocker();
	protected SpriteFont playersname;
	protected SpriteFont playertimedisplay;
	protected SpriteFont returnInstructionsLabel;
	protected Level3 level3;
	protected JTextField playersinput;
	protected String playertime;
	protected DisplayTime time;
	protected LevelLoseScreen levellose;
	protected LevelState levelState;
	

	public ScoreboardScreen(ScreenCoordinator screenCoordinator) {
		this.screenCoordinator = screenCoordinator;
	}

	@Override
	public void initialize() {
		// setup graphics on screen (background map, spritefont text)
		 background = new TitleScreenMap();
		background.setAdjustCamera(false);
		playersname = new SpriteFont("Enter your name", 60, 250, "Times New Roman", 30, Color.white);
		playersinput = new JTextField("");
		playertime = time.getTime();
		playertimedisplay = new SpriteFont(playertime, 20, 250, "Times New Roman", 30, Color.white);
		
	}

	public void update() {
		playersname = null;

		background.update(null);

        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        // if space is pressed, go back to main menu
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
            screenCoordinator.setGameState(GameState.SCOREBOARD);
        } else {
        	screenCoordinator.setGameState(GameState.MENU);
        }
	}

	public void draw(GraphicsHandler graphicsHandler) {
		background.draw(graphicsHandler);
		playersname.draw(graphicsHandler);
		playertimedisplay.draw(graphicsHandler);
	}
}

	
