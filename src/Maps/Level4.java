package Maps;

import java.util.ArrayList;


import Level.Enemy;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Tilesets.SpaceTileset;
import Tilesets.SpaceTileset2;
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
