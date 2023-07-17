package net.mscraft;

import lombok.Getter;
import lombok.Setter;
import net.mscraft.game.Window;
import net.mscraft.graphics.Renderer;
import net.mscraft.graphics.Screen;

import java.awt.image.BufferedImage;

public class MSCraft implements Runnable {

    @Getter
    private static final MSCraft instance = new MSCraft();

    @Getter
    private final Renderer renderer;

    private final Screen screen;
    @Getter
    private final Window window;
    private BufferedImage img;
    @Getter
    @Setter
    private boolean running = false;

    private Thread gameThread;

    public MSCraft() {
        this.window = new Window("MSCraft");

        /**
         * -------------------------
         * Tests de rendu des pixels
         */
        this.screen = new Screen((int) this.window.getWidth(), (int) this.window.getHeight());
        // ---------------

        this.renderer = new Renderer((int) this.window.getWidth(), (int) this.window.getHeight());
    }

    public static void main(String[] args) {
        instance.start();
    }

    /**
     * Start the game
     */
    public void start() {
        System.out.println("Starting game...");

        if (this.isRunning()) {
            System.out.println("Game is already running!");
            return;
        }

        this.running = true;
        this.gameThread = new Thread(this);
        this.gameThread.start();

        System.out.println("Game started!");
    }

    @Override
    public void run() {
        while (this.isRunning()) {
            this.tick();
            this.render();
        }
    }

    private void tick() {

    }

    private void render() {

    }

    public void stop() {
        System.out.println("Stopping game...");
        if (!this.isRunning()) {
            System.out.println("Game is already stopped!");
            return;
        }

        this.running = false;

        try {
            this.gameThread.join();
            System.out.println("Game stopped!");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
