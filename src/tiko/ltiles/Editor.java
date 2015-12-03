package tiko.ltiles;

import org.omg.SendingContext.RunTime;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
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
    JButton moveTool;
    JButton deleteTool;
    JButton addTool;
    JScrollPane assets;
    EditorArea editorArea;
    JTable assetWheel;
    LinkedList<Tile> assetList;
    int selectedTile = 0;
    int listIndex = 0;

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
        moveTool = new JButton("Move Tool");
        deleteTool = new JButton("Delete Tool");
        addTool = new JButton("Add Tool");

        load.addActionListener(e -> loadFile(e));
        open.addActionListener(e -> addTile(e));
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
        assetWheel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                pickTile(e);
            }
        });
        assets.add(assetWheel);

        //Create editor area
        editorArea = new EditorArea(this);
        editorArea.setBounds(0, 0, 2000, 2000);

        //Add all components to window
        add(editorArea, layout.CENTER);
        add(menu, layout.NORTH);
        add(assetWheel, layout.SOUTH);

        setFocusable(true);
    }

    public void loadFile(ActionEvent e) {

        final JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(this);

        if(e.getSource() == load) {
            File file = fc.getSelectedFile();
        }

    }

    public void addTile(ActionEvent e) {
        boolean success = false;
        BufferedImage tile;

        final JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(this);


        if(e.getSource() == open) {

            try {
                File file = fc.getSelectedFile();
                tile = ImageIO.read(file);
                success = true;
                assetList.add(new Tile(0, 0, tile, file.getName()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if(success) {
            assetWheel.setValueAt(listIndex + 1, 0, listIndex);
            listIndex++;
        }

    }

    public void saveFile(ActionEvent e) {
        final JFileChooser fc = new JFileChooser();
        fc.showSaveDialog(this);

        if(e.getSource() == save) {
            File file = fc.getSelectedFile();

            try(BufferedWriter writer = new BufferedWriter(
                    new FileWriter(file))) {

                for(Tile tile: editorArea.getTileList()) {
                    String tileInfo =
                            tile.getRealX() + ":" +
                            tile.getRealY() + ":" +
                            tile.getFilename() + ":";
                    writer.write(tileInfo);
                    writer.newLine();
                }

            } catch (IOException ex) {

            }
        }
    }

    public void pickTile(MouseEvent e) {

        if(e.getClickCount() == 1) {

            final JTable target = (JTable)e.getSource();
            final int row = target.getSelectedRow();
            final int col = target.getSelectedColumn();
            final int index = (int)target.getValueAt(row, col) - 1;
            selectedTile = index;
            System.out.println("Selected tile is" + index);
        }

    }

    public Tile getSelectedTile() {
        return assetList.get(selectedTile);
    }
}
