package Maps;

import java.util.ArrayList;

import Enemies.DinosaurEnemy;
import Enemies.SpaceDog;
import Enemies.SpaceDog1;
import Level.Enemy;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Tilesets.SpaceTileset;
import Tilesets.SpaceTileset2;
import Utils.Direction;
import Utils.Point;

public class Level4 extends Map{
	private int coinCount = 0;
	
	public Level4() {
        super("level4.txt", new SpaceTileset2());
        this.playerStartPosition = getMapTile(9, 26).getLocation(); 
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
