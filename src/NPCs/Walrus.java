package NPCs;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.NPC;
import Level.Player;
import SpriteFont.SpriteFont;
import Utils.Point;
import Utils.Stopwatch;

import java.awt.*;
import java.util.HashMap;

// This class is for the walrus NPC
public class Walrus extends NPC {
	
	private SpriteFont message = new SpriteFont(levelMessage, getX(), getY() - 10, "Arial", 12, Color.BLACK);;
	private static String levelMessage = "he";
	private Stopwatch message1 = new Stopwatch();
	private Stopwatch message2 = new Stopwatch();
	private Stopwatch message3 = new Stopwatch();
	private Stopwatch message4 = new Stopwatch();
	private Stopwatch message5 = new Stopwatch();
	private int currentMap = 1;
	private int i = 0;
	
    public Walrus(Point location, int currentMap) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Walrus.png"), 24, 24), "TAIL_DOWN");
        this.talkedToTime = 22000;
        message1.setWaitTime(0);
        this.currentMap = currentMap;
    }

    public void update(Player player) {
        // while npc is being talked to, it raises its tail up (in excitement?)
        if (talkedTo) {
            currentAnimationName = "TAIL_UP";
            i++;
            if (i == 1) {
            	message2.setWaitTime(3000);
            	message3.setWaitTime(7000);
            	message4.setWaitTime(12000);
            	message5.setWaitTime(17000);
            }
        } else {
            currentAnimationName = "TAIL_DOWN";
            i = 0;
        }
        
        
        if (currentMap == 1) {
        	if (message1.isTimeUp() && !message2.isTimeUp() && !message3.isTimeUp()) {
        		levelMessage = "Hello! My name is Walter the Walrus!!";
        	} else if (message2.isTimeUp() && !message3.isTimeUp()) {
        		levelMessage = "I''ll be telling you the instructions throughout this game";
        	} else if (message3.isTimeUp() && !message4.isTimeUp()){
        		levelMessage = "Now press the arrow keys to move and jump";
        	} else if (message4.isTimeUp() && !message5.isTimeUp()){
        		levelMessage = "And make sure you click m to change them tunes";
        	} else {
        		levelMessage = "OH! And random cat fact: cats hate water so steer clear!";
        	}
        }
        
//        System.out.println(!message2.isTimeUp());
        
        this.message = new SpriteFont(levelMessage, getX(), getY() - 10, "Arial", 12, Color.BLACK);
        
        // set message box relative to walrus's current calibrated location
        message.setLocation(getCalibratedXLocation() + 2, getCalibratedYLocation() - 8);

        super.update(player);
    }
    
    @Override
    protected SpriteFont createMessage() {
    	System.out.println(levelMessage);
    	return new SpriteFont(levelMessage, getX(), getY() - 10, "Arial", 12, Color.BLACK);
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
           put("TAIL_DOWN", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(0, 0))
                           .withScale(3)
                           .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                           .build()
           });
            put("TAIL_UP", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0))
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

    @Override
    public void drawMessage(GraphicsHandler graphicsHandler) {
        // draws a box with a border (think like a speech box)
        graphicsHandler.drawFilledRectangleWithBorder(Math.round(getCalibratedXLocation() - 2), Math.round(getCalibratedYLocation() - 24), 320, 25, Color.WHITE, Color.BLACK, 2);
        // draws message "Hello" in the above speech box
        message.draw(graphicsHandler);
    }
}
