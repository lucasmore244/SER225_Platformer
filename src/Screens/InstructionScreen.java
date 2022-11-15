package Screens;

import java.awt.Color;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import SpriteFont.SpriteFont;

public class InstructionScreen extends Screen {
	protected ScreenCoordinator screenCoordinator;
	protected KeyLocker keyLocker = new KeyLocker();
	protected SpriteFont header, instruction1, instruction2, instruction3, instruction4, instruction5, backtohomescreen;

	public InstructionScreen(ScreenCoordinator screenCoordinator) {
		this.screenCoordinator = screenCoordinator;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		header = new SpriteFont("INSTRUCTIONS", 300, 50, "Times New Roman", 30, Color.BLACK);
		header.setFontStyle(1);
		backtohomescreen = new SpriteFont("Click the Space Bar to go back to Previous Screen", 0, 550,
				"Times New Roman", 30, Color.BLACK);
		backtohomescreen.setFontStyle(2);
		instruction1 = new SpriteFont("1. Use the Arrow Keys for movement", 0, 100, "Times New Roman", 20, Color.RED);
		instruction1.setFontStyle(2);
		instruction2 = new SpriteFont("2. In Level2, Hold the Q Key to shoot at the asteriods", 0, 150,
				"Times New Roman", 20, Color.YELLOW);
		instruction2.setFontStyle(2);
		instruction3 = new SpriteFont("3. In Level4, Hold the Q key to attack the enemy", 0, 200, "Times New Roman", 20,
				Color.BLUE);
		instruction3.setFontStyle(2);
		instruction4 = new SpriteFont("4. Click the P key to Pause", 0, 250, "Times New Roman", 20, Color.BLACK);
		instruction4.setFontStyle(2);
		instruction5 = new SpriteFont("5. Click the M key for background song options", 0, 300, "Times New Roman", 20,
				Color.GRAY);
		instruction5.setFontStyle(2);
		keyLocker.lockKey(Key.SPACE);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (Keyboard.isKeyUp(Key.SPACE)) {
			keyLocker.unlockKey(Key.SPACE);
		}
		// if space is pressed, go back to main menu
		if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
			screenCoordinator.setGameState(GameState.MENU);
		}
	}

	@Override
	public void draw(GraphicsHandler graphicsHandler) {
		// TODO Auto-generated method stub
		header.draw(graphicsHandler);
		backtohomescreen.draw(graphicsHandler);
		instruction1.draw(graphicsHandler);
		instruction2.draw(graphicsHandler);
		instruction3.draw(graphicsHandler);
		instruction4.draw(graphicsHandler);
		instruction5.draw(graphicsHandler);
	}
}
