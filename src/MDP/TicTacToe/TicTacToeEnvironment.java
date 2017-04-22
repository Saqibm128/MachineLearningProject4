package MDP.TicTacToe;

import Model.NormalBoard;
import burlap.mdp.core.action.Action;
import burlap.mdp.core.state.State;
import burlap.mdp.singleagent.environment.Environment;
import burlap.mdp.singleagent.environment.EnvironmentOutcome;

import java.util.Random;

/**
 * Created by Mohammed on 4/20/2017.
 */
public class TicTacToeEnvironment implements Environment {
    NormalBoard board;
    double reward = 0;
    public TicTacToeEnvironment(NormalBoard board) {
        this.board = board;
    }

    @Override
    public State currentObservation() {
        return new TicTacToeState(board);
    }

    @Override
    public EnvironmentOutcome executeAction(Action action) {
        TicTacToeMove move = (TicTacToeMove) action;
        NormalBoard newBoard = board.copy();
        boolean isX =  true;
        int row = move.row;
        int col = move.col;
        newBoard.play(isX, row, col);
        boolean terminated;
        if (newBoard.winner() == 'x') {
            reward = 1000;} //our agent won
        else if (newBoard.winner() == 'o') {
            reward = -1000;} //our agent lost
        else
            reward = -4; //our agent might be wasting time
        Random rand = new Random();
        while(!newBoard.isFinished() && !newBoard.play(false, rand.nextInt(3), rand.nextInt(3)));
        TicTacToeState newState = new TicTacToeState(newBoard);
        terminated = newBoard.isFinished();
        EnvironmentOutcome outcome = new EnvironmentOutcome(currentObservation(), action,  newState, reward, terminated);
        board = newBoard;
        return outcome;
    }

    @Override
    public double lastReward() {
        return reward;
    }

    @Override
    public boolean isInTerminalState() {
        return board.isFinished();
    }

    @Override
    public void resetEnvironment() {
        this.board = (new NormalBoard(3));
    }
}
