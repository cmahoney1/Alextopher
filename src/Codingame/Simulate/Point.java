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

    public Point add(Point p) {
        return new Point(this.x + p.x, this.y + p.y);
    }

    public Point sub(Point p) {
        return new Point(this.x - p.x, this.y - p.y);
    }
    /**
     * Returns the point on a line closest to THIS point
     * @param a line
     * @param b line
     * @return
     */
    public Point closest(Point a, Point b) {
        //If line is horizontal
        if(a.y == b.y) {
            return new Point(this.x, a.y);
        }
        //If line is vertical
        if(a.x == b.x) {
            return new Point(a.x, this.y);
        }
        //If line is neither
        //Line AB
        double m = (a.y - b.y) / (a.x - b.x);
        double yint = b.y - m * b.x;

        //Line which passes through 'THIS' and is perpendicular to AB
        double m2 = - 1 / m;
        double yint2 = this.y - m2 * this.x;

        //Solve for intercept
        double pointX = (yint2 - yint) / (m + 1 / m);
        double pointY = m * pointX + yint;

        return new Point(pointX, pointY);
    }
}