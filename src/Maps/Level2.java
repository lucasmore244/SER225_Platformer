package Maps;

import Level.Map;
import Tilesets.SpaceTileset;

public class Level2 extends Map{

	public Level2() {
        super("level2.txt", new SpaceTileset());
        this.playerStartPosition = getMapTile(1, 2).getLocation(); 
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
