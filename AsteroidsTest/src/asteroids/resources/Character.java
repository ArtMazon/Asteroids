package asteroids.resources;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public abstract class Character {
	
	private Polygon character;
	private Point2D movement;

	public Character(int x, int y) {
		this.character = new Polygon(-5, -5, 10, 0, -5, 5);
		this.character.setTranslateX(x);
		this.character.setTranslateY(y);

		this.movement = new Point2D(0, 0);

	}
	
	public void setMovement(Point2D movement) {
		this.movement = movement;
	}

	public Polygon getCharacter() {

		return this.character;
	}
	
	
	public void turnLeft() {
		this.character.setRotate(this.character.getRotate() - 5);
	}
	
	public void turnRight() {
		this.character.setRotate(this.character.getRotate() + 5);

		
	}
	
	public void move() {
		this.character.setTranslateX(this.character.getTranslateX() + this.movement.getX());
		this.character.setTranslateY(this.character.getTranslateY() + this.movement.getY());
	}
	
	public void accelerate() {
		double changeX = Math.cos(Math.toRadians(this.character.getRotate()));
		double changeY = Math.sin(Math.toRadians(this.character.getRotate()));
		
		changeX *= 0.05;
		changeY *= 0.05;
		
		
		this.movement = this.movement.add(new Point2D(changeX,changeY));
		
	}

}
