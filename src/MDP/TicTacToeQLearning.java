package MDP;

import Model.NormalBoard;
import burlap.behavior.singleagent.learning.tdmethods.QLearning;
import burlap.mdp.singleagent.SADomain;
import burlap.statehashing.simple.SimpleHashableStateFactory;

/**
 * Created by Mohammed on 4/20/2017.
 */
public class TicTacToeQLearning {
    public static void main(String[] arg) {
        QLearning qLearning = new QLearning(NormalBoardDomain.generateNewDomain(), .1, new SimpleHashableStateFactory(), -.04, .4);
        for (int i = 0; i < 50; i++) {
            System.out.println(i);
            qLearning.runLearningEpisode(new TicTacToeEnvironment(new NormalBoard(3)));
        }

    }
}
