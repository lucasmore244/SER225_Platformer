package Players;

import java.util.HashMap;
import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.PlayerLevel3;
import Level.PlayerState;
import Engine.Key;
import java.util.Calendar;
import java.util.Date;

import Builders.MapTileBuilder;
import Engine.KeyLocker;
import Engine.Keyboard;
import GameObject.GameObject;
import Players.Cat;
import Utils.AirGroundState;
import Utils.Direction;
import Level.MapEntity;
import Level.Player;

import java.awt.Color;
import java.util.ArrayList;


public class CatLevel3 extends Player {

	public CatLevel3(float x, float y) {
        super(new SpriteSheet(ImageLoader.load("CatUpsideDown.png"), 24, 24), x, y, "STAND_RIGHT");
        gravity = -.8f;
        terminalVelocityY = -6f;
        jumpHeight = -16.5f;
        jumpDegrade = -.5f;
        walkSpeed = 2.3f;
        momentumYIncrease = -.15f;
        playerHealth = 5;
    }	 
	
	
	public void update() {
        super.update();
    }

    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        // drawBounds(graphicsHandler, new Color(255, 0, 0, 170));
    }
    protected void increaseMomentum() {
        momentumY -= momentumYIncrease;
        if (momentumY < terminalVelocityY) {
            momentumY = terminalVelocityY;
        }
    }

    protected void playerJumping() {
    	
        // if last frame player was on ground and this frame player is still on ground, the jump needs to be setup
        if (previousAirGroundState == AirGroundState.GROUND && airGroundState == AirGroundState.GROUND) {

        	//System.out.println("Jump:" + this.gravity);
            // sets animation to a JUMP animation based on which way player is facing
            currentAnimationName = facingDirection == Direction.RIGHT ? "JUMP_RIGHT" : "JUMP_LEFT";

            // player is set to be in air and then player is sent into the air
            
            airGroundState = AirGroundState.AIR;
            jumpForce = jumpHeight;
            if (jumpForce < 0) {
                moveAmountY -= jumpForce;
                jumpForce -= jumpDegrade;
                if (jumpForce > 0) {
                    jumpForce = 0;
                }
            }
        }

        // if player is in air (currently in a jump) and has more jumpForce, continue sending player upwards
        else if (airGroundState == AirGroundState.AIR) {
            if (jumpForce < 0) {
                moveAmountY -= jumpForce;
                jumpForce -= jumpDegrade;
                if (jumpForce > 0) {
                    jumpForce = 0;
                }
            }

            // allows you to move left and right while in the air
            if (Keyboard.isKeyDown(MOVE_LEFT_KEY)) {
                moveAmountX -= walkSpeed;
            } else if (Keyboard.isKeyDown(MOVE_RIGHT_KEY)) {
                moveAmountX += walkSpeed;
            }

            // if player is falling, increases momentum as player falls so it falls faster over time
            if (moveAmountY <= 0) {
                increaseMomentum();
            }
        }

        // if player last frame was in air and this frame is now on ground, player enters STANDING state
        else if (previousAirGroundState == AirGroundState.AIR && airGroundState == AirGroundState.GROUND) {
            playerState = PlayerState.STANDING;
        }
    }
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
    // if player collides with a map tile above it, it is now on the ground
    // if player does not collide with a map tile above, it is in air
	
    	//System.out.println(airGroundState + " " + hasCollided + " " + moveAmountY + " " + direction  + " " + entityCollidedWith);
    	if (direction == Direction.UP) {
    		if (hasCollided) {
    			momentumY = 0;
    			airGroundState = AirGroundState.GROUND;
    		} else {
    			playerState = PlayerState.JUMPING;
    			airGroundState = AirGroundState.AIR;
        }
    }	

    // if player collides with map tile downwards, it means it was jumping and then hit into a ceiling -- immediately stop upwards jump velocity
    	else if (direction == Direction.DOWN) {
    		if (hasCollided) {
    			jumpForce = 0;
    		}
    	}
	}
    protected void applyGravity() {  
    	
    	//if (airGroundState != AirGroundState.GROUND) {
    		moveAmountY += gravity - momentumY;	
    	//}
    	
    	
    	
	
}
    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(6, 3))
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });
            

            put("STAND_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(6, 3))
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("STAND_LEFT_RED", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(6, 2))
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });
            put("STAND_RIGHT_RED", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(6, 2))
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });
            put("WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(5, 3), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(5, 2), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(5, 1), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(5, 0), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(5, 3), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(5, 2), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(5, 1), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(5, 0), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });
            
            
            put("WALK_LEFT_RED", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(6, 1), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(6, 0), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(6, 1), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(6, 0), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("WALK_RIGHT_RED", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(6, 1), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(6, 0), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(6, 1), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(6, 0), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("JUMP_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(4, 3))
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("JUMP_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(4, 3))
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });
            put("JUMP_LEFT_RED", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(4, 2))
                    .withScale(3)
                    .withBounds(8, 9, 8, 9)
                    .build()
            });
            put("JUMP_RIGHT_RED", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(4, 2))
                    .withScale(3)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(8, 9, 8, 9)
                    .build()
            });
            
            put("FALL_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(3, 3))
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("FALL_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(3, 3))
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });
            
            put("FALL_LEFT_RED", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(4, 1))
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });
            put("FALL_RIGHT_RED", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(4, 1))
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });
            put("CROUCH_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 3))
                            .withScale(3)
                            .withBounds(8, 12, 8, 6)
                            .build()
            });

            put("CROUCH_LEFT_RED", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(4, 0))
                            .withScale(3)
                            .withBounds(8, 12, 8, 6)
                            .build()
            });
            put("CROUCH_RIGHT_RED", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(4, 0))
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 12, 8, 6)
                            .build()
            });
            put("CROUCH_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 3))
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 12, 8, 6)
                            .build()
            });

            put("DEATH_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(5, 3), 100)
                            .withScale(3)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(5, 2), 100)
                            .withScale(3)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(5, 1), -1)
                            .withScale(3)
                            .build()
            });

            put("DEATH_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(5, 3), 100)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(5, 2), 100)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(5, 1), -1)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });

            put("SWIM_STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(6, 0))
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("SWIM_STAND_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(6, 0))
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });
        }};
    }
	
    
}
	


