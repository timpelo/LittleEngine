package tiko.engine.system.sound;

import tiko.engine.system.GameAdapter;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Class short description
 * <p>
 * Class long descroption
 *
 * @author Jani
 * @version 1.0
 * @since 1.8
 */
public class SoundManager {

    ArrayList<Sound> soundList;
    GameAdapter host;

    public SoundManager(GameAdapter host) {
        this.host = host;
        soundList = new ArrayList<>();
    }

    public void addSound(File soundFile) {
        soundList.add(new Sound(soundFile));
    }

    public void addSound(String path) {
        soundList.add(new Sound(path));
    }

    public void addSound(Sound sound) {
        soundList.add(sound);
    }

    public void playSound(int index) {
        soundList.get(index).play();
    }

    public Sound getSound(int index) {
        return soundList.get(index);
    }
}
