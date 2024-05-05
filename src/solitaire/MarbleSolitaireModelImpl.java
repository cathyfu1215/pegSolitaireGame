package solitaire;

/**
 * This class represents the operations offered by the marble solitaire model. One object of the
 * model represents one game of marble solitaire.
 */
public class MarbleSolitaireModelImpl implements MarbleSolitaireModel {
  private final int armThickness;
  private int boardWidth;
  private int emptyRow;
  private int emptyCol;
  private String[][] board;
  private int score;
  private int emptyCells;

  /**
   * Construct a new game of Marble Solitaire using the default arm thickness of 3 and the empty
   * cell at the center.
   */
  public MarbleSolitaireModelImpl() {
    this.armThickness = 3;
    setUpBoard(armThickness, armThickness, armThickness);
  }

  /**
   * Construct a new game of Marble Solitaire using user input for the arm thickness and the empty
   * cell at the center.
   *
   * @param armThickness the arm thickness of the board
   * @throws IllegalArgumentException if the arm thickness is invalid
   */
  public MarbleSolitaireModelImpl(int armThickness) {
    if (armThickness <= 0 || armThickness % 2 == 0) {
      throw new IllegalArgumentException("Invalid arm thickness, "
          + "it should be a positive odd number");
    }
    this.armThickness = armThickness;
    setUpBoard(armThickness, armThickness, armThickness);
  }

  /**
   * Construct a new game of Marble Solitaire using two parameters:emtpyRow and emptyColumn.
   * The arm thickness is set to 3 by default.
   * The empty cell is set to the position (emptyRow, emptyCol).
   *
   * @param emptyRow    the row number of the empty cell
   * @param emptyColumn the column number of the empty cell
   * @throws IllegalArgumentException if the empty cell position is invalid
   */
  public MarbleSolitaireModelImpl(int emptyRow, int emptyColumn) {
    this.armThickness = 3;
    if (emptyRow < 0 || emptyColumn < 0
        || emptyRow > 2 * armThickness || emptyColumn > 2 * armThickness //out of the board
        || (emptyRow < armThickness - (armThickness - 1) / 2
        && emptyColumn < armThickness - (armThickness - 1) / 2) //upper left invalid square
        || (emptyRow < armThickness - (armThickness - 1) / 2
        && emptyColumn > armThickness + (armThickness - 1) / 2) //upper right invalid square
        || (emptyRow > armThickness + (armThickness - 1) / 2
        && emptyColumn < armThickness - (armThickness - 1) / 2) //lower left invalid square
        || (emptyRow > armThickness + (armThickness - 1) / 2
        && emptyColumn > armThickness + (armThickness - 1) / 2)) { //lower right invalid square
      throw new IllegalArgumentException("Invalid empty cell position");
    }

    setUpBoard(armThickness, emptyRow, emptyColumn);
  }

  /**
   * Construct a new game of Marble Solitaire using three parameters:armThickness, emptyRow and
   * emptyColumn.
   * The empty cell is set to the position (emptyRow, emptyColumn).
   *
   * @param armThickness the arm thickness of the board
   * @param emptyRow     the row number of the empty cell
   * @param emptyColumn  the column number of the empty cell
   * @throws IllegalArgumentException if the arm thickness is invalid
   * @throws IllegalArgumentException if the empty cell position is invalid
   */
  public MarbleSolitaireModelImpl(int armThickness, int emptyRow, int emptyColumn) {
    if (armThickness <= 0 || armThickness % 2 == 0) {
      throw new IllegalArgumentException("Invalid arm thickness, "
          + "it should be a positive odd number");
    }
    this.armThickness = armThickness;
    if (emptyRow < 0 || emptyColumn < 0
        || emptyRow > 2 * armThickness || emptyColumn > 2 * armThickness //out of the board
        || (emptyRow < armThickness - (armThickness - 1) / 2
        && emptyColumn < armThickness - (armThickness - 1) / 2) //upper left invalid square
        || (emptyRow < armThickness - (armThickness - 1) / 2
        && emptyColumn > armThickness + (armThickness - 1) / 2) //upper right invalid square
        || (emptyRow > armThickness + (armThickness - 1) / 2
        && emptyColumn < armThickness - (armThickness - 1) / 2) //lower left invalid square
        || (emptyRow > armThickness + (armThickness - 1) / 2
        && emptyColumn > armThickness + (armThickness - 1) / 2)) { //lower right invalid square
      throw new IllegalArgumentException("Invalid empty cell position");
    }

    setUpBoard(armThickness, emptyRow, emptyColumn);
  }


  /**
   * A helper function that consumes the arm thickness and the location of the empty cell,
   * and set up the rest of the board.
   * It also sets the score and the number of empty cells.
   * @param armThickness the arm thickness of the board
   * @param emptyRow the row number of the empty cell
   * @param emptyCol the column number of the empty cell
   *
   */
  private void setUpBoard(int armThickness, int emptyRow, int emptyCol) {
    this.boardWidth = 2 * armThickness + 1;
    this.emptyRow = armThickness;
    this.emptyCol = armThickness;
    this.board = new String[boardWidth][boardWidth];
    this.score =
        (int) (4 * (Math.pow(armThickness, 2) - (Math.pow((double) (armThickness - 1) / 2, 2))));
    this.emptyCells = 1;

    //prepare the board
    //fill it with marbles "O" first
    for (int i = 0; i < boardWidth; i++) {
      for (int j = 0; j < boardWidth; j++) {
        board[i][j] = Elements.MARBLE.getDisplayName();
      }
    }
    //then set the empty cell to "_"
    board[emptyRow][emptyCol] = Elements.EMPTY.getDisplayName();

    int invalidSquareWidth = (armThickness - 1) / 2;
    //set the invalid cells to " "
    for (int i = 0; i < boardWidth; i++) {
      for (int j = 0; j < boardWidth; j++) {
        if (i < armThickness - invalidSquareWidth && j < armThickness - invalidSquareWidth) {
          board[i][j] = Elements.INVALID.getDisplayName();
        }
        if (i < armThickness - invalidSquareWidth && j > armThickness + invalidSquareWidth) {
          board[i][j] = Elements.INVALID.getDisplayName();
        }
        if (i > armThickness + invalidSquareWidth && j < armThickness - invalidSquareWidth) {
          board[i][j] = Elements.INVALID.getDisplayName();
        }
        if (i > armThickness + invalidSquareWidth && j > armThickness + invalidSquareWidth) {
          board[i][j] = Elements.INVALID.getDisplayName();
        }
      }
    }
  }


  /**
   * Move a single marble from a given position to another given position. A move is valid only if
   * the from and to positions are valid. Specific implementations may place additional constraints
   * on the validity of a move.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {

    //check if the from and to position are inside the bound, and valid
    if (fromRow < 0 || fromCol < 0
        || fromRow >= boardWidth
        || fromCol >= boardWidth
    ) {
      throw new IllegalArgumentException("Invalid move, the from position is out of the board");
    }
    if (toRow < 0 || toCol < 0
        || toRow >= boardWidth
        || toCol >= boardWidth
    ) {
      throw new IllegalArgumentException("Invalid move, the to position is out of the board");
    }
    if (board[fromRow][fromCol].equals(Elements.INVALID.getDisplayName())) {
      throw new IllegalArgumentException("Invalid move, the from position is invalid, "
          + "no marbles could exist there ");
    }
    if (board[toRow][toCol].equals(Elements.INVALID.getDisplayName())) {
      throw new IllegalArgumentException("Invalid move, the to position is invalid, "
          + "no marbles could go there ");
    }

    //check if the to position has a marble
    if (board[toRow][toCol].equals(Elements.MARBLE.getDisplayName())) {
      throw new IllegalArgumentException("Invalid move, the to position is not empty");
    }
    //check if the from position is empty
    if (board[fromRow][fromCol].equals(Elements.EMPTY.getDisplayName())) {
      throw new IllegalArgumentException("Invalid move, the from position is empty");
    }

    //check if the move is 2 positions away from the start position
    //either horizontally or vertically
    if (Math.abs(fromRow - toRow) != 2) {
      if (Math.abs(fromCol - toCol) != 2) {
        throw new IllegalArgumentException("Invalid move, "
            + "the to position should be 2 positions away from the from position");
      }
    }
    if (Math.abs(fromRow - toRow) == 2) {
      if (fromCol != toCol) {
        throw new IllegalArgumentException("Invalid move, "
            + "we can only move either vertically or horizontally");
      }

    }
    if (Math.abs(fromCol - toCol) == 2) {
      if (fromRow != toRow) {
        throw new IllegalArgumentException("Invalid move, "
            + "we can only move either vertically or horizontally");
      }
    }

    //check if there is a marble between the from and to positions
    if (fromRow == toRow) {
      if (fromCol < toCol) { //move down 2 positions
        if (!board[fromRow][fromCol + 1].equals(Elements.MARBLE.getDisplayName())) {
          throw new IllegalArgumentException("Invalid move, "
              + "there should be a marble between the from and to positions");
        }
      } else { // move up 2 positions
        if (!board[fromRow][fromCol - 1].equals(Elements.MARBLE.getDisplayName())) {
          throw new IllegalArgumentException("Invalid move, "
              + "there should be a marble between the from and to positions");
        }
      }
    } else {
      if (fromRow < toRow) { //move right 2 positions
        if (!board[fromRow + 1][fromCol].equals(Elements.MARBLE.getDisplayName())) {
          throw new IllegalArgumentException("Invalid move, "
              + "there should be a marble between the from and to positions");
        }
      } else { //move right 2 positions
        if (!board[fromRow - 1][fromCol].equals(Elements.MARBLE.getDisplayName())) {
          throw new IllegalArgumentException("Invalid move, "
              + "there should be a marble between the from and to positions");
        }
      }
    }

    //now we are ready to move the marble
    board[fromRow][fromCol] = Elements.EMPTY.getDisplayName();
    board[toRow][toCol] = Elements.MARBLE.getDisplayName();
    //the marble that is jumped over is removed
    board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] = Elements.EMPTY.getDisplayName();
    this.score--;
    this.emptyCells++;


  }

  /**
   * Determine and return if the game is over or not. A game is over if no more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    //check if there is any possible move
    for (int i = 0; i < boardWidth; i++) {
      for (int j = 0; j < boardWidth; j++) {
        if (board[i][j].equals(Elements.MARBLE.getDisplayName())) {
          if (i + 2 < boardWidth) {
            if (board[i + 2][j].equals(Elements.EMPTY.getDisplayName())
                && board[i + 1][j].equals(Elements.MARBLE.getDisplayName())) {
              return false;
            }
          }
          if (i - 2 >= 0) {
            if (board[i - 2][j].equals(Elements.EMPTY.getDisplayName())
                && board[i - 1][j].equals(Elements.MARBLE.getDisplayName())) {
              return false;
            }
          }
          if (j + 2 < boardWidth) {
            if (board[i][j + 2].equals(Elements.EMPTY.getDisplayName())
                && board[i][j + 1].equals(Elements.MARBLE.getDisplayName())) {
              return false;
            }
          }
          if (j - 2 >= 0) {
            if (board[i][j - 2].equals(Elements.EMPTY.getDisplayName())
                && board[i][j - 1].equals(Elements.MARBLE.getDisplayName())) {
              return false;
            }
          }
        }
      }
    }
    return true;
  }

  /**
   * Return a string that represents the current state of the board. The string should have one line
   * per row of the game board. Each slot on the game board is a single character ("O", "_" or " "
   * [space] for a marble, empty and invalid position respectively). Slots in a row should be
   * separated by a space. Each row has no space before the first slot and after the last slot.
   *
   * @return the game state as a string
   */
  @Override
  public String getGameState() {
    //create a String to represent the game state
    StringBuilder gameState = new StringBuilder();
    for (int i = 0; i < boardWidth; i++) {
      for (int j = 0; j < boardWidth; j++) {
        gameState.append(board[i][j]);
        if (j != boardWidth - 1) {
          gameState.append(' ');
        }
      }
      if (i != boardWidth - 1) {
        gameState.append("\n");
      }
    }
    return gameState.toString();
  }

  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board
   */
  @Override
  public int getScore() {
    return this.score;
  }
}
