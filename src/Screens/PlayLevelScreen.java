package Screens;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
	protected Stopwatch endTimer = new Stopwatch();
	protected LevelClearedScreen levelClearedScreen;
	protected LevelLoseScreen levelLoseScreen;
	protected boolean levelCompletedStateChangeStart;
	protected boolean firstGo = true;
	protected HealthDisplay healthdisplay;
	protected TimeDisplay timedisplay;
	protected String livescount;
	protected int lives;
	protected SpriteFont level1;
	protected SpriteFont coins, doglives;
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
		} else if (currentMap == 2) {
			this.player = new SpaceshipLevel2(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
			this.player.setLevelMap(2);
			player.setPlayerHealth(lives);
			System.out.println("ye");
		} else if (currentMap == 3) {
			this.player = new CatLevel3(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
			player.setPlayerHealth(lives);
		} else if (currentMap == 4) {
			this.player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
			this.player.setLevelMap(4);
			player.setPlayerHealth(lives);
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
		// based on screen state, perform specific actions
		switch (playLevelScreenState) {
		// if level is "running" update player and map to keep game logic for the
		// platformer level going
		case RUNNING:
			player.update();
			livescount = "LIVES: " + player.getPlayerhealth();
			if (currentMap != 4) {
				coincount = "COINS: " + map.getCoinCount();
				doglives = new SpriteFont(" ", 0, 0, null, 0, null);
			} else {
				coincount = "KITTENS: " + map.getCoinCount();
				doglives = new HealthDisplay("SPACEDOG LIVES: " + SpaceDog1.getDogStatus(), 450, 70, "Times New Roman", 18,
						Color.RED);
			}
			healthdisplay = new HealthDisplay(livescount, 650, 50, "Comic Sans", 20, Color.RED);
			coins = new SpriteFont(coincount, 650, 70, "Comic Sans", 20, Color.red);
			timedisplay = new TimeDisplay("TIME TAKEN:" + getTime(), 450, 50, "Comic Sans", 20, Color.red);
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
				if (SpaceDog1.getDogStatus() <= 0) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					onLevelCompleted();
					SpaceDog1.setDogStatus(3);
			}
			lives = player.getPlayerhealth();
			break;
		// if level has been completed, bring up level cleared screen
		case LEVEL_COMPLETED:
			if (currentMap <= 4) {
				if (levelCompletedStateChangeStart) {
					screenTimer.setWaitTime(2500);
					if (getCurrentMap() == 4) {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						try {
							createTextFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
						screenCoordinator.setGameState(GameState.SCOREBOARD);
//						System.exit(0);
					}
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
					levelClearedScreen.update();
					if (screenTimer.isTimeUp()) {
						goBackToMenu();
					}
				}
			}
			break;
		// wait on level lose screen to make a decision (either resets level or sends
		// player back to main menu)
		case LEVEL_LOSE:
			levelLoseScreen.update();
			lives = 5;
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
			if (currentMap == 4) {
				doglives.draw(graphicsHandler);
			}
			
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
		if (playLevelScreenState != PlayLevelScreenState.LEVEL_COMPLETED) {
			playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
			levelCompletedStateChangeStart = true;
			playSE(2);
		}
		if (playLevelScreenState == PlayLevelScreenState.LEVEL_COMPLETED && getCurrentMap() == 4) {
			levelClearedScreen.update();
//			playSE(2);
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

	public String getTime() {
		return timer.getTime();
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

	public void createTextFile() throws IOException {
		ArrayList<String> values = new ArrayList<String>();
		String name = JOptionPane.showInputDialog(null, "Please Enter your name to record your time");
		FileWriter fWriter = new FileWriter("time.txt", true);
		if (name == null) {
			JOptionPane.getRootFrame().dispose();
		} else {
			fWriter.write(name);
			fWriter.write(" ");
			fWriter.write(this.getTime());
			fWriter.write("\n");
		}
		fWriter.close();
	}
}
