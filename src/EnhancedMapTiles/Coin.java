package EnhancedMapTiles;

import java.util.ArrayList;
import java.util.HashMap;

import Builders.FrameBuilder;
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
	
	private int coin = 0;
	
	public Coin(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Coin.png"), 80, 80), TileType.PASSABLE);
    }	
	

	public void update(Player player) {
        super.update(player);
        if (intersects(player)) {
        	coin = coin + 1;
            System.out.println("Picked up " + coin + " coins");
            

        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 500)
                        .withScale((float) .5)
                        .withBounds(0, 80, 0, 80)
                        .build(),
            });
        }};
    }
}