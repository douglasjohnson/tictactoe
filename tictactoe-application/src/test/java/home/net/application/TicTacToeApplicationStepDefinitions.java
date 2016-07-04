package home.net.application;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.text.IsEmptyString.isEmptyString;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import home.net.application.TicTacToeApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TicTacToeApplication.class, loader = SpringApplicationContextLoader.class)
@WebIntegrationTest
public class TicTacToeApplicationStepDefinitions {

    private static final String TURN_ID = "tictactoe-turn";
    private static final String FIRST_TURN_ID = "tictactoe-first-turn";
    private static final String RESULT_ID = "tictactoe-result";
    private static final String PLAY_AGAIN_ID = "tictactoe-play-again";

    private static final int WAIT_TIME_OUT_IN_SECONDS = 1;

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
        waitForAngular();
    }

    @When("^switch play first$")
    public void switchPlayFirst() {
        WebElement gameBoardFirstTurnElement = webDriver.findElement(By.id(FIRST_TURN_ID));
        gameBoardFirstTurnElement.click();
    }

    @When("^a move is made in row \"([^\"]*)\" column \"([^\"]*)\"$")
    public void makeMove(int row, int column) {
        WebElement gameBoardRowElement = webDriver.findElement(By.id("tictactoe-gameboard-row" + row));
        WebElement gameBoardCellElement = gameBoardRowElement.findElement(By.id("tictactoe-gameboard-column" + column));
        gameBoardCellElement.click();
    }

    @When("^I click play again$")
    public void playAgain() {
        webDriver.findElement(By.id(PLAY_AGAIN_ID)).click();
    }

    @When("^I click \"([^\"]*)\" on the are you sure dialog$")
    public void clickButtonOnDialog(String buttonText) {
        findDialogButton(buttonText).click();
    }

    @Then("^the game board is empty$")
    public void gameBoardEmpty() {
        waitForAngular();
        List<WebElement> gameBoardCellElements = webDriver.findElements(By.className("tictactoe-gameboard-cell"));
        assertThat("Game board should have 9 cells", gameBoardCellElements.size(), is(9));
        for (WebElement gameBoardCellElement : gameBoardCellElements) {
            assertThat(gameBoardCellElement.getText(), isEmptyString());
        }
    }

    @Then("^the game board is:$")
    public void gameBoard(List<List<String>> expectedGameBoard) {
        waitForAngular();
        for (int i = 0; i < expectedGameBoard.size(); i++) {
            List<String> row = expectedGameBoard.get(i);
            for (int j = 0; j < row.size(); j++) {
                String cell = row.get(j);
                WebElement gameBoardRowElement = webDriver.findElement(By.id("tictactoe-gameboard-row" + (i + 1)));
                WebElement gameBoardCellElement = gameBoardRowElement
                        .findElement(By.id("tictactoe-gameboard-column" + (j + 1)));
                assertThat(gameBoardCellElement.getText(), is(cell));
            }
        }
    }

    @Then("^noughts turn$")
    public void noughtsTurn() {
        waitForAngular();
        WebElement gameBoardTurnElement = webDriver.findElement(By.id(TURN_ID));
        assertThat(gameBoardTurnElement.getText(), is("Noughts"));
    }

    @Then("^crosses turn$")
    public void crossedTurn() {
        waitForAngular();
        WebElement gameBoardTurnElement = webDriver.findElement(By.id(TURN_ID));
        assertThat(gameBoardTurnElement.getText(), is("Crosses"));
    }

    @Then("^a move in row \"([^\"]*)\" column \"([^\"]*)\" is not allowed$")
    public void moveNotAllowed(int row, int column) {
        WebElement gameBoardRowElement = webDriver.findElement(By.id("tictactoe-gameboard-row" + row));
        WebElement gameBoardCellElement = gameBoardRowElement.findElement(By.id("tictactoe-gameboard-column" + column));
        String expectedText = gameBoardCellElement.getText();
        gameBoardCellElement.click();
        waitForAngular();
        assertThat(gameBoardCellElement.getText(), is(expectedText));
    }

    @Then("^noughts wins$")
    public void noughtsWins() {
        waitForAngular();
        WebElement gameBoardTurnElement = webDriver.findElement(By.id(RESULT_ID));
        assertThat(gameBoardTurnElement.getText(), is("Winner: Noughts"));
    }

    @Then("^no more moves$")
    public void noMoves() {
        waitForAngular();
        WebElement gameBoardTurnElement = webDriver.findElement(By.id(TURN_ID));
        assertThat(gameBoardTurnElement.getText(), is(""));
    }

    @Then("^game is drawn$")
    public void gameDrawn() {
        waitForAngular();
        WebElement gameBoardTurnElement = webDriver.findElement(By.id(RESULT_ID));
        assertThat(gameBoardTurnElement.getText(), is("Draw"));
    }

    @Then("^switch play first is not allowed$")
    public void switchPlayFirstNotAllowed() {
        WebElement gameBoardFirstTurnElement = webDriver.findElement(By.id(FIRST_TURN_ID));
        assertThat(gameBoardFirstTurnElement.isDisplayed(), is(false));
    }

    @Then("^the play again button is displayed$")
    public void playAgainButtonDisplayed() {
        waitForAngular();
        WebElement webElement = webDriver.findElement(By.id(PLAY_AGAIN_ID));
        assertThat(webElement.isDisplayed(), is(true));
    }

    @Then("^dialog is displayed with text \"([^\"]*)\"$")
    public void dialogIsDisplayedWithText(String text) {
        WebElement dialogElement = webDriver.findElement(By.className("ui-dialog"));
        WebElement dialogMessageElement = dialogElement.findElement(By.id("tictactoe-dialog-confirm"));

        assertThat(dialogMessageElement.getText(), is(text));
        assertThat(dialogElement.getCssValue("display"), is(not("none")));
    }

    @Then("^dialog has \"([^\"]*)\" button$")
    public void dialogHasButton(String buttonText) {
        assertThat(findDialogButton(buttonText), is(notNullValue()));
    }

    @Then("^the are you sure dialog is closed$")
    public void areYouSureDialogIsClosed() {
        WebElement dialogElement = webDriver.findElement(By.className("ui-dialog"));
        assertThat(dialogElement.getCssValue("display"), is("none"));
    }

    private WebElement findDialogButton(String buttonText) {
        WebElement dialogElement = webDriver.findElement(By.className("ui-dialog"));
        List<WebElement> buttons = dialogElement.findElements(By.tagName("button"));

        WebElement expectedButton = null;
        for (WebElement button : buttons) {
            if (buttonText.equals(button.getText())) {
                expectedButton = button;
                break;
            }
        }
        return expectedButton;
    }

    private void waitForAngular() {
        ngWebDriver.waitForAngularRequestsToFinish();
        WebDriverWait wait = new WebDriverWait(webDriver, WAIT_TIME_OUT_IN_SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tictactoe")));
    }
}
