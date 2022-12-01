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
import Maps.TestMap;
import Screens.PlayLevelScreen;
import Utils.Direction;
import Utils.Point;
public class Coin extends EnhancedMapTile {
	
	private static final String EnhancedMapTiles = null;
	protected int coin = 0;
	private boolean collected = false;
	private EnhancedMapTile collectable;
	private TestMap map;
	
	//Loads the coin into the game
	public Coin(Point location, TestMap testMap) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("CoinSpriteSheet.png"), 80, 80), TileType.PASSABLE);
        this.map = testMap;
    }	
	

	public void update(Player player) {
        super.update(player);
        
        //Says what to do when the player intersects with the coin
        if (intersects(player)&&!collected) {
        	//This will play a sound affect when collected
        	Sound.play(7);
        	//Will add to the coin count when a coin is collected
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
                        .withScale((float) .5)
                        .withBounds(40, 40, 40, 40)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 0), 500)
                        .withScale((float) .5)
                        .withBounds(40, 40, 40, 40)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(2, 0), 500)
                        .withScale((float) .5)
                        .withBounds(40, 40, 40, 40)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(3, 0), 500)
                        .withScale((float) .5)
                        .withBounds(40, 40, 40, 40)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(4, 0), 500)
                        .withScale((float) .5)
                        .withBounds(40, 40, 40, 40)
                        .build(),
            });
        }};
    	}

}