package Maps;

import java.util.ArrayList;

import Enemies.Asteriods;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import EnhancedMapTiles.Coin;
import EnhancedMapTiles.SpaceshipParts;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.EnhancedMapTile;
import Level.LevelState;
import Level.Map;
import Level.NPC;
import Level.PlayerListener;
import Level.PlayerState;
import Tilesets.SpaceTileset;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;

public class Level3 extends Map{
	
private int coinCount = 0;
private Asteriods asteriod;
	
    public Level3() {
        super("level3.txt", new SpaceTileset());
        this.playerStartPosition = getMapTile(1, 9).getLocation(); 
    }
    
    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(asteriod =new Asteriods(getMapTile(0,4).getLocation().addY(20), Direction.RIGHT));
        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        
        enhancedMapTiles.add(new SpaceshipParts(getMapTile(5, 1).getLocation(), this));

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
