package Codingame.Simulate;

import Codingame.Util;

/**
 * Created by Owner on 3/20/2017.
 */
public class CheckPoint extends Entity {
    public CheckPoint(Vector position) {
        super(position, new Vector(0,0), Util.checkpointRadius);
    }
}
