package tiko.engine.gui;



import java.awt.*;

/**
 * Camera object used in game screen.
 *
 * Camera is used in game screen. It can be moved in screen with automatic
 * detection of world borders. It won't move over world borders.
 *
 * @author Jani Timonen
 * @since 1.8
 * @version 1.0
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
     * @param worldSizeX world width.
     * @param worldSizeY world height.
     * @param cameraWidth camera view area with.
     * @param cameraHeight camera view area height.
     */
    public Camera (int worldSizeX, int worldSizeY,
                   int cameraWidth, int cameraHeight) {

        offsetMaxX = worldSizeX - cameraWidth;
        offsetMaxY = worldSizeY - cameraHeight;

        this.cameraHeight = cameraHeight;
        this.cameraWidth = cameraWidth;

        x = 0;
        y = 0;
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

        if(x < 0) {
            this.x = 0;
        }
        else if(x > offsetMaxX) {
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
        if(y < 0) {
            this.y = 0;
        }
        else if(y > offsetMaxY) {
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
