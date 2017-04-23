package MDP.TicTacToe;

import Model.NormalBoard;
import burlap.mdp.core.state.State;
import burlap.statehashing.HashableState;

import java.util.*;

/**
 * Created by Mohammed on 4/20/2017.
 */
public class TicTacToeState implements State, HashableState {
    NormalBoard board;
    Map<String, Object> stateData;
    public TicTacToeState(NormalBoard board) {
        this.board = board;
        stateData = new HashMap<>();
        stateData.put("Board", board);
    }
    @Override
    public List<Object> variableKeys() {
        List<Object> keys = new ArrayList<>();
        keys.addAll(stateData.keySet());
        return keys;
    }

    @Override
    public Object get(Object o) {
        String s = (String) o;
        return stateData.get(o);
    }

    public NormalBoard getBoard() {
        return board;
    }

    @Override
    public State copy() {
        return new TicTacToeState(board.copy());
    }

    @Override
    public int hashCode() {
        return ((NormalBoard) get("Board")).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof TicTacToeState)) {
            return false;
        }
        NormalBoard otherBoard = (NormalBoard)(((TicTacToeState) o).get("Board"));
        NormalBoard thisBoard = (NormalBoard) this.get("Board");
        return thisBoard.equals(otherBoard);
    }


    @Override
    public State s() {
        return this;
    }
}
