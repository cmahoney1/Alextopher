package Codingame.Simulate;

/**
 * Created by Owner on 2/19/2017.
 */
public class Collision {
    public Entity entity1;
    public Entity entity2;
    public double t;

    public Collision(Entity entity1, Entity entity2, double t) {
        this.entity1 = entity1;
        this.entity2 = entity2;
        this.t = t;
    }
}
