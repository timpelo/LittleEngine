package tiko;


import tiko.engine.gameobject.GameObject;
import tiko.engine.gui.Camera;
import tiko.engine.gui.Screen;
import tiko.engine.gui.ScreenManager;
import tiko.engine.gui.assetmap.TileMap;
import tiko.engine.system.InputAdapter;
import tiko.engine.system.physics.Collider;
import tiko.engine.system.physics.PhysicsBody;
import tiko.engine.system.physics.World;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Jani on 25.11.2015.
 */
public class GameScreen extends Screen {

    DemoGame host;
    GameObject player;
    GameObject ground;
    GameObject bomb;
    World world;

    boolean rightPressed = false;
    boolean leftPressed = false;
    boolean upPressed = false;

    /**
     * Constructor for this class.
     *
     * @param screenManager screen manager where this screen is located.
     */
    public GameScreen(ScreenManager screenManager, DemoGame host) {
        super(screenManager);
        world = new World(this);
        this.host = host;


        player = new GameObject(100,650, "assets/hat.png");
        bomb = new GameObject(600, 600, "assets/bomb.png");
        ground = new GameObject(0, 900, "assets/ground.jpg");


        PhysicsBody playerBody = new PhysicsBody(
                new Collider(new Rectangle(100, 650, 100, 100)),
                1.5f,
                0.75f,
                0.5f,
                false
        );

        PhysicsBody bombBody = new PhysicsBody(
                new Collider(new Rectangle(600, 600, 100, 100)),
                0,
                0,
                0,
                true
        );

        PhysicsBody groundBody = new PhysicsBody(
                new Collider(new Rectangle(0, 900, 2000, 100)),
                0,
                0,
                0,
                true
        );

        playerBody.setMaxHorizontalForce(2f);
        player.setPhysicsBody(playerBody);
        bomb.setPhysicsBody(bombBody);
        ground.setPhysicsBody(groundBody);

        //bg = new GameObject(0, 0, "assets/bg.jpg");

        //addObject(bg);
        TileMap board;
        board = new TileMap(500, 500);
        board.loadTiles("assets/");
        board.drawMap(this);
        addObject(player);
        addObject(bomb);
        addObject(ground);
        world.addObject(player);
        world.addObject(bomb);
        world.addObject(ground);

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

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    upPressed = false;
                }
            }
        });
    }

    @Override
    public void run() {
        world.physicsStep();
        updateCamera();

        if(player.getPhysicsBody().get().checkCollision(bomb.getPhysicsBody()
                .get().getCollider())) {
            System.out.println("BUM!!!");
        }


        if(rightPressed) {
            PhysicsBody body = player.getPhysicsBody().get();
            body.setHorizontalForce(body.getForceH() + 0.4f);
            host.activeScreen.getCanvas().repaint();
        }

        if(leftPressed) {
            PhysicsBody body = player.getPhysicsBody().get();
            body.setHorizontalForce(body.getForceH() - 0.4f);
            host.activeScreen.getCanvas().repaint();

        }

        if(upPressed && !(player.getPhysicsBody().get().isInAir())) {
            player.getPhysicsBody().get().setVerticalForce(-5f);
            host.activeScreen.getCanvas().repaint();
        }
    }

    public void updateCamera() {
        Camera camera = host.activeScreen.getCamera();

        Point cameraCenter = camera.getCenter();
        Point playerCenter = player.getCenter();

        if(playerCenter.getX() > cameraCenter.getX()) {
            camera.moveCameraX((int)playerCenter.getX() -
                    camera.getCameraWidth() / 2);
        }

        if(playerCenter.getX() < cameraCenter.getX()) {
            camera.moveCameraX((int)playerCenter.getX() -
                    camera.getCameraWidth() / 2);
        }

        if(playerCenter.getY() < cameraCenter.getY()) {
            camera.moveCameraY((int) playerCenter.getY() -
                    camera.getCameraHeight() / 2);
        }

        if(playerCenter.getY() < cameraCenter.getY()) {
            camera.moveCameraY((int) playerCenter.getY() -
                    camera.getCameraHeight() / 2);
        }

    }
}
