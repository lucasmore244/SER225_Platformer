package Maps;

import java.util.ArrayList;
import java.util.Random;
import Enemies.Asteriods;
import Level.Enemy;
import Enemies.DinosaurEnemy;
import Enemies.RatEnemy;
import Enemies.UFO;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import EnhancedMapTiles.Castle;
import EnhancedMapTiles.Checkpoint;
import EnhancedMapTiles.Coin;
import EnhancedMapTiles.EndLevel2Block;
import EnhancedMapTiles.HorizontalMovingPlatform;
import EnhancedMapTiles.Mushrooms;
import EnhancedMapTiles.Spaceship;
import GameObject.Rectangle;
import GameObject.Sprite;
import Level.Enemy;
import Level.EnhancedMapTile;
import Level.Map;
import Level.TileType;
import Level.Tileset;
import Tilesets.SpaceTileset;
import Utils.Colors;
import Utils.Direction;
import Utils.Point;

public class BeginningMap extends Map {
	private Sprite cat, spaceship,kittens, spacedog;

	public BeginningMap() {
		super("beginningscreen.txt", new SpaceTileset());
		Point catLocation = getMapTile(7, 6).getLocation().subtractX(24).subtractY(6);
		cat = new Sprite(ImageLoader.loadSubImage("Cat.png", Colors.MAGENTA, 0, 0, 24, 24));
		cat.setScale(6);
		cat.setLocation(catLocation.x, catLocation.y);
		Point spaceshipLocation = getMapTile(2,1).getLocation().subtractX(24).subtractY(6);
		spaceship = new Sprite(ImageLoader.loadSubImage("newRocketSheet.png", Colors.MAGENTA, 0, 0, 42, 35));
		spaceship.setScale(3);
		spaceship.setLocation(spaceshipLocation.x, spaceshipLocation.y);
		Point kittensLocation = getMapTile(12,1).getLocation().subtractX(24).subtractY(6);
		kittens = new Sprite(ImageLoader.loadSubImage("kittens.png", Colors.MAGENTA, 0, 0, 163, 98));
		kittens.setScale(1);
		kittens.setLocation(kittensLocation.x, kittensLocation.y);
		Point spacedogLocation = getMapTile(14,9).getLocation().subtractX(24).subtractY(6);
		spacedog = new Sprite(ImageLoader.loadSubImage("spacedog.png", Colors.MAGENTA, 0, 0, 117, 135));
		spacedog.setScale(1);
		spacedog.setLocation(spacedogLocation.x, spacedogLocation.y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(GraphicsHandler graphicsHandler) {
		super.draw(graphicsHandler);
		cat.draw(graphicsHandler);
		spaceship.draw(graphicsHandler);
		kittens.draw(graphicsHandler);
		spacedog.draw(graphicsHandler);
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
