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
import Maps.Level4;
import Maps.TestMap;
import Screens.PlayLevelScreen;
import Utils.Direction;
import Utils.Point;
public class Kitten1 extends EnhancedMapTile {
	
	private static final String EnhancedMapTiles = null;
	protected int coin = 0;
	private boolean collected = false;
	private EnhancedMapTile collectable;
	private Level4 map;
	protected PlayLevelScreen playscreen = new PlayLevelScreen(null);
	
	public Kitten1(Point location, Level4 level4) {
        super(location.x, location.y + 2, new SpriteSheet(ImageLoader.load("Kitten1.png"), 80, 66), TileType.PASSABLE);
        this.map = level4;
    }	
	

	public void update(Player player) {
        super.update(player);
        if (intersects(player)&&!collected) {
        	playscreen.playSE(7);
        	coin = coin + 1;
        	super.update(player);
        	super.update(null);
            collected = true;
            map.setCoinCount(1);
        }
        
    }
	
	 public void draw(GraphicsHandler graphicsHandler) {
		 if(!collected) {
	        super.draw(graphicsHandler);
	    }
	 }

	

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
      
    		return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 500)
                        .withScale((float) .7)
                        .withBounds(20, 20, 40, 40)
                        .build()
            });
        }};
    	}

}