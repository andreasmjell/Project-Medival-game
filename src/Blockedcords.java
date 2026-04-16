import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class BlockedCords {

    private boolean[][] blocked;
    private int width;
    private int height;

    public void loadBlockedMap() {
        try {
            BufferedImage img = ImageIO.read(new File("mapBlocked.png"));

            width = img.getWidth();
            height = img.getHeight();

            blocked = new boolean[width][height];

            int blockedColor = Color.RED.getRGB();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {

                    if (img.getRGB(x, y) == blockedColor) {
                        blocked[x][y] = true;
                    }
                }
            }

            System.out.println("Blocked map lastet!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isBlocked(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return true;
        }
        return blocked[x][y];
    }
}