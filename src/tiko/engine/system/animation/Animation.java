package tiko.engine.system.animation;

import tiko.engine.gameobject.GameObject;
import tiko.engine.system.Time;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class used to handle animation playing.
 *
 * Class contains spritesheet of animation. It can be set to change image
 * every x seconds. It can also be set to loop.
 *
 * @author Jani Timonen
 * @version 1.3
 * @since 1.8
 */
public class Animation {

    /**
     * Gameobject this animation belongs to.
     */
    GameObject host;

    /**
     * List containing splitted spritesheet.
     */
    BufferedImage[] animationList;

    /**
     * Represent if this animation is playing.
     */
    boolean isPlaying = true;

    /**
     * Represent is this animation set to play in loop.
     */
    boolean loop = true;

    /**
     * Speed of animation.
     */
    private float animationSpeed;

    /**
     * Timer used to calculate switch of images.
     */
    private float timer;

    /**
     * Current image showing in animation cycle.
     */
    private int currentIndex;

    /**
     * Default constructor.
     *
     * @param host gameobject this animation belongs to.
     * @param animationSpeed speed of this animation.
     */
    public Animation(GameObject host, float animationSpeed) {
        this.host = host;
        this.animationSpeed = animationSpeed;
        timer = 0;
        currentIndex = 0;
    }

    /**
     * Splits spritesheet into subimages for animation.
     *
     * @param spriteSheetSource source to spritesheet as String.
     * @param height height of a single image.
     * @param width width of a single image.
     * @param rows rows in spritesheet.
     * @param columns columns in spritesheet.
     */
    public void splitSheet(String spriteSheetSource,
                           int height,
                           int width,
                           int rows,
                           int columns) {

        // Loads spritesheet.
        BufferedImage spriteSheet = null;

        try {
            spriteSheet =
                    ImageIO.read(new File(spriteSheetSource));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Creates new array for subimages.
        animationList = new BufferedImage[rows * columns];
        int index = 0;

        // Splits spritesheet to subimages and adds them to list.
        for (int r = 0; r < rows; r++) {

            for (int c = 0; c < columns; c++) {
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

    /**
     * Updates new subimage for animation.
     *
     * Checks if timer has reached given value. Then it will select next
     * image from list and set that as image for animation cycle.
     */
    public void update() {

        if (isPlaying) {
            timer += Time.deltaTime();

            if (timer > animationSpeed) {

                // Replays animation if it has ended and animation is set to
                // play in loop.
                if (currentIndex >= animationList.length && loop) {
                    currentIndex = 0;
                } else if (currentIndex >= animationList.length) {
                    isPlaying = false;
                }

                // Changes texture and resets timer.
                host.setTexture(animationList[currentIndex]);
                currentIndex++;
                timer = 0f;
            }
        }
    }
}
