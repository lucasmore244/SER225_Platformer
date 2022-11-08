package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.LevelState;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class ScoreboardScreen extends Screen {
	protected ScreenCoordinator screenCoordinator;
	protected Map background;
	protected KeyLocker keyLocker = new KeyLocker();
	protected SpriteFont playertimeheader, playertimedisplay, playersnameheader, playersnamedisplay, backtohomescreen;
	protected DisplayTime time = new DisplayTime();
	protected PlayLevelScreen timer = new PlayLevelScreen(null);
	protected String playertime = time.getTime(), playersinput;
	protected LevelLoseScreen levellose;
	protected LevelState levelState;
	protected boolean start;
	protected String name = JOptionPane.showInputDialog(null, "Please Enter your name to record your time");
	protected ArrayList<String> data;
	protected ArrayList<SpriteFont> playerSpriteFonts = new ArrayList<>();

	public ScoreboardScreen(ScreenCoordinator screenCoordinator) {
		this.screenCoordinator = screenCoordinator;
	}

	@Override
	public void initialize() {
		playersnameheader = new SpriteFont("Player Name", 200, 50, "Times New Roman", 20, Color.white);
		playertimeheader = new SpriteFont("Time Used", 450, 50, "Times New Roman", 20, Color.white);
		backtohomescreen = new SpriteFont("Click the Space Bar twice to go back to Home Screen", 0, 550,
				"Times New Roman", 30, Color.BLACK);
		keyLocker.lockKey(Key.SPACE);
		start = true;
		try {
			createTextFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			data = loadTextFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
	}

	public void update() {
		// if(levelstate == LevelState.LEVEL_COMPLETED){
		if (start) {
			for (int i = 0; i < data.size(); i++) {
				String line = data.get(i);
				String[] split = line.split(" ");
				playerSpriteFonts.add(new SpriteFont(split[0], 200, 70 + 20 * i, "Times New Roman", 20, Color.white));
				playerSpriteFonts.add(new SpriteFont(split[1], 450, 70 + 20 * i, "Times New Roman", 20, Color.white));

			}
		}
		if (Keyboard.isKeyUp(Key.SPACE)) {
			keyLocker.unlockKey(Key.SPACE);
		}
		// if space is pressed, go back to main menu
		if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
			screenCoordinator.setGameState(GameState.MENU);
		}
	}

	private void createTextFile() throws IOException {
		ArrayList<String> values = loadTextFile();
		FileWriter fWriter = new FileWriter("scoreboard.txt");
		for (String line : values) {
			fWriter.write(line);
			fWriter.write("\n");
		}
		if (name == null) {
			JOptionPane.getRootFrame().dispose();
		} else {
			fWriter.write(name);
			fWriter.write(" ");
			fWriter.write(playertime);
			fWriter.close();
		}
		}

	private ArrayList<String> loadTextFile() throws IOException {
		ArrayList<String> lines = new ArrayList<String>();
		{
			try {
				BufferedReader file = new BufferedReader(new FileReader("scoreboard.txt"));
				String textfiledoc;
				while ((textfiledoc = file.readLine()) != null) {
					lines.add(textfiledoc);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return lines;
	}

	public void draw(GraphicsHandler graphicsHandler) {
		playersnameheader.draw(graphicsHandler);
		playertimeheader.draw(graphicsHandler);
		backtohomescreen.draw(graphicsHandler);
		for (SpriteFont font : playerSpriteFonts) {
			font.draw(graphicsHandler);
		}

	}
}
