package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.LevelState;
import Level.MapEntity;
import Level.MapEntityStatus;
import Level.Player;
import Level.PlayerListener;
import Level.PlayerState;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;
import Utils.Stopwatch;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;

// This class is for the green di enemy that shoots boness
// It walks back and forth between two set points (startLocation and endLocation)
// Every so often (based on shootTimer) it will shoot a bones enemy
public class SpaceDog1 extends Enemy {

    // start and end location defines the two points that it walks between
    // is only made to walk along the x axis and has no air ground state logic, so make sure both points have the same Y value
    protected Point startLocation;
    protected Point endLocation;
    //protected int dogLives = 3;
    protected int PlayerTouchFlag = 0;
    protected boolean shieldOn = true;
    protected float PlayerTime = 0;
    protected int damageFlag = 0;
    protected float shieldTime = 0;
    protected static int dogLives = 3;
    protected ArrayList<PlayerListener> listeners = new ArrayList<>();

    protected float movementSpeed = 1f;
    private Direction startFacingDirection;
    protected Direction facingDirection;
    protected AirGroundState airGroundState;

    // timer is used to determine when a bones is to be shot out
    protected Stopwatch shootTimer = new Stopwatch();
    // this is to stop the dog from dying instantly onc ehit by the poop
    protected Stopwatch hitTimer = new Stopwatch();
    // resets the sheild after an amount of time
    protected Stopwatch shieldTimer = new Stopwatch();
    //the ammount of time the sheild has to come back
    protected int allShieldTimerTimes = 3000;


    // can be either WALK or SHOOT based on what the enemy is currently set to do
    protected SpaceDogState spaceDogState;
    protected SpaceDogState previousSpaceDogState;

    public SpaceDog1(Point startLocation, Point endLocation, Direction facingDirection) {
        super(startLocation.x, startLocation.y-15, new SpriteSheet(ImageLoader.load("NewSpaceDogSheet.png"), 100 , 100), "WALK_RIGHT");
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startFacingDirection = facingDirection;
        this.initialize();
    }

    @Override
    public void initialize() {
        super.initialize();
        spaceDogState = SpaceDogState.WALK;
        previousSpaceDogState = spaceDogState;
        facingDirection = Direction.RIGHT;
        if (facingDirection == Direction.RIGHT) {
            currentAnimationName = "WALK_RIGHT";
        } else if (facingDirection == Direction.LEFT) {
            currentAnimationName = "WALK_LEFT";
        }
        airGroundState = AirGroundState.GROUND;
        isRespawnable = false;
        // every 2 seconds, the bones will be shot out
        shootTimer.setWaitTime(2000);
        hitTimer.setWaitTime(1000);
    }

    @Override
    public void update(Player player) {
        float startBound = startLocation.x;
        float endBound = endLocation.x;

        // if shoot timer is up and space dog is not currently shooting, set its state to SHOOT
//        if (shootTimer.isTimeUp() && spaceDogState != SpaceDogState.SHOOT) {
//            spaceDogState = SpaceDogState.SHOOT;
//        }
        
        // This is the system used to see if the dog gets hit.
        //The long if statement is so when its hit with the shield on the shield disappears
        //The else if is so the dog takes damage when hit when the shield is off
        for (int i = 0; i < map.getEnemies().size(); i++) {
			if (map.getEnemies().get(i) instanceof CatProjectile) {
				if (intersects(map.getEnemies().get(i)) && hitTimer.isTimeUp() && (currentAnimationName == "WALK_SHIELD_RIGHT" || currentAnimationName == "WALK_SHIELD_LEFT")) {
					hitTimer.setWaitTime(1000);
					shieldTimer.setWaitTime(allShieldTimerTimes);
					shieldOn = false;
				} else if(intersects(map.getEnemies().get(i)) && hitTimer.isTimeUp()) {
//					System.out.println("hit");	
					dogLives = dogLives - 1;
					currentAnimationName = facingDirection == Direction.RIGHT ? "SHOOT_HURT_RIGHT" : "SHOOT_HURT_LEFT";
					hitTimer.setWaitTime(1000);
				}
			}
		}
        
        //Turns shield back on after set amount of time
        if (shieldOn == false && shieldTimer.isTimeUp()) {
        	shieldOn = true;
        }
        
        

        // if space dog is walking, determine which direction to walk in based on facing direction (also with shield or without shield)
        if (spaceDogState == SpaceDogState.WALK) {
            if (facingDirection == Direction.RIGHT && shieldOn == true) {
                currentAnimationName = "WALK_SHIELD_RIGHT";
                moveXHandleCollision(movementSpeed);
            } else if (shieldOn == true) {
                currentAnimationName = "WALK_SHIELD_LEFT";
                moveXHandleCollision(-movementSpeed);
            }
            
            if (facingDirection == Direction.RIGHT && shieldOn == false) {
                currentAnimationName = "WALK_RIGHT";
                moveXHandleCollision(movementSpeed);
            } else if (shieldOn == false) {
                currentAnimationName = "WALK_LEFT";
                moveXHandleCollision(-movementSpeed);
            }

            // if space dog reaches the start or end location, it turns around
            // space dog may end up going a bit past the start or end location depending on movement speed
            // this calculates the difference and pushes the enemy back a bit so it ends up right on the start or end location
            if (getX1() + getWidth() >= endBound) {
                float difference = endBound - (getX2());
                moveXHandleCollision(-difference);
                facingDirection = Direction.LEFT;
            } else if (getX1() <= startBound) {
                float difference = startBound - getX1();
                moveXHandleCollision(difference);
                facingDirection = Direction.RIGHT;
            }

            
            
            // if space dog is shooting, it first turns red for 1 second
            // then the bones is actually shot out
//            if (previousSpaceDogState == SpaceDogState.WALK) {
//                shootTimer.setWaitTime(1000);
//                if (shieldOn == true) {
//                	currentAnimationName = facingDirection == Direction.RIGHT ? "SHOOT_SHIELD_RIGHT" : "SHOOT_SHIELD_LEFT";
//                }else {
//                	currentAnimationName = facingDirection == Direction.RIGHT ? "SHOOT_RIGHT" : "SHOOT_LEFT";
//                }
//            } else 
            
            
            
            //Shoots bone after set amount of time
            if (shootTimer.isTimeUp()) {

                // define where bones will spawn on map (x location) relative to space dog enemy's location
                // and define its movement speed
                int bonesX;
                float movementSpeed;
                if (facingDirection == Direction.RIGHT) {
                    bonesX = Math.round(getX()) + getWidth();
                    movementSpeed = 1.5f;
                } else {
                    bonesX = Math.round(getX());
                    movementSpeed = -1.5f;
                }

                // define where bones will spawn on the map (y location) relative to space dog enemy's location
                int bonesY = Math.round(getY()) + 20;

                // create bones enemy
                Bones bones = new Bones(new Point(bonesX, bonesY), movementSpeed, 2000);

                // add bones enemy to the map for it to offically spawn in the level
                map.addEnemy(bones);

                // change space dog back to its WALK state after shooting, reset shootTimer to wait another 2 seconds before shooting again
                spaceDogState = SpaceDogState.WALK;
                shootTimer.setWaitTime(2000);
            }
        }
        previousSpaceDogState = spaceDogState;
        if (dogLives <= 0) {
        	this.mapEntityStatus = MapEntityStatus.REMOVED;
        }
        super.update(player);
    }
    
//	public void hurtPlayer(Player player) {
//		
//		if (intersects(player) && shieldOn == true) {
//			currentAnimationName = facingDirection == Direction.RIGHT ? "WALK_SHIELD_RIGHT" : "WALK_SHIELD_LEFT";
//			shieldOn = false;
//		}
//		else if (shieldOn == false) {
//			currentAnimationName = facingDirection == Direction.RIGHT ? "WALK_RIGHT" : "WALK_LEFT";
//
//			Date date1 = new Date();
//			shieldTime = date1.getTime();
//			long temp1 = date1.getTime();
//			if (temp1 - shieldTime >= 5000) {
//			if (intersects(player) && PlayerTouchFlag == 0 ) {
//				// do a 3 seconds timer for time without shield
//				System.out.println(dogLives);
//				Date date = new Date();
//				PlayerTime = date.getTime();
//				dogLives--;
//				PlayerTouchFlag = 1;
//			}
//			
//			if (intersects(player) && PlayerTouchFlag == 1) {
//				
//				damageFlag = 1;
//				Date date = new Date();
//				long temp = date.getTime();
//				if (temp - PlayerTime >= 1000) {
//					// playerState = PlayerState.TAKING_DAMAGE;
//					PlayerTouchFlag = 0;
//					// System.out.println(playerState);
//					}
//				
//				
//			
//				}
//			}
//			shieldOn = true;
//		}
//		
//		
//		
//		
//	}


    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if space dog enemy collides with something on the x axis, it turns around and walks the other way
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
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
        	 put("WALK_SHIELD_LEFT_RED", new Frame[]{
                     new FrameBuilder(spriteSheet.getSprite(0, 2), 200)
                             .withScale((float) .7)
                             .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                             .withBounds(20, 20, 70, 60)
                             .build(),
                     new FrameBuilder(spriteSheet.getSprite(1, 2), 200)
                             .withScale((float) .7)
                             .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                             .withBounds(20, 20, 70, 60)
                             .build(),
                     new FrameBuilder(spriteSheet.getSprite(0, 2), 200)
                             .withScale((float) .7)
                             .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                             .withBounds(20, 20, 70, 60)
                             .build(),
                     new FrameBuilder(spriteSheet.getSprite(2, 0), 200)
                             .withScale((float) .7)
                             .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                             .withBounds(20, 20, 70, 60)
                             .build()
             });
        	 put("WALK_SHIELD_RIGHT_RED", new Frame[]{
                     new FrameBuilder(spriteSheet.getSprite(0, 2), 200)
                             .withScale((float) .7)
                             .withBounds(20, 20, 70, 60)
                             .build(),
                     new FrameBuilder(spriteSheet.getSprite(1, 2), 200)
                             .withScale((float) .7)
                             .withBounds(20, 20, 70, 60)
                             .build(),
                     new FrameBuilder(spriteSheet.getSprite(0, 2), 200)
                             .withScale((float) .7)
                             .withBounds(20, 20, 70, 60)
                             .build(),
            
                     new FrameBuilder(spriteSheet.getSprite(2, 0), 200)
                             .withScale((float) .7)

                             .withBounds(20, 20, 70, 60)
                             .build()
             });
        	 put("WALK_SHIELD_LEFT", new Frame[]{
                     new FrameBuilder(spriteSheet.getSprite(2, 3), 200)
                             .withScale((float) .7)
                             .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                             .withBounds(20, 20, 70, 60)
                             .build(),
                     new FrameBuilder(spriteSheet.getSprite(1, 3), 200)
                             .withScale((float) .7)
                             .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                             .withBounds(20, 20, 70, 60)
                             .build(),
                     new FrameBuilder(spriteSheet.getSprite(2, 3), 200)
                             .withScale((float) .7)
                             .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                             .withBounds(20, 20, 70, 60)
                             .build(),
            
                     new FrameBuilder(spriteSheet.getSprite(1, 0), 200)
                             .withScale((float) .7)
                             .withImageEffect(ImageEffect.FLIP_HORIZONTAL)

                             .withBounds(20, 20, 70, 60)
                             .build()
             });
        	 put("WALK_SHIELD_RIGHT", new Frame[]{
                     new FrameBuilder(spriteSheet.getSprite(2, 3), 200)
                             .withScale((float) .7)
                             .withBounds(20, 20, 70, 60)
                             .build(),
                     new FrameBuilder(spriteSheet.getSprite(1, 3), 200)
                             .withScale((float) .7)
                             .withBounds(20, 20, 70, 60)
                             .build(),
                     new FrameBuilder(spriteSheet.getSprite(2, 3), 200)
                             .withScale((float) .7)
                             .withBounds(20, 20, 70, 60)
                             .build(),            
                     new FrameBuilder(spriteSheet.getSprite(1, 0), 200)
                             .withScale((float) .7)
                             .withBounds(20, 20, 70, 60)
                             .build()
             });
        	 
        	 
            put("WALK_LEFT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 200)
                            .withScale((float) .7)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(20, 20, 70, 60)
                            .build(),
           
                    new FrameBuilder(spriteSheet.getSprite(0, 3), 200)
                            .withScale((float) .7)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(20, 20, 70, 60)
                            .build()
            });

            put("WALK_RIGHT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 200)
                            .withScale((float) .7)
                            //.withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(20, 20, 70, 60)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 3), 200)
                            .withScale((float) .7)
                           // .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(20, 20, 70, 60)
                            .build()
            });


            

            put("SHOOT_LEFT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale((float) .7)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(20, 20, 70, 60)
                            .build(),
            });

            put("SHOOT_RIGHT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale((float) .7)
                            //.withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(20, 20, 70, 60)
                            .build(),
            });
            
            put("SHOOT_SHIELD_LEFT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(2, 3))
                            .withScale((float) .7)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(20, 20, 70, 60)
                            .build(),
            });

            put("SHOOT_SHIELD_RIGHT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(2, 3))
                            .withScale((float) .7)
                            //.withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(20, 20, 70, 60)
                            .build(),
            });
            
            put("WALK_HURT_LEFT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 200)
                            .withScale((float) .7)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(20, 20, 70, 60)
                            .build(),
           
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 200)
                            .withScale((float) .7)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(20, 20, 70, 60)
                            .build()
            });

            put("WALK_HURT_RIGHT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 200)
                            .withScale((float) .7)
                            //.withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(20, 20, 70, 60)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 200)
                            .withScale((float) .7)
                           // .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(20, 20, 70, 60)
                            .build()
            });
            
        };
    };
    }
    public enum SpaceDogState {
        WALK, SHOOT
    }
    
    public static int getDogStatus() {
    	return dogLives;
    }
    public static void setDogStatus(int x) {
    	dogLives = x;
    }
    
    public static void setDogLives(int doglives) {
    	 dogLives = doglives;
    }
    
}


