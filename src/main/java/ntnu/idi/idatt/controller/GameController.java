package ntnu.idi.idatt.controller;

import java.util.List;
import ntnu.idi.idatt.model.Deck;
import ntnu.idi.idatt.model.Hand;
import ntnu.idi.idatt.model.PlayingCard;

public class GameController {

  private Deck deck;
  private Hand currentHand;

  public GameController() {
    resetGame();
  }

  public void drawHand(int handSize) {
    currentHand = deck.dealHand(handSize);
  }

  public List<PlayingCard> getCurrentHandCards() {
    if (currentHand == null) {
      return List.of();
    }
    return currentHand.getCards();
  }

  public String checkHand() {
    if (currentHand == null) {
      return "Draw cards first!";
    }

    boolean isFlush = currentHand.isFlush();
    boolean isStraight = currentHand.isStraight();

    if (isFlush && isStraight) {
      return "Straight Flush!";
    } else if (isFlush) {
      return "Flush!";
    } else if (isStraight) {
      return "Straight!";
    }

    return "No combos :(";
  }

  public String checkQueenOfSpades() {
    if (currentHand == null) {
      return "";
    }

    if (currentHand.containsQueenOfSpades()) {
      return "AYUP!";
    }

    return "Nope :(";
  }

  public String checkSumOfFaces() {
    if (currentHand == null) {
      return "";
    }

    return String.valueOf(currentHand.getRankSum());
  }

  public List<PlayingCard> checkCardOfHearts() {
    return currentHand.getCardsBySuit('H');
  }

  public String getSuitImage(char suit) {
    return switch (suit) {
      case 'S' -> "Spade.png";
      case 'H' -> "Hearts.png";
      case 'D' -> "Diamonds.png";
      case 'C' -> "Clover.png";
      default -> "unknown.png";
    };
  }

  public void resetGame() {
    deck = new Deck();
    currentHand = null;
  }
}
