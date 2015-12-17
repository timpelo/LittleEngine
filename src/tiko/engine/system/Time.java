package tiko.engine.system;

/**
 * Class short description
 * <p>
 * Class long descroption
 *
 * @author Jani
 * @version 1.0
 * @since 1.8
 */
public abstract class Time {

    private static float delta = 0;
    private static float last = 0;
    private static float now;
    private static boolean first= true;

    public static float deltaTime() {
        return delta;
    }

    public static void update() {

        if(first) {
            now = 0.00f;
            first = false;
        }
        now = System.nanoTime();
        delta = (now - last) / 1000000;
        last = now;
    }
}
