package home.net;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TicTacToeContoller {

    @Autowired
    private TicTacToeGame tictactoeGame;

    @RequestMapping(value = "/tictactoe", method = RequestMethod.GET)
    @ResponseBody
    public TicTacToeGame newGame() {
        return tictactoeGame;
    }

}
