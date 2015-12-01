package tiko;

import tiko.engine.gui.ScreenManager;

public class DemoLauncher {

    public static void main(String[] args) {
        final int WINDOW_HEIGHT = 720;
        final int WINDOW_WIDTH = 1280;

        DemoGame game = new DemoGame();
        ScreenManager scrMgr = new ScreenManager(2000, 1000,
                WINDOW_WIDTH, WINDOW_HEIGHT, game);
    }
}
