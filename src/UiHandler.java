package src;

import src.menu.SettlementMenu;
import javax.swing.*;
import java.awt.*;

public class UiHandler {
    private JFrame frame;
    private JLayeredPane layeredPane;

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private GamePanel gamePanel;
    private Ui ui;
    private HudPanel hud;
    private MapController mapController;

    public static final Integer MAP_LAYER = 0;
    public static final Integer HUD_LAYER = 100;
    public static final Integer MENU_LAYER = 200;

    public UiHandler(Ui ui, MapController mapController){
        this.mapController = mapController;
        this.ui = ui;
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        createWindow();
    }

    public void createWindow(){ //Oppretter vinduet alt skjer i.
        frame = new JFrame("Medieval Game");
        frame.setBackground(Color.BLACK);
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
        frame.setContentPane(layeredPane);

        layeredPane.removeAll();

        panel.setBounds(0, 0, screenSize.width, screenSize.height);
        layeredPane.add(panel, Integer.valueOf(0));
        
        
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        
        frame.revalidate();
        frame.repaint();
    }

    public void showHud(){
        if (hud == null){
        hud = new HudPanel(screenSize.width);
        }
        layeredPane.add(hud, HUD_LAYER);
        layeredPane.revalidate();
        layeredPane.repaint();
    }

    public HudPanel getHud() {
    return hud;
}

    public void openMenu(JPanel menu, Integer layer){
        menu.setBounds(menu.getX(), menu.getY(), menu.getWidth(), menu.getHeight());
        layeredPane.add(menu, layer);

        layeredPane.revalidate();
        layeredPane.repaint();
        menu.requestFocusInWindow();
    }

    public void closeMenu(JPanel menu){
        layeredPane.remove(menu);
        layeredPane.revalidate();
        layeredPane.repaint();

        gamePanel.requestFocusInWindow();
    }

    public JFrame getFrame(){
        return frame;
    }
    public Dimension getScreenSize() {
        return screenSize;
    }

    public void openSettlementMenu(Settlement settlement){
        SettlementMenu settlementMenu = new SettlementMenu(settlement, mapController); 
        openMenu(settlementMenu, MENU_LAYER);
    }

}
