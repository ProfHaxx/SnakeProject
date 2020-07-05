package com.michaeli.snake;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Utility {
    public static void sleep(long ms) { 
        try {
            Thread.sleep(ms);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage getImage(String path) {
        try {
            return ImageIO.read(new File(Objects.requireNonNull(Utility.class.getClassLoader().getResource(path)).getFile()));
        } catch (IOException e) {
            System.err.println("[FATAL] Failed to load Resources");
            e.printStackTrace();
        }
        return null;
    }
}
