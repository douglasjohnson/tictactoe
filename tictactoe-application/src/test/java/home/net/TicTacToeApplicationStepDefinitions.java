package home.net;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.text.IsEmptyString.isEmptyString;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.paulhammant.ngwebdriver.NgWebDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TicTacToeApplication.class, loader = SpringApplicationContextLoader.class)
@WebIntegrationTest
public class TicTacToeApplicationStepDefinitions {

    private PhantomJSDriver webDriver;
    private NgWebDriver ngWebDriver;

    @Value("${local.server.port}")
    private int port;

    @Before
    public void setUp() {
        webDriver = new PhantomJSDriver();
        ngWebDriver = new NgWebDriver(webDriver);
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

    @When("^a new game of tic tac toe is started$")
    public void startNewGame() {
        webDriver.get("http://localhost:" + port + "/TicTacToe");
        ngWebDriver.waitForAngularRequestsToFinish();
    }

    @When("^switch play first$")
    public void switchPlayFirst() {
        WebElement gameBoardFirstTurnElement = webDriver.findElement(By.id("tictactoe-first-turn"));
        gameBoardFirstTurnElement.click();
    }

    @When("^a move is made in row \"([^\"]*)\" column \"([^\"]*)\"$")
    public void makeMove(int row, int column) {
        WebElement gameBoardRowElement = webDriver.findElement(By.id("tictactoe-gameboard-row" + row));
        WebElement gameBoardCellElement = gameBoardRowElement.findElement(By.id("tictactoe-gameboard-column" + column));
        gameBoardCellElement.click();
    }

    @Then("^the game board is empty$")
    public void gameBoardEmpty() {
        ngWebDriver.waitForAngularRequestsToFinish();
        List<WebElement> gameBoardCellElements = webDriver.findElements(By.className("tictactoe-gameboard-cell"));
        assertThat("Game board should have 9 cells", gameBoardCellElements.size(), is(9));
        for (WebElement gameBoardCellElement : gameBoardCellElements) {
            assertThat(gameBoardCellElement.getText(), isEmptyString());
        }
    }

    @Then("^the game board is:$")
    public void gameBoard(List<List<String>> expectedGameBoard) {
        ngWebDriver.waitForAngularRequestsToFinish();
        for (int i = 0; i < expectedGameBoard.size(); i++) {
            List<String> row = expectedGameBoard.get(i);
            for (int j = 0; j < row.size(); j++) {
                String cell = row.get(j);
                WebElement gameBoardRowElement = webDriver.findElement(By.id("tictactoe-gameboard-row" + (i + 1)));
                WebElement gameBoardCellElement = gameBoardRowElement.findElement(By.id("tictactoe-gameboard-column" + (j + 1)));
                assertThat(gameBoardCellElement.getText(), is(cell));
            }
        }
    }

    @Then("^noughts turn$")
    public void noughtsTurn() {
        ngWebDriver.waitForAngularRequestsToFinish();
        WebElement gameBoardTurnElement = webDriver.findElement(By.id("tictactoe-turn"));
        assertThat(gameBoardTurnElement.getText(), is("Noughts"));
    }

    @Then("^crosses turn$")
    public void crossedTurn() {
        ngWebDriver.waitForAngularRequestsToFinish();
        WebElement gameBoardTurnElement = webDriver.findElement(By.id("tictactoe-turn"));
        assertThat(gameBoardTurnElement.getText(), is("Crosses"));
    }

    @When("^a move in row \"([^\"]*)\" column \"([^\"]*)\" is not allowed$")
    public void moveNotAllowed(int row, int column) {
        WebElement gameBoardRowElement = webDriver.findElement(By.id("tictactoe-gameboard-row" + row));
        WebElement gameBoardCellElement = gameBoardRowElement.findElement(By.id("tictactoe-gameboard-column" + column));
        String expectedText = gameBoardCellElement.getText();
        gameBoardCellElement.click();
        ngWebDriver.waitForAngularRequestsToFinish();
        assertThat(gameBoardCellElement.getText(), is(expectedText));
    }
}
