package tiko.engine.system.sound;

import tiko.engine.system.GameAdapter;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Sound manager to handle all sounds in game.
 *
 * Stores and handles all sounds. Can play and add sounds to list.
 *
 * @author Jani Timonen
 * @version 1.0
 * @since 1.8
 */
public class SoundManager {

    /**
     * List containing all sounds for game.
     */
    ArrayList<Sound> soundList;

    /**
     * Game which this sound manager belongs to.
     */
    GameAdapter host;

    /**
     * Default constructor.
     *
     * @param host Game which this sound manager belongs to.
     */
    public SoundManager(GameAdapter host) {
        this.host = host;
        soundList = new ArrayList<>();
    }

    /**
     * Adds sound to the list using File.
     *
     * @param soundFile sound as File.
     */
    public void addSound(File soundFile) {
        soundList.add(new Sound(soundFile));
    }

    /**
     * Adds sound to the list using path as String.
     *
     * @param path path as String.
     */
    public void addSound(String path) {
        soundList.add(new Sound(path));
    }

    /**
     * Adds sound to the list using Sound.
     *
     * @param sound sound as Sound.
     */
    public void addSound(Sound sound) {
        soundList.add(sound);
    }

    /**
     * Plays sound using index of sound in the list.
     *
     * @param index index of sound in the list.
     */
    public void playSound(int index) {
        soundList.get(index).play();
    }

    /**
     * Returns sound with index.
     *
     * @param index index of the sound in the list.
     * @return Sound from given index.
     */
    public Sound getSound(int index) {
        return soundList.get(index);
    }
}
