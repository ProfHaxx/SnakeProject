package com.michaeli.snake;

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
        BufferedImage[] snake = new BufferedImage[4];
        try {
            BufferedImage fullSnake = ImageIO.read(new File(Objects.requireNonNull(Utility.class.getClassLoader().getResource(path)).getFile()));
            snake[0] = fullSnake.getSubimage(0, 0, 20, 20);
            snake[1] = fullSnake.getSubimage(20, 0, 20, 20);
            snake[2] = fullSnake.getSubimage(40, 0, 20, 20);
            snake[3] = fullSnake.getSubimage(60, 0, 20, 20);
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
}
