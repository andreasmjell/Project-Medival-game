package src.menu;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuSlider extends JSlider {

    private Image woodTexture;
    private Font medievalFont;
    private boolean hovered = false;
    private String label;

    public MenuSlider(String label, int min, int max, int value) {
        super(min, max, value);
        this.label = label;

        java.net.URL imgURL = getClass().getResource("assets/skilt.png");
        if (imgURL != null) {
            woodTexture = new ImageIcon(imgURL).getImage();
        } else {
            System.err.println("Klarte ikke finne: assets/skilt.png");
        }

        try {
            java.io.InputStream is = getClass().getResourceAsStream("assets/fonts/Cinzel-Bold.ttf");
            if (is != null) {
                medievalFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(18f);
            } else {
                throw new Exception("Font-fil ikke funnet");
            }
        } catch (Exception e) {
            System.err.println("Kunne ikke laste font, bruker Serif.");
            medievalFont = new Font("Serif", Font.BOLD, 18);
        }

        setFont(medievalFont);
        setOpaque(false);
        setUI(new MenuSliderUI(this));

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { hovered = true;  repaint(); }
            public void mouseExited(MouseEvent e)  { hovered = false; repaint(); }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        // Wood texture background
        if (woodTexture != null) {
            g2.drawImage(woodTexture, 0, 0, w, h, null);
        } else {
            g2.setColor(new Color(101, 67, 33));
            g2.fillRoundRect(0, 0, w, h, 20, 20);
        }

        // Bottom shadow overlay
        GradientPaint shadow = new GradientPaint(
                0, h / 2f, new Color(0, 0, 0, 0),
                0, h,      new Color(0, 0, 0, 120));
        g2.setPaint(shadow);
        g2.fillRect(0, 0, w, h);

        // Top highlight overlay
        GradientPaint highlight = new GradientPaint(
                0, 0,      new Color(255, 255, 255, 80),
                0, h / 2f, new Color(255, 255, 255, 0));
        g2.setPaint(highlight);
        g2.fillRect(0, 0, w, h);

        // Hover tint
        if (hovered) {
            g2.setColor(new Color(255, 255, 255, 30));
            g2.fillRoundRect(0, 0, w, h, 20, 20);
        }

        // Border
        g2.setStroke(new BasicStroke(4));
        g2.setColor(new Color(70, 70, 70));
        g2.drawRoundRect(0, 0, w - 1, h - 1, 20, 20);

        // Corner bolts
        drawBolt(g2, 12, 12);
        drawBolt(g2, w - 24, 12);
        drawBolt(g2, 12, h - 24);
        drawBolt(g2, w - 24, h - 24);

        // Label text (same style as MenuButton)
        g2.setFont(medievalFont);
        FontMetrics fm = g2.getFontMetrics();
        String display = label + ": " + getValue() + "%";
        int tx = (w - fm.stringWidth(display)) / 2;
        int ty = fm.getAscent() + 6;

        g2.setColor(new Color(0, 0, 0, 180));
        g2.drawString(display, tx + 2, ty + 2);

        g2.setColor(new Color(255, 240, 180, 120));
        g2.drawString(display, tx - 1, ty - 1);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
        g2.setColor(new Color(220, 200, 140));
        g2.drawString(display, tx, ty);

        // Let the UI paint the track and thumb on top
        super.paintComponent(g2);
        g2.dispose();
    }

    private void drawBolt(Graphics2D g2, int x, int y) {
        int size = 12;
        GradientPaint metal = new GradientPaint(
                x, y,           new Color(180, 180, 180),
                x + size, y + size, new Color(60, 60, 60));
        g2.setPaint(metal);
        g2.fillOval(x, y, size, size);

        g2.setColor(new Color(40, 40, 40));
        g2.drawOval(x, y, size, size);

        g2.setColor(new Color(255, 255, 255, 120));
        g2.fillOval(x + 2, y + 2, size / 3, size / 3);

        g2.setColor(new Color(0, 0, 0, 80));
        g2.drawArc(x + 2, y + 2, size - 4, size - 4, 200, 140);
    }

    // Custom UI — wood track, round metal thumb
    private class MenuSliderUI extends BasicSliderUI {

        public MenuSliderUI(JSlider slider) { super(slider); }

        @Override
        public void paintTrack(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int cx = trackRect.x;
            int cy = trackRect.y + trackRect.height / 2 - 5;
            int cw = trackRect.width;
            int ch = 10;

            // Dark carved-in groove
            g2.setColor(new Color(30, 20, 10, 200));
            g2.fillRoundRect(cx, cy, cw, ch, 8, 8);

            // Filled portion (warm gold tint)
            int filled = xPositionForValue(slider.getValue()) - cx;
            g2.setColor(new Color(180, 130, 50, 200));
            g2.fillRoundRect(cx, cy, filled, ch, 8, 8);

            // Inner highlight on the groove
            g2.setColor(new Color(255, 255, 255, 30));
            g2.fillRoundRect(cx, cy, cw, ch / 2, 8, 8);

            g2.setColor(new Color(0, 0, 0, 100));
            g2.setStroke(new BasicStroke(1));
            g2.drawRoundRect(cx, cy, cw, ch, 8, 8);

            g2.dispose();
        }

        @Override
        public void paintThumb(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int tx = thumbRect.x;
            int ty = thumbRect.y;
            int tw = thumbRect.width;
            int th = thumbRect.height;

            // Metal thumb — same gradient as bolts, just bigger
            GradientPaint metal = new GradientPaint(
                    tx, ty,         new Color(200, 200, 200),
                    tx + tw, ty + th, new Color(60, 60, 60));
            g2.setPaint(metal);
            g2.fillOval(tx, ty, tw, th);

            g2.setColor(new Color(40, 40, 40));
            g2.setStroke(new BasicStroke(2));
            g2.drawOval(tx, ty, tw, th);

            // Highlight dot
            g2.setColor(new Color(255, 255, 255, 160));
            g2.fillOval(tx + tw / 4, ty + th / 4, tw / 3, th / 3);

            g2.dispose();
        }

        @Override
        public void paintFocus(Graphics g) {}
        @Override
        public void paintLabels(Graphics g) {}
        @Override
        public void paintTicks(Graphics g) {}
    }
}