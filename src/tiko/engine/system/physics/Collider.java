package tiko.engine.system.physics;

import java.awt.*;

/**
 * Collider to check collisions.
 *
 * @author Jani Timonen
 * @since 1.8
 * @version 1.0
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

    public void setX(int x) {
        collider.setLocation(x, (int) collider.getY());
    }

    public double getX() {
        return collider.getX();
    }

    public void setY(int y) {
        collider.setLocation((int) collider.getX(), y);
    }

    public double getY() {
        return collider.getY();
    }
}
