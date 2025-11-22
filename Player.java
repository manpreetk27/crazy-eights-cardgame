/**
 * Represents a player in the Crazy Eights game.
 * Manages the player's hand of cards and game actions.
 */
public class Player {
    final int MAX_HAND_SIZE = 52; // Maximum number of cards a player can have
    private String name;
    private Card[] hand;
    private int handSize;

    /**
     * Initializes a new player with the given name.
     * @param name The player's name
     */
    public Player(String name) {
        this.name = name;
        hand = new Card[MAX_HAND_SIZE];
        handSize = 0;
    }

    /**
     * Adds a card to the player's hand.
     * @param card The card to add
     */
    public void AddCard(Card card) {
        if (handSize < hand.length) {
            hand[handSize++] = card;
        }
    }

    /**
     * Plays a card from the player's hand.
     * @param index The index of the card to play
     * @return The played card, or null if index is invalid
     */
    public Card PlayCard(int index) { // Play a card from player's hand
        if (index < 0 || index >= handSize) { // Invalid index or index out of range
            return null;
        }
        Card played = hand[index];

        for (int i = index; i < handSize - 1; i++) {
            hand[i] = hand[i + 1]; // Shifting cards to fill the gap
        }
        hand[handSize - 1] = null; // Removing the previous card
        handSize--;
        return played;

    }

    /**
     * Checks if the player has won by having no cards.
     * @return true if player has no cards, false otherwise
     */
    public boolean HasWon() {
        return handSize == 0;
    }

    /**
     * Creates a copy of the player's hand.
     * @return A new array containing the player's hand
     */
    public Card[] GetHand() {
        Card[] currHand = new Card[handSize]; // Create new array to store the current hand
        for (int i = 0; i < handSize; i++) {
            currHand[i] = hand[i]; // Copy cards from original hand to the new array
        }
        return currHand;
    }

    /**
     * Gets the number of cards in player's hand.
     * @return Number of cards in hand
     */
    public int GetHandSize() {
        return handSize;
    }

    /**
     * Get the player's name.
     * @return The player's name.
     */
    public String GetName() { // Return player's name
        return name;
    }

    /**
     * Calculates total points of cards in hand.
     * @return Total points value
     */
    public int GetHandPoints() {
        int points = 0;
        for (int i = 0; i < handSize; i++) {
            points += hand[i].GetPoints();
        }
        return points;
    }
}