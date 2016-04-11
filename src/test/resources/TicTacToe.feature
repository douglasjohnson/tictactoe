Feature: TicTacToe

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

  Scenario: Play first move
    Given a new game of tic tac toe is started
     When a move is made in row "1" column "1"
     Then the game board is:
      | O |  |  |
      |   |  |  |
      |   |  |  |
      And crosses turn

  Scenario: Play first move as crosses
    Given a new game of tic tac toe is started
     When crosses set to play first
      And a move is made in row "3" column "3"
     Then the game board is:
      |  |  |   |
      |  |  |   |
      |  |  | X |
      And noughts turn

  Scenario: Play move in filled space is not allowed
    Given a new game of tic tac toe is started
     When a move is made in row "2" column "2"
     Then a move in row "2" column "2" is not allowed
      And the game board is:
      |  |   |  |
      |  | O |  |
      |  |   |  |
      And crosses turn

  Scenario: Three in a row wins
    Given a new game of tic tac toe is started
      And a move is made in row "1" column "1"
      And a move is made in row "2" column "1"
      And a move is made in row "1" column "2"
      And a move is made in row "2" column "2"
     When a move is made in row "1" column "3"
     Then noughts wins
      And the game board is:
      | O | O | O |
      | X | X |   |
      |   |   |   |

  Scenario: Three in a column wins
    Given a new game of tic tac toe is started
      And a move is made in row "1" column "1"
      And a move is made in row "1" column "3"
      And a move is made in row "1" column "2"
      And a move is made in row "2" column "3"
      And a move is made in row "2" column "1"
     When a move is made in row "3" column "3"
     Then crosses wins
      And the game board is:
      | O | O | X |
      | O |   | X |
      |   |   | X |

  Scenario: Three in a top left to borrom right diagonal wins
    Given a new game of tic tac toe is started
      And a move is made in row "1" column "1"
      And a move is made in row "1" column "2"
      And a move is made in row "2" column "2"
      And a move is made in row "1" column "3"
     When a move is made in row "3" column "3"
     Then noughts wins
      And the game board is:
      | O | X | X |
      |   | O |   |
      |   |   | O |

  Scenario: Three in a top right to borrom left diagonal wins
    Given a new game of tic tac toe is started
      And a move is made in row "1" column "3"
      And a move is made in row "1" column "1"
      And a move is made in row "2" column "2"
      And a move is made in row "1" column "2"
     When a move is made in row "3" column "1"
     Then noughts wins
      And the game board is:
      | X | X | O |
      |   | O |   |
      | O |   |   |
