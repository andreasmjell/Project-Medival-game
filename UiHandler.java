import javax.swing.*;
import java.awt.*;

public class UiHandler {
    private JFrame frame;
    private JLayeredPane layeredPane;

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private GamePanel gamePanel;
    private Ui ui;

    public UiHandler(Ui ui){
        this.ui = ui;
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        createWindow();
    }

    public void createWindow(){ //Oppretter vinduet alt skjer i.
        frame = new JFrame("Medieval Game");
        //frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenSize);
        frame.setLayout(null);

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, screenSize.width, screenSize.height);

        frame.setContentPane(layeredPane);
        frame.setVisible(true);
    }

    public void start(){
        ui.mainMenu();
    }

    public void setGamePanel(GamePanel panel){
    this.gamePanel = panel;
    panel.setBounds(0, 0, screenSize.width, screenSize.height);
    
    // Fjern menyen og sett GamePanel som det aktive innholdet
    frame.getContentPane().removeAll();
    frame.setContentPane(panel); 
    
    panel.setFocusable(true);
    panel.requestFocusInWindow();
    
    frame.revalidate();
    frame.repaint();
}

    public JFrame getFrame(){
        return frame;
    }

    public Dimension getScreenSize() {
        return screenSize;
    }

}
