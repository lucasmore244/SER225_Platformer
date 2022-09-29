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

public class Mushrooms extends EnhancedMapTile {
	
	public Mushrooms(Point location ) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Mushroom.png"), 80, 76), TileType.MUSHROOM);
	}
	
	public void update(Player player) {
        super.update(player);
        if (intersects(player)) {
        	super.update(player);
        	int health = player.getPlayerhealth();
        	health -= health;
        	player.setPlayerHealth(health);
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