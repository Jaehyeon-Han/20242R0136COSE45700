package common;

public class Color {
	private int red, green, blue;

	public Color(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public Color(javafx.scene.paint.Color color) {
		red = (int) (color.getRed() * 255);
		green = (int) (color.getGreen() * 255);
		blue = (int) (color.getBlue() * 255);
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
	
	public javafx.scene.paint.Color toFxColor() {
		return javafx.scene.paint.Color.rgb(red, green, blue);
	}
}