//This class displays a beginning screen when the game begins
package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import MapEditor.MusicPanel;
import Maps.BeginningMap;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;
import Utils.Stopwatch;

import java.awt.*;

public class BeginningScreen extends Screen {
	protected ScreenCoordinator screenCoordinator;
	protected Map background;
	protected KeyLocker keyLocker = new KeyLocker();
	protected SpriteFont catNarration, welcome, gotohomescreen;

	public BeginningScreen(ScreenCoordinator screenCoordinator) {
		this.screenCoordinator = screenCoordinator;
	}

	@Override
	public void initialize() {
		// setup graphics on screen (background map, spritefont text)
		background = new BeginningMap();
		background.setAdjustCamera(false);
		welcome = new SpriteFont("Please save my kittens", 250, 250, "Times New Roman", 30, Color.CYAN);
		welcome.setFontStyle(2);

		gotohomescreen = new SpriteFont("Click the Space Bar to go to Game Screen", 0, 550, "Times New Roman", 30,
				Color.white);

	}

	public void update() {
		background.update(null);

		// if space is pressed, go back to main menu
		if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
			screenCoordinator.setGameState(GameState.MENU);
		}
	}

	public void draw(GraphicsHandler graphicsHandler) {
		background.draw(graphicsHandler);
		welcome.draw(graphicsHandler);
		gotohomescreen.draw(graphicsHandler);
	}
}