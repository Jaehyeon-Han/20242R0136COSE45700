package model;

public class Text extends Element {
	private double fontSize = 13;
	private String content, fontFamily = "Arial";
	
	public Text(double x1, double y1, String content) {
		this.x1 = x1;
		this.y1 = y1;
		this.content = content;
	}
	
	@Override
	public void resize(double width, double height) {
		// Text cannot be resized (yet).
	}
	
	public double getFontSize() {
		return fontSize;
	}
	public void setFontSize(double fontSize) {
		this.fontSize = fontSize;
	}
	public String getFontFamily() {
		return fontFamily;
	}
	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
