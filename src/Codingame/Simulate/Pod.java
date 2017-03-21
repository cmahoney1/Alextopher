package Codingame.Simulate;

import Codingame.Util;

/**
 * Created by Owner on 2/18/2017.
 */
public class Pod extends Entity {
    public double angle;
    public int shield;
    public int timeout;
    public int checked;

    public Pod(Vector position, Vector velocity, double angle) {
        super(position, velocity, Util.podRadius);
        this.angle = angle;
        this.shield = 0;
        this.timeout = 100;
        this.checked = 0;
    }

    /**
     * Finds the angle to a point P
     * @param p point
     * @return
     */
    public double getAngle(Point p) {
        double d = this.thisPoint().distance(p);
        double dx = (p.x - this.position.magnitudeX) / d;
        double dy = (p.y - this.position.magnitudeY) / d;

        double a = Math.acos(dx) * 180 / Math.PI;

        if(dy < 0) {
            a = 360 - a;
        }

        return a;
    }

    /**
     * Find the angle needed to turn to be facing stright to a point
     * @param p Point
     * @return
     */
    double diffAngle(Point p) {
        double a = this.getAngle(p);

        double right = this.angle <= a ? a - this.angle : 360 - this.angle + a;
        double left = this.angle >= a ? this.angle - a: this.angle + 360 - a;

        if(right < left) {
            return right;
        } else {
            return -left;
        }
    }

    public void rotate(Point p) {
        double a = this.diffAngle(p);

        if(a > 18.0) {
            a = 18.0;
        } else if(a < -18.0){
            a = -18.0;
        }

        this.angle += a;

        if(this.angle >= 360.0) {
            this.angle = this.angle - 360.0;
        } else if(this.angle < 0.0) {
            this.angle += 360.0;
        }
    }

    public void boost(int thrust) {
        if(this.shield > 0) {
            return;
        }

        double ra = this.angle * Math.PI /180.0;

        this.accelerate(new Vector(Math.cos(ra), Math.sin(ra)).scalar(thrust));
    }

    public void end() {
        this.position.magnitudeX = Math.round(this.position.magnitudeX);
        this.position.magnitudeY = Math.round(this.position.magnitudeY);
        this.velocity.magnitudeX = (int) this.velocity.magnitudeX;
        this.velocity.magnitudeY = (int) this.velocity.magnitudeY;

        this.timeout -= 1;
    }

    public void play(Point p, int thrust) {
        this.rotate(p);
        this.boost(thrust);
        this.move(1.0);
        this.end();
    }

    /**
     * Calculates time to collision if there is a collision
     * @param e
     * @return
     */
    public Collision collision(Entity e) {
        //Entities going to same speed will never collide
        if(!this.velocity.equals(e.velocity)) {

            //If they are close there is already a collision
            if (this.position.sub(e.position).magnitude < this.radius + e.radius) {
                return new Collision(this, e, 0);
            }

            //Make e stationary at 0,0
            double x = this.position.magnitudeX - e.position.magnitudeX;
            double y = this.position.magnitudeY - e.position.magnitudeY;
            double vx = this.velocity.magnitudeX - e.velocity.magnitudeX;
            double vy = this.velocity.magnitudeY - e.velocity.magnitudeY;

            //Line that represents path
            Point a = new Point(x, y);
            Point b = new Point(x + vx, y + vy);

            //E = 0,0
            Point c = new Point(0, 0);

            Point closestLocation = c.closest(a, b);

            double dist = closestLocation.distance2(c);

            if (dist <= e.radius * e.radius + this.radius * this.radius) {
                double t = closestLocation.x / b.x;

                //Collision happens soon
                if(t < 1) {
                    return new Collision(this, e, t);
                }
            }
        }
        return null;
    }

    public Vector targetFromAngle(double angle) {
        angle += this.angle;
        double magnitude = 100000;
        return Util.VectorAngle(angle, magnitude);
    }

    public void bounceWithCheckpoint(CheckPoint c) {

    }

    public void bounce(Collision c) {

    }
}