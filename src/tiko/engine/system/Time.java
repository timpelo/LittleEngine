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

    private static float deltaTime = 0;
    private static float startTime = 0;

    public static float deltaTime() {
        return deltaTime;
    }

    public static void update() {
        deltaTime = startTime - System.nanoTime() / 10000000;
        startTime = System.nanoTime();
        System.out.println(deltaTime);
    }
}
