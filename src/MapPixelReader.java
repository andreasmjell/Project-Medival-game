package src;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class MapPixelReader {

    private boolean[][] blocked;
    private int width;
    private int height;

    public MapPixelReader {
        
    }

    public void loadBlockedMap() {
        try {
            BufferedImage img = ImageIO.read(this.getClass().getResourceAsStream("assets/mapBlocked.png"));

            width = img.getWidth();
            height = img.getHeight();

            blocked = new boolean[width][height];

            int blockedColor = new Color(237, 28, 36).getRGB();
            int treeColor = new Color(34, 177, 76).getRGB();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {

                    if (img.getRGB(x, y) == blockedColor) {
                        blocked[x][y] = true;
                    }
                    else if (img.getRG(x,y) == treeColor){
                        if (Match.random() > 0.6){
                            TreeObjekt tree = new TreeObjekt(x,y);
                        }
                    }
                    else{
                        blocked[x][y] = false;
                    }
                }
            }

            System.out.println("Blocked map lastet!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isBlocked(int x, int y) {
    if (x < 0 || x >= width || y < 0 || y >= height) {
        return true; // Treat "out of bounds" as blocked
    }
    return blocked[x][y];
    }
}