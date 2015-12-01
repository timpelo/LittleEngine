package tiko;


import tiko.engine.gameobject.GameObject;
import tiko.engine.gui.Camera;
import tiko.engine.gui.Screen;
import tiko.engine.gui.ScreenManager;
import tiko.engine.system.InputAdapter;
import tiko.engine.system.physics.Collider;
import tiko.engine.system.physics.PhysicsBody;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Jani on 25.11.2015.
 */
public class GameScreen extends Screen {

    DemoGame host;
    GameObject player;
    GameObject bg;
    GameObject bomb;

    boolean rightPressed = false;
    boolean leftPressed = false;
    boolean upPressed = false;
    boolean downPressed = false;

    /**
     * Constructor for this class.
     *
     * @param screenManager screen manager where this screen is located.
     */
    public GameScreen(ScreenManager screenManager, DemoGame host) {
        super(screenManager);
        this.host = host;

        player = new GameObject(100, 100, "assets/car.png");
        bomb = new GameObject(600, 600, "assets/bomb.png");


        PhysicsBody playerBody = new PhysicsBody(
                new Collider(new Rectangle(100, 100, 100, 100)),
                0,
                0,
                0,
                false
        );

        PhysicsBody bombBody = new PhysicsBody(
                new Collider(new Rectangle(600, 600, 100, 100)),
                0,
                0,
                0,
                false
        );

        player.setPhysicsBody(playerBody);
        bomb.setPhysicsBody(bombBody);

        bg = new GameObject(0, 0, "assets/bg.jpg");

        addObject(bg);
        addObject(player);
        addObject(bomb);

        getCanvas().addKeyListener(new InputAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    rightPressed = true;
                }

                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    leftPressed = true;
                }

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    downPressed = true;
                }

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    upPressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    rightPressed = false;
                }

                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    leftPressed = false;
                }

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    downPressed = false;
                }

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    upPressed = false;
                }
            }
        });
    }

    @Override
    public void run() {

        if(player.getPhysicsBody().get().checkCollision(bomb.getPhysicsBody()
                .get().getCollider())) {
            System.out.println("BUM!!!");
        }


        if(rightPressed) {
            player.setX(player.getX() + player.getSpeed());
            Camera camera = host.activeScreen.getCamera();

            Point cameraCenter = camera.getCenter();
            Point playerCenter = player.getCenter();

            if(playerCenter.getX() > cameraCenter.getX()) {
                camera.moveCameraX(camera.getX() + player.getSpeed());
            }

            host.activeScreen.getCanvas().repaint();
        }

        if(leftPressed) {

            player.setX(player.getX() - player.getSpeed());
            Camera camera = host.activeScreen.getCamera();

            Point cameraCenter = camera.getCenter();
            Point playerCenter = player.getCenter();

            if(playerCenter.getX() < cameraCenter.getX()) {
                camera.moveCameraX(camera.getX() - player.getSpeed());
            }

            host.activeScreen.getCanvas().repaint();

        }

        if(upPressed) {

            player.setY(player.getY() - player.getSpeed());
            Camera camera = host.activeScreen.getCamera();

            Point cameraCenter = camera.getCenter();
            Point playerCenter = player.getCenter();

            if(playerCenter.getY() < cameraCenter.getY()) {
                camera.moveCameraY(camera.getY() - player.getSpeed());
            }

            host.activeScreen.getCanvas().repaint();

        }

        if(downPressed) {

            player.setY(player.getY() + player.getSpeed());
            Camera camera = host.activeScreen.getCamera();

            Point cameraCenter = camera.getCenter();
            Point playerCenter = player.getCenter();

            if(playerCenter.getY() > cameraCenter.getY()) {
                camera.moveCameraY(camera.getY() + player.getSpeed());
            }

            host.activeScreen.getCanvas().repaint();

        }
    }
}
