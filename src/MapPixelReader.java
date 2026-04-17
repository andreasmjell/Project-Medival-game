package src;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.geom.PathIterator;
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
    int amountTree = 0;
    int amountPath = 0;
    ArrayList<Image> trees = new ArrayList<>();
    ArrayList<Image> travelPath = new ArrayList<>();
    Random rand = new Random();
    int oga = 0;

    public MapPixelReader (MapController mapController){
        this.mapController = mapController;
        Image treeImage1 = new ImageIcon(getClass().getResource("assets/aboveTree1.png")).getImage();
        /*Image treeImage2 = new ImageIcon(getClass().getResource("assets/tree2.png")).getImage();
        Image treeImage3 = new ImageIcon(getClass().getResource("assets/tree3.png")).getImage();
        Image treeImage4 = new ImageIcon(getClass().getResource("assets/tree4.png")).getImage();
        Image treeImage5 = new ImageIcon(getClass().getResource("assets/tree5.png")).getImage();
        Image treeImage6 = new ImageIcon(getClass().getResource("assets/tree6.png")).getImage();
        Image treeImage7 = new ImageIcon(getClass().getResource("assets/tree7.png")).getImage();
        */trees.add(treeImage1);
        /*trees.add(treeImage2);
        trees.add(treeImage3);
        trees.add(treeImage4);
        trees.add(treeImage5);
        trees.add(treeImage6);
        trees.add(treeImage7);*/

        Image pathTile1 = new ImageIcon(getClass().getResource("assets/pathTile.png")).getImage();
        Image pathTile2 = new ImageIcon(getClass().getResource("assets/pathTile2.png")).getImage();
        travelPath.add(pathTile1);
        travelPath.add(pathTile2);
    }

    public void loadBlockedMap() {
        try {
            BufferedImage img = ImageIO.read(this.getClass().getResourceAsStream("assets/mapBlocked.png"));

            width = img.getWidth();
            height = img.getHeight();

            blocked = new boolean[width][height];

            int blockedColor = new Color(63, 72, 204).getRGB(); //Paint blå
            int treeColor = new Color(34, 177, 76).getRGB(); //Paint grønn
            int roadColor = new Color(127,127,127).getRGB(); //Paint grå

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {

                    if (img.getRGB(x, y) == blockedColor) {
                        blocked[x][y] = true;
                    }
                    else if (img.getRGB(x, y) == treeColor){
                        blocked[x][y] = false;
                        if (Math.random() > 0.9999){
                            int randomtree = rand.nextInt(trees.size());
                            Image treeImagePaint = trees.get(randomtree);
                            TreeObject tree = new TreeObject(x,y, treeImagePaint);
                            mapController.addDrawable(tree);
                            amountTree++;
                        }
                    }
                    else if (img.getRGB(x,y) == oga){
                        int randomPathTile = rand.nextInt(travelPath.size());
                        Image pathImagePaint = travelPath.get(randomPathTile);
                        PathTile path = new PathTile(x, y, pathImagePaint);
                        mapController.addDrawable(path);
                        amountPath++;
                        blocked[x][y] = false;
                    }
                    else {
                        blocked[x][y] = false;
                    }
                }
            }

            System.out.println("Blocked map lastet!");
            System.out.println("Antall trær" + amountTree);
            System.out.println("Antall stier" + amountPath);

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