package douglasjohnson.tictactoe.application;

import com.paulhammant.ngwebdriver.NgWebDriver;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.text.IsEmptyString.isEmptyString;
import static org.junit.Assert.assertThat;

public class TicTacToeApplicationDriver {

    private static final String TURN_ID = "tictactoe-turn";
    private static final String FIRST_TURN_ID = "tictactoe-first-turn";
    private static final String RESULT_ID = "tictactoe-result";
    private static final String PLAY_AGAIN_ID = "tictactoe-play-again";

    private static final int WAIT_TIME_OUT_IN_SECONDS = 1;

    private static final String HOST = System.getProperty("host", "localhost");
    private static final String PORT = System.getProperty("port", "8080");

    private PhantomJSDriver webDriver;
    private NgWebDriver ngWebDriver;

    TicTacToeApplicationDriver() {
        webDriver = new PhantomJSDriver();
        ngWebDriver = new NgWebDriver(webDriver);
    }

    @Step
    public void startNewGame() {
        webDriver.get("http://" + HOST + ":" + PORT + "/TicTacToe");
        waitForAngular();
    }


    @Step
    public void switchPlayFirst() {
        WebElement gameBoardFirstTurnElement = webDriver.findElement(By.id(FIRST_TURN_ID));
        gameBoardFirstTurnElement.click();
    }

    @Step
    public void makeMove(int row, int column) {
        WebElement gameBoardRowElement = webDriver.findElement(By.id("tictactoe-gameboard-row" + row));
        WebElement gameBoardCellElement = gameBoardRowElement.findElement(By.id("tictactoe-gameboard-column" + column));
        gameBoardCellElement.click();
    }

    @Step
    public void playAgain() {
        webDriver.findElement(By.id(PLAY_AGAIN_ID)).click();
    }

    @Step
    public void clickButtonOnDialog(String buttonText) {
        findDialogButton(buttonText).click();
    }

    @Step
    public void gameBoardEmpty() {
        waitForAngular();
        List<WebElement> gameBoardCellElements = webDriver.findElements(By.className("tictactoe-gameboard-cell"));
        assertThat("Game board should have 9 cells", gameBoardCellElements.size(), is(9));
        for (WebElement gameBoardCellElement : gameBoardCellElements) {
            assertThat(gameBoardCellElement.getText(), isEmptyString());
        }
    }

    @Step
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

    @Step
    public void noughtsTurn() {
        waitForAngular();
        WebElement gameBoardTurnElement = webDriver.findElement(By.id(TURN_ID));
        assertThat(gameBoardTurnElement.getText(), is("Noughts"));
    }

    @Step
    public void crossesTurn() {
        waitForAngular();
        WebElement gameBoardTurnElement = webDriver.findElement(By.id(TURN_ID));
        assertThat(gameBoardTurnElement.getText(), is("Crosses"));
    }

    @Step
    public void moveNotAllowed(int row, int column) {
        WebElement gameBoardRowElement = webDriver.findElement(By.id("tictactoe-gameboard-row" + row));
        WebElement gameBoardCellElement = gameBoardRowElement.findElement(By.id("tictactoe-gameboard-column" + column));
        String expectedText = gameBoardCellElement.getText();
        gameBoardCellElement.click();
        waitForAngular();
        assertThat(gameBoardCellElement.getText(), is(expectedText));
    }

    @Step
    public void noughtsWins() {
        waitForAngular();
        WebElement gameBoardTurnElement = webDriver.findElement(By.id(RESULT_ID));
        assertThat(gameBoardTurnElement.getText(), is("Winner: Noughts"));
    }

    @Step
    public void noMoves() {
        waitForAngular();
        WebElement gameBoardTurnElement = webDriver.findElement(By.id(TURN_ID));
        assertThat(gameBoardTurnElement.getText(), is(""));
    }

    @Step
    public void gameDrawn() {
        waitForAngular();
        WebElement gameBoardTurnElement = webDriver.findElement(By.id(RESULT_ID));
        assertThat(gameBoardTurnElement.getText(), is("Draw"));
    }

    @Step
    public void switchPlayFirstNotAllowed() {
        WebElement gameBoardFirstTurnElement = webDriver.findElement(By.id(FIRST_TURN_ID));
        assertThat(gameBoardFirstTurnElement.isDisplayed(), is(false));
    }

    @Step
    public void playAgainButtonDisplayed() {
        waitForAngular();
        WebElement webElement = webDriver.findElement(By.id(PLAY_AGAIN_ID));
        assertThat(webElement.isDisplayed(), is(true));
    }

    @Step
    public void dialogIsDisplayedWithText(String text) {
        WebElement dialogElement = webDriver.findElement(By.className("ui-dialog"));
        WebElement dialogMessageElement = dialogElement.findElement(By.id("tictactoe-dialog-confirm"));

        assertThat(dialogMessageElement.getText(), is(text));
        assertThat(dialogElement.getCssValue("display"), is(not("none")));
    }

    @Step
    public void dialogHasButton(String buttonText) {
        assertThat(findDialogButton(buttonText), is(notNullValue()));
    }

    @Step
    public void areYouSureDialogIsClosed() {
        WebElement dialogElement = webDriver.findElement(By.className("ui-dialog"));
        assertThat(dialogElement.getCssValue("display"), is("none"));
    }

    void quit() {
        webDriver.quit();
    }

    private void waitForAngular() {
        ngWebDriver.waitForAngularRequestsToFinish();
        WebDriverWait wait = new WebDriverWait(webDriver, WAIT_TIME_OUT_IN_SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tictactoe")));
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
}
