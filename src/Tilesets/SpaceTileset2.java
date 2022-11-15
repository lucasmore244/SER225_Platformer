package Tilesets;

import java.util.ArrayList;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import Level.TileType;
import Level.Tileset;

public class SpaceTileset2 extends Tileset{

	public SpaceTileset2() {
        super(ImageLoader.load("Blacksquare.png"), 16, 16, 3);
    }
	
	public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();
        
        //Not passible space
        Frame spaceFrame = new FrameBuilder(getSubImage(0, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder grassTile = new MapTileBuilder(spaceFrame)
                .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(grassTile);

        // passible space
        Frame skyFrame = new FrameBuilder(getSubImage(0, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder skyTile = new MapTileBuilder(skyFrame);

        mapTiles.add(skyTile);
        return mapTiles;
    }
}
