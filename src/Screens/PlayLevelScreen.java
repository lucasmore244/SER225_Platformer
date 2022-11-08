package Screens;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import Enemies.Asteriods;
import Enemies.Fireball;
import Engine.DisplayTime;
import Engine.GameWindow;
import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Screen;
import Engine.Sound;
import EnhancedMapTiles.Checkpoint;
import EnhancedMapTiles.Coin;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Level.Player;
import Level.PlayerLevel3;
import Enemies.SpaceDog1;

import Level.PlayerListener;

import Level.PlayerState;
import MapEditor.MusicPanel;
import Maps.Level2;

import Maps.Level3;
import Maps.Level4;
import Maps.TestMap;
import Players.Cat;
import Players.CatLevel3;
import Players.SpaceshipLevel2;
import SpriteFont.HealthDisplay;
import SpriteFont.SpriteFont;
import SpriteFont.TimeDisplay;
import Utils.Direction;
import Utils.Point;
import Utils.Stopwatch;

// This class is for when the platformer game is actually being played
public class PlayLevelScreen extends Screen implements PlayerListener {
	protected ScreenCoordinator screenCoordinator;
	protected Map map, selectedMap;
	protected Player player;
	protected PlayLevelScreenState playLevelScreenState;
	protected Stopwatch screenTimer = new Stopwatch();
	protected Stopwatch scoreboardtime = new Stopwatch();
	protected LevelClearedScreen levelClearedScreen;
	protected LevelLoseScreen levelLoseScreen;
	protected boolean levelCompletedStateChangeStart;
	protected boolean firstGo = true;
	protected HealthDisplay healthdisplay;
	protected TimeDisplay timedisplay;
	protected String livescount;
	protected SpriteFont level1;
	protected SpriteFont coins;
	protected String coincount;
	public DisplayTime timer = new DisplayTime();
	protected int currentMap = 1;



	protected Key SHOOT_KEY = Key.Q;
	protected Sound sound = new Sound();
	protected MusicPanel musicPanel;
	protected Key MUSIC_KEY = Key.M;
	protected KeyLocker keylock = new KeyLocker();

	public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
		this.screenCoordinator = screenCoordinator;
	}

	public void initialize() {
		// define/setup map
		
		
//		stopMusic();
		if (firstGo) {
			if (currentMap == 1) {
				this.map = new TestMap();
				playMusic(6);
			} else if (currentMap == 2) {
				this.map = new Level2();
			} else if (currentMap == 3) {
				this.map = new Level3();
			} else if (currentMap == 4) {
				this.map = new Level4();
			}
		}
		map.reset();
		// setup player
		if (currentMap == 1) {
			this.player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
		} else if (currentMap == 3) {
			this.player = new CatLevel3(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
		} else if (currentMap == 2) {
			this.player = new SpaceshipLevel2(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
			this.player.setLevelMap(2);
		} else if (currentMap == 4) {
			this.player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
			this.player.setLevelMap(4);
		}
		this.player.setMap(map);
		this.player.addListener(this);
		Point playerStartPosition = map.getPlayerStartPosition();
		this.player.setLocation(playerStartPosition.x, playerStartPosition.y);
		this.playLevelScreenState = PlayLevelScreenState.RUNNING;
		levelClearedScreen = new LevelClearedScreen(this);
		levelLoseScreen = new LevelLoseScreen(this);
		level1 = new SpriteFont("LEVEL " + currentMap, 50, 50, "Comic Sans", 30, Color.red);
		level1.setOutlineColor(Color.black);
		level1.setOutlineThickness(3);
	}

	public void update() {
		System.out.println(SpaceDog1.getDogStatus());
//		if (SpaceDog1.getDogStatus() <= 0) {
//			playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
//		}
		// based on screen state, perform specific actions
		switch (playLevelScreenState) {
		// if level is "running" update player and map to keep game logic for the
		// platformer level going
		case RUNNING:
			player.update();
			livescount = "LIVES: " + player.getPlayerhealth();
			coincount = "COINS: " + map.getCoinCount();
			healthdisplay = new HealthDisplay(livescount, 650, 50, "Comic Sans", 20, Color.red);
			coins = new SpriteFont(coincount, 650, 70, "Comic Sans", 20, Color.red);
			timedisplay = new TimeDisplay("TIME TAKEN:" + timer.getTime(), 450, 50, "Comic Sans", 20, Color.red);
			map.update(player);
			if (map.getCoinCount() >= 3 && player.getPlayerhealth() < 5) {
				player.setPlayerHealth(player.getPlayerhealth() + 1);
				map.setCoinCount(-3);
			}
			if (!keylock.isKeyLocked(MUSIC_KEY) && Keyboard.isKeyDown(MUSIC_KEY)) {
				stopMusic();
				new MusicPanel(null).show();
				keylock.lockKey(MUSIC_KEY);
			}
			if (Keyboard.isKeyUp(MUSIC_KEY)) {
				keylock.unlockKey(MUSIC_KEY);
			}
			break;
		// if level has been completed, bring up level cleared screen
		case LEVEL_COMPLETED:
			if (levelCompletedStateChangeStart) {
				screenTimer.setWaitTime(2500);
				scoreboardtime.setWaitTime(5000);
				levelCompletedStateChangeStart = false;
				currentMap += 1;
				firstGo = true;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				initialize();
			} else {
//				levelClearedScreen.update();
				if (screenTimer.isTimeUp()) {
					goBackToMenu();
				}
			}
			if (scoreboardtime.isTimeUp() && getCurrentMap() == 4) {
				screenCoordinator.setGameState(GameState.SCOREBOARD);
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

	public Map getSelectedMap() {
		return selectedMap;
	}

	@Override
	public void onLevelCompleted() {
		scoreboardtime.setWaitTime(100);
		if (playLevelScreenState != PlayLevelScreenState.LEVEL_COMPLETED) {
			playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
			levelCompletedStateChangeStart = true;
			playSE(2);
		}
		if (playLevelScreenState == PlayLevelScreenState.LEVEL_COMPLETED && getCurrentMap() == 4) {
			levelClearedScreen.update();
		}
	}

	@Override
	public void onDeath() {
		if (playLevelScreenState != PlayLevelScreenState.LEVEL_LOSE) {
			playLevelScreenState = PlayLevelScreenState.LEVEL_LOSE;
			playSE(3);
		}
	}

	public void resetLevel() {
		firstGo = false;
		initialize();
	}

	public void goBackToMenu() {
		screenCoordinator.setGameState(GameState.MENU);
	}

	public int getCurrentMap() {
		return currentMap;
	}

	// This enum represents the different states this screen can be in
	private enum PlayLevelScreenState {
		RUNNING, LEVEL_COMPLETED, LEVEL_LOSE
	}

	public void CatLevel() {
		if (currentMap == 1) {
			this.player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
		} else if (currentMap == 2) {
			this.player = new SpaceshipLevel2(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
		} else if (currentMap == 3) {
			this.player = new CatLevel3(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
		}
	}

	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();
	}

	public void stopMusic() {
		sound.stop();
	}

	public void playSE(int i) {
		sound.setFile(i);
		sound.play();
	}
}
