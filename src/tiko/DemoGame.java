package tiko;




import tiko.engine.gui.Screen;
import tiko.engine.system.GameAdapter;


/**
 * Created by Jani on 13.11.2015.
 */
public class DemoGame extends GameAdapter {

    Screen activeScreen;


    public DemoGame() {

    }

    @Override
    public void run() {

        while(true) {
            activeScreen.run();
            doStep(10);
            activeScreen = getScreenManager().getActiveScreen();
        }

    }

    @Override
    public void start() {

        MainMenu menu = new MainMenu(getScreenManager(), this);
        GameScreen gameScreen = new GameScreen(getScreenManager(), this);

        getScreenManager().addScreen(menu);
        getScreenManager().changeScreen(0);
        getScreenManager().addScreen(gameScreen);
        activeScreen = getScreenManager().getActiveScreen();



    }
}
