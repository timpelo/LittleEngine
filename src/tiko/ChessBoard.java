package tiko;

import tiko.engine.gameobject.GameObject;
import tiko.engine.gui.Screen;
import tiko.engine.gui.ScreenManager;
import tiko.engine.gui.assetmap.TileMap;

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
    DemoGame game;

    public ChessBoard(ScreenManager mgr, DemoGame game) {
        super(mgr);
        this.game = game;
        addObject(new GameObject(0, 0, "assets/mountain.jpg"));
        board = new TileMap(500, 500);
        board.loadTiles("assets/");
        board.drawMap(this);

    }

    @Override
    public void run() {

    }
}
