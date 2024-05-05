package solitaire;

/**
 * Enumerated type representing the possible states of a cell in the game board.
 * Each cell can be invalid, has marble in it, or empty.
 */
public enum Elements {
  INVALID(" "),
  MARBLE("O"),
  EMPTY("_");
  private final String displayName;

  Elements(String displayName) {
    this.displayName = displayName;
  }

  /**
   * Retrieves the string representation of the element.
   *
   * @return the string representation of the element
   */
  public String getDisplayName() {
    return displayName;
  }
}
