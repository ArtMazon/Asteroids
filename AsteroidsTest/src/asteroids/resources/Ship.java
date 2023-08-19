package asteroids.resources;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public class Ship extends Character{
	
	public Ship(int x, int y) {
		super(x,y);
	}

	
	public void shipBreak() {
		super.setMovement(new Point2D(0,0));
	}

}
