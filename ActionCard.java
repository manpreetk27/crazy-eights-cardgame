// Represents an action card in the deck (Ace, Jack, Queen, King).

/**
 * Constructs a new action card.
 * @param suit The suit of the card
 * @param rank The rank (1, 11, 12, 13)
 */
public class ActionCard extends Card {
  public ActionCard(String suit, int rank) {
    super(suit, rank);
  }

  // Override
  /**
   * Gets the point value of the action card.
   * @return The point value of the card (1 for Ace, 10 for face cards)
   */
  public int GetPoints() { // Return the card point with ace as 1 and face cards as 10
    if (GetRank() == 1) { // Ace
      return 1;
    } else if (GetRank() >= 11 && GetRank() <= 13) { // Jack, Queen, King
      return 10;
    }
    return -1;
  }

  // Override
  /**
   * Checks if action card can be played.
   * @param currentSuit Current suit in play
   * @param currentRank Current rank in play
   * @return true if card matches current suit or rank
   */
  public boolean CanPlay(String currentSuit, int currentRank) { // Returns true if the card matches the current suit or rank
    return GetSuit().equals(currentSuit) || GetRank() == currentRank;
  }
}