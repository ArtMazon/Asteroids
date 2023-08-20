package asteroids.main;

import asteroids.resources.Ship;
import asteroids.resources.Asteroid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;

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

		Ship ship = new Ship(150, 50);

		List<Asteroid> asteroids = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			Random rdm = new Random();

			Asteroid asteroid = new Asteroid(rdm.nextInt(100), rdm.nextInt(100));

			asteroids.add(asteroid);

		}

		//
		pane.getChildren().add(ship.getCharacter());

		asteroids.forEach(asteroid -> pane.getChildren().add(asteroid.getCharacter()));

		//

		Scene scene = new Scene(pane);

		//

		scene.setOnKeyPressed((event) -> {

			pressedKeys.put(event.getCode(), Boolean.TRUE);

		});

		scene.setOnKeyReleased((event) -> {
			pressedKeys.put(event.getCode(), Boolean.FALSE);
		});

		new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
					ship.turnLeft();
				}

				if (pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
					ship.turnRight();
				}

				if (pressedKeys.getOrDefault(KeyCode.UP, false)) {
					ship.accelerate();
				}

				if (pressedKeys.getOrDefault(KeyCode.DOWN, false)) {
					ship.shipBreak();
				}

				ship.move();

				asteroids.forEach(asteroid -> asteroid.move());

				asteroids.forEach(asteroid -> {
					if (ship.collide(asteroid)) {
						stop();
					}

				});

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
