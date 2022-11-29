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
public class WalrusLevel2 extends NPC {
	
	private SpriteFont message = new SpriteFont(levelMessage, getX(), getY() - 10, "Arial", 12, Color.BLACK);;
	private static String levelMessage = "he";
	private Stopwatch existanceTime = new Stopwatch();
	private Stopwatch message1 = new Stopwatch();
	private Stopwatch message2 = new Stopwatch();
	private Stopwatch message3 = new Stopwatch();
	private Stopwatch message4 = new Stopwatch();
	private Stopwatch message5 = new Stopwatch();
	private int boxLength = 0;
	private boolean firstGo = true;
	
	private int i = 0;
	
    public WalrusLevel2(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Walrus.png"), 24, 24), "TAIL_DOWN");
        boxLength = 320;
        this.talkedToTime = 20000;
        existanceTime.setWaitTime(20000);
        message1.setWaitTime(0);
        talkedTo = true;
        timer.setWaitTime(talkedToTime);
    }

    public void update(Player player) {
        // while npc is being talked to, it raises its tail up (in excitement?)
    	if (existanceTime.isTimeUp()) {
    		currentAnimationName = "DISSAPEAR";
    	}else {
    		currentAnimationName = "TAIL_UP";
    		if (firstGo == true) {
            	message2.setWaitTime(3000);
            	message3.setWaitTime(7000);
            	message4.setWaitTime(12000);
            	message5.setWaitTime(17000);
            	firstGo = false;
            }
    	}
             
        	if (message1.isTimeUp() && !message2.isTimeUp() && !message3.isTimeUp()) {
        		boxLength = 180;
        		levelMessage = "Hello Cosmic! It's me again!!";
        	} else if (message2.isTimeUp() && !message3.isTimeUp()) {
        		boxLength = 320;
        		levelMessage = "I just have a few rules for you as you fly through space!!";
        	} else if (message3.isTimeUp() && !message4.isTimeUp()){
        		boxLength = 450;
        		levelMessage = "Now there's an astroid field coming up in just a few seconds so...";
        	} else if (message4.isTimeUp() && !message5.isTimeUp()){
        		levelMessage = "Press the up and down arrows to move and hit Q on your keyboard to fire!";
        	}  else if (message5.isTimeUp()) {
        		levelMessage = "Survive for 30s and you'll reach the MOON!! Good luck Cosmic!!";
        	}
        
        this.message = new SpriteFont(levelMessage, getX(), getY() - 10, "Arial", 12, Color.BLACK);
        
        // set message box relative to walrus's current calibrated location
        message.setLocation(getCalibratedXLocation() + 2, getCalibratedYLocation() - 8);

        super.update(player);
    }
    
    @Override
    protected SpriteFont createMessage() {
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
            put("DISSAPEAR", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0))
                            .withScale(0)
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
        graphicsHandler.drawFilledRectangleWithBorder(Math.round(getCalibratedXLocation() - 2), Math.round(getCalibratedYLocation() - 24), boxLength, 25, Color.WHITE, Color.BLACK, 2);
        // draws message "Hello" in the above speech box
        message.draw(graphicsHandler);
    }
}
