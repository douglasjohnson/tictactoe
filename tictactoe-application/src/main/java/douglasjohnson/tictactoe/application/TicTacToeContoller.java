package douglasjohnson.tictactoe.application;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("request")
public class TicTacToeContoller {

    @Autowired
    private TicTacToeGame tictactoeGame;

    @RequestMapping(value = "/tictactoe", method = RequestMethod.GET)
    @ResponseBody
    public TicTacToeGame tictactoe() {
        return tictactoeGame;
    }

    @RequestMapping(value = "/switchFirstTurn", method = RequestMethod.GET)
    @ResponseBody
    public TicTacToeGame switchFirstTurn() {
        tictactoeGame.switchFirstTurn();
        return tictactoeGame;
    }

    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    @ResponseBody
    public TicTacToeGame reset() {
        tictactoeGame.reset();
        return tictactoeGame;
    }

    @RequestMapping(value = "/move", method = RequestMethod.POST)
    @ResponseBody
    public TicTacToeGame move(@RequestBody Map<String, Integer> map) {
        tictactoeGame.move(map.get("row"), map.get("column"));
        return tictactoeGame;
    }

}
