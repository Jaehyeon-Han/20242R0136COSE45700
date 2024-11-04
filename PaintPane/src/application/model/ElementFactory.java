package model;

import java.util.List;

public class ElementFactory {
	private static ElementFactory instance = new ElementFactory();
	
	String type;
	double x1, y1, x2, y2;
	Color color = Color.getInstance().getClone();
	String imageSource = "default/image";
	
	public Element create(String type, double x1, double y1, double x2, double y2) {
		switch (type.toLowerCase()) {
		case "line": return new Line(x1, y1, x2, y2, color);
		case "ellipse": return new Ellipse(x1, y1, x2, y2, color);
		case "rectangle": return new Rectangle(x1, y1, x2, y2, color);
		default: throw new IllegalArgumentException("");
		}
	}
	
	public Element createComposite(List<Element> elements) {
		Composite composite = new Composite();
		for(Element element : elements) {
			composite.add(element);
		}
		return composite;
	}
	
	public void setColor(Color color) {
		if(color != null) {
			this.color = color;
		}
	}

	public void setImageSource(String imageSource) {
		if(imageSource != null ) {
			this.imageSource = imageSource;
		}
	}

	public static ElementFactory getInstance() {
		return instance;
	}
}
