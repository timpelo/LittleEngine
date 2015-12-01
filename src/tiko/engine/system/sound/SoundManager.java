package tiko.engine.system.sound;

import tiko.engine.system.GameAdapter;

import javax.sound.sampled.*;
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

    ArrayList<Clip> soundList;
    GameAdapter host;

    public SoundManager(GameAdapter host) {
        this.host = host;
        soundList = new ArrayList<>();
    }

    public void addSound(Clip sound) {
        soundList.add(sound);
    }

    public void addSound(String path) {

        URL url = this.getClass().getClassLoader().getResource("sound/" + path);
        Clip clip = null;
        try{
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
        } catch (IOException |
                UnsupportedAudioFileException |
                LineUnavailableException e) {

        }

        if(clip != null) {
            soundList.add(clip);
        }

    }
}
