package Maps;

import java.util.ArrayList;

import Enemies.DinosaurEnemy;
import Enemies.SpaceDog;
import Enemies.SpaceDog1;
import Engine.ImageLoader;
import EnhancedMapTiles.Coin;
import EnhancedMapTiles.HorizontalMovingPlatform;
import EnhancedMapTiles.Kitten1;
import EnhancedMapTiles.Kitten2;
import EnhancedMapTiles.Kitten3;
import EnhancedMapTiles.Kitten4;
import GameObject.Rectangle;
import Level.Enemy;
import Level.EnhancedMapTile;
import Level.Map;
import Level.MapEntityStatus;
import Level.NPC;
import Level.Player;
import Level.TileType;
import Screens.PlayLevelScreen;
import Tilesets.CommonTileset;
import Tilesets.SpaceTileset;
import Tilesets.SpaceTileset2;
import Tilesets.SpaceTileset3;
import Utils.Direction;
import Utils.Point;

public class Level4 extends Map {
	private int coinCount = 0;
	protected Map map;
	protected ArrayList<EnhancedMapTile> enhancedMapTiles;
	protected Boolean isRemoved = false;
	
	public Level4() {
        super("level4.txt", new SpaceTileset3());
        this.playerStartPosition = getMapTile(9, 30).getLocation(); 
    }
   
    
	@Override
	public void update(Player player) {
		super.update(player);
		if (getCoinCount() == 4 && !enhancedMapTiles.isEmpty()) {
			if (!isRemoved) {
				enhancedMapTiles.remove(9);
				enhancedMapTiles.remove(9);
				enhancedMapTiles.remove(9);
				isRemoved = true;
			}
		}
	}

	@Override
	public ArrayList<Enemy> loadEnemies() {
		ArrayList<Enemy> enemies = new ArrayList<>();
		enemies.add(new SpaceDog1(getMapTile(7, 6).getLocation(), getMapTile(13, 6).getLocation(), Direction.RIGHT));
		return enemies;
	}

	@Override
	public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
		enhancedMapTiles = new ArrayList<>();
		enhancedMapTiles.add(new HorizontalMovingPlatform(ImageLoader.load("GreyPlatform.png"),
				getMapTile(3, 29).getLocation(), getMapTile(7, 29).getLocation(), TileType.JUMP_THROUGH_PLATFORM, 3,
				new Rectangle(0, 6, 16, 4), Direction.RIGHT));
		enhancedMapTiles.add(new HorizontalMovingPlatform(ImageLoader.load("GreyPlatform.png"),
				getMapTile(10, 30).getLocation(), getMapTile(13, 30).getLocation(), TileType.JUMP_THROUGH_PLATFORM, 3,
				new Rectangle(0, 6, 16, 4), Direction.RIGHT));
		enhancedMapTiles.add(new HorizontalMovingPlatform(ImageLoader.load("GreyPlatform.png"),
				getMapTile(9, 27).getLocation(), getMapTile(13, 27).getLocation(), TileType.JUMP_THROUGH_PLATFORM, 3,
				new Rectangle(0, 6, 16, 4), Direction.RIGHT));
		enhancedMapTiles.add(new HorizontalMovingPlatform(ImageLoader.load("GreyPlatform.png"),
				getMapTile(3, 24).getLocation(), getMapTile(12, 24).getLocation(), TileType.JUMP_THROUGH_PLATFORM, 3,
				new Rectangle(0, 6, 16, 4), Direction.RIGHT));
		enhancedMapTiles.add(new HorizontalMovingPlatform(ImageLoader.load("GreyPlatform.png"),
				getMapTile(9, 19).getLocation(), getMapTile(15, 19).getLocation(), TileType.JUMP_THROUGH_PLATFORM, 3,
				new Rectangle(0, 6, 16, 4), Direction.RIGHT));
		enhancedMapTiles.add(new HorizontalMovingPlatform(ImageLoader.load("GreyPlatform.png"),
				getMapTile(4, 19).getLocation(), getMapTile(5, 19).getLocation(), TileType.JUMP_THROUGH_PLATFORM, 3,
				new Rectangle(0, 6, 16, 4), Direction.RIGHT));
		enhancedMapTiles.add(new HorizontalMovingPlatform(ImageLoader.load("GreyPlatform.png"),
				getMapTile(9, 15).getLocation(), getMapTile(14, 15).getLocation(), TileType.JUMP_THROUGH_PLATFORM, 3,
				new Rectangle(0, 6, 16, 4), Direction.RIGHT));
		enhancedMapTiles.add(new HorizontalMovingPlatform(ImageLoader.load("GreyPlatform.png"),
				getMapTile(3, 12).getLocation(), getMapTile(14, 12).getLocation(), TileType.JUMP_THROUGH_PLATFORM, 3,
				new Rectangle(0, 6, 16, 4), Direction.RIGHT));
		enhancedMapTiles.add(new HorizontalMovingPlatform(ImageLoader.load("GreyPlatform.png"),
				getMapTile(15, 10).getLocation(), getMapTile(17, 10).getLocation(), TileType.JUMP_THROUGH_PLATFORM, 3,
				new Rectangle(0, 6, 16, 4), Direction.RIGHT));
		enhancedMapTiles.add(new HorizontalMovingPlatform(ImageLoader.load("GreyPlatform.png"),
				getMapTile(17, 7).getLocation(), getMapTile(18, 7).getLocation(), TileType.NOT_PASSABLE, 3,
				new Rectangle(0, 6, 16, 4), Direction.RIGHT));
		enhancedMapTiles.add(new HorizontalMovingPlatform(ImageLoader.load("GreyPlatform.png"),
				getMapTile(2, 7).getLocation(), getMapTile(3, 7).getLocation(), TileType.NOT_PASSABLE, 3,
				new Rectangle(0, 6, 16, 4), Direction.RIGHT));
		enhancedMapTiles.add(new HorizontalMovingPlatform(ImageLoader.load("GreyPlatform.png"),
				getMapTile(3, 7).getLocation(), getMapTile(4, 7).getLocation(), TileType.NOT_PASSABLE, 3,
				new Rectangle(0, 6, 16, 4), Direction.RIGHT));
		enhancedMapTiles.add(new Kitten1(getMapTile(2, 20).getLocation(), this));
		enhancedMapTiles.add(new Kitten2(getMapTile(7, 16).getLocation(), this));
		enhancedMapTiles.add(new Kitten3(getMapTile(15, 26).getLocation(), this));
		enhancedMapTiles.add(new Kitten4(getMapTile(2, 9).getLocation(), this));
		return enhancedMapTiles;
	}

	public void setCoinCount(int x) {
		coinCount = coinCount + x;
	}

	public int getCoinCount() {
		return coinCount;
	}

	@Override
	public ArrayList<NPC> loadNPCs() {
		ArrayList<NPC> npcs = new ArrayList<>();
		return npcs;
	}

	public void setPlayerStartPosition(Point x) {
		playerStartPosition = x;
	}
}
