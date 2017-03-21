package Codingame.Game;

import Codingame.Simulate.Point;

/**
 * Created by Owner on 2/19/2017.
 */
public class Move {
    public double angle;
    public Point target;
    public int thrust;

    public Move(double angle, int thrust) {
        this.angle = angle;
        this.target = target;
        this.thrust = thrust;
    }
}
