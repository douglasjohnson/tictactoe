Feature: TicTacToe application

  Scenario: Game board is empty at start of new game
    When a new game of tic tac toe is started
    Then the game board is empty

  Scenario: First move is noughts by default
    When a new game of tic tac toe is started
    Then noughts turn

  Scenario: First move can be set to crosses
    Given a new game of tic tac toe is started
     When switch play first
     Then crosses turn

  Scenario: First move can be set to crosses and back to noughts again
    Given a new game of tic tac toe is started
      And switch play first
     When switch play first
     Then noughts turn

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
     When switch play first
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
      And no more moves
      And the game board is:
      | O | O | O |
      | X | X |   |
      |   |   |   |

  Scenario: Draw when no moves remaining and no three in a row
    Given a new game of tic tac toe is started
      And a move is made in row "2" column "2"
      And a move is made in row "1" column "1"
      And a move is made in row "1" column "2"
      And a move is made in row "3" column "2"
      And a move is made in row "2" column "1"
      And a move is made in row "2" column "3"
      And a move is made in row "3" column "1"
      And a move is made in row "1" column "3"
     When a move is made in row "3" column "3"
     Then game is drawn
      And the game board is:
      | X | O | X |
      | O | O | X |
      | O | X | O |

  Scenario: Can not set first move turn after first move has been played
    Given a new game of tic tac toe is started
      And a move is made in row "1" column "1"
     Then switch play first is not allowed
      And crosses turn

  Scenario: Play move after game won is not allowed
    Given a new game of tic tac toe is started
      And a move is made in row "1" column "1"
      And a move is made in row "2" column "1"
      And a move is made in row "1" column "2"
      And a move is made in row "2" column "2"
      And a move is made in row "1" column "3"
     Then a move in row "3" column "1" is not allowed
      And the game board is:
      | O | O | O |
      | X | X |   |
      |   |   |   |

  Scenario: Play again button is displayed
    When a new game of tic tac toe is started
    Then the play again button is displayed

  Scenario: Play again displays are you sure dialog when game is in progress
    Given a new game of tic tac toe is started
     When I click play again
     Then dialog is displayed with text "Are you sure you want to abandon this game?"
      And dialog has "Yes" button
      And dialog has "No" button

  Scenario: Play again are you sure dialog yes response restarts the game
    Given a new game of tic tac toe is started
      And a move is made in row "1" column "1"
      And I click play again
     When I click "Yes" on the are you sure dialog
     Then the game board is empty
      And the are you sure dialog is closed
      And noughts turn

  Scenario: Play again are you sure dialog no response does not affect game
    Given a new game of tic tac toe is started
      And a move is made in row "2" column "2"
      And I click play again
     When I click "No" on the are you sure dialog
     Then the game board is:
      |  |   |  |
      |  | O |  |
      |  |   |  |
      And the are you sure dialog is closed
      And crosses turn

  Scenario: Play again returns to the new game page with no dialog when games is won
    Given a new game of tic tac toe is started
      And a move is made in row "1" column "1"
      And a move is made in row "2" column "1"
      And a move is made in row "1" column "2"
      And a move is made in row "2" column "2"
      And a move is made in row "1" column "3"
     When I click play again
     Then the game board is empty
      And noughts turn

  Scenario: Play again restart the game with no dialog when games is drawn
    Given a new game of tic tac toe is started
      And a move is made in row "2" column "2"
      And a move is made in row "1" column "1"
      And a move is made in row "1" column "2"
      And a move is made in row "3" column "2"
      And a move is made in row "2" column "1"
      And a move is made in row "2" column "3"
      And a move is made in row "3" column "1"
      And a move is made in row "1" column "3"
      And a move is made in row "3" column "3"
     When I click play again
     Then the game board is empty
      And noughts turn
