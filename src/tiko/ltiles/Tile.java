package tiko.ltiles;

import java.awt.image.BufferedImage;

/**
 * Asset class for editor.
 *
 * Asset class contains all info about asset. It contains location and
 * texture for asset. Also have name which is used for save file.
 *
 * @author Jani
 * @since 1.8
 * @version 1.0
 */
public class Tile {

    /**
     * Location in x-axis.
     */
    private int x;

    /**
     * Location in y-axis.
     */
    private int y;

    /**
     * Texture for asset.
     */
    BufferedImage image;

    /**
     * File name used for save file. Has to match with name of texture file.
     */
    private String filename = "";

    /**
     * Default constructor.
     *
     * Sets x and y positions on screen. Also adds texture and filename.
     *
     * @param x position in x-axis.
     * @param y position in y-axis.
     * @param image texture for asset.
     * @param filename filename has to match with texture file name.
     */
    public Tile(int x, int y, BufferedImage image, String filename) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.filename = filename;
    }

    /**
     * Returns position in y-axis.
     *
     * @return position in y-axis.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets position in y-axis.
     *
     * @param y position in y-axis.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns position in x-axis.
     *
     * @return position in x-axis.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets position in x-axis.
     *
     * @param x position in x-axis.
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
     * @param image  texture of asset.
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * Returns file name of asset.
     *
     * @return file name of asset.
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Returns x-position in the screen.
     *
     * This is x-position which user will see. Original x-position is false,
     * due asset draw. Asset center is drawn to position of mouse pointer.
     *
     * @return real x-position.
     */
    public int getRealX() {
        return x - (image.getWidth() / 2);
    }

    /**
     * Returns y-position in the screen.
     *
     * This is y-position which user will see. Original x-position is false,
     * due asset draw. Asset center is drawn to position of mouse pointer.
     *
     * @return real y-position.
     */
    public int getRealY() {
        return y - (image.getHeight() / 2);
    }

    /**
     * Destroy texture of this asset.
     */
    public void destroy() {
        image.flush();
        image = null;
    }
}
