package Codingame.Simulate;

/**
 * Created by Owner on 2/18/2017.
 */
public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance2(Point p) {
        return (this.x - p.x) * (this.x - p.x) + (this.y - p.y) * (this.y - p.y);
    }

    public double distance(Point p) {
        return Math.sqrt(this.distance2(p));
    }

    public Point closest(Point a, Point b) {
        double da = b.y - a.y;
        double db = a.x - b.x;
        double c1 = da*a.x + db * a.y;
        double c2 = -db * this.x + da*this.y;
        double det = da*da + db*db;
        double cx = 0;
        double cy = 0;

        if(det != 0) {
            cx = (da * c1 - db * c2) / det;
            cy = (da * c2 + db * c1) / det;
        } else {
            cx = this.x;
            cy = this.y;
        }

        return new Point(cx, cy);
    }
}