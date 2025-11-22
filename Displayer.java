import java.util.Scanner;

/**
 * Handles all display and user input for the Crazy Eights game.
 */

public class Displayer {
    Scanner input = new Scanner(System.in);

    public Displayer() {
    }

    // ========== HELPER METHODS ==========//
    /**
     * Prompts the user for an integer between min and max (inclusive).
     * Displays error messages for invalid input.
     * @param min Minimum valid value
     * @param max Maximum valid value
     * @return The validated integer input
     */
    private int GetValidIntInput(int min, int max) {
        while (true) {
            int value = input.nextInt();
            input.nextLine(); // consume newline
            if (value >= min && value <= max) {
                return value;
            } else {
                System.out.print("Input out of range. Enter a number between " + min + " and " + max + ": ");
            }
        }
    }

    /**
     * Converts a single-character suit input to a full suit name.
     * @param suit The single-character suit input
     * @return The full suit name
     */
    private String CheckSuit(String suit) {
        char c = suit.charAt(0);
        if (c == ('H')) {
            return "Hearts";
        } else if (c == ('D')) {
            return "Diamonds";
        } else if (c == ('C')) {
            return "Clubs";
        } else {
            return "Spades";
        }
    }

    // ========== PUBLIC METHODS ==========//

    public void DisplayWelcomeMessage() {
        System.out.println("---------------------------------------");
        System.out.println("8       WELCOME TO CRAZY EIGHTS       8");
        System.out.println("---------------------------------------");
    }

    /**
     * Displays a welcome back message when the game is replayed.
     */
    public void DisplayWelcomeBackMessage() {
        System.out.println("---------------------------------------");
        System.out.println("8     WELCOME BACK TO CRAZY EIGHTS    8");
        System.out.println("---------------------------------------");
        System.out.println("\n" + "Thank you for playing again!");
    }

    /**
     * Displays the game rules to the players.
     */
    public void DisplayGameRules() {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("8                           GAME RULES                          8");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("|    1. Each player has 7 cards.                                |");
        System.out.println("|    2. Players will take turns playing a card that matches     |");
        System.out.println("|       the suit or rank of the top card.                       |");
        System.out.println("|    3. Wild cards (8s) can be played at any time and can       |");
        System.out.println("|       change the current suit.                                |");
        System.out.println("|    4. If the player can't play a card, cards will be drawn    |");
        System.out.println("|       until an available cards is found.                      |");
        System.out.println("|    5. First player to get rid of all cards wins the game.     |");
        System.out.println("-----------------------------------------------------------------");
    }

    /**
     * Displays the current game state.
     * @param topCard     The top card on the discard pile
     * @param currentSuit The current suit in play
     */
    public void DisplayGameState(Card topCard, String currentSuit) { // Shows the top card and the current suit
        System.out.println("\n" + "--------------------------------------------");
        System.out.println("8                GAME STATE                8");
        System.out.println("--------------------------------------------");
        System.out.printf("%-15s:%s%-26s|\n", "| Top Card ", " ", topCard.ToString());
        System.out.printf("%-15s:%s%-26s|\n", "| Current Suit ", " ", currentSuit);
        System.out.println("--------------------------------------------");
    }

    /**
     * Displays the player's hand.
     * @param player The player whose hand to display
     */

    public void DisplayPlayerHand(Player player) { // Shows the player's hand
        System.out.println("\n" + player.GetName() + "'s Hand:");
        Card[] hand = player.GetHand();
        for (int i = 0; i < player.GetHandSize(); i++) {
            System.out.println((i + 1) + ". " + hand[i].ToString());
        }
    }

    /**
     * Prompts the player to select a card from their hand.
     * @param player The player making the selection
     * @return The index of the selected card
     */
    public int PromptCardSelection(Player player) { // Prompts the player to select a card from their hand
        System.out.print("Select a card (1-" + player.GetHandSize() + "): ");
        return GetValidIntInput(1, player.GetHandSize()) - 1;
    }

    /**
     * Prompts the player to select a new suit when playing a wild card.
     * @return The chosen suit as a full string
     */
    public String PromptNewSuit() { // Prompts the player to select a new suit for a wild card
        System.out.print("Choose new suit (H/D/C/S): ");
        String suit;
        while (true) {
            suit = input.nextLine().toUpperCase();
            if (suit.length() == 1) {
                char c = suit.charAt(0);
                if (c == 'H' || c == 'D' || c == 'C' || c == 'S') {
                    break;
                }
            }
            System.out.print("Invalid suit! Choose H/D/C/S: ");
        }
        return CheckSuit(suit);
    }

    /**
     * Displays both players' scores.
     * @param player1 First player
     * @param player2 Second player
     */
    public void DisplayScores(Player player1, Player player2) { // Shows the scores of both players
        System.out.println("\n" + "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("\n" + "--------------------------------------------");
        System.out.println("8               CURRENT SCORE              8");
        System.out.println("--------------------------------------------");
        System.out.printf("|%s%-12s:%4d%-24s|\n", " ", player1.GetName(), player1.GetHandPoints(), " points");
        System.out.printf("|%s%-12s:%4d%-24s|\n", " ", player2.GetName(), player2.GetHandPoints(), " points");
        System.out.println("--------------------------------------------");
    }

    /**
     * Displays the final scores and winner.
     * @param winner The winning player
     * @param loser  The losing player
     */
    public void DisplayFinalScores(Player winner, Player loser) { // Shows the final scores of both players
        System.out.println("--------------------------------------------");
        System.out.println("8                FINAL SCORE               8");
        System.out.println("--------------------------------------------");
        System.out.printf("|%s%-12s:%-28s|\n", " ", winner.GetName(), "  0 points (WINNER)");
        System.out.printf("|%s%-12s:%3d%-25s|\n", " ", loser.GetName(), loser.GetHandPoints(), " points");
        System.out.println("--------------------------------------------");
    }

    /**
     * Displays the replay option when game ends
     */
    public void DisplayReplay() {
        System.out.println("\n" + "GAME OVER!");
        System.out.println("Thank you for playing Crazy Eights!" + "\n");
        System.out.println("Would you like to play another round?");
        System.out.println("1. Yes");
        System.out.println("2. Exit");
    }

    /**
     * Announces the winner of the game.
     * @param player The winning player
     */
    public void AnnounceWinner(Player player) {
        System.out.println("\n" + player.GetName() + " is the WINNER!");
    }

    /**
     * Announces that the player is drawing a card.
     */
    public void AnnounceDrawing() {
        System.out.println("No playable cards at the moment. Drawing a card...");
    }

    /**
     * Announces the card drawn by the player.
     * @param card The drawn card
     */
    public void AnnounceCardDrawn(Card card) {
        System.out.println("Card drawn: " + card.ToString());
    }

    /**
     * Announces an invalid card selection.
     * Prompts the player to try again.
     */
    public void AnnounceInvalidSelection() {
        System.out.println("WARNING: Invalid selection! Please try again.");
    }
}