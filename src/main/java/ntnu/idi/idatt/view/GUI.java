package ntnu.idi.idatt.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ntnu.idi.idatt.model.Deck;
import ntnu.idi.idatt.model.Hand;
import ntnu.idi.idatt.model.PlayingCard;

import java.util.List;

public class GUI extends Application {

  private Deck deck;
  private Hand currentHand;
  private final HBox cardDisplay = new HBox(10);
  private final Label resultLabel = new Label("");
  private final Label queenOfSpadesResultLabel = new Label("");

  @Override
  public void start(Stage primaryStage) {
    deck = new Deck();
    deck.shuffle();

    Button dealHandButton = new Button("Trekk en hånd");
    Button checkHandButton = new Button("Sjekk hånd");

    dealHandButton.setOnAction(e -> drawHand());
    checkHandButton.setOnAction(e -> checkHand());

    VBox root = new VBox(15, dealHandButton, checkHandButton, cardDisplay, resultLabel,
        queenOfSpadesResultLabel);
    root.setAlignment(Pos.CENTER);
    root.setStyle("-fx-padding: 20;");

    Scene scene = new Scene(root, 600, 400);
    primaryStage.setTitle("Kortspill - JavaFX");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void drawHand() {
    currentHand = deck.dealHand(5);
    cardDisplay.getChildren().clear();
    resultLabel.setText("");

    List<PlayingCard> cards = currentHand.getCards();
    for (PlayingCard card : cards) {
      cardDisplay.getChildren().add(createCardView(card));
    }
  }

  private void checkHand() {
    if (currentHand == null) {
      resultLabel.setText("Trekk en hånd først!");
      return;
    }

    boolean isFlush = currentHand.isFlush();
    boolean isStraight = currentHand.isStraight();
    boolean containsQueenOfSpades = currentHand.containsQueenOfSpades();
    if (isFlush && isStraight) {
      resultLabel.setText("Straight Flush!");
    } else if (isFlush) {
      resultLabel.setText("Flush!");
    } else if (isStraight) {
      resultLabel.setText("Straight!");
    } else {
      resultLabel.setText("Ingen spesielle kombinasjoner.");
    }

    if (currentHand.containsQueenOfSpades()) {
      queenOfSpadesResultLabel.setText("Du har spar dronning i handa!");
    }
  }

  private StackPane createCardView(PlayingCard card) {
    String imagePath = getSuitImage(card.getSuit());

    ImageView suitImage = new ImageView(new Image(imagePath));
    suitImage.setFitWidth(40);
    suitImage.setFitHeight(40);
    suitImage.setPreserveRatio(true);

    Label valueLabel = new Label(String.valueOf(card.getValue()));
    valueLabel.setAlignment(Pos.TOP_LEFT);

    VBox cardValues = new VBox(valueLabel, suitImage);

    Rectangle cardShape = new Rectangle(60, 80);
    cardShape.setFill(Color.WHITE);
    cardShape.setStroke(Color.BLACK);

    StackPane cardBox = new StackPane(cardShape, cardValues);
    cardBox.setAlignment(Pos.CENTER);
    return cardBox;
  }

  private String getSuitImage(char suit) {
    switch (suit) {
      case 'S':
        return "Spade.png";
      case 'H':
        return "Hearts.png";
      case 'D':
        return "Diamonds.png";
      case 'C':
        return "Clover.png";
      default:
        return "unknown.png";
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
