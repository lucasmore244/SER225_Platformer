package Maps;

import java.util.ArrayList;

import Enemies.Asteriods;
import Enemies.UFO;
import EnhancedMapTiles.Checkpoint;
import EnhancedMapTiles.Castle;
import EnhancedMapTiles.Spaceship;
import EnhancedMapTiles.SpaceshipBody;
import Engine.ImageLoader;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import EnhancedMapTiles.Coin;
import EnhancedMapTiles.HorizontalMovingPlatform;
import EnhancedMapTiles.SpaceshipParts;
import EnhancedMapTiles.SpaceshipTWing;
import EnhancedMapTiles.SpaceshipWing;
import EnhancedMapTiles.VerticalMovingPlatform;
import EnhancedMapTiles.VerticalMovingPlatformLevel3;
import GameObject.Rectangle;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.EnhancedMapTile;
import Level.LevelState;
import Level.Map;
import Level.NPC;
import Level.PlayerListener;
import Level.PlayerState;
import Level.TileType;
import Tilesets.SpaceTileset;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;

public class Level3 extends Map{
	
private int coinCount = 0;
private Asteriods asteriod;

	
    public Level3() {
        super("level3.txt", new SpaceTileset());
        this.playerStartPosition = getMapTile(0, 3).getLocation(); 
    }
    
    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new UFO(getMapTile(7, 10).getLocation().addY(2), getMapTile(15, 10).getLocation().addY(2), Direction.RIGHT));
        enemies.add(new UFO(getMapTile(40, 10).getLocation().addY(2), getMapTile(47, 10).getLocation().addY(2), Direction.RIGHT));
        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        
        
        enhancedMapTiles.add(new VerticalMovingPlatform(
                ImageLoader.load("GreyPlatform.png"),
                getMapTile(40, 3).getLocation(),
                getMapTile(40, 6).getLocation(),
                TileType.NOT_PASSABLE,
                3,
                new Rectangle(0, 6,16,4),
                Direction.RIGHT
        ));
        enhancedMapTiles.add(new VerticalMovingPlatform(
                ImageLoader.load("GreyPlatform.png"),
                getMapTile(53, 1).getLocation(),
                getMapTile(53, 4).getLocation(),
                TileType.NOT_PASSABLE,
                3,
                new Rectangle(0, 6,16,4),
                Direction.RIGHT
        ));
        
        enhancedMapTiles.add(new Checkpoint(getMapTile(44, 5).getLocation(), this));

        enhancedMapTiles.add(new Castle(getMapTile(67, 5).getLocation()));
        
        enhancedMapTiles.add(new SpaceshipParts(getMapTile(5, 1).getLocation(), this));
        
        enhancedMapTiles.add(new SpaceshipWing(getMapTile(16, 3).getLocation(), this));
        
        enhancedMapTiles.add(new SpaceshipBody(getMapTile(30, 3).getLocation(), this));
        
        enhancedMapTiles.add(new SpaceshipTWing(getMapTile(56, 3).getLocation(), this));




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

   
        return npcs;
    }
    
    
    public void setPlayerStartPosition(Point x) {
    	playerStartPosition = x;
    }
    
    
    
    
}
