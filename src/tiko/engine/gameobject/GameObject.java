package tiko.engine.gameobject;

import tiko.engine.system.animation.Animation;
import tiko.engine.system.physics.Collider;
import tiko.engine.system.physics.PhysicsBody;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * Game object used to draw different objects to game screen.
 * <p>
 * Object has texture for drawing. It also has position in x- and y-axis. It
 * has default speed which can be changed.
 *
 * @author Jani Timonen
 * @version 1.0
 * @since 1.8
 */
public class GameObject implements Drawable {

    /**
     * Texture used for drawing.
     */
    private BufferedImage texture;

    /**
     * Position in x-axis.
     */
    private int x;

    /**
     * Position in y-axis.
     */
    private int y;

    /**
     * Speed used in moving object.
     */
    private int speed = 3;

    /**
     * Optional PhysicsBody for physic calculations.
     */
    private Optional<PhysicsBody> physicsBody;

    private Optional<Animation> animation;

    /**
     * Constructor for this class using BufferedImage as texture.
     *
     * @param x       position in x-axis.
     * @param y       position in y-axis.
     * @param texture texuter used for drawing.
     */
    public GameObject(int x, int y, BufferedImage texture) {
        this.x = x;
        this.y = y;
        this.texture = texture;
        physicsBody = Optional.ofNullable(null);
        animation = Optional.ofNullable(null);
    }

    /**
     * Constructor for this class using source for texture.
     *
     * @param x             position in x-axis.
     * @param y             position in y-axis.
     * @param textureSource source for texture.
     */
    public GameObject(int x, int y, String textureSource) {
        this.x = x;
        this.y = y;
        physicsBody = Optional.ofNullable(null);
        animation = Optional.ofNullable(null);

        try {
            BufferedImage texture = ImageIO.read(new File(textureSource));
            this.texture = texture;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns position in x-axis.
     *
     * @return position in x-axis.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets position in x-axis.
     *
     * @param x position in x-axis.
     */
    public void setX(int x) {
        this.x = x;

        if (physicsBody.isPresent()) {
            PhysicsBody body = physicsBody.get();
            body.getCollider().setX(x);
        }
    }

    /**
     * Returns position in y-axis.
     *
     * @return position in y-axis.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets position in y-axis.
     *
     * @param y position in y-axis.
     */
    public void setY(int y) {
        this.y = y;

        if (physicsBody.isPresent()) {
            PhysicsBody body = physicsBody.get();
            body.getCollider().setY(y);
        }
    }

    /**
     * Returns texture of this object.
     *
     * @return texture of this object.
     */
    public BufferedImage getTexture() {
        return texture;
    }

    /**
     * Sets texture for this object.
     *
     * @param texture texture for this object.
     */
    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    /**
     * Returns speed of this object.
     *
     * @return speed of this object.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets speed for this object.
     *
     * @param speed speed for this object.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Returns center coordinate of this object in world.
     *
     * @return center coordinate in world as Point object.
     */
    public Point getCenter() {
        int x = getX() + (texture.getWidth() / 2);
        int y = getY() + (texture.getHeight() / 2);

        return new Point(x, y);
    }

    /**
     * Draws texture of GameObject to screen.
     *
     * @see Drawable
     */
    @Override
    public void draw(Graphics2D g2, int cameraX, int cameraY) {

        g2.drawImage(getTexture(), getX() - cameraX, getY() - cameraY, null);
    }

    /**
     * Sets PhysicBody for object.
     *
     * @param physicsBody PhysicBody for this object
     */
    public void setPhysicsBody(PhysicsBody physicsBody) {
        this.physicsBody = Optional.ofNullable(physicsBody);
    }

    /**
     * Returns PhysicBody of object.
     *
     * @return  PhysicBody of this object
     */
    public Optional<PhysicsBody> getPhysicsBody() {
        return physicsBody;
    }

    public void setAnimation(Animation animation) {
        this.animation = Optional.ofNullable(animation);
    }

    public Optional<Animation> getAnimation() {
        return animation;
    }

    /**
     * Destroys texture of GameObject.
     *
     * @see Drawable
     */
    @Override
    public void destroyTexture() {
        texture.flush();
        texture = null;
    }
}
