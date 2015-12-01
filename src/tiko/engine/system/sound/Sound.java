package tiko.engine.system.sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * Class short description
 * <p>
 * Class long descroption
 *
 * @author Jani
 * @version 1.0
 * @since 1.8
 */
public class Sound {
    private Clip clip;

    public Sound(Clip clip) {
        this.clip = clip;
    }

    public Sound(String path) {
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
            this.clip = clip;
        }
    }
}
