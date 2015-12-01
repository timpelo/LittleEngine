package tiko.engine.system.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
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
    private File soundFile;
    private boolean loop = false;

    public Sound(File soundFile) {
        this.soundFile = soundFile;
    }

    public Sound(String path) {
        soundFile = new File("sound/" + path);
    }

    public void play() {

        try {
            final Clip clip = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));

            clip.addLineListener((e) -> {
                    if(e.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
            });

            clip.open(AudioSystem.getAudioInputStream(soundFile));
            clip.start();

        } catch (LineUnavailableException e) {
            System.out.println("Line is unavailable");
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Sound file is not supported");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}
