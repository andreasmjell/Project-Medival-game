package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SmoothMoveCharacter extends JPanel {
    private int charX = 50; // startposisjon
    private int charY = 50;
    private final int CHAR_SIZE = 20;

    private int targetX = charX;
    private int targetY = charY;
    private final int SPEED = 5; // hvor mange piksler karakteren beveger seg per steg

    private Image mapImage = new ImageIcon("map.png").getImage();
    private Image charImage = new ImageIcon("ridderhjelm.png").getImage();

    public SmoothMoveCharacter() {
        // Lytter til museklikk
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                targetX = e.getX() - CHAR_SIZE / 2;
                targetY = e.getY() - CHAR_SIZE / 2;
            }
        });

        // Timer som oppdaterer posisjon hvert 20 ms (~50 FPS)
        Timer timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveCharacter();
                repaint();
            }
        });
        timer.start();

        //this.setPreferredSize(new Dimension(1920, 1080));
        this.setPreferredSize(new Dimension(mapImage.getWidth(null), mapImage.getHeight(null)));
    }

    private void moveCharacter() {
        // Beregn forskjell i x og y
        int dx = targetX - charX;
        int dy = targetY - charY;

        // Beregn avstand
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance > SPEED) {
            // Flytt karakteren litt mot målet
            charX += (int)(dx / distance * SPEED);
            charY += (int)(dy / distance * SPEED);
        } else {
            // Hvis vi er nærme, hopp rett til målet
            charX = targetX;
            charY = targetY;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Tegn bakgrunn
        g.drawImage(mapImage, 0, 0, getWidth(), getHeight(), null);

        // Tegn karakter
        g.drawImage(charImage, charX, charY, CHAR_SIZE, CHAR_SIZE, null);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Smooth Move Character");
        SmoothMoveCharacter panel = new SmoothMoveCharacter();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}