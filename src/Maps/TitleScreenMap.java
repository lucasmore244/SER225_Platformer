package Maps;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Sound;
import GameObject.Sprite;
import Level.Map;
import Tilesets.CommonTileset;
import Utils.Colors;
import Utils.Point;

// Represents the map that is used as a background for the main menu and credits menu screen
public class TitleScreenMap extends Map {
	private Sprite cat;
//	protected Sound sound = new Sound();

	public TitleScreenMap() {
		super("title_screen_map.txt", new CommonTileset());
		Point catLocation = getMapTile(6, 8).getLocation().subtractX(24).subtractY(6);
		cat = new Sprite(ImageLoader.loadSubImage("Cat.png", Colors.MAGENTA, 0, 0, 24, 24));
		cat.setScale(3);
		cat.setLocation(catLocation.x, catLocation.y);
//		this.playMusic(6);
	}

	@Override
	public void draw(GraphicsHandler graphicsHandler) {
		super.draw(graphicsHandler);
		cat.draw(graphicsHandler);
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

//	public void playMusic(int i) {
//		sound.setFile(i);
//		sound.play();
//		sound.loop();
//	}
}
