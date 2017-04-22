package MDP.TicTacToe;

import Model.NormalBoard;
import burlap.mdp.core.state.State;

import java.util.*;

/**
 * Created by Mohammed on 4/20/2017.
 */
public class TicTacToeState implements State {
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

    @Override
    public State copy() {
        return new TicTacToeState(board.copy());
    }
}
