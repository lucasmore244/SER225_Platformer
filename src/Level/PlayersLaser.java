package Level;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Players.CatLevel3;
import Utils.Direction;
import Utils.Point;
import Utils.Stopwatch;

import java.util.HashMap;
// This is the class for the Cat player character
// basically just sets some values for physics and then defines animations
public class PlayersLaser extends GameObject {

	private float movementSpeed;
	private Stopwatch existenceTimer = new Stopwatch();
	private MapEntityStatus mapEntityStatus;

	public PlayersLaser(Point location, float movementSpeed, int existenceTime) {
		super(new SpriteSheet(ImageLoader.load("Fireball.png"), 7, 7), location.y, location.x, "DEFAULT");
		this.movementSpeed = movementSpeed;

		// how long the fireball will exist for before disappearing
		existenceTimer.setWaitTime(existenceTime);
		
		initialize();
	}
	

	@Override
	public void update() {
		// if timer is up, set map entity status to REMOVED
		// the camera class will see this next frame and remove it permanently from the map
		if (existenceTimer.isTimeUp()) {
			this.mapEntityStatus = MapEntityStatus.REMOVED;
		} else {
			// move fireball forward
			moveXHandleCollision(movementSpeed);
			super.update();
	    }
	}

	@Override
	public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
		// if fireball collides with anything solid on the x axis, it is removed
		if (hasCollided) {
			this.mapEntityStatus = MapEntityStatus.REMOVED;
		}
	}

	    

	@Override
	public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
		return new HashMap<String, Frame[]>() {{
			put("DEFAULT", new Frame[]{
					new FrameBuilder(spriteSheet.getSprite(0, 0))
					.withScale(3)
					.withBounds(1, 1, 5, 5)
					.build()
	        	});	
	        }};
	    }
	
	
	}
