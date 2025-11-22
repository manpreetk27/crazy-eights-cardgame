// Abstract class representing a card in the deck. 
// Provides common functionality for all card types.

public abstract class Card {
  private String suit;
  private int rank;

  /**
   * Constructs a new card with the given suit and rank.
   * @param suit The suit of the card
   * @param rank The rank of the card
   */
  public Card(String suit, int rank) {
    this.suit = suit;
    this.rank = rank;
  }

  /**
   * Gets the suit of the card.
   * @return The suit of the card
   */
  public String GetSuit() {
    return suit;
  }

  /**
   * Gets the rank of the card.
   * @return The rank of the card
   */
  public int GetRank() {
    return rank;
  }

  /**
   * Converts the rank of the card to a string representation.
   * @return The string representation of the rank
   */
  public String RankToString() {
    String[] name = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
    return name[rank - 1]; // subtract 1 to get correct index
  }

  /**
   * Returns a string representation of the card.
   * @return The string representation of the card (eg: "Jack of Spades")
   */
  public String ToString() {
    if (RankToString().equals("8")) {
      return RankToString() + " of " + suit + " (Wild Card)";
    } else {
      return RankToString() + " of " + suit;
    }
  }

  // abstract methods to be implemented by subclasses
  public abstract int GetPoints();
  public abstract boolean CanPlay(String currentsuit, int currentRank);
}