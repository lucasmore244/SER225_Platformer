package Level;

import Engine.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import Level.Map;
import Screens.PlayLevelScreen;
import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Enemies.Bones;
import Enemies.CatProjectile;
import Enemies.DinosaurEnemy;
import Enemies.SpaceDog1;
import Enemies.Fireball;
import Enemies.Laser;
import Enemies.RatEnemy;
import Enemies.UFO;
import Enemies.UFOFireball;
import Enemies.DinosaurEnemy.DinosaurState;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Sound;
import EnhancedMapTiles.Mushrooms;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Players.Cat;
import Players.CatLevel3;
import Players.SpaceshipLevel2;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;
import Utils.Stopwatch;
import Level.MapEntity;
import java.awt.Color;
import java.util.ArrayList;

public abstract class Player extends GameObject {
	// values that affect player movement
	// these should be set in a subclass
	protected float walkSpeed = 0;
	protected float gravity = 0;
	protected float jumpHeight = 0;
	protected float jumpDegrade = 0;
	protected float terminalVelocityY = 0;
	protected float momentumYIncrease = 0;
	// values used to handle player movement
	protected float jumpForce = 0;
	protected float momentumY = 0;
	protected float moveAmountX, moveAmountY;
	protected float lastAmountMovedX, lastAmountMovedY;
	// values used to keep track of player's current state
	protected PlayerState playerState;
	protected PlayerState previousPlayerState;
	protected PlayerState playerShootState;
	protected Direction facingDirection;
	protected AirGroundState airGroundState;
	protected AirGroundState previousAirGroundState;
	protected LevelState levelState;
	protected PlayLevelScreen playscreen = new PlayLevelScreen(null);
	protected Stopwatch cooldown = new Stopwatch();
	private Stopwatch reloadTimeBossFight = new Stopwatch();
	protected int levelNum = 0;
	protected int currentMap = 0;
	protected int playerHealth = 0;
	// protected Calendar c = Calendar.getInstance();
	protected int waterFlag = 0;
	protected long waterTime = 0;
	protected int monsterTouchFlag = 0;
	protected long monsterTime = 0;
	protected int mushroomFlag = 0;
	protected long mushroomTime = 0;
	protected int damageFlag = 0;
	protected long damageTime = 0;
	// classes that listen to player events can be added to this list
	protected ArrayList<PlayerListener> listeners = new ArrayList<>();
	// define keys
	protected KeyLocker keyLocker = new KeyLocker();
	protected Key JUMP_KEY = Key.UP;
	protected Key MOVE_LEFT_KEY = Key.LEFT;
	protected Key MOVE_RIGHT_KEY = Key.RIGHT;
	protected Key CROUCH_KEY = Key.DOWN;
	protected Key SHOOT_KEY = Key.Q;
	// flags
	protected boolean isInvincible = false; // if true, player cannot be hurt by enemies (good for testing)

	public Player(SpriteSheet spriteSheet, float x, float y, String startingAnimationName) {
		super(spriteSheet, x, y, startingAnimationName);
		facingDirection = Direction.RIGHT;
		airGroundState = AirGroundState.AIR;
		previousAirGroundState = airGroundState;
		playerState = PlayerState.STANDING;
		previousPlayerState = playerState;
		levelState = LevelState.RUNNING;
		cooldown.setWaitTime(500);
		reloadTimeBossFight.setWaitTime(1000);
	}

	public void update() {
		moveAmountX = 0;
		moveAmountY = 0;
		// if player is currently playing through level (has not won or lost)
		if (levelState == LevelState.RUNNING) {
			applyGravity();
			// update player's state and current actions, which includes things like
			// determining how much it should move each frame and if its walking or jumping
			do {
				previousPlayerState = playerState;
				handlePlayerState();
			} while (previousPlayerState != playerState);
			previousAirGroundState = airGroundState;
			// move player with respect to map collisions based on how much player needs to
			// move this frame
			lastAmountMovedY = super.moveYHandleCollision(moveAmountY);
			lastAmountMovedX = super.moveXHandleCollision(moveAmountX);
			handlePlayerAnimation();
			updateLockedKeys();
			// update player's animation
			super.update();
		}
		// if player has beaten level
		else if (levelState == LevelState.LEVEL_COMPLETED) {
			updateLevelCompleted();
		}
		// if player has lost level
		else if (levelState == LevelState.PLAYER_DEAD) {
			updatePlayerDead();
		}
	}

	// add gravity to player, which is a downward force
	protected void applyGravity() {
		moveAmountY += gravity + momentumY;
	}

	// based on player's current state, call appropriate player state handling
	// method
	protected void handlePlayerState() {
		if (currentMap == 2) {
			playerLevel2();
		} else {
			switch (playerState) {
			case STANDING:
				playerStanding();
				break;
			case WALKING:
				playerWalking();
				break;
			case CROUCHING:
				playerCrouching();
				break;
			case JUMPING:
				playerJumping();
				break;
			}
		}
	}

	// player STANDING state logic
	protected void playerStanding() {
		// if walk left or walk right key is pressed, player enters WALKING state
		if (Keyboard.isKeyDown(MOVE_LEFT_KEY) || Keyboard.isKeyDown(MOVE_RIGHT_KEY)) {
			playerState = PlayerState.WALKING;
		}
		// if jump key is pressed, player enters JUMPING state
		else if (Keyboard.isKeyDown(JUMP_KEY) && !keyLocker.isKeyLocked(JUMP_KEY)) {
			keyLocker.lockKey(JUMP_KEY);
			playerState = PlayerState.JUMPING;
		}
		// if crouch key is pressed, player enters CROUCHING state
		else if (Keyboard.isKeyDown(CROUCH_KEY)) {
			playerState = PlayerState.CROUCHING;
		}
	}

	// player WALKING state logic
	protected void playerWalking() {
		// if walk left key is pressed, move player to the left
		if (Keyboard.isKeyDown(MOVE_LEFT_KEY)) {
			moveAmountX -= walkSpeed;
			facingDirection = Direction.LEFT;
		}
		// if walk right key is pressed, move player to the right
		else if (Keyboard.isKeyDown(MOVE_RIGHT_KEY)) {
			moveAmountX += walkSpeed;
			facingDirection = Direction.RIGHT;
		} else if (Keyboard.isKeyUp(MOVE_LEFT_KEY) && Keyboard.isKeyUp(MOVE_RIGHT_KEY)) {
			playerState = PlayerState.STANDING;
		}
		// if jump key is pressed, player enters JUMPING state
		if (Keyboard.isKeyDown(JUMP_KEY) && !keyLocker.isKeyLocked(JUMP_KEY)) {
			keyLocker.lockKey(JUMP_KEY);
			playerState = PlayerState.JUMPING;
		}
		// if crouch key is pressed,
		else if (Keyboard.isKeyDown(CROUCH_KEY)) {
			playerState = PlayerState.CROUCHING;
		}
	}

	// player CROUCHING state logic
	protected void playerCrouching() {
		// if crouch key is released, player enters STANDING state
		if (Keyboard.isKeyUp(CROUCH_KEY)) {
			playerState = PlayerState.STANDING;
		}
		// if jump key is pressed, player enters JUMPING state
		if (Keyboard.isKeyDown(JUMP_KEY) && !keyLocker.isKeyLocked(JUMP_KEY)) {
			keyLocker.lockKey(JUMP_KEY);
			playerState = PlayerState.JUMPING;
		}
	}

	// player JUMPING state logic

	protected void playerJumping() {
		// if last frame player was on ground and this frame player is still on ground,
		// the jump needs to be setup
		if (previousAirGroundState == AirGroundState.GROUND && airGroundState == AirGroundState.GROUND) {
			Sound.play(8);
			// sets animation to a JUMP animation based on which way player is facing
			currentAnimationName = facingDirection == Direction.RIGHT ? "JUMP_RIGHT" : "JUMP_LEFT";
			// player is set to be in air and then player is sent into the air
			airGroundState = AirGroundState.AIR;
			jumpForce = jumpHeight;
			if (Math.abs(jumpForce) > 0) {
				moveAmountY -= jumpForce;
				jumpForce -= jumpDegrade;
				if (jumpForce < 0) {
					jumpForce = 0;
				}
			}
		}
		// if player is in air (currently in a jump) and has more jumpForce, continue
		// sending player upwards
		else if (airGroundState == AirGroundState.AIR) {
			if (jumpForce > 0) {
				moveAmountY -= jumpForce;
				jumpForce -= jumpDegrade;
				if (jumpForce < 0) {
					jumpForce = 0;
				}
			}
			// allows you to move left and right while in the air
			if (Keyboard.isKeyDown(MOVE_LEFT_KEY)) {
				moveAmountX -= walkSpeed;
			} else if (Keyboard.isKeyDown(MOVE_RIGHT_KEY)) {
				moveAmountX += walkSpeed;
			}
			// if player is falling, increases momentum as player falls so it falls faster
			// over time
			if (moveAmountY > 0) {
				increaseMomentum();
			}
			if (Keyboard.isKeyDown(SHOOT_KEY) && reloadTimeBossFight.isTimeUp() && currentMap == 4) {
				CatProjectile poop = new CatProjectile(getLocation(), 10, 4000);
				 Sound.play(14);
				map.addEnemy(poop);
				reloadTimeBossFight.setWaitTime(1000);
			}
		}
		// if player last frame was in air and this frame is now on ground, player
		// enters STANDING state
		else if (previousAirGroundState == AirGroundState.AIR && airGroundState == AirGroundState.GROUND) {
			playerState = PlayerState.STANDING;
		}
		// allows you to move left and right while in the air
		// if player is falling, increases momentum as player falls so it falls faster
		// over time
		if (moveAmountY > 0) {
			increaseMomentum();
		}
		// If player is in the air this will shoot down (for boss fight)
		if (Keyboard.isKeyDown(SHOOT_KEY) && reloadTimeBossFight.isTimeUp() && currentMap == 4) {
			CatProjectile poop = new CatProjectile(getLocation(), 10, 4000);
			map.addEnemy(poop);
			reloadTimeBossFight.setWaitTime(100);
		}
		// if player last frame was in air and this frame is now on ground, player
		// enters STANDING state
		if (previousAirGroundState == AirGroundState.AIR && airGroundState == AirGroundState.GROUND) {
			playerState = PlayerState.STANDING;
		}
	}
	
	//system to handle al player movement in level 2

    protected void playerLevel2() {
    	//this runs off of a lot of principles that were already established in this code
    	//player is always in jumping state
    	playerState = PlayerState.JUMPING;
    	if (damageFlag == 0 ) {
            currentAnimationName = facingDirection == Direction.RIGHT ? "JUMP_RIGHT" : "JUMP_LEFT";
    	}
        else {
            this.currentAnimationName = facingDirection == Direction.RIGHT ? "JUMP_RIGHT_RED" : "JUMP_LEFT_RED";
            Date date = new Date();
        	long temp = date.getTime();
			if (temp - damageTime >= 600) {
				damageFlag = 0;
			}
		}
		if (Keyboard.isKeyDown(JUMP_KEY)) {
			// player is set to be in air and then player is sent into the air
			airGroundState = AirGroundState.AIR;
			jumpForce = jumpHeight;
			if (Math.abs(jumpForce) > 0) {
				moveAmountY -= jumpForce;
				jumpForce -= jumpDegrade;
				if (jumpForce < 0) {
					jumpForce = 0;
				}
			}
			applyGravity();
		}
		// makes player go down
		if (Keyboard.isKeyDown(CROUCH_KEY)) {
			airGroundState = AirGroundState.AIR;
			jumpForce = jumpHeight;
			if (Math.abs(jumpForce) > 0) {
				moveAmountY += jumpForce;
				jumpForce += jumpDegrade;
				if (jumpForce < 0) {
					jumpForce = 0;
				}
			}
			applyGravity();
		}
		//allows the player to shoot when they hit Q and seperates shots
		if (Keyboard.isKeyDown(SHOOT_KEY) && cooldown.isTimeUp()) {
			 Sound.play(12);
			Laser laser = new Laser(getLocation(), 4, 4000);
			map.addEnemy(laser);
			cooldown.setWaitTime(700);
		}
	}

	// while player is in air, this is called, and will increase momentumY by a set
	// amount until player reaches terminal velocity
	protected void increaseMomentum() {
		momentumY += momentumYIncrease;
		if (momentumY > terminalVelocityY) {
			momentumY = terminalVelocityY;
		}
	}

	protected void updateLockedKeys() {
		if (Keyboard.isKeyUp(JUMP_KEY)) {
			keyLocker.unlockKey(JUMP_KEY);
		}
	}

	// anything extra the player should do based on interactions can be handled here
	protected void handlePlayerAnimation() {
		if (playerState == PlayerState.STANDING) {
			if (damageFlag == 0) {
				this.currentAnimationName = facingDirection == Direction.RIGHT ? "STAND_RIGHT" : "STAND_LEFT";
			} else {
				this.currentAnimationName = facingDirection == Direction.RIGHT ? "STAND_RIGHT_RED" : "STAND_LEFT_RED";
				Date date = new Date();
				long temp = date.getTime();
				if (temp - damageTime >= 800) {
					damageFlag = 0;
				}
			}
			// sets animation to a STAND animation based on which way player is facing
			// this.currentAnimationName = facingDirection == Direction.RIGHT ?
			// "STAND_RIGHT" : "STAND_LEFT";
			// handles putting goggles on when standing in water
			// checks if the center of the player is currently touching a water tile
			int centerX = Math.round(getBounds().getX1()) + Math.round(getBounds().getWidth() / 2f);
			int centerY = Math.round(getBounds().getY1()) + Math.round(getBounds().getHeight() / 2f);
			MapTile currentMapTile = map.getTileByPosition(centerX, centerY);
			if (currentMapTile != null && currentMapTile.getTileType() == TileType.WATERLEVEL3) {
				playerHealth = 0;
				levelState = LevelState.PLAYER_DEAD;
			}
			if (currentMapTile != null && currentMapTile.getTileType() == TileType.WATER && waterFlag == 0) {
				playerHealth--;
				 Sound.play(10);
				Date date = new Date();
				waterTime = date.getTime();
				waterFlag = 1;
				if (playerHealth == 0) {
					levelState = LevelState.PLAYER_DEAD;
				}
			}
			if (currentMapTile != null && currentMapTile.getTileType() == TileType.MUSHROOM && mushroomFlag == 0) {
				Date date = new Date();
				mushroomTime = date.getTime();
				mushroomFlag = 1;
				playerHealth--;
				if (playerHealth == 0) {
					levelState = LevelState.PLAYER_DEAD;
				}
			}
			if (currentMapTile != null && currentMapTile.getTileType() == TileType.WATER && waterFlag == 1) {
				this.currentAnimationName = facingDirection == Direction.RIGHT ? "STAND_RIGHT_RED" : "STAND_LEFT_RED";
				Date date = new Date();
				long temp = date.getTime();
				if (temp - waterTime >= 500) {
					waterFlag = 0;
				}
			}
			if (currentMapTile != null && currentMapTile.getTileType() == TileType.MUSHROOM && mushroomFlag == 1) {
				Date date = new Date();
				long temp = date.getTime();
				if (temp - mushroomTime >= 1000) {
					mushroomFlag = 0;
				}
			}
		} else if (playerState == PlayerState.WALKING) {
			if (damageFlag == 0) {
				this.currentAnimationName = facingDirection == Direction.RIGHT ? "WALK_RIGHT" : "WALK_LEFT";
			} else {
				this.currentAnimationName = facingDirection == Direction.RIGHT ? "WALK_RIGHT_RED" : "WALK_LEFT_RED";
				Date date = new Date();
				long temp = date.getTime();
				if (temp - damageTime >= 800) {
					damageFlag = 0;
				}
			}
			// sets animation to a WALK animation based on which way player is facing
			int centerX = Math.round(getBounds().getX1()) + Math.round(getBounds().getWidth() / 2f);
			int centerY = Math.round(getBounds().getY1()) + Math.round(getBounds().getHeight() / 2f);
			MapTile currentMapTile = map.getTileByPosition(centerX, centerY);
			if (currentMapTile != null && currentMapTile.getTileType() == TileType.WATERLEVEL3) {
				playerHealth = 0;
				levelState = LevelState.PLAYER_DEAD;
			}
			if (currentMapTile != null && currentMapTile.getTileType() == TileType.WATER && waterFlag == 0) {
				playerHealth--;
				Date date = new Date();
				waterTime = date.getTime();
				waterFlag = 1;
				if (playerHealth == 0) {
					levelState = LevelState.PLAYER_DEAD;
				}
			}
			if (currentMapTile != null && currentMapTile.getTileType() == TileType.WATER && waterFlag == 1) {
				this.currentAnimationName = facingDirection == Direction.RIGHT ? "WALK_RIGHT_RED" : "WALK_LEFT_RED";
				Date date = new Date();
				long temp = date.getTime();
				if (temp - waterTime >= 500) {
					waterFlag = 0;
				}
			} else if (playerState == PlayerState.CROUCHING) {
				// sets animation to a CROUCH animation based on which way player is facing
				this.currentAnimationName = facingDirection == Direction.RIGHT ? "CROUCH_RIGHT" : "CROUCH_LEFT";
			} else if (playerState == PlayerState.JUMPING) {
				if (damageFlag == 0) {
					this.currentAnimationName = facingDirection == Direction.RIGHT ? "JUMP_RIGHT" : "JUMP_LEFT";
				} else {
					this.currentAnimationName = facingDirection == Direction.RIGHT ? "JUMP_RIGHT_RED" : "JUMP_LEFT_RED";
					Date date = new Date();
					long temp = date.getTime();
					if (currentMap == 1) {
						if (temp - damageTime >= 600) {
							damageFlag = 0;
						}
					}
					if (temp - damageTime >= 800) {
						damageFlag = 0;
					}
				}
				// if player is moving upwards, set player's animation to jump. if player moving
				// downwards, set player's animation to fall
				if (lastAmountMovedY <= 0) {
					this.currentAnimationName = facingDirection == Direction.RIGHT ? "JUMP_RIGHT" : "JUMP_LEFT";
				} else {
					this.currentAnimationName = facingDirection == Direction.RIGHT ? "FALL_RIGHT" : "FALL_LEFT";
				}
			}
		}
	}

	@Override
	public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
	}

	@Override
	public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
		// if player collides with a map tile below it, it is now on the ground
		// if player does not collide with a map tile below, it is in air
		if (direction == Direction.DOWN) {
			if (hasCollided) {
				momentumY = 0;
				airGroundState = AirGroundState.GROUND;
			} else {
				playerState = PlayerState.JUMPING;
				airGroundState = AirGroundState.AIR;
			}
		}
		// if player collides with map tile upwards, it means it was jumping and then
		// hit into a ceiling -- immediately stop upwards jump velocity
		else if (direction == Direction.UP) {
			if (hasCollided) {
				jumpForce = 0;
			}
		}
	}

	// other entities can call this method to hurt the player
	public void hurtPlayer(MapEntity mapEntity) {
		//System.out.println(monsterTouchFlag);
		if (!isInvincible) {
			// if map entity is an enemy, kill player on touch
			if (mapEntity instanceof Enemy && monsterTouchFlag == 0) {
				if (playscreen.getCurrentMap() != 2) {
					Sound.play(10);
				} else {
					Sound.play(9);
				}
				Date date = new Date();
				monsterTime = date.getTime();
				playerHealth--;
				monsterTouchFlag = 1;
				
				// drop life
				if (playerHealth == 0) {
					levelState = LevelState.PLAYER_DEAD;
					if (currentMap == 1) {
						levelState = LevelState.PLAYER_DEAD;
						for (PlayerListener listener : listeners) {
							listener.onDeath();
						}
					}
				}
			}
			
			if (mapEntity instanceof Enemy && monsterTouchFlag == 1) {
				damageFlag = 1;
				Date date = new Date();
				long temp = date.getTime();
				if (temp - monsterTime >= 1000) {
					monsterTouchFlag = 0;
				}
			}
			if (mapEntity instanceof Fireball || mapEntity instanceof Bones) {
				monsterTouchFlag = 0;
			}
		}
	}

	// other entities can call this to tell the player they beat a level
	public void completeLevel() {
		levelState = LevelState.LEVEL_COMPLETED;
	}

	// if player has beaten level, this will be the update cycle
	public void updateLevelCompleted() {
		// if player is not on ground, player should fall until it touches the ground
		if (airGroundState != AirGroundState.GROUND && map.getCamera().containsDraw(this)) {
			currentAnimationName = "FALL_RIGHT";
			applyGravity();
			increaseMomentum();
			super.update();
			moveYHandleCollision(moveAmountY);
		}
		// move player to the right until it walks off screen
		else if (map.getCamera().containsDraw(this)) {
			currentAnimationName = "WALK_RIGHT";
			super.update();
			moveXHandleCollision(walkSpeed);
		} else {
			// tell all player listeners that the player has finished the level
			for (PlayerListener listener : listeners) {
				listener.onLevelCompleted();
			}
		}
	}


    // if player has died, this will be the update cycle
    public void updatePlayerDead() {
        // change player animation to DEATH
    	if (currentMap == 2) {
    		for (PlayerListener listener : listeners) {
                listener.onDeath();
            }
    	}
        if (!currentAnimationName.startsWith("DEATH")) {
            if (facingDirection == Direction.RIGHT) {
                currentAnimationName = "DEATH_RIGHT";
            } else {
                currentAnimationName = "DEATH_LEFT";
            }
            super.update();
        }
        // if death animation not on last frame yet, continue to play out death animation
        else if (currentFrameIndex != getCurrentAnimation().length - 1) {
          super.update();
        }
        // if death animation on last frame (it is set up not to loop back to start), player should continually fall until it goes off screen
        else if (currentFrameIndex == getCurrentAnimation().length - 1) {
            if (map.getCamera().containsDraw(this)) {
                moveY(3);
            } else {
                // tell all player listeners that the player has died in the level
                for (PlayerListener listener : listeners) {
                    listener.onDeath();
                    
                }
            }
        }
    }
    public int getPlayerhealth(){
    	return playerHealth;
    }
    
    public LevelState getLevelState() {
        return levelState;
    }
    

    
    public int getCurrentMap() {
    	return currentMap;
    }

	public void setPlayerHealth(int playerHealth) {
		this.playerHealth = playerHealth;
	}

	public PlayerState getPlayerState() {
		return playerState;
	}

	public void setPlayerState(PlayerState playerState) {
		this.playerState = playerState;
	}

	public AirGroundState getAirGroundState() {
		return airGroundState;
	}

	public Direction getFacingDirection() {
		return facingDirection;
	}

	public AirGroundState setAirGroundState(AirGroundState airGroundState) {
		return airGroundState;
	}

	public void setFacingDirection(Direction facingDirection) {
		this.facingDirection = facingDirection;
	}

	public void setLevelState(LevelState levelState) {
		this.levelState = levelState;
	}

	public void addListener(PlayerListener listener) {
		listeners.add(listener);
	}

	public void setLevelMap(int currentMap) {
		this.currentMap = currentMap;
	}
}