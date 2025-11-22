/**
 * Main class for the Crazy Eights game.
 * Initializes the game, manages turns, and displays game state.
 */
import java.util.Scanner;

public class CrazyEightsMain {
  public static void main(String[] args) {

    String name1 = "";
    String name2 = "";

    GameSystem game = new GameSystem();
    Displayer disp = new Displayer();
    Scanner input = new Scanner(System.in);
    disp.DisplayWelcomeMessage();
    System.out.print("\n" + "Enter Player 1 name: ");
    name1 = input.nextLine();
    System.out.print("Enter Player 2 name: ");
    name2 = input.nextLine();
    System.out.println("\n" + "Welcome " + name1 + " and " + name2 + "!" + "\n");
    disp.DisplayGameRules();

    boolean gameReplay = false;
    while (!gameReplay) {
      game.InitializeGame(name1, name2); // Initialize the game (deck, shuffle cards, deal cards, player, start game)

      Player p1 = game.GetPlayers()[0];
      Player p2 = game.GetPlayers()[1];

      while (!game.IsGameOver()) { // When the game is not over
        Player currPlayer = game.GetCurrentPlayer();
        boolean turnEnd = false;

        while (!turnEnd && !game.IsGameOver()) { // When turn is not valid and game is not over
          disp.DisplayScores(p1, p2); // Display the current scores of players
          disp.DisplayGameState(game.GetTopCard(), game.GetCurrentSuit()); // Display the top card and current suit
          disp.DisplayPlayerHand(currPlayer); // Display current player's hand

          if (game.HasPlayableCards(currPlayer)) { // If player has playable cards
            int index = disp.PromptCardSelection(currPlayer); // Prompt player to select a card from hand
            boolean isValid = game.IsValidPlay(currPlayer, index);

            if (isValid) { // if the selected card is valid
              Card selected = currPlayer.GetHand()[index]; // Get the selected card

              String nextSuitIfWild = "";
              if (selected instanceof WildCard) { // If the selected card is a wild card
                nextSuitIfWild = disp.PromptNewSuit(); // Prompt player to select the new suit
              }

              game.PlayCard(currPlayer, index, nextSuitIfWild); // Play the selected card
              turnEnd = true;
            }

            else {
              disp.AnnounceInvalidSelection(); // Announce invalid selection
            }
          } else {
            disp.AnnounceDrawing(); // Announce drawing card
            Card drawn = game.DrawCard(); // Draw card from deck
            currPlayer.AddCard(drawn); // Add drawn card to player's hand
            disp.AnnounceCardDrawn(drawn); // Announce card drawn
          }
        }

        if (!game.IsGameOver()) {
          game.SwitchTurn(); // Switch player if game not over
        }
      }

      // Game end
      Player winner;
      Player loser;

      if (p1.HasWon()) { // If player 1 won
        winner = p1;
        loser = p2;
      } else { // Player 2 won
        winner = p2;
        loser = p1;
      }

      disp.AnnounceWinner(winner); // Announce winner
      disp.DisplayFinalScores(winner, loser); // Display final scores

      disp.DisplayReplay(); // Ask user if they want to replay
      System.out.print("Enter your choice: ");
      int choice = input.nextInt();
      input.nextLine();

      if (choice == 1) { // If user choose to replay
        System.out.println("\n" + "Replaying game..." + "\n");
        disp.DisplayWelcomeBackMessage(); // Display welcome back message
        game = new GameSystem(); // Create new game system
        gameReplay = false; // Set game replay to false to continue the game loop
      }

      else if (choice == 2) { // If user chooses to exit
        System.out.println("\n" + "Exiting game..." + "\n");
        break; // exit the game loop
      }

      else { // If user chooses invalid option
        System.out.println("Invalid choice. Pick 1 or 2");
      }
    }
    input.close();
  }
}