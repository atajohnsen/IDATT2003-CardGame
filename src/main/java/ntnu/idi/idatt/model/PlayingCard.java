package ntnu.idi.idatt.model;

public class PlayingCard {
  private char suit;
  private int value;

  public PlayingCard(char suit, int value) {
    this.suit = suit;
    this.value = value;
  }

  public char getSuit() {
    return suit;
  }

  public int getValue() {
    return value;
  }

  public void setSuit(char suit) {
    this.suit = suit;
  }

  public void setValue(char value) {
    this.value = value;
  }
}
