package MapEditor;

import Level.Map;
import Maps.BeginningMap;
import Maps.Level2;
import Maps.Level3;
import Maps.Level4;
import Maps.TestMap;
import Maps.TitleScreenMap;

import java.util.ArrayList;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("TestMap");
            add("TitleScreen");
            add("Level2");
            add("Level3");
            add("Level4");
            add("BeginningMap");
        }};
    }

    public static Map getMapByName(String mapName) {
        switch(mapName) {
            case "TestMap":
                return new TestMap();
            case "TitleScreen":
                return new TitleScreenMap();
            case "Level2":
            	return new Level2();
            case "Level3":
            	return new Level3();
            case "Level4":
            	return new Level4();
            case "BeginningMap":
            return new BeginningMap();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
