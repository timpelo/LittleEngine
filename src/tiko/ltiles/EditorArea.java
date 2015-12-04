package tiko.ltiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

/**
 * EditorArea is used to draw assets in editor.
 *
 * EditorArea has info of all assets drawn into screen. It also has info of
 * selected tile. It handles tool selector for editor.
 *
 * @author Jani
 * @since 1.8
 * @version 1.0
 */
public class EditorArea extends JPanel{

    /**
     * Editor where this EditorArea is located.
     */
    private Editor host;

    /**
     * List of all assets in editor.
     */
    private LinkedList<Tile> tileList;

    /**
     * Index of selected asset.
     */
    private Tile selectedTile;

    /**
     * Index represents selected tool
     *
     * 0 - add, 1 - select, 2 - delete
     */
    private int tool= 0;

    /**
     * Default constructor.
     *
     * Creates listeners for editor. Also creates list for storing all assets
     * on screen. This constructor creates anonymous MouseListeners. If mouse
     * is clicked, listener do action according selected tool. Select tool
     * selects asset. Move tool selects tool and moves it while mouse is
     * pressed and dragged. Delete tool removes asset from the list.
     *
     * @param host Editor where this editor is located.
     */
    public EditorArea(Editor host) {
        this.host = host;
        tileList = new LinkedList<>();
        setFocusable(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);

                switch (tool) {
                    case 1:
                        selectTile(e);
                        break;
                    case 2:
                        selectTile(e);
                        tileList.remove(selectedTile);
                        repaint();
                        break;
                    case 0:
                        addTile(e.getX(), e.getY());
                        break;

                }
            }

            public void mouseReleased(MouseEvent e) {
                selectedTile = null;
            }
        });

        addMouseMotionListener(new MouseAdapter() {

            public void mouseDragged(MouseEvent e) {
                System.out.println(selectedTile);
                if(selectedTile != null) {
                    selectedTile.setY(e.getY());
                    selectedTile.setX(e.getX());
                    repaint();
                }

            }
        });
    }

    /**
     * Draws all assets to screen.
     *
     * Gets all assets from list and draws them to screen using Graphics.
     *
     * @param g Graphics used for draw.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(Tile tile: tileList){
            g.drawImage(
                    tile.getImage(),
                    tile.getX() - (tile.getImage().getWidth() / 2),
                    tile.getY() - (tile.getImage().getHeight() / 2),
                    null);

            System.out.println("drawed " +
                    tile +
                    " to " +
                    tile.getX() +
                    ", " +
                    tile.getY()
            );
        }
    }

    /**
     * Adds new asset to screen.
     *
     * Creates new asset and adds it to list. Draws asset to screen. Asset
     * center is placed at mouse pointer location. Gets texture and file name
     * from assetList from host Editor.
     *
     * @param x x-coordinate for asset.
     * @param y y-coordinate for asset.
     */
    private void addTile(int x, int y) {

        Tile added = new Tile(
                x,
                y,
                host.getSelectedTile().getImage(),
                host.getSelectedTile().getFilename()
        );

        tileList.add(added);
        repaint();
    }

    /**
     * Selects asset from screen.
     *
     * Goes through all assets on screen. If mouse pointer is on asset, it
     * will become selected.
     *
     * @param e MouseEvent from mouse listener.
     */
    private void selectTile(MouseEvent e) {
        boolean found = false;
        int index = tileList.size() - 1;
        while(!found && index != tileList.size()) {
            Tile tile = tileList.get(index);
            System.out.println("tile:"  + tile);
            Rectangle rect = new Rectangle(
                    tile.getX() - (tile.getImage().getWidth() / 2),
                    tile.getY() - (tile.getImage().getHeight() / 2),
                    tile.getImage().getWidth(),
                    tile.getImage().getHeight()
            );

            Point point = new Point(e.getX(), e.getY());

            if(rect.contains(point)) {
                selectedTile = tile;
                found = true;
                System.out.println("selected" + selectedTile);
            }

            index--;
        }

    }

    /**
     * Returns asset list containing all assets on screen.
     *
     * @return asset list containing all assets on screen.
     */
    public LinkedList<Tile> getTileList() {

        return tileList;
    }

    /**
     * Sets selected tool for editor.
     *
     * @param tool selected tool: 0 - add, 1 - select, 2 - delete
     */
    public void setTool(int tool) {
        this.tool = tool;
    }
}
