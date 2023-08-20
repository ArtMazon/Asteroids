package asteroids.main;

import asteroids.resources.Ship;
import asteroids.resources.Asteroid;
import asteroids.resources.Projectile;
import asteroids.resources.Character;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
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
import javafx.scene.text.Text;

public class AsteroidsApplication extends Application {
	public static int WIDTH = 300;
	public static int HEIGHT = 200;

	@Override
	public void start(Stage window) {

		Map<KeyCode, Boolean> pressedKeys = new HashMap<>();

		Pane pane = new Pane();
		pane.setPrefSize(WIDTH, HEIGHT);
		
		Text score = new Text(10,20,"Points: 0");
		
		AtomicInteger points = new AtomicInteger();

		Ship ship = new Ship(WIDTH / 2, HEIGHT / 2);

		List<Asteroid> asteroids = new ArrayList<>();

		List<Projectile> projectiles = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			Random rdm = new Random();

			Asteroid asteroid = new Asteroid(rdm.nextInt(WIDTH / 3), rdm.nextInt(HEIGHT / 3));

			asteroids.add(asteroid);

		}

		//
		pane.getChildren().add(ship.getCharacter());
		pane.getChildren().add(score);

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

				if (pressedKeys.getOrDefault(KeyCode.SPACE, false) && projectiles.size() < 3) {
					Projectile projectile = new Projectile((int) ship.getCharacter().getTranslateX(),
							(int) ship.getCharacter().getTranslateY());
					projectile.getCharacter().setRotate(ship.getCharacter().getRotate());

					projectiles.add(projectile);

					projectile.accelerate();
					projectile.setMovement(projectile.getMovement().normalize().multiply(3));

					pane.getChildren().add(projectile.getCharacter());

				}

				ship.move();

				projectiles.forEach(projectile -> projectile.move());

				asteroids.forEach(asteroid -> asteroid.move());

				asteroids.forEach(asteroid -> {
					if (ship.collide(asteroid)) {
						stop();
					}

					projectiles.forEach(projectile -> {

						asteroids.forEach(asteroido -> {
							if (projectile.collide(asteroido)) {
								projectile.setAlive(false);
								asteroido.setAlive(false);
							}
							
					

						});
						
						if(!projectile.isAlive()) {
							score.setText("Points: "+ points.addAndGet(1000));
						}
						

					});
					
					
					removeCharacter(projectiles,pane);
					removeCharacter(asteroids,pane);
										

				});

			}
		}.start();

		window.setScene(scene);
		window.setTitle("Asteroids");

		window.show();
	}
	
	
	public void removeCharacter(List<? extends Character> characterList, Pane pane) {
		
		characterList.stream()
		.filter(character -> !character.isAlive())
		.forEach(character -> pane.getChildren().remove(character.getCharacter()));
		
		characterList.removeAll(characterList.stream()
		.filter(character-> !character.isAlive())
		.collect(Collectors.toList()));
	}

	public static void main(String[] args) {

		launch(args);
	}

}
