package Codingame.Simulate;

import Codingame.Game.Game;

/**
 * Created by Owner on 2/18/2017.
 */
public class Pod extends Entity {
    public double angle;
    public boolean shield;
    public int timeout;
    public int checked;

    public Pod(Vector position, Vector velocity, double angle) {
        super(position, velocity, 400);
        this.angle = angle;
        this.shield = false;
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
        if(this.shield) {
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

    public double bounceWithCheckpoint(Entity e) {

    }
}