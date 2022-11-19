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
	private int boxLength = 0;
	private int version = 1;
	private int i = 0;
	
    public Walrus(Point location, int version) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Walrus.png"), 24, 24), "TAIL_DOWN");
        if (version == 1) {
        	boxLength = 320;
        	this.talkedToTime = 22000;
        } else if (version == 2){
        	boxLength = 340;
        	this.talkedToTime = 5000;
        }
        message1.setWaitTime(0);
        this.version = version;
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
            	message5.setWaitTime(15000);
            }
        } else {
            currentAnimationName = "TAIL_DOWN";
            i = 0;
        }
        
        
        if (version == 1) {
        	if (message1.isTimeUp() && !message2.isTimeUp() && !message3.isTimeUp()) {
        		levelMessage = "Hello! My name is Walter the Walrus!!";
        	} else if (message2.isTimeUp() && !message3.isTimeUp()) {
        		levelMessage = "I''ll be telling you the instructions throughout this game";
        	} else if (message3.isTimeUp() && !message4.isTimeUp()){
        		levelMessage = "Now press the arrow keys to move and jump";
        	} else if (message4.isTimeUp() && !message5.isTimeUp()){
        		levelMessage = "And make sure you click m to change them tunes";
        		System.out.println(version);
        	} 
        } else if (version == 2) {
        	if (message1.isTimeUp() && !message2.isTimeUp() && !message3.isTimeUp()) {
        		levelMessage = "Woah there watch out!! If you haven't realized out already... ";
        	} else if (message2.isTimeUp() && !message3.isTimeUp()) {
        		boxLength = 130;
        		levelMessage = "CATS HATE WATER!!";
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
        graphicsHandler.drawFilledRectangleWithBorder(Math.round(getCalibratedXLocation() - 2), Math.round(getCalibratedYLocation() - 24), boxLength, 25, Color.WHITE, Color.BLACK, 2);
        // draws message "Hello" in the above speech box
        message.draw(graphicsHandler);
    }
}
