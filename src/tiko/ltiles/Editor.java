package tiko.ltiles;

import org.omg.SendingContext.RunTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

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
    JTable assetList;

    public Editor(String title) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(title);
        setSize(1000, 1000);
        setVisible(true);
        layout = new BorderLayout();
        setLayout(layout);

        //Create menu bar and buttons.
        menu = new JPanel();
        load = new JButton("Load");
        save = new JButton("Save");
        open = new JButton("Add tile");

        load.addActionListener(e -> loadFile(e));
        menu.add(load);
        menu.add(save);
        menu.add(open);

        //Create tile list
        assets = new JScrollPane();
        assetList = new JTable(1, 30);
        assetList.setRowHeight(50);
        assets.add(assetList);

        //Create editor area
        editorArea = new EditorArea();
        editorArea.setBounds(0, 0, 2000, 2000);

        //Add all components to window
        add(editorArea, layout.CENTER);
        add(menu, layout.NORTH);
        add(assetList, layout.SOUTH);

        setFocusable(true);
    }

    public void loadFile(ActionEvent e) {

        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);

        if(e.getSource() == load) {
            File file = fc.getSelectedFile();
        }

    }
}
