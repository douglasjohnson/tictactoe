package home.net;

public class TicTacToe {

    private static final String NOUGHTS = "O";
    private static final String CROSSES = "X";

    private String nextTurn = NOUGHTS;
    private String[][] gameBoard;
    private String winner = null;

    public TicTacToe() {
        gameBoard = new String[][] { { "", "", "" }, { "", "", "" }, { "", "", "" } };
    }

    public String[][] gameBoard() {
        return gameBoard;
    }

    public String turn() {
        return nextTurn;
    }

    public void firstTurn(String turn) {
        nextTurn = turn;
    }

    public boolean move(int row, int column) {
        boolean moveAllowed = gameBoard[row - 1][column - 1].equals("");
        if (moveAllowed) {
            gameBoard[row - 1][column - 1] = nextTurn;
            checkWinningMove(row, column);
            nextTurn = NOUGHTS.equals(nextTurn) ? CROSSES : NOUGHTS;
        }
        return moveAllowed;
    }

    private void checkWinningMove(int row, int column) {
        String turn = gameBoard[row - 1][column - 1];
        if (rowWin(turn, row) || columnWin(turn, column) || forwardDiagonalWin(turn) || backwardDiagonalWin(turn)) {
            winner = turn;
        }
    }

    private boolean rowWin(String turn, int row) {
        return gameBoard[row - 1][0].equals(turn) && gameBoard[row - 1][1].equals(turn)
                && gameBoard[row - 1][2].equals(turn);
    }

    private boolean columnWin(String turn, int column) {
        return gameBoard[0][column - 1].equals(turn) && gameBoard[1][column - 1].equals(turn)
                && gameBoard[2][column - 1].equals(turn);
    }

    private boolean forwardDiagonalWin(String turn) {
        return gameBoard[0][0].equals(turn) && gameBoard[1][1].equals(turn) && gameBoard[2][2].equals(turn);
    }

    private boolean backwardDiagonalWin(String turn) {
        return gameBoard[0][2].equals(turn) && gameBoard[1][1].equals(turn) && gameBoard[2][0].equals(turn);
    }

    public String winner() {
        return winner;
    }

}
