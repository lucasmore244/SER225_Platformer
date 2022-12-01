package EnhancedMapTiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Sound;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Maps.Level3;
import Maps.TestMap;
import Screens.PlayLevelScreen;
import Utils.Direction;
import Utils.Point;

public class SpaceshipTWing extends EnhancedMapTile {
	private static final String EnhancedMapTiles = null;
	protected int parts = 0;
	private boolean collected = false;
	private EnhancedMapTile collectable;
	private TestMap map;
	private Level3 level3;
	
	//Loads the spaceship wing collectible into the game


	public SpaceshipTWing(Point location, Level3 level3) {
		super(location.x, location.y, new SpriteSheet(ImageLoader.load("Wing2.png"), 80, 41), TileType.PASSABLE);
		this.level3 = level3;
	}

	public void update(Player player) {
		super.update(player);
		
		//If the player intersects with the collectible, a sound affect will be played and the coin counter will count up
		if (intersects(player) && !collected) {
			Sound.play(7);
			parts = parts + 1;
			super.update(player);
			super.update(null);
			collected = true;
			level3.setCoinCount(1);
		}
	}

	//If the collectible has not been collected yet, the game will display it
	public void draw(GraphicsHandler graphicsHandler) {
		if (!collected) {
			super.draw(graphicsHandler);
		}
	}


	@Override
	//Adds the collectible into the game
	public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
		return new HashMap<String, Frame[]>() {
			{
				put("DEFAULT", new Frame[] { new FrameBuilder(spriteSheet.getSprite(0, 0), 500).withScale((float) .6)
						.withBounds(40, 40, 40, 40).build(), });
			}
		};
	}
}