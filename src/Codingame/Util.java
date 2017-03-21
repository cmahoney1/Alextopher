package Codingame;

import Codingame.Simulate.Vector;

/**
 * Created by Owner on 3/21/2017.
 */
public class Util {
    /**
     * Vector from angle and magnitude
     * @param theta
     * @param magnitude
     */
    public static Vector VectorAngle(double theta, double magnitude) {
        return new Vector(Math.cos(theta) * magnitude, Math.sin(theta) * magnitude);
    }

    /**
     * Radius of a Checkpoint
     */
    public static final int checkpointRadius = 800;

    /**
     * Radius of a Pod
     */
    public static final int podRadius = 400;
}
