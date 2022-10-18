package Maps;

import java.util.ArrayList;
import java.util.Random;

import Enemies.Asteriods;
import Level.Enemy;
import Level.Map;
import Tilesets.SpaceTileset;
import Utils.Direction;

public class Level2 extends Map{
	protected Asteriods asteriod;
	

	public Level2() {
        super("level2.txt", new SpaceTileset());
        this.playerStartPosition = getMapTile(1, 2).getLocation(); 
        
    }
	
	 @Override
	    public ArrayList<Enemy> loadEnemies() {
		 Random random = new Random();
	        ArrayList<Enemy> enemies = new ArrayList<>();
	       	enemies.add(asteriod =new Asteriods(getMapTile(11,random.nextInt(10)).getLocation().addY(20), Direction.LEFT));
	        return enemies;
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
