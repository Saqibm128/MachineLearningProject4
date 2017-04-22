package MDP.TicTacToe;

import burlap.mdp.core.action.Action;

/**
 * Created by Mohammed on 4/20/2017.
 */
public class TicTacToeMove implements Action {
    int row;
    int col;

    public TicTacToeMove(int row, int col) {
        super();
        this.row = row;
        this.col = col;
    }

    @Override
    public String actionName() {
        return ""+row+","+col;
    }

    @Override
    public Action copy() {
        return new TicTacToeMove(row, col);
    }
}
