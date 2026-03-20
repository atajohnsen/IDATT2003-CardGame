package ntnu.idi.idatt.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Hand {

  private final List<PlayingCard> cards;

  public Hand(List<PlayingCard> cards) {
    this.cards = new ArrayList<>(cards);
  }

  public List<PlayingCard> getCards() {
    return List.copyOf(cards);
  }

  public boolean isFlush() {
    return cards.stream()
        .map(PlayingCard::getSuit)
        .distinct()
        .count() == 1;
  }

  public boolean isStraight() {
    List<Integer> sortedRanks = cards.stream()
        .map(PlayingCard::getFace)
        .sorted()
        .toList();

    return IntStream.range(0, sortedRanks.size() - 1)
        .allMatch(i -> sortedRanks.get(i) + 1 == sortedRanks.get(i + 1));
  }

  public boolean containsCard(PlayingCard playingCard) {
    return cards.stream()
        .anyMatch(card -> card.equals(playingCard));
  }

  public boolean containsCard(char suit, int face) {
    PlayingCard cardToCheck = new PlayingCard(suit, face);
    return cards.stream()
        .anyMatch(card -> card.equals(cardToCheck));
  }

  public int getRankSum() {
    return  cards.stream().mapToInt(PlayingCard::getFace).sum();
  }

  public List<PlayingCard> getCardsBySuit(char suit) {
    return cards.stream().filter(card -> card.getSuit() == suit).toList();
  }

  @Override
  public String toString() {
    return cards.toString();
  }
}
