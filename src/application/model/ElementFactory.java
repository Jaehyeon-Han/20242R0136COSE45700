package model;

import java.io.File;
import java.util.List;
import java.util.UUID;

import common.Color;
import common.Point;

public class ElementFactory {
	public Element create(String id, String type, Point p, Point q, 
			Color color, File imageFile, String text) {
		switch(type) {
		case "line": return new Line(id, p, q, color);
		case "rectangle": return new Rectangle(id, p, q, color);
		case "ellipse": return new Ellipse(id, p, q, color);
		case "image": return new Image(id, p, q, imageFile);
		case "text": return new Text(id, p, q, color, text);
		default: throw new IllegalArgumentException("Trying to create an illegal type");
		}
	}
	
	public Element createComposite(List<Element> elements) {
		String id = UUID.randomUUID().toString();
			Composite composite = new Composite(id);
			for(Element element : elements) {
				composite.addChild(element);
			}
			return composite;
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
