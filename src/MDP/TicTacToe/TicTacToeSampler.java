package MDP.TicTacToe;

import Model.NormalBoard;
import burlap.mdp.core.action.Action;
import burlap.mdp.core.state.State;
import burlap.mdp.singleagent.environment.EnvironmentOutcome;
import burlap.mdp.singleagent.model.FullModel;
import burlap.mdp.singleagent.model.SampleModel;
import burlap.mdp.singleagent.model.TransitionProb;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Mohammed on 4/20/2017.
 */
public class TicTacToeSampler implements SampleModel, FullModel {
    @Override
    public EnvironmentOutcome sample(State state, Action action) {
        TicTacToeMove move = (TicTacToeMove) action;
        NormalBoard board = (NormalBoard) state.get("Board");
        NormalBoard newBoard = board.copy();
        boolean isX =  true;
        int row = move.row;
        int col = move.col;
        newBoard.play(isX, row, col);
        boolean terminated = false;
        int reward;
        if (newBoard.winner() == 'x') {
            reward = 1000;
            terminated = true;}
        else if (newBoard.winner() == 'o') {
            reward = -1000;
            terminated = true;}
        else
            reward = -40;
        Random rand = new Random();
        while(!newBoard.play(false, rand.nextInt(3), rand.nextInt(3)));
        TicTacToeState newState = new TicTacToeState(newBoard);
        terminated = newBoard.isFinished();
        EnvironmentOutcome outcome = new EnvironmentOutcome(state, action,  newState, reward, terminated);
        return outcome;
    }

    @Override
    public boolean terminal(State state) {
        return ((NormalBoard)state.get("Board")).isFinished();
    }

    @Override
    public List<TransitionProb> transitions(State state, Action action) {
        if (terminal(state)) {
            return new ArrayList<>();
        }
        NormalBoard board = (NormalBoard)state.get("Board");
        int col = ((TicTacToeMove)action).col;
        int row = ((TicTacToeMove)action).row;
        NormalBoard newBoard = board.copy();
        newBoard.play(true, col, row);
        if (newBoard.isFinished()) {
            return new ArrayList<>();
        }
        int[][] availableSpots = newBoard.availableSpots();
        List<TransitionProb> probs = new ArrayList<>();
        for (int i = 0; i < availableSpots.length; i++) {
            NormalBoard newerBoard = newBoard.copy();
            newerBoard.play(false, availableSpots[i][0], availableSpots[i][1]);
            int reward;
            if (newerBoard.winner() == 'x')  {
                reward = 1000;
            } else if (newerBoard.winner() == 'o') {
                reward = -1000;
            } else {
                reward = -4;
            }
            EnvironmentOutcome eo = new EnvironmentOutcome(state, action, new TicTacToeState(newerBoard), reward, newBoard.isFinished());
            TransitionProb transitionProb = new TransitionProb(1.0/(availableSpots.length), eo);
            probs.add(transitionProb);
        }
        return probs;
    }
}
