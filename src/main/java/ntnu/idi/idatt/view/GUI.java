package ntnu.idi.idatt.view;

import java.util.List;
import java.util.stream.Collectors;
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
import ntnu.idi.idatt.controller.GameController;
import ntnu.idi.idatt.model.PlayingCard;

public class GUI extends Application {

  private final GameController gameController = new GameController();
  private final HBox cardDisplay = new HBox(10);
  private final Label resultLabel = new Label("");
  private final Label queenOfSpadesResultLabel = new Label("");
  private final Label sumOfFacesResultLabel = new Label("");
  private final Label cardOfHeartsResultLabel = new Label("");

  @Override
  public void start(Stage primaryStage) {
    Button dealHandButton = new Button("Draw cards");
    Button checkHandButton = new Button("Check hand");

    dealHandButton.setOnAction(e -> drawHand());
    checkHandButton.setOnAction(e -> checkHand());

    VBox root = new VBox(15, dealHandButton, checkHandButton, cardDisplay, resultLabel,
        queenOfSpadesResultLabel, sumOfFacesResultLabel, cardOfHeartsResultLabel);
    root.setAlignment(Pos.CENTER);
    root.setStyle("-fx-padding: 20;");

    Scene scene = new Scene(root, 600, 400);
    primaryStage.setTitle("CardGame Xtreme");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void drawHand() {
    try {
      gameController.drawHand(5);
      cardDisplay.getChildren().clear();
      resultLabel.setText("Combo: ");
      queenOfSpadesResultLabel.setText("Has Queen of Spades: ");
      sumOfFacesResultLabel.setText("Sum of faces: ");
      cardOfHeartsResultLabel.setText("Cards of Hearts: ");

      for (PlayingCard card : gameController.getCurrentHandCards()) {
        cardDisplay.getChildren().add(createCardView(card));
      }
    } catch (IllegalStateException e) {
      gameController.resetGame();
      cardDisplay.getChildren().clear();
      resultLabel.setText("Not enough cards left in deck to draw a new hand. Game is reset.");
      queenOfSpadesResultLabel.setText("Draw cards to start again.");
    }
  }


  private void checkHand() {
    resultLabel.setText("Combo: " + gameController.checkHand());
    queenOfSpadesResultLabel.setText("Has Queen Of Spades: " + gameController.checkQueenOfSpades());
    sumOfFacesResultLabel.setText("Sum of faces: " + gameController.checkSumOfFaces());
    cardOfHeartsResultLabel.setText(
        "Cards of Hearts: " + cardListToString(gameController.checkCardOfHearts()));
  }

  private StackPane createCardView(PlayingCard card) {
    String imagePath = gameController.getSuitImage(card.getSuit());

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

  private String cardListToString(List<PlayingCard> cards) {
    if (cards.isEmpty()) {
      return "None :(";
    }
    return cards.stream().map(card -> "" + card.getSuit() + card.getValue())
        .collect(Collectors.joining(", "));
  }

  public static void main(String[] args) {
    launch(args);
  }
}
