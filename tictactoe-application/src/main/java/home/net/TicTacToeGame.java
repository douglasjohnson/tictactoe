package home.net;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class TicTacToeGame {

    private static final String CROSSES_SYMBOL = "X";
    private static final String NOUGHTS_SYMBOL = "O";
    private static final String CROSSES = "Crosses";
    private static final String NOUGHTS = "Noughts";

    private TicTacToe tictactoe;

    public TicTacToeGame() {
        tictactoe = new TicTacToe();
    }

    public String[][] getGameboard() {
        return tictactoe.gameBoard();
    }

    public String getTurn() {
        return tictactoe.turn() == NOUGHTS_SYMBOL ? NOUGHTS : CROSSES;
    }

    public void switchFirstTurn() {
        tictactoe.firstTurn(tictactoe.turn() == NOUGHTS_SYMBOL ? CROSSES_SYMBOL : NOUGHTS_SYMBOL);
    }

    public void move(int row, int column) {
        tictactoe.move(row, column);
    }

}
