package tiko.engine.gui;

import java.awt.*;

/**
 * Camera object used in game screen.
 *
 * Camera is used in game screen. It can be moved in screen with automatic
 * detection of world borders. It won't move over world borders.
 *
 * @author Jani Timonen
 * @version 1.0
 * @since 1.8
 */
public class Camera {

    /**
     * Position in x-axis.
     */
    private int x;

    /**
     * Position in y-axis.
     */
    private int y;

    /**
     * Offset for moving camera in x-axis.
     */
    private int offsetMaxX;

    /**
     * Offset for moving camera in y-axis.
     */
    private int offsetMaxY;

    /**
     * Width of camera view area.
     */
    private int cameraWidth;

    /**
     * Height of camera view area.
     */
    private int cameraHeight;

    /**
     * Constructor for this object.
     *
     * @param worldSizeWidth   world width.
     * @param worldSizeHeight   world height.
     * @param cameraWidth  camera view area with.
     * @param cameraHeight camera view area height.
     * @param positionX camera x-position in world.
     * @param positionY camera y-position in world.
     */
    public Camera(int worldSizeWidth, int worldSizeHeight,
                  int cameraWidth, int cameraHeight,
                  int positionX, int positionY) {

        // Offsets are used to check that camera won't move over edges of
        // game world.
        offsetMaxX = worldSizeWidth - cameraWidth;
        offsetMaxY = worldSizeHeight - cameraHeight;

        // Height and width are used to specify viewport of camera.
        this.cameraHeight = cameraHeight;
        this.cameraWidth = cameraWidth;

        x = positionX;
        y = positionY;
    }

    /**
     * Moves camera in x-axis.
     *
     * Camera will automatically detect given world borders and won't move
     * over them.
     *
     * @param x new position of camera in x-axis.
     */
    public void moveCameraX(int x) {

        // Moves camera but checks that it won't move over given offset.
        if (x < 0) {
            this.x = 0;
        } else if (x > offsetMaxX) {
            this.x = offsetMaxX;
        } else {
            this.x = x;
        }
    }

    /**
     * Moves camera in y-axis.
     *
     * Camera will automatically detect given world borders and won't move
     * over them.
     *
     * @param y new position of camera in x-axis.
     */
    public void moveCameraY(int y) {

        // Moves camera but checks that it won't move over given offset.
        if (y < 0) {
            this.y = 0;
        } else if (y > offsetMaxY) {
            this.y = offsetMaxY;
        } else {
            this.y = y;
        }
    }

    /**
     * Returns camera position in x-axis.
     *
     * @return position in x-axis.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns camera position in y-axis.
     *
     * @return position in y-axis
     */
    public int getY() {
        return y;
    }

    /**
     * Returns width of camera view area.
     *
     * @return width of camera view area.
     */
    public int getCameraWidth() {
        return cameraWidth;
    }

    /**
     * Returns height of camera view area.
     *
     * @return height of camera view area.
     */
    public int getCameraHeight() {
        return cameraHeight;
    }

    /**
     * Returns camera center coordinate.
     *
     * @return camera center in world as Point object.
     */
    public Point getCenter() {
        int x = getX() + (getCameraWidth() / 2);
        int y = getY() + (getCameraHeight() / 2);

        return new Point(x, y);
    }
}
