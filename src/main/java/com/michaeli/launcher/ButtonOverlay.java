package com.michaeli.launcher;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ButtonOverlay {

    BufferedImage overlayImage;
    RectangularButton btn;
    int x = 0, y = 0;


    public ButtonOverlay(BufferedImage overlay, RectangularButton btn) {
        this.overlayImage = overlay;
        setButton(btn);
    }

    public void setButton(RectangularButton button) {
        this.btn = button;
        this.x = button.getX()-button.getWidth()/2+overlayImage.getWidth()/2;
        this.y = button.getY()-button.getHeight()/2+overlayImage.getHeight()/2;
    }

    public void paint(Graphics2D g) {
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f));
        g.drawImage(overlayImage, x, y, null);
    }
}
