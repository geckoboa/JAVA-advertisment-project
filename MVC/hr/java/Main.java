package hr.java.vjezbe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.niti.DatumObjaveNit;
import hr.java.vjezbe.niti.NajjeftinijiArtiklNit;
import hr.java.vjezbe.niti.PrivatniKorisnikNajduzeImeNit;
import hr.java.vjezbe.niti.ZadnjeUneseniAutomobil;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

	private static Stage stage;
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	@Override
	public void start(Stage primaryStage) {

		stage = primaryStage;
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Menu.fxml"));


			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);
			primaryStage.show();

			Timeline objava = new Timeline(new KeyFrame(Duration.seconds(10), new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Platform.runLater(new DatumObjaveNit());
				}
			}));
			objava.setCycleCount(Timeline.INDEFINITE);
			objava.play();
			
			Timeline najduzeIme = new Timeline(new KeyFrame(Duration.seconds(20), new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Platform.runLater(new PrivatniKorisnikNajduzeImeNit());
				}
			}));
			najduzeIme.setCycleCount(Timeline.INDEFINITE);
			najduzeIme.play();
			
			Timeline najJeftinijiArtikl = new Timeline(new KeyFrame(Duration.seconds(30), new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Platform.runLater(new NajjeftinijiArtiklNit());
				}
			}));
			najJeftinijiArtikl.setCycleCount(Timeline.INDEFINITE);
			najJeftinijiArtikl.play();
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setMainPage(BorderPane root) {

		Scene scene = new Scene(root, 400, 500);
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}
