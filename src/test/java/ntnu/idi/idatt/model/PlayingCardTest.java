package ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayingCardTest {

  @Test
  void constructorThrowsExceptionWhenInvalidSuit() {
    assertThrows(IllegalArgumentException.class, () -> new PlayingCard('U', 10));
  }

  @Test
  void constructorThrowsExceptionWhenInvalidFace() {
    assertThrows(IllegalArgumentException.class, () -> new PlayingCard('H', 100));
  }

  @Test
  void constructorCreatesValidCard() {
    PlayingCard card = new PlayingCard('H', 6);
    assertEquals(card.getFace(), 6);
    assertEquals(card.getSuit(), 'H');
  }

  @Test
  void getAsStringReturnsStringInProperFormat() {
    PlayingCard card = new PlayingCard('H', 6);
    assertEquals("H6", card.getAsString());
  }

  @Test
  void equalsReturnsTrueWhenMatchingFaceAndSuit() {
    PlayingCard card1 = new PlayingCard('H', 6);
    PlayingCard card2 = new PlayingCard('H', 6);
    assertTrue(card1.equals(card2));
  }

  @Test
  void equalsReturnsFalseWhenMatchingFaceButNotSuit() {
    PlayingCard card1 = new PlayingCard('H', 6);
    PlayingCard card2 = new PlayingCard('S', 6);
    assertFalse(card1.equals(card2));
  }

  @Test
  void equalsReturnsFalseWhenMatchingSuitButNotFace() {
    PlayingCard card1 = new PlayingCard('H', 6);
    PlayingCard card2 = new PlayingCard('H', 2);
    assertFalse(card1.equals(card2));
  }

  @Test
  void equalsReturnsFalseWhenSuitAndFaceDiffer() {
    PlayingCard card1 = new PlayingCard('H', 6);
    PlayingCard card2 = new PlayingCard('S', 3);
    assertFalse(card1.equals(card2));
  }

}