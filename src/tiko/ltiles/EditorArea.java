package tiko.ltiles;

import tiko.engine.gui.tilemap.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
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
    boolean moveTool = false;

    public EditorArea(Editor host) {
        this.host = host;
        tileList = new LinkedList<>();
        setFocusable(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if (moveTool) {
                    selectedTile = selectTile(e);
                } else {
                    addTile(e.getX(), e.getY());
                }
            }

            public void mouseReleased(MouseEvent e) {
                selectedTile = null;
            }
        });

        addMouseMotionListener(new MouseAdapter() {

            public void mouseDragged(MouseEvent e) {
                System.out.println("Mouse dragged to " + e.getX() + ", " + e
                        .getY());
                if(selectedTile != null) {
                    selectedTile.setY(e.getY());
                    selectedTile.setX(e.getX());
                    repaint();
                }

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

    private Tile selectTile(MouseEvent e) {
        Tile result = null;
        boolean found = false;
        int index = 0;
        while(!found && index != tileList.size()) {
            Tile tile = tileList.get(index);
            System.out.println("tile:"  + tile);
            Rectangle rect = new Rectangle(
                    tile.getX(),
                    tile.getY(),
                    tile.getImage().getWidth(),
                    tile.getImage().getHeight()
            );

            if(rect.contains(e.getX(), e.getY())) {
                selectedTile = tile;
                found = true;
            }

            index++;
        }
        System.out.println(result);
        return result;
    }
}
