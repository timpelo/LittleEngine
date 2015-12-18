package tiko.engine.system.physics;

import tiko.engine.gameobject.GameObject;
import tiko.engine.gui.Screen;

import javax.sound.sampled.Line;
import java.util.LinkedList;

/**
 * Class containing all info for physics world.
 *
 * Class contains all info needed for physics world. It calculates physics
 * for objects having PhysicsBody.
 *
 * @author Jani Timonen
 * @version 1.0
 * @since 1.8
 */
public class World {

    /**
     * List of game objects in this world.
     */
    LinkedList<GameObject> objectList;

    /**
     * Game screen where this world is located.
     */
    Screen host;

    /**
     * Default constructor
     *
     * @param host screen where this world is located.
     */
    public World(Screen host) {
        this.host = host;
        objectList = new LinkedList<>();
    }

    /**
     * Adds game object to this world.
     *
     * @param o game object to this world.
     */
    public void addObject(GameObject o) {
        objectList.add(o);
    }

    /**
     * Removes game object from this world.
     *
     * @param o game object from this world.
     */
    public void removeObject(GameObject o) {
        objectList.remove(o);
    }

    /**
     * Does phobics step for world.
     *
     * Physics step will calculate all forces and check collision of objects.
     * It will move their position according these forces.
     */
    public void physicsStep() {

        for (int i = 0; i < objectList.size(); i++){
            GameObject o = objectList.get(i);

            if (o.getPhysicsBody().isPresent()) {
                PhysicsBody body = o.getPhysicsBody().get();

                // Moves object according it forces (horizontal and vertical).
                o.setY(o.getY() +
                        (int)body.getMass() *
                                (int)body.getForceV());

                o.setX(o.getX() +
                        (int)(body.getForceH() *
                                (int)body.getMass()));

                calcDrag(o);

                // If object is not kinetic, it will get increased velocity.
                if (!body.isKinetic()) {
                    body.addForce(0.10f * body.getMass(), true);
                }

                // Hitting something will calculate jump force of object.
                if (checkCollision(body) && !body.isKinetic()) {
                        calcJump(o);
                }
            }
        }

        host.getCanvas().repaint();
    }

    /**
     * Calculates jump force of object.
     *
     * @param o object which jump will be calculated.
     */
    private void calcJump(GameObject o) {

        // Calculates new vertical force according bounciness and current
        // force of game object.
        PhysicsBody body = o.getPhysicsBody().get();
        body.setVerticalForce(-body.getForceV() * body.getBounciness());

        // If force is significant small, it well be set to zero.
        if (body.getForceV() < 0.25f * body.getMass() &&
                body.getForceV() > -0.25f * body.getMass()) {

            body.setVerticalForce(0f);
        } else {
            o.setY(o.getY() - 1);
        }
    }

    /**
     * Calculates drag of object. If object is in air, drag will be smaller.
     *
     * @param o object which drag will be calculated.
     */
    private void calcDrag(GameObject o) {

        // If force is significant small, it will be set to zero.
        PhysicsBody body = o.getPhysicsBody().get();
        if (body.getForceH() < 0.25f * body.getMass() &&
                body.getForceH() > -0.25f * body.getMass()) {

            body.setHorizontalForce(0f);
        }

        // If force is positive, calculate new force. Drag effects more to
        // force if object is in air.
        if(body.getForceH() > 0) {
            float newForce = body.getForceH() - body.getDrag();

            if(body.isInAir()) {
                newForce = body.getForceH() - (body.getDrag() * 0.2f);
            }
            body.setHorizontalForce(newForce);
        }

        // If force is negative, calculate new force. Drag effects more to
        // force if object is in air.
        else if(body.getForceH() < 0) {
            float newForce = body.getForceH() + body.getDrag();

            if(body.isInAir()) {
                newForce = body.getForceH() + (body.getDrag() * 0.2f);
            }
            body.setHorizontalForce(newForce);
        }

    }

    /**
     * Checks if collision has happened with any object.
     *
     * Collision detection uses PhysicsLayers. they can be set to ignore
     * other layers if wanted. In this case collision will return false as
     * result.
     *
     * @param body body which collision want to be checked.
     * @return true - object does collide, false - object does not collide.
     */
    private boolean checkCollision(PhysicsBody body) {
        boolean result = false;

        for (GameObject o: objectList) {
            PhysicsBody other = o.getPhysicsBody().get();

            // Checks if object does collide with anything else than itself.
            if (other.checkCollision(body.getCollider()) &&
                    !other.equals(body)) {

                result = true;

                // Sets object to not be in air if it hits ground.
                if(other.getLayer().getName().equals("ground")) {
                    body.setInAir(false);
                }

                // Checks if collision has happened with ground. This
                // prevents objects falling through ground layer.
                if(other.getLayer().isIgnored(body.getLayer())
                        && body.isInAir()) {
                    result = false;
                }
            }
        }
        return result;
    }

    /**
     * Returns object list of this world.
     *
     * @return object list of this world.
     */
    public LinkedList<GameObject> getObjectList() {
        return objectList;
    }
}
