package Maps;

import Enemies.RatEnemy;
import Enemies.UFO;
import Enemies.Asteriods;
import Enemies.DinosaurEnemy;
import Enemies.Fireball;
import Engine.ImageLoader;
import Engine.Key;
import Engine.Keyboard;
import EnhancedMapTiles.Coin;
import EnhancedMapTiles.EndLevel2Block;
import EnhancedMapTiles.Checkpoint;
import EnhancedMapTiles.Spaceship;
import EnhancedMapTiles.VerticalMovingPlatform;
import EnhancedMapTiles.HorizontalMovingPlatform;
import EnhancedMapTiles.Mushrooms;
import GameObject.Rectangle;
import Level.*;
import NPCs.Walrus;
import Tilesets.CommonTileset;
import Utils.Direction;
import Utils.Point;
import Utils.Stopwatch;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class TestMap extends Map {
	
	private int coinCount = 0;
	private Player player;
	private Stopwatch spawnblock = new Stopwatch();

	
    public TestMap() {
        super("test_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(2, 11).getLocation(); 
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
       
        enemies.add(new RatEnemy(getMapTile(15, 8).getLocation().addY(20), Direction.LEFT));
        enemies.add(new DinosaurEnemy(getMapTile(19, 1).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.RIGHT));
        enemies.add(new DinosaurEnemy(getMapTile(58, 4).getLocation().addY(2), getMapTile(60, 4).getLocation().addY(2), Direction.RIGHT));
        enemies.add(new Mushrooms(getMapTile(16, 8).getLocation()));
        
        
        enemies.add(new Mushrooms(getMapTile(24, 10).getLocation()));
        
        enemies.add(new Mushrooms(getMapTile(34, 10).getLocation()));
        
        enemies.add(new Mushrooms(getMapTile(54, 10).getLocation()));
      
        enemies.add(new Mushrooms(getMapTile(11, 10).getLocation()));
        
        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        
        enhancedMapTiles.add(new VerticalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getMapTile(4, 4).getLocation(),
                getMapTile(6, 8).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6, 16, 7),
                Direction.RIGHT
        ));
        
        enhancedMapTiles.add(new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getMapTile(24, 6).getLocation(),
                getMapTile(27, 6).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.RIGHT
        ));
        
        enhancedMapTiles.add(new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getMapTile(36, 8).getLocation(),
                getMapTile(43, 8).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.LEFT
        ));
        
        enhancedMapTiles.add(new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getMapTile(41, 8).getLocation(),
                getMapTile(51, 8).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.RIGHT
        ));
        
        enhancedMapTiles.add(new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getMapTile(48, 8).getLocation(),
                getMapTile(54, 8).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.LEFT
        ));

        enhancedMapTiles.add(new Spaceship(
                getMapTile(64, 10).getLocation()
        ));
                      
        enhancedMapTiles.add(new Coin(getMapTile(7, 11).getLocation(), this));
      
        enhancedMapTiles.add(new Coin(
        		getMapTile(20, 0).getLocation(), this
        		
        )); 
        enhancedMapTiles.add(new Coin(
        		getMapTile(44, 5).getLocation(), this
        		
        )); 
        enhancedMapTiles.add(new Coin(
        		getMapTile(58, 11).getLocation(), this
        ));
        
        enhancedMapTiles.add(new Checkpoint(getMapTile(32, 10).getLocation(), this)); 

        return enhancedMapTiles; 
    }
    
    public void setCoinCount(int x)  {
    	coinCount = coinCount + x; 
    }
    
    public int getCoinCount() {
    	return coinCount;
    	
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        npcs.add(new Walrus(getMapTile(30, 10).getLocation().subtractY(13)));

        return npcs;
    }
    
    
    public void setPlayerStartPosition(Point x) {
    	playerStartPosition = x;
    }
    
}