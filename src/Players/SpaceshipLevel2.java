package Players;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Player;
import Players.CatLevel3;

import java.util.HashMap;
// This is the class for the Cat player character
// basically just sets some values for physics and then defines animations
public class SpaceshipLevel2 extends Player {

    public SpaceshipLevel2(float x, float y) {
        super(new SpriteSheet(ImageLoader.load("Rocket.png"), 49, 35), x, y, "STAND_RIGHT");
        gravity = .5f;
        terminalVelocityY = 6f;
        jumpHeight = 14.5f;
        jumpDegrade = .5f;
        walkSpeed =2.3f;
        momentumYIncrease = .5f;
        playerHealth = 99;

    }

    public void update() {
        super.update();
    }

    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        // drawBounds(graphicsHandler, new Color(255, 0, 0, 170));
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSubImage(0, 0))
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });
            
            put("STAND_RIGHT_RED", new Frame[] {
                    new FrameBuilder(spriteSheet.getSubImage(0, 0))
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });


            put("STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSubImage(0, 0))
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });
            
            put("STAND_LEFT_RED", new Frame[] {
                    new FrameBuilder(spriteSheet.getSubImage(0, 0))
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)

                            .withBounds(8, 9, 8, 9)
                            .build()
            });
            


            put("WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSubImage(0, 0), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSubImage(0, 0), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSubImage(0, 0), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSubImage(0, 0), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });
            
            put("WALK_RIGHT_RED", new Frame[] {
                    new FrameBuilder(spriteSheet.getSubImage(0, 0), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSubImage(0, 0), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSubImage(0, 0), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSubImage(0, 0), 200)
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSubImage(0, 0), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSubImage(0, 0), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSubImage(0, 0), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSubImage(0, 0), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });
            
            put("WALK_LEFT_RED", new Frame[] {
                    new FrameBuilder(spriteSheet.getSubImage(0, 0), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSubImage(0, 0), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSubImage(0, 0), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build(),
                    new FrameBuilder(spriteSheet.getSubImage(0, 0), 200)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("JUMP_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSubImage(0, 0))
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });
            
            put("JUMP_RIGHT_RED", new Frame[] {
                    new FrameBuilder(spriteSheet.getSubImage(0, 0))
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("JUMP_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSubImage(0, 0))
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });
            
            put("JUMP_LEFT_RED", new Frame[] {
                    new FrameBuilder(spriteSheet.getSubImage(0, 0))
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });


            put("FALL_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSubImage(0, 0))
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });
            
            put("FALL_RIGHT_RED", new Frame[] {
                    new FrameBuilder(spriteSheet.getSubImage(0, 0))
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });
            
            put("FALL_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSubImage(0, 0))
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });
            put("FALL_LEFT_RED", new Frame[] {
                    new FrameBuilder(spriteSheet.getSubImage(0, 0))
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });
            put("CROUCH_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSubImage(0, 0))
                            .withScale(3)
                            .withBounds(8, 12, 8, 6)
                            .build()
            });
            put("CROUCH_RIGHT_RED", new Frame[] {
                    new FrameBuilder(spriteSheet.getSubImage(0, 0))
                            .withScale(3)
                            .withBounds(8, 12, 8, 6)
                            .build()
            });

            put("CROUCH_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSubImage(0, 0))
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 12, 8, 6)
                            .build()
            });
            put("CROUCH_LEFT_RED", new Frame[] {
                    new FrameBuilder(spriteSheet.getSubImage(0, 0))
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 12, 8, 6)
                            .build()
            });

            put("DEATH_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSubImage(0, 0), 100)
                            .withScale(3)
                            .build(),
                    new FrameBuilder(spriteSheet.getSubImage(0, 0), 100)
                            .withScale(3)
                            .build(),
                    new FrameBuilder(spriteSheet.getSubImage(0, 0), -1)
                            .withScale(3)
                            .build()
            });
            put ("TAKING_DAMAGE_RIGHT",new Frame[] {
            	new FrameBuilder(spriteSheet.getSubImage(0, 0), 100)
                .withScale(3)
                .build(),
        new FrameBuilder(spriteSheet.getSubImage(0, 0), 100)
                .withScale(3)
                .build(),
        new FrameBuilder(spriteSheet.getSubImage(0, 0), -1)
                .withScale(3)
                .build()
            });

            put ("DEATH_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSubImage(0, 0), 100)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build(),
                    new FrameBuilder(spriteSheet.getSubImage(0, 0), 100)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build(),
                    new FrameBuilder(spriteSheet.getSubImage(0, 0), -1)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });
            put ("TAKING_DAMAGE_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSubImage(0, 0), 100)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build(),
                    new FrameBuilder(spriteSheet.getSubImage(0, 0), 100)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build(),
                    new FrameBuilder(spriteSheet.getSubImage(0, 0), -1)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });

            put("SWIM_STAND_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSubImage(0, 0))
                            .withScale(3)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });

            put("SWIM_STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSubImage(0, 0))
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(8, 9, 8, 9)
                            .build()
            });
        }};
    }
}
