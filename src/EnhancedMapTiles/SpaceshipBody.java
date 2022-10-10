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
import Utils.Direction;
import Utils.Point;
public class SpaceshipBody extends EnhancedMapTile {
	
	private static final String EnhancedMapTiles = null;
	protected int parts = 0;
	private boolean collected = false;
	private EnhancedMapTile collectable;
	private TestMap map;
	private Level3 level3;
	
	public SpaceshipBody(Point location, Level3 level3) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Body.png"), 80, 45), TileType.PASSABLE);

        this.level3 = level3;
    }	


	public void update(Player player) {
        super.update(player);
        if (intersects(player)&&!collected) {
        	parts = parts + 1;
        	super.update(player);
        	super.update(null);
            collected = true;
            level3.setCoinCount(1);
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
                        .withScale((float) .6)
                        .withBounds(40, 40, 40, 40)
                        .build(),
            });
        }};
    	}

}