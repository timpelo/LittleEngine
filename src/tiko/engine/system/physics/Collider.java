package tiko.engine.system.physics;

import java.awt.*;

/**
 * Collider to check collisions.
 *
 * @author Jani Timonen
 * @version 1.0
 * @since 1.8
 */
public class Collider {

    /**
     * Shape to represent area of collider.
     */
    private Rectangle collider;

    /**
     * Default constructor.
     *
     * @param collider Rectangle to represent are of collider.
     */
    public Collider(Rectangle collider) {

        this.collider = collider;
    }

    /**
     * Returns area of this collider as Rectangle.
     *
     * @return area of this collider as Rectangle.
     */
    public Rectangle getCollider() {
        return collider;
    }

    /**
     * Sets area of this collider as Rectangle.
     *
     * @param collider area of this collider as Rectangle.
     */
    public void setCollider(Rectangle collider) {
        this.collider = collider;
    }

    /**
     * Sets x-coordinate of this Collider.
     *
     * @param x x-coordinate of this Collider.
     */
    public void setX(int x) {
        collider.setLocation(x, (int) collider.getY());
    }

    /**
     * Sets x-coordinate of this Collider.
     *
     * @return  x-coordinate of this Collider.
     */
    public double getX() {
        return collider.getX();
    }

    /**
     * Sets y-coordinate of this Collider.
     *
     * @param y y-coordinate of this Collider.
     */
    public void setY(int y) {
        collider.setLocation((int) collider.getX(), y);
    }

    /**
     * Sets y-coordinate of this Collider.
     *
     * @return y-coordinate of this Collider.
     */
    public double getY() {
        return collider.getY();
    }
}
