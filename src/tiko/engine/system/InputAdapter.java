package tiko.engine.system;

import java.awt.event.*;

/**
 * InputAdapter used to get player input from game.
 *
 * @author Jani Timonen
 * @version 1.0
 * @since 1.8
 */
public abstract class InputAdapter implements MouseListener, KeyListener,
        MouseWheelListener {

    /**
     * Called when key is typed.
     *
     * @param e Contains info of key event.
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Called when key is pressed.
     *
     * @param e Contains info of key event.
     */
    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * Called when key is released.
     *
     * @param e Contains info of key event.
     */
    @Override
    public void keyReleased(KeyEvent e){

    }

    /**
     * Called when mouse is clicked.
     *
     * @param e Contains info of mouse event.
     */
    @Override
    public void mouseClicked(MouseEvent e){

    }

    /**
     * Called when mouse is pressed.
     *
     * @param e ontains info of mouse event.
     */
    @Override
    public void mousePressed(MouseEvent e){

    }

    /**
     * Called when mouse is released.
     *
     * @param e ontains info of mouse event.
     */
    @Override
    public void mouseReleased(MouseEvent e){

    }

    /**
     * Called when mouse enters component.
     *
     * @param e Contains info of mouse event.
     */
    @Override
    public void mouseEntered(MouseEvent e){

    }

    /**
     * Called when mouse exits from component.
     *
     * @param e Contains info of mouse event.
     */
    @Override
    public void mouseExited(MouseEvent e){

    }

    /**
     * Called when mouse wheel is moved.
     *
     * @param e Contains info of mouse wheel event.
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e){

    }
}
