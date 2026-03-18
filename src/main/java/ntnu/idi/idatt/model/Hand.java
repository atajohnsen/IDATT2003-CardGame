package ntnu.idi.idatt.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Hand {

  private final List<PlayingCard> cards;

  public Hand(List<PlayingCard> cards) {
    this.cards = cards;
  }

  public List<PlayingCard> getCards() {
    return cards;
  }

  public boolean isFlush() {
    return cards.stream()
        .map(PlayingCard::getSuit)
        .distinct()
        .count() == 1;
  }

  public boolean isStraight() {
    List<Integer> sortedRanks = cards.stream()
        .map(PlayingCard::getValue)
        .sorted()
        .toList();

    return IntStream.range(0, sortedRanks.size() - 1)
        .allMatch(i -> sortedRanks.get(i) + 1 == sortedRanks.get(i + 1));
  }

  public boolean containsQueenOfSpades() {
    return cards.stream()
        .anyMatch(card -> card.getSuit() == 'S' && card.getValue() == 12);
  }

  public Map<Character, Long> getSuitCounts() {
    return cards.stream()
        .collect(Collectors.groupingBy(PlayingCard::getSuit, Collectors.counting()));
  }

  public Map<Integer, Long> getRankCounts() {
    return cards.stream()
        .collect(Collectors.groupingBy(PlayingCard::getValue, Collectors.counting()));
  }

  @Override
  public String toString() {
    return cards.toString();
  }
}
