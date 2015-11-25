package tiko;

import tiko.engine.gameobject.GameObject;
import tiko.engine.gui.Screen;
import tiko.engine.gui.ScreenManager;
import tiko.engine.system.InputAdapter;

import java.awt.event.KeyEvent;

/**
 * Created by Jani on 25.11.2015.
 */
public class MainMenu extends Screen{

    DemoGame host;
    /**
     * Constructor for this class.
     *
     * @param screenManager screen manager where this screen is located.
     */
    public MainMenu(ScreenManager screenManager, DemoGame host) {
        super(screenManager);
        this.host = host;

        GameObject bg = new GameObject(0, 0, "assets/menu.png");
        addObject(bg);

        getCanvas().addKeyListener(new InputAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);

                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    host.getScreenManager().changeScreen(1);
                }
            }
        });
    }

    @Override
    public void run() {

    }
}
