import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Ui {
    MapController mapController;
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private UiHandler uiHandler;

    public Ui(MapController mapController){
        this.mapController = mapController;
        this.uiHandler = mapController.getUiHandler();

    }
    
    public void setUiHandler(UiHandler uiHandler){
        this.uiHandler = uiHandler;
    }
    public void start(){
        //createWindow();
        mainMenu();
    }

    /*.  FLYTTET TIL UIHANDLER
    public void createWindow(){ //Oppretter vinduet alt skjer i.
        frame = new JFrame("Medieval");
        frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((int)size.getWidth(), (int)size.getHeight());
        frame.setLayout(null);
        frame.setVisible(false);
    }
        */

    // SKAL FLYTTES 
    /*
    public GamePanel drawMap(Player player, Camera camera, MapController mc){ //Fjerner alt fra vinduet og fyller med spillet.
        GamePanel gamePanel = new GamePanel(player, camera, mc);
        frame.getContentPane().removeAll();
        frame.setContentPane(gamePanel);
        frame.revalidate();
        frame.repaint();
        gamePanel.requestFocusInWindow();
        return gamePanel;
    }
        */

    public void settlementMenu(Settlement settlement){

    }

    public void mainMenu() {
    System.out.println("MAINMENU!!!!");
    Dimension size = uiHandler.getScreenSize();

    // Bruk en vanlig JPanel, men pass på rekkefølgen
    JPanel root = new JPanel(null);
    root.setBounds(0, 0, size.width, size.height);

    // 1. Lag knappene først
    MenuButton startGame = new MenuButton("Start Game");
    startGame.setBounds(600, 200, 320, 80);

    startGame.addActionListener(e -> {
        System.out.println("START GAME trykket");
        mapController.startGame();
    });

    MenuButton exit = new MenuButton("Save and Exit");
    exit.setBounds(600, 400, 320, 80); // Endret Y fra 600 til 400 for synlighet
    exit.addActionListener(e -> System.exit(0));

    // 2. Lag bakgrunnen
    JLabel bg = new JLabel(new ImageIcon("assets/Mainmenu.png"));
    bg.setBounds(0, 0, size.width, size.height);

    // 3. VIKTIG: Legg til knapper FØRST, bakgrunn SIST (i null-layout)
    // Eller bruk setComponentZOrder
    root.add(startGame);
    root.add(exit);
    root.add(bg); 

    // Fortell UiHandler at dette er den aktive menyen
    uiHandler.getFrame().setContentPane(root);
    uiHandler.getFrame().revalidate();
    uiHandler.getFrame().repaint();
}





// Ja AI har skrevet dette:
class MenuButton extends JButton {

    private boolean hovered = false;
    private boolean pressed = false;

    private Image woodTexture;

    public MenuButton(String text) {
        super(text);

        // Last tretekstur (legg bilde i assets)
        woodTexture = new ImageIcon("assets/skilt.jpeg").getImage();

try {
    Font medievalFont = Font.createFont(Font.TRUETYPE_FONT,
            new java.io.File("assets/fonts/Cinzel-Bold.ttf"))
            .deriveFont(42f);

    setFont(medievalFont);

} catch (Exception e) {
    setFont(new Font("Serif", Font.BOLD, 42));
}
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                hovered = true;
                repaint();
            }

            public void mouseExited(MouseEvent e) {
                hovered = false;
                repaint();
            }

            public void mousePressed(MouseEvent e) {
                pressed = true;
                repaint();
            }

            public void mouseReleased(MouseEvent e) {
                pressed = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        int offset = pressed ? 3 : 0;
        g2.translate(0, offset);


        g2.drawImage(woodTexture, 0, 0, w, h, null);


        GradientPaint shadow = new GradientPaint(
                0, h / 2, new Color(0, 0, 0, 0),
                0, h, new Color(0, 0, 0, 120)
        );
        g2.setPaint(shadow);
        g2.fillRect(0, 0, w, h);


        GradientPaint highlight = new GradientPaint(
                0, 0, new Color(255, 255, 255, 80),
                0, h / 2, new Color(255, 255, 255, 0)
        );
        g2.setPaint(highlight);
        g2.fillRect(0, 0, w, h);


        g2.setStroke(new BasicStroke(4));
        g2.setColor(new Color(70, 70, 70));
        g2.drawRoundRect(0, 0, w - 1, h - 1, 20, 20);



        drawBolt(g2, 12, 12);
        drawBolt(g2, w - 20, 12);
        drawBolt(g2, 12, h - 20);
        drawBolt(g2, w - 20, h - 20);



        if (hovered) {
            g2.setColor(new Color(255, 255, 255, 40));
            g2.fillRoundRect(0, 0, w, h, 20, 20);
        }


        g2.setFont(getFont());
        FontMetrics fm = g2.getFontMetrics();

        String text = getText();
        int textWidth = fm.stringWidth(text);
        int x = (w - textWidth) / 2;
        int y = (h + fm.getAscent()) / 2 - 6;
        y += (int)(Math.random() * 2);


        g2.setColor(new Color(0, 0, 0, 180));
        g2.drawString(text, x + 3, y + 3);


        g2.setColor(new Color(255, 240, 180, 120));
        g2.drawString(text, x - 1, y - 1);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));


        g2.setColor(new Color(220, 200, 140));
        g2.drawString(text, x, y);

        g2.dispose();
    }

    private void drawBolt(Graphics2D g2, int x, int y) {
    int size = 12;

    // Gradient for metall
    GradientPaint metal = new GradientPaint(
            x, y, new Color(180, 180, 180),
            x + size, y + size, new Color(60, 60, 60)
    );
    g2.setPaint(metal);
    g2.fillOval(x, y, size, size);

    // mørk kant
    g2.setColor(new Color(40, 40, 40));
    g2.drawOval(x, y, size, size);

    // highlight (lysrefleks)
    g2.setColor(new Color(255, 255, 255, 120));
    g2.fillOval(x + 2, y + 2, size / 3, size / 3);

    // liten skygge nederst
    g2.setColor(new Color(0, 0, 0, 80));
    g2.drawArc(x + 2, y + 2, size - 4, size - 4, 200, 140);
}
}}
