package tile;

import java.awt.image.BufferedImage;

public class Tile {
    //class with instance variables, but no constructor
    private BufferedImage image;
    private boolean collision = false;
    
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public boolean isCollision() {
		return collision;
	}
	public void setCollision(boolean collision) {
		this.collision = collision;
	}

    
}
