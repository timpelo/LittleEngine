package tiko.engine.system.physics;

import java.awt.*;
import java.awt.geom.Area;

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

        boolean result = false;

        if(collider.getCollider().getBounds().intersects(
                other.getCollider().getBounds()
        )) {
            result = true;
        }

        return result;
    }
}
