package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

import javax.imageio.ImageIO;

import common.Point;
import common.PropertyDTO;

public class Image extends Element {
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
	public PropertyDTO toDTO() {
		PropertyDTO.Builder builder = new PropertyDTO.Builder("image", p, q);
		PropertyDTO dto = builder.setId(this.id)
			.setImageFile(imageFile)
			.build();
		return dto;
	}
}
