package home.net.application;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import home.net.engine.TicTacToe;

@Component
@Scope("session")
public class TicTacToeGame {

    private static final String CROSSES_SYMBOL = "X";
    private static final String NOUGHTS_SYMBOL = "O";
    private static final String CROSSES = "Crosses";
    private static final String NOUGHTS = "Noughts";

    private TicTacToe tictactoe;

    public TicTacToeGame() {
        reset();
    }

    public String[][] getGameboard() {
        return tictactoe.gameBoard();
    }

    public String getTurn() {
        return convertSymbolToText(tictactoe.turn());
    }

    public String getResult() {
        String result = null;
        if (getTurn() == null) {
            String winner = tictactoe.winner();
            result = winner != null ? "Winner: " + convertSymbolToText(winner) : "Draw";
        }
        return result;
    }

    public boolean getSwitchFirstTurnAllowed() {
        return tictactoe.noMovesPlayed();
    }

    public void switchFirstTurn() {
        tictactoe.firstTurn(tictactoe.turn() == NOUGHTS_SYMBOL ? CROSSES_SYMBOL : NOUGHTS_SYMBOL);
    }

    public void move(int row, int column) {
        tictactoe.move(row, column);
    }

    private String convertSymbolToText(String symbol) {
        String text = null;
        if (NOUGHTS_SYMBOL.equals(symbol)) {
            text = NOUGHTS;
        } else if (CROSSES_SYMBOL.equals(symbol)) {
            text = CROSSES;
        }
        return text;
    }

    public void reset() {
        tictactoe = new TicTacToe();
    }

}
