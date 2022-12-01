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
 

public class UFO extends Enemy {
	
	 // timer is used to determine when a fireball is to be shot out
    protected Stopwatch shootTimer = new Stopwatch();
    protected Stopwatch shootReloadTimer = new Stopwatch();

    // can be either WALK or SHOOT based on what the enemy is currently set to do
    protected Point startLocation;
    protected Point endLocation;

    private float movementSpeed = 0.6f;
    private float amountMoved = 0;
    private Direction startFacingDirection;
    private Direction facingDirection;
    private AirGroundState airGroundState;

    public UFO(Point startLocation, Point endLocation, Direction facingDirection) {
        super(startLocation.x, startLocation.y, new SpriteSheet(ImageLoader.load("UFO.png"), 39, 39), "WALK_RIGHT");
        this.startLocation = startLocation;
        this.endLocation = endLocation;
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
        
     // every 2 seconds, the fireball will be shot out
        shootTimer.setWaitTime(2000);
        shootReloadTimer.setWaitTime(3000);
    }

    @Override
    public void update(Player player) {
    	super.update(player);
    	
    	float startBound = startLocation.x;
        float endBound = endLocation.x;
        
        
        if (facingDirection == Direction.RIGHT) {
                currentAnimationName = "WALK_RIGHT";
                moveXHandleCollision(movementSpeed);
        } else {
                currentAnimationName = "WALK_LEFT";
                moveXHandleCollision(-movementSpeed);
        }
        
    	if (getX1() + getWidth() >= endBound) {
            float difference = endBound - (getX2());
            moveXHandleCollision(-difference);
            facingDirection = Direction.LEFT;
        } else if (getX1() <= startBound) {
            float difference = startBound - getX1();
            moveXHandleCollision(difference);
            facingDirection = Direction.RIGHT;
        }
    	
    	// INITIALIZE FIREBALL
    	
    	float moveAmountX = 0;
        float moveAmountY = 0;

        // if shoot timer is up and UFO is not currently shooting, make it shoot
        if (shootTimer.isTimeUp()) {
        		int fireballY = Math.round(getY()) + 20;
        		int fireballX = Math.round(getX()) + 20;
        		UFOFireball fireball1 = new UFOFireball(new Point(fireballX, fireballY), -6, 1500);
        		UFOFireball fireball2 = new UFOFireball(new Point(fireballX, fireballY), -6, 1500);
        		UFOFireball fireball3 = new UFOFireball(new Point(fireballX, fireballY), -6, 1500);
        		UFOFireball fireball4 = new UFOFireball(new Point(fireballX, fireballY), -6, 1500);
        		UFOFireball fireball5 = new UFOFireball(new Point(fireballX, fireballY), -6, 1500);
        		fireball1.setDiagonal(0);
        		fireball2.setDiagonal(1);
        		fireball3.setDiagonal(2);
        		fireball4.setDiagonal(3);
        		fireball5.setDiagonal(4);
        		// add fireball enemy to the map for it to offically spawn in the level
        		map.addEnemy(fireball1);
        		map.addEnemy(fireball2);
        		map.addEnemy(fireball3);
        		map.addEnemy(fireball4);
        		map.addEnemy(fireball5);
        		shootTimer.setWaitTime(500);
        		
        }
        if (shootReloadTimer.isTimeUp()) {
        	shootTimer.setWaitTime(2000);
        	shootReloadTimer.setWaitTime(3000);
        }
        
        
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction,  MapEntity entityCollidedWith) {
        // if collided into something while walking forward,
        // it turns around (changes facing direction)
    	if (hasCollided) {
            if (direction == Direction.RIGHT) {
                facingDirection = Direction.LEFT;
                currentAnimationName = "WALK_LEFT";
            } else {
                facingDirection = Direction.RIGHT;
                currentAnimationName = "WALK_RIGHT";
            }
        }
    }

    @Override
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if collided with the ground, change its air ground state to GROUND
        // if it is not colliding with the ground, it means that it's currently in the air, so its air ground state is changed to AIR
        if (direction == Direction.DOWN) {
            if (hasCollided) {
                airGroundState = AirGroundState.GROUND;
            } else {
                airGroundState = AirGroundState.AIR;
            }
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 100)
                    		.withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withScale(2)
                            .withBounds(6, 6, 12, 7)
                            .build(),
            });

            put("WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 100)
                    		.withScale(2)
                            .withBounds(6, 6, 12, 7)
                            .build(),
            });
        }};
    }
}
