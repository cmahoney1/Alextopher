package Codingame.Simulate;

/**
 * Created by Owner on 2/18/2017.
 */
public class Entity {
    public Vector position;
    public Vector velocity;
    public double radius;

    public Entity(Vector position, Vector velocity, double radius) {
        this.position = position;
        this.velocity = velocity;
        this.radius = radius;
    }

    public Point thisPoint() {
        return new Point(this.position.magnitudeX, this.position.magnitudeY);
    }

    public void move(double t) {
        this.position.add(this.velocity.scalar(t));
    }

    public void accelerate(Vector v) {
        this.velocity.add(v);
    }

    public Collision collision(Entity e) {
        double dist = thisPoint().distance2(e.thisPoint());

        double sr = (this.radius + e.radius) * (this.radius + e.radius);

        if(dist < sr) {
            return new Collision(this, e, 0.0);
        }

        if(this.velocity.dotProduct(e.velocity) == 0) {
            return null;
        }

        Vector myp = this.position.sub(e.position);
        Vector myv = this.velocity.sub(e.velocity);
        Point up = new Point(0,0);
        Vector nextp = myp.add(myv);

        Point p = up.closest(new Point(myp.magnitudeX, myp.magnitudeY), new Point(nextp.magnitudeX, nextp.magnitudeY));

        double pdist = up.distance2(p);

        double mypdist = new Point(myp.magnitudeX, myp.magnitudeY).distance2(p);

        if(pdist < sr) {
            double length = myv.magnitude;

            double backdist = Math.sqrt(sr - pdist);
            p.x = p.x - backdist * (myv.magnitudeX / length);
            p.y = p.y - backdist * (myv.magnitudeY / length);

            double temp = new Point(myp.magnitudeX, myp.magnitudeY).distance2(p);
            if(temp > mypdist) {
                return null;
            }

            pdist = Math.sqrt(temp);

            if(pdist > length) {
                return null;
            }

            double t = pdist / length;

            return new Collision(this, e, t);
        }

        return null;
    }
}
