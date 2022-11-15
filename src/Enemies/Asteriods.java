package Enemies;

import Builders.FrameBuilder;
import Enemies.DinosaurEnemy.DinosaurState;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.Map;
import Level.MapEntity;
import Level.MapEntityStatus;
import Level.Player;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;
import Utils.Stopwatch;
import java.util.HashMap;
import java.util.Random;


public class Asteriods extends Enemy {	
	private float movementSpeed = 5f;
	private float amountMoved = 0;
	private Direction startFacingDirection;
	private Direction facingDirection;
	private AirGroundState airGroundState;
	protected Direction direction;
	protected boolean hasCollided;
	protected Laser laser;
	protected Stopwatch time = new Stopwatch();
	protected Stopwatch othertime = new Stopwatch();
	protected Stopwatch gameTime = new Stopwatch();
	protected Map map;
	
	Random rand = new Random();
    private float moveAmountY = rand.nextFloat()*2;
    private boolean bool = true;

	public Asteriods(Point location, Direction facingDirection, Map map) {
		super(800, location.y, new SpriteSheet(ImageLoader.load("AsteriodSpriteSheet.png"), 80, 89),
				"WALK_LEFT");
		this.startFacingDirection = facingDirection;
		this.map = map;
		isRespawnable = true;
		this.initialize();
	}

	@Override
	public void initialize() {
		super.initialize();
		facingDirection = startFacingDirection;
		if (facingDirection == Direction.RIGHT) {
			currentAnimationName = "WALK_RIGHT";
		} else if (facingDirection == Direction.LEFT) {
			currentAnimationName = "WALK_LEFT";
		}
		airGroundState = AirGroundState.AIR;
		time.setWaitTime(1000);
		othertime.setWaitTime(500);
		gameTime.setWaitTime(3000);
		this.setMovementSpeed((int) (Math.random() * 8 + 5));
		if (bool == true) {
			moveAmountY = -moveAmountY;
			bool = false;
		}else {
			
			bool = true;
		}
	}

	@Override
	public void update(Player player) {
		if (gameTime.isTimeUp()) {
			mapEntityStatus = MapEntityStatus.REMOVED;
			System.out.println("Running");
		}
		
		float moveAmountX = 0;
		
		// if on air, walk forward based on facing direction
		if (airGroundState == AirGroundState.AIR) {
			if (facingDirection == Direction.RIGHT) {
				moveAmountX += movementSpeed;
			} else {
				moveAmountX -= movementSpeed;
			}
		}
		for (int i = 0; i < map.getEnemies().size(); i++) {
			if (map.getEnemies().get(i) instanceof Laser) {
				if (intersects(map.getEnemies().get(i))) {
					if (time.isTimeUp()) {
						this.mapEntityStatus = MapEntityStatus.REMOVED;
					}
					if (othertime.isTimeUp()) {
						this.initialize();
						this.mapEntityStatus = MapEntityStatus.ACTIVE;
						this.setLocation(1000, (float) (Math.random() * (400) + 10) );
					}
				}
			}
		}

		if (intersects(player)) {
		 this.currentAnimationName = facingDirection == Direction.LEFT ? "WALK_LEFT_BROKEN" : "WALK_RIGHT_BROKEN";
		}
		
		if(currentAnimationName == "WALK_LEFT_BROKEN" && this.getX2() < 2) {
			 this.currentAnimationName = facingDirection == Direction.LEFT ? "WALK_LEFT" : "WALK_RIGHT";
			this.setLocation(740, (float) (Math.random() * (400) + 1) );
		}
		

		// move asteriod
		moveYHandleCollision(moveAmountY);
		moveXHandleCollision(moveAmountX);
		super.update(player);
		amountMoved = amountMoved + movementSpeed;
		
	}

	@Override
	public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
	}

	@Override
	public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
	}

	@Override
	public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
		return new HashMap<String, Frame[]>() {
			{
				put("WALK_LEFT", new Frame[] { new FrameBuilder(spriteSheet.getSprite(0, 0), 100).withScale((float) .6)
						.withBounds(0, 3, 80, 86).build(), });

				put("WALK_LEFT_BROKEN", new Frame[] { new FrameBuilder(spriteSheet.getSprite(0, 1), 100).withScale((float) .6)
						.withBounds(10, 3, 80, 86).build(), });

				put("WALK_RIGHT", new Frame[] { new FrameBuilder(spriteSheet.getSprite(0, 0), 100).withScale((float) .6)
						.withImageEffect(ImageEffect.FLIP_HORIZONTAL).withBounds(10, 10, 80, 89).build(), });
				put("WALK_RIGHT_BROKEN", new Frame[] { new FrameBuilder(spriteSheet.getSprite(0, 1), 100).withScale((float) .6)
						.withBounds(10, 10,80, 89).build(), });

			}
		};
	}

	public int getMovementSpeed() {
		return (int) movementSpeed;
	}

	public void setMovementSpeed(int movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

}
