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
    BufferedImage[] animationList;
    boolean playing = false;

    public void Animation(GameObject host) {
        this.host = host;
    }

    public void splitSheet(BufferedImage spriteSheet,
                           int height,
                           int width,
                           int rows,
                           int columns) {

        animationList = new BufferedImage[rows * columns];

        for (int r = 0; r < rows; r++) {

            for (int c = 0; c < columns; c++) {

                BufferedImage subImage = spriteSheet.getSubimage(
                        r * width,
                        c * height,
                        width,
                        height
                );

                animationList[r * columns] = subImage;
            }
        }
    }
}
