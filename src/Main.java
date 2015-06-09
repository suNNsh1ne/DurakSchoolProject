import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;


public class Main extends Application {
	/*
	Deck deck = new Deck();
	Dealer dealer = new Dealer(deck);
	Player player1 = new Player();
	Player player2 = new Player();
	ArrayList<Player> players = new ArrayList<Player>();
	Table table = new Table();
	*/
	HBox player1Hand = new HBox();

	//VIELLEICHT LISTENER F�R NEUE KARTEN // DENKE ABER NICHT ZWINGEND NOTWENDIG
	private Parent createGui()
	{
		Stage optionStage;
		
		//players.add(player1);
		//players.add(player2);
		
		
		//Instanziierungen
		AnchorPane root = new AnchorPane();
		ToolBar tBar = new ToolBar();
		ToolBar gameTBar = new ToolBar();
//		HBox player1Hand = new HBox();
		Button startButton = new Button("Start");
		Button exitButton = new Button("Exit");
		Button takeFieldCards = new Button("Draw Cards");
		
		//Instanziierung 2. Stage f�r die Abfrage der Spieler anzahl
		AnchorPane pane = new AnchorPane();
		pane.getStyleClass().add("pane");
		Button acceptButton = new Button("Akzeptieren");
		AnchorPane.setTopAnchor(acceptButton, 65.0);
		AnchorPane.setLeftAnchor(acceptButton, 50.0);
		AnchorPane.setRightAnchor(acceptButton, 0.0);
		TextField playerCount = new TextField();
		AnchorPane.setTopAnchor(playerCount, 25.0);
		AnchorPane.setLeftAnchor(playerCount, 0.0);
		AnchorPane.setRightAnchor(playerCount, 0.0);
		Label playerLabel = new Label("Spieleranzahl: (2-4 Spieler)");
		Scene scene2 = new Scene(pane, 200, 100);
		scene2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		pane.getChildren().addAll(playerLabel, playerCount, acceptButton);
		optionStage = new Stage();
		optionStage.setScene(scene2);
		optionStage.initModality(Modality.APPLICATION_MODAL);
		
		
		//Root Fenster settings
		root.getStyleClass().add("background");
		root.getChildren().addAll(tBar, gameTBar, player1Hand);
		
		//Toolbar settings
		tBar.getItems().addAll(startButton, exitButton);
		tBar.getStyleClass().add("toolbar");
		AnchorPane.setTopAnchor(tBar, 0.0);
		AnchorPane.setLeftAnchor(tBar, 0.0);
		AnchorPane.setRightAnchor(tBar, 0.0);
		
		//GameToolbar settings
		gameTBar.getItems().addAll(takeFieldCards);
		gameTBar.getStyleClass().add("toolbar");
		AnchorPane.setTopAnchor(gameTBar, 0.0);
		AnchorPane.setLeftAnchor(gameTBar, 650.0);
		AnchorPane.setRightAnchor(gameTBar, 0.0);

		//TakeCard button �berarbeitet pr�ft nur einmal
		/*if(table.size() == 0)
		{
			takeFieldCards.setDisable(false);
		}
		else takeFieldCards.setDisable(false);
		*/
		
		//Player1Hand settings
		player1Hand.setPadding(new Insets(-50));
		player1Hand.setAlignment(Pos.CENTER);
		player1Hand.setSpacing(-66);
		AnchorPane.setBottomAnchor(player1Hand, 0.0);
		AnchorPane.setLeftAnchor(player1Hand, 100.0);
		AnchorPane.setRightAnchor(player1Hand, 100.0);
		
		//nur zur �berpr�fung ob karten angezeigt werden
		/*for(int i = 0; i < 6;i++)
		{
		player1.addCard(deck.getCard());
		}

		for(Card cards : player1.getHand())
		{
			cards.turn_card();
			player1Hand.getChildren().add(cards);
		}
		*/
		
		
		// BUTTON FUNKTION
		
		//Akzeptieren der Spieleranzahl und Durak starten
		acceptButton.setOnAction(new EventHandler<ActionEvent>()
				{
			@Override public void handle(ActionEvent e)
			{
				String text = playerCount.getText();
				if(text.matches("[2-4]"))
				{
					System.out.println(playerCount.getText());
					Durak durak = new Durak(Integer.parseInt(playerCount.getText()));
					optionStage.close();
					//Durak durak = new Durak(Integer.parseInt(playerCount.getText()));
					//durak.run(Integer.parseInt(playerCount.getText()));
				}
				else
				{
					Stage errorStage = new Stage();
					Label playerLabel = new Label("Ung�ltige Eingabe");
					Scene errorScene = new Scene(playerLabel);
					errorStage.initModality(Modality.WINDOW_MODAL);
					errorStage.setScene(errorScene);
					errorStage.show();
				}
			}
				});
		
		//Weiterleitung zu Abfrage der Spieleranzahl
		startButton.setOnAction (new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e){
				System.out.println("Spiel startet");
				optionStage.showAndWait();
				
//				Durak durak = new Durak();
//				durak.run();
			}
		});
		
		//Schlie�en der Anwendung
		exitButton.setOnAction(new EventHandler<ActionEvent>()
				{
			@Override public void handle(ActionEvent e)
			{
				Platform.exit();
			}
				});

		//nehmen der Karten vom Table // momentan umgeschrieben zum karten ziehen zum testen
		/*
		takeFieldCards.setOnAction(new EventHandler<ActionEvent>()
				{
			@Override public void handle(ActionEvent e)
			{
				Card card = deck.getCard();
				card.turn_card();
				player1Hand.getChildren().add(card);
			}
				});
				*/

		
		return root;
		
	}

/*	private void createGame(Durak durak)
	{
		player1Hand.getChildren().add(durak.getDeck().getCard());
	}
	*/

	//starten des Fensters
	@Override
	public void start(Stage primaryStage) {
		Scene scene = new Scene(createGui());
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setWidth(800);
		primaryStage.setHeight(600);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Durak");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
		
		

		
	}
}

