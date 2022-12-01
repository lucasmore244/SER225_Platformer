package EnhancedMapTiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Sound;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Maps.Level4;
import Maps.TestMap;
import Screens.PlayLevelScreen;
import Utils.Direction;
import Utils.Point;

public class Kitten4 extends EnhancedMapTile {
	private static final String EnhancedMapTiles = null;
	protected int coin = 0;
	private boolean collected = false;
	private EnhancedMapTile collectable;
	private Level4 map;
	protected PlayLevelScreen playscreen = new PlayLevelScreen(null);
	
	//Loads the fourth kitten into the game
	public Kitten4(Point location, Level4 level4) {
		super(location.x, location.y + 7, new SpriteSheet(ImageLoader.load("Kitten4.png"), 80, 66), TileType.PASSABLE);
		this.map = level4;
	}

	public void update(Player player) {
        super.update(player);
        
        //Says what to do when the player intersects with the kitten
        if (intersects(player)&&!collected) {
        	
        	//This will play a sound affect when collected
        	Sound.play(7);
        	
        	//Will add to the coin count when a kitten is collected
        	coin = coin + 1;
        	super.update(player);
        	super.update(null);
            collected = true;
            map.setCoinCount(1);
        }
        
    }
	
	//This tells the map to display the coin if it has not been collected yet
	 public void draw(GraphicsHandler graphicsHandler) {
		 if(!collected) {
	        super.draw(graphicsHandler);
	    }
	 }

	

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
      
			//These are the animations for the coin to spin
    		return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 500)
                        .withScale((float) .6)
                        .withBounds(20, 20, 40, 40)
                        .build()
            });
        }};
    	}
}

