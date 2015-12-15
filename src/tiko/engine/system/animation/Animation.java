package tiko.engine.system.animation;

import tiko.engine.gameobject.GameObject;
import tiko.engine.system.Time;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    boolean isPlaying = false;
    boolean loop = false;
    private float animationSpeed;
    private float timer;
    private int currentIndex;

    public Animation(GameObject host, float animationSpeed) {
        this.host = host;
        this.animationSpeed = animationSpeed;
        timer = 0;
        currentIndex = 0;
    }

    public void splitSheet(String spriteSheetSource,
                           int height,
                           int width,
                           int rows,
                           int columns) {

        BufferedImage spriteSheet = null;

        try {
            spriteSheet =
                    ImageIO.read(new File(spriteSheetSource));
        } catch (IOException e) {
            e.printStackTrace();
        }

        animationList = new BufferedImage[rows * columns];
        int index = 0;

        for (int r = 0; r < rows; r++) {

            for (int c = 0; c < columns; c++) {

                /*System.out.println("x:" + r*height);
                System.out.println("y:" + c*width );
                System.out.println("width:" + width );
                System.out.println("height:" + height);*/

                BufferedImage subImage = spriteSheet.getSubimage(
                        c * width,
                        r * height,
                        width,
                        height
                );

                animationList[index] = subImage;
                index++;
            }
        }
    }

    public void update() {
        timer += Time.deltaTime();
        System.out.println(timer);

        if(timer > animationSpeed) {

            if(currentIndex == animationList.length) {
                currentIndex = 0;
            }
            host.setTexture(animationList[currentIndex]);
            currentIndex++;
            timer = 0f;

        }
    }
}
