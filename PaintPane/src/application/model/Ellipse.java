package model;

public class Ellipse extends Element {
	public Ellipse(double x1, double y1, double x2, double y2, Color color) {
		super(x1, y1, x2, y2);
		this.setColor(color);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
