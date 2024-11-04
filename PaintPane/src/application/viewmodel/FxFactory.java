package viewmodel;

import model.FxNode;
import model.FxRectangle;
import model.Color;
import model.FxEllipse;
import model.FxLine;

public class FxFactory {
	private static FxFactory instance = new FxFactory();

	String type;
	double x1, y1, x2, y2;
	Color color = Color.getInstance().getClone();
	String imageSource = "default/image";

	public FxNode create(String type, double x1, double y1, double x2, double y2) {
		switch (type.toLowerCase()) {
		case "line": {
			FxLine line = new FxLine(x1, y1, x2, y2);
			line.setStroke(getFxColor());
			return line;
		}
		case "rectangle": {
			FxRectangle rectangle = new FxRectangle(Math.min(x1, x2),
					Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
			rectangle.setFill(getFxColor());
			return rectangle;
		}

		case "ellipse": {
			FxEllipse ellipse = new FxEllipse((x1 + x2) / 2, (y1 + y2) / 2,
					Math.abs(x1 - x2) / 2, Math.abs(y1 - y2) / 2);
			ellipse.setFill(getFxColor());
			return ellipse;
		}
		default:throw new IllegalArgumentException("");
		}
	}

	public void setColor(Color color) {
		if (color != null) {
			this.color = color;
		}
	}

	public void setImageSource(String imageSource) {
		if (imageSource != null) {
			this.imageSource = imageSource;
		}
	}

	public static FxFactory getInstance() {
		return instance;
	}
	
	private javafx.scene.paint.Color getFxColor() {
		return javafx.scene.paint.Color.rgb(color.getRed(), color.getGreen(), color.getBlue());
	}
}
