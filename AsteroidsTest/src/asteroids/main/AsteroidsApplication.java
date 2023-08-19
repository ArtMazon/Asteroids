package asteroids.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AsteroidsApplication extends Application {

	@Override
	public void start(Stage window) {

		Pane pane = new Pane();
		pane.setPrefSize(300, 200);

		Scene scene = new Scene(pane);

		window.setScene(scene);
		window.setTitle("Asteroids");

		window.show();
	}

	public static void main(String[] args) {

		launch(args);
	}

}
