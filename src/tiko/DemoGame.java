package tiko;




import tiko.engine.gui.Camera;
import tiko.engine.gui.Screen;
import tiko.engine.system.GameAdapter;
import tiko.engine.system.physics.World;
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
        doStep(500);
        while(true) {
            doStep(10);
            activeScreen = getScreenManager().getActiveScreen();
            activeScreen.run();
        }

    }

    @Override
    public void start() {

        MainMenu menu = new MainMenu(getScreenManager(), this);
        GameScreen gameScreen = new GameScreen(getScreenManager(), this);

        soundManager.addSound("indy.wav");
        Sound bgSound = new Sound("bg.wav");
        bgSound.setLoop(true);
        soundManager.addSound(bgSound);
        soundManager.addSound("blop.wav");
        soundManager.playSound(1);
        soundManager.playSound(0);


        getScreenManager().addScreen(menu);
        getScreenManager().addScreen(gameScreen);
        gameScreen.setCamera(new Camera(2000, 1000, 1280, 720, 0, 650));

        getScreenManager().changeScreen(0);
        getScreenManager().addScreen(gameScreen);
        gameScreen.setCamera(new Camera(2000, 1000, 1280, 720, 0, 650));

        getScreenManager().changeScreen(0);
        activeScreen = getScreenManager().getActiveScreen();
    }
}
