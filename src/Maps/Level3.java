package Maps;

import java.util.ArrayList;

import Enemies.Asteriods;
import Level.Enemy;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Tilesets.SpaceTileset;
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
