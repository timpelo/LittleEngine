package tiko.engine.gui.tilemap;

import tiko.engine.gameobject.GameObject;
import tiko.engine.gui.Screen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

/**
 * AssetMap used to locate and draw assets from file.
 *
 * AssetMap contains all infor for assets. It contains info of position
 * and drawing.
 *
 * @author Jani
 * @since 1.8
 * @version 1.0
 */
public class TileMap {

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
    private ArrayList<Tile> tileList;

    /**
     * Default constructor.
     *
     * @param width width of map
     * @param height height of map
     */
    public TileMap(int width, int height) {
        tileList = new ArrayList<>();
        this.width = width;
        this.height = height;

    }

    /**
     * Load tiles for map.
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

            while((line = reader.readLine()) != null) {
                tileList.add(createTile(line));
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
    public Tile createTile(String tileInfo) {
        int x = 0;
        int y = 0;
        BufferedImage image = null;
        String temp = "";
        int counter = 0;

        for (int i = 0; i < tileInfo.length(); i++) {

            // Checks characters
            if(tileInfo.charAt(i) != ':') {
                temp += tileInfo.charAt(i);
            } else {

                // Places right value to right variable for tile creation.
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
                                    "assets/tile-map/" + temp.trim()));
                            temp = "";
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }

                counter++;
            }
        }
        //System.out.println(image);
        return new Tile(x, y, image);
    }

    /**
     * Draws assets from list.
     *
     * Gets location and image of asset and draws it to screen.
     *
     * @param screen screen where assets will be drawn.
     */
    public void drawMap(Screen screen) {

        for(Tile tile: tileList) {
            screen.addObject(new GameObject(
                    tile.getX(),
                    tile.getY(),
                    tile.getImage()));
        }
        screen.getCanvas().repaint();
    }
}
