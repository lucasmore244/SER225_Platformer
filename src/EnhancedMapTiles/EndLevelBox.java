package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Utils.Point;

import java.util.HashMap;

// this class has been edited in order to display a spaceship at the end of the level to match the games theme
// The image of the spaceship at the end is an animation but i think thats fine because it works and ive been stuck on it for 4 hours

// This class is for the end level gold box tile
// when the player touches it, it will tell the player that the level has been completed
public class EndLevelBox extends EnhancedMapTile {
    public EndLevelBox(Point location) {
        super(location.x-100, location.y+70, new SpriteSheet(ImageLoader.load("Rocket.png"), 50, 35), TileType.PASSABLE);
        
    }

    @Override
    public void update(Player player) {
        super.update(player);
        if (intersects(player)) {
            player.completeLevel();
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 500)
                        .withScale(4)
                        .withBounds(1, 1, 14, 14)
                        .build(),
            });
        }};
    }
}
