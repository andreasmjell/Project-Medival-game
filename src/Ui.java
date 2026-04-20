package src;
import src.menu.MenuButton;
import src.menu.MenuSlider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Ui {
    MapController mapController;
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private UiHandler uiHandler;

    public Ui(MapController mapController){
        this.mapController = mapController;
        this.uiHandler = mapController.getUiHandler();

    }
    
    public void setUiHandler(UiHandler uiHandler){
        this.uiHandler = uiHandler;
    }
    public void start(){
        //createWindow();
        mainMenu();
    }




    public void mainMenu() {
    System.out.println("MAINMENU!!!!");
    Dimension size = uiHandler.getScreenSize();

    // Bruk en vanlig JPanel, men pass på rekkefølgen
    JPanel root = new JPanel(null);
    root.setBounds(0, 0, size.width, size.height);

    // 1. Lag knappene først
    MenuButton startGame = new MenuButton("Start Game");
    startGame.setBounds(600, 200, 320, 80);

    startGame.addActionListener(e -> {
        System.out.println("START GAME trykket");
        mapController.startGame();
    });

    MenuButton exit = new MenuButton("Save and Exit");
    exit.setBounds(600, 400, 320, 80); // Endret Y fra 600 til 400 for synlighet
    exit.addActionListener(e -> System.exit(0));

    // AUDIO KNAPPER
    AudioManager audioManager = mapController.getAudioManager();
    
    
    MenuSlider audio = new MenuSlider("Audio", 0, 100, 50);
    audio.addChangeListener(e -> {
        audioManager.setVolume(audio.getValue());
    });
    
    audio.setBounds(140, 200, 240, 40);
    

    // 2. Lag bakgrunnen
    JLabel bg = new JLabel(new ImageIcon(getClass().getResource("assets/Mainmenu.png")));
    bg.setBounds(0, 0, size.width, size.height);

    // 3. VIKTIG: Legg til knapper FØRST, bakgrunn SIST (i null-layout)
    // Eller bruk setComponentZOrder
    root.add(startGame);
    root.add(exit); 
    root.add(audio); 
    root.add(bg); 
    

    // Fortell UiHandler at dette er den aktive menyen
    uiHandler.getFrame().setContentPane(root);
    uiHandler.getFrame().revalidate();
    uiHandler.getFrame().repaint();
}}