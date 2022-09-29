package EnhancedMapTiles;

import java.util.ArrayList;
import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Utils.Direction;
import Utils.Point;
public class Coin extends EnhancedMapTile {
	
	private static final String EnhancedMapTiles = null;
	private int coin = 0;
	private boolean collected = false;
	private EnhancedMapTile collectable;
	
	public Coin(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Coin.png"), 80, 80), TileType.PASSABLE);
    }	
	

	public void update(Player player) {
        super.update(player);
        if (intersects(player)&&!collected) {
        	coin = coin + 1;
        	super.update(player);
        	super.update(null);
            System.out.println("Picked up " + coin + " coins");
            collected = true;
        }
        
    }
	

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
       
    	if(!collected) {
    		return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 500)
                        .withScale((float) .5)
                        .withBounds(1, 1, 40, 40)
                        .build(),
            });
        }};
    	}
    	else {        
    		return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 500)
                            .withScale((float) .5)
                            .withBounds(1, 1, 40, 40)
                            .build(),
                });
            }};
    	}
    }
}