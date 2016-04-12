Feature: TicTacToe application

  Scenario: Game board is empty at start of new game
    When a new game of tic tac toe is started
    Then the game board is empty
