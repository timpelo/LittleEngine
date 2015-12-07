package tiko.engine.system.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Sound class which contains sound.
 *
 * This class is used to play sound. It contains info if sound is set to loop.
 *
 * @author Jani Timonen
 * @version 1.0
 * @since 1.8
 */
public class Sound {

    /**
     * Contains sound file.
     */
    private File soundFile;

    /**
     * Represents if sound is set to play in loop.
     */
    private boolean loop = false;

    /**
     * Constructor to make object by using File.
     *
     * @param soundFile sound as File.
     */
    public Sound(File soundFile) {
        this.soundFile = soundFile;
    }

    /**
     * Constructor to make object by using path.
     *
     * @param path path as String.
     */
    public Sound(String path) {
        soundFile = new File("assets/sound/" + path);
    }

    /**
     * Plays the sound.
     *
     * Plays the sound by transforming sound file to clip. Creates listener
     * to check when sound is finished. If sound is not set to loop, listener
     * will close clip, otherwise it will play it again.
     */
    public void play() {

        try {
            // Loads playable clip.
            final Clip clip = (Clip)AudioSystem.getLine(
                    new Line.Info(Clip.class));

            // Adds listener which closes clip after it is played.
            clip.addLineListener((e) -> {
                    if (e.getType() == LineEvent.Type.STOP) {
                        if (!loop) {
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

    /**
     * Sets loop for sound file.
     *
     * @param loop true - sound is looping, false sound is not looping.
     */
    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}
