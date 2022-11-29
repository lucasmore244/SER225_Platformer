package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.LevelState;
import Level.Player;
import Level.TileType;
import Players.SpaceshipLevel2;
import Utils.Point;
import Utils.Stopwatch;
import Maps.Level2;

import java.util.HashMap;

public class EndLevel2Block extends EnhancedMapTile {
	private Stopwatch levelTimer = new Stopwatch();
	protected LevelState levelState;
	protected SpaceshipLevel2 spaceship;

	public EndLevel2Block(Point location) {
		super(location.x, location.y, new SpriteSheet(ImageLoader.load("SpaceTileset.png"), 16, 16), TileType.PASSABLE);
		levelTimer.setWaitTime(50000);
	}

	@Override
	public void update(Player player) {
		super.update(player);
		if (levelTimer.isTimeUp()) {
			player.completeLevel();
		}
	}

	public boolean timeUp() {
		return levelTimer.isTimeUp();
	}

	@Override
	public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
		return new HashMap<String, Frame[]>() {
			{
				put("DEFAULT", new Frame[] { new FrameBuilder(spriteSheet.getSprite(3, 4), 500).withScale(4)
						.withBounds(1, 1, 25, 17).build(), });
			}
		};
	}
}
