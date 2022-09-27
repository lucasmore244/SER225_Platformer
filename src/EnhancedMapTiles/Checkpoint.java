package EnhancedMapTiles;

import java.awt.Color;
import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Map;
import Level.Player;
import Level.TileType;
import Maps.TestMap;
import SpriteFont.SpriteFont;
import Utils.Point;


public class Checkpoint  extends EnhancedMapTile{
	public SpriteFont checkpoint;
	
	private Map map;
	public Checkpoint(Point location, Map map) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("GoldBox.png"), 16, 16), TileType.PASSABLE);
        this.map = map;
//        System.out.println("Checkpoint reached");
       
	}
	
	public void update(Player player) {
        super.update(player);
        if (intersects(player)) {
		map.setPlayerStartPosition(new Point(player.getX(),player.getY()));;
//		 this.checkpoint = new SpriteFont("Checkpoint Reached", 400, 50, "Comic Sans", 20, Color.red);
        }  
    }
	
	public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 500)
                        .withScale(3)
                        .withBounds(1, 1, 14, 14)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 500)
                        .withScale(3)
                        .withBounds(1, 1, 14, 14)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 500)
                        .withScale(3)
                        .withBounds(1, 1, 14, 14)
                        .build()
            });
        }};
	}
}
