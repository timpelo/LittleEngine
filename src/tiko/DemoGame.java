package tiko;




import tiko.engine.gui.Screen;
import tiko.engine.system.GameAdapter;
import tiko.engine.system.sound.Sound;
import tiko.engine.system.sound.SoundManager;


/**
 * Created by Jani on 13.11.2015.
 */
public class DemoGame extends GameAdapter {

    Screen activeScreen;
    SoundManager soundManager;


    public DemoGame() {
        soundManager = new SoundManager(this);
    }

    @Override
    public void run() {

        while(true) {
            if(activeScreen != null) {
                activeScreen.run();
                doStep(10);
                activeScreen = getScreenManager().getActiveScreen();
            }
        }

    }

    @Override
    public void start() {

        MainMenu menu = new MainMenu(getScreenManager(), this);
        GameScreen gameScreen = new GameScreen(getScreenManager(), this);
        soundManager.addSound("clint.wav");
        Sound bgSound = new Sound("car.wav");
        bgSound.setLoop(true);
        soundManager.addSound(bgSound);

        soundManager.playSound(1);
        soundManager.playSound(0);
        getScreenManager().addScreen(menu);
        getScreenManager().changeScreen(0);
        getScreenManager().addScreen(gameScreen);
        activeScreen = getScreenManager().getActiveScreen();



    }
}
