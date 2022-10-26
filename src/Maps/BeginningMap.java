package Maps;
import java.util.ArrayList;
import java.util.Random;
import Enemies.Asteriods;
import Level.Enemy;
import Enemies.DinosaurEnemy;
import Enemies.RatEnemy;
import Enemies.UFO;
import Engine.ImageLoader;
import EnhancedMapTiles.Castle;
import EnhancedMapTiles.Checkpoint;
import EnhancedMapTiles.Coin;
import EnhancedMapTiles.EndLevel2Block;
import EnhancedMapTiles.HorizontalMovingPlatform;
import EnhancedMapTiles.Mushrooms;
import EnhancedMapTiles.Spaceship;
import GameObject.Rectangle;
import Level.Enemy;
import Level.EnhancedMapTile;
import Level.Map;
import Level.TileType;
import Level.Tileset;
import Tilesets.SpaceTileset;
import Utils.Direction;

public class BeginningMap extends Map {

	public BeginningMap() {
		super("beginningscreen.txt", new SpaceTileset());
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCoinCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCoinCount(int x) {
		// TODO Auto-generated method stub
		
	}
	
}






