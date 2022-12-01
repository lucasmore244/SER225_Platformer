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

public class SpaceMushroom extends Enemy {
	
	//Loads the space mushroom in the game
	public SpaceMushroom(Point location ) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("spacemushroom.png"), 77, 95), "DEFAULT");
	}
	
	public void update(Player player) {
        super.update(player);
        
        //If the player intersects with the mushroom, the player will get injured
        if (intersects(player)) {
        	super.update(player);
        }
    }

    @Override
    
    //Displays the mushroom on the map
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 500)
                        .withScale((float) .65)
                        .withBounds(20, 20, 40, 40)
                        .build(),
            });
        }};
    }
}






	
	
	
	
