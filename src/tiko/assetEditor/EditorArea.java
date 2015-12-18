package tiko.assetEditor;

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
 * @version 1.3
 * @since 1.8
 */
public class EditorArea extends JPanel{

    /**
     * Editor where this EditorArea is located.
     */
    private Editor host;

    /**
     * List of all assets in editor.
     */
    private LinkedList<Asset> assetList;

    /**
     * Index of selected asset.
     */
    private Asset selectedAsset;

    /**
     * Index of selected tool.
     *
     * 0 - add, 1 - select, 2 - delete
     */
    private int tool = 0;

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
        assetList = new LinkedList<>();
        setFocusable(true);
        addMouseListener(new MouseAdapter() {

            /**
             * Executes when mouse button is pressed.
             *
             * @param e MouseEvent containing data from action.
             */
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);

                // Changes tool to correspond pressed tool button.
                switch (tool) {
                    case 1:
                        selectAsset(e);
                        break;
                    case 2:
                        selectAsset(e);
                        assetList.remove(selectedAsset);
                        repaint();
                        break;
                    case 0:
                        addAsset(e.getX(), e.getY());
                        break;
                }
            }

            /**
             * Executes when mouse button is released.
             *
             * @param e MouseEvent containing data from action.
             */
            public void mouseReleased(MouseEvent e) {
                selectedAsset = null;
            }
        });

        addMouseMotionListener(new MouseAdapter() {

            /**
             * Executes when mouse is dragged.
             *
             * @param e MouseEvent containing data from action.
             */
            public void mouseDragged(MouseEvent e) {
                System.out.println(selectedAsset);

                // If there is selected asset, ti will be dragged according
                // mouse pointer movement.
                if (selectedAsset != null) {
                    selectedAsset.setY(e.getY());
                    selectedAsset.setX(e.getX());
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

        for (Asset asset : assetList) {

            // Draws all assets from the list to editor area.
            g.drawImage(
                    asset.getImage(),
                    asset.getX() - (asset.getImage().getWidth() / 2),
                    asset.getY() - (asset.getImage().getHeight() / 2),
                    null
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
    private void addAsset(int x, int y) {

        // Adds new asset to drawing area according what selected asset is.
        Asset added = new Asset(
                x,
                y,
                host.getSelectedAsset().getImage(),
                host.getSelectedAsset().getFilename()
        );
        assetList.add(added);
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
    private void selectAsset(MouseEvent e) {
        boolean found = false;
        int index = assetList.size() - 1;

        // Selects asset from the list and draws rectangle to it for mouse
        // pointer detection.
        while (!found && index != assetList.size()) {
            Asset asset = assetList.get(index);
            Rectangle rect = new Rectangle(
                    asset.getX() - (asset.getImage().getWidth() / 2),
                    asset.getY() - (asset.getImage().getHeight() / 2),
                    asset.getImage().getWidth(),
                    asset.getImage().getHeight()
            );

            // Checks if mouse pointer has asset under it. If asset is found, it
            // will be set to selected asset.
            Point point = new Point(e.getX(), e.getY());

            if (rect.contains(point)) {
                selectedAsset = asset;
                found = true;
                System.out.println("selected" + selectedAsset);
            }

            index--;
        }
    }

    /**
     * Returns asset list containing all assets on screen.
     *
     * @return asset list containing all assets on screen.
     */
    public LinkedList<Asset> getAssetList() {

        return assetList;
    }

    /**
     * Sets selected tool for editor. Also changes colors of the buttons to
     * show what tool is currently selected (with yellow color).
     *
     * @param tool selected tool: 0 - add, 1 - select, 2 - delete
     */
    public void setTool(int tool) {
        this.tool = tool;

        switch (tool) {

            case 0:
                host.addTool.setBackground(Color.yellow);
                host.deleteTool.setBackground(Color.lightGray);
                host.moveTool.setBackground(Color.lightGray);
                break;
            case 1:
                host.addTool.setBackground(Color.lightGray);
                host.deleteTool.setBackground(Color.lightGray);
                host.moveTool.setBackground(Color.yellow);
                break;
            case 2:
                host.addTool.setBackground(Color.lightGray);
                host.deleteTool.setBackground(Color.yellow);
                host.moveTool.setBackground(Color.lightGray);
                break;
        }
    }
}
