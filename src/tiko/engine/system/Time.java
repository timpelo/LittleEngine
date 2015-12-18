package tiko.engine.system;

/**
 * Class for handling delta time for game loop.
 *
 * Delta tile is time past in last render. It is used to make physics
 * calculations go right and also used to sync animations.
 *
 * @author Jani Timonen
 * @version 1.0
 * @since 1.8
 */
public abstract class Time {

    /**
     * Current delta time.
     */
    private static float delta = 0;

    /**
     * Last update time.
     */
    private static float last = 0;

    /**
     * Time at point when update is called.
     */
    private static float now;

    /**
     * Tells if delta time is set for first time.
     */
    private static boolean first = true;

    /**
     * Returns delta time.
     *
     * @return delta time.
     */
    public static float deltaTime() {
        return delta;
    }

    /**
     * Updates delta time.
     *
     * Should be called in start of every render. It will then
     * update delta time of last render time.
     */
    public static void update() {

        if (first) {
            now = 0.00f;
            first = false;
        }

        now = System.nanoTime();
        delta = (now - last) / 1000000;
        last = now;
    }
}
