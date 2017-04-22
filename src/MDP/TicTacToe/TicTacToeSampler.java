package MDP.TicTacToe;

import Model.NormalBoard;
import burlap.mdp.core.action.Action;
import burlap.mdp.core.state.State;
import burlap.mdp.singleagent.environment.EnvironmentOutcome;
import burlap.mdp.singleagent.model.SampleModel;

import java.util.Random;

/**
 * Created by Mohammed on 4/20/2017.
 */
public class TicTacToeSampler implements SampleModel {
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
            reward = 0;
        Random rand = new Random();
        while(!newBoard.play(false, rand.nextInt(3), rand.nextInt(3)));
        TicTacToeState newState = new TicTacToeState(newBoard);
        EnvironmentOutcome outcome = new EnvironmentOutcome(state, action,  newState, reward, terminated);
        return outcome;
    }

    @Override
    public boolean terminal(State state) {
        return ((NormalBoard)state.get("Board")).isFinished();
    }
}
