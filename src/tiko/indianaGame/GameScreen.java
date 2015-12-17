package tiko.indianaGame;


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
import tiko.engine.system.physics.PhysicsLayer;
import tiko.engine.system.physics.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

/**
 * Created by Jani on 25.11.2015.
 */
public class GameScreen extends Screen {

    DemoGame host;
    GameObject player;
    GameObject ground;
    Boss boss;
    World world;
    PhysicsLayer ballLayer;
    private float timer = 0.00f;
    private float last = System.nanoTime();

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
        ballLayer = new PhysicsLayer("ball");
        ballLayer.ignoreLayer(ballLayer);


        player = new GameObject(100,670, "assets/hat.png");
        ground = new GameObject(0, 900, "assets/ground.jpg");
        boss = new Boss(1000, 650, "assets/boss.png", this);

        PhysicsBody bossBody = new PhysicsBody(
                new Collider(new Rectangle(1000, 670, 130, 240)),
                0f,
                0f,
                0f,
                true
        );

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
        groundBody.setLayer(new PhysicsLayer("ground"));

        player.setPhysicsBody(playerBody);
        player.setAnimation(playerAnimation);
        ground.setPhysicsBody(groundBody);
        boss.setPhysicsBody(bossBody);

        TileMap board;
        board = new TileMap(500, 500);
        board.loadTiles("assets/");
        board.drawMap(this);
        addObject(player);
        addObject(ground);
        addObject(boss);
        world.addObject(player);
        world.addObject(ground);
        world.addObject(boss);
        boss.createHealthBar();

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
                    host.soundManager.playSound(2);
                }
            }
        });
    }

    @Override
    public void run() {
        Time.update();
        timer = timer + (System.nanoTime() - last);
        last = System.nanoTime();


        System.out.println("TIMER: " + (int)(timer / 1000000000));
        player.getAnimation().get().update();
        world.physicsStep();
        updateCamera();

        if(boss != null) {
            checkHits();
        }

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
                1.5f,
                0.2f,
                0.65f,
                false
        );

        ballBody.setLayer(ballLayer);
        ballBody.setHorizontalForce(7f);
        ballBody.setVerticalForce(-5f);

        ball.setPhysicsBody(ballBody);
        addObject(ball);
        world.addObject(ball);

    }

    public void checkHits() {

        LinkedList<GameObject> list = world.getObjectList();

        for(int i = 0; i < list.size(); i++) {

            GameObject o = list.get(i);

            if(o.getPhysicsBody().isPresent()) {

                PhysicsBody body = o.getPhysicsBody().get();
                PhysicsBody bossB = boss.getPhysicsBody().get();

                if(body.getLayer().getName().equals("ball")) {

                    if(body.checkCollision(bossB.getCollider())) {

                        list.remove(o);
                        removeObject(o);
                        o.destroyTexture();

                        System.out.println("destroyed: " + o);
                        boss.setHealth(boss.getHealth() - 1);
                    }
                }
            }
        }
    }
}
