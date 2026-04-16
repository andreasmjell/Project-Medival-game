package src;
import java.util.ArrayList;

public class Pathfinder {
    
    public ArrayList<Point> findPath(double startX, double startY, double targetXin, double targetYin, BlockedCords blocked){
        ArrayList<Point> path = new ArrayList<>();

        double targetX = targetXin;
        double targetY = targetYin;

        double x = startX;
        double y = startX;

        double dx = targetX - startX;
        double dy = targetY - startY;
        double dist = Math.sqrt(dx * dx + dy * dy);

        double dirX = dx / dist;
        double dirY = dy / dist;

        double prex = targetX;
        double prey = targetY;

        while(x != targetX && y != targetY){
            if (dist <= 5) {
                x = targetX;
                y = targetY;
            }
            prex = x;
            prey = y;
            x += dirX;
            y += dirY;

            if (blocked.isBlocked((int)x, (int)y)){
                targetX = prex;
                targetY = prey;
                x = targetX;
                y = targetY;
            }
        }

        Point point = new Point(targetX, targetY);

        path.add(point);

        return path;

    }
}
