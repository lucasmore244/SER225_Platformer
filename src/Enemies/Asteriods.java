package Enemies;

import Builders.FrameBuilder;
import Enemies.DinosaurEnemy.DinosaurState;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntity;
import Level.Player;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;
import Utils.Stopwatch;
import java.util.HashMap;


public class Asteriods extends Enemy {
	protected Stopwatch shootTimer = new Stopwatch();
	private float movementSpeed = 5f;
	private float amountMoved = 0;
	private Direction startFacingDirection;
	private Direction facingDirection;
	private AirGroundState airGroundState;
	protected Direction direction;
	protected boolean hasCollided;
	protected Player player;
	protected int health = player.getPlayerhealth();

	public Asteriods(Point location, Direction facingDirection) {
		super(location.x, location.y, new SpriteSheet(ImageLoader.load("Asteriod.png"), 90, 90), "WALK_LEFT");
		this.startFacingDirection = facingDirection;
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

		this.setMovementSpeed((int) (Math.random() * 10 + 5));
	}

	@Override
	public void update(Player player) {
		float moveAmountX = 0;
		float moveAmountY = 0;

		// if on air, walk forward based on facing direction
		if (airGroundState == AirGroundState.AIR) {
			if (facingDirection == Direction.RIGHT) {
				moveAmountX += movementSpeed;
			} else {
				moveAmountX -= movementSpeed;
			}
		}

		if (intersects(player)) {
			player.setPlayerHealth(health);
		}
		
		// move bug
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
				put("WALK_LEFT", new Frame[] { new FrameBuilder(spriteSheet.getSprite(0, 0), 100).withScale(1)
						.withBounds(6, 6, 12, 7).build(), });

				put("WALK_RIGHT", new Frame[] { new FrameBuilder(spriteSheet.getSprite(0, 0), 100).withScale(1)
						.withImageEffect(ImageEffect.FLIP_HORIZONTAL).withBounds(6, 6, 12, 7).build(), });
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
