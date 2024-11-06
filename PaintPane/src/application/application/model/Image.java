package model;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Image extends Element {
	private BufferedImage image;
	
	public Image(double x1, double y1, double x2, double y2, File imageSource) {
		super(x1, y1, x2, y2);
		try {
			this.image = ImageIO.read(imageSource);
		} catch (Exception e) {
			return;
		}
	}

	public BufferedImage getImage() {
		return image;
	}
}
