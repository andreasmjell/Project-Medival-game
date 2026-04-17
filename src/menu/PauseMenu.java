package src.menu;
import src.*;

import javax.swing.*;
import java.awt.*;

public class PauseMenu extends GameMenu{

    UiHandler uiHandler;
    

    public PauseMenu(MapController mapController){
        mapController.timerStop();
        uiHandler = mapController.getUiHandler();
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int menuWidth = (int) (size.getWidth());
        int menuHeight = (int) (size.getHeight());

        int x = ((int)size.getWidth() - menuWidth) / 2;
        int y = ((int)size.getHeight() - menuHeight) / 2;

        super(x,y,menuWidth,menuHeight);
        JLabel bg = new JLabel(new ImageIcon(getClass().getResource("/src/assets/Mainmenu.png")));
        int backgroundWidth = (int) (size.getWidth() * 0.8);
        int backgroundHeight = (int) (size.getHeight() * 0.8);
        bg.setBounds(0, 0, backgroundWidth, backgroundHeight);

        


        // Eksempel på lukk-knapp nederst i midten av menyen
        MenuButton closeBtn = new MenuButton("Forlat byen");
        closeBtn.setBounds((menuWidth - 250) / 2, menuHeight - 80, 250, 60);
        closeBtn.addActionListener(e -> {
            mapController.timerStart();
            uiHandler.closeMenu(this);
        });
        this.add(closeBtn);
        this.add(bg);
    }
}