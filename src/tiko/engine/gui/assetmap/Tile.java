package tiko.engine.gui.assetmap;

import java.awt.image.BufferedImage;

/**
 * Class contains info of asset.
 *
 * Class contains x and y
 *
 * @author Jani
 * @version 1.0
 * @since 1.8
 */
public class Tile {

    /**
     * Position in x-axis.
     */
    private int x;

    /**
     * Position in y-axis.
     */
    private int y;

    /**
     * Texture of asset.
     */
    BufferedImage image;

    /**
     * Default constructor.
     *
     * Sets x, y and image for this asset.
     *
     * @param x position in x-axis.
     * @param y position in y-axis.
     * @param image texture of asset.
     */
    public Tile(int x, int y, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    /**
     * Returns y-coordinate of asset.
     *
     * @return y-coordinate of asset.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets y-coordinate of asset.
     *
     * @param y y-coordinate of asset.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns x-coordinate of asset.
     *
     * @return x-coordinate of asset.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets x-coordinate of asset.
     *
     * @param x x-coordinate of asset.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns texture of asset.
     *
     * @return texture of asset.
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Sets texture of asset.
     *
     * @param image texture of asset.
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
