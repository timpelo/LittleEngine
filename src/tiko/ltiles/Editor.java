package tiko.ltiles;

import org.omg.SendingContext.RunTime;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
public class Editor extends JFrame {

    BorderLayout layout;
    JPanel menu;
    JButton load;
    JButton save;
    JButton open;
    JScrollPane assets;
    EditorArea editorArea;
    JTable assetWheel;
    LinkedList<Tile> assetList;

    public Editor(String title) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(title);
        setSize(1000, 1000);
        setVisible(true);
        layout = new BorderLayout();
        setLayout(layout);
        assetList = new LinkedList<>();

        //Create menu bar and buttons.
        menu = new JPanel();
        load = new JButton("Load");
        save = new JButton("Save");
        open = new JButton("Add tile");

        load.addActionListener(e -> loadFile(e));
        open.addActionListener(e -> addTile(e));
        save.addActionListener(e -> saveFile(e));
        menu.add(load);
        menu.add(save);
        menu.add(open);

        // Create tile list.
        assets = new JScrollPane();

        // Create non editable table.
        assetWheel = new JTable(1, 30) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        assetWheel.setRowHeight(50);
        assets.add(assetWheel);

        //Create editor area
        editorArea = new EditorArea();
        editorArea.setBounds(0, 0, 2000, 2000);

        //Add all components to window
        add(editorArea, layout.CENTER);
        add(menu, layout.NORTH);
        add(assetWheel, layout.SOUTH);

        setFocusable(true);
    }

    public void loadFile(ActionEvent e) {

        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);

        if(e.getSource() == load) {
            File file = fc.getSelectedFile();
        }

    }

    public void addTile(ActionEvent e) {
        boolean success = false;
        BufferedImage tile = null;

        final JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(this);


        if(e.getSource() == open) {

            try {
                File file = fc.getSelectedFile();
                tile = ImageIO.read(file);
                success = true;
                assetList.add(new Tile(0, 0, tile));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if(success) {

            assetWheel.setValueAt(tile, 0, 0);
        }

    }

    public void saveFile(ActionEvent e) {
        final JFileChooser fc = new JFileChooser();
        fc.showSaveDialog(this);

        if(e.getSource() == save) {

        }
    }
}
