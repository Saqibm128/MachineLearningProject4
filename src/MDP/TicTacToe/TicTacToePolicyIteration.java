package MDP.TicTacToe;

import Model.NormalBoard;
import burlap.behavior.policy.GreedyQPolicy;
import burlap.behavior.singleagent.planning.stochastic.policyiteration.PolicyIteration;
import burlap.behavior.singleagent.planning.stochastic.valueiteration.ValueIteration;
import burlap.mdp.singleagent.SADomain;
import burlap.mdp.singleagent.environment.EnvironmentOutcome;
import burlap.statehashing.HashableStateFactory;
import burlap.statehashing.simple.SimpleHashableStateFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * performs policy iteration for single agent domain of Tic Tac Toe
 *  is single agent because opponent is RNG
 * Created by Mohammed on 4/22/2017.
 */
public class TicTacToePolicyIteration {
    static int num = 3;

    public static void main(String[] args) {
        SADomain d = NormalBoardDomain.generateNewDomain();

        //create list to hold important stats
        List<List<Double>> allRewards = new ArrayList<>();
        List<List<Long>> allTimes = new ArrayList<>();

        //iterate from lamda = 0 to lamda = 1
        for (int gammaI = 0; gammaI < 10; gammaI++) {
            double gamma = gammaI * .1 + .1;
            List<Double> rewards = new ArrayList<>();
            List<Long> times = new ArrayList<>();
            //iterates over both num policy iterations and num value iterations
            for (int numIterations = 0; numIterations < 500; numIterations = numIterations + 10) {
                Long begin = System.currentTimeMillis();
                PolicyIteration policyIteration = new PolicyIteration(d, gamma, new SimpleHashableStateFactory(), .01, numIterations, numIterations);
                policyIteration.toggleDebugPrinting(false);
                NormalBoard normalBoard = new NormalBoard(num);
                GreedyQPolicy greedyQPolicy = policyIteration.planFromState(new TicTacToeState(normalBoard));
                greedyQPolicy.policyDistribution(new TicTacToeState(new NormalBoard(num)));
                TicTacToeEnvironment env = new TicTacToeEnvironment(new NormalBoard(num));
                double accumulatedReward = 0;
                Long end = System.currentTimeMillis();
                for (int i = 0; i < 100; i++) {
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
                times.add(end - begin);
                System.out.print("Iteration: " + numIterations + " Time: " + (end - begin) + "\n");
            }
            allRewards.add(rewards);
            allTimes.add(times);
        }
        try (FileWriter writer = new FileWriter("C:\\Users\\Mohammed\\JavaProjects\\MachineLearningProject4\\StandardizedTicTacToePolicyItR.csv")) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileWriter writer = new FileWriter("C:\\Users\\Mohammed\\JavaProjects\\MachineLearningProject4\\StandardizedTicTacToePolicyItRTimes.csv")) {
            for (int i = 0; i < allTimes.get(0).size(); i++) {
                writer.write("" + i + ",");
            }
            for (int i = 0; i < allTimes.size(); i++) {
                writer.write("\n");
                List<Long> times = allTimes.get(i);
                for (int j = 0; j < times.size(); j ++) {
                    writer.write("" + times.get(j) + ",");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
