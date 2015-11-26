package tiko.engine.gui;

import tiko.engine.gameobject.Drawable;
import tiko.engine.gameobject.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Canvas used to draw all game objects to the screen.
 * <p>
 * Canvas holds all objects for drawing. It also draws all objects to the
 * screen.
 *
 * @author Jani Timonen
 * @version 1.0
 * @since 1.8
 */
public class Canvas extends JPanel implements Dimensional {

    /**
     * List containing all drawable objects.
     */
    LinkedList<Drawable> objectList;

    /**
     * Screen which in this canvas is located.
     */
    Screen host;

    /**
     * Constructor for this object.
     *
     * @param host Screen which in this canvas is located.
     */
    public Canvas(Screen host) {
        objectList = new LinkedList<>();
        this.host = host;
    }

    /**
     * Draws all components form list.
     *
     * Draws all components from list by using camera position and creating
     * illusion of camera movement.
     *
     * @param g Graphics object used for drawing.
     */
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);

        for (Drawable o : objectList) {
            o.draw(g2, host.getCamera().getX(),
                    host.getCamera().getY());
        }
    }

    /**
     * Adds drawable object to the list.
     *
     * @param o GameObject which will be added to the list.
     */
    public void addObject(GameObject o) {
        objectList.add(o);
    }
}
