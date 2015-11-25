package tiko.engine.system.save;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class which keeps saved information.
 *
 * @author Jani Timonen
 * @since 1.8
 * @version 1.0
 */
public class SaveObject implements Serializable {

    /**
     * ArrayList containing all save information.
     *
     * Contains data in form HEADER:VALUE
     */
    ArrayList<String> list;

    /**
     * SerialVersionUID used for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     *
     * Uses given two dimensional array to form ArrayList for saving.
     *
     * @param saveList save values in two dimensional array.
     */
    public SaveObject(String[][] saveList) {
        list = new ArrayList<>();

        for(int i = 0; i < saveList.length; i++) {

            String saveString = saveList[i][0] + ":" + saveList[i][1];
            list.add(saveString);
        }
    }

    /**
     * Converts ArrayList of save data to two dimensional String array.
     *
     * @return two dimensional String array of saved values.
     */
    public String[][] toArray() {
        String[][] result = new String[list.size()][2];
        int index = 0;

        for(String s: list) {
            String header = "";
            String value;
            String temp = "";
            s.trim();

            for(int i = 0; i < s.length(); i++) {

                if(s.charAt(i) == ':') {
                    header = temp;
                    temp = "";
                } else {
                    temp += s.charAt(i);
                }

            }

            value = temp;
            result[index][0] = header;
            result[index][1] = value;
            index++;
        }

        return result;

    }
}
