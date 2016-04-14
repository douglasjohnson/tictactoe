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
    @Value("${local.server.port}")
    private int port;

    @Before
    public void setUp() {
        webDriver = new PhantomJSDriver();
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

    @When("^a new game of tic tac toe is started$")
    public void startNewGame() {
        webDriver.get("http://localhost:" + port + "/TicTacToe");
        NgWebDriver ngWebDriver = new NgWebDriver(webDriver);
        ngWebDriver.waitForAngularRequestsToFinish();
    }

    @When("^crosses set to play first$")
    public void crossesPlaysFirst() {
        WebElement gameBoardFirstTurnElement = webDriver.findElement(By.id("tictactoe-first-turn"));
        gameBoardFirstTurnElement.click();
    }

    @Then("^the game board is empty$")
    public void gameBoardEmpty() {
        List<WebElement> gameBoardCellElements = webDriver.findElements(By.className("tictactoe-gameboard-cell"));
        assertThat("Game board should have 9 cells", gameBoardCellElements.size(), is(9));
        for (WebElement gameBoardCellElement : gameBoardCellElements) {
            assertThat(gameBoardCellElement.getText(), isEmptyString());
        }
    }

    @Then("^noughts turn$")
    public void noughtsTurn() {
        WebElement gameBoardTurnElement = webDriver.findElement(By.id("tictactoe-turn"));
        assertThat(gameBoardTurnElement.getText(), is("Noughts"));
    }

    @Then("^crosses turn$")
    public void crossedTurn() {
        WebElement gameBoardTurnElement = webDriver.findElement(By.id("tictactoe-turn"));
        assertThat(gameBoardTurnElement.getText(), is("Crosses"));
    }

}
