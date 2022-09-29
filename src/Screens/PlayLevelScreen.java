package Screens;

import java.awt.Color;

import Engine.GraphicsHandler;
import Engine.Screen;
import EnhancedMapTiles.Checkpoint;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Level.Player;
import Level.PlayerListener;
import Maps.TestMap;
import Players.Cat;
import SpriteFont.HealthDisplay;
import SpriteFont.SpriteFont;
import SpriteFont.TimeDisplay;
import Utils.Point;
import Utils.Stopwatch;

// This class is for when the platformer game is actually being played
public class PlayLevelScreen extends Screen implements PlayerListener {
	protected ScreenCoordinator screenCoordinator;
	protected Map map;
	protected Player player;
	protected PlayLevelScreenState playLevelScreenState;
	protected Stopwatch screenTimer = new Stopwatch();
	protected LevelClearedScreen levelClearedScreen;
	protected LevelLoseScreen levelLoseScreen;
	protected boolean levelCompletedStateChangeStart;
	protected boolean firstGo = true;
	protected HealthDisplay healthdisplay;
	protected TimeDisplay timedisplay;
	protected String running;
	protected SpriteFont level1;
	protected SpriteFont coins;
	

	public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
		this.screenCoordinator = screenCoordinator;
	}

	public void initialize() {
		// define/setup map
		if (firstGo) {
			this.map = new TestMap();
		}

		map.reset();

		// setup player
		this.player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
		this.player.setMap(map);
		this.player.addListener(this);
		Point playerStartPosition = map.getPlayerStartPosition();
		this.player.setLocation(playerStartPosition.x, playerStartPosition.y);
		this.playLevelScreenState = PlayLevelScreenState.RUNNING;

		levelClearedScreen = new LevelClearedScreen();
		levelLoseScreen = new LevelLoseScreen(this);
		level1 = new SpriteFont("LEVEL 1", 50, 50, "Comic Sans", 30, Color.red);
		level1.setOutlineColor(Color.black);
        level1.setOutlineThickness(3);
        coins = new SpriteFont("COINS: ", 650, 70, "Comic Sans", 20, Color.red);
		timedisplay = new TimeDisplay("TIME TAKEN:", 450, 50, "Comic Sans", 20, Color.red);
	}

	public void update() {
		// based on screen state, perform specific actions
		switch (playLevelScreenState) {
		// if level is "running" update player and map to keep game logic for the
		// platformer level going
		case RUNNING:
			player.update();
			running = "LIVES: " + player.getPlayerhealth();
			healthdisplay = new HealthDisplay(running, 650, 50, "Comic Sans", 20, Color.red);
			map.update(player);
			break;
		// if level has been completed, bring up level cleared screen
		case LEVEL_COMPLETED:
			if (levelCompletedStateChangeStart) {
				screenTimer.setWaitTime(2500);
				levelCompletedStateChangeStart = false;
			} else {
				levelClearedScreen.update();
				if (screenTimer.isTimeUp()) {
					goBackToMenu();
				}
			}
			break;
		// wait on level lose screen to make a decision (either resets level or sends
		// player back to main menu)
		case LEVEL_LOSE:
			levelLoseScreen.update();
			break;
		}
	}

	public void draw(GraphicsHandler graphicsHandler) {
		// based on screen state, draw appropriate graphics
		switch (playLevelScreenState) {
		case RUNNING:
			map.draw(graphicsHandler);
			player.draw(graphicsHandler);
			healthdisplay.draw(graphicsHandler);
			timedisplay.draw(graphicsHandler);
			level1.draw(graphicsHandler);
			coins.draw(graphicsHandler);
			break;
		case LEVEL_COMPLETED:
			levelClearedScreen.draw(graphicsHandler);
			break;
		case LEVEL_LOSE:
			levelLoseScreen.draw(graphicsHandler);
			break;
		}
	}

	public PlayLevelScreenState getPlayLevelScreenState() {
		return playLevelScreenState;
	}

	@Override
	public void onLevelCompleted() {
		if (playLevelScreenState != PlayLevelScreenState.LEVEL_COMPLETED) {
			playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
			levelCompletedStateChangeStart = true;
		}
	}

	@Override
	public void onDeath() {
		if (playLevelScreenState != PlayLevelScreenState.LEVEL_LOSE) {
			playLevelScreenState = PlayLevelScreenState.LEVEL_LOSE;
		}
	}

	public void resetLevel() {
		firstGo = false;
		initialize();
	}

	public void goBackToMenu() {
		screenCoordinator.setGameState(GameState.MENU);
	}

	// This enum represents the different states this screen can be in
	private enum PlayLevelScreenState {
		RUNNING, LEVEL_COMPLETED, LEVEL_LOSE
	}
}
