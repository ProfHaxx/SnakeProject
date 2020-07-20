package com.michaeli.snake.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
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

    public static BufferedImage[] getSnakeImage(String path) {
        BufferedImage[] snake = new BufferedImage[6];
        try {
            BufferedImage fullSnake = ImageIO.read(new File(Objects.requireNonNull(Utility.class.getClassLoader().getResource(path)).getFile()));
            for(int i = 0; i < 6; i++) {
                snake[i] = fullSnake.getSubimage(i*40, 0, 40, 40);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return snake;
    }

    public static BufferedImage rotate(BufferedImage img, double angle) {
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) >> 1, (newHeight - h) >> 1);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, null, 0, 0);
        g2d.dispose();

        return rotated;
    }

    /**
     * Image Scaling Method
     *
     * @param sbi image to scale
     * @param dWidth width of destination image
     * @param dHeight height of destination image
     * @return scaled image
     */
    public static BufferedImage scale(BufferedImage sbi, int dWidth, int dHeight) {
        BufferedImage scaledImage = null;
        if (sbi != null) {
            scaledImage = new BufferedImage(dWidth, dHeight, sbi.getType());
            Graphics2D graphics2D = scaledImage.createGraphics();
            graphics2D.drawImage(sbi, 0, 0, dWidth, dHeight, null);
            graphics2D.dispose();
        }
        return scaledImage;
    }
}
