package tiko.engine.system.animation;

import tiko.engine.gameobject.GameObject;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Class short description
 * <p>
 * Class long descroption
 *
 * @author Jani
 * @version 1.0
 * @since 1.8
 */
public class Animation {

    GameObject host;
    ArrayList<BufferedImage> animationList;
    BufferedImage spriteSheet;

    public void Animation(GameObject host) {
        this.host = host;
    }
}
