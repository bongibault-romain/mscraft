package net.mscraft.graphics;

import lombok.Getter;
import lombok.Setter;
import net.mscraft.MSCraft;
import net.mscraft.game.Window;

public class Renderer {

    private final Window window = MSCraft.getInstance().getWindow();

    @Getter
    private int[] pixels;

    @Setter
    private int width;

    @Setter
    private int height;

    public Renderer(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];
    }


    public void draw(Renderer renderer, int xOffset, int yOffset) {
        this.width = (int) window.getWidth();
        this.height = (int) window.getHeight();
        this.pixels = new int[width * height];

        for (int y = 0; y < renderer.height; y++) {
            int yPixel = y + yOffset;
            for (int x = 0; x < renderer.width; x++) {
                int xPixel = x + xOffset;

                pixels[xPixel + yPixel * width] = renderer.pixels[x + y * renderer.width];
            }
        }
    }
}
