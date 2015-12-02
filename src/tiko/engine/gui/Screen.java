package tiko.engine.gui;

import tiko.engine.gameobject.GameObject;

/**
 * Screen contains canvas where objects are drawn.
 *
 * Screen has canvas for drawing. It also has camera used to draw objects to
 * right place in game screen.
 *
 * @author Jani Timonen
 * @version 1.0
 * @since 1.8
 */
public abstract class Screen {

    /**
     * Canvas where objects are drawn.
     */
    private Canvas canvas;

    /**
     * Represents if screen is active.
     */
    private boolean active;

    /**
     * Screen manager where this screen is located.
     */
    private ScreenManager screenManager;

    /**
     * Camera used to draw objects to right place in game screen.
     */
    private Camera camera;

    /**
     * Constructor for this class.
     *
     * @param screenManager screen manager where this screen is located.
     */
    public Screen(ScreenManager screenManager) {
        this.screenManager = screenManager;
        canvas = new Canvas(this);
        canvas.setBounds(0, 0,
                screenManager.getFrame().getWidth(),
                screenManager.getFrame().getHeight());
        active = false;
    }

    /**
     * Executes in every render.
     *
     */
    public abstract void run();

    /**
     * Returns canvas of this screen.
     *
     * @return canvas of this screen.
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * Adds object to canvas.
     *
     * @param o object wanted to add to canvas.
     */
    public void addObject(GameObject o) {

        canvas.addObject(o);
    }

    /**
     * Returns active status of this screen.
     *
     * @return true - activated screen, false - unactivated screen.
     */
    public boolean getActive() {

        return active;
    }

    /**
     * Changes active status of this screen.
     *
     * @param active true - activated screen, false - unactivated screen.
     */
    public void setActive(boolean active) {

        this.active = active;
        canvas.setVisible(active);
        canvas.setFocusable(active);
        canvas.requestFocusInWindow();
    }

    /**
     * Returns camera of this screen.
     *
     * @return camera of this screen.
     */
    public Camera getCamera() {
        return camera;
    }

    /**
     * Sets camera for this screen.
     *
     * @param camera camera for this screen.
     */
    public void setCamera(Camera camera) {
        this.camera = camera;
        this.camera.moveCameraX(camera.getX());
        this.camera.moveCameraY(camera.getY());
    }
}
