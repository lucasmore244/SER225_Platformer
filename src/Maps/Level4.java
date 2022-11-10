package Maps;

import java.util.ArrayList;

import Enemies.DinosaurEnemy;
import Enemies.SpaceDog;
import Enemies.SpaceDog1;
import Engine.ImageLoader;
import EnhancedMapTiles.HorizontalMovingPlatform;
import GameObject.Rectangle;
import Level.Enemy;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.TileType;
import Tilesets.SpaceTileset;
import Tilesets.SpaceTileset2;
import Utils.Direction;
import Utils.Point;

public class Level4 extends Map{
	private int coinCount = 0;
	
	public Level4() {
        super("level4.txt", new SpaceTileset2());
        this.playerStartPosition = getMapTile(9, 30).getLocation(); 
    }
    
    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new SpaceDog1(getMapTile(7, 6).getLocation(), getMapTile(13, 6).getLocation(), Direction.RIGHT));
        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        enhancedMapTiles.add(new HorizontalMovingPlatform(ImageLoader.load("GreenPlatform.png"),
				getMapTile(6, 16).getLocation(), getMapTile(12, 16).getLocation(), TileType.JUMP_THROUGH_PLATFORM, 3,
				new Rectangle(0, 6, 16, 4), Direction.RIGHT));
        enhancedMapTiles.add(new HorizontalMovingPlatform(ImageLoader.load("GreenPlatform.png"),
				getMapTile(9, 10).getLocation(), getMapTile(12, 10).getLocation(), TileType.JUMP_THROUGH_PLATFORM, 3,
				new Rectangle(0, 6, 16, 4), Direction.RIGHT));
        enhancedMapTiles.add(new HorizontalMovingPlatform(ImageLoader.load("GreenPlatform.png"),
				getMapTile(9, 6).getLocation(), getMapTile(12, 6).getLocation(), TileType.JUMP_THROUGH_PLATFORM, 3,
				new Rectangle(0, 6, 16, 4), Direction.RIGHT));


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
