package model;

public class Color {
	private int red, green, blue;

	public Color(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}

	public void setColor(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public Color getClone() {
		return new Color(red, green, blue);
	}
	
	public String toHexString() {
		return Integer.toHexString(red) + Integer.toHexString(green) + Integer.toHexString(blue);
	}
	
	public javafx.scene.paint.Color toFxColor() {
		return javafx.scene.paint.Color.rgb(red, green, blue);
	}
}
