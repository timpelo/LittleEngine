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

    //0 - add, 1 - select, 2 - delete
    private int tool= 0;

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
                host.getSelectedTile().getImage(),
                host.getSelectedTile().getFilename()
        );

        tileList.add(added);
        repaint();
    }

    private void selectTile(MouseEvent e) {
        boolean found = false;
        int index = 0;
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

            index++;
        }

    }

    public LinkedList<Tile> getTileList() {

        return tileList;
    }

    public void setTool(int tool) {
        this.tool = tool;
    }
}
