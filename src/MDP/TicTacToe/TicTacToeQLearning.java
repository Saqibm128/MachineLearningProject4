package MDP.TicTacToe;

import Model.NormalBoard;
import burlap.behavior.singleagent.Episode;
import burlap.behavior.singleagent.learning.tdmethods.QLearning;
import burlap.mdp.singleagent.SADomain;
import burlap.statehashing.simple.SimpleHashableStateFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammed on 4/20/2017.
 */
public class TicTacToeQLearning {
    public static void main(String[] arg) {
        SADomain d = NormalBoardDomain.generateNewDomain();
        QLearning qLearning = new QLearning(d, .1, new SimpleHashableStateFactory(), -.04, .4);
        List<Episode> episodes = new ArrayList<Episode>();
        List<Double> rewards = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            System.out.println(i);
            double rewardSum = 0;
            episodes.add(qLearning.runLearningEpisode(new TicTacToeEnvironment(new NormalBoard(3))));
            for(double reward: episodes.get(i).rewardSequence)
                rewardSum += reward;
            rewards.add(episodes.get(i).discountedReturn(.4));
        }

        try (FileWriter writer = new FileWriter("C:\\Users\\Mohammed\\Documents\\TicTacToeRewards.csv")) {
            for (int i = 0; i < rewards.size(); i++) {
                writer.write("" + rewards.get(i) + ",");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
