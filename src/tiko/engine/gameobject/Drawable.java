package tiko.engine.gameobject;

import java.awt.*;

/**
 * Interface to determine drawable objects.
 *
 * @author Jani Timonen
 * @since 1.8
 * @version 1.0
 */
public interface Drawable {

    /**
     * Draw object using Graphics2D object according camera position.
     *
     * @param g2 Graphics2D object used for drawing.
     * @param cameraX camera position in x-axis.
     * @param cameraY camera position in y-axis.
     */
    void draw(Graphics2D g2, int cameraX, int cameraY);

    /**
     * Destroy texture of drawable object.
     */
    void destroyTexture();
}
