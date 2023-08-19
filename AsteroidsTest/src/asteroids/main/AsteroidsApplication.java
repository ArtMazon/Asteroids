package asteroids.main;

import java.util.HashMap;
import java.util.Map;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.geometry.Point2D;
import javafx.stage.Stage;
import javafx.scene.shape.Polygon;

public class AsteroidsApplication extends Application {

	@Override
	public void start(Stage window) {

		Map<KeyCode, Boolean> pressedKeys = new HashMap<>();

		Pane pane = new Pane();
		pane.setPrefSize(600, 400);

		Polygon ship = new Polygon(-5, -5, 10, 0, -5, 5);
		ship.setTranslateX(300);
		ship.setTranslateY(200);
		ship.setRotate(30);
		
		Point2D movement = new Point2D(1,0);

		pane.getChildren().add(ship);
		Scene scene = new Scene(pane);

		scene.setOnKeyPressed((event) -> {

			pressedKeys.put(event.getCode(), Boolean.TRUE);

		});

		scene.setOnKeyReleased((event) -> {
			pressedKeys.put(event.getCode(), Boolean.FALSE);
		});
		
		
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				if(pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
					ship.setRotate(ship.getRotate()-5);
				}
				
				if(pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
					ship.setRotate(ship.getRotate()+5);
				}
				
				ship.setTranslateX(ship.getTranslateX()+movement.getX());
				
			}
		}.start();

		window.setScene(scene);
		window.setTitle("Asteroids");

		window.show();
	}

	public static void main(String[] args) {

		launch(args);
	}

}
