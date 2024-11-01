package viewmodel;

import model.Element;
import model.Point;

public class ElementFactory {
	private static ElementFactory instance = new ElementFactory();

	public Element getElement(Point a, Point b, String shape, String color) {
		return switch (shape) {
		case "line" -> new model.Line(a, b, color);
		case "rectangle" -> new model.Rectangle(a, b, color);
		case "ellipse" -> new model.Ellipse(a, b, color);
		default -> throw new IllegalArgumentException("Unexpected value: " + shape);
		};
	}

	public javafx.scene.Node getNode(Point a, Point b, String shape, String color) {
		double x1 = a.getX(), x2 = b.getX(), y1 = a.getY(), y2 = b.getY();

		switch (shape) {
		case "line" -> {
			javafx.scene.shape.Line line = new javafx.scene.shape.Line(x1, y1, x2, y2);
			line.setStroke(ColorMapper.mapColor(color));
			return line;
		}
		case "rectangle" -> {
			javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle(Math.min(x1, x2),
					Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
			rectangle.setFill(ColorMapper.mapColor(color));
			return rectangle;
		}

		case "ellipse" -> {
			javafx.scene.shape.Ellipse ellipse = new javafx.scene.shape.Ellipse((x1 + x2) / 2, (y1 + y2) / 2,
					Math.abs(x1 - x2) / 2, Math.abs(y1 - y2) / 2);
			ellipse.setFill(ColorMapper.mapColor(color));
			return ellipse;
		}
		default -> throw new IllegalArgumentException("Unexpected value: " + shape);
		}
	}

	public static ElementFactory getInstance() {
		return instance;
	}
}
