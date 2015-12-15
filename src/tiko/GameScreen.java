package tiko;


import tiko.engine.gameobject.GameObject;
import tiko.engine.gui.Camera;
import tiko.engine.gui.Screen;
import tiko.engine.gui.ScreenManager;
import tiko.engine.gui.assetmap.TileMap;
import tiko.engine.system.InputAdapter;
import tiko.engine.system.Time;
import tiko.engine.system.animation.Animation;
import tiko.engine.system.physics.Collider;
import tiko.engine.system.physics.PhysicsBody;
import tiko.engine.system.physics.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Jani on 25.11.2015.
 */
public class GameScreen extends Screen {

    DemoGame host;
    GameObject player;
    GameObject ground;
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
        ground = new GameObject(0, 900, "assets/ground.jpg");


        PhysicsBody playerBody = new PhysicsBody(
                new Collider(new Rectangle(100, 650, 100, 100)),
                1.5f,
                0.5f,
                0.5f,
                false
        );

        PhysicsBody groundBody = new PhysicsBody(
                new Collider(new Rectangle(0, 900, 2000, 100)),
                0,
                0,
                0,
                true
        );

        Animation playerAnimation = new Animation(player, 150f);
        playerAnimation.splitSheet(
                "assets/hatsheet.png",
                220 / 2,
                620 / 4,
                2,
                4);

        playerBody.setMaxHorizontalForce(4f);
        groundBody.setLayer("Ground");

        player.setPhysicsBody(playerBody);
        player.setAnimation(playerAnimation);
        ground.setPhysicsBody(groundBody);

        TileMap board;
        board = new TileMap(500, 500);
        board.loadTiles("assets/");
        board.drawMap(this);
        addObject(player);
        addObject(ground);
        world.addObject(player);
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

        getCanvas().addMouseListener(new InputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if (e.getButton() == MouseEvent.BUTTON1) {
                    createBall();
                }
            }
        });
    }

    @Override
    public void run() {
        Time.update();
        player.getAnimation().get().update();
        world.physicsStep();
        updateCamera();

        if(rightPressed) {
            PhysicsBody body = player.getPhysicsBody().get();
            body.setHorizontalForce(body.getForceH() + 2f);
            System.out.println("new h-force:" + body.getForceH());
            host.activeScreen.getCanvas().repaint();
        }

        if(leftPressed) {
            PhysicsBody body = player.getPhysicsBody().get();
            body.setHorizontalForce(body.getForceH() - 2f);
            host.activeScreen.getCanvas().repaint();

        }

        if(upPressed && !(player.getPhysicsBody().get().isInAir())) {
            player.getPhysicsBody().get().setVerticalForce(-5f);
            player.getPhysicsBody().get().setInAir(true);
            host.activeScreen.getCanvas().repaint();
        }
    }

    public void updateCamera() {
        Camera camera = host.activeScreen.getCamera();

        Point cameraCenter = camera.getCenter();
        Point playerCenter = player.getCenter();

        if(playerCenter.getX() > cameraCenter.getX()) {
            camera.moveCameraX((int) playerCenter.getX() -
                    camera.getCameraWidth() / 2);
        }

        if(playerCenter.getX() < cameraCenter.getX()) {
            camera.moveCameraX((int) playerCenter.getX() -
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

    public void createBall() {
        GameObject ball = new GameObject(
                player.getX() + player.getTexture().getWidth() + 5,
                player.getY() - 1,
                "assets/ball.png"
        );

        Collider collider = new Collider(
                new Rectangle(ball.getX(),
                        ball.getY(),
                        ball.getTexture().getWidth(),
                        ball.getTexture().getHeight())
        );

        PhysicsBody ballBody = new PhysicsBody(
                collider,
                1f,
                0.2f,
                0.5f,
                false
        );

        ballBody.setHorizontalForce(7f);
        ballBody.setVerticalForce(-5f);

        ball.setPhysicsBody(ballBody);
        addObject(ball);
        world.addObject(ball);

    }
}
