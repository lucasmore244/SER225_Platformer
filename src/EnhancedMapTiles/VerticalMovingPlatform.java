package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import GameObject.Rectangle;
import Level.EnhancedMapTile;
import Level.Player;
import Level.TileType;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;

import java.awt.image.BufferedImage;

// This class is for a horizontal moving platform
// the platform will move back and forth between its start location and end location
// if the player is standing on top of it, the player will be moved the same amount as the platform is moving (so the platform will not slide out from under the player)
public class VerticalMovingPlatform extends EnhancedMapTile {
    private Point startLocation;
    private Point endLocation;
    private float movementSpeed = 1f;
    private Direction startDirection;
    private Direction direction;

    public VerticalMovingPlatform(BufferedImage image, Point startLocation, Point endLocation, TileType tileType, float scale, Rectangle bounds, Direction startDirection) {
        super(startLocation.x, startLocation.y, new FrameBuilder(image).withBounds(bounds).withScale(scale).build(), tileType);
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startDirection = startDirection;
        this.initialize();
    }

    @Override
    public void initialize() {
        super.initialize();
        direction = startDirection;
    }

    @Override
    public void update(Player player) {
        float startBound = startLocation.y;
        float endBound = endLocation.y;

        // move platform Up or Down based on its current direction
        int moveAmountY = 0;
        if (direction == Direction.RIGHT) {
            moveAmountY += movementSpeed;
        } else if (direction == Direction.LEFT) {
            moveAmountY -= movementSpeed;
        }

        moveY(moveAmountY);

        // if platform reaches the start or end location, it turns around
        // platform may end up going a bit past the start or end location depending on movement speed
        // this calculates the difference and pushes the platform back a bit so it ends up right on the start or end location
        if (getY1() + getWidth() >= endBound) {
            float difference = endBound - (getY1() + getWidth());
            moveY(-difference);
            moveAmountY -= difference;
            direction = Direction.LEFT;
        } else if (getY1() <= startBound) {
            float difference = startBound - getY1();
            moveY(difference);
            moveAmountY += difference;
            direction = Direction.RIGHT;
        }

        // if tile type is NOT PASSABLE, if the platform is moving and hits into the player (x axis), it will push the player
        if (tileType == TileType.NOT_PASSABLE) {
            if (intersects(player) && moveAmountY >= 0 && player.getBoundsY1() <= getBoundsY2()) {
                player.moveYHandleCollision(getBoundsY2() - player.getBoundsY1());
            } else if (intersects(player) && moveAmountY <= 0 && player.getBoundsY2() >= getBoundsY1()) {
                player.moveYHandleCollision(getBoundsY1() - player.getBoundsY2());
            }
        }

        // if player is on standing on top of platform, move player by the amount the platform is moving
        // this will cause the player to "ride" with the moving platform
        // without this code, the platform would slide right out from under the player
        if (overlaps(player) && player.getBoundsY2() == getBoundsY1() && player.getAirGroundState() == AirGroundState.GROUND) {
            player.moveYHandleCollision(moveAmountY);
        }

        super.update(player);
    }

    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        //super.drawBounds(graphicsHandler, new Color(0, 0, 255, 100));
    }

}
