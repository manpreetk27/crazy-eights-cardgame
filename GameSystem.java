/**
 * Controls the core game logic for Crazy Eights.
 * Manages the deck, discard pile, and game state.
 */
public class GameSystem {
  private Card[] deck;
  private int deckSize;
  private Card[] discardPile;
  private int discardSize;
  private Player[] players;
  private int currentPlayer;
  private String currentSuit;
  private boolean gameOver;
  private int currentRank;
  private boolean gameReplay;

  // ========== HELPER METHODS ==========//
  /**
   * Ensures deck is not empty by reshuffling if needed.
   * If deck is empty, calls ReshuffleDeck() to replenish it.
   */
  private void EnsureDeckNotEmpty() {
    if (deckSize == 0) {
      ReshuffleDeck();
      System.out.println("Reshuffling complete. Deck is ready.");
    }
  }

  /**
   * Shuffle the deck of cards
   */
  private void ShuffleDeck() {
    for (int i = 0; i < deckSize; i++) {
      int rand = (int) (Math.random() * deckSize);
      Card temp = deck[i];
      deck[i] = deck[rand];
      deck[rand] = temp;
    }
  }

  // ========== PUBLIC METHODS ==========//

  /**
   * Initializes a new game system with empty deck and players.
   * Sets up initial game state variables.
   */
  public GameSystem() {
    players = new Player[2];
    deck = new Card[52];
    discardPile = new Card[52];
    deckSize = 0;
    discardSize = 0;
    currentPlayer = 0;
    currentSuit = "";
    currentRank = 0;
    gameOver = false;
    gameReplay = false;
  }

  /**
   * Initializes the game, including deck, players, and initial cards
   * @param name1 The name of the first player
   * @param name2 The name of the second player
   */
  public void InitializeGame(String name1, String name2) {
    // Initialize deck with 52 cards
    String[] suit = { "Spades", "Hearts", "Clubs", "Diamonds" };
    for (int i = 0; i < suit.length; i++) {
      for (int j = 1; j <= 13; j++) {
        if (j == 8) {
          deck[deckSize] = new WildCard(suit[i], j);
        } else if (j >= 2 && j <= 10) {
          deck[deckSize] = new NormalCard(suit[i], j);
        } else {
          deck[deckSize] = new ActionCard(suit[i], j);
        }
        deckSize++;
      }
    }
    // Shuffle cards
    ShuffleDeck();

    // Initialize players
    players[0] = new Player(name1);
    players[1] = new Player(name2);

    // Deal 7 cards to players
    for (int i = 0; i < 7; i++) {
      players[0].AddCard(DrawCard());
      players[1].AddCard(DrawCard());
    }

    // Start a game
    Card start = DrawCard();
    while (start instanceof WildCard) { // Not start with Wild Card
      deck[deckSize++] = start; // Put back into the deck
      start = DrawCard(); // Draw a new card
    }
    discardPile[discardSize++] = start;
    currentSuit = start.GetSuit();
    currentRank = start.GetRank();
  }

  /**
   * Draws a card from the deck.
   * Ensures deck is not empty before drawing.
   * @return The drawn card, or null if deck is empty
   */
  public Card DrawCard() {
    EnsureDeckNotEmpty();
    if (deckSize == 0) {
      return null; // No cards left
    }
    deckSize = deckSize - 1;
    return deck[deckSize];
  }

  /**
   * Reshuffles the discard pile into the deck
   * Keeps the top card on the discard pile
   * Shuffles the rest of the cards in the discard pile and add them to the deck
   */
  public void ReshuffleDeck() {
    if (discardSize <= 1) {
      return;
    }

    // Keep the top card
    Card topCard = discardPile[discardSize - 1];

    // Move the rest to the deck
    for (int i = 0; i < discardSize - 1; i++) {
      deck[deckSize++] = discardPile[i];
    }

    // Shuffle deck
    ShuffleDeck();

    // Keep top card on discard pile
    discardPile[0] = topCard;
    discardSize = 1;
  }

  /**
   * Returns the array of players.
   * @return The array of players
   */
  public Player[] GetPlayers() {
    return players;
  }

  /**
   * Returns the current player.
   * @return The current player
   */
  public Player GetCurrentPlayer() {
    return players[currentPlayer]; // Return current player
  }

  /**
   * Returns the points of the specified player.
   * @param playerIndex Index of player (0 or 1)
   * @return Player's current points, or 0 if invalid index
   */
  public int GetPlayerPoints(int playerIndex) {
    if (playerIndex < 0 || playerIndex >= players.length) {
      return 0;
    }
    return players[playerIndex].GetHandPoints();
  }

  /**
   * Returns the top card of the discard pile
   * @return The top card of the discard pile, or null if discard pile is empty
   */
  public Card GetTopCard() {
    if (discardSize == 0) { // Return null if discard pile is empty
      return null;
    }
    // return the most recent played card
    return discardPile[discardSize - 1]; // Track last card's index
  }

  /**
   * Returns the current suit in play.
   * @return The current valid suit
   */
  public String GetCurrentSuit() {
    return currentSuit;
  }

  /**
   * Returns whether the game is over.
   * @return true if game is over, false otherwise
   */
  public boolean IsGameOver() {
    return gameOver;
  }

  /**
   * Sets the game over state.
   * @param over New game over state
   */
  public void SetGameOver(boolean over) {
    gameOver = over;
  }

  /**
   * Returns whether the game is in replay mode.
   * @return true if game is in replay mode, false otherwise
   */
  public boolean IsReplay() {
    return gameReplay;
  }

  /**
   * Sets the game replay state
   * @param replay New game replay state
   */
  public void SetReplay(boolean replay) {
    gameReplay = replay;
  }

  /**
   * Switches the current player to the other player
   */
  public void SwitchTurn() {
    currentPlayer = (currentPlayer + 1) % 2;
  }

  /**
   * Checks if the player has any playable cards.
   * @param player The player to check
   * @return true if player has at least one playable card, false otherwise
   */
  public boolean HasPlayableCards(Player player) {
    Card[] hand = player.GetHand();
    int handSize = player.GetHandSize();
    for (int i = 0; i < handSize; i++) {
      if (hand[i].CanPlay(currentSuit, currentRank)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if the selected card is a valid play
   * @param player    The player playing the card
   * @param cardIndex Index of card to play
   * @return true if card can be played, false otherwise
   */
  public boolean IsValidPlay(Player player, int cardIndex) {
    // Make sure index is within player's hand
    if (cardIndex < 0 || cardIndex >= player.GetHandSize()) {
      return false; // Invalid if index is negative and if out of player hand
    }
    // Get the card that player wants to play
    Card card = player.GetHand()[cardIndex]; // Get specific card at that position
    return card.CanPlay(currentSuit, currentRank);
  }

  /**
   * Plays the selected card from the player's hand.
   * Updates game state based on played card.
   * @param player         The player playing the card
   * @param cardIndex      Index of card to play
   * @param nextSuitIfWild New suit if wild card is played
   */
  public void PlayCard(Player player, int cardIndex, String nextSuitIfWild) {
    if (!IsValidPlay(player, cardIndex)) {
      return; // Invalid play
    }
    Card playedCard = player.PlayCard(cardIndex);
    discardPile[discardSize] = playedCard;
    discardSize = discardSize + 1;

    if (playedCard instanceof WildCard) {
      currentSuit = nextSuitIfWild;
    } else {
      currentSuit = playedCard.GetSuit();
    }
    currentRank = playedCard.GetRank();

    if (player.HasWon()) {
      gameOver = true;
    }
  }
}