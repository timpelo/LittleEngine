package tiko;

import tiko.engine.gui.Screen;
import tiko.engine.gui.ScreenManager;
import tiko.engine.gui.tilemap.TileMap;

/**
 * Class short description
 * <p>
 * Class long descroption
 *
 * @author Jani
 * @version 1.0
 * @since 1.8
 */
public class ChessBoard extends Screen {

    private TileMap board;

    public ChessBoard(ScreenManager mgr) {
        super(mgr);

        board = new TileMap(500, 500);

    }


    @Override
    public void run() {

    }
}
