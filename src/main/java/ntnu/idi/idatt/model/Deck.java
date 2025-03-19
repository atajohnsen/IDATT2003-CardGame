package ntnu.idi.idatt.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {

  private final char[] suits = {'S', 'H', 'D', 'C'};
  private List<PlayingCard> cards;
  private final Random random = new Random();

  public Deck() {
    this.cards = setUpDeck();
  }

  private List<PlayingCard> setUpDeck() {
    return IntStream.range(0, 4)
        .boxed()
        .flatMap(suitIndex ->
            IntStream.rangeClosed(1, 13)
                .mapToObj(value -> new PlayingCard(suits[suitIndex], value))
        )
        .collect(Collectors.toList());
  }

  public List<PlayingCard> getCards() {
    return new ArrayList<>(cards);
  }

  public void shuffle() {
    Collections.shuffle(cards);
  }


  public Hand dealHand(int n) {
    if (n < 1 || n > cards.size()) {
      throw new IllegalArgumentException(
          "Number of cards to draw must be between 1 and " + cards.size());
    }

    List<PlayingCard> handCards = random.ints(0, cards.size())
        .distinct()
        .limit(n)
        .mapToObj(cards::get)
        .collect(Collectors.toList());

    cards.removeAll(handCards);

    return new Hand(handCards);
  }
}