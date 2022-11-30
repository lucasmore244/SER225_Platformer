package EnhancedMapTiles;

import java.util.ArrayList;
import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Utils.Direction;
import Utils.Point;

public class Mushrooms extends Enemy {
	
	public Mushrooms(Point location ) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Mushroom1.png"), 50, 88), "DEFAULT");
	}
	
	public void update(Player player) {
        super.update(player);
        if (intersects(player)) {
        	super.update(player);
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 500)
                        .withScale((float) .58)
                        .withBounds(2, 0, 48, 80)
                        .build(),
            });
        }};
    }
}