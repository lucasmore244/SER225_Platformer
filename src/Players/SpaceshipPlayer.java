package Players;

import java.util.HashMap;
import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.PlayerLevel3;
import Level.SpaceshipLevel2;
import Engine.Key;
import java.util.Calendar;
import java.util.Date;

import Builders.MapTileBuilder;
import Engine.KeyLocker;
import Engine.Keyboard;
import GameObject.GameObject;
import Players.Cat;
import Utils.AirGroundState;
import Utils.Direction;
import Level.Player;

import java.awt.Color;
import java.util.ArrayList;


public class SpaceshipPlayer extends Player {
	
	private int width;
	private int height;
	private int x;
	private int y;
	
	
	

	public SpaceshipPlayer(float x, float y) {
        super(new SpriteSheet(ImageLoader.load("Rocket1.png"), 42, 35), x, y, "STAND_RIGHT");
        gravity = 0;
        terminalVelocityY = 0;
        jumpHeight = 0;
        jumpDegrade = 0;
        walkSpeed = 0;
        momentumYIncrease = 0;
        playerHealth = 5;
   	}
	public void update() {
		//new SpaceshipLevel2();
        super.update();
    }

    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        // drawBounds(graphicsHandler, new Color(255, 0, 0, 170));
    }
    
    public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean intersects(SpaceshipPlayer other) {
		if(this.getX() < other.getWidth() + other.getX() && this.getX() + this.getWidth() > other.getX() && this.getY() < other.getY() + other.getHeight() && this.getY() + this.getHeight() > other.getY()) {
			return true;
		}
		return false;
		
		
	}

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 500)
                            .withScale(2)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });
        }};
    }
}
 