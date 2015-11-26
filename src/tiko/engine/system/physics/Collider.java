package tiko.engine.system.physics;

import java.awt.*;

/**
 * Class short description
 * <p>
 * Class long descroption
 *
 * @author Jani
 * @version 1.0
 * @since 1.8
 */
public class Collider {

    private Shape collider;

    public Collider(Shape collider) {

        this.collider = collider;
    }

    public Shape getCollider() {
        return collider;
    }

    public void setCollider(Shape collider) {
        this.collider = collider;
    }
}
