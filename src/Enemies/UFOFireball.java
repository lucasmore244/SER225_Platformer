package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntity;
import Level.MapEntityStatus;
import Level.Player;
import Utils.Direction;
import Utils.Point;
import Utils.Stopwatch;
import java.util.Random;

import java.util.HashMap;
import java.util.Random;

// This class is for the fireball enemy that the DinosaurEnemy class shoots out
// it will travel in a straight line (x axis) for a set time before disappearing
// it will disappear early if it collides with a solid map tile
public class UFOFireball extends Enemy {
    private float movementSpeed;
    private Stopwatch existenceTimer = new Stopwatch();
    private float diagonalBall = 0;
    Random rand = new Random();
    private float rando = rand.nextFloat()-0.5f*10;

    public UFOFireball(Point location, float movementSpeed, int existenceTime) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Fireball.png"), 7, 7), "DEFAULT");
        this.movementSpeed = movementSpeed;

        // how long the fireball will exist for before disappearing
        existenceTimer.setWaitTime(existenceTime);

        // this enemy will not respawn after it has been removed
        isRespawnable = false;

        initialize();
    }

    @Override
    public void update(Player player) {
        // if timer is up, set map entity status to REMOVED
        // the camera class will see this next frame and remove it permanently from the map
        if (existenceTimer.isTimeUp()) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        } else {
            // move fireball forward
            moveYHandleCollision(movementSpeed);
            // determines if fireball goes on a diagonal
            if(diagonalBall == 0) {
            	moveXHandleCollision(0);
            } else if (diagonalBall == 1) {
            	moveXHandleCollision(movementSpeed);
            } else if (diagonalBall == 2){
            	moveXHandleCollision(-movementSpeed);
            }else if (diagonalBall == 3){
            	moveXHandleCollision(movementSpeed/4);
            }else if (diagonalBall == 4){
            	moveXHandleCollision(-movementSpeed/4);
            }
            
            super.update(player);
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
    public void touchedPlayer(Player player) {
        // if fireball touches player, it disappears
        super.touchedPlayer(player);
        this.mapEntityStatus = MapEntityStatus.REMOVED;
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
    
    // Allows for ufo to shoot a fireball at a diagonal (0 = straight, 1 = diagonal, 2 = diagonal on other side)
    public void setDiagonal(float isDiagonal) {
    	diagonalBall = isDiagonal;
    }
}
