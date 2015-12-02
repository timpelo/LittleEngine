package tiko.engine.gui.tilemap;

import tiko.engine.gameobject.GameObject;
import tiko.engine.gui.Screen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
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
public class TileMap {

    private int width;
    private int height;
    private ArrayList<Tile> tileList;

    public TileMap(int width, int height) {
        tileList = new ArrayList<>();
        this.width = width;
        this.height = height;

    }

    public void loadTiles(String path) {

        try{
            BufferedReader reader = new BufferedReader(
                    new FileReader(path + "tiles/map.tl"));

            String line;

            while((line = reader.readLine()) != null) {
                tileList.add(createTile(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
                                    "assets/tiles/" + temp.trim() + ".png"));
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
