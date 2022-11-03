package Enemies;

import Engine.ImageLoader;
import GameObject.SpriteSheet;
import Level.Player;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;
import Builders.FrameBuilder;
import Enemies.DinosaurEnemy.DinosaurState;
import GameObject.Frame;
import GameObject.ImageEffect;
import Level.Enemy;
import Level.MapEntity;
import Utils.Stopwatch;

import java.util.HashMap;

public class SpaceDog extends Enemy{

	protected Point startLocation;
    protected Point endLocation;

    private float movementSpeed = 0.6f;
    private float amountMoved = 0;
    private Direction startFacingDirection;
    private Direction facingDirection;
    private AirGroundState airGroundState;

    public SpaceDog(Point startLocation, Point endLocation, Direction facingDirection) {
        super(startLocation.x, startLocation.y, new SpriteSheet(ImageLoader.load("SpaceDogSheet.png"), 39, 39), "WALK_RIGHT");
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startFacingDirection = facingDirection;
        this.initialize();
    }
    public void initialize() {
        super.initialize();
        facingDirection = startFacingDirection;
        if (facingDirection == Direction.RIGHT) {
            currentAnimationName = "WALK_RIGHT";
        } else if (facingDirection == Direction.LEFT) {
            currentAnimationName = "WALK_LEFT";
        }
    }
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
            System.out.println(difference);
            moveXHandleCollision(-difference);
            facingDirection = Direction.LEFT;
        } else if (getX1() <= startBound) {
            float difference = startBound - getX1();
            moveXHandleCollision(difference);
            facingDirection = Direction.RIGHT;
        }
	
	
}
    
    
    
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction,  MapEntity entityCollidedWith) {
        // if bug has collided into something while walking forward,
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

    
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if bug is colliding with the ground, change its air ground state to GROUND
        // if it is not colliding with the ground, it means that it's currently in the air, so its air ground state is changed to AIR
        if (direction == Direction.DOWN) {
            if (hasCollided) {
                airGroundState = AirGroundState.GROUND;
            } else {
                airGroundState = AirGroundState.AIR;
            }
        }
    }
    
    
    
}
