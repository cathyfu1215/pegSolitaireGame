import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import solitaire.MarbleSolitaireModelImpl;

/**
 * This class tests the methods of the MarbleSolitaireModelImpl class.
 */
public class MarbleSolitaireModelImplTest {

  private MarbleSolitaireModelImpl boardRegular;
  private MarbleSolitaireModelImpl boardCustomWidth;
  private MarbleSolitaireModelImpl boardCustomEmptyCell;
  private MarbleSolitaireModelImpl boardCustomEmptyCellAndArmThickness;
  private MarbleSolitaireModelImpl superSmallBoard;

  /**
   * Set up a board with 4 constructors that takes 0,1,2,3 arguments.
   */
  @Before
  public void setUp() {

    //set up a board with a constructor that takes no arguments

    this.boardRegular = new MarbleSolitaireModelImpl();
    this.boardCustomWidth = new MarbleSolitaireModelImpl(5);
    this.boardCustomEmptyCell = new MarbleSolitaireModelImpl(2, 3);
    this.boardCustomEmptyCellAndArmThickness = new MarbleSolitaireModelImpl(5, 4, 5);
    this.superSmallBoard = new MarbleSolitaireModelImpl(1);

  }

  /**
   * Test that the constructor that takes only one parameter
   * will throw an exception when a negative arm
   * thickness is passed in.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeArmThickness() {
    new MarbleSolitaireModelImpl(-3);
  }

  /**
   * Test that the constructor that takes only one parameter
   * will throw an exception when a 0 arm
   * thickness is passed in.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorZeroArmThickness() {
    new MarbleSolitaireModelImpl(0);
  }

  /**
   * Test that the constructor that takes only one parameter
   * will throw an exception when an even arm
   * thickness is passed in.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorEvenArmThickness() {
    new MarbleSolitaireModelImpl(4);
  }

  /**
   * Test that the constructor that takes two parameters
   * will throw an exception when a pair of out of bound
   * empty cell coordinates(column is out of bound) are passed in.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidEmptyCellColumnTooLarge() {
    new MarbleSolitaireModelImpl(2, 7);
  }

  /**
   * Test that the constructor that takes two parameters
   * will throw an exception when a pair of out of bound
   * empty cell coordinates(row is out of bound) are passed in.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidEmptyCellRowTooLarge() {
    new MarbleSolitaireModelImpl(7, 2);
  }

  /**
   * Test that the constructor that takes two parameters
   * will throw an exception when a pair of out of bound
   * empty cell coordinates(row is negative) are passed in.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidEmptyCellRowNegative() {
    new MarbleSolitaireModelImpl(-1, 3);
  }

  /**
   * Test that the constructor that takes two parameters
   * will throw an exception when a pair of out of bound
   * empty cell coordinates(column is negative) are passed in.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidEmptyCellColumnNegative() {
    new MarbleSolitaireModelImpl(3, -2);
  }

  /**
   * Test that the constructor that takes two parameters
   * will throw an exception when the empty cell is placed
   * in an invalid position(upper left).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidEmptyCellUpperLeft() {
    new MarbleSolitaireModelImpl(1, 1);
  }

  /**
   * Test that the constructor that takes two parameters
   * will throw an exception when the empty cell is placed
   * in an invalid position(upper right).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidEmptyCellUpperRight() {
    new MarbleSolitaireModelImpl(1, 5);
  }

  /**
   * Test that the constructor that takes two parameters
   * will throw an exception when the empty cell is placed
   * in an invalid position(lower left).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidEmptyCellLowerLeft() {
    new MarbleSolitaireModelImpl(5, 1);
  }

  /**
   * Test that the constructor that takes two parameters
   * will throw an exception when the empty cell is placed
   * in an invalid position(lower right).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidEmptyCellLowerRight() {
    new MarbleSolitaireModelImpl(5, 5);
  }

  /**
   * Test that the constructor that takes three parameters will
   * throw an exception when a negative arm
   * thickness is passed in.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreeParametersNegativeArmThickness() {
    new MarbleSolitaireModelImpl(-3, 3, 3);
  }

  /**
   * Test that the constructor that takes three parameters will
   * throw an exception when a 0 arm
   * thickness is passed in.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreeParametersZeroArmThickness() {
    new MarbleSolitaireModelImpl(0, 3, 3);
  }

  /**
   * Test that the constructor that takes three parameters will
   * throw an exception when a positive even arm
   * thickness is passed in.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreeParametersEvenArmThickness() {
    new MarbleSolitaireModelImpl(4, 3, 3);
  }

  /**
   * Test that the constructor that takes three parameters will throw
   * an exception when the passed in empty cell coordinates are out
   * of bound(row is negative).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreeParametersInvalidEmptyCellRowNegative() {
    new MarbleSolitaireModelImpl(3, -1, 3);
  }

  /**
   * Test that the constructor that takes three parameters will throw
   * an exception when the passed in empty cell coordinates are out
   * of bound(column is negative).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreeParametersInvalidEmptyCellColumnNegative() {
    new MarbleSolitaireModelImpl(3, 3, -1);
  }

  /**
   * Test that the constructor that takes three parameters will throw
   * an exception when the passed in empty cell coordinates are out
   * of bound(row is too large).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreeParametersInvalidEmptyCellRowTooLarge() {
    new MarbleSolitaireModelImpl(3, 7, 3);
  }

  /**
   * Test that the constructor that takes three parameters will throw
   * an exception when the passed in empty cell coordinates are out
   * of bound(column is too large).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreeParametersInvalidEmptyCellColumnTooLarge() {
    new MarbleSolitaireModelImpl(3, 3, 7);
  }

  /**
   * Test that the constructor that takes three parameters will throw
   * an exception when the empty cell is placed in an invalid position(upper left).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreeParametersInvalidEmptyCellUpperLeft() {
    new MarbleSolitaireModelImpl(3, 1, 1);
  }

  /**
   * Test that the constructor that takes three parameters will throw
   * an exception when the empty cell is placed in an invalid position(upper right).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreeParametersInvalidEmptyCellUpperRight() {
    new MarbleSolitaireModelImpl(3, 1, 5);
  }

  /**
   * Test that the constructor that takes three parameters will throw
   * an exception when the empty cell is placed in an invalid position(lower left).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreeParametersInvalidEmptyCellLowerLeft() {
    new MarbleSolitaireModelImpl(5, 8, 2);
  }

  /**
   * Test that the constructor that takes three parameters will throw
   * an exception when the empty cell is placed in an invalid position(lower right).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreeParametersInvalidEmptyCellLowerRight() {
    new MarbleSolitaireModelImpl(5, 8, 8);
  }


  /**
   * Test that the move method will throw an exception
   * when the from position is out of bound(row too high).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromPositionOutOfBoundRowTooHigh() {
    this.boardRegular.move(7, 3, 3, 3);
  }

  /**
   * Test that the move method will throw an exception
   * when the from position is out of bound(column too high).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromPositionOutOfBoundColumnTooHigh() {
    this.boardRegular.move(3, 7, 3, 3);
  }

  /**
   * Test that the move method will throw an exception
   * when the from position is out of bound(row too low).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromPositionOutOfBoundRowTooLow() {
    this.boardRegular.move(-1, 3, 3, 3);
  }

  /**
   * Test that the move method will throw an exception
   * when the from position is out of bound(column too low).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromPositionOutOfBoundColumnTooLow() {
    this.boardRegular.move(3, -1, 3, 3);
  }

  /**
   * Test that the move method will throw an exception
   * when the to position is out of bound(row too high).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveToPositionOutOfBoundRowTooHigh() {
    this.boardRegular.move(3, 3, 7, 3);
  }

  /**
   * Test that the move method will throw an exception
   * when the to position is out of bound(column too high).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveToPositionOutOfBoundColumnTooHigh() {
    this.boardRegular.move(3, 3, 3, 7);
  }

  /**
   * Test that the move method will throw an exception when the from position is invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromPositionInvalid() {
    this.boardRegular.move(1, 1, 3, 3);
  }

  /**
   * Test that the move method will throw an exception when the to position is invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveToPositionInvalid() {
    this.boardRegular.move(3, 3, 5, 5);
  }

  /**
   * Test that the move method will throw an exception when the from position is empty.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromPositionEmpty() {
    this.boardRegular.move(3, 1, 3, 3);
    this.boardRegular.move(3, 4, 3, 2);
    this.boardRegular.move(3, 3, 3, 1);

  }

  /**
   * Test that the move method will throw an exception when the to position is not empty.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveToPositionNotEmpty() {
    this.boardRegular.move(3, 0, 3, 2);
  }

  /**
   * Test that the move method will throw an exception when the to position is 3 positions
   * away vertically.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDiagonally3PositionsAway() {
    this.boardCustomWidth.move(5, 3, 5, 5);
    this.boardCustomWidth.move(8, 4, 5, 4);
  }

  /**
   * Test that the move method will throw an exception when the to position is 2 positions
   * away diagonally.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDiagonally2PositionsAway() {
    this.boardRegular.move(2, 3, 0, 5);
  }

  /**
   * Test that the move method will throw an exception when the to position is 2
   * positions vertically away and 1 position horizontally away.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testVertically2PositionsAwayHorizontally1PositionAway() {
    this.boardRegular.move(0, 3, 0, 5);
    this.boardRegular.move(2, 3, 0, 4);
  }

  /**
   * Test that the move method will throw an exception when the to position is 2
   * positions horizontally away and 1 position vertically away.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testHorizontally2PositionsAwayVertically1PositionAway() {
    this.boardRegular.move(2, 3, 1, 5);
  }

  /**
   * Test that the move method will throw an exception when there is no marble to jump over
   * to the right.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNoMarbleToJumpOverToTheRight() {
    this.boardRegular.move(3, 1, 3, 3);
    this.boardRegular.move(3, 0, 3, 2);
  }

  /**
   * Test that the move method will throw an exception when there is no marble to jump over
   * to the left.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNoMarbleToJumpOverToTheLeft() {
    this.boardRegular.move(3, 5, 3, 3);
    this.boardRegular.move(3, 6, 3, 4);
  }

  /**
   * Test that the move method will throw an exception when there is no marble to jump over
   * up.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNoMarbleToJumpOverUp() {
    this.boardRegular.move(5, 3, 3, 3);
    this.boardRegular.move(6, 3, 4, 3);
  }

  /**
   * Test that the move method will throw an exception when there is no marble to jump over
   * down.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNoMarbleToJumpOverDown() {
    this.boardRegular.move(1, 3, 3, 3);
    this.boardRegular.move(0, 3, 2, 3);
  }


  /**
   * Test a valid move to the right.
   */
  @Test
  public void moveToTheRight() {
    this.boardRegular.move(3, 1, 3, 3);
    //the board should have 31 marbles after the move
    assertEquals(31, this.boardRegular.getScore());

    //split the string representation of the board into lines
    String line3 = this.boardRegular.getGameState().split("\n")[3];
    //the board should have a marble at (3,3) after the move
    assertEquals('O', line3.charAt(6));
    //the board should have an empty cell at (3,1) after the move
    assertEquals('_', line3.charAt(2));
    //the board should have an empty cell at (3,2) after the move
    assertEquals('_', line3.charAt(4));

  }

  /**
   * Test a valid move to the left.
   */
  @Test
  public void moveToTheLeft() {
    this.boardRegular.move(3, 5, 3, 3);
    //the board should have 31 marbles after the move
    assertEquals(31, this.boardRegular.getScore());

    //split the string representation of the board into lines
    String line3 = this.boardRegular.getGameState().split("\n")[3];
    //the board should have a marble at (3,3) after the move
    assertEquals('O', line3.charAt(6));
    //the board should have an empty cell at (3,5) after the move
    assertEquals('_', line3.charAt(10));
    //the board should have an empty cell at (3,4) after the move
    assertEquals('_', line3.charAt(8));

  }

  /**
   * Test a valid move to the up.
   */
  @Test
  public void testMoveUp() {
    this.boardRegular.move(5, 3, 3, 3);
    //the board should have 31 marbles after the move
    assertEquals(31, this.boardRegular.getScore());

    //split the string representation of the board into lines
    String line3 = this.boardRegular.getGameState().split("\n")[3];
    String line4 = this.boardRegular.getGameState().split("\n")[4];
    String line5 = this.boardRegular.getGameState().split("\n")[5];
    //the board should have a marble at (3,3) after the move
    assertEquals('O', line3.charAt(6));
    //the board should have an empty cell at (5,3) after the move
    assertEquals('_', line5.charAt(6));
    //the board should have an empty cell at (4,3) after the move
    assertEquals('_', line4.charAt(6));

  }

  /**
   * Test a valid move down.
   */
  @Test
  public void testMoveDown() {
    this.boardRegular.move(1, 3, 3, 3);
    //the board should have 31 marbles after the move
    assertEquals(31, this.boardRegular.getScore());

    //split the string representation of the board into lines
    String line3 = this.boardRegular.getGameState().split("\n")[3];
    String line2 = this.boardRegular.getGameState().split("\n")[2];
    String line1 = this.boardRegular.getGameState().split("\n")[1];
    //the board should have a marble at (3,3) after the move
    assertEquals('O', line3.charAt(6));
    //the board should have an empty cell at (1,3) after the move
    assertEquals('_', line1.charAt(6));
    //the board should have an empty cell at (2,3) after the move
    assertEquals('_', line2.charAt(6));

  }

  /**
   * Test the isGameOver method.
   */
  @Test
  public void isGameOver() {

    //use the board with the arm thickness of 1
    // it is Game Over at the beginning
    assertTrue(this.superSmallBoard.isGameOver());

    //use the board with the regular arm thickness
    // it is not Game Over at the beginning
    assertFalse(this.boardRegular.isGameOver());

    //move the marbles in the regular board to make it Game Over
    this.boardRegular.move(1, 3, 3, 3);
    this.boardRegular.move(2, 1, 2, 3);
    this.boardRegular.move(0, 2, 2, 2);
    this.boardRegular.move(4, 1, 2, 1);
    this.boardRegular.move(3, 3, 3, 1);
    this.boardRegular.move(4, 3, 4, 1);
    this.boardRegular.move(3, 0, 3, 2);
    this.boardRegular.move(4, 0, 4, 2);
    this.boardRegular.move(3, 2, 1, 2);
    assertFalse(this.boardRegular.isGameOver());
    this.boardRegular.move(2, 0, 2, 2);
    this.boardRegular.move(4, 5, 4, 3);
    assertEquals(21, this.boardRegular.getScore());
    this.boardRegular.move(6, 4, 4, 4);
    this.boardRegular.move(6, 2, 6, 4);
    this.boardRegular.move(5, 2, 5, 4);
    assertFalse(this.boardRegular.isGameOver());
    assertEquals(18, this.boardRegular.getScore());
    this.boardRegular.move(4, 3, 4, 5);
    this.boardRegular.move(2, 2, 0, 2);
    this.boardRegular.move(6, 4, 4, 4);
    this.boardRegular.move(4, 5, 4, 3);
    this.boardRegular.move(4, 2, 4, 4);
    assertFalse(this.boardRegular.isGameOver());
    assertEquals(13, this.boardRegular.getScore());
    this.boardRegular.move(3, 4, 5, 4);
    this.boardRegular.move(1, 4, 3, 4);
    this.boardRegular.move(2, 6, 2, 4);
    this.boardRegular.move(2, 4, 4, 4);
    assertFalse(this.boardRegular.isGameOver());
    assertEquals(9, this.boardRegular.getScore());
    this.boardRegular.move(4, 6, 2, 6);
    this.boardRegular.move(5, 4, 3, 4);
    this.boardRegular.move(3, 5, 3, 3);
    assertFalse(this.boardRegular.isGameOver());
    assertEquals(6, this.boardRegular.getScore());
    this.boardRegular.move(2, 3, 4, 3);
    assertTrue(this.boardRegular.isGameOver());
    assertEquals(5, this.boardRegular.getScore());


  }

  /**
   * Test the getGameState method.
   */
  @Test
  public void getGameState() {
    //create the string representation of an empty board that has arm thickness of 3
    String emptyBoardArm3 =
        """
                O O O   \s
                O O O   \s
            O O O O O O O
            O O O _ O O O
            O O O O O O O
                O O O   \s
                O O O   \s""";
    assertEquals(emptyBoardArm3, this.boardRegular.getGameState());

    //move one marble and see the changes in the string representation
    this.boardRegular.move(3, 1, 3, 3);
    String emptyBoardArm3AfterOneMove =
        """
                O O O   \s
                O O O   \s
            O O O O O O O
            O _ _ O O O O
            O O O O O O O
                O O O   \s
                O O O   \s""";
    assertEquals(emptyBoardArm3AfterOneMove, this.boardRegular.getGameState());


    //create the string representation of an empty board that has arm thickness of 5
    String emptyBoardArm5 =
        """
                  O O O O O     \s
                  O O O O O     \s
                  O O O O O     \s
            O O O O O O O O O O O
            O O O O O O O O O O O
            O O O O O _ O O O O O
            O O O O O O O O O O O
            O O O O O O O O O O O
                  O O O O O     \s
                  O O O O O     \s
                  O O O O O     \s""";
    assertEquals(emptyBoardArm5, this.boardCustomWidth.getGameState());

    //create the string representation of an empty board that has arm thickness of 3
    //and an empty cell at (2,3)  (row, column)
    String emptyBoardArm3Cell23 =
        """
                O O O   \s
                O O O   \s
            O O O _ O O O
            O O O O O O O
            O O O O O O O
                O O O   \s
                O O O   \s""";
    assertEquals(emptyBoardArm3Cell23, this.boardCustomEmptyCell.getGameState());

    //create the string representation of an empty board that has arm thickness of 5
    //and an empty cell at (4,5)  (row, column)
    String emptyBoardArm5Cell45 =
        """
                  O O O O O     \s
                  O O O O O     \s
                  O O O O O     \s
            O O O O O O O O O O O
            O O O O O _ O O O O O
            O O O O O O O O O O O
            O O O O O O O O O O O
            O O O O O O O O O O O
                  O O O O O     \s
                  O O O O O     \s
                  O O O O O     \s""";
    assertEquals(emptyBoardArm5Cell45, this.boardCustomEmptyCellAndArmThickness.getGameState());
  }

  /**
   * Test the getScore method.
   */
  @Test
  public void getScore() {
    //the regular board should have 32 marbles at the beginning
    assertEquals(this.boardRegular.getScore(), 32);

    //the custom width(5) board should have 84 marbles at the beginning
    assertEquals(this.boardCustomWidth.getScore(), 84);

    //the custom empty cell(2,3) board should have 32 marbles at the beginning
    assertEquals(this.boardCustomEmptyCell.getScore(), 32);

    // the custom empty cell(4,5) and arm thickness(5) board should have
    // 84 marbles at the beginning
    assertEquals(this.boardCustomEmptyCellAndArmThickness.getScore(), 84);

    //for the supersmall board with arm thickness of 1, it should have 4 marble at the beginning
    assertEquals(this.superSmallBoard.getScore(), 4);
  }
}