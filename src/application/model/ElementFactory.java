package model;

import java.io.File;
import java.util.List;
import java.util.UUID;

import common.Color;
import common.Point;

public class ElementFactory {
	public Element create(String type, Point p, Point q, 
			Color color, File imageFile, String text) {
		String id = UUID.randomUUID().toString();
		
		switch(type) {
		case "line": return new Line(id, p, q, color);
		case "rectangle": return new Rectangle(id, p, q, color);
		case "ellipse": return new Ellipse(id, p, q, color);
		case "image": return new Image(id, p, q, imageFile);
		case "text": return new Text(id, p, q, color, text);
		default: throw new IllegalArgumentException("Trying to create an illegal type");
		}
	}
	
	public Composite createComposite(List<Element> elements) {
		String id = UUID.randomUUID().toString();
		return new Composite(id, elements);
	}
	
	private static class CompositeHelper {
		private static final double maxVal = 99999;
		
		private static double x1 = maxVal, y1 = maxVal;
		
		private static void update() {
			for(Element element : children) {
				x1 = Math.min(x1, element.x1);
				y1 = Math.min(y1, element.y1);
				x2 = Math.max(x2, element.x2);
				y2 = Math.max(y2, element.y2);
			}
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
