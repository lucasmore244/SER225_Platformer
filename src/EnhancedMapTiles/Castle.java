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
//this class has been edited in order to display a castle at the end of the level to match the games theme
//The image of the spaceship at the end is an animation but i think thats fine because it works and ive been stuck on it for 4 hours

public class Castle extends EnhancedMapTile {

	public Castle(Point location) {
		super(location.x, location.y, new SpriteSheet(ImageLoader.load("Castle.png"), 87, 83), TileType.PASSABLE);
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
		return new HashMap<String, Frame[]>() {
			{
				put("DEFAULT", new Frame[] { new FrameBuilder(spriteSheet.getSprite(0, 0), 500).withScale(2)
						.withBounds(1, -9500, 87, 10000).build(), });
			}
		};
	}
}



