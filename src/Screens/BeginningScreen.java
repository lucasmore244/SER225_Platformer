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
	protected Stopwatch stopwatch = new Stopwatch();
	protected Stopwatch stopwatch2 = new Stopwatch();
	protected Stopwatch stopwatch3 = new Stopwatch();
	protected Stopwatch stopwatch4 = new Stopwatch();
	protected Stopwatch stopwatch5 = new Stopwatch();
	protected SpriteFont catNarration, welcome, gotohomescreen;
//	    protected SpriteFont returnInstructionsLabel;

	public BeginningScreen(ScreenCoordinator screenCoordinator) {
		this.screenCoordinator = screenCoordinator;
	}

	@Override
	public void initialize() {
		// setup graphics on screen (background map, spritefont text)
		background = new BeginningMap();
		background.setAdjustCamera(false);
//		welcome = new SpriteFont("Welcome to the Cosmic Cat", 200, 250, "Times New Roman", 30, Color.white);
		welcome = new SpriteFont("Please save my kittens", 250, 250, "Times New Roman", 30, Color.CYAN);
		welcome.setFontStyle(2);
//		catNarration = new SpriteFont("Hi, I'm Cosmic the Cat. ", 250, 250, "Times New Roman", 25, Color.CYAN);
//		catNarration.setFontStyle(2);
		gotohomescreen = new SpriteFont("Click the Space Bar to go to Game Screen", 0, 550, "Times New Roman", 30,
				Color.white);
//		stopwatch.setWaitTime(1000);
//		stopwatch2.setWaitTime(3000);
//		stopwatch3.setWaitTime(5000);
//		stopwatch4.setWaitTime(7000);
//		stopwatch5.setWaitTime(9000);
//		keyLocker.lockKey(Key.SPACE);
	//	new Video(null).show();
	}

	public void update() {
		background.update(null);
//		if (stopwatch.isTimeUp()) {
//			welcome.setColor(Color.BLACK);
//		}
//		if (stopwatch2.isTimeUp()) {
//			catNarration = new SpriteFont("My poor kittens were stolen by a SpaceDog", 200, 250, "Times New Roman", 25,
//					Color.CYAN);
//			catNarration.setFontStyle(2);
//		}
//		if (stopwatch3.isTimeUp()) {
//			catNarration = new SpriteFont("Could you please help me bring them home by fighting the SpaceDog?", 50, 250, "Times New Roman", 25,
//					Color.CYAN);
//			catNarration.setFontStyle(2);
//		}
//		if (stopwatch4.isTimeUp()) {
//			catNarration = new SpriteFont("Please click the Space Bar to bring my kittens home", 150, 250, "Times New Roman", 25,
//					Color.CYAN);
//			catNarration.setFontStyle(2);
//		}
//		if (stopwatch5.isTimeUp()) {
//			catNarration.setColor(Color.BLACK);
//		}
//		if (Keyboard.isKeyUp(Key.SPACE)) {
//			keyLocker.unlockKey(Key.SPACE);
//		}
		// if space is pressed, go back to main menu
		if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
			screenCoordinator.setGameState(GameState.MENU);
		}
	}

	public void draw(GraphicsHandler graphicsHandler) {
		background.draw(graphicsHandler);
		welcome.draw(graphicsHandler);
//		if (welcome.getColor() == Color.BLACK) {
//			catNarration.draw(graphicsHandler);
//		}
		gotohomescreen.draw(graphicsHandler);
	}
}