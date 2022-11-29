package Maps;

import java.util.ArrayList;
import java.util.Random;
import Enemies.Asteriods;
import Level.Enemy;
import Enemies.DinosaurEnemy;
import Enemies.RatEnemy;
import Enemies.ShootingStar;
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
import Level.NPC;
import Level.TileType;
import NPCs.Walrus;
import NPCs.WalrusLevel2;
import Tilesets.SpaceTileset;
import Tilesets.SpaceTileset2;
import Utils.Direction;
import Utils.Stopwatch;

public class Level2 extends Map{
	
	public Level2() {
		super("level2.txt", new SpaceTileset2());
        this.playerStartPosition = getMapTile(1, 2).getLocation();
    }

	 @Override
	    public ArrayList<Enemy> loadEnemies() {
		 Random random = new Random();
	        ArrayList<Enemy> enemies = new ArrayList<>();
	    	enemies.add(new ShootingStar(getMapTile(7,random.nextInt(11)).getLocation().addY(20), Direction.LEFT, this));
	    	enemies.add(new ShootingStar(getMapTile(15,random.nextInt(9)).getLocation().addY(20), Direction.LEFT, this));
	    	enemies.add(new ShootingStar(getMapTile(11,random.nextInt(11)).getLocation().addY(20), Direction.LEFT, this));
	    	enemies.add(new ShootingStar(getMapTile(3,random.nextInt(11)).getLocation().addY(20), Direction.LEFT, this));
	      
	       	enemies.add(new Asteriods(getMapTile(11,random.nextInt(13)).getLocation().addY(20), Direction.LEFT, this));
	       	enemies.add(new Asteriods(getMapTile(11,random.nextInt(13)).getLocation().addY(20), Direction.LEFT, this));
	       	enemies.add(new Asteriods(getMapTile(11,random.nextInt(13)).getLocation().addY(20), Direction.LEFT, this));
	       	enemies.add(new Asteriods(getMapTile(11,random.nextInt(13)).getLocation().addY(20), Direction.LEFT, this));
	       	enemies.add(new Asteriods(getMapTile(11,random.nextInt(13)).getLocation().addY(20), Direction.LEFT, this));
	       	enemies.add(new Asteriods(getMapTile(11,random.nextInt(13)).getLocation().addY(20), Direction.LEFT, this));
	        return enemies;
	    }

	 public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
		 ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
		 enhancedMapTiles.add(new EndLevel2Block(getMapTile(19, 14).getLocation()));
		 return enhancedMapTiles; 
	    } 
	 
	 @Override
		public ArrayList<NPC> loadNPCs() {
			ArrayList<NPC> npcs = new ArrayList<>();
			npcs.add(new WalrusLevel2(getMapTile(4, 5).getLocation().subtractY(13)));
			
			return npcs;
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
