package tiko.ltiles;

import tiko.engine.gameobject.GameObject;

import java.awt.image.BufferedImage;

/**
 * Class short description
 * <p>
 * Class long descroption
 *
 * @author Jani
 * @version 1.0
 * @since 1.8
 */
public class Tile {

    private int x;
    private int y;
    BufferedImage image;
    private String filename = "";

    public Tile(int x, int y, BufferedImage image, String filename) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.filename = filename;
    }

    public Tile(int x, int y, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getFilename() {
        return filename;
    }

    public int getRealX() {
        return x - (image.getWidth() / 2);
    }

    public int getRealY() {
        return y - (image.getHeight() / 2);
    }
}
