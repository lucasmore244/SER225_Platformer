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

public class EndLevel2Block extends EnhancedMapTile{
	
	private Stopwatch levelTimer = new Stopwatch();
	
	public EndLevel2Block(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("SpaceTileset.png"), 16, 16), TileType.PASSABLE);
        levelTimer.setWaitTime(300);
        }

    @Override
    public void update(Player player) {
        super.update(player);
        if (levelTimer.isTimeUp()) {
            player.completeLevel();
        }
        
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(3, 4), 500)
                        .withScale(4)
                        .withBounds(1, 1, 25, 17)
                        .build(),
            });
        }};
    }
}
