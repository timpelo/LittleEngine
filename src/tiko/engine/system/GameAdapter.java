package tiko.engine.system;

import tiko.engine.gui.ScreenManager;

/**
 * GameAdapter for handling game running.
 *
 * This class starts new thread used for handling game running.
 *
 * @author Jani Timonen
 * @version 1.0
 * @since 1.8
 */
public abstract class GameAdapter implements Runnable {

    /**
     * ScreenManager for the game.
     */
    private ScreenManager screenManager = null;

    /**
     * Default Constructor.
     */
    public GameAdapter() {

    }

    /**
     * Starts new thread for running the game.
     *
     * @param screenManager ScreenManager used for handling
     *                      screens in the game.
     */
    public final void begin(ScreenManager screenManager) {
        this.screenManager = screenManager;
        start();
        (new Thread(this)).start();
    }

    /**
     * Executes when game starts running.
     */
    public void start() {

    }

    /**
     * Executes when game has started.
     */
    public void run() {

    }

    /**
     * Returns ScreenManager of this game.
     *
     * @return ScreenManager of this game.
     */
    public ScreenManager getScreenManager() {
        return screenManager;
    }

    /**
     * Sets ScreenManager for this game.
     *
     * @param screenManager ScreenManager for this game.
     */
    public void setScreenManager(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    /**
     * Makes game to wait for given time in milliseconds.
     *
     * @param milliseconds time to wait in milliseconds.
     */
    public void doStep(int milliseconds) {

        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
