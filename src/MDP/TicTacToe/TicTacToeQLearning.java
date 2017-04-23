package MDP.TicTacToe;

import Model.NormalBoard;
import burlap.behavior.policy.GreedyQPolicy;
import burlap.behavior.singleagent.Episode;
import burlap.behavior.singleagent.learning.tdmethods.QLearning;
import burlap.mdp.singleagent.SADomain;
import burlap.mdp.singleagent.environment.Environment;
import burlap.mdp.singleagent.environment.EnvironmentOutcome;
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
        List<List<Double>> allRewards =  new ArrayList<>();
        for (int gammaI = 0; gammaI < 10; gammaI ++) {
            double gamma = gammaI * .1 + .1;
            QLearning qLearning = new QLearning(d, gamma, new SimpleHashableStateFactory(), .04, .4);
            List<Episode> episodes = new ArrayList<Episode>();
            List<Double> rewards = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                System.out.println(i);
                double rewardSum = 0;
                Episode e = (qLearning.runLearningEpisode(new TicTacToeEnvironment(new NormalBoard(3))));
                Environment env = new TicTacToeEnvironment(new NormalBoard(3));
                double accumulatedReward = 0;
                for (int j = 0; j < 5; j++) {
                    System.out.println("attempt to generate average results now" + j + "\n");
                    double discount = 1;
                    GreedyQPolicy p = qLearning.planFromState(env.currentObservation());
                    while (!env.isInTerminalState()) {
                        EnvironmentOutcome eo = env.executeAction(p.action(env.currentObservation()));
                        accumulatedReward += eo.r * discount;
                        discount *= .5;
                    }
                    accumulatedReward /= 100;
                    rewards.add(accumulatedReward);
                    env.resetEnvironment();
                }
            }

            allRewards.add(rewards);
        }

        try (FileWriter writer = new FileWriter("C:\\Users\\Mohammed\\JavaProjects\\MachineLearningProject4\\StandardizedTicTacToeQRewards.csv")) {
            for (int i = 0; i < allRewards.get(0).size(); i++) {
                writer.write("" + i + ",");
            }
            for (int i = 0; i < allRewards.size(); i++) {
                List<Double> rewards = allRewards.get(i);
                writer.write("\n");
                for (int j = 0; j < rewards.size(); j++) {
                    writer.write("" + rewards.get(j) + ",");
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
