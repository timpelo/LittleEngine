package tiko.engine.system.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Class short description
 * <p>
 * Class long descroption
 *
 * @author Jani Timonen
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
        soundFile = new File("assets/sound/" + path);
    }

    public void play() {

        try {
            // Loads playable clip.
            final Clip clip = (Clip)AudioSystem.getLine(
                    new Line.Info(Clip.class));

            // Adds listener which closes clip after it is played.
            clip.addLineListener((e) -> {
                    if(e.getType() == LineEvent.Type.STOP) {
                        if(!loop) {
                            clip.close();
                        } else {
                            play();
                        }
                    }
            });

            // Opens clip for audio stream and plays it.
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
