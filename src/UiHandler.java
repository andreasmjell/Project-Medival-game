package src;

import src.menu.SettlementMenu;
import src.menu.PauseMenu;
import javax.swing.*;
import java.awt.*;

public class UiHandler {
    private JFrame frame;
    private JLayeredPane layeredPane;

    private JPanel activeMainPanel;

    private GameContext gameContext;

    private GamePanel gamePanel;
    private HudPanel hud;

    public static final Integer MAP_LAYER = 0;
    public static final Integer HUD_LAYER = 100;
    public static final Integer MENU_LAYER = 200;

    public UiHandler(GameContext gameContext){
        createWindow();
    }

    public void createWindow(){ //Oppretter vinduet alt skjer i.
        frame = new JFrame("Medieval Game");
        frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(gameContext.size);
        frame.setLayout(null);

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, gameContext.size.width, gameContext.size.height);

        frame.setContentPane(layeredPane);
        frame.setVisible(true);
    }

    public void start(){
        gameContext.ui.mainMenu();
    }

    public void setMainPanel(JPanel panel){
        if (activeMainPanel != null) {
        layeredPane.remove(activeMainPanel);
        }
        activeMainPanel = panel;

        panel.setBounds(0, 0, gameContext.size.width, gameContext.size.height);
        layeredPane.add(panel, MAP_LAYER);

        layeredPane.revalidate();
        layeredPane.repaint();

        panel.setFocusable(true);
        panel.requestFocusInWindow();
    }

    public void setGamePanel(GamePanel panel){
        gamePanel = panel;
        frame.setContentPane(layeredPane);

        layeredPane.removeAll();
        
        setMainPanel(panel);
    }

    /*public void openBattlePanel(BattlePanel battlePanel){
        setMainPanel(battlePanel);
    }

    public void closeBattlePanel(){
        setMainPanel(gamePanel);
    }*/

    public void showHud(){
        if (hud == null){
        hud = new HudPanel(gameContext.size.width);
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

    public void openSettlementMenu(Settlement settlement){
        SettlementMenu settlementMenu = new SettlementMenu(settlement, gameContext); 
        openMenu(settlementMenu, MENU_LAYER);
    }
    public void openPauseMenu(){
        PauseMenu pauseMenu = new PauseMenu(gameContext);
        openMenu(pauseMenu, MENU_LAYER);
    }

}
