package home.net;

public class TicTacToe {

    private static final String EMPTY_SPACE = "";
    private static final String NOUGHTS = "O";
    private static final String CROSSES = "X";

    private String nextTurn = NOUGHTS;
    private String[][] gameBoard;
    private String winner = null;

    public TicTacToe() {
        gameBoard = new String[][] { { EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE },
                { EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE }, { EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE } };
    }

    public String[][] gameBoard() {
        return gameBoard;
    }

    public String turn() {
        return nextTurn;
    }

    public boolean firstTurn(String turn) {
        boolean firstTurnUpdated = false;
        if (noMovesPlayed()) {
            nextTurn = turn;
            firstTurnUpdated = true;
        }
        return firstTurnUpdated;
    }

    private boolean noMovesPlayed() {
        boolean noMovePlayed = true;
        for (int row = 0; row < gameBoard.length; row++) {
            String[] gameBoardRow = gameBoard[row];
            for (int column = 0; column < gameBoardRow.length; column++) {
                noMovePlayed &= EMPTY_SPACE.equals(gameBoardRow[column]);
            }
        }
        return noMovePlayed;
    }

    public boolean move(int row, int column) {
        boolean moveAllowed = nextTurn != null && validMove(row, column);
        if (moveAllowed) {
            gameBoard[row - 1][column - 1] = nextTurn;
            checkWinningMove(row, column);
            if (winner == null && movesRemaining()) {
                nextTurn = NOUGHTS.equals(nextTurn) ? CROSSES : NOUGHTS;
            } else {
                nextTurn = null;
            }
        }
        return moveAllowed;
    }

    private boolean validMove(int row, int column) {
        boolean validMove = false;
        boolean validRow = 1 <= row && row <= gameBoard.length;
        if (validRow) {
            boolean validColumn = 1 <= column && column <= gameBoard[row - 1].length;
            validMove = validColumn && EMPTY_SPACE.equals(gameBoard[row - 1][column - 1]);
        }
        return validMove;
    }

    private boolean movesRemaining() {
        boolean emptySpaces = false;
        for (int row = 0; row < gameBoard.length; row++) {
            String[] gameBoardRow = gameBoard[row];
            for (int column = 0; column < gameBoardRow.length; column++) {
                emptySpaces |= EMPTY_SPACE.equals(gameBoardRow[column]);
            }
        }
        return emptySpaces;
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
