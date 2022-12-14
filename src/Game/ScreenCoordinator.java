package Game;

import Engine.DefaultScreen;
import Engine.GraphicsHandler;
import Engine.Screen;
import Screens.BeginningScreen;
import Screens.CreditsScreen;
import Screens.InstructionScreen;
import Screens.MenuScreen;
import Screens.PlayLevelScreen;
import Screens.ScoreboardScreen;

/*
 * Based on the current game state, this class determines which Screen should be shown
 * There can only be one "currentScreen", although screens can have "nested" screens
 */
public class ScreenCoordinator extends Screen {
	// currently shown Screen
	protected Screen currentScreen = new DefaultScreen();
	// keep track of gameState so ScreenCoordinator knows which Screen to show
	protected GameState gameState;
	protected GameState previousGameState;
	protected String gameTime;

	public GameState getGameState() {
		return gameState;
	}

	// Other Screens can set the gameState of this class to force it to change the
	// currentScreen
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	@Override
	public void initialize() {
		// start game off with Menu Screen
		gameState = GameState.BEGINNINGSCREEN;
	}

	@Override
	public void update() {
		do {
			// if previousGameState does not equal gameState, it means there was a change in
			// gameState
			// this triggers ScreenCoordinator to bring up a new Screen based on what the
			// gameState is
			if (previousGameState != gameState) {
				switch (gameState) {
				case BEGINNINGSCREEN:
					currentScreen = new BeginningScreen(this);
					break;
				case MENU:
					currentScreen = new MenuScreen(this);
					break;
				case LEVEL:
					currentScreen = new PlayLevelScreen(this);
					break;
				case CREDITS:
					currentScreen = new CreditsScreen(this);
					break;
				case SCOREBOARD:
					currentScreen = new ScoreboardScreen(this);
					break;
				case INSTRUCTION:
					currentScreen = new InstructionScreen(this);
				}
				currentScreen.initialize();
			}
			previousGameState = gameState;
			// call the update method for the currentScreen
			currentScreen.update();
		} while (previousGameState != gameState);
	}

	@Override
	public void draw(GraphicsHandler graphicsHandler) {
		// call the draw method for the currentScreen
		currentScreen.draw(graphicsHandler);
	}
}
