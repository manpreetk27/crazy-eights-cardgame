// Represents the standard cards in the deck (2-10)

/**
 * Constructs a new normal card.
 * @param suit The suit of the card
 * @param rank The rank (2-10)
 */
public class NormalCard extends Card {
    public NormalCard(String suit, int rank) { // Initializing the NormalCard class
        super(suit, rank);
    }

    // Override
    /**
     * Gets the point value of the normal card.
     * @return The rank value of the card
     */
    public int GetPoints() { // Return the card's rank as its point value
        return GetRank();
    }

    // Override
    /**
     * Checks if normal card can be played.
     * @param currentSuit Current suit in play
     * @param currentRank Current rank in play
     * @return true if card matches current suit or rank
     */
    public boolean CanPlay(String currentSuit, int currentRank) { // Returns true if the card matches the current suit or rank
        return GetSuit().equals(currentSuit) || GetRank() == currentRank;
    }
}