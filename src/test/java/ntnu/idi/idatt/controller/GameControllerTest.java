package ntnu.idi.idatt.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import ntnu.idi.idatt.model.Hand;
import ntnu.idi.idatt.model.PlayingCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameControllerTest {

  private GameController controller;

  @BeforeEach
  void setUp() {
    controller = new GameController();
  }

  @Test
  void getCurrentHandCardShouldReturnEmptyListBeforeDrawingAHand() {
    assertTrue(controller.getCurrentHandCards().isEmpty());
  }

  @Test
  void checkHandShouldReturnDrawCardsFirstBeforeDrawingAHand() {
    assertEquals("Draw cards first!", controller.checkHand());
  }

  @Test
  void checkQueenOfSpades_shouldReturnEmptyStringBeforeDrawingAHand() {
    assertEquals("", controller.checkQueenOfSpades());
  }

  @Test
  void checkSumOfFacesShouldReturnEmptyStringBeforeDrawingHand() {
    assertEquals("", controller.checkSumOfFaces());
  }

  @Test
  void drawHandShouldDrawSpecifiedNumberOfCards() {
    controller.drawHand(5);

    List<PlayingCard> cards = controller.getCurrentHandCards();

    assertEquals(5, cards.size());
  }

  @Test
  void resetGameShouldClearCurrentHand() {
    controller.drawHand(5);
    assertFalse(controller.getCurrentHandCards().isEmpty());

    controller.resetGame();

    assertTrue(controller.getCurrentHandCards().isEmpty());
    assertEquals("Draw cards first!", controller.checkHand());
  }

  @Test
  void checkHandShouldReturnStraightFlushWhenHandIsStraightAndFlush() {
    Hand hand = new Hand(List.of(
        new PlayingCard('H', 1),
        new PlayingCard('H', 2),
        new PlayingCard('H', 3),
        new PlayingCard('H', 4),
        new PlayingCard('H', 5)
    ));

    controller.setCurrentHand(hand);

    assertEquals("Straight Flush!", controller.checkHand());
  }

  @Test
  void checkHandShouldReturnFlushWhenHandIsOnlyFlush() {
    Hand hand = new Hand(List.of(
        new PlayingCard('S', 2),
        new PlayingCard('S', 5),
        new PlayingCard('S', 7),
        new PlayingCard('S', 9),
        new PlayingCard('S', 12)
    ));

    controller.setCurrentHand(hand);

    assertEquals("Flush!", controller.checkHand());
  }

  @Test
  void checkHandShouldReturnStraightWhenHandIsOnlyStraight() {
    Hand hand = new Hand(List.of(
        new PlayingCard('S', 3),
        new PlayingCard('H', 4),
        new PlayingCard('D', 5),
        new PlayingCard('C', 6),
        new PlayingCard('S', 7)
    ));

    controller.setCurrentHand(hand);

    assertEquals("Straight!", controller.checkHand());
  }

  @Test
  void checkHandShouldReturnNoCombosWhenNoCombosArePresent() {
    Hand hand = new Hand(List.of(
        new PlayingCard('S', 2),
        new PlayingCard('H', 5),
        new PlayingCard('D', 7),
        new PlayingCard('C', 9),
        new PlayingCard('S', 12)
    ));

    controller.setCurrentHand(hand);

    assertEquals("No combos :(", controller.checkHand());
  }

  @Test
  void checkQueenOfSpadesShouldReturnAyupWhenQueenOfSpadesExists() {
    Hand hand = new Hand(List.of(
        new PlayingCard('S', 12),
        new PlayingCard('H', 3),
        new PlayingCard('D', 5),
        new PlayingCard('C', 7),
        new PlayingCard('H', 9)
    ));

    controller.setCurrentHand(hand);

    assertEquals("AYUP!", controller.checkQueenOfSpades());
  }

  @Test
  void checkQueenOfSpadesShouldReturnNopeWhenQueenOfSpadesDoesNotExist() {
    Hand hand = new Hand(List.of(
        new PlayingCard('S', 11),
        new PlayingCard('H', 3),
        new PlayingCard('D', 5),
        new PlayingCard('C', 7),
        new PlayingCard('H', 9)
    ));

    controller.setCurrentHand(hand);

    assertEquals("Nope :(", controller.checkQueenOfSpades());
  }

  @Test
  void checkSumOfFacesShouldReturnCorrectSum() {
    Hand hand = new Hand(List.of(
        new PlayingCard('S', 1),
        new PlayingCard('H', 2),
        new PlayingCard('D', 3),
        new PlayingCard('C', 4),
        new PlayingCard('H', 5)
    ));

    controller.setCurrentHand(hand);

    assertEquals("15", controller.checkSumOfFaces());
  }

  @Test
  void checkCardOfHeartsShouldReturnOnlyHearts() {
    Hand hand = new Hand(List.of(
        new PlayingCard('H', 2),
        new PlayingCard('S', 3),
        new PlayingCard('H', 10),
        new PlayingCard('C', 7),
        new PlayingCard('D', 9)
    ));

    controller.setCurrentHand(hand);

    List<PlayingCard> hearts = controller.checkCardOfHearts();

    assertEquals(2, hearts.size());
    assertTrue(hearts.stream().allMatch(card -> card.getSuit() == 'H'));
  }

  @Test
  void getSuitImagePathShouldReturnCorrectPathForSpades() {
    assertEquals("/Spade.png", controller.getSuitImagePath('S'));
  }

  @Test
  void getSuitImagePathShouldReturnCorrectPathForHearts() {
    assertEquals("/Hearts.png", controller.getSuitImagePath('H'));
  }

  @Test
  void getSuitImagePathShouldReturnCorrectPathForDiamonds() {
    assertEquals("/Diamonds.png", controller.getSuitImagePath('D'));
  }

  @Test
  void getSuitImagePathShouldReturnCorrectPathForClubs() {
    assertEquals("/Clover.png", controller.getSuitImagePath('C'));
  }

  @Test
  void getSuitImagePathShouldReturnUnknownForInvalidSuit() {
    assertEquals("unknown.png", controller.getSuitImagePath('X'));
  }
}