package net.mscraft.graphics;

import java.util.Random;

public class Screen extends Renderer {

    private final Renderer testRenderer;

    public Screen(int width, int height) {
        super(width, height);

        /**
         * CE SONT DES TEST DE RENDU DES PIXELS
         * NE PAS PRENDRE EN COMPTE SERIEUSEMENT
         */
        testRenderer = new Renderer(width, height);
        Random random = new Random();
        for (int i = 0; i < 256 * 256; i++) {
            testRenderer.getPixels()[i] = random.nextInt();
        }
    }


    public void render() {
        draw(testRenderer, 0, 0);

    }

}
