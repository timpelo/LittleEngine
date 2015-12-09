package tiko.engine.system.physics;

import tiko.engine.gameobject.GameObject;
import tiko.engine.gui.Screen;

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

                System.out.println(
                        "collision " + checkCollision(body));
                System.out.println("kinetic " + body.isKinetic());

                if(!checkCollision(body) && !body.isKinetic()) {
                    o.setY(o.getY() + body.getMass());
                    System.out.println("going down!");
                }
            }
        }
    }

    private boolean checkCollision(PhysicsBody other) {
        boolean result = false;

        for (GameObject o: objectList) {
            PhysicsBody body = o.getPhysicsBody().get();

            if (body.checkCollision(other.getCollider()) &&
                    !body.equals(other)) {
                result = true;
            }
        }

        return result;
    }
}
