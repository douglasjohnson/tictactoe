package douglasjohnson.tictactoe.application;

import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TicTacToeApplication.class, loader = SpringApplicationContextLoader.class)
@WebIntegrationTest
public class TicTacToeApplicationStepDefinitions {

    @Steps
    TicTacToeApplicationDriver driver;

    @After
    public void tearDown() {
        driver.quit();
    }

    @When("^a new game of tic tac toe is started$")
    public void startNewGame() {
        driver.startNewGame();
    }

    @When("^switch play first$")
    public void switchPlayFirst() {
        driver.switchPlayFirst();
    }

    @When("^a move is made in row \"([^\"]*)\" column \"([^\"]*)\"$")
    public void makeMove(int row, int column) {
        driver.makeMove(row, column);
    }

    @When("^I click play again$")
    public void playAgain() {
        driver.playAgain();
    }

    @When("^I click \"([^\"]*)\" on the are you sure dialog$")
    public void clickButtonOnDialog(String buttonText) {
        driver.clickButtonOnDialog(buttonText);
    }

    @Then("^the game board is empty$")
    public void gameBoardEmpty() {
        driver.gameBoardEmpty();
    }

    @Then("^the game board is:$")
    public void gameBoard(List<List<String>> expectedGameBoard) {
        driver.gameBoard(expectedGameBoard);
    }

    @Then("^noughts turn$")
    public void noughtsTurn() {
        driver.noughtsTurn();
    }

    @Then("^crosses turn$")
    public void crossesTurn() {
        driver.crossesTurn();
    }

    @Then("^a move in row \"([^\"]*)\" column \"([^\"]*)\" is not allowed$")
    public void moveNotAllowed(int row, int column) {
        driver.moveNotAllowed(row, column);
    }

    @Then("^noughts wins$")
    public void noughtsWins() {
        driver.noughtsWins();
    }

    @Then("^no more moves$")
    public void noMoves() {
        driver.noMoves();
    }

    @Then("^game is drawn$")
    public void gameDrawn() {
        driver.gameDrawn();
    }

    @Then("^switch play first is not allowed$")
    public void switchPlayFirstNotAllowed() {
        driver.switchPlayFirstNotAllowed();
    }

    @Then("^the play again button is displayed$")
    public void playAgainButtonDisplayed() {
        driver.playAgainButtonDisplayed();
    }

    @Then("^dialog is displayed with text \"([^\"]*)\"$")
    public void dialogIsDisplayedWithText(String text) {
        driver.dialogIsDisplayedWithText(text);
    }

    @Then("^dialog has \"([^\"]*)\" button$")
    public void dialogHasButton(String buttonText) {
        driver.dialogHasButton(buttonText);
    }

    @Then("^the are you sure dialog is closed$")
    public void areYouSureDialogIsClosed() {
        driver.areYouSureDialogIsClosed();
    }

}
