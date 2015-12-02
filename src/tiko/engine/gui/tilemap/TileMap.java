package tiko.engine.gui.tilemap;

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

    private int widht;
    private int height;
    private ArrayList<Tile> tileList;

    public TileMap(int widht, int height) {
        tileList = new ArrayList<>();
        this.widht = widht;
        this.height = height;

    }

    public void loadTiles() {

    }
}
