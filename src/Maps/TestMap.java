package Maps;

import Enemies.RatEnemy;
import Enemies.UFO;
import Enemies.DinosaurEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.Checkpoint;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import GameObject.Rectangle;
import Level.*;
import NPCs.Walrus;
import Tilesets.CommonTileset;
import Utils.Direction;
import Utils.Point;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class TestMap extends Map {
	
	private int coinCount = 0;
	
    public TestMap() {
        super("test_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(2, 11).getLocation(); 
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new UFO(getMapTile(7, 1).getLocation().addY(20), Direction.RIGHT));
        enemies.add(new UFO(getMapTile(45, 1).getLocation().addY(20), Direction.RIGHT));
        enemies.add(new RatEnemy(getMapTile(15, 8).getLocation().addY(20), Direction.LEFT));
        enemies.add(new DinosaurEnemy(getMapTile(19, 1).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.RIGHT));
        enemies.add(new DinosaurEnemy(getMapTile(58, 4).getLocation().addY(2), getMapTile(60, 4).getLocation().addY(2), Direction.RIGHT));
     
        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

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
                getMapTile(42, 8).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.RIGHT
        ));
        
        enhancedMapTiles.add(new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getMapTile(42, 8).getLocation(),
                getMapTile(49, 8).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.RIGHT
        ));
        
        enhancedMapTiles.add(new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getMapTile(49, 8).getLocation(),
                getMapTile(54, 8).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.RIGHT
        ));

        enhancedMapTiles.add(new EndLevelBox(
                getMapTile(64, 10).getLocation()
        ));

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
