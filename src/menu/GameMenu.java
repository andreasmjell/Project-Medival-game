package src.menu;

import javax.swing.*;
import java.awt.*;

public abstract class GameMenu extends JPanel{
        
    public GameMenu(int x, int y, int width, int height){
        this.setLayout(null);
        this.setBounds(x, y, width, height);
        this.setOpaque(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Tegn en mørk, semi-gjennomsiktig boks som bakgrunn for menyen
        g2.setColor(new Color(0, 0, 0, 180)); 
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        
        // En enkel ramme
        g2.setColor(new Color(255, 215, 0, 150)); // Gull-aktig ramme
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
    }
}