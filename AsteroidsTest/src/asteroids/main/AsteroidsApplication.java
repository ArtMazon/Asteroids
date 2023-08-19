package asteroids.main;

import asteroids.resources.Ship;
import asteroids.resources.Asteroid;

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
		
		Ship ship = new Ship(150,50);
		Asteroid asteroid = new Asteroid(50, 50);

	
		

		pane.getChildren().add(ship.getCharacter());
		pane.getChildren().add(asteroid.getCharacter());
		
		asteroid.turnRight();
		asteroid.turnRight();
		asteroid.accelerate();
		asteroid.accelerate();
		
		
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
					ship.turnLeft();
				}
				
				if(pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
					ship.turnRight();
				}
				
				if(pressedKeys.getOrDefault(KeyCode.UP, false)) {
					ship.accelerate();
				}
				
				if(pressedKeys.getOrDefault(KeyCode.DOWN, false)) {
					ship.shipBreak();
				}
				
				ship.move();
				asteroid.move();
				
				if(ship.collide(asteroid)) {
					stop();
				}
				
				
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
