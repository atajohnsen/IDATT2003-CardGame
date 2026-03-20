package ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeckTest {

  private Deck deck;

  @BeforeEach
  void setUp() {
    deck = new Deck();
  }

  @Test
  void constructorShouldCreate52UniqueCards() {
    List<PlayingCard> cards = deck.getCards();
    assertEquals(52, cards.size());

    Set<PlayingCard> uniqueCards = new HashSet<>(cards);
    assertEquals(52, uniqueCards.size());
  }

  @Test
  void getCardsShouldReturnCopyAndNotAffectOriginalDeck() {
    List<PlayingCard> cards = deck.getCards();
    cards.clear();
    assertEquals(52, deck.getCards().size());
  }

  @Test
  void dealHandShouldHandOutCorrectNumberOfCards() {
    Hand hand = deck.dealHand(5);
    assertEquals(5, hand.getCards().size());
  }

  @Test
  void dealHandShouldRemoveCardsFromDeck() {
    deck.dealHand(5);
    assertEquals(47, deck.getCards().size());
  }

  @Test
  void dealHandShouldThrowExceptionWhenNIsLessThanOne() {
    assertThrows(IllegalArgumentException.class, () -> deck.dealHand(0));
    assertThrows(IllegalArgumentException.class, () -> deck.dealHand(-3));
  }

  @Test
  void dealHandShouldThrowExceptionWhenNotEnoughCards() {
    deck.dealHand(52);

    assertThrows(IllegalStateException.class, () -> deck.dealHand(1));
  }
}