package Codingame.Game;

import Codingame.Simulate.Point;

/**
 * Created by Owner on 2/19/2017.
 */
public class Move {
    public Point target;
    public int thrust;

    public Move(Point target, int thrust) {
        this.target = target;
        this.thrust = thrust;
    }
}
