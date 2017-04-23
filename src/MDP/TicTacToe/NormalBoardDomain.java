package MDP.TicTacToe;

import Model.NormalBoard;
import burlap.mdp.core.action.Action;
import burlap.mdp.core.action.ActionType;
import burlap.mdp.core.state.State;
import burlap.mdp.singleagent.SADomain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammed on 4/20/2017.
 */
public class NormalBoardDomain {
    static int num = 3;
    public static SADomain generateNewDomain() {
        NormalBoard board = new NormalBoard(num);
        SADomain domain = new SADomain();
        domain.addActionType(new NormalBoardAction());
        domain.setModel(new TicTacToeSampler());
        return domain;
    }

    public static class NormalBoardAction implements ActionType {

        @Override
        public String typeName() {
            return "NormalBoardActions";
        }

        @Override
        public Action associatedAction(String s) {
            String[] splittedActionRep = s.split(",");
            int row = Integer.parseInt(splittedActionRep[0]);
            int col = Integer.parseInt(splittedActionRep[1]);
            return new TicTacToeMove(row, col);
        }

        @Override
        public List<Action> allApplicableActions(State state) {
            List<Action> numbers = new ArrayList<>(num * num);
            for (int i = 0; i <= num - 1; i++) {
                for (int j = 0; j <= num - 1; j++) {
                    Action action = new TicTacToeMove(i, j);
                    NormalBoard board = (NormalBoard) state.get("Board");
                    if (!board.access(i, j).isFinished())
                        numbers.add(action);
                    }
                }
            return numbers;
            }
        }
    }




