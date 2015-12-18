package tiko.engine.system.save;

import java.io.*;

/**
 * Class which can handle saving and loading data from persistent storage.
 * <p>
 * Class can write and load SaveObjects from given location. Default directory
 * is set to root/save but it can be changed.
 *
 * @author Jani Timonen
 * @version 1.0
 * @since 1.8
 */
public abstract class SaveManager {

    /**
     * Name of save file.
     */
    String filename;

    /**
     * Default directory where file will be saved or loaded.
     */
    String defaultDirectory = "save/";

    /**
     * Default constructor.
     *
     * @param filename name of save file.
     */
    public SaveManager(String filename) {
        this.filename = filename;
    }

    /**
     * Saves given array to file using SaveObject.
     *
     * @param saveArray array of save data ([header][value]).
     */
    public void saveToFile(String[][] saveArray) {

        try {

            FileOutputStream save =
                    new FileOutputStream(new File(defaultDirectory + filename));
            ObjectOutputStream out = new ObjectOutputStream(save);
            SaveObject saveObject = new SaveObject(saveArray);
            out.writeObject(saveObject);
            System.out.println("Save successful!");
        } catch (IOException e) {
            System.out.println("Save was not successful");
            e.printStackTrace();
        }
    }

    /**
     * Loads given filename from default directory.
     *
     * Loads file and returns values in two dimensional String array.
     *
     * @param filename name of file.
     * @return saved values in two dimensional String array.
     */
    public String[][] loadFromFile(String filename) {
        String[][] loadArray = null;
        try {
            FileInputStream load =
                    new FileInputStream(new File(defaultDirectory + filename));

            ObjectInputStream in = new ObjectInputStream(load);
            // Gets object from input stream.
            Object obj = in.readObject();

            // Converts object to SaveObject and loads data from it.
            if (obj instanceof SaveObject) {
                SaveObject loadObject = (SaveObject) obj;
                loadArray = loadObject.toArray();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Load was not successful");
            e.printStackTrace();
        }

        return loadArray;
    }

    /**
     * Returns default directory of save location.
     *
     * @return default directory path as string.
     */
    public String getDefaultDirectory() {
        return defaultDirectory;
    }

    /**
     * Sets default directory of save location.
     *
     * @param defaultDirectory default directory path as string.
     */
    public void setDefaultDirectory(String defaultDirectory) {
        this.defaultDirectory = defaultDirectory;
    }
}
