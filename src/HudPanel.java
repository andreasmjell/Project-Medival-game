package src;

import javax.swing.*;
import java.awt.*;

public class HudPanel extends JPanel {
    private JLabel healthLabel;
    private JLabel goldLabel;
    private JLabel troopLabel;
    private JProgressBar healthBar;
    private int playerTroops;

    public HudPanel(int width) {
        // Oppsett av panelet: mørk, gjennomsiktig bakgrunn
        this.setBounds(0, 0, width, 60);
        this.setBackground(new Color(0, 0, 0, 150)); // 150 er alpha (gjennomsiktighet)
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));

        // Font
        Font hudFont = new Font("Serif", Font.BOLD, 18);

        // Helse-seksjon
        healthLabel = new JLabel("HP: ");
        healthLabel.setForeground(Color.WHITE);
        healthLabel.setFont(hudFont);
        
        healthBar = new JProgressBar(0, 100);
        healthBar.setValue(80);
        healthBar.setForeground(new Color(200, 0, 0));
        healthBar.setBackground(Color.DARK_GRAY);
        healthBar.setPreferredSize(new Dimension(150, 20));
        healthBar.setBorderPainted(false);

        // Gull-seksjon
        goldLabel = new JLabel("Gold: 500");
        goldLabel.setForeground(Color.YELLOW);
        goldLabel.setFont(hudFont);

        // Stamina/Energi
        troopLabel = new JLabel("Troops: " + playerTroops);
        troopLabel.setForeground(new Color(50, 200, 50));
        troopLabel.setFont(hudFont);

        // Legg til komponenter
        this.add(healthLabel);
        this.add(healthBar);
        this.add(goldLabel);
        this.add(troopLabel);
    }

    // Metoder for å oppdatere stats dynamisk
    public void updateHealth(int hp) {
        healthBar.setValue(hp);
    }

    public void updatePlayerTroops(int troops){
        playerTroops = troops;
        troopLabel.setText("Troops: " + playerTroops);

    }

    public void updateGold(int gold) {
        goldLabel.setText("Gold: " + gold);
    }
}