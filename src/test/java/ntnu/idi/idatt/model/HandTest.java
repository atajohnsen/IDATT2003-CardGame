package ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HandTest {

  private Hand hand;

  @BeforeEach
  void setup() {
    hand = new Hand(List.of(
        new PlayingCard('S', 1),
        new PlayingCard('H', 1),
        new PlayingCard('C', 1),
        new PlayingCard('D', 1),
        new PlayingCard('H', 2)
    ));
  }

  @Test
  void isFlushReturnsTrueWhenFlush() {
    assertTrue(flushHand().isFlush());
  }

  @Test
  void isFlushReturnsFalseWhenNotSameSuit() {
    assertFalse(hand.isFlush());
  }

  @Test
  void isStraightReturnsTrueWhenStraight() {
    assertTrue(straightHand().isStraight());
  }

  @Test
  void isStraightReturnsFalseWhenNotStraight() {
    assertFalse(hand.isStraight());
  }

  @Test
  void containsCardReturnsTrueIfCardExists() {
    PlayingCard card = new PlayingCard('S', 1);
    assertTrue(hand.containsCard(card));
  }

  @Test
  void containsCardReturnsFalseIfCardDoesNotExist() {
    PlayingCard card = new PlayingCard('S', 12);
    assertFalse(hand.containsCard(card));
  }

  @Test
  void getRankSumReturnsCorrectSum() {
    assertEquals(hand.getRankSum(), 6);
  }

  @Test
  void getCardsBySuitReturnsCardsOfMatchingSuit() {

  }

  Hand flushHand() {
    return new Hand(List.of(
        new PlayingCard('S', 2),
        new PlayingCard('S', 5),
        new PlayingCard('S', 7),
        new PlayingCard('S', 9),
        new PlayingCard('S', 12)
    ));
  }

  Hand straightHand() {
    return new Hand(List.of(
        new PlayingCard('S', 3),
        new PlayingCard('H', 4),
        new PlayingCard('D', 5),
        new PlayingCard('C', 6),
        new PlayingCard('S', 7)
    ));
  }

}