package douglasjohnson.tictactoe.engine;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TicTacToeStepDefinitions {

    private TicTacToe ticTacToe;

    @When("^a new game of tic tac toe is started$")
    public void startNewGame() {
        ticTacToe = new TicTacToe();
    }

    @When("^crosses set to play first$")
    public void crossesPlaysFirst() {
        assertThat(ticTacToe.firstTurn("X"), is(true));
    }

    @When("^a move is made in row \"([^\"]*)\" column \"([^\"]*)\"$")
    public void makeMove(int row, int column) {
        assertThat(ticTacToe.move(row, column), is(true));
    }

    @When("^a move in row \"([^\"]*)\" column \"([^\"]*)\" is not allowed$")
    public void moveNotAllowed(int row, int column) {
        assertThat(ticTacToe.move(row, column), is(false));
    }

    @Then("^the game board is empty$")
    public void gameBoardEmpty() {
        String[][] gameBoard = ticTacToe.gameBoard();
        for (int i = 0; i < gameBoard.length; i++) {
            String[] row = gameBoard[i];
            for (int j = 0; j < row.length; j++) {
                String cell = row[j];
                assertThat(cell, is(""));
            }
        }
    }

    @Then("^noughts turn$")
    public void noughtsTurn() {
        assertThat(ticTacToe.turn(), is("O"));
    }

    @Then("^crosses turn$")
    public void crossesTurn() {
        assertThat(ticTacToe.turn(), is("X"));
    }

    @Then("^the game board is:$")
    public void gameBoard(List<List<String>> expectedGameBoard) {
        for (int i = 0; i < expectedGameBoard.size(); i++) {
            List<String> row = expectedGameBoard.get(i);
            for (int j = 0; j < row.size(); j++) {
                String cell = row.get(j);
                assertThat(ticTacToe.gameBoard()[i][j], is(cell));
            }
        }
    }

    @Then("^noughts wins$")
    public void noughtsWins() {
        assertThat(ticTacToe.winner(), is("O"));
    }

    @Then("^crosses wins$")
    public void crossesWins() {
        assertThat(ticTacToe.winner(), is("X"));
    }

    @Then("^game is drawn$")
    public void gameDrawn() {
        assertThat(ticTacToe.winner(), is(nullValue()));
    }

    @Then("^no more moves$")
    public void noMoves() {
        assertThat(ticTacToe.turn(), is(nullValue()));
    }

    @Then("^noughts set to play first is not allowed$")
    public void noughtsPlaysFirstNotAllowed() {
        assertThat(ticTacToe.firstTurn("O"), is(false));
    }
}
