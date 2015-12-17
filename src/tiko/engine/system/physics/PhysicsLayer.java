package tiko.engine.system.physics;

import java.util.LinkedList;

/**
 * Class short description
 * <p>
 * Class long descroption
 *
 * @author Jani
 * @version 1.0
 * @since 1.8
 */
public class PhysicsLayer {

    private String layerName;
    private LinkedList<PhysicsLayer> ignoreList;

    public PhysicsLayer(String name) {
        this.layerName = name;
        ignoreList = new LinkedList<>();
    }

    public void ignoreLayer(PhysicsLayer layer) {
        ignoreList.add(layer);
    }

    public boolean isIgnored(PhysicsLayer layer) {
        boolean result = false;

        for (PhysicsLayer l : ignoreList) {

            if (l.layerName.equals(layerName)) {
                result = true;
            }
        }

        return result;
    }

    public String getName() {
        return layerName;
    }
}
