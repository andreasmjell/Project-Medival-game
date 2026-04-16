package src;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Random;

public class MapPixelReader {

    private boolean[][] blocked;
    private int width;
    private int height;
    MapController mapController;
    int teller = 0;
    ArrayList<Image> trees = new ArrayList<>();
    Random rand = new Random();

    public MapPixelReader (MapController mapController){
        this.mapController = mapController;
        Image treeImage1 = new ImageIcon(getClass().getResource("assets/tree1.png")).getImage();
        Image treeImage2 = new ImageIcon(getClass().getResource("assets/tree2.png")).getImage();
        Image treeImage3 = new ImageIcon(getClass().getResource("assets/tree3.png")).getImage();
        Image treeImage4 = new ImageIcon(getClass().getResource("assets/tree4.png")).getImage();
        Image treeImage5 = new ImageIcon(getClass().getResource("assets/tree5.png")).getImage();
        Image treeImage6 = new ImageIcon(getClass().getResource("assets/tree6.png")).getImage();
        Image treeImage7 = new ImageIcon(getClass().getResource("assets/tree7.png")).getImage();
        trees.add(treeImage1);
        trees.add(treeImage2);
        trees.add(treeImage3);
        trees.add(treeImage4);
        trees.add(treeImage5);
        trees.add(treeImage6);
        trees.add(treeImage7);
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
                    else if (img.getRGB(x, y) == treeColor){
                        if (Math.random() > 0.9999){
                            int randomtree = rand.nextInt(trees.size());
                            Image treeImagePaint = trees.get(randomtree);
                            TreeObject tree = new TreeObject(x,y, treeImagePaint);
                            mapController.addDrawable(tree);
                            teller++;

                        }
                    }
                    else{
                        blocked[x][y] = false;
                    }
                }
            }

            System.out.println("Blocked map lastet!");
            System.out.println("Antall trær" + teller);

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