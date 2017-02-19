package Codingame.Game;

import Codingame.Simulate.Entity;
import Codingame.Simulate.Pod;

/**
 * Created by Owner on 2/19/2017.
 */
public class Game {
    public Pod[] myPods;
    public Pod[] opponentPods;
    public Entity[] checkpoints;

    public Game(Pod[] myPods, Pod[] opponentPods, Entity[] checkpoints) {
        this.myPods = myPods;
        this.opponentPods = opponentPods;
        this.checkpoints = checkpoints;
    }

    public void play(Move[] moves) {
        for(int i = 0; i < myPods.length; i++) {
            myPods[i].play(moves[i].target, moves[i].thrust);
        }
        if(moves.length == 4) {
            for(int i = 2; i < 4; i++) {
                opponentPods[i].play(moves[i].target, moves[i].thrust);
            }
        }
    }
}
