package src.menu;
import src.*;

import javax.swing.*;
import java.awt.*;

public class SettlementMenu extends GameMenu{

    UiHandler uiHandler;
    

    public SettlementMenu(Settlement settlement, MapController mapController){
        mapController.timerStop();
        uiHandler = mapController.getUiHandler();
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int menuWidth = (int) (size.getWidth() * 0.8);
        int menuHeight = (int) (size.getHeight() * 0.8);

        int x = ((int)size.getWidth() - menuWidth) / 2;
        int y = ((int)size.getHeight() - menuHeight) / 2;

        super(x,y,menuWidth,menuHeight);

        JLabel nameLabel = new JLabel("Velkommen til " + settlement.getName());
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Serif", Font.BOLD, 24));
        nameLabel.setBounds(50, 30, 500, 40);
        add(nameLabel);

        // Eksempel på lukk-knapp nederst i midten av menyen
        MenuButton closeBtn = new MenuButton("Forlat byen");
        closeBtn.setBounds((menuWidth - 250) / 2, menuHeight - 80, 250, 60);
        closeBtn.addActionListener(e -> {
            mapController.timerStart();
            uiHandler.closeMenu(this);
        });
        add(closeBtn);
    }
}