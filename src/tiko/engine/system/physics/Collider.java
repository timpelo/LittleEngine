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
    private Shape collider;

    /**
     * Default constructor.
     *
     * @param collider Shape to represent are of collider.
     */
    public Collider(Shape collider) {

        this.collider = collider;
    }

    /**
     * Returns area of this collider as Shape.
     *
     * @return area of this collider as Shape.
     */
    public Shape getCollider() {
        return collider;
    }

    /**
     * Sets area of this collider as Shape.
     *
     * @param collider area of this collider as Shape.
     */
    public void setCollider(Shape collider) {
        this.collider = collider;
    }
}
