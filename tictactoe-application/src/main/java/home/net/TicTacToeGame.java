package home.net;

import org.springframework.stereotype.Component;

@Component
public class TicTacToeGame {

    private TicTacToe tictactoe;

    public TicTacToeGame() {
        tictactoe = new TicTacToe();
    }

    public String[][] getGameboard() {
        return tictactoe.gameBoard();
    }

    public String getTurn() {
        return tictactoe.turn();
    }

}
