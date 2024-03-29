package tiko.assetEditor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Editor contains all components for editing premade level maps for
 * LittleEngine.
 *
 * Class contains all buttons and panels for editor. It has menu for loading,
 * saving and adding assets. Menu also contains buttons for different tools.
 * This class also handles saving. Loading premade map is not yet functional.
 *
 * @author Jani
 * @version 1.0
 * @since 1.8
 */
public class Editor extends JFrame {

    /**
     * Layout manager used for editor.
     */
    BorderLayout layout;

    /**
     * Menu panel.
     */
    JPanel menu;

    /**
     * Load button.
     */
    JButton load;

    /**
     * Save button.
     */
    JButton save;

    /**
     * Open asset button.
     */
    JButton open;

    /**
     * Move Tool button.
     */
    JButton moveTool;

    /**
     * Delete Tool button.
     */
    JButton deleteTool;

    /**
     * Add asset button.
     */
    JButton addTool;

    /**
     * Asset menu.
     */
    JScrollPane assets;

    /**
     * Editor area used for drawing.
     */
    EditorArea editorArea;

    /**
     * Asset wheel for showing all loaded assets.
     */
    JTable assetWheel;

    JTextArea selectedAssetName;

    /**
     * List containing all loaded assets.
     */
    LinkedList<Asset> assetList;

    /**
     * Index of selected asset in list.
     */
    int selectedAsset = 0;

    /**
     * Amount of loaded assets.
     */
    int listIndex = 0;

    /**
     * Default constructor.
     *
     * Creates all components for editor. Creates listeners for buttons. Sets
     * asset wheel so it cannot be edited manually. Also adds titles and
     * bounds for all components and adds all components to this frame.
     *
     * @param title title for editor.
     */
    public Editor(String title) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(title);
        setSize(1000, 1000);
        setVisible(true);
        layout = new BorderLayout();
        setLayout(layout);
        assetList = new LinkedList<>();

        // Create menu bar and buttons.
        menu = new JPanel();
        load = new JButton("Load");
        save = new JButton("Save");
        open = new JButton("Add asset");
        moveTool = new JButton("Move Tool");
        deleteTool = new JButton("Delete Tool");
        addTool = new JButton("Add Tool");
        selectedAssetName = new JTextArea("selected");
        selectedAssetName.setEditable(false);
        load.addActionListener(e -> loadFile(e));
        open.addActionListener(e -> addAsset(e));
        save.addActionListener(e -> saveFile(e));
        moveTool.addActionListener(e -> editorArea.setTool(1));
        deleteTool.addActionListener(e -> editorArea.setTool(2));
        addTool.addActionListener(e -> editorArea.setTool(0));
        menu.add(load);
        menu.add(save);
        menu.add(open);
        menu.add(moveTool);
        menu.add(deleteTool);
        menu.add(addTool);
        menu.add(selectedAssetName);
        addTool.setBackground(Color.yellow);
        deleteTool.setBackground(Color.lightGray);
        moveTool.setBackground(Color.lightGray);

        // Create asset list.
        assets = new JScrollPane();

        // Create non editable table.
        assetWheel = new JTable(1, 30) {

            /**
             * Serialization UID.
             */
            private static final long serialVersionUID = 1L;

            /**
             * Executes when table cell is selected.
             *
             * @param row row of cell
             * @param column column of cell
             * @return true - allow editing, false - prevent editing.
             */
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        assetWheel.setRowHeight(50);
        assetWheel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                pickAsset(e);
            }
        });
        assets.add(assetWheel);

        // Create editor area
        editorArea = new EditorArea(this);
        editorArea.setBounds(0, 0, 2000, 2000);

        // Add all components to window
        add(editorArea, layout.CENTER);
        add(menu, layout.NORTH);
        add(assetWheel, layout.SOUTH);

        setFocusable(true);
    }

    /**
     * Loads file for premade map (NYI).
     *
     * @param e ActionEvent from button.
     */
    public void loadFile(ActionEvent e) {

        final JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(this);

        if (e.getSource() == load) {
            File file = fc.getSelectedFile();
        }
    }

    /**
     * Adds asset to asset wheel and asset list.
     *
     * Opens file chooser. Selected asset (image file) will be added to list
     * and asset wheel.
     *
     * @param e ActionEvent from button.
     */
    public void addAsset(ActionEvent e) {
        boolean success = false;
        BufferedImage asset;

        // Opens file chooser with open button.
        final JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(this);
        File file = null;

        if (e.getSource() == open) {

            // Gets selected file from file chooser and adds it to asset list.
            try {
                file = fc.getSelectedFile();
                asset = ImageIO.read(file);
                success = true;
                assetList.add(new Asset(0, 0, asset, file.getName()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        // If asset is found, set it to asset wheel with name of loaded asset
        // file name.
        if (success) {
            assetWheel.setValueAt(file.getName(), 0, listIndex);
            listIndex++;
        }
    }

    /**
     * Saves map to file.
     *
     * Saves created map to file. Name of file should be assets.map so
     * LittleEngine can read file and generate it to map.
     *
     * @param e ActionEvent from button.
     */
    public void saveFile(ActionEvent e) {

        // Opens file chooser with save button.
        final JFileChooser fc = new JFileChooser();
        fc.showSaveDialog(this);

        if (e.getSource() == save) {
            File file = fc.getSelectedFile();

            try(BufferedWriter writer = new BufferedWriter(
                    new FileWriter(file))) {

                // Generates save file to contain all info of assets and
                // their placements on the map.
                for (Asset asset : editorArea.getAssetList()) {
                    String tileInfo =
                            asset.getRealX() + ":" +
                            asset.getRealY() + ":" +
                            asset.getFilename() + ":";
                    writer.write(tileInfo);
                    writer.newLine();
                }
            } catch (IOException ex) {

            }
        }
    }

    /**
     * Picks asset to selected tile for editor.
     *
     * Selected asset is stored as index number.
     *
     * @param e MouseEvent from mouse click.
     */
    public void pickAsset(MouseEvent e) {

        if (e.getClickCount() == 1) {

            // Checks what cell of JTable has been clicked. If it has asset,
            // it will be made to selected asset and shown in right up corner
            // of editor.
            final JTable target = (JTable)e.getSource();
            final int index = target.getSelectedColumn();
            selectedAsset = index;
            System.out.println("Selected tile is" + index);

            String selected = (String)target.getValueAt(0, index);
            selectedAssetName.setText(selected);
        }
    }

    /**
     * Returns index number of selected asset in list.
     *
     * @return index number of selected asset in list.
     */
    public Asset getSelectedAsset() {
        return assetList.get(selectedAsset);
    }
}
