package tiko.engine.system.physics;

import tiko.engine.gameobject.GameObject;

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

    public World() {
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
    }

    private void calcGravity() {

        for (GameObject o: objectList) {

            if (o.getPhysicsBody().isPresent()) {
                PhysicsBody body = o.getPhysicsBody().get();

                if(!(body.checkCollision(body.getCollider())) && !body.isKinetic()) {
                    o.setY(o.getY() - body.getMass());
                    System.out.println("going down!");
                }
            }
        }
    }

    private boolean checkCollision(Collider c) {
        boolean result = false;

        for (GameObject o: objectList) {
            PhysicsBody body = o.getPhysicsBody().get();

            if (body.checkCollision(c)) {
                result = true;
            }
        }

        return result;
    }
}
