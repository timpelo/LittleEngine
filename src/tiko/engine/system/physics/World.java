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

    public void addObject(GameObject body) {
        objectList.add(body);
    }

    public void removeObject(PhysicsBody body) {
        objectList.remove(body);
    }

    public void physicsStep() {


    }

    private void calcGravity() {

        for(GameObject body: objectList) {

            if(body.getPhysicsBody().isPresent()) {

                /// calcl gravity
            }
        }
    }
}
