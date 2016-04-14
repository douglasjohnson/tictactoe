package home.net;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class TicTacToeGame {

    private TicTacToe tictactoe;

    public TicTacToeGame() {
        tictactoe = new TicTacToe();
    }

    public String[][] getGameboard() {
        return tictactoe.gameBoard();
    }

    public String getTurn() {
        return tictactoe.turn() == "O" ? "Noughts" : "Crosses";
    }

    public void switchFirstTurn() {
        tictactoe.firstTurn(tictactoe.turn() == "O" ? "X" : "O");
    }

    public void move(int row, int column) {
        tictactoe.move(row, column);
    }

}
