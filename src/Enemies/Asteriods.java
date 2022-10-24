package Enemies;

import Builders.FrameBuilder;
import Enemies.DinosaurEnemy.DinosaurState;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
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
	private float movementSpeed = 3f;
	private float amountMoved = 0;
	private Direction startFacingDirection;
	private Direction facingDirection;
	private AirGroundState airGroundState;
	protected Direction direction;
	protected boolean hasCollided;
	protected Player player;


	public Asteriods(Point location, Direction facingDirection) {
		super(location.x, location.y, new SpriteSheet(ImageLoader.load("AsteriodSpriteSheet.png"), 80, 89), "WALK_LEFT");
		this.startFacingDirection = facingDirection;
		isRespawnable = false;
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
			player.hurtPlayer(this);
		}
		if(this.getX2() < 2) {
			  this.setLocation(740, (float) (Math.random() * (400) + 1) );
		}
		// move bug
		moveYHandleCollision(moveAmountY);
		moveXHandleCollision(moveAmountX);
		super.update(player);
		amountMoved = amountMoved + movementSpeed;
		
		if(this.intersects(player)) {
			player.setPlayerHealth(player.getPlayerhealth() - 1);
		}
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
				put("WALK_LEFT_BROKEN", new Frame[] { new FrameBuilder(spriteSheet.getSprite(0, 1), 100).withScale(1)
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
