package tiko.indianaGame;

import tiko.engine.gameobject.GameObject;

/**
 * Class short description
 * <p>
 * Class long descroption
 *
 * @author Jani
 * @version 1.0
 * @since 1.8
 */
public class Lava extends GameObject{
    int offSetX = 20;
    boolean right = true;
    float newX = 0f;

    public Lava(int x, int y, String source) {
        super(x, y, source);
    }

    public void moveLava() {

        if(right) {

            if(getX() > offSetX) {
                right = false;
            } else {
                newX += 0.5f;
                setX((int)newX);
            }
        } else {
            if(getX() < -offSetX) {
                right = true;
            } else {
                newX -= 0.5f;
                setX((int)newX);
            }
        }
    }
}
