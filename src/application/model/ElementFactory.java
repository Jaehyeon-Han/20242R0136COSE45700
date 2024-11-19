package model;

import java.io.File;

import common.Color;
import common.Point;

public class ElementFactory {
	public Element create(String type, Point p, Point q, 
			Color color, File imageFile, String text) {
		switch(type) {
		case "line": return new Line(p, q, color);
		case "rectangle": return new Rectangle(p, q, color);
		case "ellipse": return new Ellipse(p, q, color);
		case "image": return new Image(p, q, imageFile);
		case "text": return new Text(p, q, color, text);
		default: throw new IllegalArgumentException("Trying to create an illegal type");
		}
	}

	// Singleton
	private ElementFactory() {
	}

	private static class ElementFactoryHelper {
		private static ElementFactory INSTANCE = new ElementFactory();
	}

	public static ElementFactory getInstance() {
		return ElementFactoryHelper.INSTANCE;
	}

}
