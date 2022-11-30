package Enemies;

import java.util.HashMap;
import java.util.Random;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
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

public class ShootingStar extends Enemy{

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
	protected Map map;
	
	Random rand = new Random();
    private float moveAmountY = rand.nextFloat()*2;
    private boolean bool = true;

	public ShootingStar(Point location, Direction facingDirection, Map map) {
		super(location.x, location.y, new SpriteSheet(ImageLoader.load("star.png"), 80, 82),
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
		
	}

	@Override
	public void update(Player player) {
		float moveAmountX = 0;
		
		// if on air, walk forward based on facing direction
		if (airGroundState == AirGroundState.AIR) {
			if (facingDirection == Direction.RIGHT) {
				moveAmountX += movementSpeed;
			} else {
				moveAmountX -= movementSpeed;
			}
		}
//		
		if(currentAnimationName == "WALK_LEFT" && this.getX2() < 2) {
			 this.currentAnimationName = facingDirection == Direction.LEFT ? "WALK_LEFT" : "WALK_RIGHT";
			this.setLocation(740, (float) (Math.random() * (400) + 1) );
		}
//		

		// move Star
		moveXHandleCollision(moveAmountX);
		//super.update(player);
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
				put("WALK_LEFT", new Frame[] { new FrameBuilder(spriteSheet.getSprite(0, 0), 500).withScale((float) .4)
						.withBounds(1, 1, 87, 17).build(), });
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
