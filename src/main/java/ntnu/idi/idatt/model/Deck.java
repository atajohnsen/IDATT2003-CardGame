package ntnu.idi.idatt.model;

import java.util.List;
import javax.smartcardio.Card;

public class Deck {
  private final char[] suit = { 'S', 'H', 'D', 'C' };
  private List<PlayingCard> cards;
  public Deck() {
  }
}
