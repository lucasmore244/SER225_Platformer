package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;
import Utils.Stopwatch;

import java.awt.*;

// This is the class for the main menu screen
public class MenuScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected int currentMenuItemHovered = 0; // current menu item being "hovered" over
    protected int menuItemSelected = -1;
    protected SpriteFont playGame;
    protected SpriteFont credits;
    protected SpriteFont scoreboard, instruction;
    protected Map background;
    protected Stopwatch keyTimer = new Stopwatch();
    protected int pointerLocationX, pointerLocationY;
    protected KeyLocker keyLocker = new KeyLocker();

    public MenuScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        playGame = new SpriteFont("PLAY GAME", 200, 50, "Comic Sans", 30, new Color(49, 207, 240));
        playGame.setOutlineColor(Color.black);
        playGame.setOutlineThickness(3);
        credits = new SpriteFont("CREDITS", 200, 150, "Comic Sans", 30, new Color(49, 207, 240));
        credits.setOutlineColor(Color.black);
        credits.setOutlineThickness(3);
        scoreboard = new SpriteFont("SCOREBOARD", 200, 250, "Comic Sans", 30, new Color(49, 207, 240));
        scoreboard.setOutlineColor(Color.black);
        scoreboard.setOutlineThickness(3);
        instruction = new SpriteFont("INSTRUCTIONS", 200, 350, "Comic Sans", 30, new Color(49, 207, 240));
        instruction.setOutlineColor(Color.black);
        instruction.setOutlineThickness(3);
        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        keyTimer.setWaitTime(200);
        menuItemSelected = -1;
        keyLocker.lockKey(Key.SPACE);
    }

    public void update() {
        // update background map (to play tile animations)
        background.update(null);

        // if down or up is pressed, change menu item "hovered" over (blue square in front of text will move along with currentMenuItemHovered changing)
        if (Keyboard.isKeyDown(Key.DOWN) && keyTimer.isTimeUp()) {
            keyTimer.reset();
            currentMenuItemHovered++;
        } else if (Keyboard.isKeyDown(Key.UP) && keyTimer.isTimeUp()) {
            keyTimer.reset();
            currentMenuItemHovered--;
       } 
        
        // if down is pressed on last menu item or up is pressed on first menu item, "loop" the selection back around to the beginning/end
        if (currentMenuItemHovered > 3) {
            currentMenuItemHovered = 0;
        } else if (currentMenuItemHovered < 0) {
            currentMenuItemHovered = 3;
        } 


        // sets location for blue square in front of text (pointerLocation) and also sets color of spritefont text based on which menu item is being hovered
        if (currentMenuItemHovered == 0) {
            playGame.setColor(new Color(255, 215, 0));
            credits.setColor(new Color(49, 207, 240));
            scoreboard.setColor(Color.RED);
            instruction.setColor(Color.GREEN);
            pointerLocationX = 170;
            pointerLocationY = 30;
        } else if (currentMenuItemHovered == 1) {
            playGame.setColor(Color.GREEN);
            credits.setColor(new Color(255,215,0));
            scoreboard.setColor(new Color(49, 207, 240));
            instruction.setColor(Color.RED);
            pointerLocationX = 170;
            pointerLocationY = 130;
        } 
        else if(currentMenuItemHovered == 2) {
        	playGame.setColor(Color.RED);
            credits.setColor(Color.GREEN);
            scoreboard.setColor(new Color(255,215,0));
            instruction.setColor(new Color(49, 207, 240));
            pointerLocationX = 170;
            pointerLocationY = 230;
        }
        else if(currentMenuItemHovered == 3) {
        	playGame.setColor(new Color(49, 207, 240));
            credits.setColor(Color.RED);
            scoreboard.setColor(Color.GREEN);
            instruction.setColor(new Color(255,215,0));
            pointerLocationX = 170;
            pointerLocationY = 330;
        }
        

        // if space is pressed on menu item, change to appropriate screen based on which menu item was chosen
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
            menuItemSelected = currentMenuItemHovered;
            if (menuItemSelected == 0) {
                screenCoordinator.setGameState(GameState.LEVEL);
            } else if (menuItemSelected == 1) {
                screenCoordinator.setGameState(GameState.CREDITS);
            } else if (menuItemSelected == 2){
            	screenCoordinator.setGameState(GameState.SCOREBOARD);
            }
            else{
            	screenCoordinator.setGameState(GameState.INSTRUCTION);
            }
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        playGame.draw(graphicsHandler);
        credits.draw(graphicsHandler);
        scoreboard.draw(graphicsHandler);
        instruction.draw(graphicsHandler);
        graphicsHandler.drawFilledRectangleWithBorder(pointerLocationX, pointerLocationY, 20, 20, new Color(49, 207, 240), Color.black, 2);
    }

    public int getMenuItemSelected() {
        return menuItemSelected;
    }
}
