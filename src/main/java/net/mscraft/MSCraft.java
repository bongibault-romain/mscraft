package net.mscraft;

import lombok.Getter;
import lombok.Setter;
import net.mscraft.game.Window;
import net.mscraft.graphics.Renderer;
import net.mscraft.graphics.Screen;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class MSCraft implements Runnable {

    @Getter
    private static final MSCraft instance = new MSCraft();

    @Getter
    private Renderer renderer;

    private Screen screen;
    @Getter
    private Window window;
    private BufferedImage img;
    private int[] pixels;
    @Getter
    @Setter
    private boolean running = false;

    private Thread gameThread;

    public MSCraft() {

    }

    public static void main(String[] args) {
        instance.init();
        instance.start();
    }

    private void init(){
        renderer = new Renderer(800, 600);
        window = new Window("MSCraft");
        int width = (int) window.getWidth();
        int height = (int) window.getHeight();

        /**
         * -------------------------
         * Tests de rendu des pixels
         */
        screen = new Screen(width, height);
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();

        // ---------------

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
        BufferStrategy bs = this.getWindow().getBufferStrategy();
        if(bs == null) {
            this.getWindow().createBufferStrategy(3);
            return;
        }

        screen.render();

        for (int i = 0; i < this.getWindow().getWidth()*this.getWindow().getHeight(); i++) {
            pixels[i] = screen.getPixels()[i];
        }

        Graphics graphics = bs.getDrawGraphics();
        graphics.drawImage(img, 0, 0, this.getWindow().getWidth(), this.getWindow().getHeight(), null);
        graphics.dispose();
        bs.show();
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
