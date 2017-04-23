package MDP.TicTacToe;

import burlap.mdp.core.state.State;
import burlap.statehashing.HashableState;
import burlap.statehashing.HashableStateFactory;

/**
 * Created by Mohammed on 4/22/2017.
 */
public class TicTacToeHashStateFactory implements HashableStateFactory {
    @Override
    public HashableState hashState(State state) {
        return (TicTacToeState) state;
    }
}
