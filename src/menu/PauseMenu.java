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

        int menuWidth = (int)(size.getWidth() * 0.2);
        int menuHeight = (int)(size.getHeight() * 0.6);

        int x = ((int)size.getWidth() - menuWidth) / 2;
        int y = ((int)size.getHeight() - menuHeight) / 2;

        super(x, y, menuWidth, menuHeight);

        JLabel bg = new JLabel(new ImageIcon(getClass().getResource("/src/assets/Mainmenu.png")));
        bg.setBounds(0, 0, menuWidth, menuHeight);

        MenuButton closeBtn = new MenuButton("Close Menu");

        int btnWidth = 250;
        int btnHeight = 60;
        int btnX = (menuWidth - btnWidth) / 2;
        int btnY = (int)(menuHeight * 0.75);
        closeBtn.setFont(new Font("Serif", Font.BOLD, 24));

        closeBtn.setBounds(btnX, btnY, btnWidth, btnHeight);

        closeBtn.addActionListener(e -> {
            mapController.timerStart();
            uiHandler.closeMenu(this);
        });

        this.add(closeBtn);
        this.add(bg);
    }
}