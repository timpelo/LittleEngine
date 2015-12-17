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
 * @author Jani
 * @version 1.0
 * @since 1.8
 */
public class World {
    LinkedList<GameObject> objectList;
    Screen host;

    public World(Screen host) {
        this.host = host;
        objectList = new LinkedList<>();
    }

    public void addObject(GameObject o) {
        objectList.add(o);
    }

    public void removeObject(GameObject o) {
        objectList.remove(o);
    }

    public void physicsStep() {
        calcGravity();
        host.getCanvas().repaint();
    }

    private void calcGravity() {

        for (GameObject o: objectList) {

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
    }

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

    private boolean checkCollision(PhysicsBody body) {
        boolean result = false;

        for (GameObject o: objectList) {
            PhysicsBody other = o.getPhysicsBody().get();

            // Checks if object does collide with anything else than itself.
            if (other.checkCollision(body.getCollider()) &&
                    !other.equals(body)) {

                result = true;

                // Sets object to not be in air if it hits ground.
                if(other.getLayer() == "Ground") {
                    body.setInAir(false);
                }
            }
        }
        return result;
    }

    public LinkedList getObjectList() {
        return objectList;
    }
}
