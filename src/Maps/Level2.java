package Maps;

import java.util.ArrayList;

import Enemies.DinosaurEnemy;
import Enemies.RatEnemy;
import Enemies.UFO;
import Engine.ImageLoader;
import EnhancedMapTiles.Checkpoint;
import EnhancedMapTiles.Coin;
import EnhancedMapTiles.HorizontalMovingPlatform;
import EnhancedMapTiles.Mushrooms;
import EnhancedMapTiles.Spaceship;
import GameObject.Rectangle;
import Level.Enemy;
import Level.EnhancedMapTile;
import Level.Map;
import Level.TileType;
import Tilesets.SpaceTileset;
import Utils.Direction;

public class Level2 extends Map{

	public Level2() {
        super("level2.txt", new SpaceTileset());
        this.playerStartPosition = getMapTile(1, 2).getLocation(); 
    }
	 @Override
	public ArrayList<Enemy> loadEnemies() {
		 ArrayList<Enemy> enemies = new ArrayList<>();
       
      
		 return enemies;
	    }
	 
	 
	 @Override

	 public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
		 ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

		 return enhancedMapTiles; 
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
