package com.michaeli.launcher;

import com.michaeli.snake.App;
import com.michaeli.snake.Snake;
import com.michaeli.snake.util.Utility;

import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Launcher extends JFrame {

    public static Launcher launcher;
    public static App game;
    public static Settings settings;

    public static Snake snake;

    public static final int WIDTH = 680;
    public static final int HEIGHT = 500;

    RectangularButton play_btn;
    RectangularButton settings_btn;

    BufferedImage play_content = Utility.getImage("menu/play-dummy.png");
    BufferedImage settings_content = Utility.getImage("menu/settings-dummy.png");


    //main method (Startet alles -> alles was ausgeführt werden soll muss hier rein)
    public static void main(String[]args){
        launcher = new Launcher();
        launcher.addComponents();
        launcher.setUpLauncher();
        launcher.animate();
    }

    public void setUpLauncher(){
        setTitle("Snake Game Launcher");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void addComponents(){
        //Custom Buttons
        play_btn = new RectangularButton(15, 10, play_content, true);
        play_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("[DEBUG] Play");
                game = new App();
                game.setup();
                game.addComponents();
                game.launch();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("[DEBUG] Hover over Play");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("[DEBUG] Left Play");
            }
        });
        launcher.add(play_btn);

        settings_btn = new RectangularButton(15, 110, settings_content, true);
        settings_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("[DEBUG] Settings");
                settings = new Settings();
                settings.setUp();
                settings.addComponents();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("[DEBUG] Hover over Settings");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("[DEBUG] Left Settings");
            }
        });
        launcher.add(settings_btn);
    }

    public void animate() {
        play_btn.horizontalFlyIn();
        Utility.sleep(500);
        settings_btn.horizontalFlyIn();
    }
}
