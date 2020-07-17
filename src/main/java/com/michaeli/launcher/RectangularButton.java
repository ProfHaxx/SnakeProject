package com.michaeli.launcher;

import com.michaeli.snake.util.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RectangularButton extends JComponent {
    ButtonOverlay overlay;
    BufferedImage content;
    int x, y;
    int fX, fY;

    public RectangularButton(int x, int y, BufferedImage content, boolean animated) {
        this.x = x;
        this.y = y;
        this.content = content;
        if(animated) {
            fX = 64000;
        } else {
            fX = x;
        }
        fY = y;

        setBounds(fX, fY, fX+content.getWidth(), fY+content.getHeight());
    }

    public void addButtonOverlay(BufferedImage img) {
        this.overlay = new ButtonOverlay(img, this);
    }

    public void horizontalFlyIn() {
        fX = -content.getWidth();
        new Thread(() -> {
            Utility.sleep(1000);
            while(fX < x) {
                Utility.sleep(1);
                fX++;
                setBounds(fX, fY, fX+content.getWidth(), fY+content.getHeight());
                this.repaint();
            }
        }, "Button Animation Thread").start();
    }

    public void changeImage(BufferedImage content) {
        this.content = content;
        setBounds(fX, fY, fX+content.getWidth(), fY+content.getHeight());
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(content, fX, fY, null);
        if(this.overlay != null) this.overlay.paint((Graphics2D) g);
    }

    @Override
    public String toString() {
        return "RectangularButton{" +
                "x=" + x +
                ", y=" + y +
                ", fX=" + fX +
                ", fY=" + fY +
                '}';
    }
}
