package model;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import common.Point;
import common.ModelInfo;

public class Image extends BoxedElement {
    private File imageFile;
	private BufferedImage image;

    public Image(String id, Point p, Point q, File imageFile) {
        super(id, p, q);
        this.imageFile = imageFile;
        try {
            this.image = ImageIO.read(imageFile);
            if (this.image == null) {
                throw new IllegalArgumentException("Invalid image file: " + imageFile);
            }
        } catch (Exception e) {
            System.err.println("Error loading image file: " + imageFile);
            e.printStackTrace();
            this.image = null;
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public boolean hasImage() {
        return image != null;
    }

	@Override
	public ModelInfo getModelInfo() {
		ModelInfo.Builder builder = new ModelInfo.Builder(id, "image", p, q);
		ModelInfo dto = builder
			.setImageFile(imageFile)
			.build();
		return dto;
	}
}
