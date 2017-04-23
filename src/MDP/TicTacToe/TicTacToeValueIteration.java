package MDP.TicTacToe;

import Model.NormalBoard;
import burlap.behavior.policy.GreedyQPolicy;
import burlap.behavior.singleagent.planning.stochastic.valueiteration.ValueIteration;
import burlap.mdp.singleagent.SADomain;
import burlap.mdp.singleagent.environment.EnvironmentOutcome;
import burlap.statehashing.HashableState;
import burlap.statehashing.HashableStateFactory;
import burlap.statehashing.simple.SimpleHashableStateFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Performs value iteration for single agent domain of Tic Tac Toe
 *  is single agent because opponent is RNG
 * Created by Mohammed on 4/22/2017.
 */
public class TicTacToeValueIteration {
    public static void main(String[] args) {
        SADomain d = NormalBoardDomain.generateNewDomain();
        HashableStateFactory factory = new TicTacToeHashStateFactory();

        //stores all values of interest over lambda iteration and maxNumIterations allowed
        List<List<Double>> allRewards = new ArrayList<>();
        List<List<Long>> allTimes = new ArrayList<>();
        //iterate from lambda = 0 to lambda = 1
        for (int gammaI = 0; gammaI < 10; gammaI++) {
            List<Double> rewards = new ArrayList<>();
            List<Long> times = new ArrayList<>();
            double gamma = gammaI * .1 + .1;
            //iterate through max numIterations allowed
            for (int numIterations = 0; numIterations < 50; numIterations++) {
                long time = System.currentTimeMillis();
                ValueIteration valueIteration = new ValueIteration(d, gamma, factory, .01, numIterations);
                valueIteration.toggleReachabiltiyTerminalStatePruning(true);
                valueIteration.toggleDebugPrinting(false);
                NormalBoard normalBoard = new NormalBoard(3);
                GreedyQPolicy greedyQPolicy = valueIteration.planFromState(new TicTacToeState(normalBoard));
                greedyQPolicy.policyDistribution(new TicTacToeState(new NormalBoard(3)));
                long end = System.currentTimeMillis();
                long duration = end - time;
                TicTacToeEnvironment env = new TicTacToeEnvironment(new NormalBoard(3));
                //test out our new policy in an environment
                double accumulatedReward = 0;
                for (int i = 0; i < 100; i++) { //since we have a random agent, we do this 100 more times to be sure we don't do any weird conclusions
                    double discount = 1;
                    while (!env.isInTerminalState()) {
                        EnvironmentOutcome eo = env.executeAction(greedyQPolicy.action(env.currentObservation()));
                        accumulatedReward += eo.r * discount;
                        discount *= .5;
                    }
                    env.resetEnvironment();
                }
                accumulatedReward /= 100;
                rewards.add(accumulatedReward);
                times.add(duration);
                System.out.print("Iteration: " + numIterations + " Time: " + duration + "\n");
            }
            allRewards.add(rewards);
            allTimes.add(times);
        }
        try (FileWriter writer = new FileWriter("C:\\Users\\Mohammed\\JavaProjects\\MachineLearningProject4\\StandardizedTicTacToeValItR.csv")) {
            for (int i = 0; i < allRewards.get(0).size(); i++) {
                writer.write("" + i + ",");
            }
            for (int i = 0; i < allRewards.size(); i ++) {
                List<Double> rewards = allRewards.get(i);
                writer.write("\n");
                for (int j = 0; j < rewards.size(); j ++) {
                    writer.write("" + rewards.get(j) + ",");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileWriter writer = new FileWriter("C:\\Users\\Mohammed\\JavaProjects\\MachineLearningProject4\\StandardizedtimesTTTValIt.csv")) {
            for (int i = 0; i < allTimes.get(0).size(); i++) {
                writer.write("" + i + ",");
            }
            for (int i = 0; i < allTimes.size(); i ++) {
                List<Long> times = allTimes.get(i);
                writer.write("\n");
                for (int j = 0; j < times.size(); j ++) {
                    writer.write("" + times.get(j) + ",");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
