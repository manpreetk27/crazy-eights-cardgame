# 🃏 Crazy Eights Card Game

A fun and interactive two-player card game implemented in Java. Challenge your opponent to see who can get rid of all their cards first!

## 📋 Table of Contents

- [Overview](#-overview)
- [Game Rules](#-game-rules)
- [Features](#-features)
- [Project Structure](#-project-structure)
- [How to Play](#-how-to-play)
- [Getting Started](#-getting-started)
- [Class Descriptions](#-class-descriptions)

---

## 🎮 Overview

**Crazy Eights** is a classic card game where two players compete to empty their hands first. The game features:
- A standard 52-card deck with multiple card types
- Wild cards (8s) that add strategic gameplay
- A scoring system based on remaining card values
- Support for multiple rounds with replay functionality

---

## 📚 Game Rules

1. **Setup**: Each player receives 7 cards to start the game
2. **Turns**: Players alternate turns, trying to play a card that matches the suit or rank of the top card
3. **Valid Plays**:
   - Play any card that matches the **suit** or **rank** of the top card
   - Play a **Wild Card (8)** at any time to change the current suit
4. **Drawing Cards**: If a player has no playable cards, they draw cards from the deck until they find one they can play
5. **Wild Cards**: When playing an 8, the player chooses the new suit for the next player
6. **Winning**: The first player to get rid of all their cards wins the round
7. **Scoring**: Points are calculated from the losing player's remaining cards:
   - Number cards (2-10): Face value
   - Face cards (J, Q, K): 10 points
   - Aces: 1 point
   - Wild Cards (8): 50 points

---

## ✨ Features

- 🎯 **Two-Player Gameplay**: Intuitive turn-based system
- 🃏 **Wild Card Mechanics**: Strategic use of 8s to change suits
- 📊 **Scoring System**: Track points across multiple rounds
- 🔄 **Card Reshuffling**: Automatic deck reshuffling when needed
- 🎲 **Random Deck Shuffling**: Fair and unpredictable card distribution
- 🎮 **Replay Functionality**: Play multiple rounds without restarting
- 💬 **User-Friendly Interface**: Clear prompts and game state display

---

## 📁 Project Structure

```
CrazyEights/
├── Card.java              # Abstract base class for all card types
├── NormalCard.java        # Standard cards (2-10)
├── ActionCard.java        # Face cards (J, Q, K, A)
├── WildCard.java          # Wild cards (8s)
├── Player.java            # Manages player hand and actions
├── GameSystem.java        # Core game logic and state management
├── Displayer.java         # Handles all user interface and output
├── CrazyEightsMain.java   # Entry point and main game loop
└── README.md              # This file
```

---

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- A terminal or IDE (VS Code, IntelliJ, Eclipse, etc.)

### Compilation

```bash
# Navigate to the project directory
cd /path/to/CrazyEights

# Compile all Java files
javac *.java
```

### Running the Game

```bash
# Run the main game
java CrazyEightsMain
```

---

## 🎮 How to Play

1. **Start the Game**: Run `java CrazyEightsMain`
2. **Enter Names**: When prompted, enter the names for both players
3. **Review Rules**: Read the displayed game rules
4. **Play Your Turn**:
   - View your current hand
   - Select a card by entering its number (1-7 or more if you have drawn cards)
   - If playing a Wild Card (8), choose the new suit (H/D/C/S)
5. **Game Over**: When one player has no cards left, the game ends and scores are displayed
6. **Replay**: Choose to play another round or exit

### Example Game Flow

```
Enter Player 1 name: Alice
Enter Player 2 name: Bob

Welcome Alice and Bob!

[Game Rules Display]

--------------------------------------------
8                GAME STATE                8
--------------------------------------------
| Top Card : 5 of Hearts
| Current Suit : Hearts
--------------------------------------------

Alice's Hand:
1) 2 of Clubs
2) 5 of Spades
3) 8 of Hearts (Wild Card)
4) King of Diamonds
...

Select a card (1-4): 2
```

---

## 🏗️ Class Descriptions

### `Card` (Abstract Base Class)
The foundation of all card types. Provides:
- Card properties: suit and rank
- Abstract methods: `GetPoints()` and `CanPlay()`
- Utility methods: `RankToString()`, `ToString()`

### `NormalCard`
Represents standard cards (ranks 2-10):
- Point value equals the card's rank
- Can be played if it matches suit or rank

### `ActionCard`
Represents face cards (J, Q, K, A):
- Aces worth 1 point
- Face cards worth 10 points
- Can be played if it matches suit or rank

### `WildCard`
Represents 8s (wild cards):
- Worth 50 points (high penalty if left in hand)
- Can always be played
- Allows player to change the current suit

### `Player`
Manages individual player data:
- Maintains hand of cards
- Tracks hand size and points
- Handles card addition and removal
- Determines win condition

### `GameSystem`
Controls all game logic:
- Manages deck, discard pile, and game state
- Handles card drawing and deck reshuffling
- Validates card plays
- Tracks current suit and rank
- Manages turn switching

### `Displayer`
Handles all user interface:
- Displays game state and player hands
- Prompts for player input with validation
- Shows scores and game results
- Displays welcome messages and rules

### `CrazyEightsMain`
Entry point for the game:
- Initializes players and game system
- Manages main game loop
- Handles turn management and game flow
- Supports replay functionality

---

## 💡 Tips for Playing

- **Strategic Play**: Save your Wild Cards (8s) for when you need them most
- **Card Counting**: Try to remember what cards have been played
- **Risk Management**: Drawing too many cards can hurt you if they're high-value cards
- **Suit Control**: When playing a Wild Card, choose a suit that benefits your strategy

---

## 📝 Future Enhancements

Potential improvements for future versions:
- Support for 3+ players
- Difficulty levels (AI opponents)
- Game statistics and history
- GUI interface with graphical cards
- Sound effects and animations
- Save/load game state

---

## 👨‍💻 Author

Created as a Java programming project demonstrating object-oriented design principles.

---

## 📄 License

This project is open source and available for educational purposes.

---

## 🤝 Contributing

Feel free to fork, modify, and improve this game! Suggestions and contributions are welcome.

---

**Enjoy your game! 🎉**
