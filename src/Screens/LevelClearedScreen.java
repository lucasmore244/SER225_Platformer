package Screens;

import Engine.GraphicsHandler;
import Engine.Screen;
import Engine.ScreenManager;
import Maps.TestMap;
import SpriteFont.SpriteFont;
import Utils.Stopwatch;

import java.awt.*;
import java.util.Map;

// This class is for the level cleared screen
public class LevelClearedScreen extends Screen {
	protected PlayLevelScreen playlevelscreen;
	protected SpriteFont winMessage;
	protected SpriteFont winMessage2, winMessage3;
//	protected PlayLevelScreen playscreen = new PlayLevelScreen(null);
	
	public LevelClearedScreen(PlayLevelScreen playLevelScreen) {
		this.playlevelscreen = playLevelScreen;
		initialize();
	}

	@Override
	public void initialize() {
		winMessage = new SpriteFont("Level " + playlevelscreen.getCurrentMap() + " Cleared", 320, 270, "Comic Sans", 30,
				Color.white);
		winMessage2= new SpriteFont(" ", 0, 0, null, 0, null);
		winMessage3= new SpriteFont(" ", 0, 0, null, 0, null);
	}

	@Override
	public void update() {
		if (playlevelscreen.getCurrentMap() == 4) {
			winMessage = new SpriteFont("GAME WON!", 320, 150, "Times New Roman", 30,
					Color.white);
			winMessage3 = new SpriteFont("Thank You For Saving My Kittens", 250, 200, "Times New Roman", 25,
					Color.ORANGE);
			winMessage3.setFontStyle(3);
			winMessage.setFontStyle(3);
			winMessage2 = new SpriteFont("The Scoreboard Screen would load in a sec", 0, 560, "Times New Roman", 25,
				Color.BLUE);
		}
	}

	public void draw(GraphicsHandler graphicsHandler) {
		// paint entire screen black and dislpay level cleared text
		graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(),
				Color.black);
		winMessage.draw(graphicsHandler);
		winMessage2.draw(graphicsHandler);
		winMessage3.draw(graphicsHandler);
	}
}
