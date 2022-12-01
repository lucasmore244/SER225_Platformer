package EnhancedMapTiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Maps.Level3;
import Maps.TestMap;
import Screens.PlayLevelScreen;
import Utils.Direction;
import Utils.Point;
public class SpaceshipParts extends EnhancedMapTile {
	
	private static final String EnhancedMapTiles = null;
	protected int parts = 0;
	private boolean collected = false;
	private EnhancedMapTile collectable;
	private TestMap map;
	private Level3 level3;
	protected PlayLevelScreen playscreen = new PlayLevelScreen(null);
	
	//Loads the spaceship head collectible into the game
	public SpaceshipParts(Point location, Level3 level3) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Head.png"), 80, 80), TileType.PASSABLE);

        this.level3 = level3;
    }	


	public void update(Player player) {
        super.update(player);
        
        //If the collectible is collected, a sound affect will play, and the coin counter will count up
        if (intersects(player)&&!collected) {
        	playscreen.playSE(7);
        	parts = parts + 1;
        	super.update(player);
        	super.update(null);
            collected = true;
            level3.setCoinCount(1);
        }
        
    }
	
	 public void draw(GraphicsHandler graphicsHandler) {
		 //Tells the game to draw the collectible when the collectible is not collected
		 if(!collected) {
	        super.draw(graphicsHandler);
	    }
	 }

	

    @Override
    //Draws the collectible into the game
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
      
    		return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 500)
                        .withScale((float) .5)
                        .withBounds(40, 40, 40, 40)
                        .build(),
            });
        }};
    	}

}