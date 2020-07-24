package com.michaeli.launcher;

import com.michaeli.snake.App;
import com.michaeli.snake.util.Utility;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Settings extends JFrame {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 400;

    BufferedImage[] snake_settings = new BufferedImage[]{
            Utility.getImage("settings/forest-setting.png"),
            Utility.getImage("settings/plains-setting.png"),
            Utility.getImage("settings/beach-setting.png")
    };

    BufferedImage[] settings_button = new BufferedImage[]{
            Utility.getImage("settings/button-dummy-0.png"),
            Utility.getImage("settings/button-dummy-1.png"),
            Utility.getImage("settings/button-dummy-2.png"),
            Utility.getImage("settings/button-dummy-3.png")
    };

    RectangularButton[] menu = new RectangularButton[]{
            new RectangularButton(15, 15, settings_button[0], false),
            new RectangularButton(160, 15, settings_button[1], false),
            new RectangularButton(15, 100, settings_button[2], false),
            new RectangularButton(320, 200, settings_button[3], false)
    };

    RectangularButton[] themes = new RectangularButton[]{
            new RectangularButton(50, 40, Utility.scale(snake_settings[0], 80, 160), false),
            new RectangularButton(110, 10, snake_settings[1], false),
            new RectangularButton(210, 40, Utility.scale(snake_settings[2], 80, 160), false)
    };

    public Settings() {
        addComponents();
        setUp();
    }

    public void setUp(){
        setTitle("Settings");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }

    int index = 1;
    public void addComponents() {
        themes[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Index Decreased");
                index = (index + 4) % 3;
                updateThemeIcons();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });

        themes[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Theme " + index + " Selected");
                for(RectangularButton btn:themes) btn.setVisible(false);
                for(RectangularButton btn:menu) btn.setVisible(true);
                App.theme_index = index;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });

        themes[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Index Increased");
                index = (index + 2) % 3;
                updateThemeIcons();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });

        for(RectangularButton button:themes) {
            add(button);
            button.setVisible(false);
        }

        menu[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("[DEBUG] Hover Button 1");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("[DEBUG] Unhover Button 1");
            }
        });

        menu[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("[DEBUG] Entered Themes Settings");
                for(RectangularButton button:menu) button.setVisible(false);
                for(RectangularButton button:themes) button.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("[DEBUG] Hover Button 2");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("[DEBUG] Unhover Button 2");
            }
        });

        menu[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("[DEBUG] Hover Button 3");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("[DEBUG] Unhover Button 3");
            }
        });

        menu[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("[DEBUG] Hover Button 4");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("[DEBUG] Unhover Button 4");
            }
        });

        for(RectangularButton button:menu) {
            add(button);
            button.setVisible(true);
        }
    }

    void updateThemeIcons() {
        System.out.println("Theme Index: " + index);
        themes[0].changeImage(Utility.scale(snake_settings[(index+2)%3], 80, 160));
        themes[1].changeImage(snake_settings[index]);
        themes[2].changeImage(Utility.scale(snake_settings[(index+4)%3], 80, 160));
        repaint();
    }

}
