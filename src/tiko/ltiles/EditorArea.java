package tiko.ltiles;

import tiko.engine.gui.tilemap.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

/**
 * Class short description
 * <p>
 * Class long descroption
 *
 * @author Jani
 * @version 1.0
 * @since 1.8
 */
public class EditorArea extends JPanel{
    private Editor host;
    private LinkedList<Tile> tileList;
    private Tile selectedTile;
    private boolean moveTool = false;

    public EditorArea(Editor host) {
        this.host = host;
        tileList = new LinkedList<>();
        setFocusable(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if(moveTool) {
                    selectedTile = selectTile();
                } else {
                    addTile(e.getX(), e.getY());
                }
            }

            public void mouseMoved(MouseEvent e) {


            }

            public void mouseReleased(MouseEvent e) {


            }
        });
    }

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

    private void addTile(int x, int y) {

        Tile added = new Tile(
                x,
                y,
                host.getSelectedTile().getImage()
        );

        tileList.add(added);
        repaint();
    }

    private Tile selectTile() {
        Tile result = null;


        return result;
    }
}
