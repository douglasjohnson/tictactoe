Feature: TicTacToe application

  Scenario: Game board is empty at start of new game
    When a new game of tic tac toe is started
    Then the game board is empty

  Scenario: First move is noughts by default
    When a new game of tic tac toe is started
    Then noughts turn

  Scenario: First move can be set to crosses
    Given a new game of tic tac toe is started
     When crosses set to play first
     Then crosses turn