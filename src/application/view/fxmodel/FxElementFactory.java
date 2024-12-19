package view.fxmodel;

import java.io.File;

import common.Color;
import common.Point;
import model.Element;
import model.ElementFactory;
import model.Ellipse;
import model.Image;
import model.Line;
import model.Rectangle;
import model.Text;

public class FxElementFactory {
	public FxElement create(String id, String type, Point p, Point q, 
			Color color, File imageFile, String text) {
		switch(type) {
		case "line": return new FxLine(id, p, q, color);
		case "rectangle": return new FxRectangle(id, p, q, color);
		case "ellipse": return new FxEllipse(id, p, q, color);
		case "image": return new FxImage(id, p, q, imageFile);
		case "text": return new FxText(id, p, q, color, text);
		default: throw new IllegalArgumentException("Trying to create an illegal type");
		}
	}

	public FxElement createComposite(String id, Point p, Point q) {
		return new FxComposite(id, p, q);
	}

	// Singleton
	private FxElementFactory() {
	}

	private static class FxElementFactoryHelper {
		private static FxElementFactory INSTANCE = new FxElementFactory();
	}

	public static FxElementFactory getInstance() {
		return FxElementFactoryHelper.INSTANCE;
	}
}
