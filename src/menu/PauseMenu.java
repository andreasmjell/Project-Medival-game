package src.menu;
import src.*;

import javax.swing.*;
import java.awt.*;

public class PauseMenu extends GameMenu{

    UiHandler uiHandler;
    

    public PauseMenu(GameContext gameContext){
        gameContext.mapController.timerStop();

        int menuWidth = (int)(gameContext.size.getWidth() * 0.2);
        int menuHeight = (int)(gameContext.size.getHeight() * 0.6);

        int x = ((int)gameContext.size.getWidth() - menuWidth) / 2;
        int y = ((int)gameContext.size.getHeight() - menuHeight) / 2;

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
            gameContext.mapController.timerStart();
            uiHandler.closeMenu(this);
        });

        this.add(closeBtn);
        this.add(bg);
    }
}