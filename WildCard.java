// Represents a wild card in the deck (8).

/**
 * Constructs a new wild card.
 * @param suit The suit of the card
 * @param rank The rank (8)
 */
public class WildCard extends Card {
  public WildCard(String suit, int rank) {
    super(suit, rank);
  }

  // Override
  /**
   * Gets the point value of the wild card.
   * @return The point value of the card (50)
   */
  public int GetPoints() {
    if (GetRank() == 8) { // Only 8 is a wild card, is worth 50 points
      return 50;
    }
    return -1; // Not wildcard
  }

  // Override
  /**
   * Checks if wild card can be played.
   * @param currentSuit Current suit in play
   * @param currentRank Current rank in play
   * @return true (wild card can always be played)
   */
  public boolean CanPlay(String currentSuit, int currentRank) {
    return true; // Wild card can always be played
  }
}