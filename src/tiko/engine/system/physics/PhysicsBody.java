package tiko.engine.system.physics;

import java.awt.*;

/**
 * Class short description
 *
 * Class long descroption
 *
 * @author Jani Timonen
 * @version 1.0
 * @since 1.8
 */
public class PhysicsBody {

    private Collider collider;
    private int mass;
    private int drag;
    private int bounciness;
    private boolean kinetic;
    private int angle;
    private String layer;

    public PhysicsBody(Collider collider,
                       int mass,
                       int drag,
                       int bounciness,
                       boolean kinetic) {

        this.mass = mass;
        this.drag = drag;
        this. bounciness = bounciness;
        this.kinetic = kinetic;
        layer = "default";
        angle = 0;

    }

    public boolean checkCollision(Collider other) {
        boolean collision = false;

        if(other.getCollider().intersects(collider.getCollider().getBounds())) {
            collision = true;
        }

        return collision;
    }
}
