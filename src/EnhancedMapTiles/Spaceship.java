package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Utils.Point;
import Utils.Stopwatch;

import java.util.HashMap;

// this class has been edited in order to display a spaceship at the end of the level to match the games theme
// The image of the spaceship at the end is an animation but i think thats fine because it works and ive been stuck on it for 4 hours


public class Spaceship extends EnhancedMapTile {
	
	//Loads the spaceship into the game
    public Spaceship(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Rocket.png"), 50, 35), TileType.PASSABLE);
        }

    @Override
    public void update(Player player) {
        super.update(player);
        
        //When the player intersects with the spaceship in the first level, the level is complete and it will move onto the next level
        if (intersects(player)) {
            player.completeLevel();
        }
        
    }

    //Displays the spaceship in the game
    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 500)
                        .withScale(4)
                        .withBounds(10, -9500, 25, 10000)
                        .build(),
            });
        }};
    }
}
