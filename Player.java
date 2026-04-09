public class Player {
    int troops;
    int speed = 5;
    double x;
    double y;

    Path path;

    
    
    public Player(int x, int y, int troops){
        this.x = x;
        this.y = y;
        this.troops = troops;
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

    public void updatePos(){
        if (path == null || path.isDone()) return;

        Point target = path.getCurrent();

        double dx = target.x - x;
        double dy = target.y - y;
        double dist = Math.sqrt(dx * dx + dy * dy);

        if (dist <= 5) {
            x = target.x;
            y = target.y;
                
            path.advance();
                
            if (path.isDone()) {
                path = null;
            }
        
        } else {
            double dirX = dx / dist;
            double dirY = dy / dist;
        
            x += dirX * speed;
            y += dirY * speed;
        }
    }

    public void setPath(Path newPath) {
        this.path = newPath;
        System.out.println(path);
    }
}
