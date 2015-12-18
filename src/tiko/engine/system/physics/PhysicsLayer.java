package tiko.engine.system.physics;

import java.util.LinkedList;

/**
 * Layer used for physics calculations.
 *
 * This layer contains all info of current layer. It holds list of ignored
 * layers. Two ignored layers wont effect on others in game.
 *
 * @author Jani Timonen
 * @version 1.0
 * @since 1.8
 */
public class PhysicsLayer {

    /**
     * Layer name.
     */
    private String layerName;

    /**
     * List of ignored layers.
     */
    private LinkedList<PhysicsLayer> ignoreList;

    /**
     * Default constructor.
     *
     * @param name name of layer.
     */
    public PhysicsLayer(String name) {
        this.layerName = name;
        ignoreList = new LinkedList<>();
    }

    /**
     * Adds layer to ignore list.
     *
     * @param layer layer to ignore list.
     */
    public void ignoreLayer(PhysicsLayer layer) {
        ignoreList.add(layer);
    }

    /**
     * Checks if given layer is in ignore list of this layer.
     *
     * @param layer layer for ignore check.
     * @return true - layer is ignored, false - layer is not ignored.
     */
    public boolean isIgnored(PhysicsLayer layer) {
        boolean result = false;

        for (PhysicsLayer l : ignoreList) {

            if (l.layerName.equals(layerName)) {
                result = true;
            }
        }

        return result;
    }

    /**
     * Returns name of this layer.
     *
     * @return name of this layer.
     */
    public String getName() {
        return layerName;
    }
}
