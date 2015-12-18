package tiko.engine.gui.assetmap;

import tiko.engine.gameobject.GameObject;
import tiko.engine.gui.Screen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

/**
 * AssetMap used to locate and draw assets from file.
 *
 * AssetMap contains all info for assets. It contains info of position
 * and drawing.
 *
 * @author Jani
 * @version 1.0
 * @since 1.8
 *
 */
public class AssetMap {

    /**
     * Width of asset map.
     */
    private int width;

    /**
     * Height of asset map.
     */
    private int height;

    /**
     * List containing all assets in map.
     */
    private ArrayList<Asset> assetList;

    /**
     * Default constructor.
     *
     * @param width width of map
     * @param height height of map
     */
    public AssetMap(int width, int height) {
        assetList = new ArrayList<>();
        this.width = width;
        this.height = height;
    }

    /**
     * Loads tiles for map.
     *
     * Load tiles from file. File has to be in certain location.
     * (asset-map/assets.map). It can be done with editor.
     *
     * @param path path to asset-map folder.
     */
    public void loadTiles(String path) {

        try{
            BufferedReader reader = new BufferedReader(
                    new FileReader(path + "asset-map/assets.map"));

            String line;

            while ((line = reader.readLine()) != null) {
                assetList.add(createTile(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates tile with given asset info String.
     *
     * String has to be in form x:y:image: This stores assets to list.
     *
     * @param tileInfo tile info in form x:y:image:.
     * @return created Asset.
     */
    public Asset createTile(String tileInfo) {
        int x = 0;
        int y = 0;
        BufferedImage image = null;
        String temp = "";
        int counter = 0;

        for (int i = 0; i < tileInfo.length(); i++) {

            // Checks characters
            if (tileInfo.charAt(i) != ':') {
                temp += tileInfo.charAt(i);
            } else {

                // Places correct value to correct variable for asset creation.
                switch (counter) {
                    case 0:
                        x = Integer.parseInt(temp);
                        temp = "";
                        break;
                    case 1:
                        y = Integer.parseInt(temp);
                        temp = "";
                        break;
                    case 2:
                        try{
                            System.out.println(temp);
                            image = ImageIO.read(new File(
                                    "assets/asset-map/" + temp.trim()));
                            temp = "";
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }

                counter++;
            }
        }

        return new Asset(x, y, image);
    }

    /**
     * Draws assets from list.
     *
     * Gets location and image of asset and draws it to screen.
     *
     * @param screen screen where assets will be drawn.
     */
    public void drawMap(Screen screen) {

        // Iterates asset list and draws assets according their coordinates
        // and textures.
        for (Asset asset : assetList) {
            screen.addObject(new GameObject(
                    asset.getX(),
                    asset.getY(),
                    asset.getImage()));
        }

        screen.getCanvas().repaint();
    }
}
