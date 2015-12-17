package tiko.indianaGame;

import tiko.engine.gameobject.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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
public class Boss extends GameObject {
    private int health = 100;
    private GameScreen host;
    private GameObject healthbar;
    private BufferedImage healthTexture = null;

    public Boss(int x, int y, String textureSource, GameScreen host) {
        super(x, y, textureSource);
        this.host = host;

        try {
            healthTexture = ImageIO.read(new File("assets/healthbar" +
                    ".png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setHealth(int health) {
        this.health = health;

        if(health <= 0) {
            destroy();
        } else {
            BufferedImage newHealth = healthTexture.getSubimage(
                    0,
                    0,
                    health,
                    healthTexture.getHeight());

            healthTexture = newHealth;
            healthbar.setTexture(healthTexture);
        }
    }

    public int getHealth() {
        return health;
    }

    public void createHealthBar() {

        healthbar = new GameObject(
                (int)getCenter().getX() - (healthTexture.getWidth() / 2),
                (int)getCenter().getY() - 200,
                healthTexture);

        host.addObject(healthbar);
    }

    private void destroy() {
        host.world.removeObject(this);
        host.removeObject(healthbar);
        host.removeObject(this);
        healthTexture = null;
        setTexture(null);
    }
}
