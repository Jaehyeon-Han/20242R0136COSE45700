package model;

import java.io.File;

public class FxFactory {
	private static FxFactory instance = new FxFactory();
	public static FxFactory getInstance() {
		return instance;
	}
	
	Color selectedColor;
	File imageFile;
//	String text;

	public FxNode create(Type type, double x1, double y1, double x2, double y2) {
		Color color = this.selectedColor.getClone();
		switch (type) {
		case Type.LINE: {
			FxLine lineNode = new FxLine(x1, y1, x2, y2);
			lineNode.setColor(color);
			return lineNode;
		}
		case Type.RECTANGLE: {
			FxRectangle rectangleNode = new FxRectangle(Math.min(x1, x2),
					Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
			rectangleNode.setColor(color);
			return rectangleNode;
		}
		case Type.ELLIPSE: {
			FxEllipse ellipseNode = new FxEllipse((x1 + x2) / 2, (y1 + y2) / 2,
					Math.abs(x1 - x2) / 2, Math.abs(y1 - y2) / 2);
			ellipseNode.setColor(color);
			return ellipseNode;
		}
		case Type.IMAGE: {
			FxImage imageNode = new FxImage(Math.min(x1, x2),
					Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2), imageFile);
			return imageNode;
		}
//		case Type.TEXT: {
//			FxText textNode = new FxText(x1, y1, text);
//			textNode.setColor(color);
//			return textNode;
//		}
		default:throw new IllegalArgumentException("");
		}
	}

	public void setColor(Color color) {
		if (color != null) {
			this.selectedColor = color;
		}
	}

	public void setImageSource(File imageFile) {
		if (imageFile != null) {
			this.imageFile = imageFile;
		}
	}


}
