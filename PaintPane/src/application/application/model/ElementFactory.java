package model;

import java.io.File;
import java.util.List;

public class ElementFactory {
	private static ElementFactory instance = new ElementFactory();
	Color selectedColor;
	private File imageSource;
//	private String text;
	
	public Element create(Type type, double x1, double y1, double x2, double y2) {
		Color color = this.selectedColor.getClone();
		switch (type) {
		case Type.LINE: return new Line(x1, y1, x2, y2, color);
		case Type.ELLIPSE: return new Ellipse(x1, y1, x2, y2, color);
		case Type.RECTANGLE: return new Rectangle(x1, y1, x2, y2, color);
		case Type.IMAGE: return new Image(x1, y1, x2, y2, imageSource);
//		case "text": return new Text(x1, y1, text);
		default: throw new IllegalArgumentException("");
		}
	}
	
	public Composite createComposite(List<Element> elements) {
		Composite composite = new Composite();
		for(Element element : elements) {
			composite.add(element);
		}
		return composite;
	}
	
	public void setColor(Color color) {
		if(color != null) {
			this.selectedColor = color;
		}
	}

	public void setImageSource(File imgFile) {
		if(imgFile != null ) {
			this.imageSource = imgFile;
		}
	}
	
//	public void setText(String text) {
//		this.text = text;
//	}

	public static ElementFactory getInstance() {
		return instance;
	}
}
