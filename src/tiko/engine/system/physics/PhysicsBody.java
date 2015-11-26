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

    private Shape collider;
    private int mass;
    private int drag;
    private int bounciness;
    private boolean kinetic;
    private int angle;

    public PhysicsBody(Shape collider,
                       int mass,
                       int drag,
                       int bounciness,
                       boolean kinetic) {

        this.mass = mass;
        this.drag = drag;
        this. bounciness = bounciness;
        this.kinetic = kinetic;
        angle = 0;

    }
}
