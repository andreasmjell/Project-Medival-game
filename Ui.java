import javax.swing.*;
import java.awt.*;

public class Ui {
    JFrame frame = null;
    
    public void start(){
        createWindow();
        mainMenu();
    }

    public void createWindow(){
        frame = new JFrame("Medieval");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLayout(null);
        frame.setVisible(false);
    }

    public void mainMenu(){
        ImageIcon backgroundImage = new ImageIcon("assets/Mainmenu.png"); // banen til bildet
        JLabel background = new JLabel(backgroundImage);
        background.setBounds(0, 0, 1920, 1080);
        frame.setContentPane(background);
        JButton startGame = new JButton("Start Game");
        startGame.setBounds(600, 200, 320, 80);
        background.add(startGame);
        frame.setVisible(true);
        
    }
}
