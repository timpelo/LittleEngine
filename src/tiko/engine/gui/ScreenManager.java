package tiko.engine.gui;

import tiko.engine.system.GameAdapter;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Screen manager handles all screens in the game.
 *
 * Screen manager keeps all screens in list. Object contains info of
 * world height and width. It also contains main game window. Creates default
 * camera for added screens.
 *
 * @author Jani Timonen
 * @version 1.0
 * @since 1.8
 */
public class ScreenManager {

    /**
     * Height of game world. This is public variable.
     */
    public int WORLD_HEIGHT = 0;

    /**
     * Width of game world. This is public variable.
     */
    public int WORLD_WIDTH = 0;

    /**
     * Main game window.
     */
    private JFrame frame;

    /**
     * List containing all screens.
     */
    private ArrayList<Screen> screenList;

    /**
     * Game itself where this manager is located.
     */
    private GameAdapter game;

    /**
     * Default camera used for new screens.
     */
    private Camera camera;

    /**
     * Constructor for this class.
     *
     * @param worldWith   width of game world.
     * @param worldHeight height of game world.
     * @param width       width of game window.
     * @param height      height of game window.
     * @param game        game where this manager is located.
     */
    public ScreenManager(int worldWith,
                         int worldHeight,
                         int width,
                         int height,
                         GameAdapter game) {
        this.game = game;
        setWorldBounds(worldWith, worldHeight);

        camera = new Camera(WORLD_WIDTH,
                WORLD_HEIGHT,
                width,
                height);

        screenList = new ArrayList<>();
        frame = new JFrame("EnigmaEngine v1.0");
        frame.setBounds(0, 0, width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        game.begin(this);
    }

    /**
     * Adds new screen to the list.
     *
     * @param screen screen added to the list.
     */
    public void addScreen(Screen screen) {
        screen.setCamera(camera);
        frame.getContentPane().add(screen.getCanvas());
        screenList.add(screen);
    }

    /**
     * Returns main game window.
     *
     * @return main game window as JFrame.
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Returns screen that is active.
     *
     * @return screen that is active.
     */
    public Screen getActiveScreen() {
        Screen activeScreen = null;

        for (Screen src : screenList) {

            if (src.getActive()) {
                activeScreen = src;
            }
        }

        return activeScreen;
    }

    /**
     * Sets bounds for game world.
     *
     * @param width  width of game world.
     * @param height height of game world.
     */
    public void setWorldBounds(int width, int height) {
        WORLD_HEIGHT = height;
        WORLD_WIDTH = width;
    }

    /**
     * Changes active game screen.
     *
     * @param i index of the screen in the list.
     */
    public void changeScreen(int i) {

        for (Screen screen : screenList) {
            screen.setActive(false);
        }

        screenList.get(i).setActive(true);

        System.out.println(screenList.get(i) + " is now active!");
    }
}
